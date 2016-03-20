package polyu.comp.funing.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.quentindommerc.superlistview.OnMoreListener;
import com.quentindommerc.superlistview.SuperListview;

import java.util.List;

import polyu.comp.funing.DbTask.DbTask;
import polyu.comp.funing.R;
import polyu.comp.funing.activities.ProductDetailA;
import polyu.comp.funing.adapter.ProductListAdapter;
import polyu.comp.funing.domain.Product;
import polyu.comp.funing.service.ApiService;
import polyu.comp.funing.service.ProductListR;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by liushanchen on 16/3/18.
 */
public class ProductListF extends Fragment implements SwipeRefreshLayout.OnRefreshListener, OnMoreListener, AdapterView.OnItemClickListener {
    private static String TAG = ProductListF.class.getSimpleName();
    private SuperListview productList;
    private ProductListAdapter productListAdapter;
    LinearLayout headView;
    private static int myPage = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.product_list_f, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initListView();
    }

    private void getProductList(int page) {
        productList.showMoreProgress();
        Call<ProductListR> productListRCall = ApiService.Creator.create().getProductList();
        Callback<ProductListR> productListRCallback = new Callback<ProductListR>() {
            @Override
            public void onResponse(Call<ProductListR> call, Response<ProductListR> response) {
                Log.i(TAG, "response: " + response.body().toString());
                productListAdapter.setMyList(response.body().getProducts());
                productList.setAdapter(productListAdapter);
                productList.hideMoreProgress();
//                DbTask.DbProduct dbProduct= new DbTask.DbProduct(getActivity());
//                System.out.println(TAG+"DbProductQuery: "+dbProduct.DbProductQuery());
            }

            @Override
            public void onFailure(Call<ProductListR> call, Throwable t) {
                Log.e(TAG,t.toString());
                DbTask.DbProduct dbProduct= new DbTask.DbProduct(getActivity());
                List<Product> products=dbProduct.DbProductQuery();
                Log.e(TAG,"DbProductQuery: "+products.get(0).getP_name());
            }
        };
        productListRCall.enqueue(productListRCallback);
    }

    private void initListView() {
//        getActivity().setTitle("Produ List");
        /*ListView*/
//        headView = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.inspection_item, null);
        productList = (SuperListview) getActivity().findViewById(R.id.product_list);
//        recordList.getList().addHeaderView(headView);
        productListAdapter = new ProductListAdapter(getActivity().getApplication());
        productList.setAdapter(productListAdapter);
        getProductList(myPage);
        productList.setRefreshListener(this);
        productList.setupMoreListener(this, 0);
        productList.hideMoreProgress();
//        productList.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Product record = (Product) parent.getItemAtPosition(position);
        Intent intent = new Intent();
        intent.setClass(getActivity().getApplication(), ProductDetailA.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("record", record);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onMoreAsked(int numberOfItems, int numberBeforeMore, int currentItemPos) {
        Log.i(TAG, "load more");
        myPage++;
        getProductList(myPage);

    }

    @Override
    public void onRefresh() {
        myPage = 1;
        getProductList(myPage);
    }
}
