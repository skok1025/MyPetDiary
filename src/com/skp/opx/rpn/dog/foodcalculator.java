package com.skp.opx.rpn.dog;


import com.skp.opx.rpn.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class foodcalculator extends Activity {
	

	
	 EditText editTv1, editTv2;
	   TextView textResult;
	   Double result;
	   String num1, num2;
	   Button button1;
	   
	
	   @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.foodcalculator);
	        
	        editTv1=(EditText)findViewById(R.id.edit1);
	        editTv2=(EditText)findViewById(R.id.edit2);
	        textResult = (TextView)findViewById(R.id.TextResult);
	        button1=(Button)findViewById(R.id.button1);

	        
	        
	        button1.setOnTouchListener(new View.OnTouchListener() {
	        	 @Override
	             public boolean onTouch(View arg0, MotionEvent arg1) {
	                num1 = editTv1.getText().toString();
	                num2 = editTv2.getText().toString();
	       
	               
	                result = (30*Integer.parseInt(num1)+70)*2*Double.parseDouble(num2);
	                textResult.setText("»ç·á·®: " + result.toString()+"g");
	                return false;
	          
	             }
	       

	          });

	          
	          }
	       

	       }
	    
