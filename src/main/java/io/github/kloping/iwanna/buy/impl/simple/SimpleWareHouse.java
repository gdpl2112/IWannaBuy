package io.github.kloping.iwanna.buy.impl.simple;

import io.github.kloping.file.FileUtils;
import io.github.kloping.iwanna.buy.api.Commodity;
import io.github.kloping.iwanna.buy.api.WareHouse;
import io.github.kloping.serialize.HMLObject;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static io.github.kloping.judge.Judge.isNotEmpty;

/**
 * @author github.kloping
 */
public class SimpleWareHouse implements WareHouse {
    public static SimpleWareHouse getInstance(Long qid, File dir) {
        File file = new File(dir, qid.toString());
        String hmlStr = FileUtils.getStringFromFile(file.getAbsolutePath());
        if (isNotEmpty(hmlStr)) {
            return HMLObject.parseObject(hmlStr).toJavaObject(SimpleWareHouse.class);
        } else {
            SimpleWareHouse wareHouse = new SimpleWareHouse();
            wareHouse.dir = dir;
            wareHouse.qid = qid;
            wareHouse.apply();
            return wareHouse;
        }
    }

    private File dir;
    private Long qid;
    private Integer capacity = 40;
    private List<Commodity> commodities = new LinkedList<>();

    @Override
    public Number getId() {
        return qid;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public int getSurplusCapacity() {
        int c0 = capacity;
        for (Commodity commodity : commodities) {
            c0 -= commodity.getSize();
        }
        return c0;
    }

    @Override
    public int add(Commodity commodity) {
        if (getSurplusCapacity() >= commodity.getSize()) {
            commodities.add(commodity);
        }
        apply();
        return getSurplusCapacity();
    }

    @Override
    public List<Commodity> getAll() {
        return commodities;
    }

    @Override
    public WareHouse apply() {
        File file = new File(dir, qid.toString());
        FileUtils.putStringInFile(HMLObject.toHMLString(this), file);
        return this;
    }

    @Override
    public int findCommodity(Integer id) {
        int i = 0;
        for (Commodity commodity : getAll()) {
            if (commodity.getId() == id) {
                i++;
            }
        }
        Logger.getLogger(this.getClass()).info("warehouse " + getId() + " find " + id + " " + i + "(s)");
        return i;
    }

    @Override
    public WareHouse lose(Commodity commodity, int num) {
        Logger.getLogger(this.getClass()).info("warehouse " + getId() + " lose " + commodity.getId() + " num " + num);
        int i = num;
        Iterator<Commodity> commodityIterator = getAll().iterator();
        while (commodityIterator.hasNext()) {
            Commodity c0 = commodityIterator.next();
            if (c0.getId() == commodity.getId()) {
                commodityIterator.remove();
                i--;
            }
            if (i==0){break;}
        }
        apply();
        return this;
    }
}
