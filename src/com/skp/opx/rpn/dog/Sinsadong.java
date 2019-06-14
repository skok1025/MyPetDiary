package com.skp.opx.rpn.dog;

import com.skp.opx.rpn.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Sinsadong extends Activity{

	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.sinsadong);
}
	 public void btnClick1(View v){
	     Intent myIntent = new Intent(getApplicationContext(),
	     Sinsadong_one.class);
	     startActivity(myIntent);
	    }
	 public void btnClick2(View v){
	     Intent myIntent = new Intent(getApplicationContext(),
	     Sinsadong_two.class);
	     startActivity(myIntent);
	    }
	 public void btnClick3(View v){
	     Intent myIntent = new Intent(getApplicationContext(),
	     Sinsadong_three.class);
	     startActivity(myIntent);
	    }
	 public void btnClick4(View v){
	     Intent myIntent = new Intent(getApplicationContext(),
	     Sinsadong_four.class);
	     startActivity(myIntent);
	    }
}
