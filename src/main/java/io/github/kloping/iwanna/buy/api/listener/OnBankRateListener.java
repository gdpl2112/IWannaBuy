package io.github.kloping.iwanna.buy.api.listener;

import io.github.kloping.iwanna.buy.api.Player;

/**
 * @author github.kloping
 */
public interface OnBankRateListener {
    /**
     * on bank rate in {@link io.github.kloping.iwanna.buy.api.Bank#next}
     * @param player player id
     * @param allMoney  All the money after the profit
     * @param rateMoney Profit earned
     */
    void onBankRate(Long player, Long allMoney, Long rateMoney);
}
