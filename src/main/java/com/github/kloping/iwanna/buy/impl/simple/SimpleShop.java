package com.github.kloping.iwanna.buy.impl.simple;

import com.github.kloping.iwanna.buy.api.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.github.kloping.iwanna.buy.impl.simple.SimpleSys.RANDOM;

/**
 * @author github.kloping
 */
public class SimpleShop implements Shop, CenterFindable {

    public SimpleShop(String path) {

    }

    @Override
    public int getNum() {
        return 6;
    }

    @Override
    public Event next() {
        Event event = getCenter().getEvent();
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
        for (Commodity commodity : changed) {
            commodityMap.put(commodity.getId(), commodity);
        }
        changed.clear();
        for (int i = commodityMap.size(); i < getNum(); i++) {
            while (true) {
                if (append()) {
                    break;
                }
            }
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

    private List<Commodity> changed = new ArrayList<>();

    @Override
    public List<Commodity> change(int id, int offset) {
        for (Commodity commodity : getCenter().commodities()) {
            if (commodity.getId() == id) {
                commodity.changePrice(offset);
                changed.add(commodity);
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
