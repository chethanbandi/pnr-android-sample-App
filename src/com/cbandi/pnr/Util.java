package com.cbandi.pnr;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class Util {
    public static void showAlert(final Activity activity, String message, final boolean finish) {
		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(activity);
		dialogBuilder.setMessage(message);
		dialogBuilder.setCancelable(true);
		dialogBuilder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();					
			}
		});
		
		AlertDialog alert = dialogBuilder.create();
		
		alert.setOnDismissListener(new DialogInterface.OnDismissListener() {
			
			@Override
			public void onDismiss(DialogInterface dialog) {
				if(finish) {
					activity.finish();
				}
			}
		});
		
		alert.show();
    }

}
