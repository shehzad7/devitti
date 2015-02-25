package com.example.please.devitti;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by please on 12/27/2014.
 */
public class _DATABASEManager {


    public _DATABASEManager() {

    }


    /////////////////////////////////////////////////////////////////////////////////

    public void add_data_to_database(ArrayList<NameValuePair> nameValuePairs) {
//        ArrayList<NameValuePair> nameValuePairs = getNameValuPairsForSignUp();

        String returnString = null;
        //////////////
        String response = null;
        /////////////////////////////////////////////////////////
//          try {
//              postData();
//  		} catch (Exception e) {
//  			System.out.println("errorrrrrrrr");
//  		}
        /////////////////////////////////////////////////////////

        // call executeHttpPost method passing necessary parameters
        try {
            response = CustomHttpClient.executeHttpPost(
                    //"http://129.107.187.135/CSE5324/jsonscript.php", // your ip address if using localhost server
                    "http://devitti.org/project_phpFiles/signup.php",  // in case of a remote server
                    nameValuePairs);
//     "http://omega.uta.edu/~kmr2464/jsonscript.php",

            // store the result returned by PHP script that runs MySQL query
            String result = response.toString();

            //parse json data
            try {
                returnString = "";
                JSONArray jArray = new JSONArray(result);
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject json_data = jArray.getJSONObject(i);
                    //Get an output to the screen
                    returnString += "\n" + json_data.getString("name") + " -> " + json_data.getInt("birthyear");
                }
            } catch (JSONException e) {
                Log.e("log_tag", "Error parsing data " + e.toString());
            }


        } catch (Exception e) {
            Log.e("log_tag", "Error in http connection!!" + e.toString());
        }

    }
    //////


    public String checkUsernameAvailability(String username) {
        String ret = "response is null";

//        HttpPost httppost = new HttpPost("http://devitti.org/project_phpFiles/signup.php");
        try {
            HttpClient client = new DefaultHttpClient();
            String postURL = "http://devitti.org/project_phpFiles/signup.php";
            HttpPost post = new HttpPost(postURL);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("code", "usernameAvailCheck"));
            params.add(new BasicNameValuePair("usernameToBeChecked", username));
            UrlEncodedFormEntity ent = new UrlEncodedFormEntity(params, HTTP.UTF_8);
            post.setEntity(ent);
            HttpResponse responsePOST = client.execute(post);
            HttpEntity resEntity = responsePOST.getEntity();


            if (resEntity != null) {
//                Log.i("RESPONSE---->>>>",EntityUtils.toString(resEntity));
                String temp = EntityUtils.toString(resEntity);

                if (temp.length() > 20) {
                    ret = "not available";
                } else {
                    ret = "available";
                }


//                String result = EntityUtils.toString(resEntity);

//      parse json data
//                try{
//                    ret = "nochange";
//                    JSONArray jArray = new JSONArray(result);
//
//                    ret = ((Integer) jArray.length()).toString();
//                    JSONObject json_data = jArray.getJSONObject(0);
//                    ret = json_data.getString("name");
//                    for(int i=0;i<jArray.length();i++){
//                        JSONObject json_data = jArray.getJSONObject(i);
//                        //Get an output to the screen
//                        ret += "\n" + json_data.getString("name") + " -> "+ json_data.getInt("type");
//                    }
//                }
//                catch(JSONException e){
//                    Log.e("log_tag", "Error parsing data "+e.toString());
//                }


            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return ret;


    }


    public String checkUsernameAvailabilitylast(String username) {

        String returnString = "";
        boolean isAvailable = false;

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        nameValuePairs.add(new BasicNameValuePair("code", "usernameAvailCheck"));

        nameValuePairs.add(new BasicNameValuePair("usernameToBeChecked", username));


        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://www.devitti.org/project_phpFiles/signup.php");
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);


            HttpEntity entity = response.getEntity();
            InputStream is = entity.getContent();

            Log.i("postData", response.getStatusLine().toString());

            returnString = "";

            String result = response.toString();

//            parse json data
//         try{
//                 returnString = "P+"+response.toString()+"-P";
//           JSONArray jArray = new JSONArray(result);
//
//                    for(int i=0;i<jArray.length();i++){
//                         JSONObject json_data = jArray.getJSONObject(i);
//                         //Get an output to the screen
////                         returnString += "+" + json_data.getString("type") ;
//                        returnString += "\n" + json_data.getString("name") + " -> "+ json_data.getInt("birthyear");
//                 }
//         }
//         catch(JSONException e){
//                 Log.e("log_tag", "Error parsing data "+e.toString());
//         }


            returnString = result;

        } catch (Exception e) {
            Log.e("log_tag", "Error in http connection " + e.toString());

        }


        return returnString;
    }


    public String saveSignUpData(String userId, String name, String username, String password, String type,
                                 String email, String address, String city, String country, String bankName,
                                 String branchCode, String accountNo)

    {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);

        String returnString = "";

        //code for the identification of the sql query
        nameValuePairs.add(new BasicNameValuePair("code", "signup"));


        nameValuePairs.add(new BasicNameValuePair("userId", userId));
        nameValuePairs.add(new BasicNameValuePair("name", name));
        nameValuePairs.add(new BasicNameValuePair("username", username));
        nameValuePairs.add(new BasicNameValuePair("password", password));

        nameValuePairs.add(new BasicNameValuePair("type", type));
        nameValuePairs.add(new BasicNameValuePair("email", email));
        nameValuePairs.add(new BasicNameValuePair("address", address));
        nameValuePairs.add(new BasicNameValuePair("city", city));
        nameValuePairs.add(new BasicNameValuePair("country", country));
        nameValuePairs.add(new BasicNameValuePair("bankName", bankName));
        nameValuePairs.add(new BasicNameValuePair("branchCode", branchCode));
        nameValuePairs.add(new BasicNameValuePair("accountNo", accountNo));


        //http post
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://www.devitti.org/project_phpFiles/signup.php");
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            InputStream is = entity.getContent();

            Log.i("postData", response.getStatusLine().toString());

            returnString = response.getStatusLine().toString();


        } catch (Exception e) {
            Log.e("log_tag", "Error in http connection " + e.toString());

        }
        return returnString;
    }

    public String saveCause(Cause cu) {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);

        String returnString = "";

        //code for the identification of the sql query
        nameValuePairs.add(new BasicNameValuePair("code", "addCause"));


        nameValuePairs.add(new BasicNameValuePair("causeId", cu.causeId));
        nameValuePairs.add(new BasicNameValuePair("needyId", cu.needyId));
        nameValuePairs.add(new BasicNameValuePair("moneyAskedFor", cu.moneyAskedFor));
        nameValuePairs.add(new BasicNameValuePair("description", cu.description));
        nameValuePairs.add(new BasicNameValuePair("status", cu.status));
        nameValuePairs.add(new BasicNameValuePair("catagory", cu.catagory));
        nameValuePairs.add(new BasicNameValuePair("dateOfRequest", cu.dateOfRequest));
        nameValuePairs.add(new BasicNameValuePair("dateOfCompletion", cu.dateOfCompletion));
        nameValuePairs.add(new BasicNameValuePair("dateOfMaturity", cu.dateOfMaturity));
        nameValuePairs.add(new BasicNameValuePair("latitude", cu.latitude));
        nameValuePairs.add(new BasicNameValuePair("longitude", cu.longitude));
        nameValuePairs.add(new BasicNameValuePair("type", cu.type));


        //http post
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://www.devitti.org/project_phpFiles/addCauses.php");
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            InputStream is = entity.getContent();

            Log.i("postData", response.getStatusLine().toString());

            returnString = response.getStatusLine().toString();


        } catch (Exception e) {
            Log.e("log_tag", "Error in http connection " + e.toString());

        }
        return returnString;
    }


    public String checkUsernameType(String username) {
        String ret = "response is null";

        String endString = "";

//        HttpPost httppost = new HttpPost("http://devitti.org/project_phpFiles/signup.php");
        try {
            HttpClient client = new DefaultHttpClient();
            String postURL = "http://devitti.org/project_phpFiles/login.php";
            HttpPost post = new HttpPost(postURL);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("code", "signintypecheck"));
            params.add(new BasicNameValuePair("usernameToBeChecked", username));
            UrlEncodedFormEntity ent = new UrlEncodedFormEntity(params, HTTP.UTF_8);
            post.setEntity(ent);
            HttpResponse responsePOST = client.execute(post);
            HttpEntity resEntity = responsePOST.getEntity();


            if (resEntity != null) {
//                Log.i("RESPONSE---->>>>",EntityUtils.toString(resEntity));

                String temp = EntityUtils.toString(resEntity);


                try {
                    endString = "";
                    JSONArray jArray = new JSONArray(temp);
                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject json_data = jArray.getJSONObject(i);
                        //Get an output to the screen
                        endString += json_data.getString("type");
                    }
                } catch (JSONException e) {
                    Log.e("log_tag", "Error parsing data " + e.toString());
                }


                Log.i("TYPe ", ">" + endString + "<");

                ret = endString;


            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ret;
    }


    public String checkLoginAndGetUserDetails(String username, String password) {
//        String [] ret = new String[13];
        String ret = "response is null";

        String endString = "";

//        HttpPost httppost = new HttpPost("http://devitti.org/project_phpFiles/signup.php");
        try {
            HttpClient client = new DefaultHttpClient();
            String postURL = "http://devitti.org/project_phpFiles/login.php";
            HttpPost post = new HttpPost(postURL);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("code", "signInCheckLoginAndGetUserId"));
            params.add(new BasicNameValuePair("userName", username));
            params.add(new BasicNameValuePair("password", password));

            UrlEncodedFormEntity ent = new UrlEncodedFormEntity(params, HTTP.UTF_8);
            post.setEntity(ent);
            HttpResponse responsePOST = client.execute(post);
            HttpEntity resEntity = responsePOST.getEntity();


            if (resEntity != null) {
//                Log.i("RESPONSE---->>>>", EntityUtils.toString(resEntity));

                String temp = EntityUtils.toString(resEntity);


                if (temp == null || "".equals(temp) || temp.length() < 40) {
                    ret = "no such user";

                } else {

                    try {
                        endString = "";
                        JSONArray jArray = new JSONArray(temp);


                        JSONObject json_data = jArray.getJSONObject(0);
                        //Get an output to the screen

                        endString += "userId: " + json_data.getString("userId") + " " +
                                "name: " + json_data.getString("name") + " " +
                                "username: " + json_data.getString("username") + " " +
                                "password: " + json_data.getString("password") + " " +
                                "type: " + json_data.getString("type") + " " +
                                "email: " + json_data.getString("email") + " " +
                                "address: " + json_data.getString("address") + " " +
                                "city: " + json_data.getString("city") + " " +
                                "country: " + json_data.getString("country") + " " +
                                "bankName: " + json_data.getString("bankName") + " " +
                                "branchCode: " + json_data.getString("branchCode") + " " +
                                "accountNo: " + json_data.getString("accountNo") + " ";

                    } catch (JSONException e) {
                        Log.e("log_tag", "Error parsing data " + e.toString());
                    }


                    Log.i("USER Details ", ">" + endString + "<");


                    ret = endString;

                }


            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ret;
    }


    /**
     * @param password
     * @param username checks the login details for validity and also returns the user data in a String array
     * @return String array []
     */
    public String[] checkLoginAndGetUserDetailsArray(String username, String password) {
        String[] ret = new String[13];
//        String ret  = "response is null";

        String endString = "";

//        HttpPost httppost = new HttpPost("http://devitti.org/project_phpFiles/signup.php");
        try {
            HttpClient client = new DefaultHttpClient();
            String postURL = "http://devitti.org/project_phpFiles/login.php";
            HttpPost post = new HttpPost(postURL);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("code", "signInCheckLoginAndGetUserId"));
            params.add(new BasicNameValuePair("userName", username));
            params.add(new BasicNameValuePair("password", password));

            UrlEncodedFormEntity ent = new UrlEncodedFormEntity(params, HTTP.UTF_8);
            post.setEntity(ent);
            HttpResponse responsePOST = client.execute(post);
            HttpEntity resEntity = responsePOST.getEntity();


            if (resEntity != null) {
//                Log.i("RESPONSE---->>>>", EntityUtils.toString(resEntity));

                String temp = EntityUtils.toString(resEntity);


                if (temp == null || "".equals(temp) || temp.length() < 40) {
                    ret[0] = "no such user";

                } else {

                    try {
                        endString = "";
                        JSONArray jArray = new JSONArray(temp);


                        JSONObject json_data = jArray.getJSONObject(0);
                        //Get an output to the screen

                        endString += "userId: " + json_data.getString("userId") + " " +
                                "name: " + json_data.getString("name") + " " +
                                "username: " + json_data.getString("username") + " " +
                                "password: " + json_data.getString("password") + " " +
                                "type: " + json_data.getString("type") + " " +
                                "email: " + json_data.getString("email") + " " +
                                "address: " + json_data.getString("address") + " " +
                                "city: " + json_data.getString("city") + " " +
                                "country: " + json_data.getString("country") + " " +
                                "bankName: " + json_data.getString("bankName") + " " +
                                "branchCode: " + json_data.getString("branchCode") + " " +
                                "accountNo: " + json_data.getString("accountNo") + " ";

                        ret[1] = json_data.getString("userId");
                        ret[2] = json_data.getString("name");
                        ret[3] = json_data.getString("username");
                        ret[4] = json_data.getString("password");
                        ret[5] = json_data.getString("type");
                        ret[6] = json_data.getString("email");
                        ret[7] = json_data.getString("address");
                        ret[8] = json_data.getString("city");
                        ret[9] = json_data.getString("country");
                        ret[10] = json_data.getString("bankName");
                        ret[11] = json_data.getString("branchCode");
                        ret[12] = json_data.getString("accountNo");
                        ret[0] = "user details retrieved ";


                    } catch (JSONException e) {
                        Log.e("log_tag", "Error parsing data " + e.toString());
                    }


                    Log.i("USER Details ", ">" + endString + "<");


//                    ret = endString;

                }


            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ret;
    }


    /**
     * @param needyId takes in a needy Id and returns all the causes for that Id
     * @return Cause array []
     */
    public Cause[] getCausesByNeedyId(String needyId) {
        Cause[] allCausesByUser = null;

        //        String [] ret = new String[13];
        String ret = "response is null";

        String endString = "";

//        HttpPost httppost = new HttpPost("http://devitti.org/project_phpFiles/signup.php");
        try {
            HttpClient client = new DefaultHttpClient();
            String postURL = "http://devitti.org/project_phpFiles/GiveMeCauses.php";
            HttpPost post = new HttpPost(postURL);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("code", "causesForACauseId"));
            params.add(new BasicNameValuePair("needyId", needyId));

            UrlEncodedFormEntity ent = new UrlEncodedFormEntity(params, HTTP.UTF_8);
            post.setEntity(ent);
            HttpResponse responsePOST = client.execute(post);
            HttpEntity resEntity = responsePOST.getEntity();


            if (resEntity != null) {
//                Log.i("RESPONSE---->>>>", EntityUtils.toString(resEntity));

                String temp = EntityUtils.toString(resEntity);


                if (temp == null || "".equals(temp) || temp.length() < 40) {
                    ret = "no cause found";
                    Log.i("hehheh   ", needyId);

                } else {

                    Log.i(".........", ".........");
                    Log.i("i am here too", ".........");
                    Log.i(".........", ".........");

                    try {


                        endString = "";
                        JSONArray jArray = new JSONArray(temp);

                        allCausesByUser = new Cause[jArray.length()];


                        for (int i = 0; i < jArray.length(); i++) {

//                            LendingDetailForCause ldet = new LendingDetailForCause(1,1,1,1,1,"");

                            Cause tempCause = new Cause("d", "d", "d", "d", "d", "d", "d", "d", "d", "d", "d", null, "cat", "", "");


                            JSONObject json_data = jArray.getJSONObject(i);
                            tempCause.causeId = json_data.getString("causeId");
                            tempCause.needyId = json_data.getString("needyId");
                            tempCause.moneyAskedFor = json_data.getString("moneyAskedFor");
                            tempCause.description = json_data.getString("description");

                            tempCause.status = json_data.getString("status");
                            tempCause.catagory = json_data.getString("catagory");
                            tempCause.dateOfRequest = json_data.getString("dateOfRequest");
                            tempCause.dateOfCompletion = json_data.getString("dateOfCompletion");
                            tempCause.dateOfMaturity = json_data.getString("dateOfMaturity");
                            tempCause.latitude = json_data.getString("latitude");
                            tempCause.longitude = json_data.getString("longitude");
                            tempCause.type = json_data.getString("type");

                            tempCause.noOfEndorsements = json_data.getString("noOfEndorsements");
                            tempCause.noOfLendingDetails = json_data.getString("noOfLendingDetails");

                            allCausesByUser[i] = tempCause;

//                            Log.i("CAUSE AT::::: ", ">"+i+"<   " + "CAUSE ID: "+json_data.getString("causeId")+"NeedyID: "+json_data.getString("needyId")
//                            +"MONEY ASKED FOR: "+ json_data.getString("moneyAskedFor"));


                        }

                        //Get an output to the screen

                    } catch (JSONException e) {
                        Log.e("log_tag", "Error parsing data " + e.toString());
                    }


                    Log.i("USER Details ", ">" + endString + "<");


                }


            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


//        for(int i= 0; i<allCausesByUser.length ; i++)
//        {
//            Log.i(i+" "+"CAUSESSSS DETAILLLSSSS: "+ "Money asked for : "+allCausesByUser[i].moneyAskedFor,".........");
//        }

        return allCausesByUser;

    }


    public CauseCatagory[] catagoryDetails() {
        CauseCatagory ct[] = null;
//


        String ret = "response is null";

        String endString = "";

//        HttpPost httppost = new HttpPost("http://devitti.org/project_phpFiles/signup.php");
        try {
            HttpClient client = new DefaultHttpClient();
            String postURL = "http://devitti.org/project_phpFiles/GiveMeCauses.php";
            HttpPost post = new HttpPost(postURL);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("code", "getCatagories"));

            UrlEncodedFormEntity ent = new UrlEncodedFormEntity(params, HTTP.UTF_8);
            post.setEntity(ent);
            HttpResponse responsePOST = client.execute(post);
            HttpEntity resEntity = responsePOST.getEntity();


            if (resEntity != null) {
//                Log.i("RESPONSE---->>>>", EntityUtils.toString(resEntity));

                String temp = EntityUtils.toString(resEntity);


//                    Log.i(".........",".........");
//                    Log.i("i am here too",".........");
//                    Log.i(".........",".........");

                try {


                    endString = "";
                    JSONArray jArray = new JSONArray(temp);

                    ct = new CauseCatagory[jArray.length()];


                    for (int i = 0; i < jArray.length(); i++) {

//                            LendingDetailForCause ldet = new LendingDetailForCause(1,1,1,1,1,"");

                        CauseCatagory tempCauseCatagory = new CauseCatagory("", "");


                        JSONObject json_data = jArray.getJSONObject(i);
                        tempCauseCatagory.catagoryId = json_data.getString("catagoryId");
                        tempCauseCatagory.catagoryName = json_data.getString("catagoryName");


                        ct[i] = tempCauseCatagory;

                        Log.i("Catagory::::: ", ">" + i + "<   " + "CATAGORY: " + json_data.getString("catagoryName"));


                    }

                    //Get an output to the screen

                } catch (JSONException e) {
                    Log.e("log_tag", "Error parsing data " + e.toString());
                }


//                    Log.i("USER Details ", ">" + endString + "<");


            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return ct;
    }


    public LendingDetailForCause[] getLendingDetailsForACause(Cause cu) {
        LendingDetailForCause[] returnLD = null;


        String ret = "response is null";
        String endString = "";

        try {
            HttpClient client = new DefaultHttpClient();
            String postURL = "http://devitti.org/project_phpFiles/giveMeCauseDetail.php";
            HttpPost post = new HttpPost(postURL);
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair("code", "giveCauseDetail"));
            params.add(new BasicNameValuePair("causeId", cu.causeId));

            UrlEncodedFormEntity ent = new UrlEncodedFormEntity(params, HTTP.UTF_8);
            post.setEntity(ent);
            HttpResponse responsePOST = client.execute(post);
            HttpEntity resEntity = responsePOST.getEntity();
            if (resEntity != null) {


                String temp = EntityUtils.toString(resEntity);
                try {
                    JSONArray jArray = new JSONArray(temp);


                    returnLD = new LendingDetailForCause[jArray.length()];

                    Log.e("I am hereeeeeeeeeee", String.valueOf(jArray.length()));
                    for (int i = 0; i < jArray.length(); i++) {

//                            LendingDetailForCause ldet = new LendingDetailForCause(1,1,1,1,1,"");

                        LendingDetailForCause tempLD = new LendingDetailForCause(0, 0, 0, 0, 0, "");

                        JSONObject json_data = jArray.getJSONObject(i);

                        tempLD.LDFCId = json_data.getInt("LDFCId");
                        tempLD.causeId = json_data.getInt("causeId");
                        tempLD.helperId = json_data.getInt("helperId");
                        tempLD.needyId = json_data.getInt("needyId");
                        tempLD.amountLended = json_data.getInt("amountLended");
                        tempLD.status = json_data.getString("status");


                        returnLD[i] = tempLD;
//                        Log.i("Catagory::::: ", ">"+i+"<   " + "CATAGORY: "+json_data.getString("catagoryName"));
                    }

                } catch (JSONException e) {
                    Log.e("log_tag", "Error parsing data " + e.toString());
                }

                for (int i = 0; i < returnLD.length; i++) {
                    System.out.println();

                    System.out.print(" (MAnager)LDFCIds for causeId: " + cu.causeId + " are:  ");

                    System.out.print(" " + returnLD[i].LDFCId);
                    System.out.print(" " + returnLD[i].causeId);
                    System.out.print(" " + returnLD[i].helperId);
                    System.out.print(" " + returnLD[i].amountLended);
                    System.out.print(" " + returnLD[i].status);
                    System.out.println();
                }

            } else {
                System.out.println("Cause Detail not available...");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    public Cause[] getAllCausesByUserAndDetailsForAllCauses(String needy_Id, Cause[] CausesByThisUser) {

        Cause[] returnCauses = CausesByThisUser;

        String ret = "response is null";
        String endString = "";

        try {
            HttpClient client = new DefaultHttpClient();
            String postURL = "http://devitti.org/project_phpFiles/giveMeCauseDetail.php";
            HttpPost post = new HttpPost(postURL);
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair("code", "giveAllCausesDetail"));
            params.add(new BasicNameValuePair("needyId", needy_Id));

            UrlEncodedFormEntity ent = new UrlEncodedFormEntity(params, HTTP.UTF_8);
            post.setEntity(ent);
            HttpResponse responsePOST = client.execute(post);
            HttpEntity resEntity = responsePOST.getEntity();

//            Log.e("I am hereeeeeeeeeee at the give me all causes detail at once ", "");

            if (resEntity != null) {


                LendingDetailForCause tempLD[] = null;
                String temp = EntityUtils.toString(resEntity);

                try {
                    JSONArray jArray = new JSONArray(temp);
                    tempLD = new LendingDetailForCause[jArray.length()];

                    //just to check how many causes of thi user has lending details
                    int count = 1;
                    System.out.println();
                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject json_data = jArray.getJSONObject(i);

                        System.out.println("........" + json_data.getInt("causeId"));
                        if (json_data.getInt("causeId") > count) {
                            count = json_data.getInt("causeId");
                        }
                    }


                    //add the cause details for all those causes who has them

                    for (int allCauses = 0; allCauses < CausesByThisUser.length; allCauses++) {
                        CausesByThisUser[allCauses].lendingDetails = null;
                    }


                    for (int ca = 0; ca < returnCauses.length; ca++) {

                        for (int i = 0; i < jArray.length(); i++) {

                            JSONObject json_row = jArray.getJSONObject(i);

                            if (json_row.getString("causeId").contains(returnCauses[ca].causeId.toString())) {

                                if (!(returnCauses[ca].lendingDetails == null)) {

                                    System.out.println("not nulllllll found");
                                    LendingDetailForCause newLend[] = new LendingDetailForCause[returnCauses[ca].lendingDetails.length + 1];

                                    for (int s = 0; s < newLend.length - 1; s++) {

                                        newLend[s] = returnCauses[ca].lendingDetails[s];

                                    }
                                    LendingDetailForCause tempLending = new LendingDetailForCause(
                                            json_row.getInt("LDFCId"), json_row.getInt("causeId"), json_row.getInt("helperId"),
                                            json_row.getInt("needyId"), json_row.getInt("amountLended"), json_row.getString("status"));// new data added

                                    newLend[newLend.length - 1] = tempLending;
                                    returnCauses[ca].lendingDetails = newLend;


                                }


                                if (returnCauses[ca].lendingDetails == null) {
                                    System.out.println("nullllllll found");
                                    LendingDetailForCause tempLending = new LendingDetailForCause(json_row.getInt("LDFCId"),
                                            json_row.getInt("causeId"), json_row.getInt("helperId"), json_row.getInt("needyId"),
                                            json_row.getInt("amountLended"), json_row.getString("status"));

                                    LendingDetailForCause ld[] = new LendingDetailForCause[1];
                                    ld[0] = tempLending;
                                    returnCauses[ca].lendingDetails = ld;
                                }
                            }

                        }

                    }


                    for (int i = 0; i < jArray.length(); i++) {


                        tempLD[i] = new LendingDetailForCause(0, 0, 0, 0, 0, "");

                        JSONObject json_data = jArray.getJSONObject(i);

                        tempLD[i].LDFCId = json_data.getInt("LDFCId");
                        tempLD[i].causeId = json_data.getInt("causeId");
                        tempLD[i].helperId = json_data.getInt("helperId");
                        tempLD[i].needyId = json_data.getInt("needyId");
                        tempLD[i].amountLended = json_data.getInt("amountLended");
                        tempLD[i].status = json_data.getString("status");
                    }

                    System.out.println();
                    for (int u = 0; u < returnCauses.length; u++) {

                        if (returnCauses[u].lendingDetails == null) {
//                            System.out.println("lending Detaila at [" + u + "]" + " is null");
                        } else {

                            for (int p = 0; p < returnCauses[u].lendingDetails.length; p++) {

                                System.out.print(" " + returnCauses[u].lendingDetails[p].LDFCId);
                                System.out.print(" " + returnCauses[u].lendingDetails[p].causeId);
                                System.out.print(" " + returnCauses[u].lendingDetails[p].helperId);
                                System.out.print(" " + returnCauses[u].lendingDetails[p].needyId);
                                System.out.print(" " + returnCauses[u].lendingDetails[p].amountLended);
                                System.out.print(" " + returnCauses[u].lendingDetails[p].status);


                                System.out.println();
                            }
                        }
                        System.out.println();
                    }


                    Log.e("no of causes by " + needy_Id, (String.valueOf(CausesByThisUser.length)));
                    System.out.println("to confirm: " + CausesByThisUser.length);

                } catch (JSONException e) {
                    Log.e("log_tag", "Error parsing data " + e.toString());
                }


            } else {
                System.out.println("Cause Detail not available...");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return returnCauses;
    }


    public Cause[] getSearchResultForHelper(String givenData[]) {

//        System.out.println("IITITITITTTI:  "+getQueryForHelperSearch(givenData));

        Cause[] allCausesByUser = null;
//        String [] ret = new String[13];
        String ret = "response is null";

        String endString = "";

//        HttpPost httppost = new HttpPost("http://devitti.org/project_phpFiles/signup.php");
        try {
            HttpClient client = new DefaultHttpClient();
            String postURL = "http://devitti.org/project_phpFiles/giveMeCausesForHelperSearch.php";
            HttpPost post = new HttpPost(postURL);
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair("code", "causesForHelperSearch"));

            params.add(new BasicNameValuePair("email", givenData[0]));
            params.add(new BasicNameValuePair("country", givenData[1]));
            params.add(new BasicNameValuePair("city", givenData[2]));
            params.add(new BasicNameValuePair("type", givenData[3]));
            params.add(new BasicNameValuePair("catagory", givenData[4]));
            params.add(new BasicNameValuePair("range", givenData[5]));


//            params.add(new BasicNameValuePair("needyId", needyId));

            UrlEncodedFormEntity ent = new UrlEncodedFormEntity(params, HTTP.UTF_8);
            post.setEntity(ent);
            HttpResponse responsePOST = client.execute(post);
            HttpEntity resEntity = responsePOST.getEntity();


            if (resEntity != null) {
//                Log.i("RESPONSE---->>>>", EntityUtils.toString(resEntity));

                String temp = EntityUtils.toString(resEntity);


                if (temp == null || "".equals(temp) || temp.length() < 40) {
                    System.out.println("<<<<<<<<>>>>>>>>>> no cause Found man !!!");
                    ret = "no cause found";
//                    Log.i("hehheh   ", needyId);


                } else {
                    System.out.println("<<<<<<<<>>>>>>>>>> yes we have something ");

                    try {


                        endString = "";
                        JSONArray jArray = new JSONArray(temp);

                        allCausesByUser = new Cause[jArray.length()];


                        for (int i = 0; i < jArray.length(); i++) {

//                            LendingDetailForCause ldet = new LendingDetailForCause(1,1,1,1,1,"");

                            Cause tempCause = new Cause("d", "d", "d", "d", "d", "d", "d", "d", "d", "d", "d", null, "cat", "", "");


                            JSONObject json_data = jArray.getJSONObject(i);
                            tempCause.causeId = json_data.getString("causeId");
                            tempCause.needyId = json_data.getString("needyId");
                            tempCause.moneyAskedFor = json_data.getString("moneyAskedFor");
                            tempCause.description = json_data.getString("description");

                            tempCause.status = json_data.getString("status");
                            tempCause.catagory = json_data.getString("catagory");
                            tempCause.dateOfRequest = json_data.getString("dateOfRequest");
                            tempCause.dateOfCompletion = json_data.getString("dateOfCompletion");
                            tempCause.dateOfMaturity = json_data.getString("dateOfMaturity");
                            tempCause.latitude = json_data.getString("latitude");
                            tempCause.longitude = json_data.getString("longitude");
                            tempCause.type = json_data.getString("type");

                            tempCause.noOfEndorsements = json_data.getString("noOfEndorsements");
                            tempCause.noOfLendingDetails = json_data.getString("noOfLendingDetails");

                            allCausesByUser[i] = tempCause;

//                            Log.i("CAUSE AT::::: ", ">"+i+"<   " + "CAUSE ID: "+json_data.getString("causeId")+"NeedyID: "+json_data.getString("needyId")
//                            +"MONEY ASKED FOR: "+ json_data.getString("moneyAskedFor"));


                        }

                        //Get an output to the screen

                    } catch (JSONException e) {
                        Log.e("log_tag", "Error parsing data " + e.toString());
                    }


//                    Log.i("USER Details ", ">" + endString + "<");


                }


            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


//        for(int i= 0; i<allCausesByUser.length ; i++)
//        {
//            Log.i(i+" "+"CAUSESSSS DETAILLLSSSS: "+ "Money asked for : "+allCausesByUser[i].moneyAskedFor,".........");
//        }

        return allCausesByUser;

    }



    public Cause[] getAllCausesAndDetailsForAllCauses(String needy_Id, Cause[] CausesByThisUser) {

        Cause[] returnCauses = CausesByThisUser;

        String ret = "response is null";
        String endString = "";

        try {
            HttpClient client = new DefaultHttpClient();
            String postURL = "http://devitti.org/project_phpFiles/giveMeCauseDetail.php";
            HttpPost post = new HttpPost(postURL);
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair("code", "giveAllCausesDetail"));
            params.add(new BasicNameValuePair("needyId", needy_Id));

            UrlEncodedFormEntity ent = new UrlEncodedFormEntity(params, HTTP.UTF_8);
            post.setEntity(ent);
            HttpResponse responsePOST = client.execute(post);
            HttpEntity resEntity = responsePOST.getEntity();

//            Log.e("I am hereeeeeeeeeee at the give me all causes detail at once ", "");

            if (resEntity != null) {


                LendingDetailForCause tempLD[] = null;
                String temp = EntityUtils.toString(resEntity);

                try {
                    JSONArray jArray = new JSONArray(temp);
                    tempLD = new LendingDetailForCause[jArray.length()];

                    //just to check how many causes of thi user has lending details
                    int count = 1;
                    System.out.println();
                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject json_data = jArray.getJSONObject(i);

                        System.out.println("........" + json_data.getInt("causeId"));
                        if (json_data.getInt("causeId") > count) {
                            count = json_data.getInt("causeId");
                        }
                    }


                    //add the cause details for all those causes who has them

                    for (int allCauses = 0; allCauses < CausesByThisUser.length; allCauses++) {
                        CausesByThisUser[allCauses].lendingDetails = null;
                    }


                    for (int ca = 0; ca < returnCauses.length; ca++) {

                        for (int i = 0; i < jArray.length(); i++) {

                            JSONObject json_row = jArray.getJSONObject(i);

                            if (json_row.getString("causeId").contains(returnCauses[ca].causeId.toString())) {

                                if (!(returnCauses[ca].lendingDetails == null)) {

                                    System.out.println("not nulllllll found");
                                    LendingDetailForCause newLend[] = new LendingDetailForCause[returnCauses[ca].lendingDetails.length + 1];

                                    for (int s = 0; s < newLend.length - 1; s++) {

                                        newLend[s] = returnCauses[ca].lendingDetails[s];

                                    }
                                    LendingDetailForCause tempLending = new LendingDetailForCause(
                                            json_row.getInt("LDFCId"), json_row.getInt("causeId"), json_row.getInt("helperId"),
                                            json_row.getInt("needyId"), json_row.getInt("amountLended"), json_row.getString("status"));// new data added

                                    newLend[newLend.length - 1] = tempLending;
                                    returnCauses[ca].lendingDetails = newLend;


                                }


                                if (returnCauses[ca].lendingDetails == null) {
                                    System.out.println("nullllllll found");
                                    LendingDetailForCause tempLending = new LendingDetailForCause(json_row.getInt("LDFCId"),
                                            json_row.getInt("causeId"), json_row.getInt("helperId"), json_row.getInt("needyId"),
                                            json_row.getInt("amountLended"), json_row.getString("status"));

                                    LendingDetailForCause ld[] = new LendingDetailForCause[1];
                                    ld[0] = tempLending;
                                    returnCauses[ca].lendingDetails = ld;
                                }
                            }

                        }

                    }


                    for (int i = 0; i < jArray.length(); i++) {


                        tempLD[i] = new LendingDetailForCause(0, 0, 0, 0, 0, "");

                        JSONObject json_data = jArray.getJSONObject(i);

                        tempLD[i].LDFCId = json_data.getInt("LDFCId");
                        tempLD[i].causeId = json_data.getInt("causeId");
                        tempLD[i].helperId = json_data.getInt("helperId");
                        tempLD[i].needyId = json_data.getInt("needyId");
                        tempLD[i].amountLended = json_data.getInt("amountLended");
                        tempLD[i].status = json_data.getString("status");
                    }

                    System.out.println();
                    for (int u = 0; u < returnCauses.length; u++) {

                        if (returnCauses[u].lendingDetails == null) {
//                            System.out.println("lending Detaila at [" + u + "]" + " is null");
                        } else {

                            for (int p = 0; p < returnCauses[u].lendingDetails.length; p++) {

                                System.out.print(" " + returnCauses[u].lendingDetails[p].LDFCId);
                                System.out.print(" " + returnCauses[u].lendingDetails[p].causeId);
                                System.out.print(" " + returnCauses[u].lendingDetails[p].helperId);
                                System.out.print(" " + returnCauses[u].lendingDetails[p].needyId);
                                System.out.print(" " + returnCauses[u].lendingDetails[p].amountLended);
                                System.out.print(" " + returnCauses[u].lendingDetails[p].status);


                                System.out.println();
                            }
                        }
                        System.out.println();
                    }


                    Log.e("no of causes by " + needy_Id, (String.valueOf(CausesByThisUser.length)));
                    System.out.println("to confirm: " + CausesByThisUser.length);

                } catch (JSONException e) {
                    Log.e("log_tag", "Error parsing data " + e.toString());
                }


            } else {
                System.out.println("Cause Detail not available...");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return returnCauses;
    }


    public Cause[] getAllCausesDetailsForAllCauses( Cause[] causes) {

        String query = "";


//        query = "SELECT * FROM lendingDetailsForCauses where causeId  = '10' ";
        query = "";

//        SELECT * FROM lendingDetailsForCauses where needyId = '$needyId'


        List<NameValuePair> params = new ArrayList<NameValuePair>();

        params.add(new BasicNameValuePair("code", "giveCauseDetailForTheseCauses"));
        params.add(new BasicNameValuePair("queryForCauseDetail ", query));


        query ="SELECT * FROM lendingDetailsForCauses where ( causeId = 1)  or (causeId  = 2)  or (causeId  = 3) ";

        for (int i = 0;  i <causes.length ; i++)
        {
            params.add(new BasicNameValuePair("array[]", causes[i].causeId));

//            if ( i == 0 )
//           {
//               query +=   "  ( causeId = " + causes[i].causeId + ") " ;
//           }
//           else
//           {
//               query += " or (causeId  = " + causes[i].causeId + ") ";
//
//           }

        }




        Log.e("the query  ::::::" , query);


        Cause[] returnCauses = causes;

        String ret = "response is null";
        String endString = "";

        try {
            HttpClient client = new DefaultHttpClient();
            String postURL = "http://devitti.org/project_phpFiles/giveMeCauseDetail.php";
            HttpPost post = new HttpPost(postURL);

            UrlEncodedFormEntity ent = new UrlEncodedFormEntity(params, HTTP.UTF_8);
            post.setEntity(ent);
            HttpResponse responsePOST = client.execute(post);
            HttpEntity resEntity = responsePOST.getEntity();

//            Log.e("I am hereeeeeeeeeee at the give me all causes detail at once ", "");

            if (resEntity != null) {


                LendingDetailForCause tempLD[] = null;
                String temp = EntityUtils.toString(resEntity);

                try {
                    JSONArray jArray = new JSONArray(temp);
                    tempLD = new LendingDetailForCause[jArray.length()];

                    //just to check how many causes of thi user has lending details
                    int count = 1;
                    System.out.println();
                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject json_data = jArray.getJSONObject(i);

                        System.out.println("........" + json_data.getInt("causeId"));
                        if (json_data.getInt("causeId") > count) {
                            count = json_data.getInt("causeId");
                        }
                    }


                    //add the cause details for all those causes who has them

                    for (int allCauses = 0; allCauses < causes.length; allCauses++) {
                        causes[allCauses].lendingDetails = null;
                    }


                    for (int ca = 0; ca < returnCauses.length; ca++) {

                        for (int i = 0; i < jArray.length(); i++) {

                            JSONObject json_row = jArray.getJSONObject(i);

                            if (json_row.getString("causeId").contains(returnCauses[ca].causeId.toString())) {

                                if (!(returnCauses[ca].lendingDetails == null)) {

                                    System.out.println("not nulllllll found");
                                    LendingDetailForCause newLend[] = new LendingDetailForCause[returnCauses[ca].lendingDetails.length + 1];

                                    for (int s = 0; s < newLend.length - 1; s++) {

                                        newLend[s] = returnCauses[ca].lendingDetails[s];

                                    }
                                    LendingDetailForCause tempLending = new LendingDetailForCause(
                                            json_row.getInt("LDFCId"), json_row.getInt("causeId"), json_row.getInt("helperId"),
                                            json_row.getInt("needyId"), json_row.getInt("amountLended"), json_row.getString("status"));// new data added

                                    newLend[newLend.length - 1] = tempLending;
                                    returnCauses[ca].lendingDetails = newLend;


                                }


                                if (returnCauses[ca].lendingDetails == null) {
                                    System.out.println("nullllllll found");
                                    LendingDetailForCause tempLending = new LendingDetailForCause(json_row.getInt("LDFCId"),
                                            json_row.getInt("causeId"), json_row.getInt("helperId"), json_row.getInt("needyId"),
                                            json_row.getInt("amountLended"), json_row.getString("status"));

                                    LendingDetailForCause ld[] = new LendingDetailForCause[1];
                                    ld[0] = tempLending;
                                    returnCauses[ca].lendingDetails = ld;
                                }
                            }

                        }

                    }


                    for (int i = 0; i < jArray.length(); i++) {


                        tempLD[i] = new LendingDetailForCause(0, 0, 0, 0, 0, "");

                        JSONObject json_data = jArray.getJSONObject(i);

                        tempLD[i].LDFCId = json_data.getInt("LDFCId");
                        tempLD[i].causeId = json_data.getInt("causeId");
                        tempLD[i].helperId = json_data.getInt("helperId");
                        tempLD[i].needyId = json_data.getInt("needyId");
                        tempLD[i].amountLended = json_data.getInt("amountLended");
                        tempLD[i].status = json_data.getString("status");
                    }

                    System.out.println();
                    for (int u = 0; u < returnCauses.length; u++) {

                        if (returnCauses[u].lendingDetails == null) {
//                            System.out.println("lending Detaila at [" + u + "]" + " is null");
                        } else {

                            for (int p = 0; p < returnCauses[u].lendingDetails.length; p++) {

                                System.out.print(" " + returnCauses[u].lendingDetails[p].LDFCId);
                                System.out.print(" " + returnCauses[u].lendingDetails[p].causeId);
                                System.out.print(" " + returnCauses[u].lendingDetails[p].helperId);
                                System.out.print(" " + returnCauses[u].lendingDetails[p].needyId);
                                System.out.print(" " + returnCauses[u].lendingDetails[p].amountLended);
                                System.out.print(" " + returnCauses[u].lendingDetails[p].status);


                                System.out.println();
                            }
                        }
                        System.out.println();
                    }


//                    Log.e("no of causes by " + needy_Id, (String.valueOf(CausesByThisUser.length)));
//                    System.out.println("to confirm: " + CausesByThisUser.length);

                } catch (JSONException e) {
                    Log.e("log_tag", "Error parsing data " + e.toString());
                }


            } else {
                System.out.println("Cause Detail not available...");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return returnCauses;
    }







//    public Cause[] getCausesByHelperId(String helperId) {
//        Cause[] allCausesByUser = null;
//
//        //        String [] ret = new String[13];
//        String ret = "response is null";
//
//        String endString = "";
//
////        HttpPost httppost = new HttpPost("http://devitti.org/project_phpFiles/signup.php");
//        try {
//            HttpClient client = new DefaultHttpClient();
//            String postURL = "http://devitti.org/project_phpFiles/GiveMeCauses.php";
//            HttpPost post = new HttpPost(postURL);
//            List<NameValuePair> params = new ArrayList<NameValuePair>();
//            params.add(new BasicNameValuePair("code", "causesForHelperId"));
//            params.add(new BasicNameValuePair("helperId", helperId));
//
//            UrlEncodedFormEntity ent = new UrlEncodedFormEntity(params, HTTP.UTF_8);
//            post.setEntity(ent);
//            HttpResponse responsePOST = client.execute(post);
//            HttpEntity resEntity = responsePOST.getEntity();
//
//
//            if (resEntity != null) {
////                Log.i("RESPONSE---->>>> for getcausesforhelperID", EntityUtils.toString(resEntity));
//
//                String temp = EntityUtils.toString(resEntity);
//
//
//                if (temp == null || "".equals(temp) || temp.length() < 40) {
//                    ret = "no cause found";
//                    Log.i("hehheh   ", helperId);
//
//                } else {
//
//                    Log.i(".........", ".........");
//                    Log.i("i am here too for herper ID causes", ".........");
//                    Log.i(".........", ".........");
//
//                    try {
//
//
//                        endString = "";
//                        JSONArray jArray = new JSONArray(temp);
//
//                        allCausesByUser = new Cause[jArray.length()];
//
//
//                        for (int i = 0; i < jArray.length(); i++) {
//
////                            LendingDetailForCause ldet = new LendingDetailForCause(1,1,1,1,1,"");
//
//                            Cause tempCause = new Cause("d", "d", "d", "d", "d", "d", "d", "d", "d", "d", "d", null, "cat", "", "");
//
//
//                            JSONObject json_data = jArray.getJSONObject(i);
//                            tempCause.causeId = json_data.getString("causeId");
//                            tempCause.needyId = json_data.getString("needyId");
//                            tempCause.moneyAskedFor = json_data.getString("moneyAskedFor");
//                            tempCause.description = json_data.getString("description");
//
//                            tempCause.status = json_data.getString("status");
//                            tempCause.catagory = json_data.getString("catagory");
//                            tempCause.dateOfRequest = json_data.getString("dateOfRequest");
//                            tempCause.dateOfCompletion = json_data.getString("dateOfCompletion");
//                            tempCause.dateOfMaturity = json_data.getString("dateOfMaturity");
//                            tempCause.latitude = json_data.getString("latitude");
//                            tempCause.longitude = json_data.getString("longitude");
//                            tempCause.type = json_data.getString("type");
//
//                            tempCause.noOfEndorsements = json_data.getString("noOfEndorsements");
//                            tempCause.noOfLendingDetails = json_data.getString("noOfLendingDetails");
//
//                            allCausesByUser[i] = tempCause;
//
////                            Log.i("CAUSE AT::::: ", ">"+i+"<   " + "CAUSE ID: "+json_data.getString("causeId")+"NeedyID: "+json_data.getString("needyId")
////                            +"MONEY ASKED FOR: "+ json_data.getString("moneyAskedFor"));
//
//
//                        }
//
//                        //Get an output to the screen
//
//                    } catch (JSONException e) {
//                        Log.e("log_tag", "Error parsing data causes for helper ID" + e.toString());
//                    }
//
//
//                    Log.i("USER Details ", ">" + endString + "<");
//
//
//                }
//
//
//            }
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
////        for(int i= 0; i<allCausesByUser.length ; i++)
////        {
////            Log.i(i+" "+"CAUSESSSS DETAILLLSSSS: "+ "Money asked for : "+allCausesByUser[i].moneyAskedFor,".........");
////        }
//
//        return allCausesByUser;
//
//    }




    public Cause[] getCausesByHelperId(String needyId) {
        Cause[] allCausesByUser = null;

        //        String [] ret = new String[13];
        String ret = "response is null";

        String endString = "";

//        HttpPost httppost = new HttpPost("http://devitti.org/project_phpFiles/signup.php");
        try {
            HttpClient client = new DefaultHttpClient();
            String postURL = "http://devitti.org/project_phpFiles/GiveMeCauses.php";
            HttpPost post = new HttpPost(postURL);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("code", "causesForHelperId"));
            params.add(new BasicNameValuePair("helperId", "8"));

            UrlEncodedFormEntity ent = new UrlEncodedFormEntity(params, HTTP.UTF_8);
            post.setEntity(ent);
            HttpResponse responsePOST = client.execute(post);
            HttpEntity resEntity = responsePOST.getEntity();


            if (resEntity != null) {
//                Log.i("RESPONSE---->>>>", EntityUtils.toString(resEntity));

                String temp = EntityUtils.toString(resEntity);


                if (temp == null || "".equals(temp) || temp.length() < 40) {
                    ret = "no cause found for helper ID ......................";
//                    Log.i("hehheh   ", needyId);

                } else {

                    Log.i(".........", "            yes helperId got causessssssssss");

                    try {


                        endString = "";
                        JSONArray jArray = new JSONArray(temp);

                        allCausesByUser = new Cause[jArray.length()];


                        for (int i = 0; i < jArray.length(); i++) {

//                            LendingDetailForCause ldet = new LendingDetailForCause(1,1,1,1,1,"");

                            Cause tempCause = new Cause("d", "d", "d", "d", "d", "d", "d", "d", "d", "d", "d", null, "cat", "", "");


                            JSONObject json_data = jArray.getJSONObject(i);
                            tempCause.causeId = json_data.getString("causeId");
                            tempCause.needyId = json_data.getString("needyId");
                            tempCause.moneyAskedFor = json_data.getString("moneyAskedFor");
                            tempCause.description = json_data.getString("description");

                            tempCause.status = json_data.getString("status");
                            tempCause.catagory = json_data.getString("catagory");
                            tempCause.dateOfRequest = json_data.getString("dateOfRequest");
                            tempCause.dateOfCompletion = json_data.getString("dateOfCompletion");
                            tempCause.dateOfMaturity = json_data.getString("dateOfMaturity");
                            tempCause.latitude = json_data.getString("latitude");
                            tempCause.longitude = json_data.getString("longitude");
                            tempCause.type = json_data.getString("type");

                            tempCause.noOfEndorsements = json_data.getString("noOfEndorsements");
                            tempCause.noOfLendingDetails = json_data.getString("noOfLendingDetails");

                            allCausesByUser[i] = tempCause;

//                            Log.i("CAUSE AT::::: ", ">"+i+"<   " + "CAUSE ID: "+json_data.getString("causeId")+"NeedyID: "+json_data.getString("needyId")
//                            +"MONEY ASKED FOR: "+ json_data.getString("moneyAskedFor"));


                        }

                        //Get an output to the screen

                    } catch (JSONException e) {
                        Log.e("log_tag", "Error parsing data " + e.toString());
                    }


                    Log.i("USER Details ", ">" + endString + "<");


                }


            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


//        for(int i= 0; i<allCausesByUser.length ; i++)
//        {
//            Log.i(i+" "+"CAUSESSSS DETAILLLSSSS: "+ "Money asked for : "+allCausesByUser[i].moneyAskedFor,".........");
//        }

        return allCausesByUser;

    }







}

