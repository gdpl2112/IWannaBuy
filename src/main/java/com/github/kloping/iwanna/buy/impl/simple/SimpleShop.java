package com.github.kloping.iwanna.buy.impl.simple;

import com.github.kloping.iwanna.buy.api.*;
import io.github.kloping.file.FileUtils;
import io.github.kloping.serialize.HMLObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.github.kloping.iwanna.buy.impl.simple.SimpleSys.RANDOM;

/**
 * @author github.kloping
 */
public class SimpleShop implements Shop, CenterFindable, Savable<Shop> {

    public SimpleShop(String path) {
    }

    /**
     * used by hml
     */
    public SimpleShop() {
    }

    @Override
    public Shop apply() {
        File file = new File(getCenter().basePath(), getCenter().shopPath());
        FileUtils.putStringInFile(HMLObject.toHMLString(this), file);
        return this;
    }

    @Override
    public int getNum() {
        return 6;
    }

    @Override
    public Event next() {
        Event event = getCenter().getEvent();
        changed.forEach((k, v) -> {
            k.changePrice(-v);
        });
        event.run();
        shake();
        return event;
    }

    @Override
    public List<Commodity> all() {
        return new ArrayList<>(commodityMap.values());
    }

    private Map<Integer, Commodity> commodityMap = new HashMap<>();

    private void shake() {
        commodityMap.clear();
        for (Commodity commodity : changed.keySet()) {
            commodityMap.put(commodity.getId(), commodity);
        }
        changed.clear();
        for (int i = commodityMap.size(); i < getNum(); i++) {
            append0();
        }
    }

    private boolean append() {
        Commodity commodity = getCenter().commodities().get(RANDOM.nextInt(getCenter().commodities().size()));
        if (commodityMap.containsKey(commodity.getId())) {
            return false;
        } else {
            commodityMap.put(commodity.getId(), commodity);
            return true;
        }
    }

    private void append0() {
        while (true) {
            if (append()) {
                break;
            }
        }
    }

    private Map<Commodity, Integer> changed = new HashMap<>();

    @Override
    public List<Commodity> change(int id, int offset) {
        for (Commodity commodity : getCenter().commodities()) {
            if (commodity.getId() == id) {
                commodity.changePrice(offset);
                changed.put(commodity, offset);
            }
        }
        return getCenter().commodities();
    }

    @Override
    public Map<Integer, Commodity> map() {
        Map<Integer, Commodity> map = new HashMap<>();
        for (Commodity commodity : all()) {
            map.put(commodity.getId(), commodity);
        }
        return map;
    }

    @Override
    public Center getCenter() {
        return SimpleSys.INSTANCE;
    }
}
