package com.example.administrator.englishnewsreader.util;

import android.text.Html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/5.
 */
public class HTMLParser {
    private Document doc;
    private HTMLParser(){
    }
    public List<String> getSection(String address) throws IOException {
        LinkedList<String> list_section = new LinkedList<>();
        URL url = new URL(address);
        doc = Jsoup.parse(url,2000);
        Elements elements = doc.getElementsByTag("section");
        for (Element e : elements){
            System.out.println(e.data());
        }
        return list_section;
    }


}
