package com.example.nekrasovglebandreevich_12practzadanie2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName, editTextAge, editTextEmail;
    private Button buttonSave, buttonLoad;
    private static final String FILE_NAME = "user.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        editTextEmail = findViewById(R.id.editTextEmail);
        buttonSave = findViewById(R.id.buttonSave);
        buttonLoad = findViewById(R.id.buttonLoad);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

        buttonLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });
    }

    private void saveData() {
        String name = editTextName.getText().toString();
        int age = Integer.parseInt(editTextAge.getText().toString());
        String email = editTextEmail.getText().toString();

        User user = new User(name, age, email);

        Gson gson = new Gson();
        String jsonString = gson.toJson(user);

        try (FileOutputStream fos = openFileOutput(FILE_NAME, MODE_PRIVATE)) {
            fos.write(jsonString.getBytes());
            Toast.makeText(this, "Data saved to " + FILE_NAME, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to save data", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadData() {
        try (FileInputStream fis = openFileInput(FILE_NAME);
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader reader = new BufferedReader(isr)) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            String jsonString = stringBuilder.toString();

            Gson gson = new Gson();
            User user = gson.fromJson(jsonString, User.class);

            editTextName.setText(user.getName());
            editTextAge.setText(String.valueOf(user.getAge()));
            editTextEmail.setText(user.getEmail());

            Toast.makeText(this, "Data loaded from " + FILE_NAME, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to load data", Toast.LENGTH_SHORT).show();
        }
    }
}