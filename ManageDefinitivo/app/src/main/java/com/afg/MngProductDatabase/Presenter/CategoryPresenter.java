package com.afg.MngProductDatabase.Presenter;

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
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.app.LoaderManager;
import android.content.Loader;
import android.widget.CursorAdapter;

import com.afg.MngProductDatabase.cursor.CategoryCursorLoader;
import com.afg.MngProductDatabase.interfaces.ICategoryPresenter;

public class CategoryPresenter implements ICategoryPresenter, LoaderManager.LoaderCallbacks<Cursor> {

    private Context context;
    private ICategoryPresenter.View view;
    private final static int CATEGORY = 1;

    public CategoryPresenter(ICategoryPresenter.View view){
        this.view = view;
        this.context = view.getContext();
    }

    @Override
    public void getAllCategoies() {

        //Cursor cursor = DataBaseManager.getInstance().loadCategories();
        //adapter.swapCursor(cursor);
        //DataBaseHelper.getInstance().closeDataBase();

        //getLoaderManager().initLoader(CATEGORY, null, this); -> esto se usaría desde la Activity/Fragment
        ((Activity)context).getLoaderManager().initLoader(CATEGORY, null, this);

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Loader<Cursor> loader;
        switch (id) {
            case CATEGORY:
                loader = new CategoryCursorLoader(context);
                break;
            default:
                loader = null;
        }
        return loader;
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
