package com.example.aabhassinghal.foodalacious;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;


public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private CallbackManager callbackManager;
    private TextView skip;
    private ImageView iv;
    private Button loginButton;
    private ProfileTracker profileTracker;
    private AccessTokenTracker accessTokenTracker;
    AccessToken accessToken;
    FacebookCallback facebookCallback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.fb_login);
        skip = (TextView)findViewById(R.id.skip);
        loginButton = (Button)findViewById(R.id.login_button);
        skip.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent = new Intent(LoginActivity.this, Bottom.class);
                startActivity(intent);
                return false;
            }
        });

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {

            }
        };
        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {

            }
        };
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abcd();
            }
        });

    }

    public void abcd() {
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email","public_profile"));
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                accessToken = loginResult.getAccessToken();
//            Profile profile = loginResult.getCurrent
                handleFacebookAccessToken(accessToken);

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null) {
            updateUI();
        }
    }

    private void updateUI() {

        Toast.makeText(LoginActivity.this,"Login with facebook",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(LoginActivity.this, Bottom.class);
//                    intent.putExtra("userProfile", object.toString());
        startActivity(intent);
//        GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
//            @Override
//            public void onCompleted(JSONObject object, GraphResponse response) {
//                Log.e("aa",""+response.toString());
//                Log.e("aaa",""+object);
//                try{
//                    Log.e("aaaaa",""+object.getString("email"));
//                    Intent intent = new Intent(LoginActivity.this, Bottom.class);
////                    intent.putExtra("userProfile", object.toString());
//                    startActivity(intent);
//                }catch (JSONException e)
//                {
//                    e.printStackTrace();
//                }
//            }
//        });
//        Bundle parameters = new Bundle();
//        parameters.putString("fields","id,name,email,picture.width(120).height(120)");
//        request.setParameters(parameters);
//        request.executeAsync();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void handleFacebookAccessToken(AccessToken token) {

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            Log.e("aaaaa",""+mAuth.getCurrentUser());

                            updateUI();
                        }

                    }
                });
    }

}
