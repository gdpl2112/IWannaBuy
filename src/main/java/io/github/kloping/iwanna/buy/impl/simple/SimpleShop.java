package io.github.kloping.iwanna.buy.impl.simple;

import com.alibaba.fastjson.JSON;
import io.github.kloping.iwanna.buy.api.*;
import io.github.kloping.iwanna.buy.api.listener.OnEventListener;
import io.github.kloping.iwanna.buy.impl.saver.FileHmlSaver;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.github.kloping.iwanna.buy.impl.simple.SimpleSys.RANDOM;

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

    private Saver<Shop> saver;

    @Override
    public Shop setSaver(Saver<Shop> saver) {
        this.saver = saver;
        return this;
    }

    @Override
    public Saver<Shop> getSaver() {
        if (saver == null) {
            saver = new FileHmlSaver<>(new File(getCenter().basePath(), getCenter().shopPath()));
        }
        return saver;
    }

    @Override
    public int getNum() {
        return 6;
    }

    private List<OnEventListener> listeners = new ArrayList<>();

    @Override
    public void addListener(OnEventListener eventListener) {
        listeners.add(eventListener);
    }

    @Override
    public Event next() {
        Event event = getCenter().getEvent();
        for (OnEventListener listener : listeners) {
            listener.onEventBefore(event);
        }
        event.run();
        for (OnEventListener listener : listeners) {
            listener.onEventAfter(event);
        }
        shake();
        Logger.getLogger(this.getClass()).debug("shop list " + getNames());
        getSaver().apply(this);
        changed.clear();
        return event;
    }

    private String getNames() {
        StringBuilder sb = new StringBuilder();
        commodityMap.forEach((k, v) -> {
            sb.append(v.getName()).append(",");
        });
        return sb.toString().substring(0, sb.length() - 1);
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
            Logger.getLogger(this.getClass()).info("shop append from changed:" + JSON.toJSONString(commodity));
        }
        for (int i = commodityMap.size(); i < getNum(); i++) {
            append0();
        }
        changedHistory.put(getCenter().getIndex(), changed);
    }

    private boolean append() {
        Commodity commodity = getCenter().commodities().get(RANDOM.nextInt(getCenter().commodities().size()));
        if (commodityMap.containsKey(commodity.getId())) {
            return false;
        } else {
            int r = commodity.getNowPrice().intValue() / 100;
            r = r <= 0 ? 2 : r;
            int n = RANDOM.nextInt(r);
            n = n - r / 2;
            commodity.changePrice(n);
            Logger.getLogger(this.getClass()).info("shop random commodity " + n + JSON.toJSONString(commodity));
            commodityMap.put(commodity.getId(), commodity);
            changed.put(commodity, n);
            Logger.getLogger(this.getClass()).info("shop append from sys: " + JSON.toJSONString(commodity));
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

    private Map<Integer, Map<Commodity, Integer>> changedHistory = new HashMap<>();

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
