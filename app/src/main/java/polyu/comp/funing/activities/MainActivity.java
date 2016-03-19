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
        ImageView meBtn = (ImageView) findViewById(R.id.user_info);
        recordBtn.setOnClickListener(this);
        meBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.product_list:
                ProductListF productListF=new ProductListF();
                getFragmentManager().beginTransaction().replace(R.id.main_f, productListF).commit();
                break;
            case R.id.user_info:
                LoginF loginF=new LoginF();
                getFragmentManager().beginTransaction().add(R.id.main_f, loginF).commit();
                break;
            
        }
    }
}
