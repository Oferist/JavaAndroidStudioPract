package com.example.nekrasovglebandreevich_7pract;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.widget.Toast;

public class MyService extends Service {

    private Handler handler;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // Например, отображение уведомления или выполнение какой-то задачи
            Toast.makeText(MyService.this, "Сервис запущен и работает", Toast.LENGTH_SHORT).show();
            // Этот пример будет отображать уведомление в течение 10 секунд
            handler.postDelayed(this, 10000); // 10 секунд (10000 миллисекунд)
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handler.post(runnable); // Запускаем выполнение runnable
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable); // Удаляем runnable при остановке сервиса
        Toast.makeText(this, "Сервис остановлен", Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
