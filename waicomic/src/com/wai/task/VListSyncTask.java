package com.wai.task;

import net.tsz.afinal.core.AsyncTask;

public class VListSyncTask extends AsyncTask<Object, Object, Object> {
	protected Object doInBackground(Object... arg0) {
		try {
			Thread.sleep(10000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}