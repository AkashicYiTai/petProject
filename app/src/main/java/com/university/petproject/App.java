package com.university.petproject;

import android.app.Application;

import com.university.petproject.utils.SpUtil;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化sp
        SpUtil.init(getApplicationContext(),"cache");

    }
}
