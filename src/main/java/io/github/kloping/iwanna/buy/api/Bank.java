package io.github.Kloping.iwanna.buy.api;

import io.github.Kloping.iwanna.buy.api.listener.OnBankRateListener;

/**
 * @author github.Kloping
 */
public interface Bank {
    /**
     * save money
     *
     * @param player p
     * @param money m
     * @return true or false for Successful or failure
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
     * Most of the time it's next called {@link Bank#rate()}
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

    /**
     * add a listener
     *
     * @param listener
     */
    void addListener(OnBankRateListener listener);
}
