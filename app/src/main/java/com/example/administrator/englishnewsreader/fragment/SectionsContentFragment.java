package com.example.administrator.englishnewsreader.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.administrator.englishnewsreader.R;
import com.example.administrator.englishnewsreader.data.Sections;
import com.example.administrator.englishnewsreader.util.ImgTask;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SectionsContentFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SectionsContentFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class SectionsContentFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    // TODO: Rename and change types of parameters

    private OnFragmentInteractionListener mListener;
    private static String SECTIONS = "SECTIONS";
    private Sections sections;
    public static Handler handler;
    private HashMap<String,String> title_href_map;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment SectionsContentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SectionsContentFragment newInstance(Sections param1) {
        SectionsContentFragment fragment = new SectionsContentFragment();
        Bundle args = new Bundle();
        args.putSerializable(SECTIONS, param1);
        fragment.setArguments(args);
        return fragment;
    }
    public SectionsContentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            sections = (Sections) getArguments().getSerializable(SECTIONS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sections_content, container, false);
        final ImageView iv = (ImageView) view.findViewById(R.id.image_view);
//        Log.d("debug",sections.getImg_src().getFirst());
        handler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Bitmap map = (Bitmap) msg.obj;
                iv.setImageBitmap(map);
            }
        };
        Message msg = ImgTask.handler_img.obtainMessage();
        msg.obj = sections.getImg_src().getFirst();
        ImgTask.handler_img.sendMessage(msg);
        addArticle(view);
        return view;
    }

    private void addArticle(View view){
        LinearLayout main_linear = (LinearLayout) view.findViewById(R.id.ll_main);
        main_linear.setBackgroundColor(Color.parseColor("#ADD8E6"));
        LinkedList<String> article_title = sections.getArticle_title();
        LinkedList<String> article_href = sections.getArticle_href();
        title_href_map = new HashMap<>();
        while(article_title.size() != 0){
            title_href_map.put(article_title.removeFirst(),article_href.removeFirst());
        }
        LinkedList<LinkedHashMap<String,String>> data = new LinkedList<>();
        LinkedHashMap<String,String> item;
        for (String s : title_href_map.keySet()){
            item = new LinkedHashMap<>();
            item.put("title","       "+s);
            data.add(item);
        }
        //创建一个ListView显示。
        ListView listView = (ListView) view.findViewById(R.id.lv);
        SimpleAdapter adapter = new SimpleAdapter(getContext(),data,R.layout.list_view_item,new String[]{"title"},new int[]{R.id.tv_list_view_item});
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String href = title_href_map.get(((TextView)view.findViewById(R.id.tv_list_view_item)).getText().toString().trim());
                if (!href.matches("http://.+")){
                    href = "http://edition.cnn.com"+href;
                }
                final Uri uri = Uri.parse(href);
                final Intent it = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(it);
            }
        });
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
