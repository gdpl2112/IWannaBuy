package com.github.kloping.iwanna.buy.api;

import java.util.List;

/**
 * @author github.kloping
 */
public interface WareHouse {
    /**
     * get this warehouse capacity
     *
     * @return
     */
    int getCapacity();

    /**
     * get this warehouse surplus capacity
     *
     * @return
     */
    int getSurplusCapacity();

    /**
     * add a commodity into this warehouse
     * and return current Capacity
     *
     * @param commodity
     * @return Capacity
     */
    int add(Commodity commodity);

    /**
     * current all obj
     *
     * @return
     */
    List<Commodity> getAll();
}
