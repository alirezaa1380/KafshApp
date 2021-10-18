package com.example.kafshapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Post {
    @PrimaryKey(autoGenerate = true)
    private  int id;
    private String title;
    private String image;
    private  int type;
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    private int colornumber;
    private String color3;
    private int colornumber2;
    private String color4;
    private int colornumber3;
    private String color5;
    private int colornumber4;
    private int price;
    private int off;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getColornumber() {
        return colornumber;
    }

    public void setColornumber(int colornumber) {
        this.colornumber = colornumber;
    }

    public String getColor3() {
        return color3;
    }

    public void setColor3(String color3) {
        this.color3 = color3;
    }

    public int getColornumber2() {
        return colornumber2;
    }

    public void setColornumber2(int colornumber2) {
        this.colornumber2 = colornumber2;
    }

    public String getColor4() {
        return color4;
    }

    public void setColor4(String color4) {
        this.color4 = color4;
    }

    public int getColornumber3() {
        return colornumber3;
    }

    public void setColornumber3(int colornumber3) {
        this.colornumber3 = colornumber3;
    }

    public String getColor5() {
        return color5;
    }

    public void setColor5(String color5) {
        this.color5 = color5;
    }

    public int getColornumber4() {
        return colornumber4;
    }

    public void setColornumber4(int colornumber4) {
        this.colornumber4 = colornumber4;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getOff() {
        return off;
    }

    public void setOff(int off) {
        this.off = off;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
