package com.example.aabhassinghal.foodalacious;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by aabhassinghal on 12/30/17.
 */

public interface RecipeObject {
    @GET("recipeupdate/6/")
    Call<Recipe> getStudentDetails();

    @POST("recipesave/")
    Call<Recipe> saveStudentDetails(@Body Recipe recipe);

    @PUT("recipeupdate/{id}/")
    Call<Recipe> updateStudentDetails(@Path("id")int id,@Body Recipe recipe);

    @DELETE("recipeupdate/{id}/")
    Call<Recipe> deleteStudentDetails(@Path("id")int id);

}
