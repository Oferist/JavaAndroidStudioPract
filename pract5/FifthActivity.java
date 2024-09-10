package com.example.nekrasovglebandreevich_5practpart1;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FifthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);

        // Получаем ссылку на Spinner из макета
        Spinner spinner = findViewById(R.id.spinner);

        // Создаем массив сортов
        String[] sorts = {"Сорт 1", "Сорт 2", "Сорт 3", "Сорт 4", "Сорт 5"};

        // Создаем ArrayAdapter с использованием массива сортов и стандартного макета для элементов списка
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sorts);

        // Устанавливаем макет для выпадающего списка
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Применяем адаптер к Spinner
        spinner.setAdapter(adapter);

        // Устанавливаем слушатель выбора элемента в Spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Получаем выбранный элемент
                String selectedSort = parent.getItemAtPosition(position).toString();
                // Выводим выбранный сорт в виде всплывающего сообщения
                Toast.makeText(FifthActivity.this, "Выбран сорт: " + selectedSort, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Код, который выполняется, если не выбран ни один элемент (в данном случае не требуется)
            }
        });
    }
}
