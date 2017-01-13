package com.jsw.MngProductDatabase.utils;

/*
 * Copyright (c) 2016 José Luis del Pino Gallardo.
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
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;

import com.jsw.MngProductDatabase.Presenter.ProductPresenter;
import com.jsw.MngProductDatabase.R;

/**
 * Created by usuario on 19/12/16.
 */
/*
public class MultiChoiceListener implements AbsListView.MultiChoiceModeListener {

    private Context context;
    private int statusBarColor;
    private int cont=0;
    private ProductPresenter presenter;

    public MultiChoiceListener(Context context, ProductPresenter presenter) {
        this.context = context;
        this.presenter = presenter;
    }

    @Override
    public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean b) {
        if(b) {
            cont++;
            presenter.setNewSelection(i, b);
        }
        else {
            cont--;
            presenter.removeSelection(i);
        }

        actionMode.setTitle(cont);
    }

    @Override
    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        MenuInflater inflater = actionMode.getMenuInflater();
        inflater.inflate(R.menu.action_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        statusBarColor = ((AppCompatActivity)context).getWindow().getStatusBarColor();
        ((AppCompatActivity)context).getWindow().setStatusBarColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.action_delete_product:
                presenter.deleteSelectedProduct();
            }
        }

        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode actionMode) {
        ((AppCompatActivity)context).getWindow().setStatusBarColor(ContextCompat.getColor(context, statusBarColor));
        presenter.clearSelection();
        for (View v: listView) {
            v.setVisibility(View.VISIBLE);
        }
        cont=0;
    }

    public boolean isPosition(int position){
        return true;
    }
}*/
