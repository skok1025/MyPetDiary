package com.skp.opx.rpn.dog;

import com.skp.opx.rpn.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Sinsadong_three extends Activity {
	
Button btn;
	
	   @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.sinsadong_three);

	        btn=(Button)findViewById(R.id.button1);
	        
	        btn.setOnClickListener(new View.OnClickListener(){
	        	public void onClick(View v){
	        		Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:02-576-5768"));

	    	        startActivity(intent);
	        	}
	        });
	    }

}