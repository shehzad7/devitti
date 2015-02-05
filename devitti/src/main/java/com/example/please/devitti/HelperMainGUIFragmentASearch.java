package com.example.please.devitti;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;


/**
 * A simple {@link android.app.Fragment} subclass.
 */
public class HelperMainGUIFragmentASearch extends Fragment {

    Spinner categorySpinner;
    Spinner amountRangeSpinner;
    Spinner causeTypeSpinner;

    EditText email;
    EditText country;
    EditText city;

    ImageButton searchButton;

    String[] dataFromSignIn;
    String[] ctIds;
    String[] ctNms;

    public HelperMainGUIFragmentASearch() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        dataFromSignIn= getArguments().getStringArray("searchAndMore");
        ctIds = getArguments().getStringArray("ctIDs");
        ctNms  =getArguments().getStringArray("ctNms");


        // Inflate the layout for this fragment
       View myView =  inflater.inflate(R.layout.helper_search, container, false);

        searchButton =  (ImageButton) myView.findViewById(R.id.HSSearchButton);
        email  = (EditText) myView.findViewById(R.id.HSEmail);
        country = (EditText) myView.findViewById(R.id.HSCountry);
        city  = (EditText) myView.findViewById(R.id.HSCity);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getRelevantQuery();


//            IT COULD BE USED TO GO TO THE NEXT CAUSES WINDOW
//                Intent intn = new Intent(getActivity(), HelperSearchResult.class);
//                startActivity(intn);
            }
        });



//        setListenersToButtons();

        return myView;


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        email.setText( "");
        country.setText("");
        city.setText("");

        String [] categroyData  = ctNms;
        categorySpinner = (Spinner) getActivity().findViewById(R.id.catagory_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,categroyData);
        categorySpinner.setAdapter(adapter);


        String [] amountRangeData =  {"Rs. 500-1000","1000-2000","2000-5000","5000-10000","10000+"};
        amountRangeSpinner = (Spinner)getActivity(). findViewById(R.id.amount_range_spinner);
        ArrayAdapter <String> adapter2 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,amountRangeData);
        amountRangeSpinner.setAdapter(adapter2);

        String [] CaueseTypeData =  {"loan","donation"};
        causeTypeSpinner = (Spinner)getActivity(). findViewById(R.id.cause_type_spinner);
        ArrayAdapter <String> adapter3 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,CaueseTypeData);
        causeTypeSpinner.setAdapter(adapter3);


    }
    private void setListenersToButtons()
    {
//        searchButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
////                Intent openCauseFullDetailViewPage = new Intent(getActivity().getClass(), HelperMainGui.class);
////                getActivity()startActivity(openCauseFullDetailViewPage);
//
//
//            }
//        });


    }



    boolean isEmpty(EditText text) {
        return text.getText().toString().trim().length()==0;
    }

    boolean isNotEmpty(EditText text) {
        return text.getText().toString().trim().length()!=0;
    }

    public String getRelevantQuery()
    {

        String query "Select * from Causes"
        if (isNotEmpty(email))
        {

        }
        Toast.makeText(getActivity().getApplicationContext(),"Email: "  + email.getText()   + " Country: "  + country.getText()
                        + " City: " + city.getText() + " Cause Type: " + causeTypeSpinner.getSelectedItem().toString() + " Catagory: "  +  categorySpinner.getSelectedItem().toString()
                        + " Amount Range: "  + amountRangeSpinner.getSelectedItem().toString(),
                Toast.LENGTH_SHORT).show();




        return null;

    }
}
