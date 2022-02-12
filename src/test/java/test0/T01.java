package test0;

import com.github.kloping.iwanna.buy.impl.Sys;
import com.github.kloping.iwanna.buy.impl.simple.ConfCommodity;
import com.github.kloping.iwanna.buy.impl.simple.ConfEvent;
import io.github.kloping.serialize.HMLObject;

/**
 * @author github.kloping
 */
public class T01 {
    public static void main(String[] args) {
        ConfCommodity commodity = new ConfCommodity();
        commodity.setId(1);
        commodity.setName("三星手机");
        commodity.setOwner(-1);
        commodity.setSize(1);
        commodity.setOriginalPrice(3000);
        commodity.setTime(System.currentTimeMillis());
        commodity.setNowPrice(commodity.getOriginalPrice());
        commodity.setSrc("");
        String hmlStr = HMLObject.toHMLString(commodity);
        System.out.println(hmlStr);
    }

}
