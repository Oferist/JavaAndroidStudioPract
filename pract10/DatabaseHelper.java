package com.example.nekrasovglebandreevich_10pract;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Contacts.db";
    private static final int DATABASE_VERSION = 1;

    // Имя таблицы и её столбцы
    private static final String TABLE_NAME = "Contacts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_AGE = "age";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PHONE = "phone";

    // Запрос для создания таблицы
    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_NAME + " TEXT," +
                    COLUMN_AGE + " INTEGER," +
                    COLUMN_EMAIL + " TEXT," +
                    COLUMN_PHONE + " TEXT)";

    // Конструктор
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Создание таблицы
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Обновление базы данных (в данном примере - пусто)
    }

    // Добавление контакта в базу данных
    public long addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, contact.getName());
        values.put(COLUMN_AGE, contact.getAge());
        values.put(COLUMN_EMAIL, contact.getEmail());
        values.put(COLUMN_PHONE, contact.getPhone());
        long result = db.insert(TABLE_NAME, null, values);
        db.close();
        return result;
    }

    // Получение всех контактов из базы данных
    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(COLUMN_ID);
            int nameIndex = cursor.getColumnIndex(COLUMN_NAME);
            int ageIndex = cursor.getColumnIndex(COLUMN_AGE);
            int emailIndex = cursor.getColumnIndex(COLUMN_EMAIL);
            int phoneIndex = cursor.getColumnIndex(COLUMN_PHONE);
            do {
                int id = cursor.getInt(idIndex);
                String name = cursor.getString(nameIndex);
                int age = cursor.getInt(ageIndex);
                String email = cursor.getString(emailIndex);
                String phone = cursor.getString(phoneIndex);
                Contact contact = new Contact(id, name, age, email, phone);
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return contactList;
    }

    // Обновление контакта в базе данных
    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, contact.getName());
        values.put(COLUMN_AGE, contact.getAge());
        values.put(COLUMN_EMAIL, contact.getEmail());
        values.put(COLUMN_PHONE, contact.getPhone());
        return db.update(TABLE_NAME, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(contact.getId())});
    }

    // Удаление контакта по номеру телефона
    public void deleteContactByPhone(String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_PHONE + " = ?", new String[]{phone});
        db.close();
    }

    // Поиск контактов по номеру телефона
    public List<Contact> searchContactsByPhone(String phone) {
        List<Contact> contactList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_NAME, COLUMN_AGE, COLUMN_EMAIL, COLUMN_PHONE};
        String selection = COLUMN_PHONE + " LIKE ?";
        String[] selectionArgs = {"%" + phone + "%"};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs,
                null, null, null);

        int columnIndexId = cursor.getColumnIndex(COLUMN_ID);
        int columnIndexName = cursor.getColumnIndex(COLUMN_NAME);
        int columnIndexAge = cursor.getColumnIndex(COLUMN_AGE);
        int columnIndexEmail = cursor.getColumnIndex(COLUMN_EMAIL);
        int columnIndexPhone = cursor.getColumnIndex(COLUMN_PHONE);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(columnIndexId);
            String name = cursor.getString(columnIndexName);
            int age = cursor.getInt(columnIndexAge);
            String email = cursor.getString(columnIndexEmail);
            String phoneNum = cursor.getString(columnIndexPhone);

            Contact contact = new Contact(id, name, age, email, phoneNum);
            contactList.add(contact);
        }

        cursor.close();
        db.close();
        return contactList;
    }
}

