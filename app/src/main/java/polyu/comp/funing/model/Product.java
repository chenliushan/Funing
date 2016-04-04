package polyu.comp.funing.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liushanchen on 16/3/18.
 */
public class Product implements Serializable {


    private int pid;
    private String p_code;
    private String p_name;
    private String p_description;
    private int p_quantity;
    private double p_price;
    private String p_image_url=null;
    private String p_type;
    private String p_createdAt;

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

    public String getP_description() {
        return p_description;
    }

    public void setP_description(String p_description) {
        this.p_description = p_description;
    }

    public int getP_quantity() {
        return p_quantity;
    }

    public void setP_quantity(int p_quantity) {
        this.p_quantity = p_quantity;
    }

    public double getP_price() {
        return p_price;
    }

    public void setP_price(double p_price) {
        this.p_price = p_price;
    }

    public String getP_image_url() {
        return p_image_url;
    }

    public void setP_image_url(String p_image_url) {
        this.p_image_url = p_image_url;
    }

    public String getP_createdAt() {
        return p_createdAt;
    }

    public void setP_createdAt(String p_createdAt) {
        this.p_createdAt = p_createdAt;
    }

    public String getP_type() {
        return p_type;
    }

    public void setP_type(String p_type) {
        this.p_type = p_type;
    }

    @Override
    public String toString() {
        return "Product{" +
                "pid=" + pid +
                ", p_code='" + p_code + '\'' +
                ", p_name='" + p_name + '\'' +
                ", p_description='" + p_description + '\'' +
                ", p_quantity=" + p_quantity +
                ", p_price='" + p_price + '\'' +
                ", p_image_url='" + p_image_url + '\'' +
                ", p_type='" + p_type + '\'' +
                ", p_createdAt='" + p_createdAt + '\'' +
                '}';
    }

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("pid", pid + "");
        map.put("p_code", p_code);
        map.put("p_name", p_name);
        map.put("p_description", p_description);
        map.put("p_quantity", p_quantity + "");
        map.put("p_price", p_price + "");
        return map;

    }
}
