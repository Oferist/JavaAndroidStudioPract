package com.example.nekrasovglebandreevich_12practproverka;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    Uri CONTENT_URI = Uri.parse("content://com.demo.user.provider/users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickShowDetails(View view) {
        // inserting complete table details in this text field
        TextView resultView= findViewById(R.id.res);

        // creating a cursor object of the
        // content URI
        Cursor cursor = getContentResolver().query(CONTENT_URI, null, null, null, null);

        // Check if the cursor is not null and contains at least one row
        if(cursor != null && cursor.moveToFirst()) {
            StringBuilder strBuild = new StringBuilder();
            int idIndex = cursor.getColumnIndex("id");
            int nameIndex = cursor.getColumnIndex("name");

            // Check if the columns exist in the cursor
            if (idIndex != -1 && nameIndex != -1) {
                while (!cursor.isAfterLast()) {
                    strBuild.append("\n").append(cursor.getString(idIndex)).append("-").append(cursor.getString(nameIndex));
                    cursor.moveToNext();
                }
                resultView.setText(strBuild);
            } else {
                resultView.setText("Required columns not found in the cursor");
            }

            cursor.close(); // Close the cursor when done
        } else {
            resultView.setText("No Records Found");
        }
    }
}
