package com.cbandi.pnr;

import java.math.BigInteger;
import java.util.ArrayList;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
    }
    
    public void getPNR(View view) {
    	EditText pnrText = (EditText) findViewById(R.id.pnr_number);
    	String pnrTmp = pnrText.getText().toString().trim();
    	
    	try {
    		BigInteger pnr = new BigInteger(pnrTmp);
    		
        	if(pnrTmp.length() != 10) {
        		Log.d(C.DEBUG_TAG, "invalid pnr number");
        		Util.showAlert(this, getString(R.string.pnr_validation_length), false);
    		} else {
    			Intent intent = new Intent(this, PnrResultActivity.class);
    			intent.putExtra(C.INTENT_KEY_PNR, pnr.toString());
        	
    			startActivity(intent);
    		}
    	} catch (Exception e) {
    		Log.d(C.DEBUG_TAG, e.getMessage());
    		Util.showAlert(this, getString(R.string.pnr_validation_non_numeric), false);
    	}
    }
    
    public void scanSms(View view) {
		ArrayList<String> messages = SmsHandler.getIrctcSms(this.getApplicationContext());
		
		if(messages.size() > 0) {
			Intent intent = new Intent(this, SmsListActivity.class);
			intent.putExtra(C.INTENT_KEY_SMS_LIST, messages);
			startActivity(intent);			
		} else {
			Util.showAlert(this, getString(R.string.no_m_tickets), false);
		}
    }
    
    public void lanchIrctc(View view) {
    	Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(C.IRCTC_MOBILE_WEB_URL));
    	startActivity(intent);
    }
}
