package com.cbandi.pnr;

import java.math.BigInteger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InputPnrFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.pnr_input, container, false);
	}
	
	@Override
	public void onViewCreated(final View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		EditText editText = (EditText)view.findViewById(R.id.pnr_number);
		
		editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {	
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if(actionId == EditorInfo.IME_ACTION_GO) {
					getPNR(view);
				}
				
				return false;
			}
		});
		
		Button button = (Button)view.findViewById(R.id.get_status);
		button.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				getPNR(view);
			}	
		});
	}
	
    public void getPNR(View view) {    	
    	EditText pnrText = (EditText)view.findViewById(R.id.pnr_number);
    	String pnrTmp = pnrText.getText().toString().trim();

    	try {
    		BigInteger pnr = new BigInteger(pnrTmp);
    		
        	if(pnrTmp.length() != 10) {
        		Util.showAlert(getActivity(), getString(R.string.pnr_validation_length), false);
    		} else {
    			Intent intent = new Intent(getActivity(), PnrResultActivity.class);
    			intent.putExtra(C.INTENT_KEY_PNR, pnr.toString());
        	
    			startActivity(intent);
    		}
    	} catch (Exception e) {
    		Log.d(C.DEBUG_TAG, e.getMessage());
    		Util.showAlert(getActivity(), getString(R.string.pnr_validation_non_numeric), false);
    	}
    }
}
