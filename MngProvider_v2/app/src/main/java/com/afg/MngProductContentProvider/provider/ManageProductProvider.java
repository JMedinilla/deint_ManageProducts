package com.afg.MngProductContentProvider.provider;

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

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.afg.MngProductContentProvider.R;
import com.afg.MngProductContentProvider.database.DataBaseContract;
import com.afg.MngProductContentProvider.database.DataBaseHelper;

public class ManageProductProvider extends ContentProvider {

    private static final int PRODUCT = 1;
    private static final int PRODUCT_ID = 2;
    private static final int PHARMACY = 3;
    private static final int PHARMACY_ID = 4;
    private static final int INVOICE = 5;
    private static final int INVOICE_ID = 6;
    private static final int CATEGORY = 7;
    private static final int CATEGORY_ID = 8;
    private static final int INVOICELINE = 9;
    private static final int INVOICELINE_ID = 10;
    private static final int STATUS = 11;
    private static final int STATUS_ID = 12;
    private SQLiteDatabase database;

    private static final UriMatcher uriMacher = new UriMatcher(UriMatcher.NO_MATCH);

    static {

        uriMacher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.Product.CONTENT_PATH, PRODUCT);
        uriMacher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.Product.CONTENT_PATH+"/#", PRODUCT_ID);
        uriMacher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.Pharmacy.CONTENT_PATH, PHARMACY);
        uriMacher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.Pharmacy.CONTENT_PATH+"/#", PHARMACY_ID);
        uriMacher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.Invoice.CONTENT_PATH, INVOICE);
        uriMacher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.Invoice.CONTENT_PATH+"/#", INVOICE_ID);
        uriMacher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.Category.CONTENT_PATH, CATEGORY);
        uriMacher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.Category.CONTENT_PATH+"/#", CATEGORY_ID);
        uriMacher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.InvoiceLine.CONTENT_PATH, INVOICELINE);
        uriMacher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.InvoiceLine.CONTENT_PATH+"/#", INVOICELINE_ID);
        uriMacher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.Status.CONTENT_PATH, STATUS);
        uriMacher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.Status.CONTENT_PATH+"/#", STATUS_ID);
    }

    @Override
    public boolean onCreate() {

        database = DataBaseHelper.getInstance().openDataBase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] proyection, String selection, String[] selectionArgs, String order) {

        Cursor cursor = null;
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();

        switch (uriMacher.match(uri)){

            case CATEGORY:
                builder.setTables(DataBaseContract.CategoryEntry.TABLE_NAME);
                if(!TextUtils.isEmpty(order)){

                    order = DataBaseContract.CategoryEntry.DEFAULT_SORT;
                }
                cursor = builder.query(database, proyection, selection, selectionArgs,null, null, order);
                break;
            case CATEGORY_ID:
                break;
            case PRODUCT:
                builder.setTables(DataBaseContract.ProductEntry.TABLE_NAME);
           //     builder.setProjectionMap(ManageProductContract.Product.productProjectionMaps);
                if(!TextUtils.isEmpty(order)){

                    order = DataBaseContract.ProductEntry.DEFAULT_SORT;
                }
                cursor = builder.query(database, proyection, selection, selectionArgs,null, null, order);
                break;
            case PRODUCT_ID:
                break;
            case PHARMACY:
                builder.setTables(DataBaseContract.PharmacyEntry.TABLE_NAME);
                if(!TextUtils.isEmpty(order)){

                    order = DataBaseContract.PharmacyEntry.DEFAULT_SORT;
                }
                cursor = builder.query(database, proyection, selection, selectionArgs,null, null, order);
                break;
            case PHARMACY_ID:
                break;
            case INVOICE:
                builder.setTables(selection);
                if(!TextUtils.isEmpty(order)){

                    order = DataBaseContract.InvoiceEntry.DEFAULT_SORT;
                }
                cursor = builder.query(database, null, null, null,null, null, order);
                break;
            case INVOICE_ID:
                break;
            case STATUS:
                break;
            case STATUS_ID:
                break;
            case INVOICELINE:
                break;
            case INVOICELINE_ID:
                break;
            case UriMatcher.NO_MATCH:
                throw new IllegalArgumentException("ME LA COMES CHAVAL");

        }

        String sqlQuery = builder.buildQuery(proyection, selection, null, null, order, null);
        Log.i("TAG", sqlQuery);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {

        Uri laUri = null;
        long id = -1;


        switch (uriMacher.match(uri)){

            case CATEGORY:
                id = database.insert(DataBaseContract.CategoryEntry.TABLE_NAME, null, contentValues);
                laUri = ContentUris.withAppendedId(uri, id);
                break;
            case PRODUCT:
                id = database.insert(DataBaseContract.ProductEntry.TABLE_NAME, null, contentValues);
                laUri = ContentUris.withAppendedId(uri, id);
                break;
            case PHARMACY:
                id = database.insert(DataBaseContract.PharmacyEntry.TABLE_NAME, null, contentValues);
                laUri = ContentUris.withAppendedId(uri, id);
                break;
        }

        if(id !=  -1){

            getContext().getContentResolver().notifyChange(laUri, null);

        }else {

            throw new SQLException(getContext().getString(R.string.error_insert));
        }

        return uri;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {



        int result = 0;

        switch (uriMacher.match(uri)) {

            case CATEGORY:
                result = database.delete(DataBaseContract.CategoryEntry.TABLE_NAME, s, strings);
                break;
            case PRODUCT:
                result = database.delete(DataBaseContract.ProductEntry.TABLE_NAME, s, strings);
                break;
            case PHARMACY:
                result = database.delete(DataBaseContract.PharmacyEntry.TABLE_NAME, s, strings);
                break;
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return result;

    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        Uri laUri = null;
        int result = -1;
        String rowID;

        switch (uriMacher.match(uri)) {

            case CATEGORY:
                result = database.update(DataBaseContract.CategoryEntry.TABLE_NAME, contentValues, s, strings);
                break;
            case CATEGORY_ID:
                //rowID = uri.getLastPathSegment();
                //content://package/category/1
                rowID = uri.getPathSegments().get(1);
                s = DataBaseContract.CategoryEntry._ID + "=?";
                strings = new String[] { rowID };
                result = database.update(DataBaseContract.CategoryEntry.TABLE_NAME, contentValues, s, strings);
                break;
            case PRODUCT:
                result = database.update(DataBaseContract.ProductEntry.TABLE_NAME, contentValues, s, strings);
                break;
            case PRODUCT_ID:
                rowID = uri.getPathSegments().get(1);
                s = DataBaseContract.ProductEntry._ID + "=?";
                strings = new String[] { rowID };
                result = database.update(DataBaseContract.ProductEntry.TABLE_NAME, contentValues, s, strings);
                break;
            case PHARMACY:
                result = database.update(DataBaseContract.PharmacyEntry.TABLE_NAME, contentValues, s, strings);
                break;
            case PHARMACY_ID:
                rowID = uri.getPathSegments().get(1);
                s = DataBaseContract.PharmacyEntry._ID + "=?";
                strings = new String[] { rowID };
                result = database.update(DataBaseContract.PharmacyEntry.TABLE_NAME, contentValues, s, strings);
                break;
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return result;

    }
}
