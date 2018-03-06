package com.example.danielcampbell.group21_hw5;

import java.io.Serializable;

/**
 * Created by danielcampbell on 10/27/17.
 */

public class Podcast implements Serializable {

    String pc_title;
    String pc_updateDate;
    String pc_img;
    String pc_summary;
    String pc_Limg;

    //blank constructor
    public Podcast() {
    }

    //constructor
    public Podcast(String pc_title, String pc_updateDate, String pc_img, String pc_Limg, String pc_summary) {
        this.pc_title = pc_title;
        this.pc_updateDate = pc_updateDate;
        this.pc_img = pc_img;
        this.pc_summary = pc_summary;
        this.pc_Limg = pc_Limg;
    }

    //getters + setters

    public String getPc_title() {
        return pc_title;
    }

    public void setPc_title(String pc_title) {
        this.pc_title = pc_title;
    }

    public String getPc_updateDate() {
        return pc_updateDate;
    }

    public void setPc_updateDate(String pc_updateDate) {
        this.pc_updateDate = pc_updateDate;
    }

    public String getPc_img() {
        return pc_img;
    }

    public void setPc_img(String pc_img) {
        this.pc_img = pc_img;
    }

    public String getPc_summary() {
        return pc_summary;
    }

    public void setPc_summary(String pc_summary) {
        this.pc_summary = pc_summary;
    }

    public String getPc_Limg() {
        return pc_Limg;
    }

    public void setPc_Limg(String pc_Limg) {
        this.pc_Limg = pc_Limg;
    }

    @Override
    public String toString() {
        return "Podcast{" +
                "pc_title='" + pc_title + '\'' +
                ", pc_updateDate='" + pc_updateDate + '\'' +
                ", pc_img='" + pc_img + '\'' +
                ", pc_summary='" + pc_summary + '\'' +
                ", pc_Limg='" + pc_Limg + '\'' +
                '}';
    }


}
