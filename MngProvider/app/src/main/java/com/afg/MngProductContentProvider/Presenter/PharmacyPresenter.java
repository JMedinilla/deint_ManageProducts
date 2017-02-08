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
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;

import com.afg.MngProductContentProvider.Model.Pharmacy;
import com.afg.MngProductContentProvider.R;
import com.afg.MngProductContentProvider.cursor.PharmacyCursorLoader;
import com.afg.MngProductContentProvider.database.DataBaseManager;
import com.afg.MngProductContentProvider.interfaces.IActionPharmacyAdd;
import com.afg.MngProductContentProvider.interfaces.IActionPharmacyDelete;
import com.afg.MngProductContentProvider.interfaces.IActionPharmacyUpdate;
import com.afg.MngProductContentProvider.interfaces.IViewPharmacy;

/**
 * Created by usuario on 30/01/17.
 */

public class PharmacyPresenter implements LoaderManager.LoaderCallbacks<Cursor> {


    private IViewPharmacy viewPharmacy;
    private final static int PHARMACY=2;


    public PharmacyPresenter(IViewPharmacy viewPharmacy){

        this.viewPharmacy = viewPharmacy;
    }

    public void addPharmacy(Pharmacy pharmacy){

        DataBaseManager.getInstance().addPharmacy(pharmacy, new IActionPharmacyAdd() {
            @Override
            public void onAddPharmacy(Pharmacy pharmacy) {

                viewPharmacy.actionOK(R.string.action_pharmacy_add);
                reloadPharmacies();
            }

            @Override
            public void onPreAction(int codeMsg) {

                viewPharmacy.startProgress(codeMsg);
            }

            @Override
            public void onFailAction(int codeMsg) {

                viewPharmacy.failAction(codeMsg);
            }
        });
    }

    public void updatePharmacy(Pharmacy pharmacy){

        DataBaseManager.getInstance().updatePharmacy(pharmacy, new IActionPharmacyUpdate() {
            @Override
            public void onUpdatePharmacy(Pharmacy pharmacy) {

                viewPharmacy.actionOK(R.string.action_update_pharmacy_ok);
                reloadPharmacies();
            }

            @Override
            public void onPreAction(int codeMsg) {

                viewPharmacy.startProgress(codeMsg);
            }

            @Override
            public void onFailAction(int codeMsg) {

                viewPharmacy.failAction(codeMsg);
            }
        });
    }

    public void deletePharmacy(Pharmacy pharmacy){

        DataBaseManager.getInstance().deletePharmacy(pharmacy, new IActionPharmacyDelete() {
            @Override
            public void onDeletePharmacy(Pharmacy pharmacy) {

                viewPharmacy.actionOK(R.string.action_delete_pharmacy_ok);
                reloadPharmacies();
            }

            @Override
            public void onPreAction(int codeMsg) {

                viewPharmacy.startProgress(codeMsg);
            }

            @Override
            public void onFailAction(int codeMsg) {

                viewPharmacy.failAction(codeMsg);
            }
        });
    }

    public void getAllPharmacies(){

        ((Activity)viewPharmacy.getContext()).getLoaderManager().initLoader(PHARMACY, null, this);
    }

    public void reloadPharmacies(){

        ((Activity)viewPharmacy.getContext()).getLoaderManager().restartLoader(PHARMACY, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new PharmacyCursorLoader(viewPharmacy.getContext());
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

        viewPharmacy.setCursorPharmacy(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        viewPharmacy.setCursorPharmacy(null);
    }
}
