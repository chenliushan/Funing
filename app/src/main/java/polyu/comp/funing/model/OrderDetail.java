package polyu.comp.funing.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liushanchen on 16/4/4.
 */
public class OrderDetail implements Serializable {
    /**
     * CREATE TABLE IF NOT EXISTS `orders_detail` (
     * `odid` int(11) NOT NULL AUTO_INCREMENT,
     * `pid` int(11) NOT NULL,
     * `p_code` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
     * `p_description` text COLLATE utf8_unicode_ci NOT NULL,
     * `p_name` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
     * `p_price` decimal(10,2) NOT NULL,
     * `od_quantity` int(10) NOT NULL DEFAULT '0',
     * `od_subamount` decimal(10,2) NOT NULL DEFAULT '0.00',
     * `od_created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
     * `oid` int(11) NOT NULL,
     * PRIMARY KEY (`odid`),
     * UNIQUE KEY `odid` (`odid`)
     * ) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=5 ;
     */
    private int odid=-1;
    private int pid=-1;
    private String p_code;
    private String p_name;
    private double p_price;
    private String p_description;
    private String p_image_url;
    private int od_quantity;
    private double od_subamount=-1;
    private String od_created_at;
    private int oid=-1;
    

    public OrderDetail() {
        
    }

    public OrderDetail(ShoppingCartDetail scd) {
        this.pid = scd.getPid();
        this.p_code = scd.getP_code();
        this.p_name = scd.getP_name();
        this.p_price = scd.getP_price();
        this.p_description =scd.getP_description() ;
        this.od_quantity = scd.getSd_quantity();
        this.od_subamount = scd.getSd_subamount();
    }

    public String getP_image_url() {
        return p_image_url;
    }

    public void setP_image_url(String p_image_url) {
        this.p_image_url = p_image_url;
    }

    public int getOdid() {
        return odid;
    }

    public void setOdid(int odid) {
        this.odid = odid;
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

    public String getP_description() {
        return p_description;
    }

    public void setP_description(String p_description) {
        this.p_description = p_description;
    }

    public int getOd_quantity() {
        return od_quantity;
    }

    public void setOd_quantity(int od_quantity) {
        this.od_quantity = od_quantity;
    }

    public double getOd_subamount() {
        return od_subamount;
    }

    public void setOd_subamount(double od_subamount) {
        this.od_subamount = od_subamount;
    }

    public String getOd_created_at() {
        return od_created_at;
    }

    public void setOd_created_at(String od_created_at) {
        this.od_created_at = od_created_at;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "odid=" + odid +
                ", pid=" + pid +
                ", p_code='" + p_code + '\'' +
                ", p_name='" + p_name + '\'' +
                ", p_price=" + p_price +
                ", p_description='" + p_description + '\'' +
                ", od_quantity=" + od_quantity +
                ", od_subamount=" + od_subamount +
                ", od_created_at='" + od_created_at + '\'' +
                ", oid=" + oid +
                '}';
    }

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<String, String>();
        if(oid!=-1){
            map.put("oid", oid + "");
        }
        if(pid!=-1){
            map.put("pid", pid + "");
        }
        if(odid!=-1){
            map.put("odid", odid + "");
        }
        if(od_subamount!=-1){
            map.put("od_subamount", od_subamount + "");
        }
        if(p_code!=null){
            map.put("p_code", p_code);
        }
        if(p_name!=null){
            map.put("p_name", p_name);
        }
        if(p_description!=null){
            map.put("p_description", p_description);
        }
        if(od_created_at!=null){
            map.put("od_created_at", od_created_at);
        }
      
        map.put("p_price", p_price+"");
        map.put("od_quantity", od_quantity + "");

        return map;

    }

}
