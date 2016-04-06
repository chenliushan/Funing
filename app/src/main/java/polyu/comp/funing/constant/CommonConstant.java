package polyu.comp.funing.constant;

import java.io.File;

import polyu.comp.funing.R;

/**
 * Created by liushanchen on 16/3/17.
 */
public class CommonConstant {
//    public static String apiEndpoint = "http://52.76.239.196/task_manager/v1/";
    public static String apiEndpoint = "http://52.76.239.196/funing/v1/";
    
    public static String preferenceName = "Funing";
    public static String applicationContext = "applicationContext";
    public static int noError = 0;
    
    public static String apiKey = null;
    public static int userId = -1;
    
    public static String applicationDir = "";
    public static File applicationCache = null;
    
    public static final String product_key = "product";
    public static final String shopping_cart_key = "shopping_cart";
    public static final String mainActivityF_key = "fragment";
    public static final String F_login = "Login";
    public static final String F_coupon = "Coupons";
    public static final String F_product = "Products";
    public static final String F_sc = "My Basket";
    public static final String F_order = "Order History";

    public static final String valid="valid";
    public static final String invalid="invalid";
    public static final String used="used";
    public static final String couponTypeCash="cash";

    public static final String couponUpdateUsed="Used";
    public static final String couponUpdateUcstatus="uc_status";
    public static final String scUpdateUcstatus="s_status";
    public static final String scInvalid="Invalid";



    public static class UserConstant{
        public static String user = "user";
        public static String uid = "uid";
        public static String name = "name";
        public static String email = "email";
        public static String address = "address";
        public static String phone = "phone";
        public static String password = "password";
        public static String api_key = "api_key";
        public static String status = "status";
        public static String created_at = "created_at";
    }

}
