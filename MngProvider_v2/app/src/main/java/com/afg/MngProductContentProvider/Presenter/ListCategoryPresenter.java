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
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;

import com.afg.MngProductContentProvider.Model.Category;
import com.afg.MngProductContentProvider.database.DataBaseContract;
import com.afg.MngProductContentProvider.interfaces.ICategoryPresenter;
import com.afg.MngProductContentProvider.provider.ManageProductContract;


/**
 * Created by usuario on 2/02/17.
 */

public class ListCategoryPresenter implements ICategoryPresenter, LoaderManager.LoaderCallbacks<Cursor> {


    private ICategoryPresenter.View view;
    private static final int CATEGORY = 1;

    public ListCategoryPresenter(ICategoryPresenter.View view){

        this.view = view;
    }

    public void addCategory(Category category){

     //   DataBaseManager.getInstance().addCategoty(category);

        ContentValues params = new ContentValues();
        params.put(ManageProductContract.Category.NAME, category.getName());

        try {

            view.getContext().getContentResolver().insert(ManageProductContract.Category.CONTENT_URI, params);

        } catch (SQLException e) {

            view.showMsg(e.getMessage());
            e.printStackTrace();
        }

        //  reloadCategories();

    }

    public void updateCategory(Category category){

        final ContentValues params = new ContentValues();
        final String[] whereParams = {String.valueOf(category.getId())};
        params.put(ManageProductContract.Category.NAME, category.getName());
        view.getContext().getContentResolver().update(ManageProductContract.Category.CONTENT_URI, params, "_id = ?", whereParams);

    }

    public void deleteCategory(Category category){


        final ContentValues params = new ContentValues();
        final String[] whereParams = {String.valueOf(category.getId())};
        view.getContext().getContentResolver().delete(ManageProductContract.Category.CONTENT_URI, "_id = ?", whereParams);
    }


    @Override
    public void getAllCategoies() {

        ((Activity)view.getContext()).getLoaderManager().initLoader(CATEGORY, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {

        return new CursorLoader(view.getContext(), ManageProductContract.Category.CONTENT_URI,
                ManageProductContract.Category.PROJECTION, null, null,
                DataBaseContract.CategoryEntry.DEFAULT_SORT);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

        view.setCursorCategory(cursor);
        cursor.setNotificationUri(view.getContext().getContentResolver(), ManageProductContract.Category.CONTENT_URI);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        view.setCursorCategory(null);

    }

    private void reloadCategories(){

        ((Activity)view.getContext()).getLoaderManager().restartLoader(CATEGORY, null, this);
    }
}
