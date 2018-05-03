package com.terminoz.scrapopedia;

import android.graphics.Bitmap;
import android.util.Log;

public class Product {

    private int id;
    private String title,desc;
    private Bitmap bitmap;
    private int image;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getImage() {
        return image;
    }

    public Product(int id, String title, String desc, Bitmap bitmap, int image) {

        this.id = id;
        this.title = title;
        this.desc = desc;
        this.bitmap = bitmap;
        this.image = image;
    }
}
