package com.cbandi.pnr;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

public class SmsHandler {
	public static final String TRAVEL_DATA_PNR_KEY = "PNR";
	
	private static final String SMS_INBOX_URI = "content://sms/inbox";
	private static final String PNR_SEARCH_STRING = "PNR:";
	//private static final String IRCTC_NUMBER = "'TD-IRCTC-'";
	
	
	public static ArrayList<String> getIrctcSms(Context context) {
		Uri smsInboxUri = Uri.parse(SMS_INBOX_URI);
		//String selection = "address = " + IRCTC_NUMBER;
		String selection = null;
		Cursor c = context.getContentResolver().query(smsInboxUri, null, selection, null, null);
		
		ArrayList<String> irctcMessages = new ArrayList<String>();
		
		if(c.moveToFirst()) {
			do {
				String message = c.getString(c.getColumnIndexOrThrow("body")).toString();
				if(message.startsWith(PNR_SEARCH_STRING)) {
					irctcMessages.add(message);
					Log.d(C.DEBUG_TAG, "message body is [" + message + "]");
				}
			} while(c.moveToNext());
		}

		c.close();
		
		return irctcMessages;
	}
	
	public static HashMap<String,String> parseIrctcSms(String message) {
		String[] messageTokens = message.split(",");
		
		HashMap<String,String> travelData = new HashMap<String,String>(messageTokens.length);
				
		for(int i = 0; i < messageTokens.length; i++) {
			Log.d(C.DEBUG_TAG, messageTokens[i]);
			
			String[] tmp = messageTokens[0].split(":");
			String key = tmp[0].trim();
			String value = tmp[1].trim();
			
			travelData.put(key, value);
		}
		
		return travelData;
	}
}
