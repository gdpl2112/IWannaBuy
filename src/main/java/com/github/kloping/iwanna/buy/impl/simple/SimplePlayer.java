package com.github.kloping.iwanna.buy.impl.simple;

import com.github.kloping.iwanna.buy.api.*;
import io.github.kloping.file.FileUtils;
import io.github.kloping.serialize.HMLObject;

import java.io.File;

import static io.github.kloping.judge.Judge.isNotEmpty;

/**
 * @author github.kloping
 */
public class SimplePlayer implements Player, CenterFindable {
    @Override
    public Center getCenter() {
        return SimpleSys.INSTANCE;
    }

    public static SimplePlayer getInstance(Long qid, File dir) {
        File file = new File(dir, qid.toString());
        String hmlStr = FileUtils.getStringFromFile(file.getAbsolutePath());
        if (isNotEmpty(hmlStr)) {
            return HMLObject.parseObject(hmlStr).toJavaObject(SimplePlayer.class);
        } else {
            SimplePlayer player = new SimplePlayer();
            player.dir = dir;
            player.qid = qid;
            player.apply();
            return player;
        }
    }

    private Long qid;
    private File dir;
    private Long money;

    @Override
    public Number getId() {
        return qid;
    }

    @Override
    public Player apply() {
        File file = new File(dir, qid.toString());
        FileUtils.putStringInFile(HMLObject.toHMLString(this), file);
        return this;
    }

    @Override
    public WareHouse getWareHouse() {
        return SimpleWareHouse.getInstance(qid, new File(getCenter().basePath(), getCenter().warehousePath()));
    }

    @Override
    public Number save(int money) {
        getCenter().getBank().save(this, money);
        return money;
    }

    @Override
    public Number getMoney() {
        return money;
    }

    @Override
    public Number append(int money) {
        return this.money += money;
    }

    @Override
    public Number lose(int money) {
        return this.money -= money;
    }

    @Override
    public Boolean buy(Commodity commodity, int num) {
        if (commodity.getNowPrice().intValue() * num >= getMoney().intValue()) {
            return false;
        } else {
            commodity.setOwner(qid);
            commodity.setTime(System.currentTimeMillis());
            getWareHouse().add(commodity);
            getWareHouse().apply();
            return true;
        }
    }

    @Override
    public Boolean sell(Commodity commodity, int num) {
        int n2 = getWareHouse().findCommodity(commodity.getId());
        if (n2 >= n2) {
            Commodity shopComm = getCenter().getShop().map().get(commodity.getId());
            int m0 = shopComm.getNowPrice().intValue() * num;
            getWareHouse().lose(commodity, num);
            append(m0);
            return true;
        } else {
            return false;
        }
    }
}
