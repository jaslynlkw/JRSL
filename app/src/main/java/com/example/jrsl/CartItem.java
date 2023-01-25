package com.example.jrsl;

public class CartItem {
    private String collection;
    private String size;
    private String name;
    private int qty;
    private double price;
    private String imageURL;

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

    public String getCollection() {
        return collection;
    }

    public String getName() {
        return name;
    }

    public String getSize() {
        return size;
    }

    public int getQty() {
        return qty;
    }

    public double getPrice() {
        return price;
    }

    public String getImageURL() {
        return imageURL;
    }
}
