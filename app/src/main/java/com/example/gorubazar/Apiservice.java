package com.example.gorubazar;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Apiservice {
    @FormUrlEncoded
    @POST("seller_registration.php")
    Call<String> getStringScalar(@Field("name") String name,@Field("phone") String phone, @Field("password") String password,@Field("location") String location);

}
