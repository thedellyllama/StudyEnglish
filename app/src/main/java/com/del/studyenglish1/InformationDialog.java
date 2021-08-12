package com.del.studyenglish1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class InformationDialog extends AppCompatDialogFragment {
    private static final String ARG_COUNT = "argCount";
    private int questionCount;
    private long time;

    /**
     * Creates and opens new instance of Information Dialog with the
     * given number of questions
     * @param questionCount number of questions in activity
     * @return Dialog with information about number of questions
     * and estimated time to complete activity
     */
   public static InformationDialog newInstance(int questionCount) {
        InformationDialog informationDialog = new InformationDialog();
        Bundle args = new Bundle();
        args.putInt(ARG_COUNT, questionCount);
        informationDialog.setArguments(args);
        return informationDialog;
    }

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {
            questionCount = getArguments().getInt(ARG_COUNT);
            if (questionCount < 3) {
                time = 1;
            } else {
                time = (questionCount * 30) / 60;
            }
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Information")
                .setMessage("Number of questions: " + questionCount
                        + "\nEstimated time to complete: " + time + " minutes")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                });

        return builder.create();
    }
}
