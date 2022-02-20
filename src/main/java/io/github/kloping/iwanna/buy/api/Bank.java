package io.github.kloping.iwanna.buy.api;

/**
 * @author github.kloping
 */
public interface Bank {
    /**
     * save money
     *
     * @param player
     * @param money
     * @return
     */
    boolean save(Player player, int money);

    /**
     * get money
     *
     * @param player
     * @param money
     * @return
     */
    boolean getMoney(Player player, int money);

    /**
     * select money
     *
     * @param player
     * @return
     */
    Number selectMoney(Player player);

    /**
     * get Interest Rate
     *
     * @return
     */
    double getInterestRate();

    /**
     * next day
     * Most of the time it's called {@link Bank#rate()}
     *
     * @return
     */
    int next();

    /**
     * add money by rate
     *
     * @return
     */
    double rate();
}
