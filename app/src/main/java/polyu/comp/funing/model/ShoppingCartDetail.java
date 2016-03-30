package polyu.comp.funing.model;

/**
 * Created by liushanchen on 16/3/26.
 */
public class ShoppingCartDetail {
    /**
     * CREATE TABLE IF NOT EXISTS `shoppingcart_detail` (
     * `sdid` int(11) NOT NULL AUTO_INCREMENT,
     * `sid` int(11) NOT NULL,
     * `pid` int(11) NOT NULL,
     * `p_code` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
     * `p_name` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
     * `p_price` decimal(10,2) NOT NULL DEFAULT '0.00',
     * `p_description` text COLLATE utf8_unicode_ci NOT NULL,
     * `sd_subamount` decimal(10,2) NOT NULL,
     * `sd_quantity` int(4) NOT NULL DEFAULT '1',
     * `sd_created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
     * PRIMARY KEY (`sdid`),
     * UNIQUE KEY `scdid` (`sdid`)
     * ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;
     */
    private int sdid;
    private int sid;
    private int pid;
    private String p_code;
    private String p_name;
    private double p_price;
    private double sd_subamount;
    private String p_description;
    private int sd_quantity;
    private String sd_created_at;

    public int getSdid() {
        return sdid;
    }

    public void setSdid(int sdid) {
        this.sdid = sdid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getP_code() {
        return p_code;
    }

    public void setP_code(String p_code) {
        this.p_code = p_code;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public double getP_price() {
        return p_price;
    }

    public void setP_price(double p_price) {
        this.p_price = p_price;
    }

    public double getSd_subamount() {
        return sd_subamount;
    }

    public void setSd_subamount(double sd_subamount) {
        this.sd_subamount = sd_subamount;
    }

    public String getP_description() {
        return p_description;
    }

    public void setP_description(String p_description) {
        this.p_description = p_description;
    }

    public int getSd_quantity() {
        return sd_quantity;
    }

    public void setSd_quantity(int sd_quantity) {
        this.sd_quantity = sd_quantity;
    }

    public String getSd_created_at() {
        return sd_created_at;
    }

    public void setSd_created_at(String sd_created_at) {
        this.sd_created_at = sd_created_at;
    }

    @Override
    public String toString() {
        return "ShoppingCartDetail{" +
                "sdid=" + sdid +
                ", sid=" + sid +
                ", pid=" + pid +
                ", p_code='" + p_code + '\'' +
                ", p_name='" + p_name + '\'' +
                ", p_price=" + p_price +
                ", sd_subamount=" + sd_subamount +
                ", p_description='" + p_description + '\'' +
                ", sd_quantity=" + sd_quantity +
                ", sd_created_at='" + sd_created_at + '\'' +
                '}';
    }
}
