package com.example.administrator.englishnewsreader.data;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created by Administrator on 2016/11/9.
 */
public class Sections implements Serializable{
    private LinkedList<String> main_title;
    private LinkedList<String> img_src;
    private LinkedList<String> article_title;
    private LinkedList<String> article_href;
    private LinkedList<String> photo_img_src;
    private LinkedList<String> describe;

    public LinkedList<String> getPhoto_img_src() {
        return photo_img_src;
    }

    public LinkedList<String> getDescribe() {
        return describe;
    }

    public void setPhoto_img_src(LinkedList<String> photo_img_src) {
        this.photo_img_src = photo_img_src;
    }

    public void setDescribe(LinkedList<String> describe) {
        this.describe = describe;
    }

    public Sections(){

    }

    public LinkedList<String> getMain_title() {
        return main_title;
    }

    public LinkedList<String> getImg_src() {
        return img_src;
    }

    public LinkedList<String> getArticle_title() {
        return article_title;
    }

    public LinkedList<String> getArticle_href() {
        return article_href;
    }

    public void setMain_title(LinkedList<String> main_title) {
        this.main_title = main_title;
    }

    public void setImg_src(LinkedList<String> img_src) {
        this.img_src = img_src;
    }

    public void setArticle_title(LinkedList<String> article_title) {
        this.article_title = article_title;
    }

    public void setArticle_href(LinkedList<String> article_href) {
        this.article_href = article_href;
    }
}
