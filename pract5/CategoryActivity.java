package com.example.nekrasovglebandreevich_5practpart1;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    ArrayList<String> products = new ArrayList<>();
    ArrayAdapter<String> adapter;
    TextView selectedItemsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        ListView listView = findViewById(R.id.categoryListView);
        EditText editText = findViewById(R.id.editText);
        Button addButton = findViewById(R.id.addButton);
        Button deleteButton = findViewById(R.id.deleteButton);
        Button showDescriptionButton = findViewById(R.id.showDescriptionButton);
        selectedItemsTextView = findViewById(R.id.selectedItemsTextView);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, products);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        // Получаем категорию из Intent
        String category = getIntent().getStringExtra("category");

        // Пример заполнения списка продуктов
        if (category.equals("Яблоки")) {
            products.add("Сорт Гала");
            products.add("Сорт Джонаголд");
            products.add("Сорт Болдуин");
        } else if (category.equals("Груши")) {
            products.add("Сорт Анжуйский");
            products.add("Сорт Азиатский");
        } else if (category.equals("Бананы")) {
            products.add("Сорт Кавендиш");
            products.add("Сорт Бурро");
            products.add("Сорт Нино");
        }

        adapter.notifyDataSetChanged();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newProduct = editText.getText().toString().trim();
                if (!newProduct.isEmpty()) {
                    products.add(newProduct);
                    adapter.notifyDataSetChanged();
                    editText.setText("");
                    Toast.makeText(CategoryActivity.this, "Добавлен сорт: " + newProduct, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CategoryActivity.this, "Введите название сорта", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Установка слушателя нажатий для кнопки
        showDescriptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Получаем список выбранных сортов
                ArrayList<String> selectedSorts = new ArrayList<>();
                SparseBooleanArray checkedItems = listView.getCheckedItemPositions();
                for (int i = 0; i < checkedItems.size(); i++) {
                    int position = checkedItems.keyAt(i);
                    if (checkedItems.valueAt(i)) {
                        selectedSorts.add(products.get(position));
                    }
                }

                // Проверяем количество выбранных сортов
                if (selectedSorts.size() > 3) {
                    Toast.makeText(CategoryActivity.this, "Можно выбрать только три сорта", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Переходим к SortDescriptionActivity и передаем выбранные сорта
                Intent intent = new Intent(CategoryActivity.this, SortDescriptionActivity.class);
                intent.putStringArrayListExtra("selectedSorts", selectedSorts);
                startActivity(intent);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SparseBooleanArray checkedItemPositions = listView.getCheckedItemPositions();
                List<String> itemsToRemove = new ArrayList<>();
                // Определяем элементы для удаления
                for (int i = checkedItemPositions.size() - 1; i >= 0; i--) {
                    int position = checkedItemPositions.keyAt(i);
                    if (checkedItemPositions.get(position)) {
                        itemsToRemove.add(products.get(position));
                    }
                }
                // Удаляем элементы из списка
                for (String item : itemsToRemove) {
                    adapter.remove(item);
                }
                adapter.notifyDataSetChanged();
                // Снимаем выбор со всех удаляемых элементов
                listView.clearChoices();
                // Обновляем отображение выбранных сортов после удаления
                updateSelectedItems();
            }
        });


        // Обновляем отображение выбранных сортов при выборе элементов
        listView.setOnItemClickListener((parent, view, position, id) -> updateSelectedItems());
    }

    // Метод для обновления списка выбранных сортов
    private void updateSelectedItems() {
        StringBuilder selectedItems = new StringBuilder("");
        SparseBooleanArray checkedItemPositions = ((ListView) findViewById(R.id.categoryListView)).getCheckedItemPositions();
        for (int i = 0; i < checkedItemPositions.size(); i++) {
            int position = checkedItemPositions.keyAt(i);
            if (checkedItemPositions.get(position)) {
                selectedItems.append("\n- ").append(products.get(position));
            }
        }
        selectedItemsTextView.setText(selectedItems.toString());
    }

}
