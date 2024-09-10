package com.example.nekrasovglebandreevich_6pract;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SecondActivity extends AppCompatActivity {

    private ImageView imageView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Находим Toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Получаем ActionBar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true); // Показать кнопку назад
            actionBar.setTitle("Главная"); // Установить заголовок
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home) {
                if (actionBar != null) actionBar.setTitle("Главная");
                imageView.setImageResource(R.drawable.home_image);
                return true;
            } else if (item.getItemId() == R.id.notification) {
                if (actionBar != null) actionBar.setTitle("Уведомления");
                imageView.setImageResource(R.drawable.notification_image);
                return true;
            } else if (item.getItemId() == R.id.settings) {
                if (actionBar != null) actionBar.setTitle("Настройки");
                imageView.setImageResource(R.drawable.settings_image);
                return true;
            }
            return false;
        });

        // Находим ImageView для отображения выбранной категории
        imageView = findViewById(R.id.imageView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Обработка нажатия на кнопку назад в ActionBar
        if (item.getItemId() == android.R.id.home) {
            onBackPressed(); // Закрываем текущую активность
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
