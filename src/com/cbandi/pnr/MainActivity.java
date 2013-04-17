package com.cbandi.pnr;

import java.util.ArrayList;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

public class MainActivity extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
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
