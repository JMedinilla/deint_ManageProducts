package com.afg.MngProductContentProvider.database;


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

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.os.AsyncTask;
import android.util.Log;

import com.afg.MngProductContentProvider.Model.Category;
import com.afg.MngProductContentProvider.Model.Invoice;
import com.afg.MngProductContentProvider.Model.InvoiceLine;
import com.afg.MngProductContentProvider.Model.Pharmacy;
import com.afg.MngProductContentProvider.Model.Product;
import com.afg.MngProductContentProvider.R;
import com.afg.MngProductContentProvider.interfaces.IActionPharmacyAdd;
import com.afg.MngProductContentProvider.interfaces.IActionPharmacyDelete;
import com.afg.MngProductContentProvider.interfaces.IActionPharmacyUpdate;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by usuario on 23/01/17.
 */

public class DataBaseManager {


    private static DataBaseManager instance;

    public static DataBaseManager getInstance(){

        if(instance == null){

            instance = new DataBaseManager();
        }

        return instance;
    }

    private DataBaseManager(){


    }

    public void updateProduct(Product p){


        boolean result = false;
        ContentValues params = new ContentValues();
        SQLiteDatabase database = DataBaseHelper.getInstance().openDataBase();
        params.put(DataBaseContract.ProductEntry.COLUMN_NAME, p.getName());
        params.put(DataBaseContract.ProductEntry.COLUMN_BRAND, p.getBrand());
        params.put(DataBaseContract.ProductEntry.CATEGORY_ID, p.getIdCategory());
        params.put(DataBaseContract.ProductEntry.COLUMN_DESCRIPTION, p.getDescription());
        params.put(DataBaseContract.ProductEntry.COLUMN_DOSAGE, p.getDosage());
        params.put(DataBaseContract.ProductEntry.COLUMN_IMAGE, p.getImage());
        params.put(DataBaseContract.ProductEntry.COLUMN_PRICE, p.getPrice());
        params.put(DataBaseContract.ProductEntry.COLUMN_STOCK, p.getStock());
        String[] whereParams = {String.valueOf(p.getID())};
        database.update(DataBaseContract.ProductEntry.TABLE_NAME,params, "_id = ?", whereParams);
        DataBaseHelper.getInstance().closeDataBase();


    }

    public boolean add(Product p){

        boolean result = false;
        ContentValues params = new ContentValues();
        SQLiteDatabase database = DataBaseHelper.getInstance().openDataBase();
        params.put(DataBaseContract.ProductEntry.COLUMN_NAME, p.getName());
        params.put(DataBaseContract.ProductEntry.COLUMN_BRAND, p.getBrand());
        params.put(DataBaseContract.ProductEntry.CATEGORY_ID, p.getIdCategory());
        params.put(DataBaseContract.ProductEntry.COLUMN_DESCRIPTION, p.getDescription());
        params.put(DataBaseContract.ProductEntry.COLUMN_DOSAGE, p.getDosage());
        params.put(DataBaseContract.ProductEntry.COLUMN_IMAGE, p.getImage());
        params.put(DataBaseContract.ProductEntry.COLUMN_PRICE, p.getPrice());
        params.put(DataBaseContract.ProductEntry.COLUMN_STOCK, p.getStock());
        long id = database.insert(DataBaseContract.ProductEntry.TABLE_NAME,null, params);

        if(id != -1){

            p.setID(id);
            result = true;
        }

        DataBaseHelper.getInstance().closeDataBase();
        return result;
    }

    public List<Product> getProducts(){

        ArrayList<Product> list = new ArrayList<Product>();
        Product product;
        SQLiteDatabase database = DataBaseHelper.getInstance().openDataBase();
        Cursor cursor = database.query(DataBaseContract.ProductEntry.TABLE_NAME
                ,DataBaseContract.ProductEntry.ALL_COLUMNS,
                null,null,null,null,null);

        if(cursor.moveToFirst()){

            do{

                product = new Product();
                product.setID(cursor.getLong(0));
                product.setName(cursor.getString(1));
                product.setBrand(cursor.getString(2));
                product.setIdCategory(cursor.getInt(3));
                product.setDescription(cursor.getString(4));
                product.setDosage(cursor.getString(5));
                product.setImage(cursor.getString(6));
                product.setPrice(cursor.getDouble(7));
                product.setStock(cursor.getString(8));
                list.add(product);

            }while (cursor.moveToNext());
        }

        cursor.close();

        //Mostrar en el log la peazo union de la tabla producto y categoría.
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(DataBaseContract.ProductEntry.PRODUCT_JOIN_CATEGORY);
        cursor = queryBuilder.query(database, DataBaseContract.ProductEntry.COLUMNS_PRODUCT_JOIN_CATEGORY, null,
                null,null,null,null);

        if(cursor.moveToFirst()){

            do{

                Log.e("TAG", cursor.getString(0) +", "+ cursor.getString(1) + " 8===D "+cursor.getString(2) + "//// "+cursor.getInt(3));

            }while (cursor.moveToNext());
        }

        cursor.close();


        DataBaseHelper.getInstance().closeDataBase();
        return list;

    }

    public  void deleteProduct(Product p){

        ContentValues params = new ContentValues();
        String[] whereParams = {String.valueOf(p.getID())};
        SQLiteDatabase database = DataBaseHelper.getInstance().openDataBase();
        database.delete(DataBaseContract.ProductEntry.TABLE_NAME, "_id = ?", whereParams);
        DataBaseHelper.getInstance().closeDataBase();


    }

    public Cursor loadCategories(){

        SQLiteDatabase database = DataBaseHelper.getInstance().openDataBase();
        Cursor cursor =  database.query(DataBaseContract.CategoryEntry.TABLE_NAME
                ,DataBaseContract.CategoryEntry.ALL_COLUMS,
                null,null,null,null,null);
      //  DataBaseHelper.getInstance().closeDataBase();
        return cursor;

    }

    public void addPharmacy(final Pharmacy pharmacy, final IActionPharmacyAdd callBcak){

        final ContentValues params = new ContentValues();
        final SQLiteDatabase database = DataBaseHelper.getInstance().openDataBase();
        params.put(DataBaseContract.PharmacyEntry.COLUMN_NAME, pharmacy.getName());
        params.put(DataBaseContract.PharmacyEntry.COLUMN_ADDRESS, pharmacy.getAddress());
        params.put(DataBaseContract.PharmacyEntry.COLUMN_CIF, pharmacy.getCif());
        params.put(DataBaseContract.PharmacyEntry.COLUMN_MAIL, pharmacy.getEmail());
        params.put(DataBaseContract.PharmacyEntry.COLUMN_PHONE, pharmacy.getPhone());

        new AsyncTask<Void, Void, Long>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                callBcak.onPreAction(R.string.action_pre_add);
            }

            @Override
            protected Long doInBackground(Void... voids) {

                return database.insert(DataBaseContract.PharmacyEntry.TABLE_NAME,null, params);
            }

            @Override
            protected void onPostExecute(Long aLong) {
                super.onPostExecute(aLong);
                pharmacy.setId(aLong.longValue());
                DataBaseHelper.getInstance().closeDataBase();
                callBcak.onAddPharmacy(pharmacy);
            }

            @Override
            protected void onCancelled() {
                super.onCancelled();
                callBcak.onFailAction(R.string.action_cancelled);
                DataBaseHelper.getInstance().closeDataBase();
            }
        }.execute();

    }

    public void updatePharmacy(final Pharmacy pharmacy, final IActionPharmacyUpdate callBack){

        final ContentValues params = new ContentValues();
        final SQLiteDatabase database = DataBaseHelper.getInstance().openDataBase();
        params.put(DataBaseContract.PharmacyEntry.COLUMN_NAME, pharmacy.getName());
        params.put(DataBaseContract.PharmacyEntry.COLUMN_CIF, pharmacy.getCif());
        params.put(DataBaseContract.PharmacyEntry.COLUMN_ADDRESS, pharmacy.getAddress());
        params.put(DataBaseContract.PharmacyEntry.COLUMN_MAIL, pharmacy.getEmail());
        params.put(DataBaseContract.PharmacyEntry.COLUMN_PHONE, pharmacy.getPhone());
        final String[] whereParams = {String.valueOf(pharmacy.getId())};

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                callBack.onPreAction(R.string.action_pre_update);
            }

            @Override
            protected Void doInBackground(Void... voids) {

                database.update(DataBaseContract.PharmacyEntry.TABLE_NAME,params, "_id = ?", whereParams);
                return null;
            }

            @Override
            protected void onPostExecute(Void avoid) {
                super.onPostExecute(avoid);
                DataBaseHelper.getInstance().closeDataBase();
                callBack.onUpdatePharmacy(pharmacy);;
            }

            @Override
            protected void onCancelled() {
                super.onCancelled();
                DataBaseHelper.getInstance().closeDataBase();
                callBack.onFailAction(R.string.action_cancelled);
            }
        }.execute();

    }

    public void deletePharmacy(final Pharmacy pharmacy, final IActionPharmacyDelete callBack){

        ContentValues params = new ContentValues();
        final String[] whereParams = {String.valueOf(pharmacy.getId())};
        final SQLiteDatabase database = DataBaseHelper.getInstance().openDataBase();
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                callBack.onPreAction(R.string.action_deleting);
            }

            @Override
            protected Void doInBackground(Void... voids) {

                database.delete(DataBaseContract.PharmacyEntry.TABLE_NAME, "_id = ?", whereParams);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                DataBaseHelper.getInstance().closeDataBase();
                callBack.onDeletePharmacy(pharmacy);
            }

            @Override
            protected void onCancelled() {
                super.onCancelled();
                callBack.onFailAction(R.string.action_cancelled);
            }
        }.execute();
    }

    public Cursor loadPharmacies(){

        SQLiteDatabase database = DataBaseHelper.getInstance().openDataBase();
        Cursor cursor =  database.query(DataBaseContract.PharmacyEntry.TABLE_NAME
                ,DataBaseContract.PharmacyEntry.ALL_COLUMNS,
                null,null,null,null,null);
        //  DataBaseHelper.getInstance().closeDataBase();
        return cursor;
    }

    public void addCategoty(Category category){

        final ContentValues params = new ContentValues();
        final SQLiteDatabase database = DataBaseHelper.getInstance().openDataBase();
        params.put(DataBaseContract.CategoryEntry.COLUMN_NAME, category.getName());
        category.setId(database.insert(DataBaseContract.CategoryEntry.TABLE_NAME, null, params));
        DataBaseHelper.getInstance().closeDataBase();


    }

    public void updateCategory(Category category){

        final ContentValues params = new ContentValues();
        final SQLiteDatabase database = DataBaseHelper.getInstance().openDataBase();
        final String[] whereParams = {String.valueOf(category.getId())};
        params.put(DataBaseContract.CategoryEntry.COLUMN_NAME, category.getName());
        database.update(DataBaseContract.CategoryEntry.TABLE_NAME, params, "_id = ?", whereParams);
        DataBaseHelper.getInstance().closeDataBase();
    }

    public void deleteCategory(Category category){

        final ContentValues params = new ContentValues();
        final SQLiteDatabase database = DataBaseHelper.getInstance().openDataBase();
        final String[] whereParams = {String.valueOf(category.getId())};
        database.delete(DataBaseContract.CategoryEntry.TABLE_NAME, "_id = ?", whereParams);
        DataBaseHelper.getInstance().closeDataBase();

    }

    public Cursor getAllInvoices(){

        Cursor cursor = null;
        SQLiteDatabase database = DataBaseHelper.getInstance().openDataBase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(DataBaseContract.InvoiceEntry.IN_PHARMACY_JOIN_PHARMACY);
        cursor = queryBuilder.query(database, DataBaseContract.InvoiceEntry.COLUMNS_JOIN_PHARMACY_STATUS, null,
                null, null, null, null);
        return cursor;
    }

    public Cursor getAllInvoiceLineByIdInvoice(int idInvoice){

        Cursor cursor = null;
        SQLiteDatabase database = DataBaseHelper.getInstance().openDataBase();
        String[] whereParams = {String.valueOf(idInvoice)};



        return cursor;
    }

    public void addInvoice(Invoice invoice){

        ContentValues params = new ContentValues();
        SQLiteDatabase database = DataBaseHelper.getInstance().openDataBase();
        params.put(DataBaseContract.InvoiceEntry.COLUMN_IN_DATE, invoice.getDate());
        params.put(DataBaseContract.InvoiceEntry.PHARMACY_ID, invoice.getIdPharmacy());
        params.put(DataBaseContract.InvoiceEntry.STATUS_ID, invoice.getStatus());
        invoice.setId((int) database.insert(DataBaseContract.InvoiceEntry.TABLE_NAME, null, params));
        DataBaseHelper.getInstance().closeDataBase();

        for(InvoiceLine tmp: invoice.getLines()){

            tmp.setIdInvoice(invoice.getId());
            addInvoiceLine(tmp);
        }


    }

    public void updateInvoice(Invoice invoice){

        SQLiteDatabase database = DataBaseHelper.getInstance().openDataBase();
        ContentValues params = new ContentValues();
        params.put(DataBaseContract.InvoiceEntry.COLUMN_IN_DATE, invoice.getDate());
        params.put(DataBaseContract.InvoiceEntry.PHARMACY_ID, invoice.getIdPharmacy());
        params.put(DataBaseContract.InvoiceEntry.STATUS_ID, invoice.getStatus());
        String[] whereParams = {String.valueOf(DataBaseContract.InvoiceEntry._ID)};
        database.update(DataBaseContract.InvoiceEntry.TABLE_NAME, params, "_id = ?", whereParams);
        DataBaseHelper.getInstance().closeDataBase();
    }

    public void updateInvoiceLine(InvoiceLine invoiceLine){

        ContentValues params = new ContentValues();
        SQLiteDatabase database = DataBaseHelper.getInstance().openDataBase();
        params.put(DataBaseContract.InvoiceLineEntry.COLUMN_IL_AMOUNT, invoiceLine.getAmount());
        params.put(DataBaseContract.InvoiceLineEntry.INVOICE_ID, invoiceLine.getIdInvoice());
        params.put(DataBaseContract.InvoiceLineEntry.COLUMN_IL_ORDER_PRODUCT, invoiceLine.getOrderProduct());
        params.put(DataBaseContract.InvoiceLineEntry.COLUMN_IL_PRICE, invoiceLine.getPrice());
        params.put(DataBaseContract.InvoiceLineEntry.PRODUCT_ID, invoiceLine.getIdProduct());
        String[] whereParams = {String.valueOf(invoiceLine.getIdInvoice()), String.valueOf(invoiceLine.getIdInvoice())};
        database.update(DataBaseContract.InvoiceLineEntry.TABLE_NAME, params,
                DataBaseContract.InvoiceLineEntry.INVOICE_ID + "= ? AND"+
        DataBaseContract.InvoiceLineEntry.COLUMN_IL_ORDER_PRODUCT + "= ?", whereParams);
        DataBaseHelper.getInstance().closeDataBase();

    }

    public void addInvoiceLine(InvoiceLine invoiceLine){

        ContentValues params = new ContentValues();
        SQLiteDatabase database = DataBaseHelper.getInstance().openDataBase();
        params.put(DataBaseContract.InvoiceLineEntry.COLUMN_IL_AMOUNT, invoiceLine.getAmount());
        params.put(DataBaseContract.InvoiceLineEntry.INVOICE_ID, invoiceLine.getIdInvoice());
        params.put(DataBaseContract.InvoiceLineEntry.COLUMN_IL_ORDER_PRODUCT, invoiceLine.getOrderProduct());
        params.put(DataBaseContract.InvoiceLineEntry.COLUMN_IL_PRICE, invoiceLine.getPrice());
        params.put(DataBaseContract.InvoiceLineEntry.PRODUCT_ID, invoiceLine.getIdProduct());
        database.insert(DataBaseContract.InvoiceLineEntry.TABLE_NAME, null, params);
        DataBaseHelper.getInstance().closeDataBase();

    }

    public void deleteInvoice(Invoice invoice){

        for(InvoiceLine tmp: invoice.getLines()){

            deleteInvoiceLine(tmp);
        }

        SQLiteDatabase database = DataBaseHelper.getInstance().openDataBase();
        String[] whereParams = {String.valueOf(DataBaseContract.InvoiceEntry._ID)};
        database.delete(DataBaseContract.InvoiceEntry.TABLE_NAME, "_id = ?", whereParams);
        DataBaseHelper.getInstance().closeDataBase();

    }

    public void deleteInvoiceLine(InvoiceLine invoiceLine){

        SQLiteDatabase database = DataBaseHelper.getInstance().openDataBase();
        ContentValues params = new ContentValues();
        String[] whereParams = {String.valueOf(invoiceLine.getIdInvoice()), String.valueOf(invoiceLine.getIdInvoice())};
        database.delete(DataBaseContract.InvoiceLineEntry.TABLE_NAME,
                DataBaseContract.InvoiceLineEntry.INVOICE_ID + "= ? AND"+
                        DataBaseContract.InvoiceLineEntry.COLUMN_IL_ORDER_PRODUCT + "= ?", whereParams);
        DataBaseHelper.getInstance().closeDataBase();

    }

}
