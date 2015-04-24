package com.example.please.devitti;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ResourceBundle;

/**
 * Created by please on 12/24/2014.
 */
public class HelperClickOnCause extends Activity {

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

    String dataFromSignIn[];
    LendingDetailForCause[] ldExtracted;

    private ResourceBundle arguments;

    LendingDetailForCause LDFCTemp= null;
    Receipt RECTemp  = null;
    User lenderDetail  = null;

//    String causeIdClicked = "-1";

    _DATABASEManager dM = new _DATABASEManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.needy_click_on_own_cause);
//        NCOOCList

        NCOOCList = (ListView) findViewById(R.id.NCOOCList);
        causegot = (Cause) getIntent().getSerializableExtra("ldArray");

        dataFromSignIn = (String[])getIntent().getSerializableExtra("dataFromSignIn");

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

            System.out.println("THIS REQUEST FOR LENDING DETAILS IS FROM " + dataFromSignIn[5] );
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
                }
                else if (ldExtracted[i].status.contains("helper confirmed")) {
                    subLoans[i] = "Helper has confirmed the amount: " + ldExtracted[i].amountLended + " you sent back to the helper to helperID: " + ldExtracted[i].helperId;
                }
                else {

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

        NCOOCList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (causegot.lendingDetails[position].status.contains("request sent to needy"))
                {
                    //the needy will just get a set of information and confirm it
                    // which will also need the transaction id which contains the status "request sent to needy"
                    // the status should then be needy confirmed

                    LDFCTemp = causegot.lendingDetails[position];
                    new getUserDetailInBagrd().execute();

                }
                else if(causegot.lendingDetails[position].status.contains("needy confirmed"))
                {
                    // here, the needy will have the option to send back the money to the helper,
                    // so he will fill in all  the necessary information.
                    // including the data from the helper already filled in  and then the needy will only give the information about the tans.. id
                    // the amount should already be fillled

                    LDFCTemp = causegot.lendingDetails[position];
                    new getJustUserDetailInBagrd().execute();

                    //this info below will be done in the Asyntask end of the getJustUser....
                    // User lender = lenderDetail;
                    //we have the user and the previous lending detail
                    // take all the information from it and then then make a new
                    // 1. lending detail and 2. a receipt entry associated with that lending detail

                }
                else if(causegot.lendingDetails[position].status.contains("request sent to helper"))
                {

                }
                else if(causegot.lendingDetails[position].status.contains("helper confirmed"))
                {

                }

                Toast.makeText(getApplicationContext(),

                        String.valueOf(causegot.lendingDetails[position].amountLended)

                        , Toast.LENGTH_SHORT).show();

            }
        });
    }

    public class getRecepitDetailInBagrd extends AsyncTask<String, Integer, Receipt[]>
    {


        protected Receipt[] doInBackground(String... params) {

//            Cause caus = new Cause(causeId,needyId,moneyAskedFor,descrip,status,type,dateOfRequest,dateOfCompletion,dateOfMaturity,lati,longi,null,catagory);
//            return dM.getLendingDetailsForACause(new Cause("2","","","","","","","","","","",null,""));
            return  dM.getReceiptsForLDFCId(String.valueOf(LDFCTemp.LDFCId));



        }

        @Override
        protected void onPostExecute(Receipt[] rec) {
            super.onPostExecute(rec);

            System.out.println("                                  ");

            for(int i  = 0  ; i < rec.length; i ++)
            {
                //System.out.println("origgggggggggg: >"+ LDFCIdTemp+"<");
                //System.out.println("newwwwwwwwwwww: >"+ rec[i].LDFCId+"<");

                String LDFCId  = String.valueOf(LDFCTemp.LDFCId);
                if(LDFCId.contains( rec[i].LDFCId ) && rec[i].status.contains("request sent to the needy")) {
                    RECTemp   = rec[i];
                    LendingDetailForCause a = LDFCTemp;
                    User lender = lenderDetail;

                    //This is where the next simple GUI will open in which the needy will confirm and
                    //then "request sent to the needy" will change to "needy confirmed" in the LDFCTemp.
                    System.out.println("++++++++++++++++GUI FOR THE NEEDY TO CONFIRM +++++++");
                    System.out.println(lenderDetail.name+" sent you Rs: " +LDFCTemp.amountLended);
                    System.out.println("Account No: "+lenderDetail.accountNo);
                    System.out.println("Bank Name: "+lenderDetail.bankName);
                    System.out.println("Transaction Id: "+RECTemp.transationId);


                    System.out.println("++++++++++++++++GUI FOR THE NEEDY TO CONFIRM +++++++");



                    //here, the actual confirmation actually works

                    System.out.println(".............RpId: " + rec[i].RpId + " status: " + rec[i].status);
                }
            }
            System.out.println("                                  ");

        }
    }

    public class getUserDetailInBagrd extends AsyncTask<String, Integer, User>
    {


        protected User doInBackground(String... params) {

//            Cause caus = new Cause(causeId,needyId,moneyAskedFor,descrip,status,type,dateOfRequest,dateOfCompletion,dateOfMaturity,lati,longi,null,catagory);
//            return dM.getLendingDetailsForACause(new Cause("2","","","","","","","","","","",null,""));
            return  dM.getUserDetailByUserId(String.valueOf(LDFCTemp.helperId));



        }

        @Override
        protected void onPostExecute(User user) {
            super.onPostExecute(user);
            lenderDetail = user;
            new getRecepitDetailInBagrd().execute();


        }
    }

    public class getJustUserDetailInBagrd extends AsyncTask<String, Integer, User>
    {


        protected User doInBackground(String... params) {

//            Cause caus = new Cause(causeId,needyId,moneyAskedFor,descrip,status,type,dateOfRequest,dateOfCompletion,dateOfMaturity,lati,longi,null,catagory);
//            return dM.getLendingDetailsForACause(new Cause("2","","","","","","","","","","",null,""));
            return  dM.getUserDetailByUserId(String.valueOf(LDFCTemp.helperId));



        }

        @Override
        protected void onPostExecute(User user) {
            super.onPostExecute(user);
            lenderDetail = user;


        }
    }


}
