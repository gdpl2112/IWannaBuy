package io.github.Kloping.iwanna.buy.api;

import java.util.List;
import java.util.Map;

/**
 * @author github Kloping
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
}
