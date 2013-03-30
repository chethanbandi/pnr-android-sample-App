package com.cbandi.pnr;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class SmsListActivity extends FragmentActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_sms_list);
		
		ArrayList<String> messages = SmsHandler.getIrctcSms(this.getApplicationContext());
		
		if(messages.size() == 0) {
			SmsEmptyFragment fragment = new SmsEmptyFragment();
			getSupportFragmentManager().beginTransaction().replace(R.id.sms_activity_container, fragment).commit();
		} else {
			SmsListFragment fragment = new SmsListFragment();
			
			Bundle args = new Bundle();
			args.putStringArrayList(C.INTENT_SMS_LIST_KEY, messages);
			
			fragment.setArguments(args);
			getSupportFragmentManager().beginTransaction().replace(R.id.sms_activity_container, fragment).commit();
		}
	}
}