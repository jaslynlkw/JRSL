package com.example.jrsl;

public class CartItem {
    private String collectionType;
    private String brand;
    private String desc;
    private String size;
    private int qty;
    private double price;
    private int image;
    public CartItem(String collectionType, String brand, String desc, String size, int qty, double price, int image){
        this.collectionType = collectionType;
        this.brand = brand;
        this.desc = desc;
        this.size = size;
        this.qty = qty;
        this.price = price;
        this.image = image;
    }

    public String getCollectionType() {
        return collectionType;
    }

    public String getBrand() {
        return brand;
    }

    public String getDesc() {
        return desc;
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

    public int getImage() {
        return image;
    }
}
