package polyu.comp.funing.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.quentindommerc.superlistview.SuperListview;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import polyu.comp.funing.R;
import polyu.comp.funing.activities.CreateOrderA;
import polyu.comp.funing.activities.ProductDetailA;
import polyu.comp.funing.adapter.ScDetailAdapter;
import polyu.comp.funing.constant.CommonConstant;
import polyu.comp.funing.model.Product;
import polyu.comp.funing.model.ScDbProcess;
import polyu.comp.funing.model.ShoppingCart;
import polyu.comp.funing.model.ShoppingCartDetail;
import polyu.comp.funing.service.ApiService;
import polyu.comp.funing.service.ShoppingCartR;
import polyu.comp.funing.utils.CommonUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by liushanchen on 16/3/31.
 */
public class ShoppingCartF extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {
    private static SuperListview listview;
    private TextView checkOut;
    private static TextView amount;
    private static ScDetailAdapter scDetailAdapter;
    private static ShoppingCart shoppingCart;
    private static String TAG = ShoppingCartF.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.shoppingcart_f, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    public static void amountView(List<ShoppingCartDetail> shoppingCartDetails) {
        amount.setText(CommonUtils.calTotalPrice(shoppingCartDetails) + "");
    }

    private void initView() {
        getActivity().setTitle(getResources().getString(R.string.product_f));
        listview = (SuperListview) getActivity().findViewById(R.id.shopping_cart_list);
        checkOut = (TextView) getActivity().findViewById(R.id.checkout_img);
        amount = (TextView) getActivity().findViewById(R.id.amount);
        checkOut.setOnClickListener(this);
        scDetailAdapter = new ScDetailAdapter(getActivity());
        listview.setAdapter(scDetailAdapter);
        getUserShoppingCart();
        listview.hideMoreProgress();
        listview.setOnItemClickListener(this);
    }

    /**
     * when user click the item, jump to the product detail activity and display the product information.
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ShoppingCartDetail record = (ShoppingCartDetail) parent.getItemAtPosition(position);
        Product product = record.getProduct();
        Intent intent = new Intent();
        intent.setClass(getActivity().getApplication(), ProductDetailA.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(CommonConstant.product_key, product);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    private void getUserShoppingCart() {
        if (CommonUtils.ifLogin(getActivity())) {
            Map<String, String> options = new HashMap<String, String>();
            options.put("s_status", CommonConstant.valid);

            Call<ShoppingCartR> call = ApiService.Creator.create().getShoppingCart(options, CommonConstant.apiKey);
            call.enqueue(new Callback<ShoppingCartR>() {
                @Override
                public void onResponse(Call<ShoppingCartR> call, Response<ShoppingCartR> response) {
                    listview.hideProgress();
                    listview.hideMoreProgress();
                    if (response.body() == null || response.errorBody() != null) {
                        CommonUtils.show(getActivity().getApplicationContext(), getString(R.string.fail));
                        return;
                    }
                    List<ShoppingCart> shoppingCarts = response.body().getShoppingcarts();
                    if (shoppingCarts == null || shoppingCarts.size() == 0) {
                        createUserShoppingCart();
                        CommonUtils.show(getActivity(), getResources().getString(R.string.empty_shopping_cart));
                    } else {
                        shoppingCart = shoppingCarts.get(0);
                        List<ShoppingCartDetail> shoppingCartDetails = shoppingCart.getShoppingcartdetails();
                        scDetailAdapter.setMyList(shoppingCartDetails);
                        listview.setAdapter(scDetailAdapter);
                        amount.setText(CommonUtils.calTotalPrice(shoppingCartDetails) + "");

                        /**
                         * update local database
                         */
                        ScDbProcess.NewScDbProcess(getActivity().getApplicationContext()).scDbStore(shoppingCarts);
                    }
                }

                @Override
                public void onFailure(Call<ShoppingCartR> call, Throwable t) {
                    Log.e(TAG, "failure:" + t.toString());
                    listview.hideProgress();
                    listview.hideMoreProgress();
                    /**
                     * read data from local database.
                     */
                    List<ShoppingCartDetail> shoppingCartDetails = ScDbProcess.NewScDbProcess(getActivity().getApplicationContext()).scDbGetLocalScD();
                    scDetailAdapter.setMyList(shoppingCartDetails);
                    listview.setAdapter(scDetailAdapter);
                }
            });
        }

    }

    public static void staicGetShoppingCart() {
        Map<String, String> options = new HashMap<String, String>();
        options.put("s_status", CommonConstant.valid);

        Call<ShoppingCartR> call = ApiService.Creator.create().getShoppingCart(options, CommonConstant.apiKey);
        call.enqueue(new Callback<ShoppingCartR>() {
            @Override
            public void onResponse(Call<ShoppingCartR> call, Response<ShoppingCartR> response) {
                listview.hideProgress();
                listview.hideMoreProgress();
                if (response.body() == null || response.errorBody() != null) {
                    return;
                }
                List<ShoppingCart> shoppingCarts = response.body().getShoppingcarts();
                shoppingCart = shoppingCarts.get(0);
                List<ShoppingCartDetail> shoppingCartDetails = shoppingCart.getShoppingcartdetails();
                scDetailAdapter.setMyList(shoppingCartDetails);
                listview.setAdapter(scDetailAdapter);
                amount.setText(CommonUtils.calTotalPrice(shoppingCartDetails) + "");
                
                /**
                 * update local database
                 */
            }

            @Override
            public void onFailure(Call<ShoppingCartR> call, Throwable t) {
                Log.e(TAG, "failure:" + t.toString());

            }
        });

    }

    private void createUserShoppingCart() {
        Map<String, String> options = new HashMap<String, String>();
        Call<ShoppingCartR> call = ApiService.Creator.create().createShoppingCart(options, CommonConstant.apiKey);
        call.enqueue(new Callback<ShoppingCartR>() {
            @Override
            public void onResponse(Call<ShoppingCartR> call, Response<ShoppingCartR> response) {
                listview.hideProgress();
                listview.hideMoreProgress();
                if (response.body() == null || response.errorBody() != null) {
                    CommonUtils.show(getActivity().getApplicationContext(), getString(R.string.fail));
                    return;
                }
                List<ShoppingCart> shoppingCarts = response.body().getShoppingcarts();
                shoppingCart = shoppingCarts.get(0);
                ScDbProcess.NewScDbProcess(getActivity().getApplicationContext()).scDbStore(shoppingCarts);

            }

            @Override
            public void onFailure(Call<ShoppingCartR> call, Throwable t) {

            }
        });
    }


    @Override
    public void onClick(View v) {
        if(shoppingCart!=null&&shoppingCart.getShoppingcartdetails().size()>0){
            Intent intent = new Intent();
            intent.setClass(getActivity().getApplication(), CreateOrderA.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(CommonConstant.shopping_cart_key, shoppingCart);
            intent.putExtras(bundle);
            startActivity(intent);
        }else{
            CommonUtils.show(getActivity(),getString(R.string.empty_shopping_cart));
        }
        
    }
}
