package com.example.gorubazar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Editapi {
    @FormUrlEncoded
    @POST("update_product1.php")
    Call<UpdatBody> editdata(@Field("product_id") int id ,@Field("price") String price, @Field("phone") String phone,
                                @Field("description") String description, @Field("imageurl") String imageurl);


}
