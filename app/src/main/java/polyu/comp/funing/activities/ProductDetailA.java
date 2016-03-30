package polyu.comp.funing.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import polyu.comp.funing.R;
import polyu.comp.funing.constant.CommonConstant;
import polyu.comp.funing.model.Product;


/**
 * Created by liushanchen on 16/3/19.
 */
public class ProductDetailA extends AppCompatActivity implements View.OnClickListener {
    private static String TAG = MainActivity.class.getSimpleName();
    private ImageView img;
    private TextView name;
    private TextView price;
    private TextView description;
    private EditText quantity;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);
        product = (Product) getIntent().getSerializableExtra(CommonConstant.product_key);
        if(product!=null){
            initView();
        }
        
    }

    private void initView() {
        img = (ImageView) findViewById(R.id.pd_img);
        name = (TextView) findViewById(R.id.pd_name);
        price = (TextView) findViewById(R.id.pd_price);
        description = (TextView) findViewById(R.id.pd_description);
        quantity = (EditText) findViewById(R.id.quantity);
        img.setOnClickListener(this);
        Picasso.with(this).load(product.getP_image_url()).into(img);
        name.setText(product.getP_name());
        price.setText("$ "+product.getP_price());
        description.setText(product.getP_description());
        
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.product_list:
                quantity.getText().toString();
                break;
        }
    }
    
}
