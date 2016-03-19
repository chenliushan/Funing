package polyu.comp.funing.domain;

import java.io.Serializable;

/**
 * Created by liushanchen on 16/3/18.
 */
public class Product implements Serializable {
    /*
    "pid": 24,
      "p_code": "FUN014",
      "p_name": "Canon SELPHY CP910",
      "p_description": "SELPHY CP910 compact photo printer features a new AirPrint and Wi-Fi Printing function for wireless printing of images from digital cameras, smart devices and computers. Combining a slim body design with simple operation, SELPHY CP910 incorporated with a range of enhanced technologies and functions including a new Print Finish Function for instant photo texture enrichment to create extraordinary photo",
      "p_quantity": 20,
      "p_price": "2788.00",
      "p_image_url": "http://52.76.239.196/funing/images/products/FUN014.jpg",
      "p_createdAt": "2016-03-17 02:47:24"
     */
    private int pid;
    private String p_code;
    private String p_name;
    private String p_description;
    private int p_quantity;
    private String p_price;
    private String p_image_url;
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

    public String getP_price() {
        return p_price;
    }

    public void setP_price(String p_price) {
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
                ", p_createdAt='" + p_createdAt + '\'' +
                '}';
    }
}
