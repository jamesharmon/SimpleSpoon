package com.nfjs.helloworld;

import com.squareup.spoon.Spoon;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.test.InstrumentationTestCase;
import android.test.TouchUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ConversationTest extends InstrumentationTestCase {
   
   private static final String TAG = ConversationTest.class.getSimpleName();

   public void testEnterName() {
      


      Instrumentation instrumentation = getInstrumentation();

      // We want to monitor the MainActivity
      Instrumentation.ActivityMonitor monitor = instrumentation.addMonitor(MainActivity.class.getName(), null, false);
      
      // Start the MainActivity
      Intent intent = new Intent(Intent.ACTION_MAIN);
      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      intent.setClassName(instrumentation.getTargetContext(), MainActivity.class.getName());
      instrumentation.startActivitySync(intent);

      // Wait until the MainActivity starts
      Activity currentActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5);
      
      Spoon.screenshot(currentActivity, "enter_name");

      //currentActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
      
      // Enter the name
      final EditText vin = (EditText) currentActivity.findViewById(R.id.name);
      final Button submitButton = (Button) currentActivity.findViewById(R.id.submit_button);
      
      TouchUtils.clickView(this, vin);
      
      instrumentation.sendStringSync("Joe");

      // Now we want to monitor the WelcomeActivity
      instrumentation.removeMonitor(monitor);
      monitor = instrumentation.addMonitor(WelcomeActivity.class.getName(), null, false);
  
      // Click the submit button
      TouchUtils.clickView(this, submitButton);

      // Wait until the WelcomeActivity starts
      currentActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5);
      // need to check that the returned activity is not null, requires waiting for the timeout
      //currentActivity = getInstrumentation().waitForMonitor(monitor);

      Log.v(TAG, "Activity: " + currentActivity.getClass().getSimpleName());
      
      assertNotNull(currentActivity);
      
      Spoon.screenshot(currentActivity, "display_name");
      
      // Verify the welcome message
      final TextView welcomeText = (TextView) currentActivity.findViewById(R.id.welcome_text);
      assertEquals("Hello, Joe!", welcomeText.getText().toString());
      
   }

}
