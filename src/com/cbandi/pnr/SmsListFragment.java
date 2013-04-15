package com.cbandi.pnr;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SmsListFragment extends ListFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		ArrayList<String> messages = getArguments().getStringArrayList(C.INTENT_KEY_SMS_LIST);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, messages);
		setListAdapter(adapter);

		return inflater.inflate(R.layout.list, container, false);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		String message = getListAdapter().getItem(position).toString();
		HashMap<String,String> travelData = SmsHandler.parseIrctcSms(message);
		String pnr = travelData.get(SmsHandler.TRAVEL_DATA_PNR_KEY);

		Intent intent = new Intent(getActivity(), PnrResultActivity.class);
		intent.putExtra(C.INTENT_KEY_PNR, pnr);
		
		startActivity(intent);
	}
}
