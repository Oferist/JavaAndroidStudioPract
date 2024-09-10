package com.example.nekrasovglebandreevich;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "SecondActivity";
    private TextView textViewData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Log.d(TAG, "onCreate: IKBO-28-22");

        textViewData = findViewById(R.id.textViewData);

        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null) {
            String fullName = intent.getStringExtra("ФИО");
            String group = intent.getStringExtra("Группа");
            int age = intent.getIntExtra("Возраст", 0);
            double grade = intent.getDoubleExtra("Оценка", 0);

            String data = "Full Name: " + fullName + "\n" +
                    "Group: " + group + "\n" +
                    "Age: " + age + "\n" +
                    "Grade: " + grade;

            textViewData.setText(data);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: IKBO-28-22");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: IKBO-28-22");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: IKBO-28-22");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: Activity stopped");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: Activity destroyed");
    }
}
