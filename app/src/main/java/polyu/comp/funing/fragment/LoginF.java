package polyu.comp.funing.fragment;

import android.app.Fragment;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import polyu.comp.funing.service.ApiService;
import polyu.comp.funing.service.LoginR;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by liushanchen on 16/3/18.
 */
public class LoginF extends Fragment {
    private static String TAG = LoginF.class.getSimpleName();


    private void userLogin() {
        Map<String, String> options = new HashMap<String, String>();
        options.put("email", "0607chris@gmail.com");
        options.put("password", "chris");

        Call<LoginR> call = ApiService.Creator.create().uLogin(options);
        call.enqueue(new Callback<LoginR>() {
            @Override
            public void onResponse(Call<LoginR> call, Response<LoginR> response) {
                Log.d(TAG, "call: " + call);
                Log.d(TAG, "response: " + response.message());
                Log.d(TAG, "response: " + response.body());
            }

            @Override
            public void onFailure(Call<LoginR> call, Throwable t) {

            }
        });
    }
}
