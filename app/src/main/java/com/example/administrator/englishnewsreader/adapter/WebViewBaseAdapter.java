package com.example.administrator.englishnewsreader.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.englishnewsreader.R;

import java.security.BasicPermission;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/10.
 */
public class WebViewBaseAdapter extends BaseAdapter{
    private Context context;
    private LinkedList<HashMap<String,String>> data;
    private LayoutInflater inflater;

    public WebViewBaseAdapter(Context context, LinkedList<HashMap<String,String>> data){
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = inflater.inflate(R.layout.list_view_photo_item,null);
        WebView wv = (WebView) item.findViewById(R.id.wv_photo);
        TextView tv = (TextView) item.findViewById(R.id.tv_photo);
        wv.loadUrl((String) data.get(position).get("img_src"));
        tv.setText((String)data.get(position).get("describe"));
        return item;
    }
}
