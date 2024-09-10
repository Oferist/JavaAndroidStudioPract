package com.example.nekrasovglebandreevich_7pract;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import java.util.Calendar;

public class TimePickerDialogFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // Use the current time as the default time in the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(requireContext(), this, hour, minute, false);
    }

    @Override
    public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
        // Format the selected time as a string
        String selectedTime = "Время: " + hourOfDay + ":" + minute;

        // Find TextView on activity_main.xml
        TextView textViewSelectedTime = getActivity().findViewById(R.id.textViewSelectedTime);
        // Set selected date to TextView
        textViewSelectedTime.setText(selectedTime);

        // Make the TextView visible
        textViewSelectedTime.setVisibility(View.VISIBLE);

        // Pass the selected time back to MainActivity
        ((MainActivity) getActivity()).onTimeSelected();
    }

}
