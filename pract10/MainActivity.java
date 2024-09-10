package com.example.nekrasovglebandreevich_10pract;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView textUserInfo;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textUserInfo = findViewById(R.id.textUserInfo);
        sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
    }

    // Метод для получения имени пользователя при нажатии кнопки "Получить имя"
    public void getUserName(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Получение данных из SharedPreferences
        String userName = sharedPreferences.getString("UserName", "Имя пользователя не указано");
        String course = sharedPreferences.getString("Course", "Курс не указан");
        String group = sharedPreferences.getString("Group", "Группа не указана");

        // Сохранение изначальной информации
        editor.putString("UserName", "Глеб");
        editor.putString("Course", "2");
        editor.putString("Group", "ИКБО-28-22");
        editor.apply();

        // Формирование и вывод информации
        String userInfo = "Имя: " + userName + "\nНомер курса: " + course + "\nГруппа: " + group;
        textUserInfo.setText(userInfo);
        textUserInfo.setVisibility(View.VISIBLE);
        // Добавляем уведомление о получении данных
        showToast("Данные получены");
    }

    // Метод для удаления имени пользователя при нажатии кнопки "Удалить"
    public void removeUserName(View view) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("UserName");
        editor.remove("Course");
        editor.remove("Group");
        editor.apply();
        textUserInfo.setText(""); // Очищаем поле с информацией
        // Добавляем уведомление об удалении данных
        showToast("Данные удалены");
    }

    // Метод для отображения уведомлений
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void goToSecondActivity(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}
