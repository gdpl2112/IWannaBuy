package io.github.kloping.iwanna.buy.api;

/**
 * @author github.kloping
 */
public interface Commodity {
    /**
     * get name
     *
     * @return
     */
    String getName();

    /**
     * get id
     *
     * @return
     */
    int getId();

    /**
     * get this obj size
     *
     * @return
     */
    int getSize();

    /**
     * get The time at which the item was acquired
     *
     * @return
     */
    long getTime();

    /**
     * get original price
     *
     * @return
     */
    Number getOriginalPrice();

    /**
     * get now price
     *
     * @return
     */
    Number getNowPrice();

    /**
     * return this commodity owner
     *
     * @return
     */
    Number getOwner();

    /**
     * set Owner
     *
     * @param owner
     * @return
     */
    Commodity setOwner(Number owner);

    /**
     * set time
     *
     * @param time
     * @return
     */
    Commodity setTime(Long time);

    /**
     * change price
     *
     * @param price
     * @return
     */
    Commodity changePrice(Integer price);

    /**
     * get Serial Id
     *
     * @return
     */
    Integer getSerialId();

    /**
     * set serial id
     *
     * @param id
     * @return
     */
    Integer setSerialId(Integer id);
}
