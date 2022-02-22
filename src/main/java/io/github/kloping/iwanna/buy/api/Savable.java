package io.github.kloping.iwanna.buy.api;

/**
 * @author github.kloping
 */
public interface Savable<T> {
    /**
     * set saver
     *
     * @param saver
     * @return
     */
    T setSaver(Saver<T> saver);

    /**
     * get saver
     * @return
     */
    Saver<T> getSaver();
}
