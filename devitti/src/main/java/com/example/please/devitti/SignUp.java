package com.example.please.devitti;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


import java.util.ArrayList;

/**
 * Created by please on 12/21/2014.
 */

public class SignUp extends Activity{


    ProgressDialog bar;

    ImageButton signUp;
    ImageButton check;

    _DATABASEManager dM;

    EditText name;
    EditText email;
    EditText userName;
    EditText password;
    EditText confirmPassword;
    EditText accountNo;
    EditText bankName;
    EditText branchCode;
    EditText address;
    EditText city;
    EditText country;

    RadioButton needy;
    RadioButton helper;

    RadioGroup group;

    TextView usernameCheckText;



    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads().detectDiskWrites().detectNetwork() // StrictMode is most commonly used to catch accidental disk or network access on the application's main thread
                .penaltyLog().build());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        //database manager object to help with data store
        dM  = new _DATABASEManager();

        // the main signUp button
        signUp = (ImageButton) findViewById(R.id.SignUp_signUpButton);
//        check = (ImageButton) findViewById(R.id.SIGNUPCheckButton);

        // all the text fields that needs to be stored in the database
        name = (EditText) findViewById(R.id.SignUpName);
        email = (EditText) findViewById(R.id.SignUpEmail);
        userName = (EditText) findViewById(R.id.SignUpUsername);
        password = (EditText) findViewById(R.id.SignUpPassword);
        confirmPassword = (EditText) findViewById(R.id.SignUpConfirmPassword);
        accountNo = (EditText) findViewById(R.id.SignUpAccountNo);
        bankName = (EditText) findViewById(R.id.SignUpBankName);
        branchCode = (EditText) findViewById(R.id.SignUpBranchCode);
        address = (EditText) findViewById(R.id.SignUpAddress);
        city = (EditText) findViewById(R.id.SignUpCity);
        country = (EditText) findViewById(R.id.SignUpCountry);

        //the two radio buttons which determines the account type
        helper = (RadioButton) findViewById(R.id.SignUpRadioButtonHelper);
        needy = (RadioButton) findViewById(R.id.SignUpRadioButtonNeedy);

        RadioGroup rG;

        usernameCheckText =  (TextView) findViewById(R.id.SIGNUPCheckText);




//        signUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "okkkkkkkkkkkkkkk",
//                        Toast.LENGTH_LONG).show();
//
//            }
//        });


//        dM.saveSignUpData("null",name.getText().toString(),userName.getText().toString(),
//                password.getText().toString(),type,email.getText().toString(),
//                address.getText().toString(),city.getText().toString(),
//                country.getText().toString(),bankName.getText().toString(),
//                branchCode.getText().toString(),accountNo.getText().toString());
//        Toast.makeText(getApplicationContext(), "this is my Toast message!!! =)",
//                Toast.LENGTH_LONG).show();

//        dM.saveSignUpData("null","as","as","as","as","as","as","as","as","as","as","as");

//        Toast.makeText(getApplicationContext(), "t..............",
//                Toast.LENGTH_LONG).show();

        setListeners();








    }


    private View.OnClickListener strLogoAnim = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            // Do something
            Toast.makeText(getApplicationContext(), "t..............",
                Toast.LENGTH_LONG).show();
        }
    };
    void setListeners ()
    {


        needy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(helper.isChecked())
                {
                    helper.setChecked(false);
                }
            }
        });

        helper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (needy.isChecked())
                {
                    needy.setChecked(false);
                }
            }
        });


        userName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                Toast.makeText(getApplicationContext(), "onTExtChanged",
//                        Toast.LENGTH_SHORT).show();

            }

            @Override
            public void afterTextChanged(Editable s) {

//                usernameCheckText.setText("     Check to see if its available");
//                Toast.makeText(getApplicationContext(), "afterTExtChanged",
//                        Toast.LENGTH_SHORT).show();
                if(userName.getText().length()<1)
                {
                    usernameCheckText.setText("please type in a username");
                }
                else {
                    new checkUsernamedoInBagrd().execute(2);
                }


            }
        });




        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ArrayList<NameValuePair> nameValuePairs = getNameValuePairsForSignUp2() ;

//                dM.add_data_to_database(nameValuePairs);

                String validityString = isValidDataForSignUp();
                if(validityString=="valid")
                {

                    new  signUpdoInBagrd().execute(2);
                }
                else
                {

                    Toast.makeText(getApplicationContext(), validityString,
                            Toast.LENGTH_LONG).show();
                }




            }
        });




//        check.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//         new checkUsernamedoInBagrd().execute(2);
//
//            }
//        });


    }



    String isValidDataForSignUp()
    {
        String isValid = "One or more fields not correct ";



        if(usernameCheckText.getText().toString()=="available")
        {
            isValid = "valid";
        }




        return isValid;
    }


    public class checkUsernamedoInBagrd extends AsyncTask <Integer,Integer,String>
    {
        @Override
        protected String doInBackground(Integer... params) {

            return dM.checkUsernameAvailability(userName.getText().toString());
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            usernameCheckText.setText(s);
        }
    }


    public class signUpdoInBagrd extends AsyncTask <Integer,Integer,String>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();




        }

        @Override
        protected String doInBackground(Integer... params) {


            String type = "not known";
            if(helper.isChecked())
            {

                Toast.makeText(getApplicationContext(), "helper",
                        Toast.LENGTH_LONG).show();

                type = "helper";
            }
            else if(needy.isChecked())
            {
                Toast.makeText(getApplicationContext(), "needy",
                        Toast.LENGTH_LONG).show();

                type = "needy";
            }


            return dM.saveSignUpData("null",name.getText().toString(),userName.getText().toString(),
                    password.getText().toString(),type,email.getText().toString(),
                    address.getText().toString(),city.getText().toString(),
                    country.getText().toString(),bankName.getText().toString(),
                    branchCode.getText().toString(),accountNo.getText().toString());
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Toast.makeText(getApplicationContext(), "SignUP complete..",
                    Toast.LENGTH_LONG).show();


        }
    }








    public ArrayList<NameValuePair> getNameValuePairsForSignUp(String Id,String name,String userName,
                String password,String type,String email,String address,String city,
                String country,String bankName,String branchCode,String accountNo)
    {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        // define the parameter


        //////////////


        nameValuePairs.add(new BasicNameValuePair("code","signup"));


        nameValuePairs.add(new BasicNameValuePair("userId",Id));
        nameValuePairs.add(new BasicNameValuePair("name",name));
        nameValuePairs.add(new BasicNameValuePair("username",userName));
        nameValuePairs.add(new BasicNameValuePair("password",password));

        nameValuePairs.add(new BasicNameValuePair("type",type));
        nameValuePairs.add(new BasicNameValuePair("email",email));
        nameValuePairs.add(new BasicNameValuePair("address",address));
        nameValuePairs.add(new BasicNameValuePair("city",city));
        nameValuePairs.add(new BasicNameValuePair("country",country));
        nameValuePairs.add(new BasicNameValuePair("bankName",bankName));
        nameValuePairs.add(new BasicNameValuePair("branchCode",branchCode));
        nameValuePairs.add(new BasicNameValuePair("accountNo",accountNo));


        return nameValuePairs;

    }


    public ArrayList<NameValuePair> getNameValuePairsForSignUp2()
    {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        // define the parameter


        //////////////


        nameValuePairs.add(new BasicNameValuePair("code","signup"));


        nameValuePairs.add(new BasicNameValuePair("userId","NOOOOO"));
        nameValuePairs.add(new BasicNameValuePair("name","nOOOOOO"));
        nameValuePairs.add(new BasicNameValuePair("username","nOOOOOO"));
        nameValuePairs.add(new BasicNameValuePair("password","adf"));

        nameValuePairs.add(new BasicNameValuePair("type","adf"));
        nameValuePairs.add(new BasicNameValuePair("email","adf"));
        nameValuePairs.add(new BasicNameValuePair("address","adf"));
        nameValuePairs.add(new BasicNameValuePair("city","adf"));
        nameValuePairs.add(new BasicNameValuePair("country","adf"));
        nameValuePairs.add(new BasicNameValuePair("bankName","adf"));
        nameValuePairs.add(new BasicNameValuePair("branchCode","090"));
        nameValuePairs.add(new BasicNameValuePair("accountNo","0909"));


        return nameValuePairs;

    }


}
