package com.skp.opx.rpn.month;



        import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.skp.opx.rpn.R;

public class RegisterMain extends Activity {

    Button btnDatePicker;
    TextView textResult1, textResult2, textStan;
    EditText editName;
    Button btn;
    int sYear, sMonth, sDay;
    int tYear, tMonth, tDay;
    long today, standard, re;
    int resultNum=0;
    int absR;
    final int DATE_DIALOG_ID = 0;
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        btnDatePicker = (Button) findViewById(R.id.btnDatePicker);
        textResult1 = (TextView) findViewById(R.id.textResult1);
        textResult2 = (TextView) findViewById(R.id.textResult2);
        textStan = (TextView) findViewById(R.id.textStan);
        editName = (EditText) findViewById(R.id.editName);
        btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.skp.opx.rpn.month.CalendarMain.class);

                resultNum = Math.abs((int)re); //1으로 나옴

                result=String.format("%d",resultNum);


                intent.putExtra("result",result);


                startActivity(intent);
            }
        });

        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });

        Calendar s = Calendar.getInstance();
        sYear = s.get(Calendar.YEAR);
        sMonth = s.get(Calendar.MONTH);
        sDay = s.get(Calendar.DATE);


        Calendar t = new GregorianCalendar(Locale.KOREA);
        tYear = t.get(Calendar.YEAR);
        tMonth = t.get(Calendar.MONTH);
        tDay = t.get(Calendar.DATE);
        t.set(tYear,tMonth,tDay);


        today = t.getTimeInMillis();
        standard = s.getTimeInMillis();
        re = (standard-today)/(24*60*60*1000);

        SimpleDateFormat df1 = new SimpleDateFormat("yyyy.MM.dd");
        String str1 = df1.format(t.getTime());
        textStan.setText(str1);

        resultNum = (int)re+1; //1으로 나옴
        updateDate();

        result=String.format("%d",resultNum);


    }
    private void updateDate(){
        if(resultNum>=0){
            textResult1.setText(String.format("-%d일째",resultNum));
        }
        else{
            absR = Math.abs(resultNum);
            textResult1.setText(String.format("+%d일째",absR));
        }

        Calendar cal = new GregorianCalendar();
        cal.set(sYear,sMonth, sDay);
        cal.add(Calendar.DATE, 280);
        SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd");
        String str = df.format(cal.getTime());
        textResult2.setText(str);






    }

    private DatePickerDialog.OnDateSetListener sDateSetListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker arg, int year, int monthOfYear, int dayOfMonth) {
            sYear = year;
            sMonth = monthOfYear;
            sDay = dayOfMonth;

            final Calendar s = Calendar.getInstance();
            s.set(sYear, sMonth, sDay);

            standard = s.getTimeInMillis();
            re=(standard-today)/(24*60*60*1000);

            resultNum=(int)re;
            updateDate();


        }
    };
    protected Dialog onCreateDialog(int id){
        switch(id){
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, sDateSetListener, sYear, sMonth, sDay);
        }
        return null;
    }
}

