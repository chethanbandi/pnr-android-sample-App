package com.cbandi.pnr;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends FragmentActivity {
	
	private static final String IRCTC_MOBILE_WEB_URL = "https://irctc.co.in/mobile";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.activity_main, menu);
//        return true;
//    }
    
    public String getPNR(View view) {
    	EditText pnrText = (EditText) findViewById(R.id.pnr_number);
    	String pnr = pnrText.getText().toString().trim();

    	if(pnr.length() != 10) {
    		Log.d(C.DEBUG_TAG, "invalid pnr number");
			showAlert();
			
			return null;
		}
    	
    	Intent intent = new Intent(this, PnrResultActivity.class);
    	intent.putExtra(C.INTENT_PNR_KEY, pnr);
    	
    	startActivity(intent);
    	return null;
    }
    
    public String scanSms(View view) {
        Intent intent = new Intent(this, SmsListActivity.class);
        startActivity(intent);
    	return null;
    }
    
    public String lanchIrctc(View view) {
    	Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(IRCTC_MOBILE_WEB_URL));
    	startActivity(intent);
    	return null;
    }
    
    private void showAlert() {
		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
		dialogBuilder.setMessage(getString(R.string.pnr_length));
		dialogBuilder.setCancelable(true);
		dialogBuilder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();					
			}
		});
		
		AlertDialog alert = dialogBuilder.create();
		alert.show();
    }
}
