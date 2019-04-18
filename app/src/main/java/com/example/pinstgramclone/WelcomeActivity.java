package com.example.pinstgramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.ParseUser;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //Button logOutButton;


        TextView textWelcome = findViewById(R.id.welcomView);

        //String user = "";



        textWelcome.setText("Welcome! " + ParseUser.getCurrentUser().get("username"));

        findViewById(R.id.logOutButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ParseUser.logOutInBackground();
                ParseUser.getCurrentUser().logOut();
                finish();
            }
        });
    }
}
