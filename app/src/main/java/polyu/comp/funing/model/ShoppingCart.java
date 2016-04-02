package polyu.comp.funing.model;

import java.util.List;

/**
 * Created by liushanchen on 16/3/26.
 */
public class ShoppingCart {
    /**
     * CREATE TABLE IF NOT EXISTS `shoppingcart` (
     `sid` int(11) NOT NULL AUTO_INCREMENT,
     `uid` int(11) NOT NULL,
     `s_amount` decimal(10,2) DEFAULT '0.00',
     `s_status` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
     `s_created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
     PRIMARY KEY (`sid`),
     UNIQUE KEY `sc_id` (`sid`)
     ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

     */
    private int sid;
    private int uid;
    private Double s_amount;
    private String s_status;
    private String s_created_at;
    private List<ShoppingCartDetail> shoppingcartdetails;

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public Double getS_amount() {
        return s_amount;
    }

    public void setS_amount(Double s_amount) {
        this.s_amount = s_amount;
    }

    public String getS_status() {
        return s_status;
    }

    public void setS_status(String s_status) {
        this.s_status = s_status;
    }

    public String getS_created_at() {
        return s_created_at;
    }

    public void setS_created_at(String s_created_at) {
        this.s_created_at = s_created_at;
    }

    public List<ShoppingCartDetail> getShoppingcartdetails() {
        return shoppingcartdetails;
    }

    public void setShoppingcartdetails(List<ShoppingCartDetail> shoppingcartdetails) {
        this.shoppingcartdetails = shoppingcartdetails;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "sid=" + sid +
                ", uid=" + uid +
                ", s_amount=" + s_amount +
                ", s_status='" + s_status + '\'' +
                ", s_created_at='" + s_created_at + '\'' +
                ", shoppingcartdetails=" + shoppingcartdetails +
                '}';
    }
}
