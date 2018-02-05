package com.sanilk.harmonia;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.sanilk.harmonia.entities.User;
import com.sanilk.harmonia.networking.MyMessage;
import com.sanilk.harmonia.networking.NetworkHandler;
import com.sanilk.harmonia.response_interfaces.AuthenticatingInterface;
import com.sanilk.harmonia.response_interfaces.SignUpResponseInterface;
import com.sanilk.harmonia.responses.AuthenticateResponse;
import com.sanilk.harmonia.responses.SignUpResponse;

import org.json.JSONObject;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity{
    GoogleSignInClient googleSignInClient;

    private static final String TAG="MAIN_ACTIVITY";
    private static final int RC_SIGN_IN=1;
    private final Activity activity=this;

    private CallbackManager callbackManager;

    private Handler uiHandler;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.context=this;

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        uiHandler= new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
                handleMessageAgain(msg);
            }
        };

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

        final Context context=this;

        Button signUpButton=(Button)findViewById(R.id.activity_main_sign_up_button);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, SignUpActivity.class);
                startActivity(intent);
            }
        });

        Button signinButton=(Button)findViewById(R.id.activity_main_sign_in_btn);
        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=
                        ((TextView)findViewById(R.id.activity_main_username)).getText().toString();
                String password=
                        ((TextView)findViewById(R.id.activity_main_password)).getText().toString();
                User user=new User();
                user.setName(username);
                user.setPassword(password);
                NetworkHandler.getNetworkHandler().startAuthenticationThread("AUTH_THREAD_1", user, new AuthenticatingInterface() {
                    @Override
                    public void responseReceived(AuthenticateResponse response) {
                        AuthenticatingMessage message=new AuthenticatingMessage();
                        if(response.isSuccessful()){
                            message.authenticatingMessageType=AuthenticatingMessageType.SUCCESS;
                        }else{
                            message.authenticatingMessageType=AuthenticatingMessageType.FAILED;
                        }

                        if(response.isAuthentic()){
                            message.isAuthentic=true;
                        }else{
                            message.isAuthentic=false;
                        }

                        Message msg=new Message();
                        msg.obj=message;
                        uiHandler.sendMessage(msg);

                    }

                    @Override
                    public void onFailure() {

                    }
                });
            }
        });
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

    private void handleMessageAgain(Message message){
        MyMessage.MyMessageType messageType=((MyMessage)message.obj).type;
        switch (messageType){
            case AUTHENTICATE:
                AuthenticatingInterface.AuthenticatingMessage authMessage=
                        (AuthenticatingInterface.AuthenticatingMessage) message.obj;


                switch (authMessage.authenticatingMessageType){
                    case FAILED:
                        Toast.makeText(context, "Authentication failed", Toast.LENGTH_SHORT).show();
                        break;
                    case SUCCESS:
                        if(authMessage.isAuthentic) {
                            Toast.makeText(context, "Authentication successful", Toast.LENGTH_SHORT).show();

                            Intent intent=new Intent(context, HomeActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(context, "Wrong user id password combination", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
                break;
        }
    }
}