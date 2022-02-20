package test0;

import io.github.kloping.iwanna.buy.impl.simple.ConfEvent;
import io.github.kloping.serialize.HMLObject;

/**
 * @author github.kloping
 */
public class T0 {
    public static void main(String[] args) {
        ConfEvent event = new ConfEvent();
        event.getChanges().put(1, -200);
        event.getChanges().put(2, 2);
        event.getChanges().put(3, 5);
        event.setDesc("昨日某一三星手机爆炸,导致三星手机价格直线下滑");
        event.setId(1);
        String hmlStr = HMLObject.toHMLString(event);
        System.out.println(hmlStr);
    }

}
