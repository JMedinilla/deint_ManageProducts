package com.afg.MngProductContentProvider;


import android.app.Application;
import android.content.Context;

import com.afg.MngProductContentProvider.database.DataBaseHelper;

/**
 * Created by usuario on 20/01/17.
 */

public class ManageProductApplications extends Application {

    public static Context context;


    public static Context getContext(){

        return context;
    }

    public ManageProductApplications(){

        context = this;
    }




}
