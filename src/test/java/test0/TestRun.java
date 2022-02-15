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
    }
}
