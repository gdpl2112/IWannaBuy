package com.github.kloping.iwanna.buy.api;

import java.util.List;

/**
 * @author github.kloping
 */
public interface Center {
    /**
     * base path
     *
     * @return
     */
    String basePath();

    /**
     * events path
     *
     * @return
     */
    String eventsPath();

    /**
     * players path
     *
     * @return
     */
    String playersPath();

    /**
     * commodity path
     *
     * @return
     */
    String commodityPath();

    /**
     * bank path
     *
     * @return
     */
    String bankPath();

    /**
     * shop path
     *
     * @return
     */
    String shopPath();

    /**
     * warehouse path
     *
     * @return
     */
    String warehousePath();

    /**
     * get bank
     *
     * @return
     */
    Bank getBank();

    /**
     * get shop
     *
     * @return
     */
    Shop getShop();

    /**
     * all type commodity
     *
     * @return
     */
    List<Commodity> commodities();

    /**
     * next event
     *
     * @return
     */
    Event getEvent();

    /**
     * request a serial id
     *
     * @return
     */
    int getSerialId();

    /**
     * udate SerialId
     *
     * @param id
     * @return
     */
    int updateSerialId(int id);
}
