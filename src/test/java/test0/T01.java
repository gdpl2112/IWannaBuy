package test0;

import io.github.kloping.iwanna.buy.impl.simple.ConfCommodity;
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
        commodity.setSerialId(-1);
        String hmlStr = HMLObject.toHMLString(commodity);
        System.out.println(hmlStr);
    }

}
