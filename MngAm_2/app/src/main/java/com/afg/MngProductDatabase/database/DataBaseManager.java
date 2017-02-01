package com.afg.MngProductDatabase.database;


import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.os.AsyncTask;
import android.util.Log;

import com.afg.MngProductDatabase.Model.Category;
import com.afg.MngProductDatabase.Model.Pharmacy;
import com.afg.MngProductDatabase.Model.Product;
import com.afg.MngProductDatabase.R;
import com.afg.MngProductDatabase.interfaces.IActionPharmacy;
import com.afg.MngProductDatabase.interfaces.IActionPharmacyAdd;
import com.afg.MngProductDatabase.interfaces.IActionPharmacyDelete;
import com.afg.MngProductDatabase.interfaces.IActionPharmacyLoadAll;
import com.afg.MngProductDatabase.interfaces.IActionPharmacyUpdate;

import java.util.ArrayList;
import java.util.Collections;
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
        params.put(ManageProductContract.ProductEntry.COLUMN_NAME, p.getName());
        params.put(ManageProductContract.ProductEntry.COLUMN_BRAND, p.getBrand());
        params.put(ManageProductContract.ProductEntry.COLUMN_CATEGORY, p.getIdCategory());
        params.put(ManageProductContract.ProductEntry.COLUMN_DESCRIPTION, p.getDescription());
        params.put(ManageProductContract.ProductEntry.COLUMN_DOSAGE, p.getDosage());
        params.put(ManageProductContract.ProductEntry.COLUMN_IMAGE, p.getImage());
        params.put(ManageProductContract.ProductEntry.COLUMN_PRICE, p.getPrice());
        params.put(ManageProductContract.ProductEntry.COLUMN_STOCK, p.getStock());
        String[] whereParams = {String.valueOf(p.getID())};
        database.update(ManageProductContract.ProductEntry.TABLE_NAME,params, "_id = ?", whereParams);
        DataBaseHelper.getInstance().closeDataBase();


    }

    public boolean add(Product p){

        boolean result = false;
        ContentValues params = new ContentValues();
        SQLiteDatabase database = DataBaseHelper.getInstance().openDataBase();
        params.put(ManageProductContract.ProductEntry.COLUMN_NAME, p.getName());
        params.put(ManageProductContract.ProductEntry.COLUMN_BRAND, p.getBrand());
        params.put(ManageProductContract.ProductEntry.COLUMN_CATEGORY, p.getIdCategory());
        params.put(ManageProductContract.ProductEntry.COLUMN_DESCRIPTION, p.getDescription());
        params.put(ManageProductContract.ProductEntry.COLUMN_DOSAGE, p.getDosage());
        params.put(ManageProductContract.ProductEntry.COLUMN_IMAGE, p.getImage());
        params.put(ManageProductContract.ProductEntry.COLUMN_PRICE, p.getPrice());
        params.put(ManageProductContract.ProductEntry.COLUMN_STOCK, p.getStock());
        long id = database.insert(ManageProductContract.ProductEntry.TABLE_NAME,null, params);

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
        Cursor cursor = database.query(ManageProductContract.ProductEntry.TABLE_NAME
                ,ManageProductContract.ProductEntry.ALL_COLUMNS,
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

        //Mostrar en el logo la peazo union de la tabla producto y categoría.

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(ManageProductContract.ProductEntry.PRODUCT_JOIN_CATEGORY);
        cursor = queryBuilder.query(database, ManageProductContract.ProductEntry.COLUMNS_PRODUCT_JOIN_CATEGORY, null,
                null,null,null,null);

        if(cursor.moveToFirst()){

            do{

                Log.e("TAG", cursor.getString(0) +", "+ cursor.getString(1) + " 8===D "+cursor.getString(2));

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
        database.delete(ManageProductContract.ProductEntry.TABLE_NAME, "_id = ?", whereParams);
        DataBaseHelper.getInstance().closeDataBase();


    }

    public Cursor loadCategories(){

        SQLiteDatabase database = DataBaseHelper.getInstance().openDataBase();
        Cursor cursor =  database.query(ManageProductContract.CategoryEntry.TABLE_NAME
                ,ManageProductContract.CategoryEntry.ALL_COLUMS,
                null,null,null,null,null);
      //  DataBaseHelper.getInstance().closeDataBase();
        return cursor;

    }

    public void addPharmacy(final Pharmacy pharmacy, final IActionPharmacyAdd callBcak){

        final ContentValues params = new ContentValues();
        final SQLiteDatabase database = DataBaseHelper.getInstance().openDataBase();
        params.put(ManageProductContract.PharmacyEntry.COLUMN_NAME, pharmacy.getName());
        params.put(ManageProductContract.PharmacyEntry.COLUMN_ADDRESS, pharmacy.getAddress());
        params.put(ManageProductContract.PharmacyEntry.COLUMN_CIF, pharmacy.getCif());
        params.put(ManageProductContract.PharmacyEntry.COLUMN_MAIL, pharmacy.getEmail());
        params.put(ManageProductContract.PharmacyEntry.COLUMN_PHONE, pharmacy.getPhone());

        new AsyncTask<Void, Void, Long>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                callBcak.onPreAction(R.string.action_pre_add);
            }

            @Override
            protected Long doInBackground(Void... voids) {

                return database.insert(ManageProductContract.PharmacyEntry.TABLE_NAME,null, params);
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
        params.put(ManageProductContract.PharmacyEntry.COLUMN_NAME, pharmacy.getName());
        params.put(ManageProductContract.PharmacyEntry.COLUMN_CIF, pharmacy.getCif());
        params.put(ManageProductContract.PharmacyEntry.COLUMN_ADDRESS, pharmacy.getAddress());
        params.put(ManageProductContract.PharmacyEntry.COLUMN_MAIL, pharmacy.getEmail());
        params.put(ManageProductContract.PharmacyEntry.COLUMN_PHONE, pharmacy.getPhone());
        final String[] whereParams = {String.valueOf(pharmacy.getId())};

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                callBack.onPreAction(R.string.action_pre_update);
            }

            @Override
            protected Void doInBackground(Void... voids) {

                database.update(ManageProductContract.PharmacyEntry.TABLE_NAME,params, "_id = ?", whereParams);
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

                database.delete(ManageProductContract.PharmacyEntry.TABLE_NAME, "_id = ?", whereParams);
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
        Cursor cursor =  database.query(ManageProductContract.PharmacyEntry.TABLE_NAME
                ,ManageProductContract.PharmacyEntry.ALL_COLUMNS,
                null,null,null,null,null);
        //  DataBaseHelper.getInstance().closeDataBase();
        return cursor;
    }
}
