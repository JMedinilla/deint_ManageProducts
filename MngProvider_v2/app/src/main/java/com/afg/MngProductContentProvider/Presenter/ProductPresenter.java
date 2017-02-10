package com.afg.MngProductContentProvider.Presenter;

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

import android.app.Activity;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.database.SQLException;
import android.os.AsyncTask;
import android.os.Bundle;

import com.afg.MngProductContentProvider.Model.Product;

import com.afg.MngProductContentProvider.database.DataBaseContract;
import com.afg.MngProductContentProvider.interfaces.IProductPresenter;
import com.afg.MngProductContentProvider.provider.ManageProductContract;

import java.util.List;

import static com.afg.MngProductContentProvider.Fragments.ListProduct_Fragment.p;

/**
 * Created by usuario on 9/12/16.
 */

public class ProductPresenter implements IProductPresenter, LoaderManager.LoaderCallbacks<Cursor>{

    IProductPresenter.View view;
    private final static int PRODUCT=4;

    public ProductPresenter(IProductPresenter.View Vista){
        this.view = Vista;
    }

    /*

    @Override
    public void loadProducts() {
        if(DataBaseManager.getInstance().getProducts().isEmpty())
            view.showEmptyState(true);
        else
            view.showProduct();
    }

    */

    public void loadProducts(){

        ((Activity)view.getContext()).getLoaderManager().initLoader(PRODUCT, null, this);

    }

    public void reloadPharmacies(){

        ((Activity)view.getContext()).getLoaderManager().restartLoader(PRODUCT, null, this);
    }

    @Override
    public Product getProduct(final int id) {

        return null;
    }

    @Override
    public void deleteProduct(final Product product) {


        String[] whereParams = {String.valueOf(product.getID())};
        view.getContext().getContentResolver().delete(ManageProductContract.Product.CONTENT_URI, "_id = ?",
                whereParams);

    }



    public void addProduct(final Product product){

        try {

            ContentValues params = new ContentValues();
            params.put(ManageProductContract.Product.NAME, product.getName());
            params.put(ManageProductContract.Product.BRAND, product.getBrand());
            params.put(ManageProductContract.Product.CATEGORY, product.getIdCategory());
            params.put(ManageProductContract.Product.DESCRIPTION, product.getDescription());
            params.put(ManageProductContract.Product.DOSAGE, product.getDosage());
            params.put(ManageProductContract.Product.IMAGE, product.getImage());
            params.put(ManageProductContract.Product.PRICE, product.getPrice());
            params.put(ManageProductContract.Product.STOCK, product.getStock());
            view.getContext().getContentResolver().insert(ManageProductContract.Product.CONTENT_URI, params);

        } catch (SQLException e) {

            view.showMessage(e.getMessage(), null);
        }

    }

    public void updateProduct(final Product newProduct){

        ContentValues params = new ContentValues();
        params.put(ManageProductContract.Product.NAME, newProduct.getName());
        params.put(ManageProductContract.Product.BRAND, newProduct.getBrand());
        params.put(ManageProductContract.Product.CATEGORY, newProduct.getIdCategory());
        params.put(ManageProductContract.Product.DESCRIPTION, newProduct.getDescription());
        params.put(ManageProductContract.Product.DOSAGE, newProduct.getDosage());
        params.put(ManageProductContract.Product.IMAGE, newProduct.getImage());
        params.put(ManageProductContract.Product.PRICE, newProduct.getPrice());
        params.put(ManageProductContract.Product.STOCK, newProduct.getStock());
        String[] whereParams = {String.valueOf(newProduct.getID())};
        view.getContext().getContentResolver().update(ManageProductContract.Product.CONTENT_URI, params,
                "_id = ?", whereParams);


       // this.addProduct(newProduct);
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }


    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(view.getContext(), ManageProductContract.Product.CONTENT_URI,
                ManageProductContract.Product.PROJECTIONS, null, null, DataBaseContract.ProductEntry.DEFAULT_SORT);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

        view.setCursorPharmacy(cursor);
        cursor.setNotificationUri(view.getContext().getContentResolver(), ManageProductContract.Product.CONTENT_URI);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        view.setCursorPharmacy(null);

    }
}
