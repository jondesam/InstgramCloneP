package com.example.pinstgramclone;

//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import androidx.appcompat.app.AppCompatActivity;

//import java.lang.annotation.Target;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public  void helloWorldTapped(View view) {

        ParseObject player = new ParseObject("Player");
        player.put("name", "jon");
        player.put("speed", "10");
        player.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
//                    Toast.makeText(MainActivity.this, "saved", Toast.LENGTH_SHORT).show();
//                    FancyToast.makeText(this,"Hello World !",FancyToast.LENGTH_LONG,FancyToast.DEFAULT,true);
                    FancyToast.makeText(MainActivity.this, "it's fancily saved", Toast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                }
            }
        });

    }


}

