package polyu.comp.funing.service;

import java.util.List;

import polyu.comp.funing.model.Product;

/**
 * Created by liushanchen on 16/3/18.
 */
public class ProductListR {
    private int error;
    private String message;
    private List<Product> products;

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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "ProductListR{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", products=" + products +
                '}';
    }
}
