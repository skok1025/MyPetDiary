package com.skp.opx.rpn.dog;

import com.skp.opx.rpn.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Teheranro_one extends Activity {
	
Button btn;
	
	   @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.teheranro_one);

	        btn=(Button)findViewById(R.id.button1);
	        
	        btn.setOnClickListener(new View.OnClickListener(){
	        	public void onClick(View v){
	        		Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:02-566-6181"));

	    	        startActivity(intent);
	        	}
	        });
	    }

}