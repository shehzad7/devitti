package com.example.please.devitti;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ResourceBundle;

/**
 * Created by please on 12/24/2014.
 */
public class HelperClickOnLendingDetailsForCause extends Activity {

    String[] subLoans = {"Returned Confirmed my <usman232>",
            "Returned Confirmed my <akram424>",
            "Needs to be returned to user <akram424>\nAmmount: 4000",
            "Returned Confirmed my <uzair545>",
            "Returned Confirmed my <Fawad654>",
            "Needs to be returned to user <Salman543>\nAmmount: 4500",
            "Returned Confirmed my <Anas231>",
            "Returned Confirmed my <Younis3434>",
            "Returned Confirmed my <akram424>",
            "Needs to be returned to user <akram424>\nAmmount: 4000",
            "Returned Confirmed my <uzair545>",
            "Returned Confirmed my <Fawad654>",
            "Needs to be returned to user <Salman543>\nAmmount: 4500",
            "Returned Confirmed my <Anas231>",
            "Returned Confirmed my <Younis3434>"};

    ListView NCOOCList;

    Cause causegot = null;

    LendingDetailForCause[] ldExtracted;

    private ResourceBundle arguments;

//    String causeIdClicked = "-1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helper_click_lending_details_botton);
//        NCOOCList
        NCOOCList = (ListView) findViewById(R.id.HCOLDFCList);
//        causeIdClicked = (String) getIntent().getSerializableExtra("causeId");
            setListData();
    }

    public void setListData() {




            subLoans = new String[1];
            subLoans[0] = "dummy cause detail";

        ArrayAdapter<String> at = new ArrayAdapter(this, android.R.layout.simple_list_item_1, subLoans);
        NCOOCList.setAdapter(at);

    }

    public void setListListener() {

    }


}
