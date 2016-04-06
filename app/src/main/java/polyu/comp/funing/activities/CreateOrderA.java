package polyu.comp.funing.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import polyu.comp.funing.R;
import polyu.comp.funing.adapter.OrderDetailAdapter;
import polyu.comp.funing.constant.CommonConstant;
import polyu.comp.funing.model.Coupon;
import polyu.comp.funing.model.Order;
import polyu.comp.funing.model.OrderDetail;
import polyu.comp.funing.model.ShoppingCart;
import polyu.comp.funing.model.User;
import polyu.comp.funing.service.ApiService;
import polyu.comp.funing.service.OrderR;
import polyu.comp.funing.service.UserCouponR;
import polyu.comp.funing.utils.CommonUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by liushanchen on 16/4/4.
 */
public class CreateOrderA extends AppCompatActivity implements View.OnClickListener {
    private static String TAG = CreateOrderA.class.getSimpleName();

    private static ShoppingCart shoppingCart;
    private static Order order;
    private static Coupon coupon;
    private static List<Coupon> couponList;
    private static String[] couponNames = null;

    private EditText shippingName;
    private EditText shippingPhone;
    private EditText shippingEmail;
    private EditText shippingAddress;
    private ListView listView;
    private Button selectCoupon;
    private Button confirmOrder;
    private TextView cpName;
    private TextView cpDescription;
    private TextView totalAmount;
    private TextView actualAmount;
    private View couponItem;
    private OrderDetailAdapter orderDetailAdapter;

    private static double totalPrice;
    private static double actualPrice;

    private AlertDialog.Builder builder;
    private static ProgressDialog progress;
    private GetCouponsTask getCouponsTask;
    SubmitOrderTask submitOrderTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_order);
        shoppingCart = (ShoppingCart) getIntent().getSerializableExtra(CommonConstant.shopping_cart_key);
        if (shoppingCart != null) {
            initOrder();
            initView();
            getCouponsTask = new GetCouponsTask();
            getCouponsTask.execute("");
            setViewValue();
        } else {
            Log.e(TAG, "shoppingCart is null!!");
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        setViewValue();
    }


    private void initOrder() {
        User u = CommonUtils.getUser(this);
        if (u != null) {
            order = new Order(u);
        } else {
            order = new Order();
        }
        if (order.getUid() == 0) {
            order.setUid(CommonConstant.userId);
        }
        order.setOrderdetails(shoppingCart.getOrderDetails());
        totalPrice = CommonUtils.calTotalPrice(shoppingCart.getShoppingcartdetails());


    }

    private void initView() {
        setTitle(getResources().getString(R.string.order_activity));

        listView = (ListView) findViewById(R.id.order_product_list);

        selectCoupon = (Button) findViewById(R.id.select_coupon);
        confirmOrder = (Button) findViewById(R.id.confirm_order);

        couponItem = findViewById(R.id.coupon_dis);
        ImageView cpImg = (ImageView) couponItem.findViewById(R.id.p_item_img);
        cpImg.setVisibility(View.GONE);
        cpName = (TextView) couponItem.findViewById(R.id.cp_name);
        cpDescription = (TextView) couponItem.findViewById(R.id.cp_expired_date);

        shippingName = (EditText) findViewById(R.id.name);
        shippingPhone = (EditText) findViewById(R.id.phone);
        shippingEmail = (EditText) findViewById(R.id.email);
        shippingAddress = (EditText) findViewById(R.id.address);

        totalAmount = (TextView) findViewById(R.id.total_amount);
        actualAmount = (TextView) findViewById(R.id.actual_amount);

        orderDetailAdapter = new OrderDetailAdapter(this);
        orderDetailAdapter.setMyList(shoppingCart.getShoppingcartdetails());
        listView.setAdapter(orderDetailAdapter);
        CommonUtils.setListViewHeight(listView, 0);

        selectCoupon.setOnClickListener(this);
        confirmOrder.setOnClickListener(this);
    }

    private void setViewValue() {
        shippingName.setText(order.getName());
        shippingPhone.setText(order.getPhone());
        shippingEmail.setText(order.getEmail());
        shippingAddress.setText(order.getAddress());
        totalAmount.setText(totalPrice + "");
        if (coupon != null) {
            couponItem.setVisibility(View.VISIBLE);
            cpName.setText(coupon.getC_name());
            cpDescription.setText(coupon.getC_description());
            actualPrice = CommonUtils.calActualPrice(coupon, totalPrice);
        } else {
            couponItem.setVisibility(View.GONE);
            actualPrice = totalPrice;
        }
        actualAmount.setText(actualPrice + "");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.select_coupon:
                if (couponNames != null) {
                    showSingleChoiceDialog();
                }
                break;
            case R.id.confirm_order:
                orderSubmission();
                confirmOrder.setClickable(false);
                break;
        }

    }

    private void showSingleChoiceDialog() {
        builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.funninng_logo);
        builder.setTitle(R.string.coupon_select);
        builder.setSingleChoiceItems(couponNames, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                coupon = couponList.get(i);
                order.setUcid(coupon.getUcid());
//                CommonUtils.show(getApplication(),couponNames[i]+""+coupon.getC_name());
            }
        });
        builder.setCancelable(true);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                setViewValue();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                order.setUcid(0);
                coupon = null;
                setViewValue();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    private void orderSubmission() {
        if (shippingName.getText().length() < 1 || shippingPhone.getText().length() < 1 || shippingEmail.getText().length() < 1 || shippingAddress.getText().length() < 1) {
            CommonUtils.show(this, getString(R.string.illegal_input));
            return;
        }
        order.setName(shippingName.getText().toString());
        order.setPhone(shippingPhone.getText().toString());
        order.setEmail(shippingEmail.getText().toString());
        order.setAddress(shippingAddress.getText().toString());
        order.setO_amount(actualPrice);
        if (coupon != null) {
            order.setUcid(coupon.getUcid());
        }

        progress = ProgressDialog.show(this, getString(R.string.order_confirm),
               getString(R.string.processing), true);

        submitOrderTask = new SubmitOrderTask();
        submitOrderTask.execute(this);
    }


    private static class GetCouponsTask extends AsyncTask<String, Integer, Integer> {
        protected Integer doInBackground(String... strings) {
            getCoupons();
            return 0;
        }

        private void getCoupons() {
            Map<String, String> options = new HashMap<String, String>();
            options.put("uc_status", CommonConstant.valid);
            Call<UserCouponR> call = ApiService.Creator.create().getCouponList(options, CommonConstant.apiKey);
            Callback<UserCouponR> callback = new Callback<UserCouponR>() {
                @Override
                public void onResponse(Call<UserCouponR> call, Response<UserCouponR> response) {
                    if (response.body() == null || response.errorBody() != null) {
                        return;
                    }
                    couponList = response.body().getCouponList();
                    if (couponList != null && couponList.size() > 0) {
                        List<String> cNames = new ArrayList<String>();
                        for (Coupon c : couponList) {
                            cNames.add(c.getC_name());
                        }
                        couponNames = (String[]) cNames.toArray(new String[cNames.size()]);
                    }
                    return;
                }

                @Override
                public void onFailure(Call<UserCouponR> call, Throwable t) {
                }
            };
            call.enqueue(callback);
        }

    }

    private static class SubmitOrderTask extends AsyncTask<Context, Integer, Boolean> {
        boolean result=false;

        @Override
        protected Boolean doInBackground(Context... params) {
            createOder(params[0]);
            return result;
        }

        protected void onPostExecute(Boolean result) {
            progress.dismiss();
            
        }

        private void createOder(final Context context) {
            Map<String, String> options = new HashMap<String, String>(order.toMap());
            if (options.containsValue(null)) {
            }
            CommonUtils.printMap(options);
            Call<OrderR> call = ApiService.Creator.create().createOder(options, CommonConstant.apiKey);
            Callback<OrderR> callback = new Callback<OrderR>() {
                @Override
                public void onResponse(Call<OrderR> call, Response<OrderR> response) {
                    if (response.body().getError() > 0) {
                        return;
                    }
                    int oid = response.body().getOid();
                    if (oid == -1) {
                        return;
                    }
                    for (OrderDetail od : order.getOrderdetails()) {
                        od.setOid(oid);
                        createOderDetails(od);
                    }
                    if (coupon != null) {
                        updateCouponInfo(oid);
                    }
                    invalidShoppingCart(context);
                }
                @Override
                public void onFailure(Call<OrderR> call, Throwable t) {
                }
            };
            call.enqueue(callback);
        }

        private void createOderDetails(OrderDetail od) {
            Map<String, String> options = new HashMap<String, String>(od.toMap());
            if (options.containsValue(null)) {
            }
            Call<OrderR> call = ApiService.Creator.create().createOrderDetail(options, CommonConstant.apiKey);
            call.enqueue(new Callback<OrderR>() {
                @Override
                public void onResponse(Call<OrderR> call, Response<OrderR> response) {
                    if (response.body() == null || response.errorBody() != null) {
                        return;
                    }
                }

                @Override
                public void onFailure(Call<OrderR> call, Throwable t) {
                }
            });
        }

        private void updateCouponInfo(int oid) {
            Map<String, String> options = new HashMap<String, String>();
            options.put(CommonConstant.couponUpdateUcstatus, CommonConstant.couponUpdateUsed);
            options.put("oid", oid + "");
            if (options.containsValue(null)) {
            }
            Call<OrderR> call = ApiService.Creator.create().updateCoupons(coupon.getCid(), options, CommonConstant.apiKey);
            call.enqueue(new Callback<OrderR>() {
                @Override
                public void onResponse(Call<OrderR> call, Response<OrderR> response) {
                    if (response.body() == null || response.errorBody() != null) {
                        return;
                    }
                }

                @Override
                public void onFailure(Call<OrderR> call, Throwable t) {
                }
            });

        }

        private void invalidShoppingCart(final Context context) {
            Map<String, String> options = new HashMap<String, String>();
            options.put(CommonConstant.scUpdateUcstatus, CommonConstant.scInvalid);
            options.put("sid", shoppingCart.getSid() + "");
            options.put("s_amount", order.getO_amount() + "");
            if (options.containsValue(null)) {
            }
            Call<OrderR> call = ApiService.Creator.create().invalidSC(options, CommonConstant.apiKey);
            call.enqueue(new Callback<OrderR>() {
                @Override
                public void onResponse(Call<OrderR> call, Response<OrderR> response) {
                    if (response.body() == null || response.errorBody() != null) {
                        return;
                    }
                    if(response.body().getError()==0){
                        result=true;
                        CommonUtils.show(context,context.getString(R.string.success));
                        Intent intent = new Intent();
                        intent.setClass(context, MainActivity.class);
                        intent.putExtra(CommonConstant.mainActivityF_key, CommonConstant.F_order);
                        context.startActivity(intent);
                    }
                    
                }

                @Override
                public void onFailure(Call<OrderR> call, Throwable t) {
                }
            });
        }
    }

}
