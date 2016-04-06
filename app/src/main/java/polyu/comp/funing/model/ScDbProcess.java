package polyu.comp.funing.model;

import android.content.Context;
import android.util.Log;

import java.util.List;

import polyu.comp.funing.DbTask.DbTask;
import polyu.comp.funing.constant.CommonConstant;

/**
 * Created by liushanchen on 16/4/3.
 */
public class ScDbProcess {
    private static String TAG = ScDbProcess.class.getSimpleName();

    private static ScDbProcess instance = null;
    private static Context context = null;
    private static DbTask.DbShoppingCart dbShoppingCart;
    private static DbTask.DbShoppingCartDetail dbShoppingCartDetail;

    private ScDbProcess(Context context) {
        this.context = context;
        this.dbShoppingCart = new DbTask.DbShoppingCart(context);
        this.dbShoppingCartDetail = new DbTask.DbShoppingCartDetail(context);

    }

    public static ScDbProcess NewScDbProcess(Context context) {
        if (instance == null && context != null) {
            instance = new ScDbProcess(context);
            return instance;
        } else if (instance == null && context == null) {
            return null;
        } else {
            return instance;
        }
    }


    public boolean scDbStore(List<ShoppingCart> shoppingCarts) {
        boolean db = false;
        if (CommonConstant.userId == -1) {
            Log.e(TAG, "CommonConstant.userId==-1");
            return db;
        }
        int count = 0;
        for (ShoppingCart s : shoppingCarts) {
            if (dbShoppingCart.querySid(s.getSid()).size() > 0) {
                count += dbShoppingCart.update(s);
            } else {
                
                count += dbShoppingCart.insert(s);
            }
        }
        if (count==shoppingCarts.size()) {
            for (ShoppingCart s : shoppingCarts) {
                List<ShoppingCartDetail> details = s.getShoppingcartdetails();
                for (ShoppingCartDetail d : details) {
                    updateScDetail(d);

                }
            }
        }
        return db;
    }

    public int scDbGetSid() {
        ShoppingCart sc = scDbGetLocalSc();
        if (sc != null) {
            return sc.getSid();
        } else {
            return -1;
        }
    }


    public ShoppingCart scDbGetLocalSc() {
        if (CommonConstant.userId == -1) {
            Log.e(TAG, "CommonConstant.userId==-1");
            return null;
        }
        List<ShoppingCart> shoppingCarts = dbShoppingCart.query(CommonConstant.userId);
        Log.i(TAG, "QUERY:shoppingCarts:" + shoppingCarts);
        if (shoppingCarts.size() > 0) {
            return shoppingCarts.get(0);
        } else {
            return null;
        }

    }

    public List<ShoppingCartDetail> scDbGetLocalScD() {
        return dbShoppingCartDetail.query(scDbGetSid());
    }

    public void updateScDetail(ShoppingCartDetail d) {
        if (d == null) {
            return;
        }
        if (d.getSdid() > 0 && dbShoppingCartDetail.query(d.getSdid()).size() != 0) {
            dbShoppingCartDetail.update(d);
            Log.i(TAG, "detail-update:");

        } else {
            dbShoppingCartDetail.insert(d);
            Log.i(TAG, "detail-insert:");

        }
    }
    public boolean deleteDetail(int sdid) {
       return dbShoppingCartDetail.delete(sdid);
    }
}
