package com.example.pinstgramclone;

//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import androidx.appcompat.app.AppCompatActivity;

//import java.lang.annotation.Target;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSave;
    private EditText editName;
    private EditText editSpeed;
    private EditText textGetData;
    private EditText editPower;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSave = findViewById(R.id.btnSave);
        editName = findViewById(R.id.editName);
        editSpeed = findViewById(R.id.speed);
        btnSave.setOnClickListener(MainActivity.this);
        textGetData = findViewById(R.id.textGetData);
        editPower = findViewById(R.id.power);


        //query from server
        textGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("Player");
                parseQuery.getInBackground("0BSyhI5cPd", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if (object != null && e == null) {

                            textGetData.setText(object.get("Speed") + "");
                        } else {
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }

                    }
                });

            }
        });



    }

        //input data to server
    @Override
    public void onClick(View v) {


            ParseObject player = new ParseObject("Player");
            player.put("name", editName.getText().toString());
            player.put("speed", editSpeed.getText().toString());
            player.put("power", Integer.parseInt( editSpeed.getText().toString()));

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

