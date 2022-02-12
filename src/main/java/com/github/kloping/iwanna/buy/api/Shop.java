package com.github.kloping.iwanna.buy.api;

import java.util.List;

/**
 * @author github kloping
 */
public interface Shop {
    /**
     * next event
     *
     * @return
     */
    Event next();

    /**
     * get now all commodity
     *
     * @return
     */
    List<Commodity> all();

    /**
     * auto next time
     *
     * @return
     */
    long getAutoNext();
}
