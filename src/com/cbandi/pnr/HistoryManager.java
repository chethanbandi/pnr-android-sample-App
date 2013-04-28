package com.cbandi.pnr;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class HistoryManager {
	public static int STORAGE_MAX_PNRS = 10;
	public static String STORAGE_PREFERENCE_FILE = "com.cbandi.pnr.history";
	public static String STORAGE_PREFERENCE_KEY = "com.cbandi.pnr.history.pnr";
	public static String STORAGE_HISTORY_PNR_SEP = "|";
	public static String STORAGE_HISTORY_PNR_SEP_REGX = "\\|";

	private static HistoryManager historyManager = null;

	private ArrayList<String> pnrs;
	
	private HistoryManager() {
		pnrs = new ArrayList<String>(STORAGE_MAX_PNRS);
	}
	
	public static HistoryManager getInstance() {
		if(historyManager == null) {
			historyManager = new HistoryManager();
		}
		
		return historyManager;
	}
	
	public void add(BigInteger pnr) {
		int size = this.pnrs.size();
		int index = this.pnrs.indexOf(pnr.toString());
		
		if(index == -1) {
			if(size < STORAGE_MAX_PNRS) {
				this.pnrs.add(0, pnr.toString());
			} else {
				this.pnrs.remove(STORAGE_MAX_PNRS-1);
				this.pnrs.add(0, pnr.toString());
			}
		} else {
			this.pnrs.remove(index);
			this.pnrs.add(0, pnr.toString());
		}
	}
	
	public void save(Context context) {
		StringBuilder sb = new StringBuilder();
		
		for(String pnr : this.pnrs) {
			sb.append(pnr);
			sb.append(STORAGE_HISTORY_PNR_SEP);
		}
		
		SharedPreferences sharedPref = context.getSharedPreferences(STORAGE_PREFERENCE_FILE, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();
		
		editor.putString(STORAGE_PREFERENCE_KEY, sb.toString());
		editor.commit();
		
		Log.d(C.DEBUG_TAG, "HistoryManager Stored pnrs " + sb.toString());
	}

	public ArrayList<String> load(Context context) {
		SharedPreferences sharedPref = context.getSharedPreferences(STORAGE_PREFERENCE_FILE, Context.MODE_PRIVATE);
		String pnrBlob = sharedPref.getString(STORAGE_PREFERENCE_KEY, null);
	
		Log.d(C.DEBUG_TAG, "HistoryManager loaded pnrs " + pnrBlob);
		
		if(pnrBlob != null) {
			String[] hPnrs = pnrBlob.split(STORAGE_HISTORY_PNR_SEP_REGX);
			this.pnrs.clear();
			this.pnrs.addAll(Arrays.asList(hPnrs));
		}
		
		return this.pnrs;
	}
}
