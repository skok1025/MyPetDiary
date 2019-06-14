package com.skp.opx.rpn.month;



import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.skp.opx.rpn.R;


/**
 * �׸���並 �̿��� ���� Ķ������ ����� ����� ���� �� �� �ֽ��ϴ�.
 *
 * @author Mike
 */
public class CalendarMain extends Activity {
    TextView textMain, textDate1, textDate11, textDate2, textDate3, textDate31, textDate4, textDate41, textDate5, textDate51, textDate6;
    int tYear, tMonth, tDay;

    /**
     * ���� Ķ���� �� ��ü
     */
    CalendarMonthView monthView;

    /**
     * ���� Ķ���� �����
     */
    CalendarMonthAdapter monthViewAdapter;

    /**
     * ���� ǥ���ϴ� �ؽ�Ʈ��
     */
    TextView monthText;

    /**
     * ���� ����
     */
    int curYear;

    /**
     * ���� ��
     */
    int curMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);
        textDate1 = (TextView) findViewById(R.id.textDate1);
        textDate11 = (TextView) findViewById(R.id.textDate11);
        textDate2 = (TextView) findViewById(R.id.textDate2);
        textDate3 = (TextView) findViewById(R.id.textDate3);
        textDate31 = (TextView) findViewById(R.id.textDate31);
       
        /*
        textDate4 = (TextView) findViewById(R.id.textDate4);
        textDate41 = (TextView) findViewById(R.id.textDate41);
        textDate5 = (TextView) findViewById(R.id.textDate5);
        textDate51 = (TextView) findViewById(R.id.textDate51);

*/


        Intent intent = getIntent();
        String a = intent.getStringExtra("result");
        int aa = Integer.parseInt(a);

        Calendar t = new GregorianCalendar(Locale.KOREA);
        tYear = t.get(Calendar.YEAR);
        tMonth = t.get(Calendar.MONTH);
        tDay = t.get(Calendar.DATE);
        t.set(tYear,tMonth,tDay);


        SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd");
        String str11 = df.format(t.getTime());


        Calendar cal0 = new GregorianCalendar();
        cal0.set(tYear, tMonth, tDay);
        cal0.add(Calendar.DATE, 42-aa); //ù��°
        String str0 = df.format(cal0.getTime());


        Calendar cal1 = new GregorianCalendar();
        cal1.set(tYear, tMonth, tDay);
        cal1.add(Calendar.DATE, 56-aa); //�ι�°
        String str1 = df.format(cal1.getTime());
        textDate1.setText(str0);
        textDate11.setText(str1);


        Calendar cal2 = new GregorianCalendar();
        cal2.set(tYear, tMonth, tDay);
        cal2.add(Calendar.DATE, 70-aa); //����°
        String str2 = df.format(cal2.getTime());
        textDate2.setText(str2);

        Calendar cal3 = new GregorianCalendar();
        cal3.set(tYear, tMonth, tDay);
        cal3.add(Calendar.DATE, 84-aa); //�׹�°
        String str3 = df.format(cal3.getTime());

        Calendar cal31 = new GregorianCalendar();
        cal31.set(tYear, tMonth, tDay);
        cal31.add(Calendar.DATE, 98-aa); //�ټ���°
        String str31 = df.format(cal31.getTime());
        textDate3.setText(str3);
        textDate31.setText(str31);
        
        
        

        




        // ���� Ķ���� �� ��ü ����
        monthView = (CalendarMonthView) findViewById(R.id.monthView);
        monthViewAdapter = new CalendarMonthAdapter(this);
        monthView.setAdapter(monthViewAdapter);

        // ������ ����
        monthView.setOnDataSelectionListener(new OnDataSelectionListener() {
            public void onDataSelected(AdapterView parent, View v, int position, long id) {
                // ���� ������ ���� ���� ǥ��
                MonthItem curItem = (MonthItem) monthViewAdapter.getItem(position);
                int day = curItem.getDay();


            }
        });

        monthText = (TextView) findViewById(R.id.monthText);
        setMonthText();

        // ���� ���� �Ѿ�� �̺�Ʈ ó��
        Button monthPrevious = (Button) findViewById(R.id.monthPrevious);
        monthPrevious.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                monthViewAdapter.setPreviousMonth();
                monthViewAdapter.notifyDataSetChanged();

                setMonthText();
            }
        });

        // ���� ���� �Ѿ�� �̺�Ʈ ó��
        Button monthNext = (Button) findViewById(R.id.monthNext);
        monthNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                monthViewAdapter.setNextMonth();
                monthViewAdapter.notifyDataSetChanged();

                setMonthText();
            }
        });

    }

    /**
     * �� ǥ�� �ؽ�Ʈ ����
     */
    private void setMonthText() {
        curYear = monthViewAdapter.getCurYear();
        curMonth = monthViewAdapter.getCurMonth();

        monthText.setText(curYear + "�� " + (curMonth+1) + "��");
    }


   
}
