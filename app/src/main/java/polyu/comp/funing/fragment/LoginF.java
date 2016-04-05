package polyu.comp.funing.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import polyu.comp.funing.DbTask.DbTask;
import polyu.comp.funing.R;
import polyu.comp.funing.activities.RegisterA;
import polyu.comp.funing.constant.CommonConstant;
import polyu.comp.funing.model.ScDbProcess;
import polyu.comp.funing.model.ShoppingCart;
import polyu.comp.funing.model.ShoppingCartDetail;
import polyu.comp.funing.model.User;
import polyu.comp.funing.service.ApiService;
import polyu.comp.funing.service.LoginR;
import polyu.comp.funing.service.ShoppingCartR;
import polyu.comp.funing.utils.CommonUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by liushanchen on 16/3/18.
 */
public class LoginF extends Fragment implements View.OnClickListener {
    private static String TAG = LoginF.class.getSimpleName();
    EditText emailEdit;
    EditText pwEdit;
    String e = "0607chris@gmail.com";
    String p = "chris";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        getActivity().setTitle(getResources().getString(R.string.login_f));
        Button loginBtn = (Button) getActivity().findViewById(R.id.login_btn);
        Button registerBtn = (Button) getActivity().findViewById(R.id.register_btn);
        emailEdit = (EditText) getActivity().findViewById(R.id.login_email_edit);
        pwEdit = (EditText) getActivity().findViewById(R.id.login_password_edit);
        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
        User user = CommonUtils.getUser(getActivity());
//        if (user != null && user.getEmail() != null && user.getPassword() != null) {
//            userLogin(user.getEmail(), user.getPassword());
//        }
        if (user != null && user.getEmail() != null ) {
            emailEdit.setText(user.getEmail());
            pwEdit.setText(user.getPassword());
        }
    }

    private void userLogin(final String email, final String password) {
        CommonUtils.show(getActivity(), R.string.processing);
        Map<String, String> options = new HashMap<String, String>();
        options.put("email", email);
        options.put("password", password);

        Call<LoginR> call = ApiService.Creator.create().uLogin(options);
        call.enqueue(new Callback<LoginR>() {
            @Override
            public void onResponse(Call<LoginR> call, Response<LoginR> response) {
                if(response.body().getError()!=CommonConstant.noError){
                    CommonUtils.show(getActivity().getApplicationContext(),response.body().getMessage());
                    return;
                }
                if (response.body().getError() == 0) {
                    User u = new User();
                    u.setEmail(email);
                    u.setPassword(password);
                    u.setApi_key(response.body().getApiKey());
                    u.setCreated_at(response.body().getCreatedAt());
                    CommonUtils.setUser(getActivity(), u);
                    CommonUtils.show(getActivity(), getString(R.string.success));
                    CommonConstant.apiKey = response.body().getApiKey();
                    getUserShoppingCart();
                    //go to user_info fragment
//                    ProductListF productListF=new ProductListF();
//                    getFragmentManager().beginTransaction().replace(R.id.main_f, productListF).commit();
                } else {
                    CommonUtils.show(getActivity(), response.body().getMessage());
                }

            }

            @Override
            public void onFailure(Call<LoginR> call, Throwable t) {

            }
        });
    }

    private void getUserShoppingCart() {
        Map<String, String> options = new HashMap<String, String>();
        options.put("s_status", CommonConstant.valid);
        
        Call<ShoppingCartR> call = ApiService.Creator.create().getShoppingCart(options,CommonConstant.apiKey);
        call.enqueue(new Callback<ShoppingCartR>() {
            @Override
            public void onResponse(Call<ShoppingCartR> call, Response<ShoppingCartR> response) {
                if (response.body() == null || response.errorBody() != null) {
                    CommonUtils.show(getActivity().getApplicationContext(), getString(R.string.fail));
                    return;
                }
                List<ShoppingCart> shoppingCarts=response.body().getShoppingcarts();
                CommonConstant.userId = shoppingCarts.get(0).getUid();
                if(shoppingCarts==null||shoppingCarts.size()==0){
                    createUserShoppingCart();
                }else{
                    ScDbProcess.NewScDbProcess(getActivity().getApplicationContext()).scDbStore(shoppingCarts);
                    UserInfoF userInfoF=new UserInfoF();
                    getFragmentManager().beginTransaction().replace(R.id.main_f, userInfoF).commit();

                }
            }

            @Override
            public void onFailure(Call<ShoppingCartR> call, Throwable t) {

            }
        });
    }
    private void createUserShoppingCart() {
        Map<String, String> options = new HashMap<String, String>();
        Call<ShoppingCartR> call = ApiService.Creator.create().createShoppingCart(options,CommonConstant.apiKey);
        call.enqueue(new Callback<ShoppingCartR>() {
            @Override
            public void onResponse(Call<ShoppingCartR> call, Response<ShoppingCartR> response) {
                if (response.body() == null || response.errorBody() != null) {
                    CommonUtils.show(getActivity().getApplicationContext(), getString(R.string.fail));
                    return;
                }
                List<ShoppingCart> shoppingCarts=response.body().getShoppingcarts();
                ScDbProcess.NewScDbProcess(getActivity().getApplicationContext()).scDbStore(shoppingCarts);
                UserInfoF userInfoF=new UserInfoF();
                getFragmentManager().beginTransaction().replace(R.id.main_f, userInfoF).commit();
            }

            @Override
            public void onFailure(Call<ShoppingCartR> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn:
                userLogin(emailEdit.getText().toString(), pwEdit.getText().toString());
                break;
            case R.id.register_btn:
                Intent intent = new Intent();
                intent.setClass(getActivity(), RegisterA.class);
                startActivity(intent);
//                getActivity().finish();
                break;
        }
    }
}
