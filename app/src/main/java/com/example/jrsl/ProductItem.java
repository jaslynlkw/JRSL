package com.example.jrsl;

public class ProductItem {
    private int product_id;
    private String collection;
    private String name;
    private String desc;
    private double price;
    private int image;
    private String category;
    private int savedStatus;

    public ProductItem(int product_id, String collection, String name, String desc, double price, int image, String category, int savedStatus){
        this.product_id = product_id;
        this.collection = collection;
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.image = image;
        this.category = category;
        this.savedStatus = savedStatus;
    }

    public ProductItem(int product_id, String collection, String name, double price, int image, String category, int savedStatus){
        this.product_id = product_id;
        this.collection = collection;
        this.name = name;
        this.price = price;
        this.image = image;
        this.category = category;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getSavedStatus() {
        return savedStatus;
    }

    public void setSavedStatus(int savedStatus) {
        this.savedStatus = savedStatus;
    }
}
