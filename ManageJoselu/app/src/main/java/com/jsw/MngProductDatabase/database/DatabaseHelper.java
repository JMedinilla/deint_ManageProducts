package com.jsw.MngProductDatabase.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import com.jsw.MngProductDatabase.ManageproductApplication;

import java.util.concurrent.atomic.AtomicInteger;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 7;
    private static final String DATABASE_NAME = "Manage.db";
    private volatile static DatabaseHelper databaseHelper;
    private AtomicInteger mOpenCounter;
    private SQLiteDatabase mDatabase;

    private DatabaseHelper() {
        super(ManageproductApplication.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
        mOpenCounter = new AtomicInteger();
    }

    public synchronized static DatabaseHelper getInstance() {
        if (databaseHelper == null) {
            databaseHelper = new DatabaseHelper();
        }
        return databaseHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.beginTransaction();
        try {
            sqLiteDatabase.execSQL(ManageContract.CategoryEntry.SQL_CREATE_ENTRIES);
            sqLiteDatabase.execSQL(ManageContract.ProductEntry.SQL_CREATE_ENTRIES);
            sqLiteDatabase.execSQL(ManageContract.StatusEntry.SQL_CREATE_ENTRIES);
            sqLiteDatabase.execSQL(ManageContract.PharmacyEntry.SQL_CREATE_ENTRIES);
            sqLiteDatabase.execSQL(ManageContract.InvoiceEntry.SQL_CREATE_ENTRIES);
            sqLiteDatabase.execSQL(ManageContract.InvoiceLineEntry.SQL_CREATE_ENTRIES);
            sqLiteDatabase.execSQL(ManageContract.CategoryEntry.SQL_INSERT_CATEGORY_1);
            sqLiteDatabase.execSQL(ManageContract.CategoryEntry.SQL_INSERT_CATEGORY_2);
            sqLiteDatabase.execSQL(ManageContract.CategoryEntry.SQL_INSERT_CATEGORY_3);
            sqLiteDatabase.execSQL(ManageContract.CategoryEntry.SQL_INSERT_CATEGORY_4);
            sqLiteDatabase.execSQL(ManageContract.CategoryEntry.SQL_INSERT_CATEGORY_5);
            sqLiteDatabase.setTransactionSuccessful();
        } catch (SQLiteException ex) {
            Log.e("MngProductDatabase", "Error al crear la base de datos" + ex.getMessage());
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldV, int newV) {
        sqLiteDatabase.beginTransaction();
        try {
            sqLiteDatabase.execSQL(ManageContract.InvoiceLineEntry.SQL_DELETE_ENTRIES);
            sqLiteDatabase.execSQL(ManageContract.InvoiceEntry.SQL_DELETE_ENTRIES);
            sqLiteDatabase.execSQL(ManageContract.PharmacyEntry.SQL_DELETE_ENTRIES);
            sqLiteDatabase.execSQL(ManageContract.StatusEntry.SQL_DELETE_ENTRIES);
            sqLiteDatabase.execSQL(ManageContract.ProductEntry.SQL_DELETE_ENTRIES);
            sqLiteDatabase.execSQL(ManageContract.CategoryEntry.SQL_DELETE_ENTRIES);
            onCreate(sqLiteDatabase);
            sqLiteDatabase.setTransactionSuccessful();
        } catch (SQLiteException ex) {
            Log.e("MngProductDatabase", "Error al eliminar la base de datos" + ex.getMessage());
        } finally {
            sqLiteDatabase.endTransaction();
        }
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

    public synchronized SQLiteDatabase openDatabase() {
        if (mOpenCounter.incrementAndGet() == 1) {
            mDatabase = getWritableDatabase();
        }
        return mDatabase;
    }

    public synchronized void closeDatabase() {
        if (mOpenCounter.decrementAndGet() == 0) {
            mDatabase.close();
        }
    }
}
