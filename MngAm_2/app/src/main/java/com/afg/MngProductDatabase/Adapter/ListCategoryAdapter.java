package com.afg.MngProductDatabase.Adapter;

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

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.afg.MngProductDatabase.Model.Category;
import com.afg.MngProductDatabase.R;

/**
 * Created by usuario on 2/02/17.
 */

public class ListCategoryAdapter extends CursorAdapter {

    private int layout;

    class CategoryHolder{

        TextView txvName;
    }

    public ListCategoryAdapter(Context context, int layout) {
        super(context, null,0);
        this.layout = layout;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View rootView = inflater.inflate(layout, viewGroup, false);
        CategoryHolder categoryHolder = new CategoryHolder();
        categoryHolder.txvName = (TextView)rootView.findViewById(R.id.txvNameCategory);
        rootView.setTag(categoryHolder);
        return rootView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        CategoryHolder categoryHolder = (CategoryHolder) view.getTag();
        categoryHolder.txvName.setText(cursor.getString(1));
    }

    @Override
    public Object getItem(int position) {

        getCursor().moveToPosition(position);
        Category category = new Category();
        category.setId(getCursor().getLong(0));
        category.setName(getCursor().getString(1));
        return category;
    }
}
