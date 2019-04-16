package com.example.pinstgramclone;

//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import androidx.appcompat.app.AppCompatActivity;

//import java.lang.annotation.Target;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSave;
    private EditText editName;
    private EditText editSpeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSave = findViewById(R.id.btnSave);
        editName = findViewById(R.id.editName);
        editSpeed = findViewById(R.id.speed);
        btnSave.setOnClickListener(MainActivity.this);



    }


    @Override
    public void onClick(View v) {


            ParseObject player = new ParseObject("Player");
            player.put("name", editName.getText().toString());
            player.put("speed",  editSpeed.getText().toString());

            player.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
//
                        FancyToast.makeText(MainActivity.this, "it's fancily saved", Toast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                    } else {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });



    }
}

