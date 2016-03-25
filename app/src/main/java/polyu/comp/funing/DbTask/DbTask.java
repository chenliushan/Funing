package polyu.comp.funing.DbTask;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import polyu.comp.funing.domain.Coupon;
import polyu.comp.funing.domain.Product;

/**
 * Created by liushanchen on 16/3/19.
 */
public class DbTask {
    private static String TAG = DbTask.class.getSimpleName();

    public static class DbCoupon extends AsyncTask<Coupon, Integer, Boolean> {
        private Context mContext;


        public DbCoupon(Context context) {
            this.mContext = context;
        }

        @Override
        protected Boolean doInBackground(Coupon... coupons) {
            if (coupons == null) {
                return false;
            }
            MyDbHelper mDbHelper = new MyDbHelper(mContext);
            // Gets the data repository in write mode
            SQLiteDatabase db = mDbHelper.getWritableDatabase();
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
        private Context mContext;

        public DbProduct(Context context) {
            this.mContext = context;
        }

        public Boolean DbProductInsert(List<Product>... objs) {
            if (objs == null) {
                return false;
            }
            MyDbHelper mDbHelper = new MyDbHelper(mContext);
            // Gets the data repository in write mode
            SQLiteDatabase db = mDbHelper.getWritableDatabase();
            // Create a new map of values, where column names are the keys

            for (Product o : objs[0]) {
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

        public Boolean DbProductUpdate(List<Product>... objs) {
            if (objs == null) {
                return false;
            }
            MyDbHelper mDbHelper = new MyDbHelper(mContext);
            // Gets the data repository in write mode
            SQLiteDatabase db = mDbHelper.getWritableDatabase();
            // Create a new map of values, where column names are the keys
            int count = 0;
            for (Product o : objs[0]) {
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
                newRowId = db.update(DbContract.FeedProduct.TABLE_NAME, values, DbContract.FeedProduct.COLUMN_NAME_PID + "=?", new String[]{o.getPid() + ""});
                count += newRowId;
            }
            if(count==objs[0].size()){
                return true;
            }
            return false;
        }

        public List<Product> DbProductQuery() {
            MyDbHelper mDbHelper = new MyDbHelper(mContext);
            // Gets the data repository in write mode
            SQLiteDatabase db = mDbHelper.getReadableDatabase();
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
            Log.i(TAG, "products: " + products);
            return products;
        }
    }

}