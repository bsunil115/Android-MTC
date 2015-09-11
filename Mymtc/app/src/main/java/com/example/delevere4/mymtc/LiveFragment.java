package com.example.delevere4.mymtc;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * Created by delevere4 on 25/8/15.
 */
public class LiveFragment extends android.app.Fragment {
    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
     Spinner spinnerOsversions;
    static final String[] busno = new String[] {  "Select Bus Number","6", "6A/H","10A","10K","12D","12K","14","16","17K","1T","201V","20A","210","211","222","234Y",
            "25A","25B","25D","25D/V","25D/M","25E","25G","25G/R","25IT","25J","25K","25M","25P","25U","28A/28K","28A/D","28A/P","28C","28J",
            "28P","28R","28Z/H","300C","300M","30A","31","328","333","333k","341","35","36","38","38A","38B","38C","38D","38H","38J",
            "38K","38M","38N","38R","38T","38U","38Y","38Y/F","38Z","400","400H","400H","400K","400N","400S","400T","400Y","404","48","48A",
            "500A","500A/D","500P","500Y","505","51","52D","60","600","600C","60C","60R","63","64","65F","666","68/68K","69","6B","6D","700",
            "747","77","777","77T","844","888","900","900K","900T","99","999","99A/C","99K","9E"
    };;
    public LiveFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.livefragment, container, false);
        spinnerOsversions = (Spinner) rootView.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(this.getActivity(),
                R.layout.simple_spinner_item, busno);
        adapter_state
                .setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spinnerOsversions.setAdapter(adapter_state);

        Button btn = (Button) rootView.findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int v2 =   spinnerOsversions.getSelectedItemPosition();
                String tmpStr10 = Integer.toString(v2);
                String result = null;
                InputStream is = null;
                TextView tv=(TextView) rootView.findViewById(R.id.textView5);

                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
//                Toast.makeText(getActivity(), v2, Toast.LENGTH_SHORT).show();
                nameValuePairs.add(new BasicNameValuePair("f1",tmpStr10));
                StrictMode.setThreadPolicy(policy);
                try
                {
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppost = new HttpPost("http://www.ers.hol.es/select.php");
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse response = httpclient.execute(httppost);
                    HttpEntity entity = response.getEntity();
                    is = entity.getContent();

                    Log.e("log_tag", "connection success ");
                    Toast.makeText(getActivity(), "Connection pass", Toast.LENGTH_SHORT).show();
                }
                catch(Exception e)
                {
                    Log.e("log_tag", "Error in http connection "+e.toString());
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
                }
                catch(Exception e)
                {
                    Log.e("log_tag", "Error converting result "+e.toString());

                    Toast.makeText(getActivity(), " Input reading fail", Toast.LENGTH_SHORT).show();

                }

                //parse json data
                try{

                    Toast.makeText(getActivity(),result, Toast.LENGTH_SHORT).show();
                    JSONObject object = new JSONObject(result);
                    String ch=object.getString("re");
                    Toast.makeText(getActivity(), ch, Toast.LENGTH_SHORT).show();
                    if(ch.equals("success"))
                    {

                        JSONObject no = object.getJSONObject("0");

                        //long q=object.getLong("f1");
                        String w= no.getString("f2");
                      //  Toast.makeText(getActivity(), w, Toast.LENGTH_SHORT).show();
                       // long e=no.getLong("f3");

                        tv.setText(w);
                       // String myString = NumberFormat.getInstance().format(e);


                       // tv.setText(myString);

                    }


                    else
                    {

                        Toast.makeText(getActivity(), "Record is not available.. Enter valid number", Toast.LENGTH_SHORT).show();

                    }


                }
                catch(JSONException e)
                {
                    Log.e("log_tag", "Error parsing data "+e.toString());
                    Toast.makeText(getActivity(), "JsonArray fail", Toast.LENGTH_SHORT).show();
                }


            }
        });

        return rootView;
    }
}

