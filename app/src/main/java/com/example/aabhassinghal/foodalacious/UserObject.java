package com.example.aabhassinghal.foodalacious;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by aabhassinghal on 12/30/17.
 */

public interface UserObject {
    @GET("login/{id}/")
    Call<User> getUserDetails();

    @POST("signup/")
    Call<User> saveuserDetails(@Body User user);


}
