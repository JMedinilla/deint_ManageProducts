package com.afg.MngProductContentProvider.Presenter;

/*
 * Copyright (c) 2017 José Luis del Pino Gallardo.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *  jose.gallardo994@gmail.com
 */

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;

import com.afg.MngProductContentProvider.cursor.CategoryCursorLoader;
import com.afg.MngProductContentProvider.database.DataBaseContract;
import com.afg.MngProductContentProvider.interfaces.ICategoryPresenter;
import com.afg.MngProductContentProvider.provider.ManageProductContract;

/**
 * Created by usuario on 26/01/17.
 */

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
               cursorLoader =  new CursorLoader(
                       context,
                       ManageProductContract.Category.CONTENT_URI,
                       ManageProductContract.Category.PROJECTION,
                       null, null, DataBaseContract.CategoryEntry.DEFAULT_SORT);
                break;


        }

        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        view.setCursorCategory(cursor);
        cursor.setNotificationUri(context.getContentResolver(), ManageProductContract.Category.CONTENT_URI);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        view.setCursorCategory(null);
    }
}
