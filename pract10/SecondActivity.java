package com.example.nekrasovglebandreevich_10pract;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private ListView listViewContacts;
    private EditText editTextName, editTextAge, editTextEmail, editTextPhone;
    private Button buttonSave, buttonUpdate, buttonDelete, buttonFind;
    private DatabaseHelper databaseHelper;
    private List<Contact> contactList;
    private ArrayAdapter<Contact> adapter;
    private int selectedContactPosition = AdapterView.INVALID_POSITION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        listViewContacts = findViewById(R.id.listViewContacts);
        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPhone = findViewById(R.id.editTextPhone);
        buttonSave = findViewById(R.id.buttonSave);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonFind = findViewById(R.id.buttonFind);

        databaseHelper = new DatabaseHelper(this);

        // Получение всех контактов из базы данных
        contactList = databaseHelper.getAllContacts();

        // Создание адаптера для списка контактов
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contactList);

        // Установка адаптера для списка
        listViewContacts.setAdapter(adapter);

        // Обработчик нажатия на элемент списка
        listViewContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Сохраняем позицию выбранного контакта
                selectedContactPosition = position;

                // Получаем выбранный контакт из списка
                Contact selectedContact = contactList.get(selectedContactPosition);

                // Заполняем поля ввода данными выбранного контакта
                editTextName.setText(selectedContact.getName());
                editTextAge.setText(String.valueOf(selectedContact.getAge()));
                editTextEmail.setText(selectedContact.getEmail());
                editTextPhone.setText(selectedContact.getPhone());
            }
        });

        // Обработчик нажатия на кнопку "Save"
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Получаем данные из полей ввода
                String name = editTextName.getText().toString();
                int age = Integer.parseInt(editTextAge.getText().toString());
                String email = editTextEmail.getText().toString();
                String phone = editTextPhone.getText().toString();

                // Создаем новый контакт с автоматически сгенерированным id
                // В данном случае, так как мы добавляем новый контакт, id не имеет значения,
                // поэтому мы можем передать любое значение, например, -1
                Contact newContact = new Contact(-1, name, age, email, phone);

                // Добавляем контакт в базу данных
                long result = databaseHelper.addContact(newContact);

                if (result != -1) {
                    // Обновляем список контактов
                    contactList.clear();
                    contactList.addAll(databaseHelper.getAllContacts());
                    adapter.notifyDataSetChanged();

                    // Выводим Toast сообщение
                    Toast.makeText(SecondActivity.this, "Contact saved successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Обработчик нажатия на кнопку "Update Contact"
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Проверяем, что позиция выбрана
                if (selectedContactPosition != AdapterView.INVALID_POSITION) {
                    // Получаем данные из полей ввода
                    int id = contactList.get(selectedContactPosition).getId();
                    String name = editTextName.getText().toString();
                    int age = Integer.parseInt(editTextAge.getText().toString());
                    String email = editTextEmail.getText().toString();
                    String phone = editTextPhone.getText().toString();

                    // Создаем объект Contact с обновленными данными
                    Contact updatedContact = new Contact(id, name, age, email, phone);

                    // Обновляем контакт в базе данных
                    int rowsAffected = databaseHelper.updateContact(updatedContact);

                    if (rowsAffected > 0) {
                        // Обновляем список контактов
                        contactList.clear();
                        contactList.addAll(databaseHelper.getAllContacts());
                        adapter.notifyDataSetChanged();

                        // Выводим Toast сообщение
                        Toast.makeText(SecondActivity.this, "Contact updated successfully", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Обработчик нажатия на кнопку "Delete"
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Получаем номер телефона из поля ввода
                String phone = editTextPhone.getText().toString();

                // Удаляем контакт из базы данных по номеру телефона
                databaseHelper.deleteContactByPhone(phone);

                // Обновляем список контактов
                contactList.clear();
                contactList.addAll(databaseHelper.getAllContacts());
                adapter.notifyDataSetChanged();

                // Выводим Toast сообщение
                Toast.makeText(SecondActivity.this, "Contact deleted successfully", Toast.LENGTH_SHORT).show();
            }
        });

        // Обработчик нажатия на кнопку "Find"
        buttonFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Получаем номер телефона из поля ввода
                String phone = editTextPhone.getText().toString();

                // Поиск контактов по номеру телефона
                contactList.clear();
                contactList.addAll(databaseHelper.searchContactsByPhone(phone));
                adapter.notifyDataSetChanged();

                // Выводим Toast сообщение
                Toast.makeText(SecondActivity.this, "Contacts found successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
