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
    private static final String KEY_USER_ID = "id";
    private static final String KEY_USER_USERNAME = "userName";
    private static final String KEY_USER_EMAIL = "email";
    private static final String KEY_USER_PASSWORD = "password";
    private static final String KEY_USER_SAVEDITEMS = "savedItems";

    //product table
    private static final String TABLE_PRODUCT = "product";
    private static final String KEY_PRODUCT_PRODUCTID = "product_id";
    private static final String KEY_PRODUCT_COLLECTION = "collection";
    private static final String KEY_PRODUCT_NAME = "name";
    private static final String KEY_PRODUCT_DESC = "desc";
    private static final String KEY_PRODUCT_PRICE = "price";
    private static final String KEY_PRODUCT_IMAGE = "image";
    private static final String KEY_PRODUCT_CATEGORY = "category";
    private static final String KEY_PRODUCT_SAVEDSTATUS = "savedStatus";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create User Table
        String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_USER + "("
                + KEY_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_USER_USERNAME + " TEXT,"
                + KEY_USER_EMAIL + " TEXT," + KEY_USER_PASSWORD + "TEXT," + KEY_USER_SAVEDITEMS + "TEXT" + ")";
        db.execSQL(CREATE_USER_TABLE);

        // Create Product Table
        String CREATE_PRODUCT_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_PRODUCT + "("
                + KEY_PRODUCT_PRODUCTID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_PRODUCT_COLLECTION + " TEXT,"
                + KEY_PRODUCT_NAME + " TEXT," + KEY_PRODUCT_DESC + "TEXT," + KEY_PRODUCT_PRICE + "REAL, "
                + KEY_PRODUCT_IMAGE + "TEXT, " + KEY_PRODUCT_CATEGORY + "TEXT, " + KEY_PRODUCT_SAVEDSTATUS + "INTEGER" + ")";
        db.execSQL(CREATE_PRODUCT_TABLE);

    }


    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        // Create tables again
        onCreate(db);
    }

    // code to add a default user
    public void addDefaultUser() {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USER_USERNAME, "user123");
        values.put(KEY_USER_EMAIL, "user123@gmail.com");
        values.put(KEY_USER_PASSWORD, "user123");
        values.put(KEY_USER_SAVEDITEMS, "");

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    // code to add products
    public void addAllProducts() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO " + TABLE_PRODUCT + "(" + KEY_PRODUCT_COLLECTION + ", " + KEY_PRODUCT_NAME + ", " + KEY_PRODUCT_DESC + ", " + KEY_PRODUCT_PRICE + ", " + KEY_PRODUCT_IMAGE + ", " + KEY_PRODUCT_CATEGORY + ", " + KEY_PRODUCT_SAVEDSTATUS + ") VALUES " +
                   "(\"Lyla Collection\",\"Off Shoulder Top\",\"Elegant White Off Shoulder Top with Frills. Appropriate for casual to semi-formal events.\",30.30,\"https://res.cloudinary.com/jaslynlkw/image/upload/v1674630291/ANDE/clothing/lyla_offshouldertop_wy7xb9.jpg\",\"clothing\", 0)," +
                   "(\"Freya Collection\",\"Pink Dotted Dress\",\"Cute Pink Dotted Dress with Frills. Appropriate for casual gatherings.\",60.40,\"https://res.cloudinary.com/jaslynlkw/image/upload/v1674630280/ANDE/clothing/freya_pinkdotteddress_dpggft.jpg\",\"clothing\", 0)," +
                   "(\"Aleya Collection\",\"Extravagant Black Dress\",\"Elegant Chic Black Dress with Frills. Appropriate for big events.\",230.30,\"https://res.cloudinary.com/jaslynlkw/image/upload/v1674630258/ANDE/clothing/aleya_extravagantblackdress_c96drc.jpg\",\"clothing\", 0)," +
                   "(\"Daniel Collection\",\"White Flared Jeans\",\"Cool white flared jeans for cool times. Appropriate for casual to semi-formal events.\",45.60,\"https://res.cloudinary.com/jaslynlkw/image/upload/v1674630272/ANDE/clothing/daniel_whiteflaredjeans_osrtq6.jpg\",\"clothing\", 0)," +
                   "(\"Reza Collection\",\"Off-white Teddy Bear Coat\",\"Oversized Teddy Bear Coat for the chilly days. Appropriate for casual to semi-formal events.\",80.90,\"https://res.cloudinary.com/jaslynlkw/image/upload/v1674630313/ANDE/clothing/reza_offwhiteteddybearcoat_ctx2ui.jpg\",\"clothing\", 0)," +
                   "(\"Niki Collection\",\"Yellow Matching Set\",\"Yellow hoodie and sweatpants set. Appropriate for casual events.\",71.20,\"https://res.cloudinary.com/jaslynlkw/image/upload/v1674630312/ANDE/clothing/niki_yellowmatchingset_snyyyk.jpg\",\"clothing\", 0)," +
                   "(\"Camila Collection\",\"Canvas High Top Shoes\",\"Black High Top shoes made of canvas. Appropriate for casual events.\",70.00,\"https://res.cloudinary.com/jaslynlkw/image/upload/v1674630336/ANDE/shoes/camila_canvashightopshoes_qvl0if.jpg\",\"shoes\", 0)," +
                   "(\"Allanwood Collection\",\"Pink Gradient Sparkly Heels\",\"Elegant and eye-catching pink sparkly heels. Appropriate for casual gatherings and big events.\",160.00,\"https://res.cloudinary.com/jaslynlkw/image/upload/v1674630337/ANDE/shoes/allanwood_sparklyheels_etuyz2.jpg\",\"shoes\", 0)," +
                   "(\"Nathan Collection\",\"Brown Boots\",\"Brown waterproof boots. Appropriate for snowy weather.\",130.00,\"https://res.cloudinary.com/jaslynlkw/image/upload/v1674630354/ANDE/shoes/nathan_brownboots_cc6ipo.jpg\",\"shoes\", 0)," +
                   "(\"Laura Collection\",\"Black Court Shoes\",\"Classic black court shoes. Appropriate for casual to formal events.\",45.00,\"https://res.cloudinary.com/jaslynlkw/image/upload/v1674630348/ANDE/shoes/laura_blackcourtshoes_yehhgs.jpg\",\"shoes\", 0)," +
                   "(\"Erik Collection\",\"Salmon Pink Sneakers\",\"Comfy sneakers. And they're pink! Appropriate for casual to semi-formal events.\",90.00,\"https://res.cloudinary.com/jaslynlkw/image/upload/v1674630342/ANDE/shoes/erik_salmonpinksneakers_muizqj.jpg\",\"shoes\", 0)," +
                   "(\"Najla Collection\",\"Heeled Ballet Flats\",\"Simple and classic ballet flats, with a bit of a heel! Appropriate for casual to formal events.\",40.00,\"https://res.cloudinary.com/jaslynlkw/image/upload/v1674630355/ANDE/shoes/najla_heeledballetflats_gid0rz.jpg\",\"shoes\", 0);");
        db.close();
    }

//    public boolean validateUser(String username, String password) {
//
//        boolean result = false;
//        String countQuery = "SELECT * FROM " + TABLE_USER + " WHERE " + KEY_USER_USERNAME + " = " + username + " AND " + KEY_USER_PASSWORD + " = " + password;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(countQuery, null);
//
//        // ensure user exists in user db
//        if (cursor.getCount() == 1) {
//            result = true;
//        }
//        cursor.close();
//        return result;
//    }




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

    // code to get all products of a category in a list view
    public List<ProductItem> getAllProducts(String category) {
        List<ProductItem> productList = new ArrayList<ProductItem>();
        // Select All Query
        String selectQuery = "SELECT " + KEY_PRODUCT_PRODUCTID + ", " + KEY_PRODUCT_COLLECTION + ", " + KEY_PRODUCT_NAME + ", " + KEY_PRODUCT_PRICE + ", " + KEY_PRODUCT_IMAGE + ", " + KEY_PRODUCT_SAVEDSTATUS
                + " FROM " + TABLE_PRODUCT + " WHERE " + KEY_PRODUCT_CATEGORY + " = " + category;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ProductItem product = new ProductItem();
                product.setProductID(Integer.parseInt(cursor.getString(0)));
                product.setCollection(cursor.getString(1));
                product.setName(cursor.getString(2));
                product.setPrice(cursor.getDouble(3));
                product.setImageURL(cursor.getString(4));
                product.setSavedStatus(cursor.getInt(5));
                // Adding contact to list
                productList.add(product);
            } while (cursor.moveToNext());
        }

        // return contact list
        return productList;
    }


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
