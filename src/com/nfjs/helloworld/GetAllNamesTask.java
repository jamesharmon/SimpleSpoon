package com.nfjs.helloworld;

import java.util.List;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.nfjs.helloworld.db.DbAdapter;

public class GetAllNamesTask extends AsyncTask<Void, Void, List<String>> {

	private static final String TAG = GetAllNamesTask.class.getSimpleName();

	private WelcomeFragment welcomeFragment;

	ProgressDialog pd;

	public GetAllNamesTask(WelcomeFragment welcomeFragment) {
		this.welcomeFragment = welcomeFragment;
	}

	@Override
	protected void onPreExecute() {
		//mainActivity.clearList();
		pd = new ProgressDialog(welcomeFragment.getActivity());
		pd.setMessage("Loading Names ...");
		pd.show();
	}

	@Override
	protected List<String> doInBackground(Void... param) {
		
		try {
			Log.v(TAG, "Thread sleep");
			Thread.sleep(2000);
			Log.v(TAG, "Thread awake");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Log.v(TAG, "Running doInBackground");
        DbAdapter dba = new DbAdapter(welcomeFragment.getActivity());
        dba.open();

		List<String> names = dba.getAllNames();
		Log.v(TAG, "Completed doInBackground");
		return names;

	}

	@Override
	protected void onPostExecute(List<String> names) {
		Log.v(TAG, "Running onPostExecute");
		welcomeFragment.showList(names);
		welcomeFragment.removeTask();
		pd.hide();
		pd.dismiss();  // fixes problem with memory leak when exiting MainActivity
	}

}
