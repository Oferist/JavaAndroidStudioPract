package com.example.nekrasovglebandreevich_5practpart1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SortDescriptionActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private SortAdapter mAdapter;
    private ArrayList<SortItem> mSortList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort_description);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        Button nextButton = findViewById(R.id.nextButton); // Найдите кнопку по идентификатору

        // Получаем выбранные сорта из Intent
        ArrayList<String> selectedSorts = getIntent().getStringArrayListExtra("selectedSorts");

        // Инициализируем список сортов
        mSortList = new ArrayList<>();

        // Добавляем информацию о выбранных сортах в список
        for (String sortName : selectedSorts) {
            // Получите описание и изображение для каждого сорта
            String description = getDescriptionForSort(sortName);
            int imageResource = getImageResourceForSort(sortName);
            mSortList.add(new SortItem(sortName, description, imageResource));
        }

        // Создаем адаптер и устанавливаем его для RecyclerView
        mAdapter = new SortAdapter(this, mSortList);
        mRecyclerView.setAdapter(mAdapter);

        nextButton.setOnClickListener(new View.OnClickListener() { // Назначьте слушатель нажатий на кнопку
            @Override
            public void onClick(View v) {
                // Создайте намерение для перехода на четвёртую активность и начните его
                Intent intent = new Intent(SortDescriptionActivity.this, FourthActivity.class);
                startActivity(intent);
            }
        });
    }

    // Метод для получения описания сорта
    private String getDescriptionForSort(String sortName) {
        // Здесь вы можете реализовать логику для получения описания сорта
        // Возвращайте описание в зависимости от названия сорта
        if (sortName.equals("Сорт Гала")) {
             return "Они высокие и тонкие, с кожицей чуть более желтого цвета, чем у других яблок. Иногда на них даже бывают желтые полосы.";
        } else if (sortName.equals("Сорт Джонаголд")) {
             return "Джонагольды - это гибридные яблоки. Нечто среднее между терпким яблоком Джонатан и сладким голден делишес, они сладкие с легкой кислинкой.";
        } else if (sortName.equals("Сорт Болдуин")) {
            return "Эти яблоки в основном продаются на северо-востоке на местных фермерских рынках. У них пряно-терпко-сладкий вкус.";
        } else if (sortName.equals("Сорт Анжуйский")) {
            return "Анжуйские груши пухлые, с гладкой кожицей. Мякоть анжуйских груш сочная и упругая. Они сладкие и вкусные, если употреблять их в сыром виде.";
        } else if (sortName.equals("Сорт Азиатский")) {
            return "Азиатские груши также больше всего похожи на яблоки, чем другие груши. Благодаря своей хрустящей текстуре и сладкому вкусу эти груши идеально подходят для перекусов или употребления в сыром виде в салатах.";
        } else if (sortName.equals("Сорт Кавендиш")) {
            return "Их кожица ярко-зеленая и становится ярко-золотисто-желтой по мере созревания. Они имеют классический банановый вкус.";
        } else if (sortName.equals("Сорт Бурро")) {
            return "Бананы Бурро напоминают сорт Кавендиш, но они короче. Их мякоть при созревании имеет лимонно-банановый вкус.";
        } else if (sortName.equals("Сорт Нино")) {
            return "Бананы Нино короткие. После созревания их вкус становится богатым и сладким с ванильными и карамельными оттенками.";
        }
        return "Описание для " + sortName;
    }

    // Метод для получения ресурса изображения для сорта
    private int getImageResourceForSort(String sortName) {
        // Здесь вы можете реализовать логику для получения ресурса изображения для сорта
        // Возвращайте соответствующий ресурс в зависимости от названия сорта
         if (sortName.equals("Сорт Гала")) {
             return R.drawable.sort_gala;
         } else if (sortName.equals("Сорт Джонаголд")) {
             return R.drawable.sort_djonagold;
         } else if (sortName.equals("Сорт Болдуин")) {
             return R.drawable.sort_bolduin;
         } else if (sortName.equals("Сорт Анжуйский")) {
             return R.drawable.sort_anjuskiy;
         } else if (sortName.equals("Сорт Азиатский")) {
             return R.drawable.sort_azian;
         } else if (sortName.equals("Сорт Кавендиш")) {
             return R.drawable.sort_kavendish;
         } else if (sortName.equals("Сорт Бурро")) {
             return R.drawable.sort_burro;
         } else if (sortName.equals("Сорт Нино")) {
             return R.drawable.sort_nino;
         }
        return R.drawable.ic_launcher_background;
    }

}
