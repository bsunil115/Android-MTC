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
import android.os.StrictMode;
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

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.delevere4.mymtc.R.layout.busdetail;

public class BusDetailsFragment extends ListFragment {
    static final String[] busno = new String[] { "6", "6A/H","10A","10K","12D","12K","14","16","17K","1T","201V","20A","210","211","222","234Y",
            "25A","25B","25D","25D/V","25D/M","25E","25G","25G/R","25IT","25J","25K","25M","25P","25U","28A/28K","28A/D","28A/P","28C","28J",
            "28P","28R","28Z/H","300C","300M","30A","31","328","333","333k","341","35","36","38","38A","38B","38C","38D","38H","38J",
            "38K","38M","38N","38R","38T","38U","38Y","38Y/F","38Z","400","400H","400H","400K","400N","400S","400T","400Y","404","48","48A",
            "500A","500A/D","500P","500Y","505","51","52D","60","600","600C","60C","60R","63","64","65F","666","68/68K","69","6B","6D","700",
            "747","77","777","77T","844","888","900","900K","900T","99","999","99A/C","99K","9E"
            };
    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    ArrayAdapter<String> adapter;
    Button btnClosePopup;
     PopupWindow popWindow;
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {



       // Toast.makeText(getActivity(),"You Clicked:"+FRUITS[position],Toast.LENGTH_SHORT).show();



        //initiatePopupWindow();
        showPopup(v,position);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Toast.makeText(getActivity(),"Select bus number",Toast.LENGTH_SHORT).show();

        String[] from = {"txt"};
      //  int[] to = {R.id.txt};
      //  SimpleAdapter adapter = new SimpleAdapter(getActivity().getBaseContext(), aList, R.layout.busdetail, from, to);
        adapter = new ArrayAdapter<String>(
                inflater.getContext(),R.layout.simple_list_item_1,
                busno);
        //Button button = (Button) container.findViewById(R.id.button);

        setListAdapter(adapter);


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

        tv.setText("You Clicked:" + busno[position]);

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

        String result = null;
        InputStream is = null;
        //    TextView tv=(TextView) rootView.findViewById(R.id.textView5);
        String tmpStr10 = busno[position];
        Toast.makeText(getActivity(), tmpStr10, Toast.LENGTH_SHORT).show();
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
//                Toast.makeText(getActivity(), v2, Toast.LENGTH_SHORT).show();
        nameValuePairs.add(new BasicNameValuePair("f1", tmpStr10));
        //nameValuePairs.add(new BasicNameValuePair("f2", tmpStr11));
        StrictMode.setThreadPolicy(policy);
        HttpEntity entity = null;
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://www.ers.hol.es/select2.php");
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            entity = response.getEntity();
            is = entity.getContent();

            Log.e("log_tag", "connection success ");
            Toast.makeText(getActivity(), "Connection pass", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e("log_tag", "Error in http connection " + e.toString());
            Toast.makeText(getActivity(), "Connection fail", Toast.LENGTH_SHORT).show();

        }
        //convert response to string
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"),8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            int i=0;
            while ((line = reader.readLine()) != null)
            {

                sb.append(line + "\n");


            }
            is.close();

            result=sb.toString();
            // Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();
        }
        catch(Exception e)
        {
            Log.e("log_tag", "Error converting result "+e.toString());

            Toast.makeText(getActivity(), " Input reading fail", Toast.LENGTH_SHORT).show();

        }
        // try {
        //      result = EntityUtils.toString(entity);
        //   } catch (IOException e) {
        //      e.printStackTrace();
        //  }

        //parse json data
        try {

         //   Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();
            //
            JSONObject object = new JSONObject(result.substring(result.indexOf("{"), result.lastIndexOf("}") + 1));
            Toast.makeText(getActivity(), "done", Toast.LENGTH_SHORT).show();
            String ch = object.getString("re");
            Toast.makeText(getActivity(), ch, Toast.LENGTH_SHORT).show();
            if (ch.equals("success")) {

                JSONObject no = object.getJSONObject("0");

                //long q=object.getLong("f1");
                String w = no.getString("from_location");
                tv.setText(w);
                //  Toast.makeText(getActivity(), w, Toast.LENGTH_SHORT).show();
                // long e=no.getLong("f3");
                TextView tv1 = (TextView) popupView.findViewById(R.id. textView7);
                String w2 = no.getString("to_location");
                tv1.setText(w2);

                TextView tv2 = (TextView) popupView.findViewById(R.id. textView9);
                String w1 = no.getString("route");
                tv2.setText(w1);
                //tv2.setText("Via :"+w1);
                // String myString = NumberFormat.getInstance().format(e);


                // tv.setText(myString);

            } else {
                tv.setText("No Bus Found");
                //    Toast.makeText(getActivity(), "Record is not available.. Enter valid number", Toast.LENGTH_SHORT).show();

            }


        } catch (JSONException e) {
            Log.e("log_tag", "Error parsing data " + e.toString());
            Toast.makeText(getActivity(), "JsonArray fail" + e, Toast.LENGTH_SHORT).show();
        }



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

