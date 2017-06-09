package com.example.rs.greendaodemo.db;

import android.content.Context;
import android.util.Log;

import com.example.rs.greendaodemo.greendao.gen.DaoMaster;
import com.example.rs.greendaodemo.greendao.gen.DaoSession;

/**
 * Created by rongsheng1 on 2017/3/16.
 */

public class GreenDaoManager {
    private static GreenDaoManager mInstance;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    private GreenDaoManager(Context context) {
        Log.d("GreenDaoManager", "GreenDaoManager  2");
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, "users-db", null);
        mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
    }

    public static GreenDaoManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new GreenDaoManager(context);
            Log.d("GreenDaoManager", "GreenDaoManager  1");
        }
        return mInstance;
    }

    public DaoMaster getMaster() {
        return mDaoMaster;
    }

    public DaoSession getSession() {
        return mDaoSession;
    }

    public DaoSession getNewSession() {
        mDaoSession = mDaoMaster.newSession();
        return mDaoSession;
    }
}
