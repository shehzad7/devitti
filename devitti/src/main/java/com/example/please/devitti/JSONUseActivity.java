package com.example.please.devitti;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class JSONUseActivity extends Activity {
 EditText byear;   // To take birthyear as input from user
 Button submit;    
 TextView tv;      // TextView to show the result of MySQL query 
 
 TextView shz;
 
 String returnString;   // to store the result of MySQL query after decoding JSON
 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
     StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
  .detectDiskReads().detectDiskWrites().detectNetwork() // StrictMode is most commonly used to catch accidental disk or network access on the application's main thread
  .penaltyLog().build());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsonuse);
        

        submit = (Button) findViewById(R.id.submitbutton);
        // define the action when user clicks on submit button
        submit.setOnClickListener(new View.OnClickListener(){        
         public void onClick(View v) {
             new getData().execute(32);

         }         
        });

    }
    
    
    
    class getData extends AsyncTask<Integer, Integer, String> {

        @Override
        protected String doInBackground(Integer... params) {


            // declare parameters that are passed to PHP script i.e. the name "birthyear" and its value submitted by user
            ArrayList<NameValuePair> nameValuePairs = getNameValuPairsForSignUp();

            //////////////
            String response = null;

            // call executeHttpPost method passing necessary parameters
            try {
                response = CustomHttpClient.executeHttpPost(
//                        "http://omega.uta.edu/~kmr2464/jsonscript.php",
//                        "http://129.107.187.135/CSE5324/jsonscript.php", // your ip address if using localhost server
                        "http://devitti.org/project_phpFiles/signup.php",  // in case of a remote server
                        nameValuePairs);
//     "http://omega.uta.edu/~kmr2464/jsonscript.php",

                // store the result returned by PHP script that runs MySQL query
                String result = response.toString();

                //parse json data
                try{
                    returnString = "";
                    JSONArray jArray = new JSONArray(result);
                    for(int i=0;i<jArray.length();i++){
                        JSONObject json_data = jArray.getJSONObject(i);
                        //Get an output to the screen
                        returnString += "\n" + json_data.getString("name") + " -> "+ json_data.getInt("type");
                    }
                }
                catch(JSONException e){
                    Log.e("log_tag", "Error parsing data "+e.toString());
                }


            }
            catch (Exception e) {
                Log.e("log_tag","Error in http connection!!" + e.toString());
            }



            return returnString;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try{
                tv.setText(s);

            }
            catch(Exception e){
                Log.e("log_tag","Error in Display!" + e.toString());;
            }


        }
    }

    
//    ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
    
    //new for sending data to storedata.php

    public String postData()
    {
    	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);

    	returnString= "";
    	
        nameValuePairs.add(new BasicNameValuePair("insert_record","6"));
        nameValuePairs.add(new BasicNameValuePair("fname","Uzair_abbu"));
        nameValuePairs.add(new BasicNameValuePair("lname","Khan_abbu"));
        nameValuePairs.add(new BasicNameValuePair("cell","032323323_abbu"));
        
        nameValuePairs.add(new BasicNameValuePair("address","Mainawaliii_abbu"));
        nameValuePairs.add(new BasicNameValuePair("email","uzi@namam.com_abbu"));
        nameValuePairs.add(new BasicNameValuePair("country","Pakkkki_abbu"));

        //http post
        try{
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new      
            HttpPost("http://www.devitti.org/storedata.php");
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            InputStream is = entity.getContent();

            Log.i("postData", response.getStatusLine().toString());
            
            returnString = response.getStatusLine().toString();
            
            
        }

        catch(Exception e)
        {
            Log.e("log_tag", "Error in http connection "+e.toString());

        }
		return returnString;           
    }
    








    ArrayList<NameValuePair> getNameValuPairsForSignUp()
    {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        // define the parameter


        //////////////


        nameValuePairs.add(new BasicNameValuePair("code","signup"));


        nameValuePairs.add(new BasicNameValuePair("userId","adf"));
        nameValuePairs.add(new BasicNameValuePair("name","adf"));
        nameValuePairs.add(new BasicNameValuePair("username","adf"));
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




    //new for recieveing data from recievedata.php
    public String recieveData() throws ClientProtocolException, IOException
    {
    	
    	////////////////////////////////////////////////////////////////////
//     	public static String executeHttpPost(String url,ArrayList<NameValuePair> postParameters) throws Exception {
        
    	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
        nameValuePairs.add(new BasicNameValuePair("select_query","Select * from people"));
        
     	BufferedReader in = null;

    	  try {
    	
    	   HttpClient client = new DefaultHttpClient();
    	
    	   HttpPost request = new HttpPost("http://www.devitti.org/recievedata.php");
    	
    	   UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(nameValuePairs);
    	
    	   request.setEntity(formEntity);
    	
    	   HttpResponse response = client.execute(request);
    	
    	   in = new BufferedReader(new InputStreamReader(response.getEntity()
    	
    	     .getContent()));
    	
    	   
    	
    	   String sb = new String("");
    	
    	   String line = "";
    	
    	   String NL = System.getProperty("line.separator");
    	
    	   while ((line = in.readLine()) != null) {
    	
    	    sb+=(line + NL);
    	
    	   }
    	
    	   in.close();
    	
    	 
    	   String result = sb.toString();
    	
    	   return result;
    	
    	  } finally {

       if (in != null) {

        try {

         in.close();

        } catch (IOException e) {

         Log.e("log_tag", "Error converting result "+e.toString()); 

         e.printStackTrace();

        }

       }

      }

     


    	////////////////////////////////////////////////////////////////////
    	
    	
    	
    	
//    	returnString= "";
    	
        

        //http post
//        try{
//            HttpClient httpclient = new DefaultHttpClient();
//            HttpPost httppost = new HttpPost("http://www.devitti.org/storedata.php");
//            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
//            HttpResponse response = httpclient.execute(httppost);
//            HttpEntity entity = response.getEntity();
//            InputStream is = entity.getContent();
//
//            Log.i("postData", response.getStatusLine().toString());
//            
//            returnString = response.getStatusLine().toString();
//            
//            
//        }
//
//        catch(Exception e)
//        {
//            Log.e("log_tag", "Error in http connection "+e.toString());
//        }
//		return returnString;           
    }
        
}