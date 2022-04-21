package io.github.kloping.iwanna.buy.api;

/**
 * @author github.kloping
 */
public interface Saver<T> {
    /**
     * save
     * @param t
     * @return
     */
    T apply(T t);
}
