package polyu.comp.funing.fragment;

import android.app.Fragment;
import android.content.Intent;
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

import polyu.comp.funing.R;
import polyu.comp.funing.activities.OrderDetailA;
import polyu.comp.funing.adapter.OrderHistoryListAdapter;
import polyu.comp.funing.constant.CommonConstant;
import polyu.comp.funing.model.Order;
import polyu.comp.funing.service.ApiService;
import polyu.comp.funing.service.OrderR;
import polyu.comp.funing.utils.CommonUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by liushanchen on 16/4/6.
 */
public class OrderHistoryF extends Fragment implements SwipeRefreshLayout.OnRefreshListener, OnMoreListener, AdapterView.OnItemClickListener {
    private static String TAG = ProductListF.class.getSimpleName();
    private SuperListview listview;
    private OrderHistoryListAdapter orderHistoryListAdapter;
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.order_history_f, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        getActivity().setTitle(getResources().getString(R.string.order_history_f));
        listview = (SuperListview) getActivity().findViewById(R.id.order_list);
        orderHistoryListAdapter = new OrderHistoryListAdapter(getActivity());
        listview.setAdapter(orderHistoryListAdapter);
        getOrderList();

        listview.setRefreshListener(this);
//        listview.setupMoreListener(this, 0);
        listview.hideMoreProgress();
        listview.setOnItemClickListener(this);
    }

    @Override
    public void onRefresh() {
        getOrderList();

    }

    @Override
    public void onMoreAsked(int numberOfItems, int numberBeforeMore, int currentItemPos) {
        
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Order order=(Order)parent.getItemAtPosition(position); 
        Intent intent = new Intent();
        intent.setClass(getActivity().getApplication(), OrderDetailA.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(CommonConstant.order_detail_key, order);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    private void getOrderList() {
        listview.showMoreProgress();
        if(CommonUtils.ifLogin(getActivity())) {
            Call<OrderR> listviewRCall = ApiService.Creator.create().getOder(CommonConstant.apiKey);
            Callback<OrderR> listviewRCallback = new Callback<OrderR>() {
                @Override
                public void onResponse(Call<OrderR> call, Response<OrderR> response) {
                    if (response.body() == null || response.errorBody() != null) {
                        CommonUtils.show(getActivity().getApplicationContext(), getString(R.string.fail));
                        return;
                    }
                    Log.i(TAG, "response: " + response.body().toString());
                    orderHistoryListAdapter.setMyList(response.body().getOrders());
                    listview.setAdapter(orderHistoryListAdapter);
                    listview.hideProgress();
                    listview.hideMoreProgress();
                    boolean db;
//                DbTask.DbProduct dbProduct= new DbTask.DbProduct(getActivity());
//                if(dbProduct.DbProductQuery()!=null){
//                    db=dbProduct.DbProductUpdate(response.body().());
//                    Log.i(TAG,"DbProductUpdate: "+db);
//                }else{
//                    db=dbProduct.DbProductInsert(response.body().getProducts());
//                    Log.i(TAG,"DbProductInsert: "+db);
//                }

                }

                @Override
                public void onFailure(Call<OrderR> call, Throwable t) {
                    Log.e(TAG, "failure" + t.toString());
                    listview.hideProgress();
                    listview.hideMoreProgress();
//                DbTask.DbProduct dbProduct= new DbTask.DbProduct(getActivity());
//                List<Product> products=dbProduct.DbProductQuery();
//                orderHistoryListAdapter.setMyList(products);
//                listview.setAdapter(orderHistoryListAdapter);
//                Log.e(TAG,"DbProductQuery: "+products.get(0).getP_name());
                }
            };
            listviewRCall.enqueue(listviewRCallback);
        }
    }

}
