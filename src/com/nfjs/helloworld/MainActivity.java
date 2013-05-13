package com.nfjs.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button b = (Button) findViewById(R.id.submit_button);
        b.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                sayHello(v);
            }
        });
    }
    
    public void sayHello(View view) {
        EditText et = (EditText) findViewById(R.id.name);
        String name = et.getText().toString();
        Intent hello = new Intent(this, WelcomeActivity.class);
        hello.putExtra("name", name);
        startActivity(hello);
    }

}
