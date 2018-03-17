package com.example.aabhassinghal.foodalacious;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class Signup extends AppCompatActivity {
    String url = "http://10.0.0.49:8000/";
    EditText name,username,email;
    JSONObject response,profile_pic_url,profile_pic_data;
    String jsondata,id;
    URL avatar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        name = (EditText) findViewById(R.id.EditTextName);
        username = (EditText) findViewById(R.id.EditTextUserName);
        email = (EditText) findViewById(R.id.EditTextEmail);
        Button ButtonObject= (Button) findViewById(R.id.ButtonSendFeedback);
        Intent intent = getIntent();
        jsondata = intent.getStringExtra("userProfile");
        try {
            response = new JSONObject(jsondata);
            name.setText(response.get("name").toString());
            email.setText(response.get("email").toString());
            profile_pic_data = new JSONObject(response.get("picture").toString());
            profile_pic_url = new JSONObject(profile_pic_data.getString("data"));
            id = response.get("id").toString();
            avatar = new URL(profile_pic_url.getString("url"));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
            ButtonObject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //                View VisibleArray = findViewById(R.id.RetrofitArray);
                    //                VisibleArray.setVisibility(View.GONE);
                    //                View VisibleObject = findViewById(R.id.RetrofitObject);
                    //                VisibleObject.setVisibility(View.GONE);
                    // getRetrofitObject();
                    signup();
                }
            });

    }
//    void getRetrofitObject() {
//        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
//        RecipeObject service = retrofit.create(RecipeObject.class);
//        Call<List<Recipe>> call = service.getallStudentDetails();
//        call.enqueue(new Callback<List<Recipe>>() {
//            @Override
//            public void onResponse(Response<List<Recipe>> response, Retrofit retrofit) {
//                try {
//                    Log.d("message","asssA"+response.body());
//                    List<Recipe> re = response.body();
//
//                } catch (Exception e) {
//                    Log.d("onResponse", "There is an error");
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                Log.d("onFailure", t.toString());
//            }
//        });
//
//    }
//
//    void updateRetrofitObject() {
//        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
//        RecipeObject service = retrofit.create(RecipeObject.class);
//        Recipesinstructions ri = new Recipesinstructions("Cook on sleweweweow");
//        ArrayList<Recipesinstructions> recipes_instructions = new ArrayList<>();
//        recipes_instructions.add(0,ri);
//        Recipeingredients ri2 = new Recipeingredients("12","Jeeweweweera");
//        ArrayList<Recipeingredients> recipes_ingredients = new ArrayList<>();
//        recipes_ingredients.add(0,ri2);
//        Recipe r = new Recipe("Pannwweweweerrasa22s",1,12221,31,recipes_instructions,recipes_ingredients);
//        Call<Recipe> call = service.updateStudentDetails(5,r);
//        call.enqueue(new Callback<Recipe>() {
//            @Override
//            public void onResponse(Response<Recipe> response, Retrofit retrofit) {
//                try {
//                    Recipe r1 = response.body();
//                    text_id_1.setText("StudentId  :  " + r1.getTitle());
//
//                } catch (Exception e) {
//                    Log.d("onResponse", "There is an error");
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                Log.d("onFailure", t.toString());
//            }
//        });
//
//    }
//
    void signup() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        UserObject service = retrofit.create(UserObject.class);

        User user = null;

        user = new User(name.getText().toString(), email.getText().toString(),
                    username.getText().toString(),id,avatar);

        Call<User> call = service.saveuserDetails(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Response<User> response, Retrofit retrofit) {
                try {
                    User user = response.body();
                    Intent intent = new Intent(Signup.this, Bottom.class);
                    intent.putExtra("userProfile", jsondata);
                    startActivity(intent);
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
//
//    void deleteRetrofitObject() {
//        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
//        RecipeObject service = retrofit.create(RecipeObject.class);
//        Call<Recipe> call = service.deleteStudentDetails(5);
//        call.enqueue(new Callback<Recipe>() {
//            @Override
//            public void onResponse(Response<Recipe> response, Retrofit retrofit) {
//                try {
//
//                    text_id_1.setText("StudentId  :  " + response.toString());
//
//                } catch (Exception e) {
//                    Log.d("onResponse", "There is an error");
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                Log.d("onFailure", t.toString());
//            }
//        });
//
//    }
}
