package com.sanilk.harmonia;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import org.json.JSONObject;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    GoogleSignInClient googleSignInClient;

    private static final String TAG="MAIN_ACTIVITY";
    private static final int RC_SIGN_IN=1;
    private final Activity activity=this;

    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);


//        GoogleSignInOptions gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN)
//                .requestEmail()
//                .build();
//        googleSignInClient= GoogleSignIn.getClient(this, gso);
//        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
//        if(account==null) {
//            findViewById(R.id.google_sign_in_button).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent signInIntent = googleSignInClient.getSignInIntent();
//                    startActivityForResult(signInIntent, RC_SIGN_IN);
//                }
//            });
//        }else{
//            googleSignInSuccessful();
//        }

        callbackManager = CallbackManager.Factory.create();
        final LoginManager loginManager=LoginManager.getInstance();
        loginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                facebookSignInSuccessful();
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "onCancel in facebook login");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "onError in facebook login");
                error.printStackTrace();
            }
        });

        ((LinearLayout)findViewById(R.id.facebook_login_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginManager.logInWithReadPermissions(activity, Arrays.asList("user_status", "email"));
            }
        });


//        LoginButton loginButton=(LoginButton)findViewById(R.id.facebook_login_button);
//        loginButton.setReadPermissions(Arrays.asList("user_status", "email"));
//        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//            }
//
//            @Override
//            public void onCancel() {
//                Log.d(TAG, "onCancel in facebook login");
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//                Log.d(TAG, "onError in facebook login");
//                error.printStackTrace();
//            }
//        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);

        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try{
                GoogleSignInAccount gsa=task.getResult(ApiException.class);
                googleSignInSuccessful();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    private void facebookSignInSuccessful(){
        Log.d(TAG, "onSuccess in facebook login");
        AccessToken accessToken=AccessToken.getCurrentAccessToken();

        GraphRequest graphRequest=GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback(){
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    Log.d(TAG, object.getString("link"));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        Bundle parameters=new Bundle();
        parameters.putString("fields", "id,name,link");
        graphRequest.setParameters(parameters);
        graphRequest.executeAsync();
    }

    private void googleSignInSuccessful(){
        String a=GoogleSignIn.getLastSignedInAccount(this).getDisplayName();
        Log.d(TAG, GoogleSignIn.getLastSignedInAccount(this).getDisplayName());
        Intent intent=new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}