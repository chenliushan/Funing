package polyu.comp.funing.DbTask;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by liushanchen on 16/3/19.
 */
public class MyDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    private static String TAG = MyDbHelper.class.getSimpleName();
    public static final int DATABASE_VERSION = 5;
    public static final String DATABASE_NAME = "funing";

    public MyDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DbContract.FeedDb.SQL_CREATE_TABLE_COUPONS);
        db.execSQL(DbContract.FeedDb.SQL_CREATE_TABLE_PRODUCT);
        db.execSQL(DbContract.FeedDb.SQL_CREATE_TABLE_SHOPPING_CART);
        db.execSQL(DbContract.FeedDb.SQL_CREATE_TABLE_SHOPPING_CART_DETAIL);
        db.execSQL(DbContract.FeedDb.SQL_CREATE_TABLE_ORDER);
        db.execSQL(DbContract.FeedDb.SQL_CREATE_TABLE_ORDER_DETAIL);
        Log.i(TAG,"DB onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(DbContract.FeedDb.SQL_DELETE_TABLE_COUPONS);
        db.execSQL(DbContract.FeedDb.SQL_DELETE_TABLE_PRODUCT);
        db.execSQL(DbContract.FeedDb.SQL_DELETE_TABLE_SHOPPING_CART);
        db.execSQL(DbContract.FeedDb.SQL_DELETE_TABLE_SHOPPING_CART_DETAIL);
        db.execSQL(DbContract.FeedDb.SQL_DELETE_TABLE_ORDER);
        db.execSQL(DbContract.FeedDb.SQL_DELETE_TABLE_ORDER_DETAIL);
        onCreate(db);
    } 
    
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
    
}
