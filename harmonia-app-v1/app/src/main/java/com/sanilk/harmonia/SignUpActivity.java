package com.sanilk.harmonia;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sanilk.harmonia.entities.User;
import com.sanilk.harmonia.networking.MyMessage;
import com.sanilk.harmonia.networking.NetworkHandler;
import com.sanilk.harmonia.response_interfaces.SignUpResponseInterface;
import com.sanilk.harmonia.responses.SignUpResponse;

public class SignUpActivity extends AppCompatActivity {
    Handler uiHandler;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        this.context=this;

        uiHandler=new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
                handleMessageAgain(msg);
            }
        };

        Button signUpButton=(Button)findViewById(R.id.activity_sign_up_singup_btn);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=((EditText)findViewById(R.id.activity_sign_up_name)).getText().toString();
                String email=((EditText)findViewById(R.id.activity_sign_up_email)).getText().toString();
                String password=((EditText)findViewById(R.id.activity_sign_up_password)).getText().toString();

                NetworkHandler networkHandler=NetworkHandler.getNetworkHandler();
                networkHandler.startSignUpThread("SIGN_UP_THREAD_1", new User(name, password, email), new SignUpResponseInterface() {
                    @Override
                    public void responseReceived(SignUpResponse response) {
                        SignUpMessage signUpMessage=new SignUpMessage();
                        signUpMessage.signUpMessageType=SignUpMessageType.SUCCESS;
                        Message msg=new Message();
                        msg.obj=signUpMessage;
                        uiHandler.sendMessage(msg);
                    }

                    @Override
                    public void onFailure() {
                        SignUpMessage signUpMessage=new SignUpMessage();
                        signUpMessage.signUpMessageType=SignUpMessageType.FAILED;
                        Message msg=new Message();
                        msg.obj=signUpMessage;
                        uiHandler.sendMessage(msg);
                    }

                });
            }
        });
    }

    private void handleMessageAgain(Message message){
        MyMessage.MyMessageType messageType=((MyMessage)message.obj).type;
        switch (messageType){
            case SIGN_UP:
                SignUpResponseInterface.SignUpMessage signUpMessage=
                        (SignUpResponseInterface.SignUpMessage)message.obj;

                switch (signUpMessage.signUpMessageType){
                    case FAILED:
                        Toast.makeText(context, "SignUp failed", Toast.LENGTH_SHORT).show();
                        break;
                    case SUCCESS:
                        Toast.makeText(context, "SignUp successful", Toast.LENGTH_SHORT).show();
                        break;
                }
                break;
        }
    }
}
