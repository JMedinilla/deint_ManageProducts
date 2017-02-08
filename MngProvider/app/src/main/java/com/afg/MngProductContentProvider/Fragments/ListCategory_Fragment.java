package com.afg.MngProductContentProvider.Fragments;

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
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.afg.MngProductContentProvider.Adapter.ListCategoryAdapter;
import com.afg.MngProductContentProvider.Model.Category;
import com.afg.MngProductContentProvider.Presenter.ListCategoryPresenter;
import com.afg.MngProductContentProvider.R;
import com.afg.MngProductContentProvider.interfaces.ICategoryPresenter;

/**
 * Created by usuario on 2/02/17.
 */

public class ListCategory_Fragment extends Fragment implements ICategoryPresenter.View {

    private ListView listView;
    private ListCategoryAdapter adapter;
    private FloatingActionButton fab;
    public static final String RECOVERY_CATEGORY = "category";
    public static final String RECOVERY_MODE_OPEN = "mode_open";
    private ListCategoryPresenter presenter;
    public static final int MODE_UPDATE = 1;
    public static final int MODE_NEW = 2;
    private IListCategoryFragment callBack;

    @Override
    public void setCursorCategory(Cursor cursor) {

        adapter.swapCursor(cursor);
    }

    public void addCategory(Category category){

        presenter.addCategory(category);
    }

    public void updateCategory(Category category){

        presenter.updateCategory(category);
    }

    public void deleteCategory(Category category){

        presenter.deleteCategory(category);
    }

    public interface IListCategoryFragment{

        void showManageCategory(Category category, int mode);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        callBack = (IListCategoryFragment)activity;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.getAllCategoies();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_list_category, container, false);
        presenter = new ListCategoryPresenter(this);
        adapter = new ListCategoryAdapter(getContext(),R.layout.item_list_category);
        listView = (ListView)rootView.findViewById(R.id.listCategory);
        fab = (FloatingActionButton)rootView.findViewById(R.id.fabAddCategory);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                callBack.showManageCategory((Category) adapter.getItem(i), MODE_UPDATE);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                callBack.showManageCategory(new Category(), MODE_NEW);
            }
        });

        return rootView;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        getActivity().getMenuInflater().inflate(R.menu.delete_menu, menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(final MenuItem item) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());

        alertDialog.setTitle("Aviso");
        alertDialog.setMessage("¿Esta seguro que desea borrar esta farmacia?");

        alertDialog.setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                presenter.deleteCategory((Category) adapter.getItem(((AdapterView.AdapterContextMenuInfo)item.
                        getMenuInfo()).position));

            }
        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }
        ).show();

        return super.onContextItemSelected(item);
    }
}
