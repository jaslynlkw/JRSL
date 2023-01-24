package com.example.jrsl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "jrslDatabase";

    //user table
    private static final String TABLE_USER = "user";
    private static final String KEY_ID = "id";
    private static final String KEY_USERNAME = "userName";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";

    //product table
    private static final String TABLE_PRODUCT = "product";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create User Table
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_USERNAME + " TEXT,"
                + KEY_EMAIL + " TEXT," + KEY_PASSWORD + "TEXT" + ")";
        db.execSQL(CREATE_USER_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        // Create tables again
        onCreate(db);
    }

//    // code to get the single product
//    ProductItem getProduct(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = db.query(TABLE_PRODUCT, new String[] { KEY_ID,
//                        KEY_USERNAME, KEY_EMAIL }, KEY_ID + "=?",
//                new String[] { String.valueOf(id) }, null, null, null, null);
//        if (cursor != null)
//            cursor.moveToFirst();
//
//        ProductItem product = new Product(Integer.parseInt(cursor.getString(0)),
//                cursor.getString(1), cursor.getString(2));
//        // return contact
//        return product;
//    }
//
//    // code to get all products in a list view
//    public List<ProductItem> getAllProducts() {
//        List<ProductItem> productList = new ArrayList<ProductItem>();
//        // Select All Query
//        String selectQuery = "SELECT  * FROM " + TABLE_PRODUCT;
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//
//        // looping through all rows and adding to list
//        if (cursor.moveToFirst()) {
//            do {
//                ProductItem product = new ProductItem();
//                product.setProductID(Integer.parseInt(cursor.getString(0)));
//                product.setCollection(cursor.getString(1));
//                product.setName(cursor.getString(2));
//                product.setPrice(cursor.getDouble(3));
//                product.setImage(cursor.getInt(4));
//                product.setSavedStatus(cursor.getInt(5));
//                // Adding contact to list
//                productList.add(product);
//            } while (cursor.moveToNext());
//        }
//
//        // return contact list
//        return productList;
//    }

    // Getting Products Count
    public int getProductsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_PRODUCT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}
