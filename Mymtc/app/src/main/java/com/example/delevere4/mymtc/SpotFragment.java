package com.example.delevere4.mymtc;

import android.app.Fragment;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SpotFragment extends Fragment implements LocationListener {
    public SpotFragment(){}
    private TextView latituteField;
    private TextView longitudeField;
    private LocationManager locationManager;
    private String provider;
    Button btn;
    TextView tvAddress;
    LocationAddress locationAddress;
    AppLocationService appLocationService;
    Spinner spinnerOsversions;
    //  String FRUITS1 ;
    static final String[] FRUITS = new String[] {"Select Bus Number", "6", "6A", "540",
            "38", "38J", "28", "25it", "400A",
            "25R", "222","222", "999", "211", "500" };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.spot, container, false);

        latituteField = (TextView) rootView.findViewById(R.id.TextView02);
        longitudeField = (TextView) rootView.findViewById(R.id.TextView04);
        tvAddress = (TextView) rootView.findViewById(R.id.tvAddress);
        spinnerOsversions = (Spinner) rootView.findViewById(R.id.osversions);
        ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(this.getActivity(),
                R.layout.simple_spinner_item, FRUITS);
        adapter_state
                .setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spinnerOsversions.setAdapter(adapter_state);
        // Get the location manager
       locationManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
        Toast.makeText(getActivity(), "value : "+provider,Toast.LENGTH_SHORT).show();
        // Define the criteria how to select the locatioin provider -> use
        // default
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, true);
     //   appLocationService = new AppLocationService(SpotFragment.this);


        //Location location = appLocationService
              //  .getLocation(LocationManager.GPS_PROVIDER);
        // Initialize the location fields
        btn= (Button) rootView.findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Location location = locationManager.getLastKnownLocation(provider);
                if (location != null) {
                    System.out.println("Provider " + provider + " has been selected.");
                    onLocationChanged(location);
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    locationAddress = new LocationAddress();
                    locationAddress.getAddressFromLocation(latitude, longitude,
                            getActivity(), new GeocoderHandler());

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
        locationManager.requestLocationUpdates(provider, 400, 1,this );
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
        }
    }

    public void getSystemService(String locationService) {
    }
}
