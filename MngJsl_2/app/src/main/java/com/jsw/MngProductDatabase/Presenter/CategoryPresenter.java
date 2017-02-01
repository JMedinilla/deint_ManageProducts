package com.jsw.MngProductDatabase.Presenter;

import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;

import com.jsw.MngProductDatabase.database.DatabaseManager;
import com.jsw.MngProductDatabase.interfaces.ICategory;

public class CategoryPresenter implements ICategory {

    ICategory.View view;

    public CategoryPresenter(ICategory.View view) {
        this.view = view;
    }

    @Override
    public void getAllCategory(CursorAdapter cursorAdapter) {
        Cursor cursor = DatabaseManager.getInstance().getCategories();
        cursorAdapter.swapCursor(cursor);
    }
}
