package com.afg.MngProductDatabase.cursor;

import android.content.Context;
import android.database.Cursor;
import android.content.CursorLoader;

import com.afg.MngProductDatabase.database.DataBaseManager;

public class PharmacyCursorLoader extends CursorLoader {
    public PharmacyCursorLoader(Context context) {
        super(context);
    }

    @Override
    public Cursor loadInBackground() {
        return DataBaseManager.getInstance().loadPharmacies();
    }
}
