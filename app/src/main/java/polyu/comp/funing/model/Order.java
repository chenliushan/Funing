package polyu.comp.funing.model;

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
    private int oid;
    private double o_amount;
    private String o_status;
    private int uid;
    private String name;
    private String address;
    private String phone;
    private String email;
    private int ucid;
    private String o_created_at;

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
                '}';
    }
}