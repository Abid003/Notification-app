package com.example.notificationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int notificationId =1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.setBtn).setOnClickListener(this);
        findViewById(R.id.cancelBtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {


        TimePicker timePicker = findViewById(R.id.timePicker);


        TextView textView1 = findViewById(R.id.time1Tv);
        TextView textView2 = findViewById(R.id.time2Tv);


        // Intent
        Intent intent = new Intent(MainActivity.this, AlarmReciver.class);
        intent.putExtra("notificationId", notificationId);


        // PendingIntent
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                MainActivity.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT
        );

        // AlarmManager
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);


        switch (v.getId()) {
            case R.id.setBtn:
                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();

                String pikedHr = Integer.toString(hour);
                String pikedmin = Integer.toString(minute);

                String pickedTime = pikedHr+" : "+pikedmin;



                // Create time.

           /*   Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY, hour);
                startTime.set(Calendar.MINUTE, minute);
                startTime.set(Calendar.SECOND, 0);
                long alarmStartTime = startTime.getTimeInMillis(); */



                if(textView1.getText().equals("")){
                    textView1.setText(pickedTime);

                    // Create time.

                    Calendar startTime = Calendar.getInstance();
                    startTime.set(Calendar.HOUR_OF_DAY, hour);
                    startTime.set(Calendar.MINUTE, minute);
                    startTime.set(Calendar.SECOND, 0);
                    long alarmStartTime = startTime.getTimeInMillis();

                    // Set Alarm
                    alarmManager.set(AlarmManager.RTC_WAKEUP, alarmStartTime, pendingIntent);
                    Toast.makeText(this, "alarm 1 set!", Toast.LENGTH_SHORT).show();

                }
                else if(textView2.getText().equals("")){
                    textView2.setText(pickedTime);

                    // Create time.

                    Calendar startTime = Calendar.getInstance();
                    startTime.set(Calendar.HOUR_OF_DAY, hour);
                    startTime.set(Calendar.MINUTE, minute);
                    startTime.set(Calendar.SECOND, 0);
                    long alarmStartTime = startTime.getTimeInMillis();

                    // Set Alarm
                    alarmManager.set(AlarmManager.RTC_WAKEUP, alarmStartTime, pendingIntent);
                    Toast.makeText(this, "alarm 2 set!", Toast.LENGTH_SHORT).show();
                }



                // Set Alarm
               // alarmManager.set(AlarmManager.RTC_WAKEUP, alarmStartTime, pendingIntent);
               // Toast.makeText(this, "Done!", Toast.LENGTH_SHORT).show();
                break;

            case R.id.cancelBtn:
                alarmManager.cancel(pendingIntent);
                Toast.makeText(this, "Canceled.", Toast.LENGTH_SHORT).show();
                break;
        }


    }


}