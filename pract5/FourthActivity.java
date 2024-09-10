package com.example.nekrasovglebandreevich_5practpart1;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FourthActivity extends AppCompatActivity {

    private LinearLayout sortLayout;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        sortLayout = findViewById(R.id.sortLayout);
        editText = findViewById(R.id.editText);
        Button goToFiveActivityButton = findViewById(R.id.goToFiveActivityButton);

        // Устанавливаем слушатель изменений текста на EditText
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                // Получаем текст из EditText
                String newSort = s.toString().trim();
                if (!newSort.isEmpty()) {
                    // Создаем новый элемент TextView
                    TextView textView = new TextView(FourthActivity.this);
                    textView.setText(newSort);
                    // Добавляем новый элемент в LinearLayout
                    sortLayout.addView(textView);
                    // Очищаем EditText после добавления
                    editText.setText("");
                }
            }
        });

        // Устанавливаем слушатель клика на кнопку для перехода на пятую активность
        goToFiveActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FourthActivity.this, FifthActivity.class);
                startActivity(intent);
            }
        });
    }
}
