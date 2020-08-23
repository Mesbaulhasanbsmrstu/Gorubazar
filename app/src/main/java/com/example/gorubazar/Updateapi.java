package com.example.gorubazar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Updateapi {
    @FormUrlEncoded
    //@GET("update_product.php")
    //Call<String> update(@Query("price") String price, @Query("product_id") String userid);

    @POST("update_product.php")
    Call<UpdatBody> update(@Field("id") int id, @Field("price") String price,@Field("phone") String phone,@Field("description") String description);
}
