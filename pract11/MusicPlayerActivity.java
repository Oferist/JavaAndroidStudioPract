package com.example.nekrasovglebandreevich_11pract;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

public class MusicPlayerActivity extends AppCompatActivity {

    public static final String CHANNEL_ID = "music_player_channel";
    public static final String CHANNEL_ID_SCHEDULE = "delayed_channel";
    public static final int NOTIFICATION_ID = 1;

    private static final int PERMISSION_REQUEST_CODE = 100;

    private Button buttonPlay;
    private MediaPlayer mediaPlayer;
    private TextView textSongTitle;
    private Animation moveRotateAnimation;

    private float dX, dY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        buttonPlay = findViewById(R.id.buttonPlay);
        mediaPlayer = MediaPlayer.create(this, R.raw.ladygaga_judas); // замените на URL вашей музыки

        textSongTitle = findViewById(R.id.textSongTitle);

        // Создание канала уведомлений
        createNotificationChannel();

        // Анимация перемещения и вращения изображения judas
        moveRotateAnimation = AnimationUtils.loadAnimation(this, R.anim.move_rotate_anim);
        moveRotateAnimation.setRepeatCount(Animation.INFINITE); // Бесконечное повторение анимации
        findViewById(R.id.imageView).startAnimation(moveRotateAnimation);

        // Анимация изменения размера и цвета текста названия песни
        final ObjectAnimator animatorSize = ObjectAnimator.ofFloat(textSongTitle, "scaleX", 0.5f, 2f);
        animatorSize.setDuration(3000);
        animatorSize.setRepeatCount(ObjectAnimator.INFINITE);
        animatorSize.setRepeatMode(ObjectAnimator.REVERSE);
        animatorSize.start();

        final ObjectAnimator animatorColor = ObjectAnimator.ofObject(textSongTitle, "textColor",
                new ArgbEvaluator(), Color.RED, Color.BLUE);
        animatorColor.setDuration(3000);
        animatorColor.setRepeatCount(ObjectAnimator.INFINITE);
        animatorColor.setRepeatMode(ObjectAnimator.REVERSE);
        animatorColor.start();

        // Остановить анимации при уничтожении активности
        textSongTitle.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View view) {}

            @Override
            public void onViewDetachedFromWindow(View view) {
                animatorSize.cancel();
                animatorColor.cancel();
            }
        });

        buttonPlay.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        dX = v.getX() - event.getRawX();
                        dY = v.getY() - event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        v.setX(event.getRawX() + dX);
                        v.setY(event.getRawY() + dY);
                        break;
                    case MotionEvent.ACTION_UP:
                        v.performClick(); // Вызываем performClick() при отпускании кнопки
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    buttonPlay.setText("Play");
                } else {
                    mediaPlayer.start();
                    buttonPlay.setText("Pause");

                    // Отправка уведомления при начале воспроизведения
                    sendNotification("Now playing", "Lady Gaga - Judas");

                    // Добавим вывод в лог для проверки
                    Log.d("MusicPlayerActivity", "Button clicked");
                }
            }
        });

        // Для обеспечения правильного обработки нажатий кнопки в режиме доступности
        buttonPlay.setAccessibilityDelegate(new View.AccessibilityDelegate() {
            @Override
            public boolean performAccessibilityAction(View host, int action, Bundle args) {
                if (action == AccessibilityNodeInfo.ACTION_CLICK) {
                    buttonPlay.performClick();
                    return true;
                }
                return super.performAccessibilityAction(host, action, args);
            }
        });

        // Обработка нажатия на кнопку для отложенного уведомления
        Button btnScheduleNotification = findViewById(R.id.btnScheduleNotification);
        btnScheduleNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(MusicPlayerActivity.this, android.Manifest.permission.SET_ALARM) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MusicPlayerActivity.this, new String[]{android.Manifest.permission.SET_ALARM}, PERMISSION_REQUEST_CODE);
                } else {
                    scheduleNotification(5000);
                }
            }
        });
    }

    private void scheduleNotification(long delay) {
        Log.d("MusicPlayerActivity", "Scheduling notification with delay: " + delay);

        Intent notificationIntent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        long futureInMillis = System.currentTimeMillis() + delay;

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, futureInMillis, pendingIntent);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, futureInMillis, pendingIntent);
            } else {
                alarmManager.set(AlarmManager.RTC_WAKEUP, futureInMillis, pendingIntent);
            }
        } catch (SecurityException e) {
            Log.e("MusicPlayerActivity", "Permission denied for setting alarms", e);
        }
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Scheduled Notification Channel";
            String description = "Channel for scheduled notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID_SCHEDULE, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }

    private void sendNotification(String title, String message) {
        // Создание интента для запуска активности при нажатии на уведомление
        Intent intent = new Intent(this, MusicPlayerActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        // Создание уведомления
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID_SCHEDULE)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(title)
                .setContentText(message)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        // Отправка уведомления
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    private void cancelNotification(Context context) {
        Intent notificationIntent = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (alarmManager != null) {
            alarmManager.cancel(pendingIntent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop(); // Остановить воспроизведение музыки
            mediaPlayer.release();
            mediaPlayer = null;
        }
        cancelNotification(this); // Отменить запланированное уведомление
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Разрешение было предоставлено, запланируем уведомление
                scheduleNotification(5000);
            } else {
                // Разрешение не было предоставлено, уведомляем пользователя
                Log.e("MusicPlayerActivity", "Permission denied to set alarms");
            }
        }
    }
}
