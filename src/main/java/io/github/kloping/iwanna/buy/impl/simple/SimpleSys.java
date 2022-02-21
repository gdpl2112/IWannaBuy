package io.github.kloping.iwanna.buy.impl.simple;

import io.github.kloping.annotations.IgnoreField;
import io.github.kloping.date.FrameUtils;
import io.github.kloping.file.FileUtils;
import io.github.kloping.iwanna.buy.api.*;
import io.github.kloping.iwanna.buy.impl.Sys;
import io.github.kloping.serialize.HMLObject;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileFilter;
import java.util.*;

/**
 * @author github.kloping
 */
public class SimpleSys extends Sys implements Savable<SimpleSys> {

    public static SimpleSys INSTANCE;
    public static final Random RANDOM = new Random();

    private static String commodityFileName = "commodities";
    private static String eventsFileName = "events";
    private static String playerFileName = "players";
    private static String bankFileName = "bankConf.hml";
    private static String shopFileName = "shopConf.hml";
    private static String warehouseFileName = "warehouses";

    @IgnoreField
    private String path;

    public static SimpleSys factory(String path) {
        SimpleSys sys = new SimpleSys();
        SimpleSys.INSTANCE = sys;
        sys.path = path;
        sys.shop = new SimpleShop(path);
        sys.bank = new SimpleBank(path);
        sys.t = 10 * 1000L;
        FrameUtils.INSTANCE.getFrames().add(sys);
        sys.loadEvents();
        sys.loadCommodity();
        return sys;
    }

    @IgnoreField
    private List<Event> events = new LinkedList<>();
    @IgnoreField
    private Map<Integer, Commodity> commodityMap = new HashMap<>();
    private Integer eventIndex = 0;
    private int sid = 1000;
    private int indexEvent = 0;

    private SimpleSys() {
    }


    @Override
    public void next() {
        super.next();
        eventIndex++;
        Logger.getLogger(this.getClass()).debug("=======================");
        apply();
    }

    @Override
    public SimpleSys apply() {
        FileUtils.putStringInFile(HMLObject.toHMLString(this), new File(basePath(), "center.hml"));
        return this;
    }

    @Override
    public int getSerialId() {
        return getId();
    }

    public synchronized int getId() {
        return ++sid;
    }

    @Override
    public int updateSerialId(int id) {
        return updateId(id);
    }

    @Override
    public int getIndex() {
        return eventIndex;
    }

    public int updateId(int id) {
        return sid = sid < id ? sid : id;
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
        return bankFileName;
    }

    @Override
    public String shopPath() {
        return shopFileName;
    }

    @Override
    public Bank getBank() {
        return bank;
    }

    @Override
    public Shop getShop() {
        return shop;
    }

    @Override
    public Event getEvent() {
        Event event = null;
        if (indexEvent < events.size()) {
            event = events.get(indexEvent);
            indexEvent++;
        } else if (events.size() == indexEvent) {
            Collections.shuffle(events);
            indexEvent = 0;
            event = events.get(indexEvent);
        }
        return event;
    }

    @Override
    public List<Commodity> commodities() {
        return new ArrayList<>(commodityMap.values());
    }

    @Override
    public String toString() {
        return "SimpleSys{" +
                "path='" + path + '\'' +
                ", events=" + events +
                ", commodityMap=" + commodityMap +
                ", indexEvent=" + indexEvent +
                '}';
    }
}

