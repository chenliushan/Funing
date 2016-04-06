package polyu.comp.funing.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liushanchen on 16/4/4.
 */
public class Order {
    /**
     * CREATE TABLE IF NOT EXISTS `orders` (
     * `oid` int(11) NOT NULL AUTO_INCREMENT,
     * `o_amount` decimal(10,2) DEFAULT NULL,
     * `o_status` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
     * `uid` int(11) NOT NULL,
     * `name` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
     * `address` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
     * `phone` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
     * `email` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
     * `ucid` int(11) NOT NULL,
     * `o_created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
     * PRIMARY KEY (`oid`),
     * UNIQUE KEY `oid` (`oid`)
     * ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;
     */
    private int oid = -1;
    private double o_amount = -1;
    private String o_status;
    private int uid = -1;
    private String name;
    private String address;
    private String phone;
    private String email;
    private int ucid = 0;//coupon
    private String o_created_at;
    private List<OrderDetail> orderdetails;

    public Order() {

    }

    public Order(User u) {
        this.uid = u.getUid();
        this.name = u.getName();
        this.address = u.getAddress();
        this.phone = u.getPhone();
        this.email = u.getEmail();
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public double getO_amount() {
        return o_amount;
    }

    public void setO_amount(double o_amount) {
        this.o_amount = o_amount;
    }

    public String getO_status() {
        return o_status;
    }

    public void setO_status(String o_status) {
        this.o_status = o_status;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUcid() {
        return ucid;
    }

    public void setUcid(int ucid) {
        this.ucid = ucid;
    }

    public String getO_created_at() {
        return o_created_at;
    }

    public void setO_created_at(String o_created_at) {
        this.o_created_at = o_created_at;
    }

    public List<OrderDetail> getOrderdetails() {
        return orderdetails;
    }

    public void setOrderdetails(List<OrderDetail> orderdetails) {
        this.orderdetails = orderdetails;
    }

    @Override
    public String toString() {
        return "Order{" +
                "oid=" + oid +
                ", o_amount=" + o_amount +
                ", o_status='" + o_status + '\'' +
                ", uid=" + uid +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", ucid=" + ucid +
                ", o_created_at='" + o_created_at + '\'' +
                ", orderdetails=" + orderdetails +
                '}';
    }

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<String, String>();
        if (oid != -1) {
            map.put("oid", oid + "");
        }
        if (o_amount != -1) {
            map.put("o_amount", o_amount + "");
        }
        if (uid != -1) {
            map.put("uid", uid + "");
        }
        map.put("ucid", ucid + "");

        if (o_status != null) {
            map.put("o_status", o_status);
        }
        if (o_created_at != null) {
            map.put("o_created_at", o_created_at);
        }
        if (name != null) {
            map.put("name", name);
        }
        if (address != null) {
            map.put("address", address);
        }
        if (email != null) {
            map.put("email", email);
        }

        if (phone != null) {
            map.put("phone", phone);
        }

        return map;

    }

}
