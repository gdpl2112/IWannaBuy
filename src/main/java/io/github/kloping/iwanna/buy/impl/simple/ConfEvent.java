package io.github.Kloping.iwanna.buy.impl.simple;

import com.alibaba.fastjson.annotation.JSONField;
import io.github.Kloping.iwanna.buy.api.Center;
import io.github.Kloping.iwanna.buy.api.CenterFindable;
import io.github.Kloping.iwanna.buy.api.Event;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author github.Kloping
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
            Logger.getLogger(this.getClass()).info("event touch " + k + "=" + v);
        });
        Logger.getLogger(this.getClass()).info("event touch:" + getDesc());
    }

    @JSONField(serialize = false, deserialize = false)
    @Override
    public Center getCenter() {
        return SimpleSys.INSTANCE;
    }

    @JSONField(serialize = false, deserialize = false)
    @Override
    public List<Integer> getMustId() {
        return new ArrayList<>(changes.keySet());
    }
}
