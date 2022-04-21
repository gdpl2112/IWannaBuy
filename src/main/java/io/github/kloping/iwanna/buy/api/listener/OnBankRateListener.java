package io.github.Kloping.iwanna.buy.api.listener;

/**
 * @author github.Kloping
 */
public interface OnBankRateListener {
    /**
     * on bank rate in {@link io.github.Kloping.iwanna.buy.api.Bank#next}
     * @param player player id
     * @param allMoney  All the money after the profit
     * @param rateMoney Profit earned
     */
    void onBankRate(Long player, Long allMoney, Long rateMoney);
}
