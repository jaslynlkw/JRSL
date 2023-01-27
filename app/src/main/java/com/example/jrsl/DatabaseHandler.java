package com.example.jrsl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "jrslDatabase";

    //user table
    private static final String TABLE_USER = "user";
    private static final String KEY_USER_ID = "id";
    private static final String KEY_USER_USERNAME = "userName";
    private static final String KEY_USER_EMAIL = "email";
    private static final String KEY_USER_PASSWORD = "password";
    private static final String KEY_USER_ADDRESS = "address";
    private static final String KEY_USER_SAVEDITEMS = "savedItems";

    //product table
    private static final String TABLE_PRODUCT = "product";
    private static final String KEY_PRODUCT_PRODUCTID = "product_id";
    private static final String KEY_PRODUCT_COLLECTION = "collection";
    private static final String KEY_PRODUCT_NAME = "name";
    private static final String KEY_PRODUCT_DESC = "description";
    private static final String KEY_PRODUCT_PRICE = "price";
    private static final String KEY_PRODUCT_IMAGEURL = "imageurl";
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
                + KEY_USER_ID + " INTEGER PRIMARY KEY," + KEY_USER_USERNAME + " TEXT UNIQUE,"
                + KEY_USER_EMAIL + " TEXT," + KEY_USER_PASSWORD + " TEXT," + KEY_USER_ADDRESS + " TEXT," + KEY_USER_SAVEDITEMS + " TEXT" + ")";
        db.execSQL(CREATE_USER_TABLE);
        Log.d(null,"User table created.");

        // Create Product Table
        String CREATE_PRODUCT_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_PRODUCT + "("
                + KEY_PRODUCT_PRODUCTID + " INTEGER PRIMARY KEY," + KEY_PRODUCT_COLLECTION + " TEXT,"
                + KEY_PRODUCT_NAME + " TEXT," + KEY_PRODUCT_DESC + " TEXT," + KEY_PRODUCT_PRICE + " REAL, "
                + KEY_PRODUCT_IMAGEURL + " TEXT, " + KEY_PRODUCT_CATEGORY + " TEXT, " + KEY_PRODUCT_SAVEDSTATUS + " INTEGER" + ")";
        db.execSQL(CREATE_PRODUCT_TABLE);
        Log.d(null,"Product table created.");

    }


    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT);

        // Create tables again
        onCreate(db);
    }

    // code to add a default user
    public void addDefaultUser() {
        SQLiteDatabase db = this.getWritableDatabase();

        //hash user password
        String password = "user123";
        String bcryptHashedPassword = BCrypt.withDefaults().hashToString(12, password.toCharArray());

        ContentValues values = new ContentValues();
        values.put(KEY_USER_ID, 1);
        values.put(KEY_USER_USERNAME, "user123");
        values.put(KEY_USER_EMAIL, "user123@gmail.com");
        values.put(KEY_USER_PASSWORD, bcryptHashedPassword);
        values.put(KEY_USER_ADDRESS, "user,123,Singapore,101 Astar Lane,House 1,101101,Singapore");
        values.put(KEY_USER_SAVEDITEMS, "1");

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.execSQL("INSERT OR IGNORE INTO " + TABLE_USER + "('" + KEY_USER_ID + "', '" + KEY_USER_USERNAME + "', '" + KEY_USER_EMAIL + "','" + KEY_USER_PASSWORD + "', '" + KEY_USER_ADDRESS + "', '" + KEY_USER_SAVEDITEMS + "') VALUES " +
                   "(1,\"user123\",\"user123@gmail.com\",\"user123\",\"user,123,Singapore,101 Astar Lane,House 1,101101,Singapore\",\"1\");");
        db.close();
    }

    // code to add products
    public void addAllProducts() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT OR IGNORE INTO " + TABLE_PRODUCT + "('" + KEY_PRODUCT_PRODUCTID + "', '" + KEY_PRODUCT_COLLECTION + "', '" + KEY_PRODUCT_NAME + "','" + KEY_PRODUCT_DESC + "', '" + KEY_PRODUCT_PRICE + "', '" + KEY_PRODUCT_IMAGEURL + "', '" + KEY_PRODUCT_CATEGORY + "', '" + KEY_PRODUCT_SAVEDSTATUS + "') VALUES " +
                   "(1,\"Lyla Collection\",\"Off Shoulder Top\",\"Elegant White Off Shoulder Top with Frills. Appropriate for casual to semi-formal events.\",30.30,\"https://res.cloudinary.com/jaslynlkw/image/upload/v1674630291/ANDE/clothing/lyla_offshouldertop_wy7xb9.jpg\",\"clothing\", 0)," +
                   "(2,\"Freya Collection\",\"Pink Dotted Dress\",\"Cute Pink Dotted Dress with Frills. Appropriate for casual gatherings.\",60.40,\"https://res.cloudinary.com/jaslynlkw/image/upload/v1674630280/ANDE/clothing/freya_pinkdotteddress_dpggft.jpg\",\"clothing\", 0)," +
                   "(3,\"Aleya Collection\",\"Extravagant Black Dress\",\"Elegant Chic Black Dress with Frills. Appropriate for big events.\",230.30,\"https://res.cloudinary.com/jaslynlkw/image/upload/v1674630258/ANDE/clothing/aleya_extravagantblackdress_c96drc.jpg\",\"clothing\", 0)," +
                   "(4,\"Daniel Collection\",\"White Flared Jeans\",\"Cool white flared jeans for cool times. Appropriate for casual to semi-formal events.\",45.60,\"https://res.cloudinary.com/jaslynlkw/image/upload/v1674630272/ANDE/clothing/daniel_whiteflaredjeans_osrtq6.jpg\",\"clothing\", 0)," +
                   "(5,\"Reza Collection\",\"Off-white Teddy Bear Coat\",\"Oversized Teddy Bear Coat for the chilly days. Appropriate for casual to semi-formal events.\",80.90,\"https://res.cloudinary.com/jaslynlkw/image/upload/v1674630313/ANDE/clothing/reza_offwhiteteddybearcoat_ctx2ui.jpg\",\"clothing\", 0)," +
                   "(6,\"Niki Collection\",\"Yellow Matching Set\",\"Yellow hoodie and sweatpants set. Appropriate for casual events.\",71.20,\"https://res.cloudinary.com/jaslynlkw/image/upload/v1674630312/ANDE/clothing/niki_yellowmatchingset_snyyyk.jpg\",\"clothing\", 0)," +
                   "(7,\"Camila Collection\",\"Canvas High Top Shoes\",\"Black High Top shoes made of canvas. Appropriate for casual events.\",70.00,\"https://res.cloudinary.com/jaslynlkw/image/upload/v1674630336/ANDE/shoes/camila_canvashightopshoes_qvl0if.jpg\",\"shoes\", 0)," +
                   "(8,\"Allanwood Collection\",\"Pink Gradient Sparkly Heels\",\"Elegant and eye-catching pink sparkly heels. Appropriate for casual gatherings and big events.\",160.00,\"https://res.cloudinary.com/jaslynlkw/image/upload/v1674630337/ANDE/shoes/allanwood_sparklyheels_etuyz2.jpg\",\"shoes\", 0)," +
                   "(9,\"Nathan Collection\",\"Brown Boots\",\"Brown waterproof boots. Appropriate for snowy weather.\",130.00,\"https://res.cloudinary.com/jaslynlkw/image/upload/v1674630354/ANDE/shoes/nathan_brownboots_cc6ipo.jpg\",\"shoes\", 0)," +
                   "(10,\"Laura Collection\",\"Black Court Shoes\",\"Classic black court shoes. Appropriate for casual to formal events.\",45.00,\"https://res.cloudinary.com/jaslynlkw/image/upload/v1674630348/ANDE/shoes/laura_blackcourtshoes_yehhgs.jpg\",\"shoes\", 0)," +
                   "(11,\"Erik Collection\",\"Salmon Pink Sneakers\",\"Comfy sneakers. And they're pink! Appropriate for casual to semi-formal events.\",90.00,\"https://res.cloudinary.com/jaslynlkw/image/upload/v1674630342/ANDE/shoes/erik_salmonpinksneakers_muizqj.jpg\",\"shoes\", 0)," +
                   "(12,\"Najla Collection\",\"Heeled Ballet Flats\",\"Simple and classic ballet flats, with a bit of a heel! Appropriate for casual to formal events.\",40.00,\"https://res.cloudinary.com/jaslynlkw/image/upload/v1674630355/ANDE/shoes/najla_heeledballetflats_gid0rz.jpg\",\"shoes\", 0);");
        db.close();
    }

    // code to validate user upon login
    public String[] validateUser(String username, String password) {

        String[] results = new String[6];
        results[0] = "false";
        String selectUserQuery = "SELECT * FROM " + TABLE_USER + " WHERE " + KEY_USER_USERNAME + " = '" + username + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectUserQuery, null);

        if (cursor.moveToFirst()) {
            do {
                // ensure username exists in user db then verify password
                if (cursor.getCount() == 1) {
                    String hashedUserPassword = cursor.getString(3);
                    BCrypt.Result verifyResult = BCrypt.verifyer().verify(password.toCharArray(), hashedUserPassword);
                    if (verifyResult.verified) {
                        results[0] = "true";
                        results[1] = cursor.getString(0);
                        results[2] = cursor.getString(1);
                        results[3] = cursor.getString(2);
                        results[4] = cursor.getString(4);
                        results[5] = cursor.getString(5);
                    }
                }
            } while (cursor.moveToNext());
        }

        cursor.close();
        return results;

    }

    // code to update saved item status in product table
    public void updateSavedItems(String savedItemIDs, String type) {
        SQLiteDatabase db = this.getWritableDatabase();

        if (type.equals("save")) {
           String UPDATE_PRODUCT_TABLE = "UPDATE " + TABLE_PRODUCT + " SET " + KEY_PRODUCT_SAVEDSTATUS + " = 1 WHERE " + KEY_PRODUCT_PRODUCTID + " IN (" + savedItemIDs + ")";
           db.execSQL(UPDATE_PRODUCT_TABLE);
       } else {
            String UPDATE_PRODUCT_TABLE = "UPDATE " + TABLE_PRODUCT + " SET " + KEY_PRODUCT_SAVEDSTATUS + " = 0";
            db.execSQL(UPDATE_PRODUCT_TABLE);
        }
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

    // code to get all products of a category in a list view
    public List<ProductItem> getAllProducts(String category) {
        List<ProductItem> productList = new ArrayList<ProductItem>();
        // Select All Query
        String selectQuery = "SELECT " + KEY_PRODUCT_PRODUCTID + ", " + KEY_PRODUCT_COLLECTION + ", " + KEY_PRODUCT_NAME + ", " + KEY_PRODUCT_PRICE + ", " + KEY_PRODUCT_IMAGEURL + ", " + KEY_PRODUCT_SAVEDSTATUS
                + " FROM " + TABLE_PRODUCT + " WHERE " + KEY_PRODUCT_CATEGORY + " = '" + category + "'";

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
                Log.d(null,"In db handler: " + cursor.getString(4));
                product.setSavedStatus(cursor.getInt(5));
                // Adding contact to list
                productList.add(product);
            } while (cursor.moveToNext());
        }

        // return contact list
        return productList;
    }

    // select * product where product_id = 1,2,3,4,5

    // Getting Products Count
    public int getProductsCount(String category) {
        String countQuery = "SELECT  * FROM " + TABLE_PRODUCT + " WHERE " + KEY_PRODUCT_CATEGORY + " = '" + category + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
}
