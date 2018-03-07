package com.example.hp.madose.model;

/**
 * Created by erick on 24/02/2018.
 */

public class Item {
    private String text,subtext,stock;
    private int image;
    private boolean isExpandable;

    public Item(String text, String subtext, int image, String stock, boolean isExpandable) {
        this.text = text;
        this.subtext = subtext;
        this.image=image;
        this.stock=stock;
        this.isExpandable = isExpandable;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getImage() {
        return image;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getSubtext() {
        return subtext;
    }

    public void setSubtext(String subtext) {
        this.subtext = subtext;
    }

    public boolean isExpandable() {
        return isExpandable;
    }

    public void setExpandable(boolean expandable) {
        isExpandable = expandable;
    }
}
