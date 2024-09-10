package com.example.nekrasovglebandreevich_6pract;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private ImageView fruitImageView; // Поле для отображения картинки фрукта

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.apple) {
                fruitImageView.setImageResource(R.drawable.apple_image);
                // Вместо вывода сообщения об ошибке можно выполнить другие действия, например, загрузить изображение яблока
            } else if (itemId == R.id.pear) {
                fruitImageView.setImageResource(R.drawable.pear_image);
            } else if (itemId == R.id.banana) {
                fruitImageView.setImageResource(R.drawable.banana_image);
            } else if (itemId == R.id.second_activity) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
            drawerLayout.closeDrawers(); // Закрываем выдвижную панель после выбора элемента
            return true;
        });

        // Находим ImageView для отображения картинки фрукта
        fruitImageView = findViewById(R.id.fruit_image_view);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
