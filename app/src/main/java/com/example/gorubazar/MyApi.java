package com.example.gorubazar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface MyApi {
    @FormUrlEncoded
    @POST("add_product.php")
    Call<ResponseBody> insertdata(@Field("imageurl") String imageurl,@Field("price") String price,@Field("phone") String phone,@Field("description") String description,@Field("type") String type,@Field("seller_id") String userid);
   }
