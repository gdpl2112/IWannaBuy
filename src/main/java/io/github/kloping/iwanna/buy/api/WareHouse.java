package io.github.Kloping.iwanna.buy.api;

import java.util.List;

/**
 * @author github.Kloping
 */
public interface WareHouse extends Savable<WareHouse> {
    /**
     * this warehouse id
     *
     * @return
     */
    Number getId();

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

    /**
     * find commodity num
     *
     * @param id
     * @return
     */
    int findCommodity(Integer id);

    /**
     * lose obj
     *
     * @param commodity
     * @param num
     * @return
     */
    WareHouse lose(Commodity commodity, int num);
}
