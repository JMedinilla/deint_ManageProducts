package com.jsw.MngProductDatabase.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.jsw.MngProductDatabase.Model.Product;

import java.util.ArrayList;
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
        ArrayList<Product> products = new ArrayList<>();
        Product product;
        SQLiteDatabase sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();
        Cursor cursor = sqLiteDatabase.query(ManageContract.ProductEntry.TABLE_NAME,
                ManageContract.ProductEntry.ALL_COLUMN, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                product = new Product();
                product.setID(cursor.getInt(0));
                product.setName(cursor.getString(1));
                product.setDescription(cursor.getString(2));
                product.setBrand(cursor.getString(3));
                product.setDosage(cursor.getString(4));
                product.setPrice(cursor.getDouble(5));
                product.setStock(cursor.getInt(6));
                product.setImage(cursor.getString(7));
                product.setIdCategory(cursor.getInt(8));
                products.add(product);
            }
            while (cursor.moveToNext());
        }

        DatabaseHelper.getInstance().closeDatabase();
        return products;
    }

    public void updateProduct(Product product) {
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

        String where = BaseColumns._ID + " = ?";
        String[] whereArgs = new String[]{String.valueOf(product.getID())};

        sqLiteDatabase.update(ManageContract.ProductEntry.TABLE_NAME, contentValues, where, whereArgs);
        DatabaseHelper.getInstance().closeDatabase();
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
