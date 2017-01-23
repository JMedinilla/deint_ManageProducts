package com.jsw.MngProductDatabase.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.jsw.MngProductDatabase.Model.Product;

import java.util.List;

public class DatabaseManager {
    private static DatabaseManager databaseManager;

    public static DatabaseManager getInstance() {
        if (databaseManager == null) {
            databaseManager = new DatabaseManager();
        }
        return databaseManager;
    }

    private DatabaseManager() {
    }

    public List<Product> getAllProducts() {
        return null;
    }

    public void updateProduct(Product product) {

    }

    public void addProduct(Product product) {
        SQLiteDatabase sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ManageContract.ProductEntry.COLUMN_NAME, product.getName());
        contentValues.put(ManageContract.ProductEntry.COLUMN_DESCRIPTION, product.getDescription());
        contentValues.put(ManageContract.ProductEntry.COLUMN_BRAND, product.getBrand());
        contentValues.put(ManageContract.ProductEntry.COLUMN_DOSAGE, product.getDosage());
        contentValues.put(ManageContract.ProductEntry.COLUMN_PRICE, product.getPrice());
        contentValues.put(ManageContract.ProductEntry.COLUMN_STOCK, product.getStock());
        contentValues.put(ManageContract.ProductEntry.COLUMN_IMAGE, product.getImage());
        contentValues.put(ManageContract.ProductEntry.COLUMN_IDCATEGORY, 1);
        sqLiteDatabase.insert(ManageContract.ProductEntry.TABLE_NAME, null, contentValues);
        DatabaseHelper.getInstance().closeDatabase();
    }

    public void deleteProduct(Product product) {

    }
}
