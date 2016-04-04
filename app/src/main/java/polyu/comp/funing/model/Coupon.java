package polyu.comp.funing.model;

/**
 * Created by liushanchen on 16/3/19.
 */
public class Coupon {
  
    private int cid;
    private String c_code;
    private String c_name;
    private String c_description;
    private String c_status;
    private String c_image_url;
    private String uc_expired_at;
    private String c_created_at;
    private String c_discount_type;
    private double c_discount_detail=-1;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getC_code() {
        return c_code;
    }

    public void setC_code(String c_code) {
        this.c_code = c_code;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getC_description() {
        return c_description;
    }

    public void setC_description(String c_description) {
        this.c_description = c_description;
    }

    public String getC_status() {
        return c_status;
    }

    public void setC_status(String c_status) {
        this.c_status = c_status;
    }

    public String getC_image_url() {
        return c_image_url;
    }

    public void setC_image_url(String c_image_url) {
        this.c_image_url = c_image_url;
    }

    public String getC_created_at() {
        return c_created_at;
    }

    public void setC_created_at(String c_created_at) {
        this.c_created_at = c_created_at;
    }

    public String getC_discount_type() {
        return c_discount_type;
    }

    public void setC_discount_type(String c_discount_type) {
        this.c_discount_type = c_discount_type;
    }

    public String getUc_expired_at() {
        return uc_expired_at;
    }

    public void setUc_expired_at(String uc_expired_at) {
        this.uc_expired_at = uc_expired_at;
    }

    public double getC_discount_detail() {
        return c_discount_detail;
    }

    public void setC_discount_detail(double c_discount_detail) {
        this.c_discount_detail = c_discount_detail;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "cid=" + cid +
                ", c_code='" + c_code + '\'' +
                ", c_name='" + c_name + '\'' +
                ", c_description='" + c_description + '\'' +
                ", c_status='" + c_status + '\'' +
                ", c_image_url='" + c_image_url + '\'' +
                ", uc_expired_at='" + uc_expired_at + '\'' +
                ", c_created_at='" + c_created_at + '\'' +
                ", c_discount_type='" + c_discount_type + '\'' +
                ", c_discount_detail=" + c_discount_detail +
                '}';
    }
}
