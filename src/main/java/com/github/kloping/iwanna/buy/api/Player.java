package com.github.kloping.iwanna.buy.api;

/**
 * @author github.kloping
 */
public interface Player extends Savable<Player> {

    /**
     * get player id
     *
     * @return
     */
    Number getId();

    /**
     * get my warehouse
     *
     * @return
     */
    WareHouse getWareHouse();

    /**
     * save money
     *
     * @param money
     * @return
     */
    Number save(int money);

    /**
     * get current money
     *
     * @return
     */
    Number getMoney();

    /**
     * add money
     *
     * @param money
     * @return
     */
    Number append(int money);

    /**
     * lose money
     *
     * @param money
     * @return
     */
    Number lose(int money);

    /**
     * buy some same Commodity
     *
     * @param commodity
     * @param num
     * @return
     */
    Boolean buy(Commodity commodity, int num);

    /**
     * buy a commodity
     *
     * @param commodity
     * @return
     */
    default Boolean buy(Commodity commodity) {
        return buy(commodity, 1);
    }


    /**
     * sell a commodity
     *
     * @param commodity
     * @return
     */
    default Boolean sell(Commodity commodity) {
        return sell(commodity, 1);
    }

    /**
     * sell some same commodity
     *
     * @param commodity
     * @param num
     * @return
     */
    Boolean sell(Commodity commodity, int num);
}
