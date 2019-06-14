package com.skp.opx.rpn.dog;



import com.skp.opx.rpn.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;



public class Splash_Activity extends Activity{
   
   @Override

   protected void onCreate(Bundle savedInstanceState) {

       super.onCreate(savedInstanceState);

       setContentView(R.layout.splash_activity);

       Handler hd = new Handler();
       hd.postDelayed(new Runnable(){
    	   
    	   @Override
    	   public void run(){
    		   finish();
    		   
    	   }
       }
       
    		   
    		   ,3000);
       

       
   }

   

 
 
   }

