package com.example.delevere4.mymtc;

import android.app.Fragment;
import android.app.ListFragment;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SearchBusFragment extends Fragment {
    public SearchBusFragment() {
    }
    int j;
    Spinner spinnerOsversions;
   //  String FRUITS1 ;
    static final String[] FRUITS = new String[] {"Select Bus Number", "6", "6A", "540",
            "38", "38J", "28", "25it", "400A",
            "25R", "222","222", "999", "211", "500" };
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
        final TextView tv3 = (TextView) rootView.findViewById(R.id.textView4);
     //   final Button button =  (Button) rootView.findViewById(R.id.button2);

        spinnerOsversions = (Spinner) rootView.findViewById(R.id.osversions);
        ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_item, FRUITS);
        adapter_state
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOsversions.setAdapter(adapter_state);
     spinnerOsversions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
         public void onItemSelected(AdapterView<?> parent, View view,
                                    int position, long id) {
             spinnerOsversions.setSelection(position);
             String selState = (String) spinnerOsversions.getSelectedItem();
             tv.setText("");
             tv2.setText("");
             tv3.setText("");
           //  String txt=et.getText().toString();
           //  Toast.makeText(getActivity(),"Text!"+selState,Toast.LENGTH_SHORT).show();
             int j=0;
             for (String s : FRUITS) {

                 // int i = s.indexOf("38");
                 //Toast.makeText(getActivity(),"index"+i,Toast.LENGTH_SHORT).show();
                 String result = s.replaceAll("[A-z]", "");
                 if (Arrays.asList(result).contains(selState)) {
                     if ( j==0) {


                         Toast.makeText(getActivity(),"Text!"+s,Toast.LENGTH_SHORT).show();


                         tv.setText(s);

                     }
                     if ( j== 1) {


                         Toast.makeText(getActivity(),"Text!"+s,Toast.LENGTH_SHORT).show();


                         tv2.setText(s);
                         j++;
                     }
                     j++;
                 }

                 // Assign adapter to ListView


             }

         }

         @Override
            public void onNothingSelected(AdapterView<?> parent) {
             Toast.makeText(getActivity(),"Please select item",Toast.LENGTH_SHORT).show();
            }
        });
     //   spinnerOsversions.setSelection(position);
      //  final String selState = (String) spinnerOsversions.getSelectedItem();

     /*   button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tv.setText("");
                tv2.setText("");
                tv3.setText("");
                   String txt=et.getText().toString();
                    Toast.makeText(getActivity(),"Text!"+txt,Toast.LENGTH_SHORT).show();
                    int k=0;
                    for (String s : FRUITS) {

                       // int i = s.indexOf("38");
                        //Toast.makeText(getActivity(),"index"+i,Toast.LENGTH_SHORT).show();
                        String result = s.replaceAll("[A-z]", "");
                        if (Arrays.asList(result).contains(txt)) {
                        if ( k==0) {


                            Toast.makeText(getActivity(),"Text!"+s,Toast.LENGTH_SHORT).show();


                            tv.setText(s);

                        }
                        if ( k== 1) {


                            Toast.makeText(getActivity(),"Text!"+s,Toast.LENGTH_SHORT).show();


                            tv2.setText(s);
                            k++;
                        }
                           k++;
                    }

                    // Assign adapter to ListView


                    }
                if (Arrays.asList(FRUITS).contains(et.getText().toString())) {

                } else {
                    tv.setText("No  Bus found");
                }

            }
        });

*/

        //getActivity().getActionBar().setTitle(postion1[position]);

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