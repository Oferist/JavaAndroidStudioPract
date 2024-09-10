package com.example.nekrasovglebandreevich_4pract;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    TextView textViewName;
    TextView textViewSurname;
    EditText editTextSubject;
    Button btnEnterInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textViewName = findViewById(R.id.textViewName);
        textViewSurname = findViewById(R.id.textViewSurname);
        editTextSubject = findViewById(R.id.editTextSubject);
        btnEnterInfo = findViewById(R.id.btnEnterInfo);

        Intent intent = getIntent();
        String firstName = intent.getStringExtra("firstName");
        String lastName = intent.getStringExtra("lastName");

        // Проверяем, что данные не null, чтобы избежать ошибок при отображении
        if (firstName != null && lastName != null) {
            textViewName.setText("Имя: " + firstName);
            textViewSurname.setText("Фамилия: " + lastName);
        }

        btnEnterInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subject = editTextSubject.getText().toString().trim();

                // Создаем Intent для перехода на ThirdActivity
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivityForResult(intent, 1); // Запускаем третью активность с ожиданием результата
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                // Получаем переданные данные о времени из ThirdActivity
                String time = data.getStringExtra("time");
                Toast.makeText(SecondActivity.this, "Получено время: " + time, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
