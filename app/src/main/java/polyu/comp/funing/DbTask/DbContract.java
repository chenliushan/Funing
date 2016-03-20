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
                        FeedProduct.COLUMN_NAME_P_CREATEDAT+ TEXT_TYPE + 
                        " )";
        protected static final String SQL_DELETE_TABLE_PRODUCT =
                "DROP TABLE IF EXISTS " + FeedProduct.TABLE_NAME;
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

}
