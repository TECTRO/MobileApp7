package com.tectro.mobileapp7.Dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.VoiceInteractor;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.tectro.mobileapp7.Model.Model;
import com.tectro.mobileapp7.R;

import java.util.concurrent.atomic.AtomicReference;

public class AddDialog extends DialogFragment {
    private int listSelector;

    //region Singleton
    private static AddDialog current;

    public static AddDialog GetDialog() {
        if (current == null)
            current = new AddDialog();
        return current;
    }

    private AddDialog() {
    }
    //endregion

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        Activity currentActivity = getActivity();
        if (currentActivity != null) {
            View view = currentActivity.getLayoutInflater().inflate(R.layout.fragment_add_layout, null);

            TextView newItem =  view.findViewById(R.id.newListItem);
            ((RadioGroup) view.findViewById(R.id.list_group)).setOnCheckedChangeListener((radioGroup, i) ->
            {
                switch (i) {
                    case R.id.list_1:
                        listSelector = 1;
                        break;
                    case R.id.list_2:
                        listSelector = 2;
                        break;
                }
            });

            return new AlertDialog.Builder(getActivity())
                    .setView(view)
                    .setNegativeButton("отмена", (dialogInterface, i) -> {
                    })
                    .setPositiveButton("ok", (dialogInterface, i) ->
                    {
                        if (newItem.getText().toString() != "") {
                            Model model = Model.CreateInstance();
                            switch (listSelector) {
                                case 1:
                                    model.getList1().add(new AtomicReference<>(newItem.getText().toString()));
                                    break;
                                case 2:
                                    model.getList2().add(new AtomicReference<>(newItem.getText().toString()));
                                    break;
                            }
                            model.invoke();
                        }
                    })
                    .create();
        }
        return super.onCreateDialog(savedInstanceState);
    }
}
