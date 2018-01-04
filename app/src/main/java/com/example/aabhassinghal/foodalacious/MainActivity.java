package com.example.aabhassinghal.foodalacious;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {
    String url = "http://10.0.0.49:8000/";
    TextView text_id_1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text_id_1 = (TextView) findViewById(R.id.text_id_1);
        Button ButtonObject= (Button) findViewById(R.id.button);
        ButtonObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                View VisibleArray = findViewById(R.id.RetrofitArray);
//                VisibleArray.setVisibility(View.GONE);
//                View VisibleObject = findViewById(R.id.RetrofitObject);
//                VisibleObject.setVisibility(View.GONE);
                getRetrofitObject();
            }
        });
    }
    void getRetrofitObject() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        RecipeObject service = retrofit.create(RecipeObject.class);
        Call<Recipe> call = service.getStudentDetails();
        call.enqueue(new Callback<Recipe>() {
            @Override
            public void onResponse(Response<Recipe> response, Retrofit retrofit) {
                try {
                    Log.d("message","asssA"+response.body());
                    text_id_1.setText("StudentId  :  " + response.body().getTitle());

                } catch (Exception e) {
                    Log.d("onResponse", "There is an error");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("onFailure", t.toString());
            }
        });

    }

    void saveRetrofitObject() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        RecipeObject service = retrofit.create(RecipeObject.class);
        Recipesinstructions ri = new Recipesinstructions("Cook on slow");
        ArrayList<Recipesinstructions> recipes_instructions = new ArrayList<>();
        recipes_instructions.add(0,ri);
        Recipeingredients ri2 = new Recipeingredients("1","Jeera");
        ArrayList<Recipeingredients> recipes_ingredients = new ArrayList<>();
        recipes_ingredients.add(0,ri2);
        Recipe r = new Recipe("Pannerrasas",1,121,3,recipes_instructions,recipes_ingredients);
        Call<Recipe> call = service.saveStudentDetails(r);
        call.enqueue(new Callback<Recipe>() {
            @Override
            public void onResponse(Response<Recipe> response, Retrofit retrofit) {
                try {
                    Recipe r1 = response.body();
                    text_id_1.setText("StudentId  :  " + r1.getTitle());

                } catch (Exception e) {
                    Log.d("onResponse", "There is an error");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("onFailure", t.toString());
            }
        });

    }
}
