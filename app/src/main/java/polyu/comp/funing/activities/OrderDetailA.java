package polyu.comp.funing.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import polyu.comp.funing.R;
import polyu.comp.funing.adapter.OrderDetailAdapter3;
import polyu.comp.funing.constant.CommonConstant;
import polyu.comp.funing.model.Coupon;
import polyu.comp.funing.model.Order;
import polyu.comp.funing.utils.CommonUtils;


/**
 * Created by liushanchen on 16/4/4.
 */
public class OrderDetailA extends AppCompatActivity {
    private static String TAG = OrderDetailA.class.getSimpleName();


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
    private OrderDetailAdapter3 orderDetailAdapter;
    private LinearLayout cLayout;
    private static double totalPrice;
    private static double actualPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_order);
        order = (Order) getIntent().getSerializableExtra(CommonConstant.order_detail_key);
        if (order != null) {
            initOrder();
            initView();
            setViewValue();
        } else {
            Log.e(TAG, "order is null!!");
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        setViewValue();
    }


    private void initOrder() {
        if (order.getUsercoupondetails().size() > 0) {
            coupon = order.getUsercoupondetails().get(0);
        }
        totalPrice = CommonUtils.calTotalPrice2(order.getOrderdetails());

    }

    private void initView() {
        setTitle(getResources().getString(R.string.order_activity));

        listView = (ListView) findViewById(R.id.order_product_list);
        listView.requestFocus();
        selectCoupon = (Button) findViewById(R.id.select_coupon);
        selectCoupon.setVisibility(View.GONE);
        confirmOrder = (Button) findViewById(R.id.confirm_order);
        confirmOrder.setVisibility(View.GONE);


        cLayout = (LinearLayout) findViewById(R.id.coupon_dis_layout);
        couponItem = findViewById(R.id.coupon_dis);
        ImageView cpImg = (ImageView) couponItem.findViewById(R.id.p_item_img);
        cpImg.setVisibility(View.GONE);
        cpName = (TextView) couponItem.findViewById(R.id.cp_name);
        cpDescription = (TextView) couponItem.findViewById(R.id.cp_expired_date);

        shippingName = (EditText) findViewById(R.id.name);
        shippingPhone = (EditText) findViewById(R.id.phone);
        shippingEmail = (EditText) findViewById(R.id.email);
        shippingAddress = (EditText) findViewById(R.id.address);
        shippingName.setFocusable(false);
        shippingPhone.setFocusable(false);
        shippingEmail.setFocusable(false);
        shippingAddress.setFocusable(false);


        totalAmount = (TextView) findViewById(R.id.total_amount);
        actualAmount = (TextView) findViewById(R.id.actual_amount);

        orderDetailAdapter = new OrderDetailAdapter3(this);
        orderDetailAdapter.setMyList(order.getOrderdetails());
        listView.setAdapter(orderDetailAdapter);
        CommonUtils.setListViewHeight(listView, 0);


    }

    private void setViewValue() {
        shippingName.setText(order.getName());
        shippingPhone.setText(order.getPhone());
        shippingEmail.setText(order.getEmail());
        shippingAddress.setText(order.getAddress());
        totalAmount.setText("$ "+totalPrice + "");
        if (coupon != null) {
            couponItem.setVisibility(View.VISIBLE);
            cpName.setText(coupon.getC_name());
            cpDescription.setText(coupon.getC_description());
            actualPrice = CommonUtils.calActualPrice(coupon, totalPrice);
        } else {
            cLayout.setVisibility(View.GONE);
//            couponItem.setVisibility(View.GONE);
            actualPrice = totalPrice;
        }
        actualAmount.setText("$ "+actualPrice + "");
    }


}
