package com.example.periodtracker2;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
public class insertdate extends AppCompatActivity implements DatePickerDialog.OnDateSetListener
{

    private TextView startDateTextView;
    private TextView endDateTextView;
    private Button selectStartDateButton;
    private Button selectEndDateButton;
    private Calendar startDate;
    private Calendar endDate;
    
    private TextView nextPeriodTextView;

    private Button savebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertdate);

        startDateTextView = findViewById(R.id.start_date_text_view);
        endDateTextView = findViewById(R.id.end_date_text_view);
        selectStartDateButton = findViewById(R.id.select_start_date_button);
        selectEndDateButton = findViewById(R.id.select_end_date_button);
        savebutton = findViewById(R.id.save_button);
        nextPeriodTextView=findViewById(R.id.next_period_text_view);

        selectStartDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(true);
            }
        });

        selectEndDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(false);
            }
        });

        savebutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                adddate(view);
            }
        });
    }

    private void showDatePickerDialog(boolean isStartDate) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, this,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));

        if (isStartDate) {
            datePickerDialog.getDatePicker().setTag("start");
        } else {
            datePickerDialog.getDatePicker().setTag("end");
        }

        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        String tag = (String) datePicker.getTag();

        if (tag.equals("start")) {
            startDate = Calendar.getInstance();
            startDate.set(year, month, dayOfMonth);
            startDateTextView.setText(getFormattedDate(startDate));
        } else {
            endDate = Calendar.getInstance();
            endDate.set(year, month, dayOfMonth);
            endDateTextView.setText(getFormattedDate(endDate));
        }
    }

    private String getFormattedDate(Calendar calendar) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        return dateFormat.format(calendar.getTime());
    }
    public void adddate(View view)
    {
            dbmanage db = new dbmanage(this);
            String res = db.addrec(startDate.getTime(), endDate.getTime());
            Toast.makeText(this, res, Toast.LENGTH_LONG).show();
            startDate.getTime();
            endDate.getTime();
        Calendar nextPeriod = Calendar.getInstance();
        nextPeriod.setTime(startDate.getTime());
        nextPeriod.add(Calendar.DATE, 28);

        // Format the next period date to dd/MM/yyyy
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        String nextPeriodDate = dateFormat.format(nextPeriod.getTime());

        // Display the next period date
        nextPeriodTextView.setText("Next period date: " + nextPeriodDate);
    }
    public void gotonext(View view)
    {
        startActivity(new Intent(this, Showdate.class));
    }

}
