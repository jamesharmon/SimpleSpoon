package com.nfjs.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class WelcomeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Intent input = this.getIntent();
        String name = input.getExtras().getString("name");
        TextView output = (TextView) findViewById(R.id.welcome_text);
        output.setText("Hello, " + name + "!");
    }
 
}
