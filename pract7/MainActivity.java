package com.example.nekrasovglebandreevich_7pract;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements AlertDialogFragment.AlertDialogListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AlertDialogFragment alertDialogFragment = new AlertDialogFragment();
        alertDialogFragment.show(getSupportFragmentManager(), "alertDialog");
    }

    public void startService(View view) {
        Intent serviceIntent = new Intent(this, MyService.class);
        startService(serviceIntent);
    }

    public void stopService(View view) {
        Intent serviceIntent = new Intent(this, MyService.class);
        stopService(serviceIntent);
    }

    @Override
    public void onPositiveClick() {
        // Show DatePickerDialogFragment if the user selected the corresponding option
        DatePickerDialogFragment datePickerDialogFragment = new DatePickerDialogFragment();
        datePickerDialogFragment.show(getSupportFragmentManager(), "datePicker");

        // Найдем TextView
        TextView textViewFeedback = findViewById(R.id.textViewFeedback);
        // Сделаем его видимым
        textViewFeedback.setVisibility(View.VISIBLE);
    }

    @Override
    public void onNegativeClick() {
        // Find TextView for negative feedback
        TextView textViewNegativeFeedback = findViewById(R.id.textViewNegativeFeedback);
        // Make it visible
        textViewNegativeFeedback.setVisibility(View.VISIBLE);

        // Show DatePickerDialogFragment
        DatePickerDialogFragment datePickerDialogFragment = new DatePickerDialogFragment();
        datePickerDialogFragment.show(getSupportFragmentManager(), "datePicker");
    }

    // Добавьте этот метод для обработки выбора времени из TimePickerDialogFragment
    public void onDateSelected() {
        // Show TimePickerDialogFragment after the date is selected
        TimePickerDialogFragment timePickerDialogFragment = new TimePickerDialogFragment();
        timePickerDialogFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void onTimeSelected() {
        // Show CustomDialogFragment after the time is selected
        CustomDialogFragment customDialogFragment = new CustomDialogFragment();
        customDialogFragment.show(getSupportFragmentManager(), "customDialog");
    }
}
