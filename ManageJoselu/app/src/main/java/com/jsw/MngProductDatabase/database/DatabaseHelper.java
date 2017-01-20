package com.jsw.MngProductDatabase.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "Manage.db";
    private volatile static DatabaseHelper databaseHelper;

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public synchronized static DatabaseHelper getInstance(Context context) {
        if (databaseHelper == null) {
            databaseHelper = new DatabaseHelper(context.getApplicationContext());
        }
        return databaseHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(ManageContract.CategoryEntry.SQL_CREATE_ENTRIES);
        sqLiteDatabase.execSQL(ManageContract.ProductEntry.SQL_CREATE_ENTRIES);
        sqLiteDatabase.execSQL(ManageContract.StatusEntry.SQL_CREATE_ENTRIES);
        sqLiteDatabase.execSQL(ManageContract.PharmacyEntry.SQL_CREATE_ENTRIES);
        sqLiteDatabase.execSQL(ManageContract.InvoiceEntry.SQL_CREATE_ENTRIES);
        sqLiteDatabase.execSQL(ManageContract.InvoiceLineEntry.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldV, int newV) {
        sqLiteDatabase.execSQL(ManageContract.InvoiceLineEntry.SQL_DELETE_ENTRIES);
        sqLiteDatabase.execSQL(ManageContract.InvoiceEntry.SQL_DELETE_ENTRIES);
        sqLiteDatabase.execSQL(ManageContract.PharmacyEntry.SQL_DELETE_ENTRIES);
        sqLiteDatabase.execSQL(ManageContract.StatusEntry.SQL_DELETE_ENTRIES);
        sqLiteDatabase.execSQL(ManageContract.ProductEntry.SQL_DELETE_ENTRIES);
        sqLiteDatabase.execSQL(ManageContract.CategoryEntry.SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, newVersion, oldVersion);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                db.setForeignKeyConstraintsEnabled(true);
            } else {
                db.execSQL("PRAGMA foreign_keys = ON");
            }
        }
    }

    public SQLiteDatabase open() {
        return getWritableDatabase();
    }
}
