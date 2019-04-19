package com.example.pinstgramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class loginActivity extends AppCompatActivity {

    EditText editLoginEmailActivity;
    EditText editlogInPasswordActivity;

    Button buttonLoginActivity;
    Button buttonSignUpGoBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Log In");

        editLoginEmailActivity = findViewById(R.id.editLoginEmailActivity);
        editlogInPasswordActivity = findViewById(R.id.editlogInPasswordActivity);
        buttonLoginActivity = findViewById(R.id.logInButtonGoIntent);
        buttonSignUpGoBack = findViewById(R.id.buttonSignUpGoBack);


        if (ParseUser.getCurrentUser() != null) {
            ParseUser.getCurrentUser().logOut();
        }


        buttonSignUpGoBack.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(loginActivity.this, MainActivity.class);
            startActivity(intent);
        }
    });

      buttonLoginActivity.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ParseUser.logInInBackground(editLoginEmailActivity.getText().toString(),
                    editlogInPasswordActivity.getText().toString(), new LogInCallback() {
                        @Override
                        public void done(ParseUser user, ParseException e) {
                            if (user != null && e == null) {
                                FancyToast.makeText(loginActivity.this, user.getUsername() + " has loged in!",
                                        Toast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                                transitionToSocialMediaActivity();

                            } else {
                                Toast.makeText(loginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });

        }
    });

    }

    public void rootLayoutTapped(View view){

        try{
            InputMethodManager inputMethodManager =
                    (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0 );

        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    public void transitionToSocialMediaActivity(){
        Intent intent = new Intent(loginActivity.this,SocialMediaActivity.class);
        startActivity(intent);
    }



}
