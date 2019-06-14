package com.skp.opx.rpn.dog;

import com.skp.opx.rpn.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class agecalculator extends Activity {


    EditText edit;
	TextView textResult;
	Integer result;
	String num1;
	Button btnmonth, btnyear;
	Integer[] numIds = {R.id.btnYear};
  
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.agecalculator);

       
       edit=(EditText)findViewById(R.id.Edit);
       textResult = (TextView)findViewById(R.id.TextResult);
       btnyear=(Button)findViewById(R.id.btnYear);
      btnmonth=(Button)findViewById(R.id.btnMonth);
      
      btnyear.setOnTouchListener(new View.OnTouchListener() {

          @Override
          public boolean onTouch(View arg0, MotionEvent arg1) {
             num1 = edit.getText().toString();
            

             result = Integer.parseInt(num1)*4 + 16;
             textResult.setText("사람나이: " + result.toString()+"살");
             return false;
      
          }
       });

      btnmonth.setOnTouchListener(new View.OnTouchListener() {

          @Override
          public boolean onTouch(View arg0, MotionEvent arg1) {
             num1 = edit.getText().toString();
            

             result = Integer.parseInt(num1) + 1;
             textResult.setText("사람나이: " + result.toString()+"살");
             return false;
      
          }
       });



    }
 }

