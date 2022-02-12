package com.github.kloping.iwanna.buy.impl.simple;

import com.github.kloping.iwanna.buy.api.Bank;
import com.github.kloping.iwanna.buy.api.Commodity;
import com.github.kloping.iwanna.buy.api.Event;
import com.github.kloping.iwanna.buy.api.Shop;
import com.github.kloping.iwanna.buy.impl.Sys;
import io.github.kloping.file.FileUtils;
import io.github.kloping.serialize.HMLObject;

import java.io.File;
import java.io.FileFilter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author github.kloping
 */
public class SimpleSys extends Sys {
    private static int ID = 1000;

    public static synchronized int getId() {
        return ++ID;
    }

    public static synchronized int updateId(int id) {
        return ID = ID < id ? ID : id;
    }

    private String path;

    public SimpleSys(String path) {
        super(SimpleShop.INSTANCE, SimpleBank.INSTANCE);
        this.path = path;
        loadEvents();
        loadCommodity();
    }

    private void loadCommodity() {
        for (File file : new File(path, "commodities").listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(".hml");
            }
        })) {
            try {
                String hmlStr = FileUtils.getStringFromFile(file.getAbsolutePath());
                ConfCommodity confCommodity = HMLObject.parseObject(hmlStr).toJavaObject(ConfCommodity.class);
                commodityMap.put(confCommodity.getId(), confCommodity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private List<Event> events = new LinkedList<>();
    private Map<Integer, Commodity> commodityMap = new HashMap<>();

    @Override
    protected void loadEvents() {
        for (File file : new File(path, "events").listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(".hml");
            }
        })) {
            try {
                String hmlStr = FileUtils.getStringFromFile(file.getAbsolutePath());
                ConfEvent confEvent = HMLObject.parseObject(hmlStr).toJavaObject(ConfEvent.class);
                events.add(confEvent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected Event getEvent() {
        return null;
    }
}
