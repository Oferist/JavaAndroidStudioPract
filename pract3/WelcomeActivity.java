package com.example.nekrasovglebandreevich_3pract;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        TextView welcomeTextView = findViewById(R.id.welcomeTextView);
        Button backButton = findViewById(R.id.backButton);

        String login = getIntent().getStringExtra("login");
        String welcomeMessage = getString(R.string.welcome_message, login);
        welcomeTextView.setText(welcomeMessage);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
