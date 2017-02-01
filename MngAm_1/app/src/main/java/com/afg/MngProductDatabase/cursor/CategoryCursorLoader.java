package com.afg.MngProductDatabase.cursor;


import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;

import com.afg.MngProductDatabase.database.DataBaseManager;

public class CategoryCursorLoader extends CursorLoader {


    public CategoryCursorLoader(Context context) {
        super(context);
    }

    @Override
    public Cursor loadInBackground() {
        return DataBaseManager.getInstance().loadCategories();
    }
}
