package com.example.please.devitti;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by please on 12/24/2014.
 */
public class CauseFullDetailView extends Activity {

    String [] subLoans = {"Returned Confirmed my <usman232>",
            "Returned Confirmed my <akram424>",
            "Needs to be returned to user <akram424>\nAmmount: 4000",
            "Returned Confirmed my <uzair545>",
            "Returned Confirmed my <Fawad654>",
            "Needs to be returned to user <Salman543>\nAmmount: 4500",
            "Returned Confirmed my <Anas231>",
            "Returned Confirmed my <Younis3434>",
            "Returned Confirmed my <Younis3434>",
            "Returned Confirmed my <Younis3434>",
            "Returned Confirmed my <Younis3434>",
            "Returned Confirmed my <Younis3434>",
            "Returned Confirmed my <Younis3434>",
            "Returned Confirmed my <Younis3434>",
            "Returned Confirmed my <Younis3434>",
            "Returned Confirmed my <Younis3434>",
            "Returned Confirmed my <Younis3434>",
            "Returned Confirmed my <Younis3434>",};

    ScrollView CFDVScrollViewMain;

    ListView CFDVMainList;

    Cause theCause;
    String [] dataFromSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cause_full_detail_view);

        theCause = (Cause) getIntent().getSerializableExtra("cause");
        dataFromSignIn = (String[]) getIntent().getSerializableExtra("dataFromSignIn");
        String percen = (String) getIntent().getSerializableExtra("percentageCompleted");
        System.out.println("nnnnnnnnnnnnnnnnn"+percen.toString());
        TextView description =  (TextView) findViewById(R.id.CFDVDescription);
        description.setText(theCause.description.toString());
        TextView percentage =  (TextView) findViewById(R.id.CFDVPercentage);
        percentage.setText(percen);

        TextView noOfEn = (TextView) findViewById(R.id.CFDVnoOfEndorsements);
        noOfEn.setText(theCause.noOfEndorsements);
        System.out.println();

//        intnt.putExtra("percentageCompleted",percentage.toString());


//        intnt.putExtra("cause", ii);
//        intnt.putExtra("dataFromSignIn",dataFromSignIn);


//        CFDVMainList = (ListView) findViewById(R.id.CFDVMainList);
//        ArrayAdapter<String> at =  new ArrayAdapter(this,android.R.layout.simple_list_item_1,subLoans);
//        CFDVMainList.setAdapter(at);
//        CFDVScrollViewMain = (ScrollView) findViewById(R.id.CFDVScrollViewMain);
//        CFDVScrollViewMain.pageScroll(ScrollView.FOCUS_UP);
    }
}
