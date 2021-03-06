package io.github.kloping.iwanna.buy.api;

import io.github.kloping.iwanna.buy.api.listener.OnEventListener;

import java.util.List;
import java.util.Map;

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
     * change commodity price
     *
     * @param id
     * @param offset
     * @return
     */
    List<Commodity> change(int id, int offset);

    /**
     * get now all commodity
     * map id for commodity
     *
     * @return
     */
    Map<Integer, Commodity> map();

    /**
     * commodity num
     *
     * @return
     */
    int getNum();

    /**
     * add event
     *
     * @param eventListener
     */
    void addListener(OnEventListener eventListener);
}
