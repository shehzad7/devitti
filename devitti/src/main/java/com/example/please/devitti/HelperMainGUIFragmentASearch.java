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
import android.widget.ImageButton;
import android.widget.Spinner;


/**
 * A simple {@link android.app.Fragment} subclass.
 */
public class HelperMainGUIFragmentASearch extends Fragment {

    Spinner categorySpinner;
    Spinner amountRangeSpinner;
    Spinner causeTypeSpinner;

    ImageButton searchButton;

    public HelperMainGUIFragmentASearch() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View myView =  inflater.inflate(R.layout.helper_search, container, false);

        ImageButton searchButton =  (ImageButton) myView.findViewById(R.id.HSSearchButton);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intn = new Intent(getActivity(), HelperSearchResult.class);
                startActivity(intn);
            }
        });



//        setListenersToButtons();

        return myView;


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String [] categroyData =  {"Education","Health","Business Expansion"};
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
}
