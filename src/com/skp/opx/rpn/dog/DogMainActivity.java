package com.skp.opx.rpn.dog;

import com.skp.opx.rpn.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DogMainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dog_activity_main);
        
        Intent i = new Intent(getApplicationContext(), Splash_Activity.class);
        
        startActivity(i);
    }
    
       
          	
    public void btnClick1(View v){
	     Intent myIntent = new Intent(getApplicationContext(),
	     foodcalculator.class);
	     startActivity(myIntent);
	    }
    
    public void btnClick2(View v){
	     Intent myIntent = new Intent(getApplicationContext(),
	     agecalculator.class);
	     startActivity(myIntent);
	    }
    
    public void btnClick3(View v){
	     Intent myIntent = new Intent(getApplicationContext(),
	     educationvideo.class);
	     startActivity(myIntent);
	    }
    
    public void btnClick4(View v){
	     Intent myIntent = new Intent(getApplicationContext(),
	    		 com.skp.opx.rpn.ui.PathAlertActivity.class);
	     startActivity(myIntent);
	    }
   
    
    public void btnClick5(View v){
	     Intent myIntent = new Intent(getApplicationContext(),
	    		 com.skp.opx.rpn.month.RegisterMain.class);
	     startActivity(myIntent);
	    }
    
    public void btnClick6(View v){
	     Intent myIntent = new Intent(getApplicationContext(),
	     petcafe.class);
	     startActivity(myIntent);
	    }
    
    
    

    }