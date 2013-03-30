package com.cbandi.pnr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class PnrResultActivity extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pnr_result);
		
		Intent intent = getIntent();
		String pnr = intent.getStringExtra(C.INTENT_PNR_KEY);
		getPNR(pnr);
	}
	
    public String getPNR(String pnr) {
		Log.d(C.DEBUG_TAG, "PNR number is " + pnr);
		
    	ConnectivityManager mgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    	NetworkInfo networkInfo = mgr.getActiveNetworkInfo();
    	
    	if(networkInfo != null && networkInfo.isConnected()) {
    		new DownloadWebpageText().execute(pnr);
    	} else {
    		Log.d(C.DEBUG_TAG, "There is no network");
    	}
    	
		return null;
    }
    
	private class DownloadWebpageText extends AsyncTask<String, String, JSONObject> {
		private final ProgressDialog progress = new ProgressDialog(PnrResultActivity.this);
		
		@Override
		protected void onPreExecute() {
			progress.setMessage(getString(R.string.loading));
			progress.show();
		}
		
		@Override
		protected JSONObject doInBackground(String... pnr) {
			try {
				String url = C.PNR_URL + pnr[0];
				
				InputStream is = downloadUrl(url);
				return new JSONObject(getJsonString(is));
				
			} catch(Exception e) {
				e.printStackTrace();
				return null;
			}
		}
			
		@Override
		protected void onPostExecute(JSONObject result) {
			TextView resultView = (TextView)findViewById(R.id.pnr_result_view);			
			
			String message = null;
			
			try {
				int responseCode = result.getJSONObject(C.KEY_STATUS).getInt(C.KEY_STATUS_CODE);
				if(responseCode == 0) {				
					StringBuilder sb = new StringBuilder();
					
					sb.append(C.LABEL_TRAIN_NUMBER + ":" + result.getString(C.KEY_TRAIN_NUMBER) + "\n");
					sb.append(C.LABEL_TRAIN_NAME + ":" + result.getString(C.KEY_TRAIN_NAME) + "\n");
					sb.append(C.LABEL_FROM + ":" + result.getString(C.KEY_FROM) + "\n");
					sb.append(C.LABEL_TO + ":" + result.getString(C.KEY_TO) + "\n");
					sb.append(C.LABEL_BOARDING_POINT + ":" + result.getString(C.KEY_BOARDING_POINT) + "\n");
					sb.append(C.LABEL_RESERVED_UPTO + ":" + result.getString(C.KEY_RESERVED_UPTO) + "\n");
					sb.append(C.LABEL_BOARDING_DATE + ":" + result.getString(C.KEY_BOARDING_DATE) + "\n");
					sb.append(C.LABEL_CLASS + ":" + result.getString(C.KEY_CLASS) + "\n");
					sb.append(C.LABEL_CHARTING_STATUS + ":" + result.getString(C.KEY_CHARTING_STATUS) + "\n\n");
					
					sb.append("Passenger PNR status...\n\n");
					
					JSONArray passengerList = result.getJSONArray(C.KEY_PASSENGER);
					int size = passengerList.length();
					
					for(int i = 0; i < size; i++) {
						String name = passengerList.getJSONObject(i).getString(C.KEY_PASSENGER_NAME);
						String cstatus = passengerList.getJSONObject(i).getString(C.KEY_CURRENT_STATUS);
						String bstatus = passengerList.getJSONObject(i).getString(C.KEY_BOOKING_STATUS);
						
						sb.append(C.LABEL_PASSENGER_NAME + " : " + name + "\n");
						sb.append(C.LABEL_CURRENT_STATUS + " : " + cstatus + "\n");
						sb.append(C.LABEL_BOOKING_STATUS + " : " + bstatus + "\n\n");
					}
					
					message = sb.toString();
				} else {
					message = result.getJSONObject(C.KEY_STATUS).getString(C.KEY_STATUS_MESSAGE);
				}
			} catch (Exception e) {
				e.printStackTrace();
				message = C.INTERNAL_ERROR_GENERIC_MESSAGE;
			}
														
			resultView.setText(message);
			
			progress.dismiss();
		}
		
		private InputStream downloadUrl(String Url) throws IOException {
			URL url = new URL(Url);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setReadTimeout(C.PNR_QUERY_READ_TIMEOUT);
			conn.setConnectTimeout(C.PNR_QUERY_CONN_TIMEOUT);
				
			conn.connect();
				
			int response = conn.getResponseCode();
				
			if(response != HttpURLConnection.HTTP_OK) {
				Log.d(C.DEBUG_TAG, "The response is " + response);
				Log.d(C.DEBUG_TAG, "The url is " + Url);

				return null;
			}
				
			return conn.getInputStream();
		}
		
		private String getJsonString(InputStream is) throws IOException {
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			StringBuilder sb = new StringBuilder();
			
			String line;
			while((line = reader.readLine()) != null) {
				sb.append(line);
			}
			
			return sb.toString();
		}		
	}
}
