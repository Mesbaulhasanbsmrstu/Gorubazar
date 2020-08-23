package com.example.gorubazar;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Loginapi {

    @GET("seller_login.php")
    Call<Loginresponse> getStringScalar(@Query("phone") String phone, @Query("password") String password);

}
