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
public class NeedyClickOnOwnCause extends Activity {

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

        setContentView(R.layout.needy_click_on_own_cause);
//        NCOOCList

        NCOOCList = (ListView) findViewById(R.id.NCOOCList);
        causegot = (Cause) getIntent().getSerializableExtra("ldArray");
//        causeIdClicked = (String) getIntent().getSerializableExtra("causeId");

        if (!(causegot.lendingDetails == null || causegot.lendingDetails.length < 1)) {
            ldExtracted = new LendingDetailForCause[causegot.lendingDetails.length];

            int pointer = 0;
            for (int u = 0; u < causegot.lendingDetails.length; u++) {
                System.out.println("status: " + causegot.lendingDetails[u] + "CauseID: " + causegot.lendingDetails[u].causeId +
                        "helperID: " + causegot.lendingDetails[u].helperId);
                if (!(causegot.lendingDetails[u].status.contains("helper confirmed"))) {
                    ldExtracted[pointer] = causegot.lendingDetails[u];
                    pointer++;
                }
            }

            LendingDetailForCause[] templd = new LendingDetailForCause[pointer];
            for (int y = 0; y < pointer; y++) {
                System.out.println("MMMMMMMMM");
                templd[y] = ldExtracted[y];
            }

            ldExtracted = templd;

            setListData();
            setListListener();

            System.out.println("causegotlending details length:  " + causegot.lendingDetails.length);
            System.out.println("extracted lending details length: " + ldExtracted.length);
//        System.out.println("causegotlending details length:  "  + templd.length);
            System.out.println("pointer: " + pointer);


//        Log.e("causegotlending details length:  " + String.valueOf(causegot.lendingDetails.length ) ,"");
//        Log.e("extracted lending details length:  " + String.valueOf(ldExtracted.length ) ,"");
//        Log.e("causegotlending details length:  " + String.valueOf(templd.length ) ,"");


        }
        else
        {
            System.out.println("hoooooooooooooooowaaaaaaaaaaaaaaaaaa");
        }
    }

    public void setListData() {


        if (causegot.lendingDetails != null) {
//            subLoans  =new String[causegot.lendingDetails.length];
            subLoans = new String[ldExtracted.length];
//
            for (int i = 0; i < subLoans.length; i++) {
                if (ldExtracted[i].status.contains("request sent to needy")) {
                    subLoans[i] = "Request for confirmation of Rs: " + ldExtracted[i].amountLended + " sent to you by helperID: " + ldExtracted[i].helperId + " ";
                } else if (ldExtracted[i].status.contains("needy confirmed")) {
                    subLoans[i] = "You have confirmed Rs: " + ldExtracted[i].amountLended + " sent to you by helperID: " + ldExtracted[i].helperId;
                } else if (ldExtracted[i].status.contains("request sent to helper")) {
                    subLoans[i] = "You have sent requested the confirmation of Rs: " + ldExtracted[i].amountLended + " to helperID: " + ldExtracted[i].helperId;
                } else {

                    System.out.println("/////////////////////////////////// not in any catagory man!!!!");
                }

//                subLoans[i]  = causegot.lendingDetails [i].status;
            }


        } else {

            subLoans = new String[1];
            subLoans[0] = "no cause details found!!";
        }

        ArrayAdapter<String> at = new ArrayAdapter(this, android.R.layout.simple_list_item_1, subLoans);
        NCOOCList.setAdapter(at);

    }

    public void setListListener() {

    }


}
