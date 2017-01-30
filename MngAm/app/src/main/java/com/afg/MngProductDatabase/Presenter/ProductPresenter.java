package com.afg.MngProductDatabase.Presenter;

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

import android.os.AsyncTask;

import com.afg.MngProductDatabase.Model.Product;
import com.afg.MngProductDatabase.database.DataBaseManager;
import com.afg.MngProductDatabase.interfaces.IProductPresenter;

import java.util.List;

/**
 * Created by usuario on 9/12/16.
 */

public class ProductPresenter implements IProductPresenter{

    View view;

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

        view.showProgressDialog();
        new AsyncTask<Void, Void, List<Product>>(){
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                view.showProgressDialog();
            }

            @Override
            protected List<Product> doInBackground(Void... voids) {
                try{
                    Thread.sleep(1800);
                } catch (Exception ex){

                }

                return DataBaseManager.getInstance().getProducts();
            }

            @Override
            protected void onPostExecute(List<Product> products) {
                view.showProduct();
                view.dismissProgressDialog();
            }

            @Override
            protected void onCancelled() {
                super.onCancelled();
                view.showEmptyState(true);
                view.dismissProgressDialog();
            }
        }.execute();
    }

    @Override
    public Product getProduct(final int id) {

        final Product[] p = {new Product()};

        new AsyncTask<Void, Void, Product>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                view.showProgressDialog();
            }

            @Override
            protected Product doInBackground(Void... voids) {

                return DataBaseManager.getInstance().getProducts().get(id);
            }

            @Override
            protected void onPostExecute(Product product) {

                p[0] = product;
                view.dismissProgressDialog();

            }



            @Override
            protected void onCancelled() {
                super.onCancelled();
                view.showProduct();
                view.dismissProgressDialog();
            }
        }.execute();

        return p[0];
    }

    @Override
    public void deleteProduct(final Product product) {

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                view.showProgressDialog();
            }

            @Override
            protected Void doInBackground(Void... voids) {


                DataBaseManager.getInstance().deleteProduct(product);
                return null;
            }


            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                view.dismissProgressDialog();
            }

            @Override
            protected void onCancelled() {
                super.onCancelled();
                view.dismissProgressDialog();
            }
        }.execute();

        //Vuelve a cargar los productos y actualiza los productos.
        view.showMessage("Product Delete", product);
        loadProducts();
    }



    public void addProduct(final Product product){

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                view.showProgressDialog();
            }

            @Override
            protected Void doInBackground(Void... voids) {

                DataBaseManager.getInstance().add(product);
                view.showProduct();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                view.dismissProgressDialog();
            }

            @Override
            protected void onCancelled() {
                super.onCancelled();
                view.dismissProgressDialog();
            }
        }.execute();


    }

    public void updateProduct(final Product newProduct){

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                view.showProgressDialog();
            }

            @Override
            protected void onCancelled() {

                view.dismissProgressDialog();
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                view.dismissProgressDialog();
            }

            @Override
            protected Void doInBackground(Void... voids) {
                DataBaseManager.getInstance().updateProduct(newProduct);
                view.showProduct();
                return null;
            }
        }.execute();


       // this.addProduct(newProduct);
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }


}
