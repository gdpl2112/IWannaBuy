package io.github.Kloping.iwanna.buy.api;

/**
 * @author github.Kloping
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
