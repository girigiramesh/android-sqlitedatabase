package com.example.apple.sqlitedatabase;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by Apple on 8/4/16.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);

    }
}
