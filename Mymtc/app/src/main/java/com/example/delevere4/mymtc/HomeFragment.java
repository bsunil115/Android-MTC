package com.example.delevere4.mymtc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by delevere4 on 4/8/15.
 */
public class HomeFragment extends android.app.Fragment {

    public HomeFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int position = 0;
        //mainlayout.setVisibility(RelativeLayout.GONE);
       // String[] postion1= getResources().getStringArray(R.array.option);
       View rootView = inflater.inflate(R.layout.activity_main, container, false);
      TextView tv = (TextView) rootView.findViewById(R.id.tv);

        // Setting currently selected river name in the TextView
     tv.setText("Home");

        // Updating the action bar title
       // getSupportActionBar().setTitle(mActivityTitle);

        //getActivity().getActionBar().setTitle(postion1[position]);
        return rootView;
    }
}
