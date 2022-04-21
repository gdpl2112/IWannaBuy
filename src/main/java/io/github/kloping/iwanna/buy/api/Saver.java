package io.github.Kloping.iwanna.buy.api;

/**
 * @author github.Kloping
 */
public interface Saver<T> {
    /**
     * save
     * @param t
     * @return
     */
    T apply(T t);
}
