package myapp.is.com.todolist;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class SetReminder extends AppCompatActivity {

    TextView timeChoser, dateChoser;
    FloatingActionButton setReminderBtn;
    TimePickerDialog timePickerDialog;
    DatePickerDialog datePickerDialog;
    Calendar calendar;
    int CurrentHour;
    int CurrentMinute;
    int CurrentDay;
    int CurrentMonth;
    int CurrentYear;
    String amPm;
    String putReminderValue;
    String date, time, returnDT, returnReminderValue;
    RecyclerView ReminderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_reminder);

        timeChoser = findViewById(R.id.timeChoser);
        dateChoser = findViewById(R.id.dateChoser);
        setReminderBtn = findViewById(R.id.setReminderBtn);

        timeChoser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //popup window to select  time.
                calendar = Calendar.getInstance();
                CurrentHour = calendar.get(Calendar.HOUR_OF_DAY);
                CurrentMinute = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(SetReminder.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if (hourOfDay >= 12) {
                            amPm = "Pm";
                        } else {
                            amPm = "Am";
                        }
                        //timeChoser.setText(hourOfDay +":"+minute+" "+amPm); //Without Format
                        time = String.format("%02d:%02d", hourOfDay, minute) + amPm;
                        timeChoser.setText(time); //With time Format
                    }
                }, CurrentHour, CurrentMinute, false);
                timePickerDialog.show();
            }
        });

        dateChoser.setOnClickListener(new View.OnClickListener() {
            //popup window to select  date.
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                CurrentYear = calendar.get(Calendar.YEAR);
                CurrentMonth = calendar.get(Calendar.MONTH);
                CurrentDay = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(SetReminder.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        date = (String.format("%02d-%02d-%02d", dayOfMonth, month, year));
                        dateChoser.setText(date);
                    }
                }, CurrentYear, CurrentMonth, CurrentDay);
                datePickerDialog.show();
            }
        });

        setReminderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
