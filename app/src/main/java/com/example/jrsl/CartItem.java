package com.example.jrsl;

public class CartItem {

    private int product_id;
    private String collection;
    private String size;
    private String name;
    private int qty;
    private double price;
    private String imageURL;

    public CartItem(int product_id, String collection, String name, String size, int qty, double price, String imageURL){
        this.product_id = product_id;
        this.collection = collection;
        this.name = name;
        this.size = size;
        this.qty = qty;
        this.price = price;
        this.imageURL = imageURL;
    }

    public CartItem(String collection, String name, String size, int qty, double price, String imageURL){
        this.collection = collection;
        this.name = name;
        this.size = size;
        this.qty = qty;
        this.price = price;
        this.imageURL = imageURL;
    }

    public CartItem(String collection, String name, double price, String imageURL){
        this.collection = collection;
        this.name = name;
        this.price = price;
        this.imageURL = imageURL;
    }

    public CartItem() {

    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
