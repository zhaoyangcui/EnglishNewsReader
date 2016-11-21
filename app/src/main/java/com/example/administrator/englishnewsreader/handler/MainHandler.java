package com.example.administrator.englishnewsreader.handler;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.StringBuilderPrinter;

import com.example.administrator.englishnewsreader.R;
import com.example.administrator.englishnewsreader.activity.DrawerLayoutActivity;
import com.example.administrator.englishnewsreader.data.Sections;
import com.example.administrator.englishnewsreader.fragment.PhotoFragment;
import com.example.administrator.englishnewsreader.fragment.SectionsFragment;
import com.example.administrator.englishnewsreader.util.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/9.
 */
public class MainHandler extends Handler{
    private Context context;

    public MainHandler(Looper looper, Context context) {
        super(looper);
        this.context = context;
    }
    private Sections sections;

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (msg.what){
            case 0:
                Log.d("debug","00000000000");
                StringBuilder builder = (StringBuilder) msg.obj;
                Log.d("debug","handler");
                String featured_json = builder.toString();
                Map<String,JSONArray> m = JsonParser.jsonParse2Array(featured_json, new String[]{"main_title", "img_href", "article_title", "article_href"});
                //get main title
                LinkedList<String> main_title_list = JsonParser.jsonParseArray2List(m.get("main_title"),"main_title");
                Log.d("debug","main_title"+main_title_list.size());
                for (String s : main_title_list){
                    Log.d("debug",s);
                }
                LinkedList<String> article_title_list = JsonParser.jsonParseArray2List(m.get("article_title"),"article_title");
                for (String s : article_title_list){
                    Log.d("debug",s);
                }
                LinkedList<String> article_href_list = JsonParser.jsonParseArray2List(m.get("article_href"),"article_href");
                for (String s : article_href_list){
                    Log.d("debug",s);
                }
                LinkedList<String> img_src_list = JsonParser.jsonParseArray2List(m.get("img_href"),"img_href");
                for (String s : img_src_list){
                    Log.d("debug",s);
                }
                sections = new Sections();
                sections.setMain_title(main_title_list);
                sections.setImg_src(img_src_list);
                sections.setArticle_title(article_title_list);
                sections.setArticle_href(article_href_list);

                Fragment sections_fragment = SectionsFragment.newInstance(sections);
                ((DrawerLayoutActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,sections_fragment).commit();
                break;
            case 1:
                Log.d("debug","11111111");
                sections = new Sections();
                StringBuilder photo_builder = (StringBuilder) msg.obj;
                Map<String,JSONArray> map = JsonParser.jsonParse2Array(photo_builder.toString(),new String[]{"img_src","describe"} );
                sections.setPhoto_img_src(JsonParser.jsonParseArray2List(map.get("img_src"),"img_src"));
                sections.setDescribe(JsonParser.jsonParseArray2List(map.get("describe"),"describe"));
                Log.d("debug","imgsrc"+sections.getPhoto_img_src().size());
                Log.d("debug","describe"+sections.getDescribe().size());
                Fragment photo_fragment = PhotoFragment.newInstance(sections);
                ((DrawerLayoutActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,photo_fragment).commit();
                break;
        }

    }

    public Sections getSections() {
        return sections;
    }
}
