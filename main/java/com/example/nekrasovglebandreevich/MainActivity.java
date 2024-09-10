package com.example.nekrasovglebandreevich;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private EditText editTextFullName;
    private EditText editTextGroup;
    private EditText editTextAge;
    private EditText editTextGrade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: IKBO-28-22");

        editTextFullName = findViewById(R.id.editTextFullName);
        editTextGroup = findViewById(R.id.editTextGroup);
        editTextAge = findViewById(R.id.editTextAge);
        editTextGrade = findViewById(R.id.editTextGrade);
    }

    public void openSecondActivity(View view) {
        String fullName = editTextFullName.getText().toString();
        String group = editTextGroup.getText().toString();
        int age = Integer.parseInt(editTextAge.getText().toString());
        double grade = Double.parseDouble(editTextGrade.getText().toString());

        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("ФИО", fullName);
        intent.putExtra("Группа", group);
        intent.putExtra("Возраст", age);
        intent.putExtra("Оценка", grade);
        startActivity(intent);
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
        Log.d(TAG, "onStop: IKBO-28-22");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: IKBO-28-22");
    }

    // Декларативный переход на вторую активность
    public void onDeclarativeButtonClick(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    // Программный переход на вторую активность
    public void onProgrammaticButtonClick(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        // Добавление данных в интент
        intent.putExtra("fullname", "John Doe");
        intent.putExtra("group", "IKBO-28-22");
        intent.putExtra("age", 25);
        intent.putExtra("grade", 5.0);
        startActivity(intent);
    }
}
