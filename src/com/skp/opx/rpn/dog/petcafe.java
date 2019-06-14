package com.skp.opx.rpn.dog;

import com.skp.opx.rpn.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class petcafe extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.petcafe);
    }

   
    public void btnClick1(View v){
	     Intent myIntent = new Intent(getApplicationContext(),
	     Sinsadong.class);
	     startActivity(myIntent);
	    }

    	           	    
    public void btnClick2(View v){
         Intent myIntent = new Intent(getApplicationContext(),
         Teheranro.class);
         startActivity(myIntent);
         }
        	    	 
    public void btnClick3(View v){
       Intent myIntent = new Intent(getApplicationContext(),
       Seocho.class);
       startActivity(myIntent);
       }
    
    public void btnClick4(View v){
       Intent myIntent = new Intent(getApplicationContext(),
       Jamsil.class);
        startActivity(myIntent);
         }
    
    public void btnClick5(View v){
         Intent myIntent = new Intent(getApplicationContext(),
         Kondae.class);
         startActivity(myIntent);
        }
        	    	
    public void btnClick6(View v){
	     Intent myIntent = new Intent(getApplicationContext(),
	     Itaewon.class);
	     startActivity(myIntent);
	    }
	
	 public void btnClick7(View v){
	     Intent myIntent = new Intent(getApplicationContext(),
	     Hongdae.class);
	     startActivity(myIntent);
	    }
	 public void btnClick8(View v){
	     Intent myIntent = new Intent(getApplicationContext(),
	     Bukchon.class);
	     startActivity(myIntent);
	    }

    }



