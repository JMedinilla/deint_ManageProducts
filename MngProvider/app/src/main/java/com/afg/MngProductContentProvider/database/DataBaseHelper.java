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

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import com.afg.MngProductContentProvider.ManageProductApplications;


import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by usuario on 20/01/17.
 */

public final class DataBaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "ManageProduct.db";
    private Context context;
    private static volatile DataBaseHelper instance;
    private  AtomicInteger mOpenCounter;
    private SQLiteDatabase mDataBase;

    public synchronized static DataBaseHelper getInstance(){

            if(instance == null){

                instance = new DataBaseHelper();
            }

        return instance;
    }

    public synchronized SQLiteDatabase openDataBase(){

        if(mOpenCounter.incrementAndGet() == 1){

            mDataBase = getWritableDatabase();
        }

        return mDataBase;
    }

    public synchronized void closeDataBase(){

        if(mOpenCounter.decrementAndGet() == 0){

            mDataBase.close();
        }
    }

    private DataBaseHelper() {
        super(ManageProductApplications.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
        this.context = ManageProductApplications.getContext();
        this.mOpenCounter = new AtomicInteger();
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if(!db.isReadOnly()){

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){

                db.setForeignKeyConstraintsEnabled(true);

            }else {

                db.execSQL("PRAGMA foreign_keys = ON");
            }

        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.beginTransaction();

        try {

            sqLiteDatabase.execSQL(DataBaseContract.CategoryEntry.SQL_CREATE_ENTRIES);
            sqLiteDatabase.execSQL(DataBaseContract.ProductEntry.SQL_CREATE_ENTRIES);
            sqLiteDatabase.execSQL(DataBaseContract.PharmacyEntry.SQL_CREATE_ENTRIES);
            sqLiteDatabase.execSQL(DataBaseContract.StatusEntry.SQL_CREATE_ENTRIES);
            sqLiteDatabase.execSQL(DataBaseContract.InvoiceEntry.SQL_CREATE_ENTRIES);
            sqLiteDatabase.execSQL(DataBaseContract.InvoiceLineEntry.SQL_CREATE_ENTRIES);
            sqLiteDatabase.setTransactionSuccessful();
            sqLiteDatabase.execSQL(DataBaseContract.StatusEntry.SQL_INSERT_ENTRIES);

        }catch (SQLiteException ex){


        }finally {
            sqLiteDatabase.endTransaction();
        }



    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, newVersion, oldVersion);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.beginTransaction();

        try {

            sqLiteDatabase.execSQL(DataBaseContract.CategoryEntry.SQL_DELETE_ENTRIES);
            sqLiteDatabase.execSQL(DataBaseContract.ProductEntry.SQL_DELETE_ENTRIES);
            sqLiteDatabase.execSQL(DataBaseContract.PharmacyEntry.SQL_DELETE_ENTRIES);
            sqLiteDatabase.execSQL(DataBaseContract.StatusEntry.SQL_DELETE_ENTRIES);
            sqLiteDatabase.execSQL(DataBaseContract.InvoiceEntry.SQL_DELETE_ENTRIES);
            sqLiteDatabase.execSQL(DataBaseContract.InvoiceLineEntry.SQL_DELETE_ENTRIES);
            onCreate(sqLiteDatabase);
            sqLiteDatabase.setTransactionSuccessful();


        }catch (SQLiteException ex){


        }finally {

            sqLiteDatabase.endTransaction();
        }
    }

    public SQLiteDatabase open(){

        return getWritableDatabase();
    }
}
