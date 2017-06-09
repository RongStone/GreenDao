package com.example.rs.greendaodemo;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.example.rs.greendaodemo.db.GreenDaoManager;

/**
 * Created by rongsheng1 on 2017/3/16.
 */

public class MyApplication extends Application {
    public  static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyApplication", "MyApplication  1");
        mContext = getApplicationContext();
        Log.d("MyApplication", "MyApplication  2");
        GreenDaoManager.getInstance(mContext);
    }

    public static Context getContext() {
        Log.d("MyApplication", "MyApplication  3");
        return getContext();
    }

}
