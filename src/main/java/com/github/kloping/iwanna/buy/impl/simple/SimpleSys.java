package com.github.kloping.iwanna.buy.impl.simple;

import com.github.kloping.iwanna.buy.api.*;
import com.github.kloping.iwanna.buy.impl.Sys;
import io.github.kloping.file.FileUtils;
import io.github.kloping.serialize.HMLObject;

import java.io.File;
import java.io.FileFilter;
import java.util.*;

/**
 * @author github.kloping
 */
public class SimpleSys extends Sys {
    private static int ID = 1000;

    public static SimpleSys INSTANCE;

    private static String commodityFileName = "commodities";
    private static String eventsFileName = "events";
    private static String playerFileName = "players";
    private static String backFileName = "bank";
    private static String warehouseFileName = "warehouses";

    public static synchronized int getId() {
        return ++ID;
    }

    public static synchronized int updateId(int id) {
        return ID = ID < id ? ID : id;
    }

    private String path;

    public SimpleSys(String path) {
        super(new SimpleShop(path),new SimpleBank(path));
        this.path = path;
        loadEvents();
        loadCommodity();
    }

    private void loadCommodity() {
        for (File file : new File(path, commodityFileName).listFiles(new FileFilter() {
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
        for (File file : new File(path, eventsFileName).listFiles(new FileFilter() {
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
    public String basePath() {
        return path;
    }

    @Override
    public String eventsPath() {
        return eventsFileName;
    }

    @Override
    public String warehousePath() {
        return warehouseFileName;
    }

    @Override
    public String playersPath() {
        return playerFileName;
    }

    @Override
    public String commodityPath() {
        return commodityFileName;
    }

    @Override
    public String bankPath() {
        return backFileName;
    }

    @Override
    public Bank getBank() {
        return bank;
    }

    @Override
    public Shop getShop() {
        return shop;
    }

    private int indexEvent = 0;

    @Override
    protected Event getEvent() {
        Event event = null;
        if (events.size() < indexEvent) {
            event = events.get(indexEvent);
            indexEvent++;
        } else if (events.size() == indexEvent) {
            Collections.shuffle(events);
            indexEvent = 0;
            event = events.get(indexEvent);
        }
        return event;
    }

}

