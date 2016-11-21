package com.example.administrator.englishnewsreader.fragment;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.administrator.englishnewsreader.R;
import com.example.administrator.englishnewsreader.activity.DrawerLayoutActivity;
import com.example.administrator.englishnewsreader.adapter.SectionsContentAdapter;
import com.example.administrator.englishnewsreader.data.Sections;
import com.example.administrator.englishnewsreader.web.HTTPGetter;
import java.net.MalformedURLException;
import java.util.LinkedList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SectionsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SectionsFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class SectionsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    // TODO: Rename and change types of parameters
    private Sections sections_data;
    private static String SECTION  = "SECTION";
    private ViewPager mViewPager;
    private LinkedList<String> tabs;
    private LinkedList<android.support.v4.app.Fragment> fragmentList;
    private SectionsContentAdapter adapter;
//    private NewsFragmentPagerAdapter adapter;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SectionsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SectionsFragment newInstance(Sections sections) {
        SectionsFragment fragment = new SectionsFragment();
        Bundle args = new Bundle();
        Log.d("debug",sections == null?"sections null":"sections not null");
        args.putSerializable(SECTION,sections);
        fragment.setArguments(args);
        return fragment;
    }
    public SectionsFragment() {
        // Required empty public constructor
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            sections_data = (Sections) getArguments().getSerializable(SECTION);
            Log.d("debug",sections_data == null?"null":"not null");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sections, container, false);
        mViewPager = (ViewPager) view.findViewById(R.id.vp);
        initViewPager();
        return view;
    }

    private void initViewPager(){
        setTab();
        setFragmentList();
        adapter = new SectionsContentAdapter(super.getActivity().getSupportFragmentManager(),fragmentList,tabs);
        mViewPager.setAdapter(adapter);
    }

    private void setFragmentList(){
        fragmentList = new LinkedList<>();
        LinkedList<String> img_src = sections_data.getImg_src();
        LinkedList<String> article_title = sections_data.getArticle_title();
        int num = article_title.size() / 4;
        LinkedList<String> article_href = sections_data.getArticle_href();
        for (int i = 0;i < img_src.size();i ++){
            LinkedList<String> content_img = new LinkedList<>();
            LinkedList<String> content_title = new LinkedList<>();
            LinkedList<String> content_href = new LinkedList<>();
            content_img.add(img_src.get(i));
            for (int i_title = 0;i_title < num;i_title ++){
                content_title.add(article_title.removeFirst());
                content_href.add(article_href.removeFirst());
            }
            Sections sections_content = new Sections();
            sections_content.setImg_src(content_img);
            sections_content.setArticle_title(content_title);
            sections_content.setArticle_href(content_href);
            SectionsContentFragment contentFragment = SectionsContentFragment.newInstance(sections_content);
            fragmentList.add(contentFragment);
        }
    }

    private void setTab(){
        Log.d("debug","tabs"+sections_data.getMain_title().size()+"////////////");
        tabs = sections_data.getMain_title();
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
