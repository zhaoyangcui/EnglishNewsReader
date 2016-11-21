package com.example.englishnews;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ReadFragment extends Fragment {

    @Override
    public void onAttach(Activity activity) {
        // TODO Auto-generated method stub
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView tvTitle=new TextView(super.getActivity());
        tvTitle.setText("阅读");
        tvTitle.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
        tvTitle.setBackgroundColor(Color.WHITE);
        return tvTitle;
    }
    @Override
    public void setArguments(Bundle args) {
        // TODO Auto-generated method stub
        super.setArguments(args);
    }

}
