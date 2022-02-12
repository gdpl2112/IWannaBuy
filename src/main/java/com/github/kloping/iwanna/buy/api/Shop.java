package com.github.kloping.iwanna.buy.api;

import java.util.List;
import java.util.Map;

/**
 * @author github kloping
 */
public interface Shop {
    /**
     * next event
     *
     * @return
     */
    Event next();

    /**
     * get now all commodity
     *
     * @return
     */
    List<Commodity> all();

    /**
     * change commodity price
     *
     * @param id
     * @param offset
     * @return
     */
    List<Commodity> change(int id, int offset);

    /**
     * get now all commodity
     * map id->commodity
     *
     * @return
     */
    Map<Integer, Commodity> map();
}
