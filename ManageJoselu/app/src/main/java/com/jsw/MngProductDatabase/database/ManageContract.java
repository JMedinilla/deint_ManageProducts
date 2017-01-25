package com.jsw.MngProductDatabase.database;

import android.provider.BaseColumns;

/*
 * Clase que guarda el esquema de la base de datos de la aplicación
 */
public class ManageContract {

    private ManageContract() {

    }

    public static class CategoryEntry implements BaseColumns {
        public static final String TABLE_NAME = "category";
        public static final String COLUMN_NAME = "name";

        public static final String SQL_CREATE_ENTRIES =
                String.format("CREATE TABLE %s (" +
                                "%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                                "%s TEXT NOT NULL)",
                        TABLE_NAME, BaseColumns._ID, COLUMN_NAME);
        public static final String SQL_DELETE_ENTRIES =
                String.format("DROP TABLE IF EXISTS %s",
                        TABLE_NAME);
        public static final String SQL_INSERT_CATEGORY = "INSERT INTO category VALUES ('1', 'jarabe')";
    }

    public static class ProductEntry implements BaseColumns {
        public static final String TABLE_NAME = "product";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_BRAND = "brand";
        public static final String COLUMN_DOSAGE = "dosage";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_STOCK = "stock";
        public static final String COLUMN_IMAGE = "image";
        public static final String COLUMN_IDCATEGORY = "idCategory";
        public static final String[] ALL_COLUMN = new String[] { BaseColumns._ID, COLUMN_NAME, COLUMN_DESCRIPTION,
                                    COLUMN_BRAND, COLUMN_DOSAGE, COLUMN_PRICE, COLUMN_STOCK, COLUMN_IMAGE, COLUMN_IDCATEGORY};
        public static final String REFERENCE_ID_CATEGORY = String.format("REFERENCES %s (%s) ON UPDATE CASCADE ON DELETE RESTRICT",
                CategoryEntry.TABLE_NAME, BaseColumns._ID);

        public static final String SQL_CREATE_ENTRIES =
                String.format("CREATE TABLE %s (" +
                                "%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                                "%s TEXT NOT NULL," +
                                "%s TEXT NOT NULL," +
                                "%s TEXT NOT NULL," +
                                "%s TEXT NOT NULL," +
                                "%s REAL NOT NULL," +
                                "%s INTEGER NOT NULL," +
                                "%s TEXT NOT NULL," +
                                "%s INTEGER NOT NULL %s)",
                        TABLE_NAME, BaseColumns._ID, COLUMN_NAME, COLUMN_DESCRIPTION, COLUMN_BRAND,
                        COLUMN_DOSAGE, COLUMN_PRICE, COLUMN_STOCK, COLUMN_IMAGE, COLUMN_IDCATEGORY,
                        REFERENCE_ID_CATEGORY);
        public static final String SQL_DELETE_ENTRIES =
                String.format("DROP TABLE IF EXISTS %s",
                        TABLE_NAME);
    }

    public static class StatusEntry implements BaseColumns {
        public static final String TABLE_NAME = "status";
        public static final String COLUMN_NAME = "name";

        public static final String SQL_CREATE_ENTRIES =
                String.format("CREATE TABLE %s (" +
                                "%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                                "%s TEXT NOT NULL)",
                        TABLE_NAME, BaseColumns._ID, COLUMN_NAME);
        public static final String SQL_DELETE_ENTRIES =
                String.format("DROP TABLE IF EXISTS %s",
                        TABLE_NAME);
    }

    public static class PharmacyEntry implements BaseColumns {
        public static final String TABLE_NAME = "pharmacy";
        public static final String COLUMN_CIF = "cif";
        public static final String COLUMN_ADDRESS = "address";
        public static final String COLUMN_PHONE = "phone";
        public static final String COLUMN_MAIL = "mail";

        public static final String SQL_CREATE_ENTRIES =
                String.format("CREATE TABLE %s (" +
                                "%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                                "%s TEXT NOT NULL," +
                                "%s TEXT NOT NULL," +
                                "%s TEXT NOT NULL," +
                                "%s TEXT NOT NULL)",
                        TABLE_NAME, BaseColumns._ID, COLUMN_CIF, COLUMN_ADDRESS,
                        COLUMN_PHONE, COLUMN_MAIL);
        public static final String SQL_DELETE_ENTRIES =
                String.format("DROP TABLE IF EXISTS %s",
                        TABLE_NAME);
    }

    public static class InvoiceEntry implements BaseColumns {
        public static final String TABLE_NAME = "invoice";
        public static final String COLUMN_IDPHARMACY = "idPharmacy";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_IDSTATUS = "idStatus";
        public static final String REFERENCE_ID_PHARMACY = String.format("REFERENCES %s (%s) ON UPDATE CASCADE ON DELETE RESTRICT",
                PharmacyEntry.TABLE_NAME, BaseColumns._ID);
        public static final String REFERENCE_ID_STATUS = String.format("REFERENCES %s (%s) ON UPDATE CASCADE ON DELETE RESTRICT",
                StatusEntry.TABLE_NAME, BaseColumns._ID);

        public static final String SQL_CREATE_ENTRIES =
                String.format("CREATE TABLE %s (" +
                                "%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                                "%s INTEGER NOT NULL %s," +
                                "%s TEXT NOT NULL," +
                                "%s INTEGER NOT NULL %s)",
                        TABLE_NAME, BaseColumns._ID, COLUMN_IDPHARMACY, REFERENCE_ID_PHARMACY,
                        COLUMN_DATE, COLUMN_IDSTATUS, REFERENCE_ID_STATUS);
        public static final String SQL_DELETE_ENTRIES =
                String.format("DROP TABLE IF EXISTS %s",
                        TABLE_NAME);
    }

    public static class InvoiceLineEntry implements BaseColumns {
        public static final String TABLE_NAME = "invoiceLine";
        public static final String COLUMN_IDINVOICE = "idInvoice";
        public static final String COLUMN_ORDERPRODUCT = "orderProduct";
        public static final String COLUMN_IDPRODUCT = "idProduct";
        public static final String COLUMN_AMOUNT = "amount";
        public static final String COLUMN_PRICE = "price";
        public static final String REFERENCE_ID_INVOICE = String.format("REFERENCES %s (%s) ON UPDATE CASCADE ON DELETE RESTRICT",
                InvoiceEntry.TABLE_NAME, BaseColumns._ID);
        public static final String REFERENCE_ID_PRODUCT = String.format("REFERENCES %s (%s) ON UPDATE CASCADE ON DELETE RESTRICT",
                ProductEntry.TABLE_NAME, BaseColumns._ID);

        public static final String SQL_CREATE_ENTRIES =
                String.format("CREATE TABLE %s (" +
                                "%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                                "%s INTEGER NOT NULL %s," +
                                "%s INTEGER NOT NULL," +
                                "%s INTEGER NOT NULL %s," +
                                "%s INTEGER NOT NULL," +
                                "%s REAL NOT NULL)",
                        TABLE_NAME, BaseColumns._ID, COLUMN_IDINVOICE, REFERENCE_ID_INVOICE, COLUMN_ORDERPRODUCT,
                        COLUMN_IDPRODUCT, REFERENCE_ID_PRODUCT, COLUMN_AMOUNT, COLUMN_PRICE);
        public static final String SQL_DELETE_ENTRIES =
                String.format("DROP TABLE IF EXISTS %s",
                        TABLE_NAME);
    }
}
