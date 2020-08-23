package com.example.gorubazar;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Showapiforclient {
   // @FormUrlEncoded
    @GET("fetch_product.php")
    Call<List<Show_elementforclient>> getdata1(@Query("location") String location);
}
