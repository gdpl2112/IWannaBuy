package com.github.kloping.iwanna.buy.impl.simple;

import com.github.kloping.iwanna.buy.api.*;
import io.github.kloping.file.FileUtils;
import io.github.kloping.serialize.HMLObject;

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

    private String path;
    private Double rate = 0.001;
    private Map<Number, Number> bankMap = new HashMap<>();

    public SimpleBank(String path) {
        this.path = path;
        load();
    }

    private void load() {
        String hmlStr = FileUtils.getStringFromFile(new File(path, getCenter().bankPath()).getAbsolutePath());
        if (isNotEmpty(hmlStr)) {
            SimpleBank bank = HMLObject.parseObject(hmlStr).toJavaObject(SimpleBank.class);
            this.bankMap.putAll(bank.bankMap);
        }
    }

    @Override
    public Bank apply() {
        File file = new File(path, getCenter().bankPath());
        FileUtils.putStringInFile(HMLObject.toHMLString(this), file);
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
        });
        return 0;
    }

    @Override
    public double rate() {
        int i = RANDOM.nextInt(30) + 20;
        float f = 0.0001f * i;
        return rate = Double.valueOf(f);
    }
}
