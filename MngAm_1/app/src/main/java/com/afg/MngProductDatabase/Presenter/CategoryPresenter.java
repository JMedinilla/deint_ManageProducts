package com.afg.MngProductDatabase.Presenter;


import android.app.Activity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import com.afg.MngProductDatabase.cursor.CategoryCursorLoader;
import com.afg.MngProductDatabase.interfaces.ICategoryPresenter;

public class CategoryPresenter implements ICategoryPresenter, LoaderManager.LoaderCallbacks<Cursor> {

    private ICategoryPresenter.View view;
    private final static int CATEGORY = 1;
    private Context context;

    public CategoryPresenter(ICategoryPresenter.View view){

        this.view = view;
        this.context = view.getContext();
    }

    @Override
    public void getAllCategoies() {

      //  Cursor cursor = DataBaseManager.getInstance().loadCategories();
      //  adapter.swapCursor(cursor);
      //  DataBaseHelper.getInstance().closeDataBase();
        ((Activity)context).getLoaderManager().initLoader(CATEGORY, null, this); //Esto llama a onCreateLoader

    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {

        Loader<Cursor> cursorLoader = null;

        switch (i){

            case CATEGORY:
               cursorLoader = new CategoryCursorLoader(context);
                break;


        }

        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

        view.setCursorCategory(cursor);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        view.setCursorCategory(null);
    }
}
