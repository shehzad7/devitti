package com.example.please.myapplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.util.Log;

public class CustomHttpClient {

 /** The time it takes for our client to timeout */
	public static final int HTTP_TIMEOUT = 30 * 1000; // milliseconds 
 /** Single instance of our HttpClient */
	private static HttpClient mHttpClient; 
 /**
  * Get our single instance of our HttpClient object.
  * 
  * @return an HttpClient object with connection parameters set
  */

 public static HttpClient getHttpClient() {

  if (mHttpClient == null) {
   mHttpClient = new DefaultHttpClient();
   
   final HttpParams params = mHttpClient.getParams();
   HttpConnectionParams.setConnectionTimeout(params, HTTP_TIMEOUT);
   HttpConnectionParams.setSoTimeout(params, HTTP_TIMEOUT);
   ConnManagerParams.setTimeout(params, HTTP_TIMEOUT);
  }

  
  return mHttpClient;
 }
 
 
 
 
 /**
  * Performs an HTTP Post request to the specified url with the specified
  * parameters.
  * 
  * @param url
  *            The web address to post the request to
  * @param postParameters
  *            The parameters to send via the request
  * @return The result of the request
  * @throws Exception
  */

 	public static String executeHttpPost(String url,ArrayList<NameValuePair> postParameters) throws Exception {

 		BufferedReader in = null;

	  try {
	
	   HttpClient client = getHttpClient();
	
	   HttpPost request = new HttpPost(url);
	
	   UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(
	
	     postParameters);
	
	   request.setEntity(formEntity);
	
	   HttpResponse response = client.execute(request);
	
	   in = new BufferedReader(new InputStreamReader(response.getEntity()
	
	     .getContent()));
	
	   
	
	   StringBuffer sb = new StringBuffer("");
	
	   String line = "";
	
	   String NL = System.getProperty("line.separator");
	
	   while ((line = in.readLine()) != null) {
	
	    sb.append(line + NL);
	
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

 }

 
 /**

  * Performs an HTTP GET request to the specified url.

  * 

  * @param url

  *            The web address to post the request to

  * @return The result of the request

  * @throws Exception

  */

 public static String executeHttpGet(String url) throws Exception {

  BufferedReader in = null;

		  try {
		
		   HttpClient client = getHttpClient();
		
		   HttpGet request = new HttpGet();
		
		   request.setURI(new URI(url));
		
		   HttpResponse response = client.execute(request);
		
		   in = new BufferedReader(new InputStreamReader(response.getEntity()
		
		     .getContent()));
		
		 
		   StringBuffer sb = new StringBuffer("");
		
		   String line = "";
		
		   String NL = System.getProperty("line.separator");
		
		   while ((line = in.readLine()) != null) {
		
		    sb.append(line + NL);
		
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

 }
 
 
 
 /**
  * for sending data to php server
  * */
 public void postData() {
	    // Create a new HttpClient and Post Header
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost("http://www.yoursite.com/script.php");

	    try {

//	    	String[] arr = new String[20];
//	    	
//	    	arr[0] = "3";
//	    	arr[1] = "4";
//	    	arr[2] = "Mazher";
//	    	arr[3] = "Abdullah";
//	    	arr[4] = "1";
//	    	arr[5] = "1";
//	    	arr[6] = "1999";
//	    	arr[7] = "1998";
	    	
	    	
	    	
	    	// Add your data
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	        nameValuePairs.add(new BasicNameValuePair("id", "12345"));
	        nameValuePairs.add(new BasicNameValuePair("stringdata", "Hi"));
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

	        // Execute HTTP Post Request
	        HttpResponse response = httpclient.execute(httppost);

	    } catch (ClientProtocolException e) {
	        // TODO Auto-generated catch block
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	    }
	} 
 
 
 
 

}