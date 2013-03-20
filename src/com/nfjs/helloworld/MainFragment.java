package com.nfjs.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.nfjs.helloworld.db.DbAdapter;

public class MainFragment extends Fragment {
	
	LinearLayout layoutView;
	
	FragmentListener fragmentListener;

    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		
		layoutView = (LinearLayout) inflater.inflate(R.layout.main_fragment, container, false);
		
        Button b = (Button) layoutView.findViewById(R.id.name_button);
        b.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                sayHello(v);
            }
        });
        
		return layoutView;
	}
    
    public void sayHello(View view) {
    	
        EditText et = (EditText) layoutView.findViewById(R.id.name);
        String name = et.getText().toString();
        DbAdapter dba = new DbAdapter(getActivity());
        dba.open();
        dba.insertName(name);
        
    		fragmentListener.updateName(name);
    }
    
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			fragmentListener = (FragmentListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString() + " must implement FragmentListener");
		}
	}
}
