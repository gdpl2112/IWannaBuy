package com.github.kloping.iwanna.buy.impl.simple;

import com.github.kloping.iwanna.buy.api.Center;
import com.github.kloping.iwanna.buy.api.CenterFindable;
import com.github.kloping.iwanna.buy.api.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author github.kloping
 */
public class ConfEvent implements Event, CenterFindable {
    private Map<Integer, Integer> changes = new HashMap<>();
    private String desc;
    private Integer id;

    @Override
    public int getIndex() {
        return 0;
    }

    public Map<Integer, Integer> getChanges() {
        return changes;
    }

    public void setChanges(Map<Integer, Integer> changes) {
        this.changes = changes;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public void run() {
        changes.forEach((k, v) -> {
            getCenter().getShop().change(k, v);
        });
    }

    @Override
    public Center getCenter() {
        return SimpleSys.INSTANCE;
    }

    @Override
    public List<Integer> getMustId() {
        return new ArrayList<>(changes.keySet());
    }
}
