package com.github.kloping.iwanna.buy.impl.simple;

import com.github.kloping.iwanna.buy.api.*;
import io.github.kloping.file.FileUtils;
import io.github.kloping.serialize.HMLObject;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static com.github.kloping.iwanna.buy.impl.simple.SimpleSys.RANDOM;
import static io.github.kloping.judge.Judge.isNotEmpty;

/**
 * @author github.kloping
 */
public class SimpleBank implements Bank, Savable<Bank>, CenterFindable {
    @Override
    public Center getCenter() {
        return SimpleSys.INSTANCE;
    }

    {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                apply();
            }
        });
    }

    private String path;
    private Double rate = 0.001;
    private Map<Number, Number> bankMap = new HashMap<>();

    /**
     * used by hml
     */
    public SimpleBank() {
    }

    public SimpleBank(String path) {
        this.path = path;
        load();
    }

    private void load() {
        String hmlStr = FileUtils.getStringFromFile(new File(path, getCenter().bankPath()).getAbsolutePath());
        if (isNotEmpty(hmlStr)) {
            SimpleBank bank = HMLObject.parseObject(hmlStr).toJavaObject(SimpleBank.class);
            this.bankMap.putAll(bank.bankMap);
            Logger.getLogger(this.getClass()).info("bank load");
        }
    }

    @Override
    public Bank apply() {
        File file = new File(path, getCenter().bankPath());
        FileUtils.putStringInFile(HMLObject.toHMLString(this), file);
        Logger.getLogger(this.getClass()).info("bank apply");
        return this;
    }

    @Override
    public boolean save(Player player, int money) {
        if (player.getMoney().intValue() >= money) {
            if (bankMap.containsKey(player.getId())) {
                bankMap.put(player.getId(), bankMap.get(player.getId()).intValue() + money);
            } else {
                bankMap.put(player.getId(), money);
            }
            Logger.getLogger(this.getClass()).info("player-" + player.getId() + " Take in the From bank " + money);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean getMoney(Player player, int money) {
        if (bankMap.containsKey(player.getId())) {
            if (bankMap.get(player.getId()).intValue() >= money) {
                player.append(money);
                bankMap.put(player.getId(), bankMap.get(player.getId()).intValue() - money);
                Logger.getLogger(this.getClass()).info("player-" + player.getId() + " Take out the From bank " + money);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public Number selectMoney(Player player) {
        if (bankMap.containsKey(player.getId())) {
            return bankMap.get(player.getId());
        } else {
            return null;
        }
    }

    @Override
    public double getInterestRate() {
        return rate == null ? rate() : rate;
    }

    @Override
    public int next() {
        bankMap.forEach((k, v) -> {
            float f = (float) (v.intValue() * getInterestRate());
            int fi = (int) f;
            fi = fi > 0 ? fi : 1;
            bankMap.put(k, v.intValue() + fi);
            Logger.getLogger(this.getClass()).info("player-" + k + " append From bank " + fi);
        });
        rate();
        apply();
        return 0;
    }

    @Override
    public double rate() {
        try {
            int i = RANDOM.nextInt(30) + 20;
            Float f = 0.0001f * i;
            String s0 = f.toString().length() > 6 ? f.toString().substring(0, 6) : f.toString();
            return rate = Double.valueOf(s0);
        } finally {
            Logger.getLogger(this.getClass()).info("rate Change for " + rate);
        }
    }
}
