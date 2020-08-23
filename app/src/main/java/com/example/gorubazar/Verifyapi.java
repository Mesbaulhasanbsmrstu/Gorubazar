package com.example.gorubazar;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Verifyapi {
    @GET("verify.php")
    Call<Verifyresponse> getStringScalar(@Query("phone") String phone, @Query("password") String password);
}
