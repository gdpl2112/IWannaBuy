package test0;

import com.github.kloping.iwanna.buy.api.Player;
import com.github.kloping.iwanna.buy.impl.simple.SimplePlayer;
import com.github.kloping.iwanna.buy.impl.simple.SimpleSys;
import io.github.kloping.date.FrameUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static io.github.kloping.date.FrameUtils.SERVICE;

/**
 * @author github.kloping
 */
public class TestRun {
    public static void main(String[] args) {
        SimpleSys sys = SimpleSys.factory("./base");
        SimpleSys.INSTANCE = sys;
        Player player = SimplePlayer.getInstance(3474006766L, new File(sys.basePath(), sys.playersPath()));
        player.save(100);
        SERVICE.scheduleAtFixedRate(() -> {
            Logger.getLogger(TestRun.class).info(SimpleSys.INSTANCE.getShop().all());
        }, 5000, 5000, TimeUnit.MILLISECONDS);
    }
}
