package com.example.administrator.englishnewsreader.util;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
/**
 * Created by Administrator on 2016/11/9.
 */
public class JsonParser {
    private static LinkedList<String> list_main_title;
    private static LinkedList<String> list_article_title;
    private static LinkedList<String> list_article_href;
    private static LinkedList<String> list_img_src;

    public static Map<String, JSONArray> jsonParse2Array(String featuredSection_json, String[] array_key_list){
        Map<String,JSONArray> json_array_map = new HashMap<>();
        try {
            JSONObject jsonObject = new JSONObject(featuredSection_json);
            JSONArray array;
            for (String s : array_key_list){
                array = jsonObject.getJSONArray(s);
                json_array_map.put(s,array);
            }
            return json_array_map;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static LinkedList<String> jsonParseArray2List(JSONArray json_array, String key){
        LinkedList<String> content_list = new LinkedList();
        try {
            JSONObject object = null;
            for (int i = 0;i < json_array.length();i ++){
                object = json_array.getJSONObject(i);
                content_list.add(object.getString(key));
            }
            return content_list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
