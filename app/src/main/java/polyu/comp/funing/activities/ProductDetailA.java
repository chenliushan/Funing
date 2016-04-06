package polyu.comp.funing.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import polyu.comp.funing.R;
import polyu.comp.funing.constant.CommonConstant;
import polyu.comp.funing.model.Product;
import polyu.comp.funing.model.ScDbProcess;
import polyu.comp.funing.model.ShoppingCartDetail;
import polyu.comp.funing.service.ApiService;
import polyu.comp.funing.service.ScDetailR;
import polyu.comp.funing.utils.CommonUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by liushanchen on 16/3/19.
 */
public class ProductDetailA extends AppCompatActivity implements View.OnClickListener {
    private static String TAG = MainActivity.class.getSimpleName();
    private ImageView img;
    private ImageView addToCart;
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
        if (product != null) {
            initView();
        }

    }

    private void initView() {
        setTitle(getResources().getString(R.string.product_a));

        img = (ImageView) findViewById(R.id.pd_img);
        addToCart = (ImageView) findViewById(R.id.add_to_cart);
        name = (TextView) findViewById(R.id.pd_name);
        price = (TextView) findViewById(R.id.pd_price);
        description = (TextView) findViewById(R.id.pd_description);
        quantity = (EditText) findViewById(R.id.quantity);
        if(product.getP_image_url()!=null){
            Picasso.with(this).load(product.getP_image_url()).into(img);
        }else{
            img.setVisibility(View.GONE);
        }
        name.setText(product.getP_name());
        price.setText("$ " + product.getP_price());
        description.setText(product.getP_description());
        addToCart.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_to_cart:
                int q = Integer.valueOf(quantity.getText().toString());
                createScDetail(q);
                break;
        }
    }

    /**
     * add to shopping cart
     * The user has to login before doing this.
     *
     * @param quantity
     */
    private void createScDetail(final int quantity) {
        if(CommonUtils.ifLogin(this)){
            Map<String, String> options = new HashMap<String, String>(product.toMap());
            final double amount = product.getP_price() * quantity;
            final int sid = ScDbProcess.NewScDbProcess(getApplicationContext()).scDbGetSid();
            options.put("sid", sid + "");
            options.put("sd_quantity", quantity + "");
            options.put("sd_subamount", amount + "");

            Call<ScDetailR> call = ApiService.Creator.create().createScDetail(options, CommonConstant.apiKey);
            call.enqueue(new Callback<ScDetailR>() {
                @Override
                public void onResponse(Call<ScDetailR> call, Response<ScDetailR> response) {
                    if (response.body() == null || response.errorBody() != null) {
                        CommonUtils.show(getApplicationContext(), getString(R.string.fail));
                        return;
                    } else {
                        CommonUtils.show(getApplicationContext(), getString(R.string.success));

                        ShoppingCartDetail scd = new ShoppingCartDetail(product);
                        scd.setSdid(response.body().getSdid());
                        scd.setSid(sid);
                        scd.setSd_subamount(amount);
                        scd.setSd_quantity(quantity);
//                        ScDbProcess.NewScDbProcess(getApplicationContext()).updateScDetail(scd);
                    }
                }

                @Override
                public void onFailure(Call<ScDetailR> call, Throwable t) {

                }
            });
        }else{
            this.finish();
        }
    }
}
