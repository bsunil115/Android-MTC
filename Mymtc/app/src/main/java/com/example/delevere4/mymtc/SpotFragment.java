package com.example.delevere4.mymtc;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import java.util.ArrayList;

public class SpotFragment extends Fragment implements LocationListener {
    public SpotFragment(){}
    private TextView latituteField;
    private TextView longitudeField;
    private LocationManager locationManager;
    private String provider;
    Location gpsLocation;
    Button btn;
    TextView tvAddress;
    LocationAddress locationAddress;
    AppLocationService appLocationService;
    Spinner spinnerOsversions;
    InputStream is = null;
    String result = null;

    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    //  String FRUITS1 ;
    static final String[] busno = new String[] { "Select Bus Number","6", "6A/H","10A","10K","12D","12K","14","16","17K","1T","201V","20A","210","211","222","234Y",
            "25A","25B","25D","25D/V","25D/M","25E","25G","25G/R","25IT","25J","25K","25M","25P","25U","28A/28K","28A/D","28A/P","28C","28J",
            "28P","28R","28Z/H","300C","300M","30A","31","328","333","333k","341","35","36","38","38A","38B","38C","38D","38H","38J",
            "38K","38M","38N","38R","38T","38U","38Y","38Y/F","38Z","400","400H","400H","400K","400N","400S","400T","400Y","404","48","48A",
            "500A","500A/D","500P","500Y","505","51","52D","60","600","600C","60C","60R","63","64","65F","666","68/68K","69","6B","6D","700",
            "747","77","777","77T","844","888","900","900K","900T","99","999","99A/C","99K","9E"
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.spot, container, false);

        latituteField = (TextView) rootView.findViewById(R.id.TextView02);
        longitudeField = (TextView) rootView.findViewById(R.id.TextView04);
        tvAddress = (TextView) rootView.findViewById(R.id.tvAddress);
        spinnerOsversions = (Spinner) rootView.findViewById(R.id.osversions);
        ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(this.getActivity(),
                R.layout.simple_spinner_item, busno);
        adapter_state
                .setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spinnerOsversions.setAdapter(adapter_state);
        // Get the location manager
       locationManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
        Toast.makeText(getActivity(), "value : "+provider,Toast.LENGTH_SHORT).show();
        // Define the criteria how to select the locatioin provider -> use
        // default
        Criteria criteria = new Criteria();
    // provider = locationManager.getBestProvider(criteria, true);
      //  provider=LocationManager.GPS_PROVIDER;
        provider = LocationManager.NETWORK_PROVIDER;
     //   appLocationService = new AppLocationService(SpotFragment.this);
        Toast.makeText(getActivity(), provider,Toast.LENGTH_SHORT).show();

        //Location location = appLocationService
              //  .getLocation(LocationManager.GPS_PROVIDER);
        // Initialize the location fields
        btn= (Button) rootView.findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

              //  gpsLocation = appLocationService
               //         .getLocation(LocationManager.GPS_PROVIDER);
                Location location = locationManager.getLastKnownLocation(provider);
                if (location != null) {
                    System.out.println("Provider " + provider + " has been selected.");
                    onLocationChanged(gpsLocation);
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    locationAddress.getAddressFromLocation(latitude,longitude,
                            getActivity(), new GeocoderHandler());
                    latituteField.setText(String.valueOf(latitude));
                    longitudeField.setText(String.valueOf(longitude));
                    locationAddress = new LocationAddress();
                //    locationAddress.getAddressFromLocation(latitude, longitude,
                   //         getActivity(), new GeocoderHandler());

                } else {
                    latituteField.setText("Can't access location");
                   // longitudeField.setText("Location not available");
                }
            }

        });
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        locationManager.requestLocationUpdates(provider, 400, 1, this);
    }

    /* Remove the locationlistener updates when Activity is paused */
    @Override
   public void onPause() {
        super.onPause();
        locationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        double lat =location.getLatitude();
        double lng = location.getLongitude();
        locationAddress.getAddressFromLocation(lat, lng,
                getActivity(), new GeocoderHandler());
       latituteField.setText(String.valueOf(lat));
        longitudeField.setText(String.valueOf(lng));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        /* TODO Auto-generated method stub */

    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(getActivity(), "Gps is on "+provider,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(getActivity(), "Gps is off "+provider,Toast.LENGTH_SHORT).show();
    }
    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");



                    break;
                default:
                    locationAddress = null;
            }

            tvAddress.setText(locationAddress);
            String v1 =  tvAddress.getText().toString();
            String v2 =  (String) spinnerOsversions.getSelectedItem();
            int v3 =   spinnerOsversions.getSelectedItemPosition();
            String tmpStr10 = Integer.toString(v3);
            Toast.makeText(getActivity(), v2, Toast.LENGTH_SHORT).show();

            //insert into database
            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

            nameValuePairs.add(new BasicNameValuePair("f1",v2));
            nameValuePairs.add(new BasicNameValuePair("f2",v1));
            nameValuePairs.add(new BasicNameValuePair("f3",tmpStr10));
            //  nameValuePairs.add(new BasicNameValuePair("f3",v3));


            StrictMode.setThreadPolicy(policy);


            //http post
            try {
                DefaultHttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://www.ers.hol.es/insert.php");
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity entity = response.getEntity();
                is = entity.getContent();

                Log.e("log_tag", "connection success ");
                Toast.makeText(getActivity(), "pass", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Log.e("log_tag", "Error in http connection " + e.toString());
                Toast.makeText(getActivity(), "Connection fail", Toast.LENGTH_SHORT).show();

            }
            //convert response to string
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                  //  Toast.makeText(getActivity(), line, Toast.LENGTH_SHORT).show();
                    sb.append(line + "\n");
                 //   Intent i = new Intent(getActivity(), SpotFragment.class);
                    // startActivity(i);
                }
                is.close();

                result = sb.toString();
                Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();
            }
            catch (Exception e) {
                Log.e("log_tag", "Error converting result " + e.toString());
            }


            try {

                JSONObject json_data = new JSONObject(result);

                CharSequence w = (CharSequence) json_data.get("re");
                System.out.print(w);

               // Toast.makeText(getActivity(), w, Toast.LENGTH_SHORT).show();


            } catch (JSONException e) {
                Log.e("log_tag", "Error parsing data " + e.toString());
                Toast.makeText(getActivity(), "JsonArray fail", Toast.LENGTH_SHORT).show();
            }

        }
    }


}
