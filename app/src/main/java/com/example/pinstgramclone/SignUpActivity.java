package com.example.pinstgramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpActivity extends AppCompatActivity {

    private EditText editUserNameSignUp, editPassWordSignUp, editUserNameLogIn, editPassWordLogIn;
    private Button logInButton, signUpButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editUserNameLogIn = findViewById(R.id.editUserNameLogIn);
        editPassWordLogIn = findViewById(R.id.editPassWordLogIn);
        editUserNameSignUp = findViewById(R.id.editUserNameSignUp);
        editPassWordSignUp = findViewById(R.id.editPassWordSignUp);

        logInButton = findViewById(R.id.logInButton);
        signUpButton = findViewById(R.id.signUpButton);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ParseUser appUser = new ParseUser();
                appUser.setUsername(editUserNameSignUp.getText().toString());
                appUser.setPassword(editPassWordSignUp.getText().toString());

                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            FancyToast.makeText(SignUpActivity.this,
                                    appUser.get("username") + " has signed up!",
                                    Toast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                        } else {
                            FancyToast.makeText(SignUpActivity.this,
                                    FancyToast.ERROR, Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });


        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 ParseUser.logInInBackground(editUserNameLogIn.getText().toString(),
                         editPassWordLogIn.getText().toString(), new LogInCallback() {
                             @Override
                             public void done(ParseUser user, ParseException e) {

                                 if (user != null && e == null) {
                                     FancyToast.makeText(SignUpActivity.this,
                                             user.get("username") + " has logged in!",
                                             Toast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                                 } else {
                                     Toast.makeText(SignUpActivity.this,
                                             e.getMessage(),
                                             Toast.LENGTH_SHORT).show();
                                 }
                             }
                         });
            }
        });



    }
}