package com.example.please.devitti;


import android.annotation.TargetApi;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executor;


import android.app.Activity;

import android.content.Context;

import android.location.Location;

import android.location.LocationListener;

import android.location.LocationManager;

import android.os.Bundle;

import android.widget.Toast;

/**
 * A simple {@link android.app.Fragment} subclass.
 */
public class NeedyMainGUIFragmentB extends Fragment {

    Cause causeToBeAdded = null;

    CauseCatagory [] causeCatagoryDetails;

    _DATABASEManager dM;
    String [] dataFromSignIn;

    ImageButton makeACause;


    public NeedyMainGUIFragmentB() {
        // Required empty public constructor
    }
    Spinner categorySpinner;

    Spinner causeTypeSpinner;

    EditText description;
    EditText amount;

    double latitude= 123;
    double longitude=123;

    TextView latTxt;
    TextView lonTxt;

    String  [] ctIds= null ;
    String  [] ctNms = null;

    String [] categroyData = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        dataFromSignIn= getArguments().getStringArray("existingCauses");
        ctIds = getArguments().getStringArray("ctIDs");
        ctNms  =getArguments().getStringArray("ctNms");





                // Inflate the layout for this fragment
    View myView = inflater.inflate(R.layout.needy_make_a_cause, container, false);




        return myView;


    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        latTxt = (TextView) getActivity().findViewById(R.id.NMACLati);
        lonTxt = (TextView) getActivity().findViewById(R.id.NMACLongi);


        dM =  new _DATABASEManager();


        makeACause = (ImageButton) getActivity().findViewById(R.id.NMACMakeACauseButton);

        description = (EditText) getActivity().findViewById(R.id.NMACDescription);
        amount  = (EditText) getActivity().findViewById(R.id.NMACAmount);


//        new getCatagoryDataInBagrd().execute();

//        String [] categroyData =  new String [causeCatagoryDetails.length];

//             categroyData = new String[] {"Education","Health","Business Expansion"};
//        for(int i = 0 ;i <causeCatagoryDetails.length ; i++)
//        {
//            categroyData[i] = causeCatagoryDetails[i].catagoryName;
//        }


        categorySpinner = (Spinner) getActivity().findViewById(R.id.catagory_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,ctNms);
        categorySpinner.setAdapter(adapter);



        String [] CaueseTypeData =  {"loan","donation"};
        causeTypeSpinner = (Spinner) getActivity().findViewById(R.id.cause_type_spinner);
        ArrayAdapter <String> adapter3 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,CaueseTypeData);
        causeTypeSpinner.setAdapter(adapter3);

        //location manager for GPS
//        LocationManager mlocManager = getS
        LocationManager lm  = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        LocationListener myLocationListener = new LocationClass();

        lm.requestLocationUpdates(lm.GPS_PROVIDER,2000,10,myLocationListener);

        addListenerToButton();


    }



    public void  addListenerToButton()
    {
        makeACause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                new addCauseDoInBackground().execute(1);
                new getCatagoryDataInBagrd().execute("");
//                new CheckLoginAndGetUserDetailsArraydoInBagrd(SignIn.this).execute(2);

//                Toast.makeText(getActivity().getApplicationContext(), "iam here",
//                        Toast.LENGTH_LONG).show();

            }
        });

    }




    public class addCauseDoInBackground extends AsyncTask<Integer,Integer,String >
    {
        @Override
        protected String doInBackground(Integer... params) {
//            Toast.makeText(getActivity().getApplicationContext(), "iam here",
//                    Toast.LENGTH_LONG).show();
            new getCatagoryDataInBagrd().execute("");

//            return dM.checkLoginAndGetUserDetails(userNameBox.getText().toString(), passwordBox.getText().toString());
//            return dM.checkLoginAndGetUserDetails("kamran","kamran2015");

        return null;

        }

        @Override
        protected void onPostExecute(String  s) {
            super.onPostExecute(s);

        }
    }



    public class LocationClass implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
         latitude =  location.getLatitude();
         longitude =  location.getLongitude();

            latTxt.setText(String.valueOf(latitude));
            lonTxt.setText(String.valueOf(longitude));


//            Toast.makeText(,"Latitude: "+ String.valueOf(latitude) + "\nLongitude: "+ String.valueOf(longitude),Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {
//            Toast.makeText()
        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }



    public class getCatagoryDataInBagrd extends AsyncTask<String, Integer, CauseCatagory[]>
    {

        @Override
        protected CauseCatagory[] doInBackground(String... params) {

            return dM.catagoryDetails();
        }

        @Override
        protected void onPostExecute(CauseCatagory[] s) {
            super.onPostExecute(s);

            causeCatagoryDetails =s;


            //the rest of the cause addition work after getting the catagory details


            String causeId;
            String needyId;
            String moneyAskedFor;
            String descrip;
            String status;
            String catagory;
            String dateOfRequest;
            String dateOfCompletion;
            String dateOfMaturity;
            String lati;
            String longi;
            String type;

            causeId = "null";
            needyId = dataFromSignIn[1];
            moneyAskedFor = amount.getText().toString();
            descrip = description.getText().toString();
            status = "not completed";

            catagory = categorySpinner.getSelectedItem().toString();


            System.out.println("[][][][]][][][][][][][][]");
            for(int i = 0 ;i <causeCatagoryDetails.length; i ++)
            {
                System.out.println(causeCatagoryDetails[i].catagoryName + " "+causeCatagoryDetails[i].catagoryId);


                if(causeCatagoryDetails[i].catagoryName.contains(catagory))
                {
                    catagory = causeCatagoryDetails[i].catagoryId;
                    System.out.println("IAM HERE");
                }
            }
            System.out.println("[][][][]][][][][][][][][]");



            dateOfRequest = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            dateOfCompletion = "2000-01-01";
            dateOfMaturity = "2000-01-01";

            //from the location manager
            lati = String.valueOf(latitude);
            longi = String.valueOf(longitude);
            type = causeTypeSpinner.getSelectedItem().toString();


            Cause caus = new Cause(causeId,needyId,moneyAskedFor,descrip,status,type,dateOfRequest,dateOfCompletion,dateOfMaturity,lati,longi,null,catagory,"0","0");

            causeToBeAdded = caus;


            Toast.makeText(getActivity().getApplicationContext(), "iam here tttttttt",
                    Toast.LENGTH_SHORT).show();

                        Log.e("log_tag", ">>>>>>>>" +"\n !cause id: "+ causeToBeAdded.causeId +"\n !needyID: " + causeToBeAdded.needyId+"\n !moneyaskedFor:  "
                                +causeToBeAdded.moneyAskedFor+"\n !description:  "+causeToBeAdded.description +" "+"\n !status:  " + causeToBeAdded.status
                                +"\n !catagory: " +causeToBeAdded.catagory +"\n !dateofRequest " + causeToBeAdded.dateOfRequest+
                                "\n !dateofComp: "+causeToBeAdded.dateOfCompletion +"\n !dateofmaturity:  "+
                                causeToBeAdded.dateOfMaturity +"\n !latitude: " +causeToBeAdded.latitude +"\n !longitude:  "+causeToBeAdded.longitude
                                +"\n !type: "+causeToBeAdded.type +" ");



            //this oneeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee
            new saveCauseInBagrd().execute();



//            Cause caus =  new Cause(causeId,needyId,moneyAskedFor,descrip,status,catagory,dateOfRequest,dateOfCompletion,dateOfMaturity,lati,longi,null,catagory);

//            Log.e("log_tag", "!!!!!!!!!!!!!!!!!! " +" !cause id: "+ causeId +" !needyID: " + needyId+" !moneyaskedFor:  " +moneyAskedFor+" !description:  "+descrip +" "
//                            +" !status:  " + status+" !catagory: " +catagory +" !dateofRequest " + dateOfRequest+" !dateofComp: "+dateOfCompletion +" !dateofmaturity:  "+
//                            dateOfMaturity +" !latitude: " +latitude +" !longitude:  "+longitude +" !type: "+type +" "
//            );
//            System.out.println("  hmmmm  "+ catagory);




            //the catagory data must be retrieved prior to displaying the list as here



        }
    }


    public class saveCauseInBagrd extends AsyncTask<Cause, Integer, String>
    {

        @Override
        protected String doInBackground(Cause... params) {

            return dM.saveCause(causeToBeAdded);

        }

        @Override
        protected void onPostExecute(String st) {
            super.onPostExecute(st);

            Toast.makeText(getActivity().getApplicationContext(), "",
                    Toast.LENGTH_SHORT).show();

//            new getLendingDetailForACauseInBagrd().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "")
//            startMyTask(new getLendingDetailForACauseInBagrd(),null);


    }
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB) // API 11
    void startMyTask(AsyncTask asyncTask,Object params) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, params);
        else
            asyncTask.execute(params);
    }








}
