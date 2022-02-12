package com.github.kloping.iwanna.buy.impl.simple;

import com.github.kloping.iwanna.buy.api.Commodity;
import com.github.kloping.iwanna.buy.api.Event;
import com.github.kloping.iwanna.buy.api.Shop;

import java.util.List;

/**
 * @author github.kloping
 */
public class SimpleShop implements Shop {
    public static final SimpleShop INSTANCE = new SimpleShop();

    @Override
    public Event next() {
        return null;
    }

    @Override
    public List<Commodity> all() {
        return null;
    }

    @Override
    public long getAutoNext() {
        return 0;
    }
}
