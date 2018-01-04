package com.example.aabhassinghal.foodalacious;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by aabhassinghal on 12/30/17.
 */

public interface RecipeObject {
    @GET("recipeupdate/6/")
    Call<Recipe> getStudentDetails();

    @POST("recipesave/")
    Call<Recipe> saveStudentDetails(@Body Recipe recipe);
}
