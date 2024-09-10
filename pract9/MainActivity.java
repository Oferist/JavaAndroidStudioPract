package com.example.nekrasovglebandreevich_9pract;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import android.content.DialogInterface;

public class MainActivity extends AppCompatActivity {

    EditText editTextFileName, editTextFileContent;
    TextView textViewFileContent;

    private static final String FILE_NAME_KEY = "file_name";
    private static final String FILE_CONTENT_KEY = "file_content";
    private static final String TEXT_VIEW_CONTENT_KEY = "text_view_content";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextFileName = findViewById(R.id.editTextFileName);
        editTextFileContent = findViewById(R.id.editTextFileContent);
        textViewFileContent = findViewById(R.id.textViewFileContent);

        Button buttonCreateFile = findViewById(R.id.buttonCreateFile);
        Button buttonReadFile = findViewById(R.id.buttonReadFile);
        Button buttonDeleteFile = findViewById(R.id.buttonDeleteFile);
        Button buttonAppendToFile = findViewById(R.id.buttonAppendToFile);

        buttonCreateFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createFile();
            }
        });

        buttonReadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readFile();
            }
        });

        buttonDeleteFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFile();
            }
        });

        buttonAppendToFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToFile();
            }
        });

        // Восстановление сохраненного состояния
        if (savedInstanceState != null) {
            editTextFileName.setText(savedInstanceState.getString(FILE_NAME_KEY));
            editTextFileContent.setText(savedInstanceState.getString(FILE_CONTENT_KEY));
            textViewFileContent.setText(savedInstanceState.getString(TEXT_VIEW_CONTENT_KEY));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Сохранение состояния
        outState.putString(FILE_NAME_KEY, editTextFileName.getText().toString());
        outState.putString(FILE_CONTENT_KEY, editTextFileContent.getText().toString());
        outState.putString(TEXT_VIEW_CONTENT_KEY, textViewFileContent.getText().toString());
    }

    private void createFile() {
        String fileName = editTextFileName.getText().toString();
        String fileContent = editTextFileContent.getText().toString();
        FileOutputStream fos = null;
        try {
            File file = new File(getFilesDir(), fileName);
            fos = new FileOutputStream(file);
            fos.write(fileContent.getBytes());
            Toast.makeText(this, "Файл создан успешно", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void readFile() {
        String fileName = editTextFileName.getText().toString();
        FileInputStream fis = null;
        try {
            File file = new File(getFilesDir(), fileName);
            fis = new FileInputStream(file);
            int character;
            StringBuilder fileContent = new StringBuilder();
            while ((character = fis.read()) != -1) {
                fileContent.append((char) character);
            }
            textViewFileContent.setText(fileContent.toString());
            Toast.makeText(this, "Содержимое файла прочитано успешно", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void deleteFile() {
        String fileName = editTextFileName.getText().toString();
        File file = new File(getFilesDir(), fileName);
        if (file.exists()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Вы уверены, что хотите удалить файл?")
                    .setCancelable(false)
                    .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            boolean deleted = file.delete();
                            if (deleted) {
                                Toast.makeText(MainActivity.this, "Файл успешно удален", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MainActivity.this, "Не удалось удалить файл", Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        } else {
            Toast.makeText(this, "Файл не существует", Toast.LENGTH_SHORT).show();
        }
    }

    private void appendToFile() {
        String fileName = editTextFileName.getText().toString();
        String fileContent = editTextFileContent.getText().toString();
        FileOutputStream fos = null;
        try {
            File file = new File(getFilesDir(), fileName);
            fos = new FileOutputStream(file, true); // true for append mode
            fos.write(fileContent.getBytes());
            Toast.makeText(this, "Содержимое добавлено в файл", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}