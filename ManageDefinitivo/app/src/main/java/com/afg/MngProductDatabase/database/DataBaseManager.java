package com.afg.MngProductDatabase.database;


import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.afg.MngProductDatabase.Model.Category;
import com.afg.MngProductDatabase.Model.Product;
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
        return cursor;

    }
}
