package polyu.comp.funing.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import polyu.comp.funing.R;
import polyu.comp.funing.fragment.LoginF;
import polyu.comp.funing.fragment.ProductListF;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    /*
    This is the welcome activity, only display the logo
     */
    private static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        
    }

    private void initView(){
        ProductListF productListF=new ProductListF();
        //要用getSupportFragmentManager，而不是getFragmentManager
        getFragmentManager().beginTransaction().add(R.id.main_f, productListF).commit();

        ImageView recordBtn = (ImageView) findViewById(R.id.product_list);
        ImageView basketBtn = (ImageView) findViewById(R.id.basket);
        ImageView orderBtn = (ImageView) findViewById(R.id.order);
        ImageView accountBtn = (ImageView) findViewById(R.id.account);
        ImageView couponBtn = (ImageView) findViewById(R.id.coupon);
        recordBtn.setOnClickListener(this);
        basketBtn.setOnClickListener(this);
        orderBtn.setOnClickListener(this);
        accountBtn.setOnClickListener(this);
        couponBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ImageView img=(ImageView)v;
        switch (v.getId()){
            case R.id.product_list:
                img.setImageResource(R.mipmap.products_black);
                ProductListF productListF=new ProductListF();
                getFragmentManager().beginTransaction().replace(R.id.main_f, productListF).commit();
                break;
            case R.id.basket:
                img.setImageResource(R.mipmap.basket_black);
//                LoginF loginF=new LoginF();
//                getFragmentManager().beginTransaction().replace(R.id.main_f, loginF).commit();
                break;
            case R.id.order:
                img.setImageResource(R.mipmap.orderhistory_black);
//                LoginF loginF=new LoginF();
//                getFragmentManager().beginTransaction().replace(R.id.main_f, loginF).commit();
                break;
            case R.id.account:
                img.setImageResource(R.mipmap.my_account_black);
                LoginF loginF=new LoginF();
                getFragmentManager().beginTransaction().replace(R.id.main_f, loginF).commit();
                break;
            case R.id.coupon:
                img.setImageResource(R.mipmap.coupons_black);
//                LoginF loginF=new LoginF();
//                getFragmentManager().beginTransaction().replace(R.id.main_f, loginF).commit();
                break;
            
        }
    }
}
