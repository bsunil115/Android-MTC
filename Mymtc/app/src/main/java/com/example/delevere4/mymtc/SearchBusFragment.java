package com.example.delevere4.mymtc;

import android.app.Fragment;
import android.app.ListFragment;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
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
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SearchBusFragment extends Fragment {
    public SearchBusFragment() {
    }
    int j;
    String tmpStr10;
    String tmpStr11;
    Spinner spinnerOsversions;
    Spinner spinnerOsversions1;
    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
   //  String FRUITS1 ;
/*    static final String[] source = new String[] {"Select Source", "Anakapalle", "Arilova Colony", "Bhimili",
            "Collector Office", "Fishing Harbour", "HB Colony", "K.Kotapad", "Kapula Uppada",
            "Kothavalasa", "Kurmannapalem","Kusalawada", "Maddilapalem", "Madhavadhara", "Madillapalem","Marikavalasa","MVP Complex"
   ,"OHPO","Pedarushikonda","PM Palem","Purna Market","Railway Station","Ratnagiri HB Colony","Ravindra Nagar","RK beach","RTC complex",
   "Sagar Nagar","Sagarnagar","Scindia","Simhachalam","SteelPlant S5","Town Kotharoad","Venkojipalem","Vepada","Visakhapatnam Airport",
  "Vuda Park","Zilla Parishad" };*/

    static final String[] source = new String[] {"Select Destination","Achutapuram","Addaroad","Alamanda", "Anakapalle","Ananadapuram", "Arilova Colony","Autonagar",
            "Bakkannapalem","Bhanojithota","Bhimili","Chintagatla","Chintalagraharam","Chodavaram","Dabbanda","Denderu","Devarapalle","Dibbapalem","Duvvada Railway Station",
            "Eluppi\n", "Fishing Harbour", "HB Colony", "K.Kotapad", "Kapula Uppada",
            "Kothavalasa", "Gajuwaka","Ganesh Nagar", "Gangavaram", "Gantyada HB Colony", "Gurajadanagar","Indian Express","IT Park"
            ,"Janata Colony","K.Kotapad","Kailashagiri","Kapujaggarajupeta","Kapulatunglam","Kollivanipalem","Kommadi","Kothavalasa","Kurmannapalem",
            "Madugula","Marikavalasa","Midhilapuri Colony","Mindi","MN Club","Nadupur Dairy Colony","Nadupuru","Nagarapalem","Narava",
            "VNGGO'S Colony","OHPO","Pendurthi/Kothavalasa","PM Palem","R K Beach","RTC complex","Railway Station","Ravalammapalem","S.Kota","Sabbavaram","Sagar Nagar",
            "Scindia","Sevanagar","Simhachalam","Simhachalam hills","Steelplant","Sujatanagar","Sundarayya Colony","Swayambuvaram","Tagarapuvalasa","Thadi",
            "Thanam","Vadachipurupalle","Vambey Colony","Vizianagaram","VSEZ","Yarada","Yelamanchili"};

    static final String[] destination = new String[] {"Select Destination","Achutapuram","Addaroad","Alamanda", "Anakapalle","Ananadapuram", "Arilova Colony","Autonagar",
            "Bakkannapalem","Bhanojithota","Bhimili","Chintagatla","Chintalagraharam","Chodavaram","Dabbanda","Denderu","Devarapalle","Dibbapalem","Duvvada Railway Station",
            "Eluppi\n", "Fishing Harbour", "HB Colony", "K.Kotapad", "Kapula Uppada",
            "Kothavalasa", "Gajuwaka","Ganesh Nagar", "Gangavaram", "Gantyada HB Colony", "Gurajadanagar","Indian Express","IT Park"
            ,"Janata Colony","K.Kotapad","Kailashagiri","Kapujaggarajupeta","Kapulatunglam","Kollivanipalem","Kommadi","Kothavalasa","Kurmannapalem",
            "Madugula","Marikavalasa","Midhilapuri Colony","Mindi","MN Club","Nadupur Dairy Colony","Nadupuru","Nagarapalem","Narava",
            "VNGGO'S Colony","OHPO","Pendurthi/Kothavalasa","PM Palem","R K Beach","RTC complex","Railway Station","Ravalammapalem","S.Kota","Sabbavaram","Sagar Nagar",
    "Scindia","Sevanagar","Simhachalam","Simhachalam hills","Steelplant","Sujatanagar","Sundarayya Colony","Swayambuvaram","Tagarapuvalasa","Thadi",
    "Thanam","Vadachipurupalle","Vambey Colony","Vizianagaram","VSEZ","Yarada","Yelamanchili"};

     ListView listView ;
    ArrayAdapter<String> adapter;
    Button btnClosePopup;
    PopupWindow popWindow;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       // int position = 0;
        //mainlayout.setVisibility(RelativeLayout.GONE);
        // String[] postion1= getResources().getStringArray(R.array.option);
        View rootView = inflater.inflate(R.layout.search, container, false);
      //  TextView tv = (TextView) rootView.findViewById(R.id.tv);

        // Setting currently selected river name in the TextView
       // tv.setText("Home");

        // Updating the action bar title
        // getSupportActionBar().setTitle(mActivityTitle);
        //String[] bob = { "this", "is", "a", "really", "silly", "list" };
       // final EditText et = (EditText) rootView.findViewById(R.id.editText);
       final TextView tv = (TextView) rootView.findViewById(R.id.textView2);
        final TextView tv2 = (TextView) rootView.findViewById(R.id.textView3);
       // final TextView tv3 = (TextView) rootView.findViewById(R.id.textView4);
     //   final Button button =  (Button) rootView.findViewById(R.id.button2);

        spinnerOsversions = (Spinner) rootView.findViewById(R.id.osversions);
        spinnerOsversions1 = (Spinner) rootView.findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(this.getActivity(),
                R.layout.simple_spinner_item,source);
        ArrayAdapter<String> adapter_state1 = new ArrayAdapter<String>(this.getActivity(),
                R.layout.simple_spinner_item, destination);
        adapter_state
                .setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        adapter_state1
                .setDropDownViewResource(R.layout.simple_spinner_dropdown_item);

        spinnerOsversions.setAdapter(adapter_state);
        spinnerOsversions1.setAdapter(adapter_state1);
     spinnerOsversions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
         public void onItemSelected(AdapterView<?> parent, View view,
                                    int position, long id) {
             spinnerOsversions.setSelection(position);
             String selState = (String) spinnerOsversions.getSelectedItem();
             tv.setText("");
             tv2.setText("");
            // tv3.setText("");
            // Toast.makeText(getActivity(),"D"+tmpStr10,Toast.LENGTH_SHORT).show();
           //  String txt=et.getText().toString();
           //  Toast.makeText(getActivity(),"Text!"+selState,Toast.LENGTH_SHORT).show();
            // int v2 =   spinnerOsversions.getSelectedItemPosition();
          //   tmpStr10 = Integer.toString(v2);
             int j=0;

                tmpStr10=spinnerOsversions.getSelectedItem().toString();
             Toast.makeText(getActivity(),"D"+tmpStr10,Toast.LENGTH_SHORT).show();

         }




         @Override
            public void onNothingSelected(AdapterView<?> parent) {
             Toast.makeText(getActivity(),"Please select item",Toast.LENGTH_SHORT).show();
            }
        });
        spinnerOsversions1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
             //   int v2 =   spinnerOsversions1.getSelectedItemPosition();
              //  tmpStr11 = Integer.toString(v2);
                tmpStr11=spinnerOsversions1.getSelectedItem().toString();
                Toast.makeText(getActivity(),"D"+tmpStr11,Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getActivity(),"Please select item",Toast.LENGTH_SHORT).show();
            }

        });

    ;
     //   spinnerOsversions.setSelection(position);
      //  final String selState = (String) spinnerOsversions.getSelectedItem();



        //getActivity().getActionBar().setTitle(postion1[position]);
        Button btn = (Button) rootView.findViewById(R.id.button3);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //   int v2 =   spinnerOsversions.getSelectedItemPosition();
                //   String tmpStr10 = Integer.toString(v2);
                //  int v2 =   spinnerOsversions.getSelectedItemPosition();
                // tmpStr10 = Integer.toString(v2);
                // Toast.makeText(getActivity(),"s"+tmpStr10,Toast.LENGTH_SHORT).show();
                // int v3 =   spinnerOsversions.getSelectedItemPosition();
                //  tmpStr11 = Integer.toString(v3);
                // Toast.makeText(getActivity(),"D"+tmpStr11,Toast.LENGTH_SHORT).show();
                String result = null;
                InputStream is = null;
                //    TextView tv=(TextView) rootView.findViewById(R.id.textView5);

                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
//                Toast.makeText(getActivity(), v2, Toast.LENGTH_SHORT).show();
                nameValuePairs.add(new BasicNameValuePair("f1", tmpStr10));
                nameValuePairs.add(new BasicNameValuePair("f2", tmpStr11));
                StrictMode.setThreadPolicy(policy);
                HttpEntity entity = null;
                try {
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppost = new HttpPost("http://www.ers.hol.es/select1.php");
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
                        String w = no.getString("bus_number");
                        //  Toast.makeText(getActivity(), w, Toast.LENGTH_SHORT).show();
                        // long e=no.getLong("f3");
                        String w1 = no.getString("route");
                        tv.setText(w);
                        tv2.setText("Via :"+w1);
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
        });

        return rootView;
    }

    public void showPopup( View v,Character s ) {

        LayoutInflater inflater = LayoutInflater.from(getActivity());
      //  View popupView = inflater.inflate(R.layout.dialog_fragment, null);

    //  popWindow = new PopupWindow(
      //          AbsListView.LayoutParams.FILL_PARENT, AbsListView.LayoutParams.FILL_PARENT);

        // Example: If you have a TextView inside `popup_layout.xml`
       ;



        // Initialize more widgets from `popup_layout.xml`


        // If the PopupWindow should be focusable
   //     popWindow.setFocusable(true);

        // If you need the PopupWindow to dismiss when when touched outside
    //    popWindow.setBackgroundDrawable(new ColorDrawable());
      //  btnClosePopup = (Button) popupView.findViewById(R.id.btn_close_popup);
      //  btnClosePopup.setOnClickListener(cancel_button_click_listener);
   //     int location[] = new int[2];

        // Get the View's(the one that was clicked in the Fragment) location
    //   anchorView.getLocationOnScreen(location);

        // Using location, the PopupWindow will be displayed right under anchorView
     //   popWindow.showAtLocation(anchorView, Gravity.NO_GRAVITY,
      //          location[0], location[1] + anchorView.getHeight());





    }



    private View.OnClickListener cancel_button_click_listener = new View.OnClickListener() {
        public void onClick(View v) {
            popWindow.dismiss();

        }
    };
    }