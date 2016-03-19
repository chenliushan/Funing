package polyu.comp.funing.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import polyu.comp.funing.constant.CommonConstant;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


/**
 * Created by liushanchen on 16/2/20.
 */
public interface ApiService {

    @FormUrlEncoded
    @POST("login")
    Call<LoginR> uLogin(@FieldMap Map<String, String> options);

    @GET("products")
    Call<ProductListR> getProductList();
    
      @GET("users/{username}")
    Call<LoginR> getUser(@Path("username") String username);
    
    
    public static class Creator {
        public static ApiService create(){
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
            Gson gson = new GsonBuilder().create();
            
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(CommonConstant.apiEndpoint)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            ApiService apiService = retrofit.create(ApiService.class);
            return apiService;
        }
    }
    
}
