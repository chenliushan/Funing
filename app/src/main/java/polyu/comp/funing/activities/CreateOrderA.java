package polyu.comp.funing.activities;

import android.content.DialogInterface;
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
import polyu.comp.funing.model.ShoppingCart;
import polyu.comp.funing.model.User;
import polyu.comp.funing.service.ApiService;
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

    private ShoppingCart shoppingCart;
    private Order order;
    private Coupon coupon;
    private List<Coupon> couponList;
    private String[] couponNames = null;

    private EditText shippingName;
    private EditText shippingPhone;
    private EditText shippingEmail;
    private EditText shippingAddress;
    private ListView listView;
    private Button selectCoupon;
    private TextView cpName;
    private TextView cpDescription;
    private TextView totalAmount;
    private TextView actualAmount;
    private View couponItem;
    private OrderDetailAdapter orderDetailAdapter;

    private static double totalPrice;

    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_order);
        shoppingCart = (ShoppingCart) getIntent().getSerializableExtra(CommonConstant.shopping_cart_key);
        if (shoppingCart != null) {
            initOrder();
            initView();
            getCoupons();
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
        order.setOrderdetails(shoppingCart.getOrderDetails());
        totalPrice = CommonUtils.calTotalPrice(shoppingCart.getShoppingcartdetails());


    }

    private void initView() {
        listView = (ListView) findViewById(R.id.order_product_list);

        selectCoupon = (Button) findViewById(R.id.select_coupon);
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
            actualAmount.setText(CommonUtils.calActualPrice(coupon, totalPrice) + "");
        } else {
            couponItem.setVisibility(View.GONE);
            actualAmount.setText(totalPrice + "");
        }
    }


    @Override
    public void onClick(View v) {
        if (couponNames != null) {
//            
            showSingleChoiceDialog();

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
                coupon = null;
                setViewValue();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void getCoupons() {
        Map<String, String> options = new HashMap<String, String>();
        options.put("uc_status", CommonConstant.valid);

        Call<UserCouponR> call = ApiService.Creator.create().getCouponList(options, CommonConstant.apiKey);
        Callback<UserCouponR> callback = new Callback<UserCouponR>() {
            @Override
            public void onResponse(Call<UserCouponR> call, Response<UserCouponR> response) {
                if (response.body() == null || response.errorBody() != null) {
                    CommonUtils.show(getApplicationContext(), getString(R.string.fail));
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

            }

            @Override
            public void onFailure(Call<UserCouponR> call, Throwable t) {
                CommonUtils.show(getApplicationContext(), getString(R.string.fail));
            }
        };
        call.enqueue(callback);

    }

}
