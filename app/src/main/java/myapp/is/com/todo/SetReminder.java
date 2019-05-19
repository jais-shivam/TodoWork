package myapp.is.com.todo;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.Calendar;
import java.util.Date;


public class SetReminder extends AppCompatActivity {

    private EditText reminder;
    private TextView time, date;
    private FloatingActionButton setReminderBtn;
    TimePickerDialog timePickerDialog;
    Calendar calendar;
    int CurrentHour;
    int CurrentMinute;
    int CurrentDay;
    int CurrentMonth;
    int CurrentYear;
    String amPm, timeWithFormat, dateWithFormat;
    DatePickerDialog datePickerDialog;

    public static final String Extra_Title = "myapp.is.com.todo.Extra_Title";
    public static final String Extra_Time = "myapp.is.com.todo.Extra_Time";
    public static final String Extra_Date = "myapp.is.com.todo.Extra_Date";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_reminder);
        reminder = findViewById(R.id.putReminder);
        time = findViewById(R.id.timeChoser);
        date = findViewById(R.id.dateChoser);
        setReminderBtn = findViewById(R.id.setReminderBtn);

        Date currentTime = Calendar.getInstance().getTime();

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //popup window to select  time.
                calendar = Calendar.getInstance();
                CurrentHour = (int) calendar.get(Calendar.HOUR_OF_DAY);
                CurrentMinute = (int) calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(SetReminder.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if (hourOfDay >= 12) {
                            amPm = "Pm";
                        } else {
                            amPm = "Am";
                        }
                        //timeChoser.setText(hourOfDay +":"+minute+" "+amPm); //Without Format
                        timeWithFormat = String.format("%02d:%02d", hourOfDay, minute) + amPm;
                        time.setText(timeWithFormat); //With time Format
                    }
                }, CurrentHour, CurrentMinute, false);
                timePickerDialog.show();
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
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
                        dateWithFormat = (String.format("%02d-%02d-%02d", dayOfMonth, month, year));
                        date.setText(dateWithFormat);
                    }
                }, CurrentYear, CurrentMonth, CurrentDay);
                datePickerDialog.show();
            }
        });

        setReminderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote(); //for saving data
           /*
                Intent i = new Intent(SetReminder.this, MainActivity.class);
                startActivity(i);*/
            }
        });

    }

    private void saveNote() {
        String title = reminder.getText().toString();
        String timeSelected = time.getText().toString();
        String dateSelected = date.getText().toString();

        if (title.trim().isEmpty() || timeSelected.trim().isEmpty() || dateSelected.trim().isEmpty()) {
            Toast.makeText(this, "Please enter Title , Time & Date", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(Extra_Title, title);
        data.putExtra(Extra_Time, timeSelected);
        data.putExtra(Extra_Date, dateSelected);

        setResult(RESULT_OK, data);
        finish();
    }
}
