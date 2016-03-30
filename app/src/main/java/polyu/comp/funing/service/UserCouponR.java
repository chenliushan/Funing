package polyu.comp.funing.service;

import java.util.List;

import polyu.comp.funing.model.Coupon;

/**
 * Created by liushanchen on 16/3/26.
 */
public class UserCouponR {
    private boolean error;
    private String message;
    private List<Coupon> usercoupons;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<Coupon> getCouponList() {
        return usercoupons;
    }

    public void setCouponList(List<Coupon> couponList) {
        this.usercoupons = couponList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "CouponR{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", couponList=" + usercoupons +
                '}';
    }
}
