package polyu.comp.funing.service;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import polyu.comp.funing.constant.CommonConstant;
import polyu.comp.funing.model.Order;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;


/**
 * Created by liushanchen on 16/2/20.
 */
public interface ApiService {

    @FormUrlEncoded
    @POST("login")
    Call<LoginR> uLogin(@FieldMap Map<String, String> options);

    @FormUrlEncoded
    @POST("register")
    Call<LoginR> uRegister(@FieldMap Map<String, String> options);

    @FormUrlEncoded
    @POST("shoppingcart")
    Call<ShoppingCartR> createShoppingCart(@FieldMap Map<String, String> options,@Header("Authorization") String authorization);

    @GET("shoppingcart")
    Call<ShoppingCartR> getShoppingCart(@QueryMap Map<String, String> parameters,@Header("Authorization") String authorization);
    
    @FormUrlEncoded
    @POST("shoppingcart_detail")
    Call<ScDetailR> createScDetail(@FieldMap Map<String, String> options,@Header("Authorization") String authorization);

    @FormUrlEncoded
    @PUT("shoppingcart_detail")
    Call<ScDetailR> updateScDetail(@FieldMap Map<String, String> options,@Header("Authorization") String authorization);
    
    @DELETE("shoppingcart_detail/{sdid}")
    Call<ScDetailR> deleteScDetail(@Path("sdid") int sdid,@Header("Authorization") String authorization);

    @FormUrlEncoded
    @POST("orders")
    Call<OrderR> createOder(@FieldMap Map<String, String> options, @Header("Authorization") String authorization);
   
    @FormUrlEncoded
    @POST("orders_detail")
    Call<OrderR> createOrderDetail(@FieldMap Map<String, String> options,@Header("Authorization") String authorization);

    @FormUrlEncoded
    @PUT("usercoupons/{ucid}")
    Call<OrderR> updateCoupons(@Path("ucid") int sdid,@FieldMap Map<String, String> options,@Header("Authorization") String authorization);

    @FormUrlEncoded
    @PUT("shippingcart")
    Call<OrderR> invalidSC(@FieldMap Map<String, String> options,@Header("Authorization") String authorization);

    @GET("products")
    Call<ProductListR> getProductList();

    @GET("coupons")
    Call<CouponR> getCouponList();

    @GET("usercouponsQuery")
    Call<UserCouponR> getCouponList(@QueryMap Map<String, String> parameters, @Header("Authorization") String authorization);

    @GET("users/{username}")
    Call<LoginR> getUser(@Path("username") String username);


    public static class Creator {
        public static ApiService create() {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = null;
            //setting the cache
            if (!CommonConstant.applicationDir.equals("")) {
                CachingInterceptor cachingInterceptor = new CachingInterceptor();
                Cache cache = new Cache(new File(CommonConstant.applicationDir, "http"), 10 * 1024 * 1024);
                client = new OkHttpClient.Builder()
                        .addInterceptor(interceptor)
                        .addInterceptor(cachingInterceptor)
                        .cache(cache)
                        .build();
            } else {
                client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
            }
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

    //define the cache interceptor
    public class CachingInterceptor implements Interceptor {
        private static String TAG = CachingInterceptor.class.getSimpleName();

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);
            String cacheControl = request.cacheControl().toString();
            if (TextUtils.isEmpty(cacheControl)) {
                cacheControl = "public, max-age=60 ,max-stale=2419200";
            }
            return response.newBuilder()
                    .header("Cache-Control", cacheControl)
                    .removeHeader("Pragma")
                    .build();
        }
    }

}
