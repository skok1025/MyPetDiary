package com.skp.opx.rpn.dog;

import com.skp.opx.rpn.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class Jamsil extends Activity{

	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.jamsil);
}
	 public void btnClick1(View v){
	     Intent myIntent = new Intent(getApplicationContext(),
	    		 Jamsil_one.class);
	     startActivity(myIntent);
	    }
	 public void btnClick2(View v){
	     Intent myIntent = new Intent(getApplicationContext(),
	    		 Jamsil_two.class);
	     startActivity(myIntent);
	    }
	 
}