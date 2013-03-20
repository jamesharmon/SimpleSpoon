package com.nfjs.helloworld;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class WelcomeFragment extends Fragment {
	private ListView list;
	private GetAllNamesTask task;

	RelativeLayout layoutView;

	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);

		layoutView = (RelativeLayout) inflater.inflate(
				R.layout.welcome_fragment, container, false);

		Intent input = getActivity().getIntent();
		if (input.getExtras() != null) {
			String name = input.getExtras().getString("name");
			TextView output = (TextView) layoutView
					.findViewById(R.id.welcome_text);
			output.setText("Hello, " + name + "!");
		}

		list = (ListView) layoutView.findViewById(R.id.name_list);

		task = new GetAllNamesTask(this);
		task.execute();

		return layoutView;
	}

	public void showList(List<String> names) {
		// Build the adapter with whatever the current events are
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
				getActivity(), android.R.layout.simple_list_item_1,
				names);
		list.setAdapter(arrayAdapter);
	}

	public void removeTask() {
		if (task != null) {
			task.cancel(true);
		}
		task = null;
	}

	public void updateName(String name) {
		TextView output = (TextView) layoutView
				.findViewById(R.id.welcome_text);
		output.setText("Hello, " + name + "!");
		
		list = (ListView) layoutView.findViewById(R.id.name_list);

		task = new GetAllNamesTask(this);
		task.execute();
	}

}
