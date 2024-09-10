package com.example.nekrasovglebandreevich_12pract;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Uri CONTENT_URI = Uri.parse("content://com.demo.user.provider/users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }

    public void onClickAddDetails(View view) {
        // class to add values in the database
        ContentValues values = new ContentValues();

        // fetching text from user
        values.put("name", ((EditText) findViewById(R.id.textName)).getText().toString());

        // inserting into database through content URI
        getContentResolver().insert(MyContentProvider.CONTENT_URI, values);

        // displaying a toast message
        Toast.makeText(getBaseContext(), "New Record Inserted", Toast.LENGTH_LONG).show();
    }

    public void onClickShowDetails(View view) {
        // inserting complete table details in this text field
        TextView resultView = (TextView) findViewById(R.id.res);

        // creating a cursor object of the content URI
        Cursor cursor = getContentResolver().query(CONTENT_URI, null, null, null, null);

        // iteration of the cursor to print the whole table
        if (cursor != null && cursor.moveToFirst()) {
            StringBuilder strBuild = new StringBuilder();
            int idIndex = cursor.getColumnIndex("id");
            int nameIndex = cursor.getColumnIndex("name");

            if (idIndex != -1 && nameIndex != -1) {
                while (!cursor.isAfterLast()) {
                    strBuild.append("\n")
                            .append(cursor.getString(idIndex))
                            .append(" - ")
                            .append(cursor.getString(nameIndex));
                    cursor.moveToNext();
                }
                resultView.setText(strBuild);
            } else {
                resultView.setText("Columns not found");
            }
            cursor.close();
        } else {
            resultView.setText("No Records Found");
        }
    }
}
