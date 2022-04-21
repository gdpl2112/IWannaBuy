package io.github.Kloping.iwanna.buy.api;

import java.util.List;

/**
 * @author github.Kloping
 */
public interface Event extends Runnable {
    /**
     * Get the number of updates
     *
     * @return
     */
    int getIndex();

    /**
     * Gets a description of the current event
     *
     * @return
     */
    String getDesc();

    /**
     * get ID of the current event
     *
     * @return
     */
    int getId();

    /**
     * event touch
     */
    @Override
    void run();

    /**
     * must id
     *
     * @return
     */
    List<Integer> getMustId();
}
