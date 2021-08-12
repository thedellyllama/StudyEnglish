package com.del.studyenglish1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class ProgressPage extends Fragment {

    private int activitiesCompletedDaily;
    private int activitiesCompletedWeekly;
    private int activitiesGoal;
    private String timeFrameGoals;

    private QuizDbHelper dbHelper;

    private ListView listView;
    private TextView textViewCurrentGoals;
    private TextView textViewCurrentTimeFrame;
    private TextView textViewNoProgress;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_progress_page, container, false);
        dbHelper = QuizDbHelper.getInstance(getContext());
        textViewCurrentGoals = view.findViewById(R.id.text_view_current_goals);
        textViewCurrentTimeFrame = view.findViewById(R.id.text_view_daily_goals);
        listView = view.findViewById(R.id.list_view_progress);
        textViewNoProgress = view.findViewById(R.id.text_view_no_progress);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        timeFrameGoals = dbHelper.getTimeFrameGoals();
        activitiesGoal = dbHelper.getActivityGoals();
        activitiesCompletedDaily = dbHelper.getAllActivityCompletedDaily();
        activitiesCompletedWeekly = dbHelper.getAllActivityCompletedWeekly();

        updateGoals();
        updateActivitiesCompleted();
        loadProgress();

    }

    /**
     * Updates the number of activities completed in the Goal banner
     */
    public void updateActivitiesCompleted() {

        if (timeFrameGoals.equals("DAILY")) {
            if (activitiesCompletedDaily >= activitiesGoal) {
                textViewCurrentGoals.setText(activitiesCompletedDaily + "/" + activitiesGoal + " activities completed\nGOAL ACHIEVED!");

            } else {
                textViewCurrentGoals.setText(activitiesCompletedDaily + "/" + activitiesGoal + " activities completed");
            }
        } else {
            if (activitiesCompletedWeekly >= activitiesGoal) {
                textViewCurrentGoals.setText(activitiesCompletedWeekly + "/" + activitiesGoal + " activities completed\nGOAL ACHIEVED!");
            } else {
                textViewCurrentGoals.setText(activitiesCompletedWeekly + "/" + activitiesGoal + " activities completed");
            }
        }
        textViewCurrentTimeFrame.setText(timeFrameGoals + " GOALS:");

    }

    /**
     * Access the database to update the Goal selection in the Goal banner
     */
    public void updateGoals() {
        QuizDbHelper dbHelper = QuizDbHelper.getInstance(getContext());
        timeFrameGoals = dbHelper.getTimeFrameGoals();
        activitiesGoal = dbHelper.getActivityGoals();
    }

    /**
     * Load all topics with >0 activities completed into listView
     */
    public void loadProgress() {
        QuizDbHelper dbHelper = QuizDbHelper.getInstance(getContext());
        ArrayList<Topic> progressList = dbHelper.getTopicProgress();
       if (progressList.isEmpty()) {
           textViewNoProgress.setText("Complete an activity to see your progress here!");
        } else {
            MyProgressAdapter myProgressAdapter = new MyProgressAdapter(getContext(), progressList);
            listView.setAdapter(myProgressAdapter);
       }
    }

}
