package polyu.comp.funing.DbTask;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import polyu.comp.funing.model.Coupon;
import polyu.comp.funing.model.Order;
import polyu.comp.funing.model.OrderDetail;
import polyu.comp.funing.model.Product;
import polyu.comp.funing.model.ShoppingCart;
import polyu.comp.funing.model.ShoppingCartDetail;

/**
 * Created by liushanchen on 16/3/19.
 */
public class DbTask {
    private static String TAG = DbTask.class.getSimpleName();
    private static SQLiteDatabase db = null;
    private static DbTask dbTask = null;

    private DbTask(Context context) {
        if (dbTask == null) {
            MyDbHelper mDbHelper = new MyDbHelper(context);
            this.db = mDbHelper.getWritableDatabase();
        }
        this.dbTask = this;
    }

    public static DbTask getDbTask(Context context) {
        if (dbTask != null) {
            Log.i(TAG, "static db:" + dbTask.db.toString());
            return dbTask;
        } else {
            return new DbTask(context);
        }
    }

    public static class DbCoupon extends AsyncTask<Coupon, Integer, Boolean> {
        private Context mContext;


        public DbCoupon(Context context) {
            this.mContext = context;
            if (db == null) {
                // Gets the data repository in write mode

            }
        }

        @Override
        protected Boolean doInBackground(Coupon... coupons) {
            if (coupons == null) {
                return false;
            }
            MyDbHelper mDbHelper = new MyDbHelper(mContext);


            // Create a new map of values, where column names are the keys

            for (Coupon c : coupons) {
                ContentValues values = new ContentValues();

                values.put(DbContract.FeedCoupons.COLUMN_NAME_CID, c.getCid());
                values.put(DbContract.FeedCoupons.COLUMN_NAME_C_CODE, c.getC_code());
                values.put(DbContract.FeedCoupons.COLUMN_NAME_C_NAME, c.getC_name());
                values.put(DbContract.FeedCoupons.COLUMN_NAME_C_DESCRIPTION, c.getC_description());
                values.put(DbContract.FeedCoupons.COLUMN_NAME_C_STATUS, c.getC_status());
                values.put(DbContract.FeedCoupons.COLUMN_NAME_C_IMAGE_URL, c.getC_image_url());
                values.put(DbContract.FeedCoupons.COLUMN_NAME_C_CREATED_AT, c.getC_created_at());
                values.put(DbContract.FeedCoupons.COLUMN_NAME_C_DISCOUNT_TYPE, c.getC_discount_type());
                values.put(DbContract.FeedCoupons.COLUMN_NAME_C_DISCOUNT_DETAIL, c.getC_discount_detail());
                // Insert the new row, returning the primary key value of the new row
                long newRowId;
                newRowId = db.insert(DbContract.FeedCoupons.TABLE_NAME, null, values);
            }
            return true;
        }

        protected void onProgressUpdate(Integer... progress) {
        }

        protected void onPostExecute(Long result) {
        }
    }

    public static class DbProduct {
        public DbProduct(Context context) {
            getDbTask(context);
        }
        public Boolean DbProductInsert(List<Product> objs) {
            if (objs == null) {
                return false;
            }
//            // Create a new map of values, where column names are the keys
            for (Product o : objs) {
                ContentValues values = new ContentValues();
                values.put(DbContract.FeedProduct.COLUMN_NAME_PID, o.getPid());
                values.put(DbContract.FeedProduct.COLUMN_NAME_P_CODE, o.getP_code());
                values.put(DbContract.FeedProduct.COLUMN_NAME_P_NAME, o.getP_name());
                values.put(DbContract.FeedProduct.COLUMN_NAME_P_DESCRIPTION, o.getP_description());
                values.put(DbContract.FeedProduct.COLUMN_NAME_P_QUANTITY, o.getP_quantity());
                values.put(DbContract.FeedProduct.COLUMN_NAME_P_PRICE, o.getP_price());
                values.put(DbContract.FeedProduct.COLUMN_NAME_P_IMAGE_URL, o.getP_image_url());
                values.put(DbContract.FeedProduct.COLUMN_NAME_P_TYPE, o.getP_type());
                values.put(DbContract.FeedProduct.COLUMN_NAME_P_CREATEDAT, o.getP_createdAt());
                // Insert the new row, returning the primary key value of the new row
                long newRowId;
                newRowId = db.insert(DbContract.FeedProduct.TABLE_NAME, null, values);
            }
            return true;
        }
        public Boolean DbProductUpdate(List<Product> objs) {
            if (objs == null) {
                return false;
            }
            int count = 0;
            for (Product o : objs) {
                ContentValues values = new ContentValues();
                values.put(DbContract.FeedProduct.COLUMN_NAME_PID, o.getPid());
                values.put(DbContract.FeedProduct.COLUMN_NAME_P_CODE, o.getP_code());
                values.put(DbContract.FeedProduct.COLUMN_NAME_P_NAME, o.getP_name());
                values.put(DbContract.FeedProduct.COLUMN_NAME_P_DESCRIPTION, o.getP_description());
                values.put(DbContract.FeedProduct.COLUMN_NAME_P_QUANTITY, o.getP_quantity());
                values.put(DbContract.FeedProduct.COLUMN_NAME_P_PRICE, o.getP_price());
                values.put(DbContract.FeedProduct.COLUMN_NAME_P_IMAGE_URL, o.getP_image_url());
                values.put(DbContract.FeedProduct.COLUMN_NAME_P_TYPE, o.getP_type());
                values.put(DbContract.FeedProduct.COLUMN_NAME_P_CREATEDAT, o.getP_createdAt());
                // Insert the new row, returning the primary key value of the new row
                count += (int) db.update(DbContract.FeedProduct.TABLE_NAME, values, DbContract.FeedProduct.COLUMN_NAME_PID + "=?", new String[]{o.getPid() + ""});

            }
            if (count == objs.size()) {
                return true;
            } else {
                return false;
            }

        }

        public List<Product> DbProductQuery() {

            // Define a projection that specifies which columns from the database
            // you will actually use after this query.
            String[] projection = {
                    DbContract.FeedProduct.COLUMN_NAME_PID,
                    DbContract.FeedProduct.COLUMN_NAME_P_CODE,
                    DbContract.FeedProduct.COLUMN_NAME_P_NAME,
                    DbContract.FeedProduct.COLUMN_NAME_P_DESCRIPTION,
                    DbContract.FeedProduct.COLUMN_NAME_P_QUANTITY,
                    DbContract.FeedProduct.COLUMN_NAME_P_PRICE,
                    DbContract.FeedProduct.COLUMN_NAME_P_IMAGE_URL,
                    DbContract.FeedProduct.COLUMN_NAME_P_TYPE,
                    DbContract.FeedProduct.COLUMN_NAME_P_CREATEDAT,
            };
            Cursor c = db.query(
                    DbContract.FeedProduct.TABLE_NAME,  // The table to query
                    projection,                               // The columns to return
                    null,                                // The columns for the WHERE clause
                    null,                            // The values for the WHERE clause
                    null,                                     // don't group the rows
                    null,                                     // don't filter by row groups
                    null,                                 // The sort order
                    String.valueOf(10)                                 // The limit
            );
            List<Product> products = new ArrayList<Product>();
            c.moveToFirst();
            while (!c.isAfterLast()) {
                Product o = new Product();
                o.setPid(c.getInt(c.getColumnIndex(DbContract.FeedProduct.COLUMN_NAME_PID)));
                o.setP_code(c.getString(c.getColumnIndex(DbContract.FeedProduct.COLUMN_NAME_P_CODE)));
                o.setP_name(c.getString(c.getColumnIndex(DbContract.FeedProduct.COLUMN_NAME_P_NAME)));
                o.setP_description(c.getString(c.getColumnIndex(DbContract.FeedProduct.COLUMN_NAME_P_DESCRIPTION)));
                o.setP_quantity(c.getInt(c.getColumnIndex(DbContract.FeedProduct.COLUMN_NAME_P_QUANTITY)));
                o.setP_price(c.getDouble(c.getColumnIndex(DbContract.FeedProduct.COLUMN_NAME_P_PRICE)));
                o.setP_image_url(c.getString(c.getColumnIndex(DbContract.FeedProduct.COLUMN_NAME_P_IMAGE_URL)));
                o.setP_type(c.getString(c.getColumnIndex(DbContract.FeedProduct.COLUMN_NAME_P_TYPE)));
                o.setP_createdAt(c.getString(c.getColumnIndex(DbContract.FeedProduct.COLUMN_NAME_P_CREATEDAT)));
                products.add(o);
                c.moveToNext();
            }
            Log.i(TAG, "DbProductQuery-products: " + products);
            return products;
        }
    }

    public static class DbShoppingCart {

        private String valid = "Valid";


        public DbShoppingCart(Context mContext) {
            getDbTask(mContext);
        }

        public Boolean insert(List<ShoppingCart> objs) {
            if (objs == null) {
                return false;
            }


            for (ShoppingCart o : objs) {
                ContentValues values = new ContentValues();
                values.put(DbContract.FeedShoppingCart.COLUMN_NAME_SID, o.getSid());
                values.put(DbContract.FeedShoppingCart.COLUMN_NAME_UID, o.getUid());
                values.put(DbContract.FeedShoppingCart.COLUMN_NAME_S_AMOUNT, o.getS_amount());
                values.put(DbContract.FeedShoppingCart.COLUMN_NAME_S_STATUS, o.getS_status());
                values.put(DbContract.FeedShoppingCart.COLUMN_NAME_S_CREATED_AT, o.getS_created_at());
                // Insert the new row, returning the primary key value of the new row
                long newRowId;
                newRowId = db.insert(DbContract.FeedShoppingCart.TABLE_NAME, null, values);
            }
            return true;
        }

        public int insert(ShoppingCart o) {
            if (o == null) {
                return -1;
            }


            ContentValues values = new ContentValues();
            values.put(DbContract.FeedShoppingCart.COLUMN_NAME_SID, o.getSid());
            values.put(DbContract.FeedShoppingCart.COLUMN_NAME_UID, o.getUid());
            values.put(DbContract.FeedShoppingCart.COLUMN_NAME_S_AMOUNT, o.getS_amount());
            values.put(DbContract.FeedShoppingCart.COLUMN_NAME_S_STATUS, o.getS_status());
            values.put(DbContract.FeedShoppingCart.COLUMN_NAME_S_CREATED_AT, o.getS_created_at());
            // Insert the new row, returning the primary key value of the new row
            int newRowId = (int) db.insert(DbContract.FeedShoppingCart.TABLE_NAME, null, values);
            return newRowId;
        }

        public Boolean update(List<ShoppingCart> objs) {
            if (objs == null) {
                return false;
            }

            int count = 0;
            for (ShoppingCart o : objs) {
                ContentValues values = new ContentValues();
                values.put(DbContract.FeedShoppingCart.COLUMN_NAME_SID, o.getSid());
                values.put(DbContract.FeedShoppingCart.COLUMN_NAME_UID, o.getUid());
                values.put(DbContract.FeedShoppingCart.COLUMN_NAME_S_AMOUNT, o.getS_amount());
                values.put(DbContract.FeedShoppingCart.COLUMN_NAME_S_STATUS, o.getS_status());
                values.put(DbContract.FeedShoppingCart.COLUMN_NAME_S_CREATED_AT, o.getS_created_at());
                long newRowId;
                newRowId = db.update(DbContract.FeedShoppingCart.TABLE_NAME, values, DbContract.FeedShoppingCart.COLUMN_NAME_SID + "=?", new String[]{o.getSid() + ""});
                count += newRowId;
            }
            if (count == objs.size()) {
                return true;
            }
            return false;
        }

        public int update(ShoppingCart o) {
            if (o == null) {
                return -1;
            }

            int count = 0;
            ContentValues values = new ContentValues();
            values.put(DbContract.FeedShoppingCart.COLUMN_NAME_SID, o.getSid());
            values.put(DbContract.FeedShoppingCart.COLUMN_NAME_UID, o.getUid());
            values.put(DbContract.FeedShoppingCart.COLUMN_NAME_S_AMOUNT, o.getS_amount());
            values.put(DbContract.FeedShoppingCart.COLUMN_NAME_S_STATUS, o.getS_status());
            values.put(DbContract.FeedShoppingCart.COLUMN_NAME_S_CREATED_AT, o.getS_created_at());
            int newRowId = db.update(DbContract.FeedShoppingCart.TABLE_NAME, values, DbContract.FeedShoppingCart.COLUMN_NAME_SID + "=?", new String[]{o.getSid() + ""});
            Log.i(TAG, "update-ShoppingCart: " + newRowId);
            return newRowId;
        }

        public List<ShoppingCart> query() {

            String[] projection = {
                    DbContract.FeedShoppingCart.COLUMN_NAME_SID,
                    DbContract.FeedShoppingCart.COLUMN_NAME_UID,
                    DbContract.FeedShoppingCart.COLUMN_NAME_S_STATUS,
                    DbContract.FeedShoppingCart.COLUMN_NAME_S_AMOUNT,
                    DbContract.FeedShoppingCart.COLUMN_NAME_S_CREATED_AT,

            };
            Cursor c = db.query(
                    DbContract.FeedShoppingCart.TABLE_NAME,  // The table to query
                    projection,                               // The columns to return
                    DbContract.FeedShoppingCart.COLUMN_NAME_S_STATUS + "=?",     // The columns for the WHERE clause
                    new String[]{valid},                            // The values for the WHERE clause
                    null,                                     // don't group the rows
                    null,                                     // don't filter by row groups
                    null,                                 // The sort order
                    String.valueOf(10)                                 // The limit
            );
            List<ShoppingCart> list = new ArrayList<ShoppingCart>();
            c.moveToFirst();
            while (!c.isAfterLast()) {
                ShoppingCart o = new ShoppingCart();
                o.setSid(c.getInt(c.getColumnIndex(DbContract.FeedShoppingCart.COLUMN_NAME_SID)));
                o.setUid(c.getInt(c.getColumnIndex(DbContract.FeedShoppingCart.COLUMN_NAME_UID)));
                o.setS_amount(c.getDouble(c.getColumnIndex(DbContract.FeedShoppingCart.COLUMN_NAME_S_AMOUNT)));
                o.setS_status(c.getString(c.getColumnIndex(DbContract.FeedShoppingCart.COLUMN_NAME_S_STATUS)));
                o.setS_created_at(c.getString(c.getColumnIndex(DbContract.FeedShoppingCart.COLUMN_NAME_S_CREATED_AT)));

                list.add(o);
                c.moveToNext();
            }
            Log.i(TAG, "query-ShoppingCart: " + list);
            return list;
        }

        public List<ShoppingCart> query(int uid) {

            String[] projection = {
                    DbContract.FeedShoppingCart.COLUMN_NAME_SID,
                    DbContract.FeedShoppingCart.COLUMN_NAME_UID,
                    DbContract.FeedShoppingCart.COLUMN_NAME_S_STATUS,
                    DbContract.FeedShoppingCart.COLUMN_NAME_S_AMOUNT,
                    DbContract.FeedShoppingCart.COLUMN_NAME_S_CREATED_AT,

            };
            Cursor c = db.query(
                    DbContract.FeedShoppingCart.TABLE_NAME,  // The table to query
                    projection,                               // The columns to return
                    DbContract.FeedShoppingCart.COLUMN_NAME_UID + "=? and "
                            + DbContract.FeedShoppingCart.COLUMN_NAME_S_STATUS + "=?",  // The columns for the WHERE clause
                    new String[]{uid + "", valid},             // The values for the WHERE clause
                    null,                                     // don't group the rows
                    null,                                     // don't filter by row groups
                    null,                                 // The sort order
                    String.valueOf(10)                                 // The limit
            );
            List<ShoppingCart> list = new ArrayList<ShoppingCart>();
            c.moveToFirst();
            while (!c.isAfterLast()) {
                ShoppingCart o = new ShoppingCart();
                o.setSid(c.getInt(c.getColumnIndex(DbContract.FeedShoppingCart.COLUMN_NAME_SID)));
                o.setUid(c.getInt(c.getColumnIndex(DbContract.FeedShoppingCart.COLUMN_NAME_UID)));
                o.setS_amount(c.getDouble(c.getColumnIndex(DbContract.FeedShoppingCart.COLUMN_NAME_S_AMOUNT)));
                o.setS_status(c.getString(c.getColumnIndex(DbContract.FeedShoppingCart.COLUMN_NAME_S_STATUS)));
                o.setS_created_at(c.getString(c.getColumnIndex(DbContract.FeedShoppingCart.COLUMN_NAME_S_CREATED_AT)));

                list.add(o);
                c.moveToNext();
            }
            Log.i(TAG, "query-uid-ShoppingCart: " + list);
            return list;
        }

        public List<ShoppingCart> querySid(int sid) {

            String[] projection = {
                    DbContract.FeedShoppingCart.COLUMN_NAME_SID,
                    DbContract.FeedShoppingCart.COLUMN_NAME_UID,
                    DbContract.FeedShoppingCart.COLUMN_NAME_S_STATUS,
                    DbContract.FeedShoppingCart.COLUMN_NAME_S_AMOUNT,
                    DbContract.FeedShoppingCart.COLUMN_NAME_S_CREATED_AT,

            };
            Cursor c = db.query(
                    DbContract.FeedShoppingCart.TABLE_NAME,  // The table to query
                    projection,                               // The columns to return
                    DbContract.FeedShoppingCart.COLUMN_NAME_SID + "=? and "
                            + DbContract.FeedShoppingCart.COLUMN_NAME_S_STATUS + "=?",  // The columns for the WHERE clause
                    new String[]{sid + "", valid},             // The values for the WHERE clause
                    null,                                     // don't group the rows
                    null,                                     // don't filter by row groups
                    null,                                 // The sort order
                    String.valueOf(10)                                 // The limit
            );
            List<ShoppingCart> list = new ArrayList<ShoppingCart>();
            c.moveToFirst();
            while (!c.isAfterLast()) {
                ShoppingCart o = new ShoppingCart();
                o.setSid(c.getInt(c.getColumnIndex(DbContract.FeedShoppingCart.COLUMN_NAME_SID)));
                o.setUid(c.getInt(c.getColumnIndex(DbContract.FeedShoppingCart.COLUMN_NAME_UID)));
                o.setS_amount(c.getDouble(c.getColumnIndex(DbContract.FeedShoppingCart.COLUMN_NAME_S_AMOUNT)));
                o.setS_status(c.getString(c.getColumnIndex(DbContract.FeedShoppingCart.COLUMN_NAME_S_STATUS)));
                o.setS_created_at(c.getString(c.getColumnIndex(DbContract.FeedShoppingCart.COLUMN_NAME_S_CREATED_AT)));

                list.add(o);
                c.moveToNext();
            }
            Log.i(TAG, "querySid-ShoppingCart: " + list);
            return list;
        }
    }

    public static class DbShoppingCartDetail {

        public DbShoppingCartDetail(Context mContext) {
            getDbTask(mContext);
        }

        public boolean delete(int sdid) {
            if (sdid == -1) {
                return false;
            }
            if (db.delete(DbContract.FeedShoppingCartDetail.TABLE_NAME, DbContract.FeedShoppingCartDetail.COLUMN_NAME_SDID + "=?", new String[]{sdid + ""}) > 0) {
                return true;
            }
            return false;
        }

        public Boolean insert(List<ShoppingCartDetail> objs) {
            if (objs == null) {
                return false;
            }

            for (ShoppingCartDetail o : objs) {
                ContentValues values = new ContentValues();
                values.put(DbContract.FeedShoppingCartDetail.COLUMN_NAME_SDID, o.getSdid());
                values.put(DbContract.FeedShoppingCartDetail.COLUMN_NAME_SID, o.getSid());
                values.put(DbContract.FeedShoppingCartDetail.COLUMN_NAME_PID, o.getPid());
                values.put(DbContract.FeedShoppingCartDetail.COLUMN_NAME_P_NAME, o.getP_name());
                values.put(DbContract.FeedShoppingCartDetail.COLUMN_NAME_P_PRICE, o.getP_price());
                values.put(DbContract.FeedShoppingCartDetail.COLUMN_NAME_P_CODE, o.getP_code());
                values.put(DbContract.FeedShoppingCartDetail.COLUMN_NAME_P_DESCRIPTION, o.getP_description());
                values.put(DbContract.FeedShoppingCartDetail.COLUMN_NAME_SD_QUANTITY, o.getSd_quantity());
                values.put(DbContract.FeedShoppingCartDetail.COLUMN_NAME_SD_SUBAMOUNT, o.getSd_subamount());
                values.put(DbContract.FeedShoppingCartDetail.COLUMN_NAME_SD_CREATED_AT, o.getSd_created_at());
                // Insert the new row, returning the primary key value of the new row
                long newRowId;
                newRowId = db.insert(DbContract.FeedShoppingCartDetail.TABLE_NAME, null, values);
            }
            return true;
        }

        public int insert(ShoppingCartDetail o) {
            if (o == null) {
                return -1;
            }

            ContentValues values = new ContentValues();
            values.put(DbContract.FeedShoppingCartDetail.COLUMN_NAME_SDID, o.getSdid());
            values.put(DbContract.FeedShoppingCartDetail.COLUMN_NAME_SID, o.getSid());
            values.put(DbContract.FeedShoppingCartDetail.COLUMN_NAME_PID, o.getPid());
            values.put(DbContract.FeedShoppingCartDetail.COLUMN_NAME_P_NAME, o.getP_name());
            values.put(DbContract.FeedShoppingCartDetail.COLUMN_NAME_P_PRICE, o.getP_price());
            values.put(DbContract.FeedShoppingCartDetail.COLUMN_NAME_P_CODE, o.getP_code());
            values.put(DbContract.FeedShoppingCartDetail.COLUMN_NAME_P_DESCRIPTION, o.getP_description());
            values.put(DbContract.FeedShoppingCartDetail.COLUMN_NAME_SD_QUANTITY, o.getSd_quantity());
            values.put(DbContract.FeedShoppingCartDetail.COLUMN_NAME_SD_SUBAMOUNT, o.getSd_subamount());
            values.put(DbContract.FeedShoppingCartDetail.COLUMN_NAME_SD_CREATED_AT, o.getSd_created_at());
            // Insert the new row, returning the primary key value of the new row
            int newRowId = (int) db.insert(DbContract.FeedShoppingCartDetail.TABLE_NAME, null, values);
            return newRowId;
        }


        public Boolean update(List<ShoppingCartDetail> objs) {
            if (objs == null) {
                return false;
            }

            int count = 0;
            for (ShoppingCartDetail o : objs) {
                ContentValues values = new ContentValues();
                values.put(DbContract.FeedShoppingCartDetail.COLUMN_NAME_SDID, o.getSdid());
                values.put(DbContract.FeedShoppingCartDetail.COLUMN_NAME_SID, o.getSid());
                values.put(DbContract.FeedShoppingCartDetail.COLUMN_NAME_PID, o.getPid());
                values.put(DbContract.FeedShoppingCartDetail.COLUMN_NAME_P_NAME, o.getP_name());
                values.put(DbContract.FeedShoppingCartDetail.COLUMN_NAME_P_PRICE, o.getP_price());
                values.put(DbContract.FeedShoppingCartDetail.COLUMN_NAME_P_CODE, o.getP_code());
                values.put(DbContract.FeedShoppingCartDetail.COLUMN_NAME_P_DESCRIPTION, o.getP_description());
                values.put(DbContract.FeedShoppingCartDetail.COLUMN_NAME_SD_QUANTITY, o.getSd_quantity());
                values.put(DbContract.FeedShoppingCartDetail.COLUMN_NAME_SD_SUBAMOUNT, o.getSd_subamount());
                values.put(DbContract.FeedShoppingCartDetail.COLUMN_NAME_SD_CREATED_AT, o.getSd_created_at());
                long newRowId;
                newRowId = db.update(DbContract.FeedShoppingCartDetail.TABLE_NAME, values, DbContract.FeedShoppingCartDetail.COLUMN_NAME_SDID + "=?", new String[]{o.getSdid() + ""});
                count += newRowId;
            }
            if (count == objs.size()) {
                return true;
            }
            return false;
        }

        public int update(ShoppingCartDetail o) {
            if (o == null) {
                return -1;
            }

            int count = 0;

            ContentValues values = new ContentValues();
            values.put(DbContract.FeedShoppingCartDetail.COLUMN_NAME_SDID, o.getSdid());
            values.put(DbContract.FeedShoppingCartDetail.COLUMN_NAME_SID, o.getSid());
            values.put(DbContract.FeedShoppingCartDetail.COLUMN_NAME_PID, o.getPid());
            values.put(DbContract.FeedShoppingCartDetail.COLUMN_NAME_P_NAME, o.getP_name());
            values.put(DbContract.FeedShoppingCartDetail.COLUMN_NAME_P_PRICE, o.getP_price());
            values.put(DbContract.FeedShoppingCartDetail.COLUMN_NAME_P_CODE, o.getP_code());
            values.put(DbContract.FeedShoppingCartDetail.COLUMN_NAME_P_DESCRIPTION, o.getP_description());
            values.put(DbContract.FeedShoppingCartDetail.COLUMN_NAME_SD_QUANTITY, o.getSd_quantity());
            values.put(DbContract.FeedShoppingCartDetail.COLUMN_NAME_SD_SUBAMOUNT, o.getSd_subamount());
            values.put(DbContract.FeedShoppingCartDetail.COLUMN_NAME_SD_CREATED_AT, o.getSd_created_at());
            int newRowId = db.update(DbContract.FeedShoppingCartDetail.TABLE_NAME, values, DbContract.FeedShoppingCartDetail.COLUMN_NAME_SDID + "=?", new String[]{o.getSdid() + ""});

            return newRowId;
        }

        public List<ShoppingCartDetail> query() {

            String[] projection = {
                    DbContract.FeedShoppingCartDetail.COLUMN_NAME_SDID,
                    DbContract.FeedShoppingCartDetail.COLUMN_NAME_SID,
                    DbContract.FeedShoppingCartDetail.COLUMN_NAME_PID,
                    DbContract.FeedShoppingCartDetail.COLUMN_NAME_P_NAME,
                    DbContract.FeedShoppingCartDetail.COLUMN_NAME_P_CODE,
                    DbContract.FeedShoppingCartDetail.COLUMN_NAME_P_PRICE,
                    DbContract.FeedShoppingCartDetail.COLUMN_NAME_P_DESCRIPTION,
                    DbContract.FeedShoppingCartDetail.COLUMN_NAME_SD_SUBAMOUNT,
                    DbContract.FeedShoppingCartDetail.COLUMN_NAME_SD_QUANTITY,
                    DbContract.FeedShoppingCartDetail.COLUMN_NAME_SD_CREATED_AT,

            };
            Cursor c = db.query(
                    DbContract.FeedShoppingCartDetail.TABLE_NAME,  // The table to query
                    projection,                               // The columns to return
                    null,                                // The columns for the WHERE clause
                    null,                            // The values for the WHERE clause
                    null,                                     // don't group the rows
                    null,                                     // don't filter by row groups
                    null,                                 // The sort order
                    String.valueOf(10)                                 // The limit
            );
            List<ShoppingCartDetail> list = new ArrayList<ShoppingCartDetail>();
            c.moveToFirst();
            while (!c.isAfterLast()) {
                ShoppingCartDetail o = new ShoppingCartDetail();
                o.setSdid(c.getInt(c.getColumnIndex(DbContract.FeedShoppingCartDetail.COLUMN_NAME_SDID)));
                o.setSid(c.getInt(c.getColumnIndex(DbContract.FeedShoppingCartDetail.COLUMN_NAME_SID)));
                o.setPid(c.getInt(c.getColumnIndex(DbContract.FeedShoppingCartDetail.COLUMN_NAME_PID)));
                o.setP_name(c.getString(c.getColumnIndex(DbContract.FeedShoppingCartDetail.COLUMN_NAME_P_NAME)));
                o.setP_code(c.getString(c.getColumnIndex(DbContract.FeedShoppingCartDetail.COLUMN_NAME_P_CODE)));
                o.setP_description(c.getString(c.getColumnIndex(DbContract.FeedShoppingCartDetail.COLUMN_NAME_P_DESCRIPTION)));
                o.setP_price(c.getDouble(c.getColumnIndex(DbContract.FeedShoppingCartDetail.COLUMN_NAME_P_PRICE)));
                o.setSd_quantity(c.getInt(c.getColumnIndex(DbContract.FeedShoppingCartDetail.COLUMN_NAME_SD_QUANTITY)));
                o.setSd_subamount(c.getDouble(c.getColumnIndex(DbContract.FeedShoppingCartDetail.COLUMN_NAME_SD_SUBAMOUNT)));
                o.setSd_created_at(c.getString(c.getColumnIndex(DbContract.FeedShoppingCartDetail.COLUMN_NAME_SD_CREATED_AT)));

                list.add(o);
                c.moveToNext();
            }
            Log.i(TAG, "products: " + list);
            return list;
        }

        public List<ShoppingCartDetail> query(int sdid) {

            String[] projection = {
                    DbContract.FeedShoppingCartDetail.COLUMN_NAME_SDID,
                    DbContract.FeedShoppingCartDetail.COLUMN_NAME_SID,
                    DbContract.FeedShoppingCartDetail.COLUMN_NAME_PID,
                    DbContract.FeedShoppingCartDetail.COLUMN_NAME_P_NAME,
                    DbContract.FeedShoppingCartDetail.COLUMN_NAME_P_CODE,
                    DbContract.FeedShoppingCartDetail.COLUMN_NAME_P_PRICE,
                    DbContract.FeedShoppingCartDetail.COLUMN_NAME_P_DESCRIPTION,
                    DbContract.FeedShoppingCartDetail.COLUMN_NAME_SD_SUBAMOUNT,
                    DbContract.FeedShoppingCartDetail.COLUMN_NAME_SD_QUANTITY,
                    DbContract.FeedShoppingCartDetail.COLUMN_NAME_SD_CREATED_AT,

            };
            Cursor c = db.query(
                    DbContract.FeedShoppingCartDetail.TABLE_NAME,  // The table to query
                    projection,                               // The columns to return
                    DbContract.FeedShoppingCartDetail.COLUMN_NAME_SDID + "=?",                                // The columns for the WHERE clause
                    new String[]{sdid + ""},                            // The values for the WHERE clause
                    null,                                     // don't group the rows
                    null,                                     // don't filter by row groups
                    null,                                 // The sort order
                    String.valueOf(10)                                 // The limit
            );
            List<ShoppingCartDetail> list = new ArrayList<ShoppingCartDetail>();
            c.moveToFirst();
            while (!c.isAfterLast()) {
                ShoppingCartDetail o = new ShoppingCartDetail();
                o.setSdid(c.getInt(c.getColumnIndex(DbContract.FeedShoppingCartDetail.COLUMN_NAME_SDID)));
                o.setSid(c.getInt(c.getColumnIndex(DbContract.FeedShoppingCartDetail.COLUMN_NAME_SID)));
                o.setPid(c.getInt(c.getColumnIndex(DbContract.FeedShoppingCartDetail.COLUMN_NAME_PID)));
                o.setP_name(c.getString(c.getColumnIndex(DbContract.FeedShoppingCartDetail.COLUMN_NAME_P_NAME)));
                o.setP_code(c.getString(c.getColumnIndex(DbContract.FeedShoppingCartDetail.COLUMN_NAME_P_CODE)));
                o.setP_description(c.getString(c.getColumnIndex(DbContract.FeedShoppingCartDetail.COLUMN_NAME_P_DESCRIPTION)));
                o.setP_price(c.getDouble(c.getColumnIndex(DbContract.FeedShoppingCartDetail.COLUMN_NAME_P_PRICE)));
                o.setSd_quantity(c.getInt(c.getColumnIndex(DbContract.FeedShoppingCartDetail.COLUMN_NAME_SD_QUANTITY)));
                o.setSd_subamount(c.getDouble(c.getColumnIndex(DbContract.FeedShoppingCartDetail.COLUMN_NAME_SD_SUBAMOUNT)));
                o.setSd_created_at(c.getString(c.getColumnIndex(DbContract.FeedShoppingCartDetail.COLUMN_NAME_SD_CREATED_AT)));

                list.add(o);
                c.moveToNext();
            }
            Log.i(TAG, "products: " + list);
            return list;
        }
    }

    public static class DbOrder {
        public DbOrder(Context mContext) {
            getDbTask(mContext);
        }

        public boolean delete(int oid) {
            if (oid == -1) {
                return false;
            }
            if (db.delete(DbContract.FeedOrder.TABLE_NAME, DbContract.FeedOrder.COLUMN_NAME_OID + "=?", new String[]{oid + ""}) > 0) {
                return true;
            }
            return false;
        }

        /**
         * private int oid;
         * private double o_amount;
         * private String o_status;
         * private int uid;
         * private String name;
         * private String address;
         * private String phone;
         * private String email;
         * private int ucid;
         * private String o_created_at;
         *
         * @param objs
         * @return
         */
        public Boolean insert(List<Order> objs) {
            if (objs == null) {
                return false;
            }
            for (Order o : objs) {
                ContentValues values = new ContentValues();
                values.put(DbContract.FeedOrder.COLUMN_NAME_OID, o.getOid());
                values.put(DbContract.FeedOrder.COLUMN_NAME_O_AMOUNT, o.getO_amount());
                values.put(DbContract.FeedOrder.COLUMN_NAME_O_STATUS, o.getO_status());
                values.put(DbContract.FeedOrder.COLUMN_NAME_UID, o.getUid());
                values.put(DbContract.FeedOrder.COLUMN_NAME_NAME, o.getName());
                values.put(DbContract.FeedOrder.COLUMN_NAME_ADDRESS, o.getAddress());
                values.put(DbContract.FeedOrder.COLUMN_NAME_PHONE, o.getPhone());
                values.put(DbContract.FeedOrder.COLUMN_NAME_EMAIL, o.getEmail());
                values.put(DbContract.FeedOrder.COLUMN_NAME_UCID, o.getUcid());
                values.put(DbContract.FeedOrder.COLUMN_NAME_O_CREATED_AT, o.getO_created_at());
                // Insert the new row, returning the primary key value of the new row
                long newRowId;
                newRowId = db.insert(DbContract.FeedOrder.TABLE_NAME, null, values);
            }
            return true;
        }

        public int insert(Order o) {
            if (o == null) {
                return -1;
            }

            ContentValues values = new ContentValues();
            values.put(DbContract.FeedOrder.COLUMN_NAME_OID, o.getOid());
            values.put(DbContract.FeedOrder.COLUMN_NAME_O_AMOUNT, o.getO_amount());
            values.put(DbContract.FeedOrder.COLUMN_NAME_O_STATUS, o.getO_status());
            values.put(DbContract.FeedOrder.COLUMN_NAME_UID, o.getUid());
            values.put(DbContract.FeedOrder.COLUMN_NAME_NAME, o.getName());
            values.put(DbContract.FeedOrder.COLUMN_NAME_ADDRESS, o.getAddress());
            values.put(DbContract.FeedOrder.COLUMN_NAME_PHONE, o.getPhone());
            values.put(DbContract.FeedOrder.COLUMN_NAME_EMAIL, o.getEmail());
            values.put(DbContract.FeedOrder.COLUMN_NAME_UCID, o.getUcid());
            values.put(DbContract.FeedOrder.COLUMN_NAME_O_CREATED_AT, o.getO_created_at());
            // Insert the new row, returning the primary key value of the new row
            int newRowId = (int) db.insert(DbContract.FeedOrder.TABLE_NAME, null, values);
            return newRowId;
        }


        public Boolean update(List<Order> objs) {
            if (objs == null) {
                return false;
            }

            int count = 0;
            for (Order o : objs) {
                ContentValues values = new ContentValues();
                values.put(DbContract.FeedOrder.COLUMN_NAME_OID, o.getOid());
                values.put(DbContract.FeedOrder.COLUMN_NAME_O_AMOUNT, o.getO_amount());
                values.put(DbContract.FeedOrder.COLUMN_NAME_O_STATUS, o.getO_status());
                values.put(DbContract.FeedOrder.COLUMN_NAME_UID, o.getUid());
                values.put(DbContract.FeedOrder.COLUMN_NAME_NAME, o.getName());
                values.put(DbContract.FeedOrder.COLUMN_NAME_ADDRESS, o.getAddress());
                values.put(DbContract.FeedOrder.COLUMN_NAME_PHONE, o.getPhone());
                values.put(DbContract.FeedOrder.COLUMN_NAME_EMAIL, o.getEmail());
                values.put(DbContract.FeedOrder.COLUMN_NAME_UCID, o.getUcid());
                values.put(DbContract.FeedOrder.COLUMN_NAME_O_CREATED_AT, o.getO_created_at());
                long newRowId;
                newRowId = db.update(DbContract.FeedOrder.TABLE_NAME, values, DbContract.FeedOrder.COLUMN_NAME_OID + "=?", new String[]{o.getOid() + ""});
                count += newRowId;
            }
            if (count == objs.size()) {
                return true;
            }
            return false;
        }

        public int update(Order o) {
            if (o == null) {
                return -1;
            }

            int count = 0;

            ContentValues values = new ContentValues();
            values.put(DbContract.FeedOrder.COLUMN_NAME_OID, o.getOid());
            values.put(DbContract.FeedOrder.COLUMN_NAME_O_AMOUNT, o.getO_amount());
            values.put(DbContract.FeedOrder.COLUMN_NAME_O_STATUS, o.getO_status());
            values.put(DbContract.FeedOrder.COLUMN_NAME_UID, o.getUid());
            values.put(DbContract.FeedOrder.COLUMN_NAME_NAME, o.getName());
            values.put(DbContract.FeedOrder.COLUMN_NAME_ADDRESS, o.getAddress());
            values.put(DbContract.FeedOrder.COLUMN_NAME_PHONE, o.getPhone());
            values.put(DbContract.FeedOrder.COLUMN_NAME_EMAIL, o.getEmail());
            values.put(DbContract.FeedOrder.COLUMN_NAME_UCID, o.getUcid());
            values.put(DbContract.FeedOrder.COLUMN_NAME_O_CREATED_AT, o.getO_created_at());
            int newRowId = db.update(DbContract.FeedOrder.TABLE_NAME, values, DbContract.FeedOrder.COLUMN_NAME_OID + "=?", new String[]{o.getOid() + ""});

            return newRowId;
        }

        public List<Order> query() {

            String[] projection = {
                    DbContract.FeedOrder.COLUMN_NAME_OID,
                    DbContract.FeedOrder.COLUMN_NAME_O_AMOUNT,
                    DbContract.FeedOrder.COLUMN_NAME_O_STATUS,
                    DbContract.FeedOrder.COLUMN_NAME_UID,
                    DbContract.FeedOrder.COLUMN_NAME_NAME,
                    DbContract.FeedOrder.COLUMN_NAME_ADDRESS,
                    DbContract.FeedOrder.COLUMN_NAME_PHONE,
                    DbContract.FeedOrder.COLUMN_NAME_EMAIL,
                    DbContract.FeedOrder.COLUMN_NAME_UCID,
                    DbContract.FeedOrder.COLUMN_NAME_O_CREATED_AT,

            };
            Cursor c = db.query(
                    DbContract.FeedOrder.TABLE_NAME,  // The table to query
                    projection,                               // The columns to return
                    null,                                // The columns for the WHERE clause
                    null,                            // The values for the WHERE clause
                    null,                                     // don't group the rows
                    null,                                     // don't filter by row groups
                    null,                                 // The sort order
                    String.valueOf(10)                                 // The limit
            );
            List<Order> list = new ArrayList<Order>();
            c.moveToFirst();
            while (!c.isAfterLast()) {
                Order o = new Order();
                o.setOid(c.getInt(c.getColumnIndex(DbContract.FeedOrder.COLUMN_NAME_OID)));
                o.setO_amount(c.getDouble(c.getColumnIndex(DbContract.FeedOrder.COLUMN_NAME_O_AMOUNT)));
                o.setO_status(c.getString(c.getColumnIndex(DbContract.FeedOrder.COLUMN_NAME_O_STATUS)));
                o.setUid(c.getInt(c.getColumnIndex(DbContract.FeedOrder.COLUMN_NAME_UID)));
                o.setName(c.getString(c.getColumnIndex(DbContract.FeedOrder.COLUMN_NAME_NAME)));
                o.setAddress(c.getString(c.getColumnIndex(DbContract.FeedOrder.COLUMN_NAME_ADDRESS)));
                o.setPhone(c.getString(c.getColumnIndex(DbContract.FeedOrder.COLUMN_NAME_PHONE)));
                o.setEmail(c.getString(c.getColumnIndex(DbContract.FeedOrder.COLUMN_NAME_EMAIL)));
                o.setUcid(c.getInt(c.getColumnIndex(DbContract.FeedOrder.COLUMN_NAME_UCID)));
                o.setO_created_at(c.getString(c.getColumnIndex(DbContract.FeedOrder.COLUMN_NAME_O_CREATED_AT)));

                list.add(o);
                c.moveToNext();
            }
            Log.i(TAG, "products: " + list);
            return list;
        }

        public List<Order> query(int oid) {

            String[] projection = {
                    DbContract.FeedOrder.COLUMN_NAME_OID,
                    DbContract.FeedOrder.COLUMN_NAME_O_AMOUNT,
                    DbContract.FeedOrder.COLUMN_NAME_O_STATUS,
                    DbContract.FeedOrder.COLUMN_NAME_UID,
                    DbContract.FeedOrder.COLUMN_NAME_NAME,
                    DbContract.FeedOrder.COLUMN_NAME_ADDRESS,
                    DbContract.FeedOrder.COLUMN_NAME_PHONE,
                    DbContract.FeedOrder.COLUMN_NAME_EMAIL,
                    DbContract.FeedOrder.COLUMN_NAME_UCID,
                    DbContract.FeedOrder.COLUMN_NAME_O_CREATED_AT,


            };
            Cursor c = db.query(
                    DbContract.FeedOrder.TABLE_NAME,  // The table to query
                    projection,                               // The columns to return
                    DbContract.FeedOrder.COLUMN_NAME_OID + "=?",                                // The columns for the WHERE clause
                    new String[]{oid + ""},                            // The values for the WHERE clause
                    null,                                     // don't group the rows
                    null,                                     // don't filter by row groups
                    null,                                 // The sort order
                    String.valueOf(10)                                 // The limit
            );
            List<Order> list = new ArrayList<Order>();
            c.moveToFirst();
            while (!c.isAfterLast()) {
                Order o = new Order();
                o.setOid(c.getInt(c.getColumnIndex(DbContract.FeedOrder.COLUMN_NAME_OID)));
                o.setO_amount(c.getDouble(c.getColumnIndex(DbContract.FeedOrder.COLUMN_NAME_O_AMOUNT)));
                o.setO_status(c.getString(c.getColumnIndex(DbContract.FeedOrder.COLUMN_NAME_O_STATUS)));
                o.setUid(c.getInt(c.getColumnIndex(DbContract.FeedOrder.COLUMN_NAME_UID)));
                o.setName(c.getString(c.getColumnIndex(DbContract.FeedOrder.COLUMN_NAME_NAME)));
                o.setAddress(c.getString(c.getColumnIndex(DbContract.FeedOrder.COLUMN_NAME_ADDRESS)));
                o.setPhone(c.getString(c.getColumnIndex(DbContract.FeedOrder.COLUMN_NAME_PHONE)));
                o.setEmail(c.getString(c.getColumnIndex(DbContract.FeedOrder.COLUMN_NAME_EMAIL)));
                o.setUcid(c.getInt(c.getColumnIndex(DbContract.FeedOrder.COLUMN_NAME_UCID)));
                o.setO_created_at(c.getString(c.getColumnIndex(DbContract.FeedOrder.COLUMN_NAME_O_CREATED_AT)));

                list.add(o);
                c.moveToNext();
            }
            Log.i(TAG, "products: " + list);
            return list;
        }
    }

    public static class DbOrderDetail {
        public DbOrderDetail(Context mContext) {
            getDbTask(mContext);
        }
        
        public boolean delete(int sdid) {
            if (sdid == -1) {
                return false;
            }
            if (db.delete(DbContract.FeedOrderDetail.TABLE_NAME, DbContract.FeedOrderDetail.COLUMN_NAME_ODID + "=?", new String[]{sdid + ""}) > 0) {
                return true;
            }
            return false;
        }

        public Boolean insert(List<OrderDetail> objs) {
            if (objs == null) {
                return false;
            }

            for (OrderDetail o : objs) {
                ContentValues values = new ContentValues();
                values.put(DbContract.FeedOrderDetail.COLUMN_NAME_ODID, o.getOdid());
                values.put(DbContract.FeedOrderDetail.COLUMN_NAME_OID, o.getOid());
                values.put(DbContract.FeedOrderDetail.COLUMN_NAME_PID, o.getPid());
                values.put(DbContract.FeedOrderDetail.COLUMN_NAME_P_NAME, o.getP_name());
                values.put(DbContract.FeedOrderDetail.COLUMN_NAME_P_PRICE, o.getP_price());
                values.put(DbContract.FeedOrderDetail.COLUMN_NAME_P_CODE, o.getP_code());
                values.put(DbContract.FeedOrderDetail.COLUMN_NAME_P_DESCRIPTION, o.getP_description());
                values.put(DbContract.FeedOrderDetail.COLUMN_NAME_OD_QUANTITY, o.getOd_quantity());
                values.put(DbContract.FeedOrderDetail.COLUMN_NAME_OD_SUBAMOUNT, o.getOd_subamount());
                values.put(DbContract.FeedOrderDetail.COLUMN_NAME_OD_CREATED_AT, o.getOd_created_at());
                // Insert the new row, returning the primary key value of the new row
                long newRowId;
                newRowId = db.insert(DbContract.FeedOrderDetail.TABLE_NAME, null, values);
            }
            return true;
        }

        public int insert(OrderDetail o) {
            if (o == null) {
                return -1;
            }

            ContentValues values = new ContentValues();
            values.put(DbContract.FeedOrderDetail.COLUMN_NAME_ODID, o.getOdid());
            values.put(DbContract.FeedOrderDetail.COLUMN_NAME_OID, o.getOid());
            values.put(DbContract.FeedOrderDetail.COLUMN_NAME_PID, o.getPid());
            values.put(DbContract.FeedOrderDetail.COLUMN_NAME_P_NAME, o.getP_name());
            values.put(DbContract.FeedOrderDetail.COLUMN_NAME_P_PRICE, o.getP_price());
            values.put(DbContract.FeedOrderDetail.COLUMN_NAME_P_CODE, o.getP_code());
            values.put(DbContract.FeedOrderDetail.COLUMN_NAME_P_DESCRIPTION, o.getP_description());
            values.put(DbContract.FeedOrderDetail.COLUMN_NAME_OD_QUANTITY, o.getOd_quantity());
            values.put(DbContract.FeedOrderDetail.COLUMN_NAME_OD_SUBAMOUNT, o.getOd_subamount());
            values.put(DbContract.FeedOrderDetail.COLUMN_NAME_OD_CREATED_AT, o.getOd_created_at());
            // Insert the new row, returning the primary key value of the new row
            int newRowId = (int) db.insert(DbContract.FeedOrderDetail.TABLE_NAME, null, values);
            return newRowId;
        }


        public Boolean update(List<OrderDetail> objs) {
            if (objs == null) {
                return false;
            }

            int count = 0;
            for (OrderDetail o : objs) {
                ContentValues values = new ContentValues();
                values.put(DbContract.FeedOrderDetail.COLUMN_NAME_ODID, o.getOdid());
                values.put(DbContract.FeedOrderDetail.COLUMN_NAME_OID, o.getOid());
                values.put(DbContract.FeedOrderDetail.COLUMN_NAME_PID, o.getPid());
                values.put(DbContract.FeedOrderDetail.COLUMN_NAME_P_NAME, o.getP_name());
                values.put(DbContract.FeedOrderDetail.COLUMN_NAME_P_PRICE, o.getP_price());
                values.put(DbContract.FeedOrderDetail.COLUMN_NAME_P_CODE, o.getP_code());
                values.put(DbContract.FeedOrderDetail.COLUMN_NAME_P_DESCRIPTION, o.getP_description());
                values.put(DbContract.FeedOrderDetail.COLUMN_NAME_OD_QUANTITY, o.getOd_quantity());
                values.put(DbContract.FeedOrderDetail.COLUMN_NAME_OD_SUBAMOUNT, o.getOd_subamount());
                values.put(DbContract.FeedOrderDetail.COLUMN_NAME_OD_CREATED_AT, o.getOd_created_at());
                long newRowId;
                newRowId = db.update(DbContract.FeedOrderDetail.TABLE_NAME, values, DbContract.FeedOrderDetail.COLUMN_NAME_ODID + "=?", new String[]{o.getOdid() + ""});
                count += newRowId;
            }
            if (count == objs.size()) {
                return true;
            }
            return false;
        }

        public int update(OrderDetail o) {
            if (o == null) {
                return -1;
            }

            int count = 0;

            ContentValues values = new ContentValues();
            values.put(DbContract.FeedOrderDetail.COLUMN_NAME_ODID, o.getOdid());
            values.put(DbContract.FeedOrderDetail.COLUMN_NAME_OID, o.getOid());
            values.put(DbContract.FeedOrderDetail.COLUMN_NAME_PID, o.getPid());
            values.put(DbContract.FeedOrderDetail.COLUMN_NAME_P_NAME, o.getP_name());
            values.put(DbContract.FeedOrderDetail.COLUMN_NAME_P_PRICE, o.getP_price());
            values.put(DbContract.FeedOrderDetail.COLUMN_NAME_P_CODE, o.getP_code());
            values.put(DbContract.FeedOrderDetail.COLUMN_NAME_P_DESCRIPTION, o.getP_description());
            values.put(DbContract.FeedOrderDetail.COLUMN_NAME_OD_QUANTITY, o.getOd_quantity());
            values.put(DbContract.FeedOrderDetail.COLUMN_NAME_OD_SUBAMOUNT, o.getOd_subamount());
            values.put(DbContract.FeedOrderDetail.COLUMN_NAME_OD_CREATED_AT, o.getOd_created_at());
            int newRowId = db.update(DbContract.FeedOrderDetail.TABLE_NAME, values, DbContract.FeedOrderDetail.COLUMN_NAME_ODID + "=?", new String[]{o.getOdid() + ""});

            return newRowId;
        }

        public List<OrderDetail> query() {

            String[] projection = {
                    DbContract.FeedOrderDetail.COLUMN_NAME_ODID,
                    DbContract.FeedOrderDetail.COLUMN_NAME_OID,
                    DbContract.FeedOrderDetail.COLUMN_NAME_PID,
                    DbContract.FeedOrderDetail.COLUMN_NAME_P_NAME,
                    DbContract.FeedOrderDetail.COLUMN_NAME_P_CODE,
                    DbContract.FeedOrderDetail.COLUMN_NAME_P_PRICE,
                    DbContract.FeedOrderDetail.COLUMN_NAME_P_DESCRIPTION,
                    DbContract.FeedOrderDetail.COLUMN_NAME_OD_SUBAMOUNT,
                    DbContract.FeedOrderDetail.COLUMN_NAME_OD_QUANTITY,
                    DbContract.FeedOrderDetail.COLUMN_NAME_OD_CREATED_AT,

            };
            Cursor c = db.query(
                    DbContract.FeedOrderDetail.TABLE_NAME,  // The table to query
                    projection,                               // The columns to return
                    null,                                // The columns for the WHERE clause
                    null,                            // The values for the WHERE clause
                    null,                                     // don't group the rows
                    null,                                     // don't filter by row groups
                    null,                                 // The sort order
                    String.valueOf(10)                                 // The limit
            );
            List<OrderDetail> list = new ArrayList<OrderDetail>();
            c.moveToFirst();
            while (!c.isAfterLast()) {
                OrderDetail o = new OrderDetail();
                o.setOdid(c.getInt(c.getColumnIndex(DbContract.FeedOrderDetail.COLUMN_NAME_ODID)));
                o.setOid(c.getInt(c.getColumnIndex(DbContract.FeedOrderDetail.COLUMN_NAME_OID)));
                o.setPid(c.getInt(c.getColumnIndex(DbContract.FeedOrderDetail.COLUMN_NAME_PID)));
                o.setP_name(c.getString(c.getColumnIndex(DbContract.FeedOrderDetail.COLUMN_NAME_P_NAME)));
                o.setP_code(c.getString(c.getColumnIndex(DbContract.FeedOrderDetail.COLUMN_NAME_P_CODE)));
                o.setP_description(c.getString(c.getColumnIndex(DbContract.FeedOrderDetail.COLUMN_NAME_P_DESCRIPTION)));
                o.setP_price(c.getDouble(c.getColumnIndex(DbContract.FeedOrderDetail.COLUMN_NAME_P_PRICE)));
                o.setOd_quantity(c.getInt(c.getColumnIndex(DbContract.FeedOrderDetail.COLUMN_NAME_OD_QUANTITY)));
                o.setOd_subamount(c.getDouble(c.getColumnIndex(DbContract.FeedOrderDetail.COLUMN_NAME_OD_SUBAMOUNT)));
                o.setOd_created_at(c.getString(c.getColumnIndex(DbContract.FeedOrderDetail.COLUMN_NAME_OD_CREATED_AT)));

                list.add(o);
                c.moveToNext();
            }
            Log.i(TAG, "products: " + list);
            return list;
        }

        public List<OrderDetail> query(int sdid) {

            String[] projection = {
                    DbContract.FeedOrderDetail.COLUMN_NAME_ODID,
                    DbContract.FeedOrderDetail.COLUMN_NAME_OID,
                    DbContract.FeedOrderDetail.COLUMN_NAME_PID,
                    DbContract.FeedOrderDetail.COLUMN_NAME_P_NAME,
                    DbContract.FeedOrderDetail.COLUMN_NAME_P_CODE,
                    DbContract.FeedOrderDetail.COLUMN_NAME_P_PRICE,
                    DbContract.FeedOrderDetail.COLUMN_NAME_P_DESCRIPTION,
                    DbContract.FeedOrderDetail.COLUMN_NAME_OD_SUBAMOUNT,
                    DbContract.FeedOrderDetail.COLUMN_NAME_OD_QUANTITY,
                    DbContract.FeedOrderDetail.COLUMN_NAME_OD_CREATED_AT,

            };
            Cursor c = db.query(
                    DbContract.FeedOrderDetail.TABLE_NAME,  // The table to query
                    projection,                               // The columns to return
                    DbContract.FeedOrderDetail.COLUMN_NAME_ODID + "=?",                                // The columns for the WHERE clause
                    new String[]{sdid + ""},                            // The values for the WHERE clause
                    null,                                     // don't group the rows
                    null,                                     // don't filter by row groups
                    null,                                 // The sort order
                    String.valueOf(10)                                 // The limit
            );
            List<OrderDetail> list = new ArrayList<OrderDetail>();
            c.moveToFirst();
            while (!c.isAfterLast()) {
                OrderDetail o = new OrderDetail();
                o.setOdid(c.getInt(c.getColumnIndex(DbContract.FeedOrderDetail.COLUMN_NAME_ODID)));
                o.setOid(c.getInt(c.getColumnIndex(DbContract.FeedOrderDetail.COLUMN_NAME_OID)));
                o.setPid(c.getInt(c.getColumnIndex(DbContract.FeedOrderDetail.COLUMN_NAME_PID)));
                o.setP_name(c.getString(c.getColumnIndex(DbContract.FeedOrderDetail.COLUMN_NAME_P_NAME)));
                o.setP_code(c.getString(c.getColumnIndex(DbContract.FeedOrderDetail.COLUMN_NAME_P_CODE)));
                o.setP_description(c.getString(c.getColumnIndex(DbContract.FeedOrderDetail.COLUMN_NAME_P_DESCRIPTION)));
                o.setP_price(c.getDouble(c.getColumnIndex(DbContract.FeedOrderDetail.COLUMN_NAME_P_PRICE)));
                o.setOd_quantity(c.getInt(c.getColumnIndex(DbContract.FeedOrderDetail.COLUMN_NAME_OD_QUANTITY)));
                o.setOd_subamount(c.getDouble(c.getColumnIndex(DbContract.FeedOrderDetail.COLUMN_NAME_OD_SUBAMOUNT)));
                o.setOd_created_at(c.getString(c.getColumnIndex(DbContract.FeedOrderDetail.COLUMN_NAME_OD_CREATED_AT)));

                list.add(o);
                c.moveToNext();
            }
            Log.i(TAG, "products: " + list);
            return list;
        }
    }

}