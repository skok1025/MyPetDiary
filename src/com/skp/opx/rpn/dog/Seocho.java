package com.skp.opx.rpn.dog;

import com.skp.opx.rpn.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class Seocho extends Activity{

	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.seocho);
}
	 public void btnClick1(View v){
	     Intent myIntent = new Intent(getApplicationContext(),
	    Seocho_one.class);
	     startActivity(myIntent);
	    }
	 public void btnClick2(View v){
	     Intent myIntent = new Intent(getApplicationContext(),
	    Seocho_two.class);
	     startActivity(myIntent);
	    }
	 public void btnClick3(View v){
	     Intent myIntent = new Intent(getApplicationContext(),
	     Seocho_three.class);
	     startActivity(myIntent);
	    }
	
}