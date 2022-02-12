package com.github.kloping.iwanna.buy.impl.simple;

import com.github.kloping.iwanna.buy.api.Commodity;
import com.github.kloping.iwanna.buy.api.Event;
import com.github.kloping.iwanna.buy.api.Shop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author github.kloping
 */
public class SimpleShop implements Shop {

    @Override
    public Event next() {
        return null;
    }

    @Override
    public List<Commodity> all() {
        return null;
    }

    @Override
    public List<Commodity> change(int id, int offset) {
        return null;
    }

    @Override
    public Map<Integer, Commodity> map() {
        Map<Integer, Commodity> map = new HashMap<>();
        for (Commodity commodity : all()) {
            map.put(commodity.getId(), commodity);
        }
        return map;
    }
}
