package com.example.jrsl;

public class SliderData {
    // image url is used to
    // store the url of image
    private int imgUrl;
    private String imgTitle;
    private String imgDesc;

    // Constructor method.
    public SliderData(int imgUrl, String imgTitle, String imgDesc) {

        this.imgUrl = imgUrl;
        this.imgTitle = imgTitle;
        this.imgDesc = imgDesc;
    }

    // Getter method
    public int getImgUrl() {

        return imgUrl;
    }

    public String getImgTitle() {
        return imgTitle;
    }

    public String getImgDesc() {
        return imgDesc;
    }

    public void setImgDesc(String imgDesc) {
        this.imgDesc = imgDesc;
    }

    // Setter method
    public void setImgUrl(int imgUrl) {

        this.imgUrl = imgUrl;
    }

    public void setImgTitle(String imgTitle) {
        this.imgTitle = imgTitle;
    }

}
