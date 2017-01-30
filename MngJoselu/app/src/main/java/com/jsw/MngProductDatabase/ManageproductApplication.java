package com.jsw.MngProductDatabase;

import android.app.Application;
import android.content.Context;

import com.jsw.MngProductDatabase.database.DatabaseHelper;

public class ManageproductApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        DatabaseHelper.getInstance().open();
    }

    public static Context getContext() {
        return mContext;
    }
}
