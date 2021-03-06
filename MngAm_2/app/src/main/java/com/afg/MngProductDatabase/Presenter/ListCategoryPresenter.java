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
import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;

import com.afg.MngProductDatabase.Model.Category;
import com.afg.MngProductDatabase.cursor.CategoryCursorLoader;
import com.afg.MngProductDatabase.database.DataBaseManager;
import com.afg.MngProductDatabase.interfaces.ICategoryPresenter;

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

        DataBaseManager.getInstance().addCategoty(category);
        reloadCategories();

    }

    public void updateCategory(Category category){

        DataBaseManager.getInstance().updateCategory(category);
        reloadCategories();

    }

    public void deleteCategory(Category category){

        DataBaseManager.getInstance().deleteCategory(category);
        reloadCategories();
    }


    @Override
    public void getAllCategoies() {

        ((Activity)view.getContext()).getLoaderManager().initLoader(CATEGORY, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CategoryCursorLoader(view.getContext());
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

        view.setCursorCategory(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        view.setCursorCategory(null);

    }

    private void reloadCategories(){

        ((Activity)view.getContext()).getLoaderManager().restartLoader(CATEGORY, null, this);
    }
}
