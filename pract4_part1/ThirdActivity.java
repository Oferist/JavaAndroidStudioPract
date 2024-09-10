package com.example.nekrasovglebandreevich_4pract;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    EditText editTextDay, editTextTime, editTextComments;
    Button btnOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        editTextDay = findViewById(R.id.editTextDay);
        editTextTime = findViewById(R.id.editTextTime);
        editTextComments = findViewById(R.id.editTextComments);
        btnOK = findViewById(R.id.btnOK);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Получите данные из полей ввода
                String day = editTextDay.getText().toString().trim();
                String time = editTextTime.getText().toString().trim();
                String comments = editTextComments.getText().toString().trim();

                // Создайте сообщение о успешной передаче данных
                Toast.makeText(ThirdActivity.this, "Время занятия успешно передано", Toast.LENGTH_LONG).show();

                // Возврат к предыдущей активности с передачей результата
                Intent resultIntent = new Intent();
                resultIntent.putExtra("time", time); // Передача времени в качестве результата
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
