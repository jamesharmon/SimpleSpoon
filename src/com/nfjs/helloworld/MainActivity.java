package com.nfjs.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class MainActivity extends FragmentActivity implements
		FragmentListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
	}

	@Override
	public void updateName(String name) {

		WelcomeFragment viewer = (WelcomeFragment) getSupportFragmentManager()
				.findFragmentById(R.id.welcome_fragment);

		if (viewer == null || !viewer.isInLayout()) {
			Intent hello = new Intent(this, WelcomeActivity.class);
			hello.putExtra("name", name);
			startActivity(hello);
		} else {
			viewer.updateName(name);
		}
	}

}
