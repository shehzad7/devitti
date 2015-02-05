package com.example.please.devitti;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by please on 12/21/2014.
 */
public class SignIn extends Activity{

    ProgressDialog prgsDlg;

    EditText userNameBox;
    EditText passwordBox;

    ImageButton loginButton;
    ImageButton signUpButton;
    _DATABASEManager dM;

    String userDetails [];

    CauseCatagory [] catagoryDetailsGot   =null;



    protected void onCreate(Bundle savedInstanceState) {

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads().detectDiskWrites().detectNetwork() // StrictMode is most commonly used to catch accidental disk or network access on the application's main thread
                .penaltyLog().build());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        dM = new _DATABASEManager();

        userNameBox = (EditText) findViewById(R.id.SIGNINUsername);
        passwordBox = (EditText) findViewById(R.id.SIGNINPassword);


        loginButton= (ImageButton) findViewById(R.id.SIGNINLoginButton);

        loginButton.setBackgroundResource(R.drawable.login_button_icon_changer);

        signUpButton= (ImageButton) findViewById(R.id.SIGNINSignUpButton);


        userDetails = new String[13];



        setlisteners();





    }



    void setlisteners()
    {

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSignUp =new Intent( SignIn.this, SignUp.class );
//                gettotheneedyGUI.putExtra("ldArray" ,ii );
//            gettotheneedyGUI.putExtra("causeId", causesByThisUser[item].causeId);

                startActivity( goToSignUp );
            }
        });
//
//        userNameBox.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (hasFocus) {
//
//                    userNameBox.setTextColor(Color.BLACK);
//                }
//                else
//                    userNameBox.setTextColor(Color.GRAY);
//
//            }
//        });
//        userNameBox.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//
//        passwordBox.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (hasFocus) {
////                    userNameBox.setText("");
//                    userNameBox.setTextColor(Color.BLACK);
//                }
//                else
//                    userNameBox.setTextColor(Color.GRAY);
//
//            }
//        });
//        passwordBox.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });








        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                loginButton.setImageResource(R.drawable.login_button_icon_changer);

//                Intent in = new Intent(SignIn.this, HelperFeed.class);
//                in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
//                startActivity(in);



                //the orignal one
//                new CheckLoginAndGetUserDetailsdoInBagrd().execute(2);

                new getCatagoryDetailsAndLogin().execute();
//////////////////here old
//                new CheckLoginAndGetUserDetailsArraydoInBagrd(SignIn.this).execute(2);
//////////////////here old

//                Toast.makeText(getApplicationContext(), ">"+userNameBox.getText().toString()+"<",
//                        Toast.LENGTH_LONG).show();


//                Toast.makeText(getApplicationContext(), ">"+



            }
        });




    }


    public class CheckLoginAndGetUserDetailsdoInBagrd extends AsyncTask<Integer,Integer,String >
    {
        @Override
        protected String doInBackground(Integer... params) {

            return dM.checkLoginAndGetUserDetails(userNameBox.getText().toString(), passwordBox.getText().toString());
//            return dM.checkLoginAndGetUserDetails("kamran","kamran2015");

        }

        @Override
        protected void onPostExecute(String  s) {
            super.onPostExecute(s);

            userDetails[1] =s;
            if(s=="no such user") {
                Toast.makeText(getApplicationContext(), "No Login Details found!!\nYou either have an incorrect \nUsername or Password",
                        Toast.LENGTH_SHORT).show();
            }
            else
            {

//                Intent in = new Intent(SignIn.this, HelperFeed.class);
//                in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
//                startActivity(in);


//                String dataTemp = "LOGIN USER DETAILS:  ";
//
//
//                for (int i = 0; i<12; i++)
//                {
//                    dataTemp += userDetails[i];
//                }
//
//                Toast.makeText(getApplicationContext(), dataTemp,
//                        Toast.LENGTH_LONG).show();


                //calling the next activity


            }
        }
    }

    public class getCatagoryDetailsAndLogin extends AsyncTask<Integer,Integer,CauseCatagory [] >
    {
        @Override
        protected CauseCatagory [] doInBackground(Integer... params) {

            return dM.catagoryDetails();
//            return dM.checkLoginAndGetUserDetails("kamran","kamran2015");

        }

        @Override
        protected void onPostExecute(CauseCatagory []  s) {
            super.onPostExecute(s);


            catagoryDetailsGot = s;
            new CheckLoginAndGetUserDetailsArraydoInBagrd(SignIn.this).execute(2);

        }
    }


    public class CheckLoginAndGetUserDetailsArraydoInBagrd extends AsyncTask<Integer,Integer,String [] >
    {
        public CheckLoginAndGetUserDetailsArraydoInBagrd(SignIn signIn) {
        }

        @Override
        protected String [] doInBackground(Integer... params) {

//            prgsDlg = ProgressDialog.show(getApplicationContext(), "logging in..",
//                    "LOGIN IN", true);

            return dM.checkLoginAndGetUserDetailsArray(userNameBox.getText().toString(), passwordBox.getText().toString());
//            return dM.checkLoginAndGetUserDetails("kamran","kamran2015");

        }

        @Override
        protected void onPostExecute(String [] s) {
            super.onPostExecute(s);

//            prgsDlg.dismiss();

            userDetails =s;
            if(s[0]=="no such user") {
                Toast.makeText(getApplicationContext(), "No Login Details found!!\nYou either have an incorrect \nUsername or Password",
                        Toast.LENGTH_SHORT).show();
            }
            else if (userDetails[5].contains("needy") )
            {
//                i
//                String dataTemp = "LOGIN USER DETAILS:  ";
//
//                for (int i = 0; i<12; i++)
//                {
//                    dataTemp += userDetails[i];
//                }
//                Toast.makeText(getApplicationContext(), dataTemp,
//                        Toast.LENGTH_LONG).show();


                String [] catagoryIDs = new String[catagoryDetailsGot.length];
                String [] catagoryNames = new String[catagoryDetailsGot.length];

                for (int i = 0 ;i <catagoryIDs.length ;i++)
                {
                    catagoryIDs[i] = catagoryDetailsGot[i].catagoryId;
                    catagoryNames[i] = catagoryDetailsGot[i].catagoryName;
                    Log.e("  ????? Catagory Id: " + catagoryIDs[i]+" Name: " + catagoryNames[i],"");
                }


                Bundle bndl=new Bundle();

                bndl.putStringArray("needy", userDetails);
                bndl.putStringArray("ctIDs" ,catagoryIDs);
                bndl.putStringArray("ctNms" ,catagoryNames);


//                bndl.putStringArray("needy", new String[]{"", ""});

                Intent gettotheneedyGUI =new Intent( SignIn.this, NeedyMainGui.class );
                gettotheneedyGUI.putExtras(bndl);

                startActivity( gettotheneedyGUI );


            }
            else if (userDetails[5].contains("helper"))
            {
                Toast.makeText(getApplicationContext(), "Its a helper account but i dont have shit!!! give me some info man!!! :/",
                        Toast.LENGTH_SHORT).show();

            }
        }
    }


}
