package com.afg.MngProductDatabase;


import android.app.Application;
import android.content.Context;

import com.afg.MngProductDatabase.database.DataBaseHelper;

/**
 * Created by usuario on 20/01/17.
 */

public class ManageProductApplications extends Application {

    public static Context context;

    public static Context getContext(){

        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.context = this;
        DataBaseHelper.getInstance().open();

    }
}
