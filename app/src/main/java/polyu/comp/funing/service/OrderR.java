package polyu.comp.funing.service;

import java.util.List;

import polyu.comp.funing.model.Order;

/**
 * Created by liushanchen on 16/4/5.
 */
public class OrderR {
    private int error;
    private String message;
    private int oid=-1;
    private int odid;
    private List<Order> orders;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getOdid() {
        return odid;
    }

    public void setOdid(int odid) {
        this.odid = odid;
    }

    
}
