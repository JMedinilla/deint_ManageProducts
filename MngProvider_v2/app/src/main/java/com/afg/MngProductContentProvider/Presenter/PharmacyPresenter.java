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

import com.afg.MngProductContentProvider.Model.Pharmacy;
import com.afg.MngProductContentProvider.R;

import com.afg.MngProductContentProvider.database.DataBaseContract;
import com.afg.MngProductContentProvider.interfaces.IActionPharmacyAdd;
import com.afg.MngProductContentProvider.interfaces.IActionPharmacyDelete;
import com.afg.MngProductContentProvider.interfaces.IActionPharmacyUpdate;
import com.afg.MngProductContentProvider.interfaces.IViewPharmacy;
import com.afg.MngProductContentProvider.provider.ManageProductContract;
import com.afg.MngProductContentProvider.provider.ManageProductProvider;

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

        final ContentValues params = new ContentValues();
        params.put(ManageProductContract.Pharmacy.NAME, pharmacy.getName());
        params.put(ManageProductContract.Pharmacy.ADDRESS, pharmacy.getAddress());
        params.put(ManageProductContract.Pharmacy.CIF, pharmacy.getCif());
        params.put(ManageProductContract.Pharmacy.MAIL, pharmacy.getEmail());
        params.put(ManageProductContract.Pharmacy.PHONE, pharmacy.getPhone());

        try {

            viewPharmacy.getContext().getContentResolver().insert(ManageProductContract.Pharmacy.CONTENT_URI, params);

        } catch (SQLException e) {

            viewPharmacy.showMsg(e.getMessage());
            e.printStackTrace();
        }
    }

    public void updatePharmacy(Pharmacy pharmacy){

        final ContentValues params = new ContentValues();
        params.put(ManageProductContract.Pharmacy.NAME, pharmacy.getName());
        params.put(ManageProductContract.Pharmacy.CIF, pharmacy.getCif());
        params.put(ManageProductContract.Pharmacy.ADDRESS, pharmacy.getAddress());
        params.put(ManageProductContract.Pharmacy.MAIL, pharmacy.getEmail());
        params.put(ManageProductContract.Pharmacy.PHONE, pharmacy.getPhone());
        final String[] whereParams = {String.valueOf(pharmacy.getId())};
        viewPharmacy.getContext().getContentResolver().update(ManageProductContract.Pharmacy.CONTENT_URI, params,
                "_id = ?", whereParams);

    }

    public void deletePharmacy(Pharmacy pharmacy){

        ContentValues params = new ContentValues();
        final String[] whereParams = {String.valueOf(pharmacy.getId())};
        viewPharmacy.getContext().getContentResolver().delete(ManageProductContract.Pharmacy.CONTENT_URI,
                "_id = ?", whereParams);


    }

    public void getAllPharmacies(){

        ((Activity)viewPharmacy.getContext()).getLoaderManager().initLoader(PHARMACY, null, this);
    }

    public void reloadPharmacies(){

        ((Activity)viewPharmacy.getContext()).getLoaderManager().restartLoader(PHARMACY, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(viewPharmacy.getContext(), ManageProductContract.Pharmacy.CONTENT_URI,
                ManageProductContract.Pharmacy.PROJECTIONS, null, null, DataBaseContract.PharmacyEntry.DEFAULT_SORT);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

        viewPharmacy.setCursorPharmacy(cursor);
        cursor.setNotificationUri(viewPharmacy.getContext().getContentResolver(), ManageProductContract.Pharmacy.CONTENT_URI);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        viewPharmacy.setCursorPharmacy(null);
    }
}
