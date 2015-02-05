package com.example.please.myapplication;

import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class llogin extends Activity {
	
	private static final int TIMEOUT_MILLISEC = 0;




	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jsonuse);
	}

	
	
	
//	public void clickbuttonRecieve(View v) {
//	    try {
//	        JSONObject json = new JSONObject();
//	        json.put("UserName", "test2");
//	        json.put("FullName", "1234567");
//	        HttpParams httpParams = new BasicHttpParams();
//	        HttpConnectionParams.setConnectionTimeout(httpParams,
//	                TIMEOUT_MILLISEC);
//	        HttpConnectionParams.setSoTimeout(httpParams, TIMEOUT_MILLISEC);
//	        HttpClient client = new DefaultHttpClient(httpParams);
//	        //
//	        //String url = "http://10.0.2.2:8080/sample1/webservice2.php?" + 
//	        //             "json={\"UserName\":1,\"FullName\":2}";
//	        String url = "http://10.0.2.2:8080/sample1/webservice2.php";
//
//	        HttpPost request = new HttpPost(url);
//	        request.setEntity(new ByteArrayEntity(json.toString().getBytes(
//	                "UTF8")));
//	        request.setHeader("json", json.toString());
//	        HttpResponse response = client.execute(request);
//	        HttpEntity entity = response.getEntity();
//	        // If the response does not enclose an entity, there is no need
//	        if (entity != null) {
//	            InputStream instream = entity.getContent();
//
//	            String result = RestClient.convertStreamToString(instream);
//	            Log.i("Read from server", result);
//	            Toast.makeText(this,  result,
//	                    Toast.LENGTH_LONG).show();
//	        }
//	    } catch (Throwable t) {
//	        Toast.makeText(this, "Request failed: " + t.toString(),
//	                Toast.LENGTH_LONG).show();
//	    }
//	}
	
	

}
