package com.afg.MngProductDatabase.Fragments;


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

import android.app.ProgressDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.afg.MngProductDatabase.Adapter.PharmacyAdapter;
import com.afg.MngProductDatabase.Model.Pharmacy;
import com.afg.MngProductDatabase.Presenter.PharmacyPresenter;
import com.afg.MngProductDatabase.R;
import com.afg.MngProductDatabase.interfaces.IPharmacyPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListPharmacy_Fragment extends Fragment implements IPharmacyPresenter.View {


    private ProgressDialog dialog;
    private ListView list;
    private FloatingActionButton fabAddPharmacy;
    public static final String RECOVERY_PHARMACY = "pharmacy";
    private CoordinatorLayout parent;

    private PharmacyAdapter adapter;
    private PharmacyPresenter presenter;

    public ListPharmacy_Fragment() {


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_list_pharmacy, null);
        list = (ListView)rootView.findViewById(R.id.listPharmacy);
        parent = (CoordinatorLayout)rootView.findViewById(R.id.listPharmacyFragment);
        fabAddPharmacy = (FloatingActionButton)rootView.findViewById(R.id.fabAddPharmacy);

        registerForContextMenu(list);

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                //Se coje el item de la posicion del adapter y se le pasa al fragment


                //Se recoge la farmacia modificada y se le pasa al presentador


                return false;
            }
        });

        fabAddPharmacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ManagePharmacy_Fragment fragment = ManagePharmacy_Fragment.newInstance(new Pharmacy());
                fragment.setOnActionFinishedListener(new ManagePharmacy_Fragment.IReturnAction() {
                    @Override
                    public void onActionFinished(Pharmacy pharmacy) {

                        //Se le pasa al presentador la farmacia para que la añada a la lista
                        fabAddPharmacy.setVisibility(View.VISIBLE);
                        getActivity().getSupportFragmentManager().beginTransaction().remove(fragment).commit();

                    }
                });

                fabAddPharmacy.setVisibility(View.GONE);
                getActivity().getSupportFragmentManager().beginTransaction().replace(parent.getId(),
                        fragment).addToBackStack(null).commit();
            }
        });

        presenter = new PharmacyPresenter(this);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new PharmacyAdapter(null, 0, null, null, null, 0);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);

    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.getAllPharmacies();
    }

    @Override
    public void setCursorPharmacy(Cursor cursor) {
        adapter.changeCursor(cursor);
    }
}
