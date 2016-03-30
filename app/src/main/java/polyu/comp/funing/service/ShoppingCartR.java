package polyu.comp.funing.service;

import java.util.List;

import polyu.comp.funing.model.ShoppingCart;

/**
 * Created by liushanchen on 16/3/26.
 */
public class ShoppingCartR {
    private int error;
    private String message;
    private int sid;
    private List<ShoppingCart> shoppingcarts;

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

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public List<ShoppingCart> getShoppingcarts() {
        return shoppingcarts;
    }

    public void setShoppingcarts(List<ShoppingCart> shoppingcarts) {
        this.shoppingcarts = shoppingcarts;
    }

    @Override
    public String toString() {
        return "ShoppingCartR{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", sid=" + sid +
                ", shoppingcarts=" + shoppingcarts +
                '}';
    }
}
