package com.afg.MngProductContentProvider.provider;


import android.net.Uri;
import android.provider.BaseColumns;

import com.afg.MngProductContentProvider.database.DataBaseContract;

/**
 * Clase que guarda el esquema de la clase de la base de datos de la app
 *
 */

public class ManageProductContract  {


    public static final String AUTHORITY = "com.afg.MngProductContentProvider";
    public static final Uri AUTHORITY_URI = Uri.parse("content://"+AUTHORITY);

    public static class Category implements BaseColumns{

        public  static final String CONTENT_PATH = "category";
        public static final String NAME = "ca_name";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, CONTENT_PATH);
        public static final String[] PROJECTION = {String.valueOf(_ID), NAME};


    }

    public static class Product implements BaseColumns{

        public static final String CONTENT_PATH = "product";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, CONTENT_PATH);
        public static final String NAME = "pr_name";
        public static final String DESCRIPTION = "pr_description";
        public static final String BRAND = "pr_brand";
        public static final String DOSAGE = "pr_dosage";
        public static final String PRICE = "pr_price";
        public static final String STOCK = "pr_stock";
        public static final String IMAGE = "pr_image";
        public static final String CATEGORY = "category_id";
        public static final String[] PROJECTIONS = {_ID, NAME,
                BRAND, CATEGORY,
                DESCRIPTION, DOSAGE,
                IMAGE, PRICE,
                STOCK };
    }

    public static class Pharmacy implements BaseColumns{

        public static final String CONTENT_PATH = "pharmacy";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, CONTENT_PATH);
        public static final String NAME = "ph_name";
        public static final String CIF = "ph_cif";
        public static final String ADDRESS = "ph_address";
        public static final String PHONE = "ph_phone";
        public static final String MAIL = "ph_mail";
        public static final String[] PROJECTIONS = {_ID, NAME, CIF, ADDRESS, PHONE,
        MAIL};

    }

    public static class Status implements BaseColumns{

        public static final String CONTENT_PATH = "status";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, CONTENT_PATH);
        public static final String NAME = "st_staname";
    }

    public static class Invoice implements BaseColumns{

        public static final String CONTENT_PATH = "in_invoice";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, CONTENT_PATH);
        public static final String PHARMACY = "in_pharmacy";
        public static final String DATE = "in_date";
        public static final String STATUS = "in_status";
        public static final String[] PROJECTIONS = {

                DataBaseContract.PharmacyEntry.COLUMN_NAME,
                DataBaseContract.StatusEntry.COLUMN_NAME,
                DATE,
                "i."+_ID
        };


    }

    public static class InvoiceLine implements BaseColumns{

        public static final String CONTENT_PATH = "invoice_line";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, CONTENT_PATH);
        public static final String INVOICE = "il_invoice";
        public static final String PRODUCT = "il_product";
        public static final String IL_PRODUCT = "il_product";
        public static final String AMOUNT = "il_amount";
        public static final String PRICE = "il_price";


    }


}
