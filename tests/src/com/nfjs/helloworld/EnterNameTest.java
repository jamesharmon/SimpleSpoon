package com.nfjs.helloworld;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.squareup.spoon.Spoon;

public class EnterNameTest extends
		ActivityInstrumentationTestCase2<MainActivity> {

	private static final String TAG = EnterNameTest.class
			.getSimpleName();

	public EnterNameTest() {
		super(MainActivity.class);
	}

	private Instrumentation instrumentation;
	private Activity activity;
	private Button manual;

	// private Solo solo;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		instrumentation = getInstrumentation();
		activity = getActivity();
		// solo = new Solo(getInstrumentation(), activity);
	}

	public void testEditVehicle() {

		Spoon.screenshot(activity, "initial_state");
		Log.v("Spoon Test", "Log message");

		final EditText vin = (EditText) activity
				.findViewById(R.id.name);
		final Button submitButton = (Button) activity
				.findViewById(R.id.name_button);

		// Click the "manual entry" button.
		instrumentation.runOnMainSync(new Runnable() {
			@Override
			public void run() {
				vin.setText("Joe");
			}
		});

		instrumentation.waitForIdleSync();
		Spoon.screenshot(activity, "name_entered");
	}
}
