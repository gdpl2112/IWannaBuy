package com.github.kloping.iwanna.buy.impl.simple;

import com.github.kloping.iwanna.buy.api.Bank;
import com.github.kloping.iwanna.buy.api.Player;

/**
 * @author github.kloping
 */
public class SimpleBank implements Bank {
    public static final SimpleBank INSTANCE = new SimpleBank();

    @Override
    public boolean save(Player player, int money) {
        return false;
    }

    @Override
    public boolean getMoney(Player player, int money) {
        return false;
    }

    @Override
    public double getInterestRate() {
        return 0;
    }

    @Override
    public int next() {
        return 0;
    }

    @Override
    public double rate() {
        return 0;
    }
}
