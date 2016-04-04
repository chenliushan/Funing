package polyu.comp.funing.activities;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import polyu.comp.funing.R;
import polyu.comp.funing.constant.CommonConstant;
import polyu.comp.funing.model.User;
import polyu.comp.funing.service.ApiService;
import polyu.comp.funing.service.LoginR;
import polyu.comp.funing.utils.CommonUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by liushanchen on 16/3/26.
 */
public class RegisterA extends AppCompatActivity implements View.OnClickListener {
    private static String TAG = RegisterA.class.getSimpleName();

    private EditText phone;
    private EditText name;
    private EditText psw;
    private EditText rePsw;
    private EditText address;
    private EditText email;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.register);
        initView();
    }

    private void initView() {
        phone = (EditText) findViewById(R.id.r_phone);
        name = (EditText) findViewById(R.id.r_name);
        psw = (EditText) findViewById(R.id.r_password);
        rePsw = (EditText) findViewById(R.id.r_r_password);
        address = (EditText) findViewById(R.id.r_address);
        email = (EditText) findViewById(R.id.r_email);

        Button submitBtn = (Button) findViewById(R.id.r_submit);
        submitBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.r_submit:
                if (!psw.getText().toString().equals(rePsw.getText().toString())) {
                    psw.requestFocus();
                    CommonUtils.show(this, "Password do not match.");
                } else if (name.getText().toString().isEmpty() || address.getText().toString().isEmpty()) {
                    CommonUtils.show(this, "Please fill the required information");
                } else {
                    Pattern p = Pattern.compile("[0-9]*");
                    Matcher m = p.matcher(phone.getText().toString());
                    if (m.matches()) {
                        registerApi();
                    } else {
                        phone.requestFocus();
                        CommonUtils.show(this, "Please fill in correct phone number");
                    }

                }

                break;

            default:
                break;

        }
    }

    private void registerApi() {
        CommonUtils.show(getApplicationContext(), R.string.processing);
        Map<String, String> options = new HashMap<String, String>();
        options.put("phone", phone.getText().toString());
        options.put("password", psw.getText().toString());
        options.put("name", name.getText().toString());
        options.put("email", email.getText().toString());
        options.put("address", address.getText().toString());
        Call<LoginR> call = ApiService.Creator.create().uRegister(options);
        call.enqueue(new Callback<LoginR>() {

            @Override
            public void onResponse(Call<LoginR> call, Response<LoginR> response) {
                if(response.body().getError()!=CommonConstant.noError){
                    CommonUtils.show(getApplicationContext(),response.body().getMessage());
                    return;
                }
                Log.i(TAG, response.body().toString());
                User u = new User();
                u.setEmail(email.getText().toString());
                u.setPassword(psw.getText().toString());
                u.setName(name.getText().toString());
                u.setAddress(address.getText().toString());
                u.setPhone(phone.getText().toString());
                CommonUtils.setUser(getApplicationContext(), u);
                CommonUtils.show(getApplicationContext(), getString(R.string.success));
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Call<LoginR> call, Throwable t) {

            }
        });
    }
}
