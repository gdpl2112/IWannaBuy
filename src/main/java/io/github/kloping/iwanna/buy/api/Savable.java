package io.github.kloping.iwanna.buy.api;

/**
 * @author github.kloping
 */
public interface Savable<T> {
    /**
     * save apply
     *
     * @return
     */
    T apply();
}
