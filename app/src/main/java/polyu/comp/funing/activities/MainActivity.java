package polyu.comp.funing.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v4.os.EnvironmentCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import polyu.comp.funing.R;
import polyu.comp.funing.constant.CommonConstant;
import polyu.comp.funing.fragment.CouponsF;
import polyu.comp.funing.fragment.LoginF;
import polyu.comp.funing.fragment.OrderHistoryF;
import polyu.comp.funing.fragment.ProductListF;
import polyu.comp.funing.fragment.ShoppingCartF;
import polyu.comp.funing.fragment.UserInfoF;
import polyu.comp.funing.model.ShoppingCart;
import polyu.comp.funing.utils.CommonUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static String TAG = MainActivity.class.getSimpleName();
    private ImageView recordBtn;
    private ImageView basketBtn;
    private ImageView orderBtn;
    private ImageView accountBtn;
    private ImageView couponBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CommonConstant.applicationCache= Environment.getDownloadCacheDirectory();
        initView();
    }
    
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null) {
            String f = intent.getStringExtra(CommonConstant.mainActivityF_key);
            if(f==null)return;
            Log.i(TAG,"f="+f);
            switch (f) {
                case CommonConstant.F_login:
                    accountBtn.callOnClick();
                    break;
                case CommonConstant.F_product:
                    recordBtn.callOnClick();
                    break;
                case CommonConstant.F_sc:
                    basketBtn.callOnClick();
                    break;
                case CommonConstant.F_coupon:
                    couponBtn.callOnClick();
                    break;
                case CommonConstant.F_order:
                    orderBtn.callOnClick();
                    break;
                default:
                    recordBtn.callOnClick();
                    break;
            }
        }
    }

    private void initView() {
        ProductListF productListF = new ProductListF();
        //要用getSupportFragmentManager，而不是getFragmentManager
        getFragmentManager().beginTransaction().add(R.id.main_f, productListF).commit();

        recordBtn = (ImageView) findViewById(R.id.product_list);
        basketBtn = (ImageView) findViewById(R.id.basket);
        orderBtn = (ImageView) findViewById(R.id.order);
        accountBtn = (ImageView) findViewById(R.id.account);
        couponBtn = (ImageView) findViewById(R.id.coupon);
        recordBtn.setOnClickListener(this);
        basketBtn.setOnClickListener(this);
        orderBtn.setOnClickListener(this);
        accountBtn.setOnClickListener(this);
        couponBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        recordBtn.setImageResource(R.mipmap.products_grey);
        basketBtn.setImageResource(R.mipmap.basket_grey);
        orderBtn.setImageResource(R.mipmap.orderhistory_grey);
        accountBtn.setImageResource(R.mipmap.my_account_grey);
        couponBtn.setImageResource(R.mipmap.coupons_grey);
        ImageView img = (ImageView) v;
        switch (v.getId()) {
            case R.id.product_list:
                img.setImageResource(R.mipmap.products_black);
                ProductListF productListF = new ProductListF();
                getFragmentManager().beginTransaction().replace(R.id.main_f, productListF).commit();
                break;
            case R.id.basket:
                img.setImageResource(R.mipmap.basket_black);
                ShoppingCartF shoppingCartf=new ShoppingCartF();
                getFragmentManager().beginTransaction().replace(R.id.main_f, shoppingCartf).commit();
                break;
            case R.id.order:
                img.setImageResource(R.mipmap.orderhistory_black);
                OrderHistoryF orderHistoryF=new OrderHistoryF();
                getFragmentManager().beginTransaction().replace(R.id.main_f, orderHistoryF).commit();
                break;
            case R.id.account:
                img.setImageResource(R.mipmap.my_account_black);
                if (CommonConstant.apiKey == null) {
                    LoginF loginF = new LoginF();
                    getFragmentManager().beginTransaction().replace(R.id.main_f, loginF).commit();
                }else{
                    UserInfoF userInfoF=new UserInfoF();
                    getFragmentManager().beginTransaction().replace(R.id.main_f, userInfoF).commit();
                }
                break;
            case R.id.coupon:
                img.setImageResource(R.mipmap.coupons_black);
                CouponsF couponsF = new CouponsF();
                getFragmentManager().beginTransaction().replace(R.id.main_f, couponsF).commit();
                break;

        }
    }
}
