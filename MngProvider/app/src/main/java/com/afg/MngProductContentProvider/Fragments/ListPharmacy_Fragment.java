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
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.afg.MngProductContentProvider.Adapter.ListPharmacyAdapter;
import com.afg.MngProductContentProvider.Model.Pharmacy;
import com.afg.MngProductContentProvider.Presenter.PharmacyPresenter;
import com.afg.MngProductContentProvider.R;
import com.afg.MngProductContentProvider.interfaces.IViewPharmacy;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListPharmacy_Fragment extends Fragment implements IViewPharmacy {


    private ProgressDialog dialog;
    private ListView list;
    private FloatingActionButton fabAddPharmacy;
    public static final String RECOVERY_PHARMACY = "pharmacy";
    public static final String RECOVERY_MODE = "mode";
    public static final int MODE_NEW = 1;
    public static final int MODE_UPDATE = 2;
    private CoordinatorLayout parent;
    private PharmacyPresenter presenter;
    private ListPharmacyAdapter adapter;
    private IListPharmacyFragment callBack;

    public interface IListPharmacyFragment{

        void showManagePharmacy(Pharmacy pharmacy, int mode);

        void onCreatePharmacy(Pharmacy pharmacy);
    }


    public ListPharmacy_Fragment() {


    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.getAllPharmacies();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_pharmacy, null);
        list = (ListView)rootView.findViewById(R.id.listPharmacy);
        parent = (CoordinatorLayout)rootView.findViewById(R.id.listPharmacyFragment);
        fabAddPharmacy = (FloatingActionButton)rootView.findViewById(R.id.fabAddPharmacy);
        presenter = new PharmacyPresenter(this);
        dialog = new ProgressDialog(getContext());
        registerForContextMenu(list);
        adapter = new ListPharmacyAdapter(getContext(), R.layout.item_list_pharmacy);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                callBack.showManagePharmacy((Pharmacy) adapter.getItem(i), MODE_UPDATE);
            }


        });

        fabAddPharmacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                callBack.showManagePharmacy(new Pharmacy(), MODE_NEW);
            }
        });

        return rootView;
    }

    public void addPharmacy(Pharmacy pharmacy){

        presenter.addPharmacy(pharmacy);
    }

    public void updatePharmacy(Pharmacy pharmacy){

        presenter.updatePharmacy(pharmacy);
    }

    public void deletePharmacy(Pharmacy pharmacy){

        presenter.deletePharmacy(pharmacy);
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

                presenter.deletePharmacy((Pharmacy) adapter.getItem(((AdapterView.AdapterContextMenuInfo)item.getMenuInfo()).position));

            }
        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }
        ).show();
        return super.onContextItemSelected(item);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        callBack = (IListPharmacyFragment)activity;
    }

    @Override
    public void startProgress(int codeMsg) {

        dialog.setMessage(getString(codeMsg));
    //    dialog.show();
    }

    @Override
    public void actionOK(int codeMsg) {

        dialog.dismiss();
        adapter.notifyDataSetChanged();
        showMsg(codeMsg);
    }

    @Override
    public void failAction(int codeMsg) {

        dialog.dismiss();
        showMsg(codeMsg);
    }

    @Override
    public void setCursorPharmacy(Cursor cursor) {

        adapter.changeCursor(cursor);
        adapter.notifyDataSetChanged();
        dialog.dismiss();
    }

    private void showMsg(int codeMsg){

        Snackbar.make(parent, getString(codeMsg), Snackbar.LENGTH_LONG).show();
    }
}
