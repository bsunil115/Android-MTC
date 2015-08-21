package com.example.delevere4.mymtc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by delevere4 on 4/8/15.
 */
public class HowAppworksFragment extends android.app.Fragment {

    public HowAppworksFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.howappworks, container, false);

        return rootView;
    }
}

