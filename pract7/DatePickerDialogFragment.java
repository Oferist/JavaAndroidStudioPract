package com.example.nekrasovglebandreevich_7pract;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import java.util.Calendar;

public class DatePickerDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(requireContext(), this, year, month, day);
    }

    @Override
    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
        // Implement your logic when a date is selected
        // For example, you can pass the selected date back to the activity or fragment
        String selectedDate = "Дата: " + dayOfMonth + "/" + (month + 1) + "/" + year;

        // Find TextView on activity_main.xml
        TextView textViewSelectedDate = getActivity().findViewById(R.id.textViewSelectedDate);
        // Set selected date to TextView
        textViewSelectedDate.setText(selectedDate);

        // Make the TextView visible
        textViewSelectedDate.setVisibility(View.VISIBLE);

        // Call onDateSelected() in MainActivity to show TimePickerDialogFragment
        ((MainActivity) getActivity()).onDateSelected();
    }
}

