package com.github.kloping.iwanna.buy.api;

/**
 * @author github.kloping
 */
public interface Commodity {
    /**
     * get name
     *
     * @return
     */
    String getName();

    /**
     * get id
     *
     * @return
     */
    int getId();

    /**
     * get this obj size
     *
     * @return
     */
    int getSize();

    /**
     * get The time at which the item was acquired
     *
     * @return
     */
    long getTime();

    /**
     * get original price
     *
     * @return
     */
    Number getOriginalPrice();

    /**
     * get now price
     *
     * @return
     */
    Number getNowPrice();

    /**
     * return this commodity owner
     *
     * @return
     */
    Number getOwner();
}
