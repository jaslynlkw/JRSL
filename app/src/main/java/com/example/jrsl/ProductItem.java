package com.example.jrsl;

public class ProductItem {
    private int product_id;
    private String collection;
    private String name;
    private String desc;
    private String size;
    private int qty;
    private double price;
    private int image;
    private int savedStatus;

    public ProductItem(int product_id, String collection, String name, String desc, String size, int qty, double price, int image, int savedStatus){
        this.product_id = product_id;
        this.collection = collection;
        this.name = name;
        this.desc = desc;
        this.size = size;
        this.qty = qty;
        this.price = price;
        this.image = image;
        this.savedStatus = savedStatus;
    }

    public ProductItem(int product_id, String collection, String name, double price, int image, int savedStatus){
        this.product_id = product_id;
        this.collection = collection;
        this.name = name;
        this.price = price;
        this.image = image;
        this.savedStatus = savedStatus;
    }

    public ProductItem() {

    }

    public int getProductID() {
        return product_id;
    }

    public void setProductID(int product_id) {
        this.product_id = product_id;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getSavedStatus() {
        return savedStatus;
    }

    public void setSavedStatus(int savedStatus) {
        this.savedStatus = savedStatus;
    }
}
