package com.cbandi.pnr;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HistoryListFragment extends ListFragment {

	ArrayList<String> pnrs;
	ArrayAdapter<String> adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.list, container, false);
	}

	@Override
	public void onViewCreated(View v, Bundle savedInstanceState) {
		Context context = getActivity();
		
		HistoryManager historyManager = HistoryManager.getInstance();
		this.pnrs = historyManager.load(context);
		
		adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, pnrs);
		setListAdapter(adapter);
		
		super.onViewCreated(v, savedInstanceState);
		
		Log.d(C.DEBUG_TAG, "HistoryListFragment::onViewCreated");
	}
	
	@Override
	public void onResume() {		
		super.onResume();
		
		Context context = getActivity();
		
		HistoryManager historyManager = HistoryManager.getInstance();
		this.pnrs = historyManager.load(context);
		this.adapter.notifyDataSetChanged();

		Log.d(C.DEBUG_TAG, "HistoryListFragment::onResume");
		Log.d(C.DEBUG_TAG, "HistoryListFragment " + this.pnrs.toString());
	}
	
	@Override
	public void onPause() {
		super.onPause();
		
//		setListShown(false);
		
		Log.d(C.DEBUG_TAG, "HistoryListFragment::onPause");
	}
	
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		String pnr = getListAdapter().getItem(position).toString();
		
		Intent intent = new Intent(getActivity(), PnrResultActivity.class);
		intent.putExtra(C.INTENT_KEY_PNR, pnr);
		
		startActivity(intent);
	}
}
	