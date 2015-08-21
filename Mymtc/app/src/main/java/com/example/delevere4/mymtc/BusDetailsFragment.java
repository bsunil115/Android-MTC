package com.example.delevere4.mymtc;

import android.app.Fragment;
import android.app.ListActivity;
import android.app.ListFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.delevere4.mymtc.R.layout.busdetail;

/**
 * Created by delevere4 on 4/8/15.
 */
public class BusDetailsFragment extends ListFragment {
    static final String[] FRUITS = new String[] { "6", "6A", "540",
            "38", "38J", "28", "25it", "400A",
            "25R", "222", "999", "211", "500" };
    ArrayAdapter<String> adapter;
    Button btnClosePopup;
     PopupWindow popWindow;
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Toast.makeText(getActivity(),"You Clicked:"+FRUITS[position],Toast.LENGTH_SHORT).show();
        //initiatePopupWindow();
        showPopup(v,position);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       // View rootView = inflater.inflate(busdetail,
         //       container, false);

       // List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();
      //  for(int i=0;i<10;i++){
       //     HashMap<String, String> hm = new HashMap<String,String>();
       //     hm.put("txt", "Bus No: : " + FRUITS[i]);


         //   aList.add(hm);
       //// }
        String[] from = {"txt"};
      //  int[] to = {R.id.txt};
      //  SimpleAdapter adapter = new SimpleAdapter(getActivity().getBaseContext(), aList, R.layout.busdetail, from, to);
        adapter = new ArrayAdapter<String>(
                inflater.getContext(),R.layout.simple_list_item_1,
                FRUITS);
        //Button button = (Button) container.findViewById(R.id.button);

        setListAdapter(adapter);
         //setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1 , FRUITS));
    //    Button button = (Button) container.findViewById(R.id.button);



       // ListView listView = getListView();
      //  listView.setTextFilterEnabled(true);
      // button.setOnClickListener(new View.OnClickListener() {

      //  public void onClick(View arg0) {

                  // When clicked, show a toast with the TextView text
        //      Toast.makeText(getActivity(),"Text!",Toast.LENGTH_SHORT).show();
            //   updateDetail();
     //  }
     //  });

            return super.

            onCreateView(inflater, container, savedInstanceState);
        }

    public void showPopup(View anchorView,int position) {

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View popupView = inflater.inflate(R.layout.screen_popup, null);

        popWindow = new PopupWindow(popupView,
                AbsListView.LayoutParams.FILL_PARENT, AbsListView.LayoutParams.FILL_PARENT);

        // Example: If you have a TextView inside `popup_layout.xml`
        TextView tv = (TextView) popupView.findViewById(R.id.txtView);

        tv.setText("You Clicked:" + FRUITS[position]);

        // Initialize more widgets from `popup_layout.xml`


        // If the PopupWindow should be focusable
        popWindow.setFocusable(true);

        // If you need the PopupWindow to dismiss when when touched outside
        popWindow.setBackgroundDrawable(new ColorDrawable());
        btnClosePopup = (Button) popupView.findViewById(R.id.btn_close_popup);
        btnClosePopup.setOnClickListener(cancel_button_click_listener);
        int location[] = new int[2];

        // Get the View's(the one that was clicked in the Fragment) location
        anchorView.getLocationOnScreen(location);

        // Using location, the PopupWindow will be displayed right under anchorView
        popWindow.showAtLocation(anchorView, Gravity.NO_GRAVITY,
                location[0], location[1] + anchorView.getHeight());





    }


    private View.OnClickListener cancel_button_click_listener = new View.OnClickListener() {
        public void onClick(View v) {
            popWindow.dismiss();

        }
    };



    public void updateDetail() {
       // Intent intent = new Intent(getActivity(), Selectdb.class);
       // startActivity(intent);
        Intent i = new Intent(getActivity().getApplication(),Selectdb.class);
        startActivity(i);
    }
    }

