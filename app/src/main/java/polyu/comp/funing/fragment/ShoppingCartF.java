package polyu.comp.funing.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.quentindommerc.superlistview.OnMoreListener;
import com.quentindommerc.superlistview.SuperListview;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import polyu.comp.funing.DbTask.DbTask;
import polyu.comp.funing.R;
import polyu.comp.funing.adapter.ProductListAdapter;
import polyu.comp.funing.adapter.ScDetailAdapter;
import polyu.comp.funing.constant.CommonConstant;
import polyu.comp.funing.model.Product;
import polyu.comp.funing.model.ShoppingCart;
import polyu.comp.funing.model.ShoppingCartDetail;
import polyu.comp.funing.service.ApiService;
import polyu.comp.funing.service.ShoppingCartR;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by liushanchen on 16/3/31.
 */
public class ShoppingCartF extends Fragment implements OnMoreListener, AdapterView.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {
    private SuperListview listview;
    private ScDetailAdapter scDetailAdapter;
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

    private void initView(){
        getActivity().setTitle(getResources().getString(R.string.product_f));
        listview = (SuperListview) getActivity().findViewById(R.id.product_list);
//        recordList.getList().addHeaderView(headView);
        scDetailAdapter = new ScDetailAdapter(getActivity().getApplication());
        listview.setAdapter(scDetailAdapter);
        getUserShoppingCart();
        listview.setRefreshListener(this);
        listview.setupMoreListener(this, 0);
        listview.hideMoreProgress();
        listview.setOnItemClickListener(this);
    }

    @Override
    public void onMoreAsked(int numberOfItems, int numberBeforeMore, int currentItemPos) {
        
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        
    }

    @Override
    public void onRefresh() {
        
    }
   
    private void getUserShoppingCart() {
        Map<String, String> options = new HashMap<String, String>();
        options.put("s_status",CommonConstant. valid);

        Call<ShoppingCartR> call = ApiService.Creator.create().getShoppingCart(options, CommonConstant.apiKey);
        call.enqueue(new Callback<ShoppingCartR>() {
            @Override
            public void onResponse(Call<ShoppingCartR> call, Response<ShoppingCartR> response) {
                List<ShoppingCart> shoppingCarts=response.body().getShoppingcarts();
                List<ShoppingCartDetail> shoppingCartDetails=shoppingCarts.get(0).getShoppingcartdetails();
                if(shoppingCarts==null||shoppingCarts.size()==0){
                    createUserShoppingCart();
                }else{
                    scDetailAdapter.setMyList(shoppingCartDetails);
                    listview.setAdapter(scDetailAdapter);
                    listview.hideProgress();
                    listview.hideMoreProgress();
                    
                    boolean db;
                    DbTask.DbShoppingCart dbShoppingCart= new DbTask.DbShoppingCart(getActivity());
                    DbTask.DbShoppingCartDetail dbShoppingCartDetail= new DbTask.DbShoppingCartDetail(getActivity());
                    if(dbShoppingCart.query()!=null){
                        db=dbShoppingCart.update(shoppingCarts);
                        if(db){
                            dbShoppingCartDetail.update(shoppingCartDetails);
                        }
                        Log.i(TAG,"dbShoppingCart: "+db);
                    }else{
                        db=dbShoppingCart.insert(shoppingCarts);
                        if(db){
                            dbShoppingCartDetail.insert(shoppingCartDetails);
                        }
                        Log.i(TAG,"dbShoppingCart: "+db);
                    }
                }
            }

            @Override
            public void onFailure(Call<ShoppingCartR> call, Throwable t) {
                Log.e(TAG,"failure:"+t.toString());
                listview.hideProgress();
                listview.hideMoreProgress();
                DbTask.DbShoppingCart dbShoppingCart= new DbTask.DbShoppingCart(getActivity());
                DbTask.DbShoppingCartDetail dbShoppingCartDetail= new DbTask.DbShoppingCartDetail(getActivity());
                if(CommonConstant.userId!=-1){
                    int sid=dbShoppingCart.query(CommonConstant.userId).get(0).getSid();
                    List<ShoppingCartDetail> shoppingCartDetails=dbShoppingCartDetail.query(sid);
                    scDetailAdapter.setMyList(shoppingCartDetails);
                    listview.setAdapter(scDetailAdapter);
                }
               
            }
        });
    }
    private void createUserShoppingCart() {
        Map<String, String> options = new HashMap<String, String>();
        Call<ShoppingCartR> call = ApiService.Creator.create().createShoppingCart(options,CommonConstant.apiKey);
        call.enqueue(new Callback<ShoppingCartR>() {
            @Override
            public void onResponse(Call<ShoppingCartR> call, Response<ShoppingCartR> response) {

            }

            @Override
            public void onFailure(Call<ShoppingCartR> call, Throwable t) {

            }
        });
    }
}