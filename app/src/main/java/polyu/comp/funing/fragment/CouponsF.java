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
import android.widget.TextView;

import com.quentindommerc.superlistview.OnMoreListener;
import com.quentindommerc.superlistview.SuperListview;

import java.util.HashMap;
import java.util.Map;

import polyu.comp.funing.R;
import polyu.comp.funing.adapter.CouponListAdapter;
import polyu.comp.funing.constant.CommonConstant;
import polyu.comp.funing.service.ApiService;
import polyu.comp.funing.service.UserCouponR;
import polyu.comp.funing.utils.CommonUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by liushanchen on 16/3/26.
 */
public class CouponsF extends Fragment implements SwipeRefreshLayout.OnRefreshListener, OnMoreListener, AdapterView.OnItemClickListener, View.OnClickListener {
    private static String TAG = CouponsF.class.getSimpleName();

    private SuperListview superListview;
    private CouponListAdapter couponListAdapter;
    private TextView validCP;
    private TextView notValidCP;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.coupon_list_f, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        getActivity().setTitle(getResources().getString(R.string.coupon_f));
        /*ListView*/
        superListview = (SuperListview) getActivity().findViewById(R.id.coupon_list);
        couponListAdapter = new CouponListAdapter(getActivity().getApplication());
        superListview.setAdapter(couponListAdapter);
        getCoupons(CommonConstant.valid);
        superListview.setRefreshListener(this);
        superListview.setupMoreListener(this, 0);
        superListview.hideMoreProgress();
        superListview.setOnItemClickListener(this);
        validCP = (TextView) getActivity().findViewById(R.id.valid_cp);
        notValidCP = (TextView) getActivity().findViewById(R.id.not_valid_cp);
        validCP.setOnClickListener(this);
        notValidCP.setOnClickListener(this);

    }

    private void getCoupons(final String if_valid) {
        Map<String, String> options = new HashMap<String, String>();
        options.put("uc_status", if_valid);
        if(CommonUtils.ifLogin(getActivity())){
            Call<UserCouponR> call = ApiService.Creator.create().getCouponList(options, CommonConstant.apiKey);
            Callback<UserCouponR> callback = new Callback<UserCouponR>() {
                @Override
                public void onResponse(Call<UserCouponR> call, Response<UserCouponR> response) {
                    if (response.body() == null || response.errorBody() != null) {
                        CommonUtils.show(getActivity().getApplicationContext(), getString(R.string.fail));
                        return;
                    }
                    Log.i(TAG, "response: " + response.body().toString());
                    if(response.body().getCouponList()!=null){
                        if (if_valid.equals(CommonConstant.used)) {
                            couponListAdapter.updateMyList(response.body().getCouponList());
                            superListview.setAdapter(couponListAdapter);
                            superListview.hideProgress();
                            superListview.hideMoreProgress();
                        } else {
                            couponListAdapter.updateMyList(response.body().getCouponList());
                            superListview.setAdapter(couponListAdapter);
                            superListview.hideProgress();
                            superListview.hideMoreProgress();
                        }
                    }
                }

                @Override
                public void onFailure(Call<UserCouponR> call, Throwable t) {
                    superListview.hideProgress();
                    superListview.hideMoreProgress();
                }
            };
            call.enqueue(callback);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onMoreAsked(int numberOfItems, int numberBeforeMore, int currentItemPos) {

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onClick(View v) {
        validCP.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
        notValidCP.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
        TextView textView = (TextView) v;
        textView.setBackgroundColor(getActivity().getResources().getColor(R.color.colorPrimary));
        switch (v.getId()) {
            case R.id.valid_cp:
                couponListAdapter.clearMyList();
                getCoupons(CommonConstant.valid);
                break;
            case R.id.not_valid_cp:
                couponListAdapter.clearMyList();
                getCoupons(CommonConstant.invalid);
                getCoupons(CommonConstant.used);
                break;
        }
    }
}
