package com.example.please.devitti;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by please on 12/21/2014.
 */

public class HelperSearch extends Activity{
    Spinner categorySpinner;
    Spinner amountRangeSpinner;
    Spinner causeTypeSpinner;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helper_search);

        String [] categroyData =  {"Education","Health","Business Expansion"};
        categorySpinner = (Spinner) findViewById(R.id.catagory_spinner);
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,categroyData);
        categorySpinner.setAdapter(adapter);


        String [] amountRangeData =  {"Rs. 500-1000","1000-2000","2000-5000","5000-10000","10000+"};
        amountRangeSpinner = (Spinner) findViewById(R.id.amount_range_spinner);
        ArrayAdapter <String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,amountRangeData);
        amountRangeSpinner.setAdapter(adapter2);

        String [] CaueseTypeData =  {"loan","donation"};
        causeTypeSpinner = (Spinner) findViewById(R.id.cause_type_spinner);
        ArrayAdapter <String> adapter3 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,CaueseTypeData);
        causeTypeSpinner.setAdapter(adapter3);


//        cause_type_spinner



    }


}





