package polyu.comp.funing.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import polyu.comp.funing.R;
import polyu.comp.funing.activities.MainActivity;
import polyu.comp.funing.constant.CommonConstant;
import polyu.comp.funing.model.Coupon;
import polyu.comp.funing.model.ShoppingCartDetail;
import polyu.comp.funing.model.User;

/**
 * Created by liushanchen on 16/3/25.
 */
public class CommonUtils {

    public static void show(Context context, String info) {
        Toast.makeText(context, info, Toast.LENGTH_LONG).show();
    }

    public static void show(Context context, int info) {
        Toast.makeText(context, context.getString(info), Toast.LENGTH_LONG).show();
    }

    public Date date(String dateTime) {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            if (dateTime != null) {
                date = sdf.parse(dateTime);

                return date;
            }
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * set the height of the listView
     *
     * @param listView
     * @param headerFooter
     */
    public static void setListViewHeight(ListView listView, int headerFooter) {

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        if (listAdapter.getCount() >= headerFooter) {
            for (int i = 0; i < listAdapter.getCount(); i++) {
                View listItem = listAdapter.getView(i, null, listView);
                listItem.measure(0, 0);
                totalHeight += listItem.getMeasuredHeight();
            }
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
            listView.setLayoutParams(params);
        } else {
            listView.setVisibility(View.GONE);
        }

    }

    /**
     * public static String user = "user";
     * public static String uid = "uid";
     * public static String name = "name";
     * public static String email = "email";
     * public static String address = "address";
     * public static String phone = "phone";
     * public static String password = "password";
     * public static String api_key = "api_key";
     * public static String status = "status";
     * public static String created_at = "created_at";
     *
     * @param context
     * @param u
     * @return
     */
    public static boolean setUser(Context context, User u) {
        SharedPreferences.Editor editor = context.getSharedPreferences(CommonConstant.UserConstant.user, 0).edit();
        editor.putInt(CommonConstant.UserConstant.uid, u.getUid());
        editor.putString(CommonConstant.UserConstant.name, u.getName());
        editor.putString(CommonConstant.UserConstant.email, u.getEmail());
        editor.putString(CommonConstant.UserConstant.address, u.getAddress());
        editor.putString(CommonConstant.UserConstant.phone, u.getPhone());
        editor.putString(CommonConstant.UserConstant.password, u.getPassword());
        editor.putString(CommonConstant.UserConstant.api_key, u.getApi_key());
        editor.putString(CommonConstant.UserConstant.status, u.getStatus());
        editor.putString(CommonConstant.UserConstant.created_at, u.getCreated_at());
        return editor.commit();
    }

    public static User getUser(Context context) {
        SharedPreferences sp = context.getSharedPreferences(CommonConstant.UserConstant.user, 0);
        User u = new User();
        u.setUid(sp.getInt(CommonConstant.UserConstant.uid, 0));
        u.setName(sp.getString(CommonConstant.UserConstant.name, null));
        u.setEmail(sp.getString(CommonConstant.UserConstant.email, null));
        u.setAddress(sp.getString(CommonConstant.UserConstant.address, null));
        u.setPhone(sp.getString(CommonConstant.UserConstant.phone, null));
        u.setPassword(sp.getString(CommonConstant.UserConstant.password, null));
        u.setApi_key(sp.getString(CommonConstant.UserConstant.api_key, null));
        u.setStatus(sp.getString(CommonConstant.UserConstant.status, null));
        u.setCreated_at(sp.getString(CommonConstant.UserConstant.created_at, null));
        return u;
    }

    /**
     * @param context must be an activity context
     */
    public static boolean ifLogin(Context context) {
        if (CommonConstant.apiKey == null) {
            CommonUtils.show(context, context.getResources().getString(R.string.login_first));
            Intent intent = new Intent();
            intent.setClass(context, MainActivity.class);
            intent.putExtra(CommonConstant.mainActivityF_key, CommonConstant.F_login);
            context.startActivity(intent);
            return false;
        }
        return true;
    }

    public static double calTotalPrice(List<ShoppingCartDetail> ds) {
        double amount = 0;
        for (ShoppingCartDetail d : ds) {
            amount += d.getP_price() * d.getSd_quantity();
        }
        return amount;
    }

    public static double calActualPrice(Coupon c, double total) {
        double actual = 0;

        if (c != null && c.getC_discount_detail() != -1) {
            double d = c.getC_discount_detail();
            if (c.getC_discount_type().equals(CommonConstant.couponTypeCash)) {
                actual = total - d;
            } else {
                actual = total * (1 - d);
            }
        }
        return actual;
    }
    public static void printMap(Map<String,String> map) {
        for( Map.Entry<String,String> m:map.entrySet()) {
            Log.i("", m.getKey() + ":" + m.getValue());
        }
    }
}
