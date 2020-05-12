package com.heyaug.httpso;

import android.app.Application;

public class MyApp extends Application {
    private static MyApp myApp;
    public static MyApp getInstance() {
        return myApp;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;
    }
}
