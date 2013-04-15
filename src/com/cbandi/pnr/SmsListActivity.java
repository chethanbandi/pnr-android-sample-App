package com.cbandi.pnr;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class SmsListActivity extends FragmentActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.list_container);
		
		ArrayList<String> messages = getIntent().getStringArrayListExtra(C.INTENT_KEY_SMS_LIST);
		
		SmsListFragment fragment = new SmsListFragment();
		
		Bundle args = new Bundle();
		args.putStringArrayList(C.INTENT_KEY_SMS_LIST, messages);
		
		fragment.setArguments(args);
		getSupportFragmentManager().beginTransaction().replace(R.id.sms_activity_container, fragment).commit();
	}
}