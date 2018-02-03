package com.sanilk.harmonia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sanilk.harmonia.entities.User;
import com.sanilk.harmonia.networking.NetworkHandler;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Button signUpButton=(Button)findViewById(R.id.activity_sign_up_singup_btn);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=((EditText)findViewById(R.id.activity_sign_up_name)).getText().toString();
                String email=((EditText)findViewById(R.id.activity_sign_up_email)).getText().toString();
                String password=((EditText)findViewById(R.id.activity_sign_up_password)).getText().toString();

                NetworkHandler networkHandler=NetworkHandler.getNetworkHandler();
                networkHandler.startSignUpThread("SIGN_UP_THREAD_1", new User(name, password, email));
            }
        });
    }
}
