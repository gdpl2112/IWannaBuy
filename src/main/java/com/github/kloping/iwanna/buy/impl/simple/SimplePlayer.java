package com.github.kloping.iwanna.buy.impl.simple;

import com.github.kloping.iwanna.buy.api.*;
import io.github.kloping.clasz.ClassUtils;
import io.github.kloping.file.FileUtils;
import io.github.kloping.serialize.HMLObject;
import org.apache.log4j.Logger;

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
        SimplePlayer player;
        Logger.getLogger(SimplePlayer.class).info("get player " + qid);
        File file = new File(dir, qid.toString());
        String hmlStr = FileUtils.getStringFromFile(file.getAbsolutePath());
        if (isNotEmpty(hmlStr)) {
            Logger.getLogger(SimplePlayer.class).info("get player from parse " + qid);
            return player = HMLObject.parseObject(hmlStr).toJavaObject(SimplePlayer.class);
        } else {
            player = new SimplePlayer();
            player.dir = dir;
            player.qid = qid;
            player.apply();
            Logger.getLogger(SimplePlayer.class).info("get player from new " + qid);
            return player;
        }
    }

    private Long qid = -1L;
    private File dir;
    private Long money = 1000L;

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
        Logger.getLogger(this.getClass()).info("player " + qid + " will save:" + money);
        if (getCenter().getBank().save(this, money)) {
            Logger.getLogger(this.getClass()).info("player " + qid + " saved:" + money);
            return lose(money);
        } else {
            return money;
        }
    }

    @Override
    public Number getMoney() {
        return money;
    }

    @Override
    public Number append(int money) {
        try {
            return this.money += money;
        } finally {
            apply();
        }
    }

    @Override
    public Number lose(int money) {
        try {
            return this.money -= money;
        } finally {
            apply();
        }
    }

    @Override
    public Boolean buy(Commodity c, int num) {
        Logger.getLogger(this.getClass()).info("player " + qid + " will buy " + c.getName() + "(" + c.getId() + "," + c.getNowPrice() + ")");
        Commodity commodity = null;
        try {
            commodity = ClassUtils.copyAllField(c);
            commodity.setSerialId(getCenter().getSerialId());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        int mm = commodity.getNowPrice().intValue() * num;
        if (mm >= getMoney().intValue()) {
            return false;
        } else {
            commodity.setOwner(qid);
            commodity.setTime(System.currentTimeMillis());
            getWareHouse().add(commodity);
            lose(mm);
            getWareHouse().apply();
            apply();
            Logger.getLogger(this.getClass()).info("player " + qid + " buy ed " + c.getName() + "(" + c.getId() + "," + c.getNowPrice() + ")");
            return true;
        }
    }

    @Override
    public Boolean sell(Commodity c, int num) {
        Logger.getLogger(this.getClass()).info("player " + qid + " will sell " + c.getName() + "(" + c.getId() + "," + c.getNowPrice() + ")");
        int n2 = getWareHouse().findCommodity(c.getId());
        if (n2 >= n2) {
            Commodity shopComm = getCenter().getShop().map().get(c.getId());
            int m0 = shopComm.getNowPrice().intValue() * num;
            getWareHouse().lose(c, num);
            append(m0);
            apply();
            Logger.getLogger(this.getClass()).info("player " + qid + " sell ed " + c.getName() + "(" + c.getId() + "," + c.getNowPrice() + ")");
            return true;
        } else {
            return false;
        }
    }
}
