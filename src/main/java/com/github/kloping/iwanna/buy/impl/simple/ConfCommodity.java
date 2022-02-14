package com.github.kloping.iwanna.buy.impl.simple;

import com.github.kloping.iwanna.buy.api.Commodity;

/**
 * @author github.kloping
 */
public class ConfCommodity implements Commodity {
    private String name;
    private Integer id;
    private Integer size;
    private Integer originalPrice;
    private Integer nowPrice;
    private Number owner;
    private long time;
    private String src;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    @Override
    public long getTime() {
        return time;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public Integer getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Integer originalPrice) {
        this.originalPrice = originalPrice;
    }

    @Override
    public Integer getNowPrice() {
        return nowPrice;
    }

    public void setNowPrice(Integer nowPrice) {
        this.nowPrice = nowPrice;
    }

    @Override
    public Number getOwner() {
        return owner;
    }

    @Override
    public Commodity setOwner(Number owner) {
        this.owner = owner;
        return this;
    }

    @Override
    public Commodity setTime(Long time) {
        this.time = time;
        return this;
    }

    @Override
    public Commodity changePrice(Integer price) {
        this.nowPrice += price;
        return this;
    }

    private Integer sid = 0;

    @Override
    public Integer getSerialId() {
        return sid;
    }

    @Override
    public Integer setSerialId(Integer id) {
        return sid;
    }

    @Override
    public String toString() {
        return "\nConfCommodity{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", size=" + size +
                ", originalPrice=" + originalPrice +
                ", nowPrice=" + nowPrice +
                ", owner=" + owner +
                ", time=" + time +
                ", src='" + src + '\'' +
                ", sid=" + sid +
                '}';
    }
}
