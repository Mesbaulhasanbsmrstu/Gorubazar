package com.example.gorubazar;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Deleteapi {
    @FormUrlEncoded
    @POST("product_delete.php")
    Call<Deleteresponse> delete_product(@Field("product_id") int id);

}
