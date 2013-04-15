package com.cbandi.pnr;

import java.util.ArrayList;
import java.util.Arrays;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class HistoryListFragment extends ListFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.list, container, false);
	}

	@Override
	public void onViewCreated(View v, Bundle savedInstanceState) {
		TextView header = new TextView(getActivity());
		header.setText(R.string.pnr_history_list_header);
		getListView().addHeaderView(header);
		
		Context context = getActivity();
		SharedPreferences sharedPref = context.getSharedPreferences(C.STORAGE_PREFERENCE_FILE, Context.MODE_PRIVATE);
		String pnrs = sharedPref.getString(C.STORAGE_PREFERENCE_KEY, null);
		
		ArrayList<String> messages = new ArrayList<String>();
		
		if(pnrs != null) {
			String[] hPnrs = pnrs.split(C.STORAGE_HISTORY_PNR_SEP);
			messages.addAll(Arrays.asList(hPnrs));
		}
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, messages);
		setListAdapter(adapter);
		
		super.onViewCreated(v, savedInstanceState);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		String pnr = getListAdapter().getItem(position).toString();
		
		Intent intent = new Intent(getActivity(), PnrResultActivity.class);
		intent.putExtra(C.INTENT_KEY_PNR, pnr);
		
		startActivity(intent);
	}
	
	@Override
	public void onPause() {
		saveHistory();
		super.onPause();
	}
	
	private void saveHistory() {
		Context context = getActivity();
		SharedPreferences sharedPref = context.getSharedPreferences(C.STORAGE_PREFERENCE_FILE, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();
		String pnrs = "1234567890|0987654321|5432167890|6789054321";
		editor.putString(C.STORAGE_PREFERENCE_KEY, pnrs);
		editor.commit();
	}
}
	