package test0;

import io.github.kloping.iwanna.buy.api.Commodity;
import io.github.kloping.iwanna.buy.api.Player;
import io.github.kloping.iwanna.buy.api.WareHouse;
import io.github.kloping.iwanna.buy.impl.simple.SimplePlayer;
import io.github.kloping.iwanna.buy.impl.simple.SimpleSys;
import io.github.kloping.iwanna.buy.impl.simple.SimpleWareHouse;

import java.io.File;

/**
 * @author github.kloping
 */
public class TestRun {
    public static void main(String[] args) {
        SimpleSys sys = SimpleSys.factory("./base");
        SimpleSys.INSTANCE = sys;
        sys.getBank().addListener((player, allMoney, rateMoney) -> {
            long id = 3474006766L;
            Player p0 = SimplePlayer.getInstance(id, new File(sys.basePath(), sys.playersPath()));
            Commodity c0 = sys.getShop().all().get(0);
            p0.buy(c0, 1);
            WareHouse w0 = SimpleWareHouse.getInstance(id, new File(sys.basePath(), sys.warehousePath()));
            Commodity c1 = w0.getAll().get(0);
            p0.sell(c1, 1);
        });
    }
}
