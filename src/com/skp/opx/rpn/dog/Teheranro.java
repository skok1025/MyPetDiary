package com.skp.opx.rpn.dog;

import com.skp.opx.rpn.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Teheranro extends Activity{

	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.teheranro);
}
	 public void btnClick1(View v){
	     Intent myIntent = new Intent(getApplicationContext(),
	     Teheranro_one.class);
	     startActivity(myIntent);
	    }
	 public void btnClick2(View v){
	     Intent myIntent = new Intent(getApplicationContext(),
	    Teheranro_two.class);
	     startActivity(myIntent);
	    }
	 
}