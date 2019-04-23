package com.example.pinstgramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("dGJqwlaDefmFP4Mx3vlMnmbSA4cEA1EytOWdDLCx")
                // if defined
                .clientKey("epsFXvSjHbieKNynrOzBHh5XhyVbMCQrC39ey06O")
                .server("https://parseapi.back4app.com/")
                .build()
        );

    }
}
