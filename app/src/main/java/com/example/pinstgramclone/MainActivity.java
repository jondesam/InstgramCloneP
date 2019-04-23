package com.example.pinstgramclone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

//import java.lang.annotation.Target;

import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editId;
    private EditText editPassword;
    private EditText editEmail;

    private Button logInButton;
    private Button signUpButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            setTitle("Sign Up");

          //  btnSave.setOnClickListener(MainActivity.this);

            editEmail  = findViewById(R.id.editEmail);
            editPassword = findViewById(R.id.editlogInPasswordActivity);
            editId = findViewById(R.id.editLoginEmailActivity);

            editPassword.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View view, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_ENTER
                            && event.getAction() == KeyEvent.ACTION_DOWN) {
                        signUpButton.callOnClick();
                    }
                    return false;
                }
            });

            logInButton = findViewById(R.id.logInButtonGoIntent);
            signUpButton = findViewById(R.id.SignUpButton);

          //  logInButton.setOnClickListener(MainActivity.this);
           // signUpButton.setOnClickListener(MainActivity.this);


        if (ParseUser.getCurrentUser() != null) {
            ParseUser.getCurrentUser().logOut();
        }

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editEmail.getText().toString().equals("") ||
                        editId.getText().toString().equals("") ||
                        editPassword.getText().toString().equals("")) {
                    FancyToast.makeText(MainActivity.this,  "Please enter Email, ID, Password! ",
                            Toast.LENGTH_LONG,FancyToast.SUCCESS,true).show();

                } else {
                    final ParseUser user = new ParseUser();
                    user.setUsername(editId.getText().toString());
                    user.setEmail(editEmail.getText().toString());
                    user.setPassword(editPassword.getText().toString());

                    final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                    progressDialog.setMessage("Signing up " + editId.getText().toString());
                    progressDialog.show();

                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {

                            if (e == null) {
                                FancyToast.makeText(MainActivity.this, user.getUsername() + " has signed up!",
                                        Toast.LENGTH_LONG,FancyToast.SUCCESS,true).show();

                                transitionToSocialMediaActivity();
                            } else {
                                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                            progressDialog.dismiss();
                        }
                    });

                }

            }
        });


            logInButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(MainActivity.this,loginActivity.class);
                    startActivity(intent);
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
        Intent intent = new Intent(MainActivity.this,SocialMediaActivity.class);
        startActivity(intent);
    }


}

