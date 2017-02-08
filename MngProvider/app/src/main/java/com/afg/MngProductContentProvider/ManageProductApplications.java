package com.afg.MngProductContentProvider;


import android.app.Application;
import android.content.Context;

import com.afg.MngProductContentProvider.database.DataBaseHelper;

public class ManageProductApplications extends Application {

    private static ManageProductApplications instance;
    public ManageProductApplications() {
        instance = this;
    }
    public static Context getContext(){
        return instance;
    }

    /*
    @Override
    public void onCreate() {
        super.onCreate();
        this.context = this;
        DataBaseHelper.getInstance().open();
    }
    */
}
