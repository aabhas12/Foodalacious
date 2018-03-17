package com.example.aabhassinghal.foodalacious;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.PATCH;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by aabhassinghal on 12/30/17.
 */

public interface RecipeObject {
    @GET("recipe/recipeupdate/6/")
    Call<Recipe> getStudentDetails();

    @GET("recipe/recipeupdate/")
    Call<List<Recipe>> getallStudentDetails();

    @POST("recipe/recipeupdate/")
    Call<Recipe> saveStudentDetails(@Body Recipe recipe);

    @PATCH("recipe/recipeupdate/{id}/")
    Call<Recipe> updateStudentDetails(@Path("id")int id,@Body Recipe recipe);

    @DELETE("recipe/recipeupdate/{id}/")
    Call<Recipe> deleteStudentDetails(@Path("id")int id);

}
