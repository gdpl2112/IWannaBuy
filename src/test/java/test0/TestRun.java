package test0;

import io.github.kloping.iwanna.buy.api.Player;
import io.github.kloping.iwanna.buy.impl.simple.SimplePlayer;
import io.github.kloping.iwanna.buy.impl.simple.SimpleSys;

import java.io.File;

/**
 * @author github.kloping
 */
public class TestRun {
    public static void main(String[] args) {
        SimpleSys sys = SimpleSys.factory("./base");
        SimpleSys.INSTANCE = sys;
        Player player = SimplePlayer.getInstance(3474006766L, new File(sys.basePath(), sys.playersPath()));
    }
}
