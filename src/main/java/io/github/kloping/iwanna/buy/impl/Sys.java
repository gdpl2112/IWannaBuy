package io.github.Kloping.iwanna.buy.impl;

import io.github.Kloping.date.FrameUtils;
import io.github.Kloping.iwanna.buy.api.Bank;
import io.github.Kloping.iwanna.buy.api.Center;
import io.github.Kloping.iwanna.buy.api.Shop;

import java.util.Objects;

/**
 * @author github.Kloping
 */
public abstract class Sys implements Runnable, Center {
    public Shop shop;
    public Bank bank;
    public long t = 1200000;
    public long index = 0;

    public Sys() {
    }

    public Sys(Shop shop, Bank bank) {
        this.shop = shop;
        this.bank = bank;
        FrameUtils.INSTANCE.getFrames().add(this);
    }

    /**
     * load all events
     */
    protected abstract void loadEvents();

    public void next() {
        index = 0;
        shop.next();
        bank.next();
    }

    @Override
    public void run() {
        index += FrameUtils.INSTANCE.getEve();
        if (index >= t) {
            index = 0;
            next();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sys sys = (Sys) o;
        return t == sys.t && index == sys.index && Objects.equals(shop, sys.shop) && Objects.equals(bank, sys.bank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shop, bank, t, index);
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public void setT(long t) {
        this.t = t;
    }

    public void setIndex(long index) {
        this.index = index;
    }
}
