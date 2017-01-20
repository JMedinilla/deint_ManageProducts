package com.jsw.MngProductDatabase;

import android.app.Application;

import com.jsw.MngProductDatabase.database.DatabaseHelper;

public class ManageproductApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseHelper.getInstance(getApplicationContext()).open();
    }
}
