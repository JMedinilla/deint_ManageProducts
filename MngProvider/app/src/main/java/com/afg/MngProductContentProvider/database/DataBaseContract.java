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

import android.provider.BaseColumns;



/**
 * Created by usuario on 6/02/17.
 */

public class DataBaseContract {

    public static class CategoryEntry implements BaseColumns {

        public static final String TABLE_NAME = "category";
        public static final String COLUMN_NAME = "ca_name";
        public static final String SQL_CREATE_ENTRIES = String.format("CREATE TABLE %s (%s " +
                        "INTEGER PRIMARY KEY AUTOINCREMENT," + "%s TEXT NOT NULL)", TABLE_NAME,
                BaseColumns._ID, COLUMN_NAME);
        public static final String[] ALL_COLUMS = {String.valueOf(_ID), COLUMN_NAME};
        public static final String SQL_DELETE_ENTRIES = String.format("DROP TABLE IF EXISTS %s",
                TABLE_NAME);

    }

    public static class ProductEntry implements BaseColumns{

        public static final String TABLE_NAME = "product";
        public static final String COLUMN_NAME = "pr_name";
        public static final String COLUMN_DESCRIPTION = "pr_description";
        public static final String COLUMN_BRAND = "pr_brand";
        public static final String COLUMN_DOSAGE = "pr_dosage";
        public static final String COLUMN_PRICE = "pr_price";
        public static final String COLUMN_STOCK = "pr_stock";
        public static final String COLUMN_IMAGE = "pr_image";
        public static final String CATEGORY_ID = "category_id";
        public static final String PRODUCT_JOIN_CATEGORY = String.format("%s p INNER JOIN %s c ON p.%s = c.%s", TABLE_NAME,
                DataBaseContract.CategoryEntry.TABLE_NAME, CATEGORY_ID, DataBaseContract.CategoryEntry._ID);
        public static final String[] COLUMNS_PRODUCT_JOIN_CATEGORY = new String[] {

                COLUMN_NAME,
                COLUMN_DESCRIPTION,
                DataBaseContract.CategoryEntry.COLUMN_NAME,
                "p."+_ID

        };
        public static final String[] ALL_COLUMNS = {DataBaseContract.ProductEntry._ID, DataBaseContract.ProductEntry.COLUMN_NAME,
                DataBaseContract.ProductEntry.COLUMN_BRAND, ProductEntry.CATEGORY_ID,
                DataBaseContract.ProductEntry.COLUMN_DESCRIPTION, DataBaseContract.ProductEntry.COLUMN_DOSAGE,
                DataBaseContract.ProductEntry.COLUMN_IMAGE, DataBaseContract.ProductEntry.COLUMN_PRICE,
                DataBaseContract.ProductEntry.COLUMN_STOCK};
        public static final String REFERENCE_ID_CATEGORY = String.format("REFERENCES %s (%s) ON UPDATE CASCADE ON" +
                " DELETE RESTRICT",  DataBaseContract.CategoryEntry.TABLE_NAME, DataBaseContract.CategoryEntry._ID);
        public static final String SQL_CREATE_ENTRIES = String.format("CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s TEXT NOT NULL," +
                        "%s TEXT NOT NULL," +
                        "%s TEXT NOT NULL," +
                        "%s TEXT NOT NULL," +
                        "%s REAL NOT NULL," +
                        "%s INTEGER NOT NULL," +
                        "%s TEXT NOT NULL," +
                        "%s INTEGER NOT NULL %s)",
                TABLE_NAME,
                _ID,
                COLUMN_NAME,
                COLUMN_DESCRIPTION,
                COLUMN_BRAND,
                COLUMN_DOSAGE,
                COLUMN_PRICE,
                COLUMN_STOCK,
                COLUMN_IMAGE,
                CATEGORY_ID,
                REFERENCE_ID_CATEGORY
        );

        public static final String SQL_DELETE_ENTRIES = String.format("DROP TABLE IF EXISTS %s",
                TABLE_NAME);

    }

    public static class PharmacyEntry implements BaseColumns{

        public static final String TABLE_NAME = "pharmacy";
        public static final String COLUMN_NAME = "ph_name";
        public static final String COLUMN_CIF = "ph_cif";
        public static final String COLUMN_ADDRESS = "ph_address";
        public static final String COLUMN_PHONE = "ph_phone";
        public static final String COLUMN_MAIL = "pr_mail";
        public static final String[] ALL_COLUMNS = {_ID, COLUMN_NAME, COLUMN_CIF, COLUMN_ADDRESS, COLUMN_PHONE,
                COLUMN_MAIL};
        public static final String SQL_CREATE_ENTRIES = String.format("CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT NOT NULL,"+
                        "%s TEXT NOT NULL," +
                        "%s TEXT NOT NULL," +
                        "%s TEXT NOT NULL," +
                        "%s TEXT NOT NULL)",
                TABLE_NAME,
                _ID,
                COLUMN_NAME,
                COLUMN_CIF,
                COLUMN_ADDRESS,
                COLUMN_PHONE,
                COLUMN_MAIL
        );
        public static final String SQL_DELETE_ENTRIES = String.format("DROP TABLE IF EXISTS %s",
                TABLE_NAME);

    }

    public static class StatusEntry implements BaseColumns{

        public static final String TABLE_NAME = "status";
        public static final String COLUMN_NAME = "st_name";
        public static final String SQL_CREATE_ENTRIES = String.format("CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT NOT NULL)",
                TABLE_NAME,
                _ID,
                COLUMN_NAME
        );
        public static final String SQL_INSERT_ENTRIES = String.format("INSERT INTO %s VALUES (1, 'En curso'), (2, 'Cancelado')", TABLE_NAME);
        public static final String SQL_DELETE_ENTRIES = String.format("DROP TABLE IF EXISTS %s",
                TABLE_NAME);

    }

    public static class InvoiceEntry implements BaseColumns{

        public static final String TABLE_NAME = "invoice";
        public static final String PHARMACY_ID = "pharmacy_id";
        public static final String COLUMN_IN_DATE = "in_date";
        public static final String STATUS_ID = "status_id";
        public static final String IN_STATUS_JOIN_STATUS = String.format("INNER JOIN %s s ON i.%s = s.%s  ", DataBaseContract.StatusEntry.TABLE_NAME,
                STATUS_ID, DataBaseContract.StatusEntry._ID);
        public static final String IN_PHARMACY_JOIN_PHARMACY = String.format("%s i INNER JOIN %s p ON i.%s = p.%s %s", TABLE_NAME,
                DataBaseContract.PharmacyEntry.TABLE_NAME, _ID, DataBaseContract.PharmacyEntry._ID, IN_STATUS_JOIN_STATUS);
        public static final String REFERENCE_ID_PHARMACY = String.format("REFERENCES %s (%s) ON UPDATE CASCADE ON" +
                " DELETE RESTRICT",  DataBaseContract.PharmacyEntry.TABLE_NAME, DataBaseContract.PharmacyEntry._ID);
        public static final String[] COLUMNS_JOIN_PHARMACY_STATUS = {

                DataBaseContract.PharmacyEntry.COLUMN_NAME,
                DataBaseContract.StatusEntry.COLUMN_NAME,
                COLUMN_IN_DATE,
                "i."+_ID

        };
        public static final String REFERENCE_ID_STATUS = String.format("REFERENCES %s (%s) ON UPDATE CASCADE ON" +
                " DELETE RESTRICT",  DataBaseContract.StatusEntry.TABLE_NAME, DataBaseContract.StatusEntry._ID);
        public static final String SQL_CREATE_ENTRIES = String.format("CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s INTEGER NOT NULL %s," +
                        "%s TEXT NOT NULL," +
                        "%s INTEGER NOT NULL %s)",
                TABLE_NAME,
                _ID,
                PHARMACY_ID,
                REFERENCE_ID_PHARMACY,
                COLUMN_IN_DATE,
                STATUS_ID,
                REFERENCE_ID_STATUS
        );
        public static final String SQL_DELETE_ENTRIES = String.format("DROP TABLE IF EXISTS %s",
                TABLE_NAME);

    }

    public static class InvoiceLineEntry implements BaseColumns{

        public static final String TABLE_NAME = "invoice_line";
        public static final String INVOICE_ID = "invoice_id";
        public static final String COLUMN_IL_ORDER_PRODUCT = "il_order_product";
        public static final String PRODUCT_ID = "product_id";
        public static final String COLUMN_IL_AMOUNT = "il_amount";
        public static final String COLUMN_IL_PRICE = "il_price";
        public static final String SELECT_PRIMARY_KEY = String.format("PRIMARY KEY (%s, %s)",INVOICE_ID, COLUMN_IL_ORDER_PRODUCT);
        public static final String REFERENCE_ID_INVOICE = String.format("REFERENCES %s (%s) ON UPDATE CASCADE ON" +
                " DELETE RESTRICT", DataBaseContract.InvoiceEntry.TABLE_NAME, DataBaseContract.InvoiceEntry._ID);
        public static final String REFERENCE_ID_PRODUCT = String.format("REFERENCES %s (%s) ON UPDATE CASCADE ON" +
                " DELETE RESTRICT",  DataBaseContract.ProductEntry.TABLE_NAME, DataBaseContract.ProductEntry._ID);
        public static final String SQL_CREATE_ENTRIES = String.format("CREATE TABLE %s (" +
                        "%s INTEGER NOT NULL," +
                        "%s INTEGER NOT NULL %s," +
                        "%s INTERGER NOT NULL," +
                        "%s INTEGER NOT NULL %s," +
                        "%s INTEGER NOT NULL," +
                        "%s REAL NOT NULL, %s)",
                TABLE_NAME,
                _ID,
                INVOICE_ID,
                REFERENCE_ID_INVOICE,
                COLUMN_IL_ORDER_PRODUCT,
                PRODUCT_ID,
                REFERENCE_ID_PRODUCT,
                COLUMN_IL_AMOUNT,
                COLUMN_IL_PRICE,
                SELECT_PRIMARY_KEY
        );


        public static final String SQL_DELETE_ENTRIES = String.format("DROP TABLE IF EXISTS %s",
                TABLE_NAME);

    }

}
