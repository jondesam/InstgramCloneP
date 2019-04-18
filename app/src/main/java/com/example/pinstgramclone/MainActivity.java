package com.example.pinstgramclone;

//import android.support.v7.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import androidx.appcompat.app.AppCompatActivity;

//import java.lang.annotation.Target;

public class MainActivity extends AppCompatActivity  {

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
                                } else {
                                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                                progressDialog.dismiss();
                    }
                });
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


}

