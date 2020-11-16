package com.tectro.mobileapp7.Dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.tectro.mobileapp7.Model.Model;
import com.tectro.mobileapp7.R;

import java.util.concurrent.atomic.AtomicReference;

public class MoveDialog extends DialogFragment {
    private int listSelector = 0;
    private AtomicReference<String>  movingValue;

    //region Singleton
    private static MoveDialog current;

    public static MoveDialog GetDialog(AtomicReference<String> movingValue) {
        if (current == null)
            current = new MoveDialog();
        current.movingValue = movingValue;
        return current;
    }

    private MoveDialog() {
    }
    //endregion


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        Activity currentActivity = getActivity();
        if (currentActivity != null) {
            View view = currentActivity.getLayoutInflater().inflate(R.layout.fragment_move_layout, null);


            ((RadioGroup) view.findViewById(R.id.list_group)).setOnCheckedChangeListener((radioGroup, i) ->
            {
                switch (i) {
                    case R.id.move:
                        listSelector = 1;
                        break;
                    case R.id.remove:
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
                        Model model = Model.CreateInstance();
                        switch (listSelector) {
                            case 1:
                                model.move(movingValue);
                                break;
                            case 2:
                                model.remove(movingValue);
                                break;
                        }
                    })
                    .create();
        }
        return super.onCreateDialog(savedInstanceState);
    }
}
