package com.skp.opx.rpn.dog;

import com.skp.opx.rpn.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class educationvideo extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.educationvideo);
        
        //버튼 선언
        Button bt1 = (Button) findViewById (R.id.button1);
        
        //버튼 이벤트처리
        bt1.setOnClickListener(this);
        
       
        Button bt2 = (Button) findViewById (R.id.button2);
        
        bt2.setOnClickListener(this);
        
        
        Button bt3 = (Button) findViewById (R.id.button3);
        
        bt3.setOnClickListener(this);
        
        
        Button bt4 = (Button) findViewById (R.id.button4);
        
        bt4.setOnClickListener(this);
        
        
        Button bt5 = (Button) findViewById (R.id.button5);
        
        bt5.setOnClickListener(this);
        
        
        bt1.setOnClickListener(new View.OnClickListener(){
        	@Override
        	   public void onClick(View arg0) {
                // TODO Auto-generated method stub

                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=R-0H8OgJpEA"));

                startActivity(i);
        	}
            });
        		
        		
        		bt2.setOnClickListener(new View.OnClickListener(){
        				@Override
        				
        				   public void onClick(View arg0) {
        			        // TODO Auto-generated method stub

        			        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=vRzkMWZ61Vs"));

        			        startActivity(i);


        				}
        		});
        		

        		bt3.setOnClickListener(new View.OnClickListener(){
        				@Override
        				  public void onClick(View arg0) {
        			        // TODO Auto-generated method stub

        			        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=QootzGK0iio"));

        			        startActivity(i);

        				
        				}
        		});
        		
        		
        		bt4.setOnClickListener(new View.OnClickListener(){
        			@Override
        			  public void onClick(View arg0) {
    			        // TODO Auto-generated method stub

    			        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=i_CaHhHnQ2k"));

    			        startActivity(i);

        			
        			
        	
        			}
        		});
        	    
        	    bt5.setOnClickListener(new View.OnClickListener(){
        			@Override
        			 public void onClick(View arg0) {
    			        // TODO Auto-generated method stub

    			        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=eu6jrYuCoTc"));

    			        startActivity(i);

        			
        			}
        	    });
        	    
            }

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
        }
            
    

    
	



