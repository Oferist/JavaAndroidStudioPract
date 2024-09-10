package com.example.nekrasovglebandreevich_7pract;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class CustomDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // Используем Builder для создания AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Настроим заголовок и сообщение
        builder.setTitle("Отлично, вы всё записали")
                .setMessage("Надо проверить сервис!");

        // Вернем созданный AlertDialog
        return builder.create();
    }
}
