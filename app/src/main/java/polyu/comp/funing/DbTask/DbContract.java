package polyu.comp.funing.DbTask;

import android.provider.BaseColumns;

/**
 * Created by liushanchen on 16/3/19.
 */
public final class DbContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public DbContract() {
    }

    /* Inner class that defines the table contents */
    protected static abstract class FeedDb {
        private static final String TEXT_TYPE = " TEXT";
        private static final String INT_TYPE = " INTEGER";
        private static final String DOUBLE_TYPE = " REAL";
        private static final String COMMA_SEP = ",";

        protected static final String SQL_CREATE_TABLE_COUPONS =
                "CREATE TABLE " + FeedCoupons.TABLE_NAME + " (" +
                        FeedCoupons.COLUMN_NAME_CID + INT_TYPE + " INTEGER PRIMARY KEY," +
                        FeedCoupons.COLUMN_NAME_C_CODE + TEXT_TYPE + COMMA_SEP +
                        FeedCoupons.COLUMN_NAME_C_NAME + TEXT_TYPE + COMMA_SEP +
                        FeedCoupons.COLUMN_NAME_C_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                        FeedCoupons.COLUMN_NAME_C_STATUS + TEXT_TYPE + COMMA_SEP +
                        FeedCoupons.COLUMN_NAME_C_IMAGE_URL + TEXT_TYPE + COMMA_SEP +
                        FeedCoupons.COLUMN_NAME_C_CREATED_AT + TEXT_TYPE + COMMA_SEP +
                        FeedCoupons.COLUMN_NAME_C_DISCOUNT_TYPE + TEXT_TYPE + COMMA_SEP +
                        FeedCoupons.COLUMN_NAME_C_DISCOUNT_DETAIL + DOUBLE_TYPE +
                        " )";
        protected static final String SQL_DELETE_TABLE_COUPONS =
                "DROP TABLE IF EXISTS " + FeedCoupons.TABLE_NAME;

        protected static final String SQL_CREATE_TABLE_PRODUCT =
                "CREATE TABLE " + FeedProduct.TABLE_NAME + " (" +
                        FeedProduct.COLUMN_NAME_PID + INT_TYPE + " INTEGER PRIMARY KEY," +
                        FeedProduct.COLUMN_NAME_P_CODE + TEXT_TYPE + COMMA_SEP +
                        FeedProduct.COLUMN_NAME_P_NAME + TEXT_TYPE + COMMA_SEP +
                        FeedProduct.COLUMN_NAME_P_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                        FeedProduct.COLUMN_NAME_P_QUANTITY + INT_TYPE + COMMA_SEP +
                        FeedProduct.COLUMN_NAME_P_PRICE + DOUBLE_TYPE + COMMA_SEP +
                        FeedProduct.COLUMN_NAME_P_IMAGE_URL + TEXT_TYPE + COMMA_SEP +
                        FeedProduct.COLUMN_NAME_P_TYPE + TEXT_TYPE + COMMA_SEP +
                        FeedProduct.COLUMN_NAME_P_CREATEDAT + TEXT_TYPE +
                        " )";
        protected static final String SQL_DELETE_TABLE_PRODUCT =
                "DROP TABLE IF EXISTS " + FeedProduct.TABLE_NAME;

        protected static final String SQL_CREATE_TABLE_SHOPPING_CART =
                "CREATE TABLE " + FeedShoppingCart.TABLE_NAME + " (" +
                        FeedShoppingCart.COLUMN_NAME_SID + INT_TYPE + " INTEGER PRIMARY KEY," +
                        FeedShoppingCart.COLUMN_NAME_UID + INT_TYPE + COMMA_SEP +
                        FeedShoppingCart.COLUMN_NAME_S_AMOUNT + DOUBLE_TYPE + COMMA_SEP +
                        FeedShoppingCart.COLUMN_NAME_S_STATUS + TEXT_TYPE + COMMA_SEP +
                        FeedShoppingCart.COLUMN_NAME_S_CREATED_AT + TEXT_TYPE +
                        " )";
        protected static final String SQL_DELETE_TABLE_SHOPPING_CART =
                "DROP TABLE IF EXISTS " + FeedShoppingCart.TABLE_NAME;
        
        protected static final String SQL_CREATE_TABLE_SHOPPING_CART_DETAIL =
                "CREATE TABLE " + FeedShoppingCartDetail.TABLE_NAME + " (" +
                        FeedShoppingCartDetail.COLUMN_NAME_SDID + INT_TYPE + " INTEGER PRIMARY KEY," +
                        FeedShoppingCartDetail.COLUMN_NAME_SID + INT_TYPE + COMMA_SEP +
                        FeedShoppingCartDetail.COLUMN_NAME_PID + INT_TYPE + COMMA_SEP +
                        FeedShoppingCartDetail.COLUMN_NAME_P_CODE + TEXT_TYPE + COMMA_SEP +
                        FeedShoppingCartDetail.COLUMN_NAME_P_NAME + INT_TYPE + COMMA_SEP +
                        FeedShoppingCartDetail.COLUMN_NAME_P_PRICE + DOUBLE_TYPE + COMMA_SEP +
                        FeedShoppingCartDetail.COLUMN_NAME_P_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                        FeedShoppingCartDetail.COLUMN_NAME_SD_SUBAMOUNT + TEXT_TYPE + COMMA_SEP +
                        FeedShoppingCartDetail.COLUMN_NAME_SD_QUANTITY + TEXT_TYPE + COMMA_SEP +
                        FeedShoppingCartDetail.COLUMN_NAME_SD_CREATED_AT + TEXT_TYPE +
                        " )";
        protected static final String SQL_DELETE_TABLE_SHOPPING_CART_DETAIL =
                "DROP TABLE IF EXISTS " + FeedShoppingCartDetail.TABLE_NAME;
       
        protected static final String SQL_CREATE_TABLE_ORDER =
                "CREATE TABLE " + FeedOrder.TABLE_NAME + " (" +
                        FeedOrder.COLUMN_NAME_OID + INT_TYPE + " INTEGER PRIMARY KEY," +
                        FeedOrder.COLUMN_NAME_UCID + INT_TYPE + COMMA_SEP +
                        FeedOrder.COLUMN_NAME_UID + INT_TYPE + COMMA_SEP +
                        FeedOrder.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                        FeedOrder.COLUMN_NAME_ADDRESS + TEXT_TYPE + COMMA_SEP +
                        FeedOrder.COLUMN_NAME_O_AMOUNT + DOUBLE_TYPE + COMMA_SEP +
                        FeedOrder.COLUMN_NAME_PHONE + TEXT_TYPE + COMMA_SEP +
                        FeedOrder.COLUMN_NAME_EMAIL + TEXT_TYPE + COMMA_SEP +
                        FeedOrder.COLUMN_NAME_O_STATUS + TEXT_TYPE + COMMA_SEP +
                        FeedOrder.COLUMN_NAME_O_CREATED_AT + TEXT_TYPE +
                        " )";
        protected static final String SQL_DELETE_TABLE_ORDER=
                "DROP TABLE IF EXISTS " + FeedOrder.TABLE_NAME;
        
        protected static final String SQL_CREATE_TABLE_ORDER_DETAIL =
                "CREATE TABLE " + FeedOrderDetail.TABLE_NAME + " (" +
                        FeedOrderDetail.COLUMN_NAME_ODID + INT_TYPE + " INTEGER PRIMARY KEY," +
                        FeedOrderDetail.COLUMN_NAME_OID + INT_TYPE + COMMA_SEP +
                        FeedOrderDetail.COLUMN_NAME_PID + INT_TYPE + COMMA_SEP +
                        FeedOrderDetail.COLUMN_NAME_P_CODE + TEXT_TYPE + COMMA_SEP +
                        FeedOrderDetail.COLUMN_NAME_P_NAME + INT_TYPE + COMMA_SEP +
                        FeedOrderDetail.COLUMN_NAME_P_PRICE + DOUBLE_TYPE + COMMA_SEP +
                        FeedOrderDetail.COLUMN_NAME_P_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                        FeedOrderDetail.COLUMN_NAME_OD_QUANTITY + INT_TYPE + COMMA_SEP +
                        FeedOrderDetail.COLUMN_NAME_OD_SUBAMOUNT + DOUBLE_TYPE + COMMA_SEP +
                        FeedOrderDetail.COLUMN_NAME_OD_CREATED_AT + TEXT_TYPE +
                        " )";
        protected static final String SQL_DELETE_TABLE_ORDER_DETAIL =
                "DROP TABLE IF EXISTS " + FeedOrderDetail.TABLE_NAME;

        

    }

    public static abstract class FeedCoupons implements BaseColumns {
        public static final String TABLE_NAME = "coupons";
        public static final String COLUMN_NAME_CID = "cid";
        public static final String COLUMN_NAME_C_CODE = "c_code";
        public static final String COLUMN_NAME_C_NAME = "c_name";
        public static final String COLUMN_NAME_C_DESCRIPTION = "c_description";
        public static final String COLUMN_NAME_C_STATUS = "c_status";
        public static final String COLUMN_NAME_C_IMAGE_URL = "c_image_url";
        public static final String COLUMN_NAME_C_CREATED_AT = "c_created_at";
        public static final String COLUMN_NAME_C_DISCOUNT_TYPE = "c_discount_type";
        public static final String COLUMN_NAME_C_DISCOUNT_DETAIL = "c_discount_detail";
    }

    public static abstract class FeedProduct implements BaseColumns {
        public static final String TABLE_NAME = "product";
        public static final String COLUMN_NAME_PID = "pid";
        public static final String COLUMN_NAME_P_CODE = "p_code";
        public static final String COLUMN_NAME_P_NAME = "p_name";
        public static final String COLUMN_NAME_P_DESCRIPTION = "p_description";
        public static final String COLUMN_NAME_P_QUANTITY = "p_quantity";
        public static final String COLUMN_NAME_P_PRICE = "p_price";
        public static final String COLUMN_NAME_P_IMAGE_URL = "p_image_url";
        public static final String COLUMN_NAME_P_TYPE = "p_type";
        public static final String COLUMN_NAME_P_CREATEDAT = "p_createdAt";
    }

    public static abstract class FeedShoppingCart implements BaseColumns {
        public static final String TABLE_NAME = "shopping_cart";
        public static final String COLUMN_NAME_SID = "sid";
        public static final String COLUMN_NAME_UID = "uid";
        public static final String COLUMN_NAME_S_AMOUNT = "s_amount";
        public static final String COLUMN_NAME_S_STATUS = "s_status";
        public static final String COLUMN_NAME_S_CREATED_AT = "s_created_at";
    }

    public static abstract class FeedShoppingCartDetail implements BaseColumns {
        public static final String TABLE_NAME = "shopping_cart_detail";
        public static final String COLUMN_NAME_SDID = "sdid";
        public static final String COLUMN_NAME_SID = "sid";
        public static final String COLUMN_NAME_PID = "pid";
        public static final String COLUMN_NAME_P_CODE = "p_code";
        public static final String COLUMN_NAME_P_NAME = "p_name";
        public static final String COLUMN_NAME_P_PRICE = "p_price";
        public static final String COLUMN_NAME_SD_SUBAMOUNT = "sd_subamount";
        public static final String COLUMN_NAME_P_DESCRIPTION = "p_description";
        public static final String COLUMN_NAME_SD_QUANTITY = "sd_quantity";
        public static final String COLUMN_NAME_SD_CREATED_AT = "sd_created_at";

    }

    public static abstract class FeedOrder implements BaseColumns {

        public static final String TABLE_NAME = "order_t";
        public static final String COLUMN_NAME_OID = "oid";
        public static final String COLUMN_NAME_O_AMOUNT = "o_amount";
        public static final String COLUMN_NAME_O_STATUS = "o_status";
        public static final String COLUMN_NAME_UID = "uid";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_ADDRESS = "address";
        public static final String COLUMN_NAME_PHONE = "phone";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_UCID = "ucid";
        public static final String COLUMN_NAME_O_CREATED_AT = "o_created_at";

    }

    public static abstract class FeedOrderDetail implements BaseColumns {
       
        public static final String TABLE_NAME = "order_detail";
        public static final String COLUMN_NAME_ODID = "odid";
        public static final String COLUMN_NAME_PID = "pid";
        public static final String COLUMN_NAME_P_CODE = "p_code";
        public static final String COLUMN_NAME_P_NAME = "p_name";
        public static final String COLUMN_NAME_P_PRICE = "p_price";
        public static final String COLUMN_NAME_P_DESCRIPTION = "p_description";
        public static final String COLUMN_NAME_OD_QUANTITY = "od_quantity";
        public static final String COLUMN_NAME_OD_SUBAMOUNT = "od_subamount";
        public static final String COLUMN_NAME_OID = "oid";
        public static final String COLUMN_NAME_OD_CREATED_AT = "od_created_at";

    }

}
