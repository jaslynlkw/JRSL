package com.example.jrsl;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;

import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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

    //cart table
    private static final String TABLE_CART = "cart";
    private static final String KEY_CART_CARTID = "cart_id";
    private static final String KEY_CART_PRODUCTID = "productid";
    private static final String KEY_CART_SIZE = "size";
    private static final String KEY_CART_QTY = "qty";
    private static final String KEY_CART_PRICE = "price";
    private static final String KEY_CART_SHIPPING = "shipping";


    //order table

    private static final String TABLE_ORDER = "orders";
    private static final String KEY_ORDER_ORDERID = "order_id";
    private static final String KEY_ORDER_USERID = "user_id";
    private static final String KEY_ORDER_ADDRESS = "user_address";
    private static final String KEY_ORDER_PRODUCTIDS = "order_productids";
    private static final String KEY_ORDER_SIZES = "order_sizes";
    private static final String KEY_ORDER_QTY = "order_qty";
    private static final String KEY_ORDER_PRICES = "order_prices";
    private static final String KEY_ORDER_TOTAL = "order_total";
    private static final String KEY_ORDER_STATUS = "order_status";
    private static final String KEY_ORDER_DATE = "order_date";
    private static final String KEY_ORDER_REFERENCE = "order_ref";

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

        // Create Cart Table
        String CREATE_CART_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_CART + "("
                + KEY_CART_CARTID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_CART_PRODUCTID + " INTEGER,"
                + KEY_CART_SIZE + " TEXT," + KEY_CART_QTY + " INTEGER," + KEY_CART_PRICE + " REAL, "
                + KEY_CART_SHIPPING + " REAL )";
        db.execSQL(CREATE_CART_TABLE);
        Log.d(null,"Cart table created.");

        // Create Order Table
        String CREATE_ORDER_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_ORDER + "("
                + KEY_ORDER_ORDERID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_ORDER_USERID + " TEXT," + KEY_ORDER_ADDRESS + " TEXT," + KEY_ORDER_PRODUCTIDS + " TEXT,"
                + KEY_ORDER_SIZES + " TEXT," + KEY_ORDER_QTY + " TEXT," + KEY_ORDER_PRICES + " TEXT, "
                + KEY_ORDER_TOTAL + " REAL, " + KEY_ORDER_STATUS  + " INT, " + KEY_ORDER_DATE + " DATE, " + KEY_ORDER_REFERENCE + " TEXT " + "  )";
        db.execSQL(CREATE_ORDER_TABLE);
        Log.d(null,"Order table created.");

    }


    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CART);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDER);

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

    //code to add default orders
    public void addDefaultOrders() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT OR IGNORE INTO " + TABLE_ORDER + "('" + KEY_ORDER_USERID + "', '" + KEY_ORDER_ADDRESS + "', '" + KEY_ORDER_PRODUCTIDS + "', '" + KEY_ORDER_SIZES + "','" + KEY_ORDER_QTY + "', '" + KEY_ORDER_PRICES + "', '" + KEY_ORDER_TOTAL + "', '" + KEY_ORDER_STATUS + "', '" + KEY_ORDER_DATE + "', '" + KEY_ORDER_REFERENCE + "') VALUES " +
                "(1,\"user,123,Singapore,101 Astar Lane,House 1,101101,Singapore\",\"1,4,9\",\"M,M,7\",\"1,1,1\",\"30.30,45.60,130.00\",205.90,\"Delivered\",\"2022/12/24\",\"D317HS\" )," +
                "(1,\"user,123,Singapore,101 Astar Lane,House 1,101101,Singapore\",\"3,8\",\"M,7\",\"1,1\",\"71.20,160.00\",390.30,\"Delivered\",\"2023/01/12\",\"C459WQ\" );");
        db.close();
    }

    //code to add default cart items
    public void addDefaultCartItems() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT OR IGNORE INTO " + TABLE_CART + "('" + KEY_CART_CARTID + "', '" + KEY_CART_PRODUCTID + "', '" + KEY_CART_SIZE + "','" + KEY_CART_QTY + "', '" + KEY_CART_PRICE + "', '" + KEY_CART_SHIPPING + "') VALUES " +
                "(1,6,\"S\",1,71.20,0.00 )," +
                "(2,5,\"S\",1,80.90,0.00 )," +
                "(3,7,\"M\",1,70.00,0.00 );");


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

    // code to add product to cart
    public void addToCart(int productid, String sizeSelected, int qty, double price, double shippingfee) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO " + TABLE_CART + "('" + KEY_CART_PRODUCTID + "', '" + KEY_CART_SIZE + "','" + KEY_CART_QTY + "', '" + KEY_CART_PRICE + "', '" + KEY_CART_SHIPPING + "') VALUES " +
                "('" + productid + "','" + sizeSelected + "','" + qty + "','" + price + "','"+ shippingfee + "')");
        db.close();
    }

    // code to add product to user's saveditems
    public String addToSaved(int userid, int productid) {
        SQLiteDatabase dbRead = this.getReadableDatabase();
        SQLiteDatabase dbWrite = this.getWritableDatabase();

        //getting user's saved items
        String savedItems = "";
        String selectUserSavedItemsQuery = "SELECT " + KEY_USER_SAVEDITEMS + " FROM " + TABLE_USER + " WHERE " + KEY_USER_ID + " = '" + userid + "'";
        Cursor cursor = dbRead.rawQuery(selectUserSavedItemsQuery, null);

        if (cursor.moveToFirst()) {
            savedItems = cursor.getString(0);
        }
        cursor.close();

        String newSavedItems = "";

        //updating user's saved items
        if (savedItems.equals("")) {
            newSavedItems = String.valueOf(productid);
        } else {
            newSavedItems = savedItems + "," + productid;
        }
        String UPDATE_USER_TABLE = "UPDATE " + TABLE_USER + " SET " + KEY_USER_SAVEDITEMS + " = '" + newSavedItems + "' WHERE " + KEY_USER_ID + " IN (" + userid + ")";
        dbWrite.execSQL(UPDATE_USER_TABLE);

        //update saved item status in product table
        updateSavedItems(newSavedItems, "save");

        return newSavedItems;
    }

    // code to remove product to user's saveditems
    public String removeFromSaved(int userid, int productid) {
        SQLiteDatabase dbRead = this.getReadableDatabase();
        SQLiteDatabase dbWrite = this.getWritableDatabase();
        String productidStr = String.valueOf(productid);
        int indexS=0;

        //getting user's saved items
        String savedItems = "";
        String selectUserSavedItemsQuery = "SELECT " + KEY_USER_SAVEDITEMS + " FROM " + TABLE_USER + " WHERE " + KEY_USER_ID + " = '" + userid + "'";
        Cursor cursor = dbRead.rawQuery(selectUserSavedItemsQuery, null);

        if (cursor.moveToFirst()) {
            savedItems = cursor.getString(0);
        }
        cursor.close();

        //removing from user's saved items
        ArrayList<String> savedItemsArrayList = new ArrayList<>(Arrays.asList(savedItems.split(",")));

        //getting index of product to remove
        for(int i=0; i<savedItemsArrayList.size(); i++) {

            if(savedItemsArrayList.get(i).equals(productidStr)){
                indexS = i;
                //remove productid from saved item arraylist
                savedItemsArrayList.remove(indexS);
                break;
            }
        }

        String newSavedItems = TextUtils.join(",", savedItemsArrayList);

        String UPDATE_USER_TABLE = "UPDATE " + TABLE_USER + " SET " + KEY_USER_SAVEDITEMS + " = '" + newSavedItems + "' WHERE " + KEY_USER_ID + " IN (" + userid + ")";
        dbWrite.execSQL(UPDATE_USER_TABLE);

        //update saved item status in product table
        updateSavedItems("", "clear");
        updateSavedItems(newSavedItems, "save");

        return newSavedItems;
    }

    // code to update saved item status in product table
    public void updateSavedItems(String savedItemIDs, String type) {
        SQLiteDatabase db = this.getWritableDatabase();

        if (type.equals("save")) {
           String UPDATE_PRODUCT_TABLE = "UPDATE " + TABLE_PRODUCT + " SET " + KEY_PRODUCT_SAVEDSTATUS + " = 1 WHERE " + KEY_PRODUCT_PRODUCTID + " IN (" + savedItemIDs + ")";
           db.execSQL(UPDATE_PRODUCT_TABLE);
        } else if (type.equals("clear")) {
            String UPDATE_PRODUCT_TABLE = "UPDATE " + TABLE_PRODUCT + " SET " + KEY_PRODUCT_SAVEDSTATUS + " = 0";
            db.execSQL(UPDATE_PRODUCT_TABLE);
        }
    }

    // code to get the single product
    ProductItem getProduct(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PRODUCT, new String[]
                        { KEY_PRODUCT_COLLECTION, KEY_PRODUCT_NAME, KEY_PRODUCT_DESC, KEY_PRODUCT_PRICE, KEY_PRODUCT_IMAGEURL, KEY_PRODUCT_SAVEDSTATUS }, KEY_PRODUCT_PRODUCTID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        ProductItem product = new ProductItem(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getDouble(3), cursor.getString(4), cursor.getInt(5));
        // return product
        return product;
    }

    // code to get order details for receipt
    OrderDetailsItem getReceipt() {
        String getOrderReceiptQuery = "SELECT  * FROM " + TABLE_ORDER + " ORDER BY " + KEY_ORDER_ORDERID + " DESC LIMIT 1";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(getOrderReceiptQuery, null);
        if (cursor != null)
            cursor.moveToFirst();
        OrderDetailsItem orderItem = new OrderDetailsItem(cursor.getString(2), Double.parseDouble(cursor.getString(7)), cursor.getString(9), cursor.getString(10), 0.00);
        Log.d(null, "ORDERITEM --> " + orderItem.getOrderRef());
        // return orderItem
        return orderItem;
    }

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

    // Getting Products Count
    public int getProductsCount(String category) {
        String countQuery = "SELECT  * FROM " + TABLE_PRODUCT + " WHERE " + KEY_PRODUCT_CATEGORY + " = '" + category + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int prodCount = cursor.getCount();
        cursor.close();

        // return count
        return prodCount;

    }


    // code to get all saved products of a user
    public List<ProductItem> getAllSavedProducts() {
        List<ProductItem> productList = new ArrayList<ProductItem>();
        // Select All Query
        String selectQuery = "SELECT " + KEY_PRODUCT_PRODUCTID + ", " + KEY_PRODUCT_COLLECTION + ", " + KEY_PRODUCT_NAME + ", " + KEY_PRODUCT_PRICE + ", " + KEY_PRODUCT_IMAGEURL + ", " + KEY_PRODUCT_SAVEDSTATUS
                + " FROM " + TABLE_PRODUCT + " WHERE " + KEY_PRODUCT_SAVEDSTATUS + " =1 " ;

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
                Log.d(null,"In db handler get saved: " + cursor.getString(4));
                product.setSavedStatus(cursor.getInt(5));
                // Adding contact to list
                productList.add(product);
            } while (cursor.moveToNext());
        }
        cursor.close();

        // return contact list
        return productList;
    }



    //get cart items
    public List<CartItem> getAllCartItems() {
        List<CartItem> cartList = new ArrayList<CartItem>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_CART ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(1);
                String selectQuery1 = "SELECT " + KEY_PRODUCT_COLLECTION + ", " + KEY_PRODUCT_NAME + ", " + KEY_PRODUCT_IMAGEURL + " FROM " + TABLE_PRODUCT + " WHERE " + KEY_PRODUCT_PRODUCTID + " = " + id;
                SQLiteDatabase db1 = this.getWritableDatabase();
                Cursor cursor1 = db1.rawQuery(selectQuery1, null);
                Log.d(null, selectQuery1);
                // looping through all rows and adding to list
                if (cursor1.moveToFirst()) {

                    do {
                        //Log.d(null, cursor1.getString(0));
                        CartItem cart = new CartItem();
                        cart.setCollection(cursor1.getString(0));
                        cart.setName(cursor1.getString(1));
                        cart.setImageURL(cursor1.getString(2));
                        cart.setProduct_id(id);
                        cart.setPrice(cursor.getDouble(4));
                        cart.setSize(cursor.getString(2));
                        cart.setQty(Integer.parseInt(cursor.getString(3)));
                        Log.d(null, "In db handler get item: \n Collection : " + cursor1.getString(0) + "\n Name: " + cursor1.getString(1) + "\n Image : " + cursor1.getString(2) + "\n Price: " + cursor.getDouble(4) + "\n Size" + cursor.getString(2) + "\n Qty: " + Integer.parseInt(cursor.getString(3)));
                        // Adding contact to list
                        cartList.add(cart);
                    } while (cursor1.moveToNext());
                }

            } while (cursor.moveToNext());
        }
        // return contact list
        return cartList;
    }



    //get Order History
    public List<OrderDetailsItem> getAllOrderHistory() {
        List<OrderDetailsItem> orderHistoryList = new ArrayList<OrderDetailsItem>();
        // Select All Query
        String selectQuery = "SELECT "+ KEY_ORDER_DATE + ", " + KEY_ORDER_REFERENCE + ", " + KEY_ORDER_STATUS + ", " + KEY_ORDER_PRODUCTIDS + ", " + KEY_ORDER_TOTAL +" FROM " + TABLE_ORDER ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                    String[] productid = cursor.getString(3).split(",");
                    String selectQuery1 = "SELECT " + KEY_PRODUCT_IMAGEURL + " FROM " + TABLE_PRODUCT + " WHERE " + KEY_PRODUCT_PRODUCTID + " =" + productid[0];
                    SQLiteDatabase db1 = this.getWritableDatabase();
                    Cursor cursor1 = db1.rawQuery(selectQuery1, null);
                    Log.d(null, selectQuery1);
                    // looping through all rows and adding to list
                    if (cursor1.moveToFirst()) {
                        do {
                            Log.d(null, cursor.getString(4));
                            OrderDetailsItem cart = new OrderDetailsItem();
                            cart.setOrderDate(cursor.getString(0));
                            cart.setOrderRef(cursor.getString(1));
                            cart.setOrderStatus(cursor.getString(2));
                            cart.setOrderTotal(cursor.getDouble(4));
                            Log.d(null,"Total price db: " + cart.getOrderTotal());
                            cart.setOrderImageURL(cursor1.getString(0));
                            orderHistoryList.add(cart);

                        } while (cursor1.moveToNext());
                    }
                // Adding contact to list
            } while (cursor.moveToNext());

        }
        // return contact list
        return orderHistoryList;
    }

    //Get order details
    public List<CartItem> getAllOrderDetails(String ref) {
        List<CartItem> cartList = new ArrayList<CartItem>();
        // Select All Query
        String selectQuery = "SELECT " + KEY_ORDER_PRODUCTIDS + ", " + KEY_ORDER_SIZES + ", " + KEY_ORDER_QTY + ", " + KEY_ORDER_PRICES
                + " FROM " + TABLE_ORDER + " WHERE " + KEY_ORDER_REFERENCE + " = '" + ref + "'" ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            String[] sizes = cursor.getString(1).split(",");
            String[] qty = cursor.getString(2).split(",");
            String[] prices = cursor.getString(3).split(",");
            String productids = cursor.getString(0);
            String[] ids = cursor.getString(0).split(",");
            Log.d(null, cursor.getString(0));

            String selectQuery1 = "SELECT " + KEY_PRODUCT_COLLECTION + ", " + KEY_PRODUCT_NAME + ", " + KEY_PRODUCT_IMAGEURL + " FROM " + TABLE_PRODUCT + " WHERE "+KEY_PRODUCT_PRODUCTID+" IN (" + productids + ") " ;
            SQLiteDatabase db1 = this.getWritableDatabase();
            Cursor cursor1 = db1.rawQuery(selectQuery1, null);
            Log.d(null, selectQuery1);
            // looping through all rows and adding to list
            if (cursor1.moveToFirst()) {
                int i = 0;
                do {
                    //Log.d(null, cursor1.getString(0));
                    CartItem cart = new CartItem();
                    cart.setProduct_id(Integer.parseInt(ids[i]));
                    Log.d(null,"product db : " + Integer.parseInt(ids[i]) );
                    cart.setCollection(cursor1.getString(0));
                    cart.setName(cursor1.getString(1));
                    cart.setImageURL(cursor1.getString(2));
                    cart.setPrice(Double.parseDouble(prices[i]));
                    cart.setSize(sizes[i]);
                    cart.setQty(Integer.parseInt(qty[i]));
                    Log.d(null, "In db handler get item: \n Collection : " + cursor1.getString(0) + "\n Name: " + cursor1.getString(1) + "\n Image : " + cursor1.getString(2) + "\n Price: " + Double.parseDouble(prices[i]) + "\n Size" + sizes[i] + "\n Qty: " + Integer.parseInt(qty[i]) );
                    // Adding contact to list
                    cartList.add(cart);
                    i++;
                } while (cursor1.moveToNext());

            }
        }

        // return contact list
        return cartList;

    }

    //insert into order table
    public void addToOrder(int user_id, String user_address, String orderProducts, String orderSizes, String orderQty, String orderPrices, double orderTotal, String orderStatus, String orderDate, String orderRef ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ORDER_USERID, user_id);
        values.put(KEY_ORDER_ADDRESS, user_address);
        values.put(KEY_ORDER_PRODUCTIDS, orderProducts);
        values.put(KEY_ORDER_SIZES, orderSizes);
        values.put(KEY_ORDER_QTY, orderQty);
        values.put(KEY_ORDER_PRICES, orderPrices);
        values.put(KEY_ORDER_TOTAL, orderTotal);
        values.put(KEY_ORDER_STATUS, orderStatus);
        values.put(KEY_ORDER_DATE, orderDate);
        values.put(KEY_ORDER_REFERENCE, orderRef);
        db.insertWithOnConflict(TABLE_ORDER, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        db.close();
    }


    //delete everything from cart
    public void deleteAllFromCart() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_CART );
        db.close();
    }

    //Delete item from cart
    public void deleteOneFromCart(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_CART + " WHERE " + KEY_CART_PRODUCTID + " = " +  id);
        db.close();
    }

}
