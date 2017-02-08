package com.afg.MngProductContentProvider.provider;


import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Clase que guarda el esquema de la clase de la base de datos de la app
 *
 */

public class ManageProductContract  {


    public static final String AUTHORITY = "com.afg.MngProductContentProvider";
    public static final Uri AUTHORITY_URI = Uri.parse("content://"+AUTHORITY);

    public static class Category implements BaseColumns{

        public  static final String CONTENT_PATH = "category";
        public static final String NAME = "name";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, CONTENT_PATH);
        public static final String[] PROJECTION = {String.valueOf(_ID), NAME};


    }

    public static class Product implements BaseColumns{

        public static final String CONTENT_PATH = "product";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, CONTENT_PATH);
        public static final String NAME = "name";
        public static final String DESCRIPTION = "description";
        public static final String BRAND = "brand";
        public static final String DOSAGE = "dosage";
        public static final String PRICE = "price";
        public static final String STOCK = "stock";
        public static final String IMAGE = "image";
        public static final String CATEGORY = "category";
        public static final String[] PROJECTIONS = {_ID, NAME,
                BRAND, CATEGORY,
                DESCRIPTION, DOSAGE,
                IMAGE, PRICE,
                STOCK};


    }

    public static class Pharmacy implements BaseColumns{

        public static final String CONTENT_PATH = "pharmacy";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, CONTENT_PATH);
        public static final String NAME = "name";
        public static final String CIF = "cif";
        public static final String ADDRESS = "address";
        public static final String PHONE = "phone";
        public static final String MAIL = "pr_mail";
        public static final String[] ALL_COLUMNS = {_ID, NAME, CIF, ADDRESS, PHONE,
        MAIL};

    }

    public static class Status implements BaseColumns{

        public static final String CONTENT_PATH = "status";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, CONTENT_PATH);
        public static final String NAME = "name";
    }

    public static class Invoice implements BaseColumns{

        public static final String CONTENT_PATH = "invoice";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, CONTENT_PATH);
        public static final String PHARMACY = "pharmacy";
        public static final String DATE = "date";
        public static final String STATUS = "in_status";

    }

    public static class InvoiceLine implements BaseColumns{

        public static final String CONTENT_PATH = "invoice_line";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, CONTENT_PATH);
        public static final String INVOICE = "invoice";
        public static final String PRODUCT = "product";
        public static final String IL_PRODUCT = "il_product";
        public static final String AMOUNT = "il_amount";
        public static final String PRICE = "price";


    }


}
