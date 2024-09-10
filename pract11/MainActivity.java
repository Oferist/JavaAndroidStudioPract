package com.example.nekrasovglebandreevich_11pract;

import android.os.Bundle;
import android.content.Intent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final long DELAY_TIME_MILLIS = 5000; // Время задержки в миллисекундах (в данном случае, 5 секунд)
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient()); // Помогает приложению открывать ссылки внутри WebView, а не во внешнем браузере
        webView.getSettings().setJavaScriptEnabled(true); // Включаем поддержку JavaScript

        webView.loadUrl("https://online-edu.mirea.ru "); // Загрузка страницы

        // Создаем объект Handler
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // После заданной задержки запускаем вторую активность
                startActivity(new Intent(MainActivity.this, MusicPlayerActivity.class));
                // Закрываем текущую активность (опционально)
                finish();
            }
        }, DELAY_TIME_MILLIS);
    }
}

