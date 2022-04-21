package io.github.kloping.iwanna.buy.api;

import java.util.List;

/**
 * @author github.kloping
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
