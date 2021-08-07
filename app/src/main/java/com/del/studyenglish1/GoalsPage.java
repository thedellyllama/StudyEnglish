package com.del.studyenglish1;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

public class GoalsPage extends Fragment {

    private int activitiesCompletedDaily;
    private int activitiesCompletedWeekly;
    private int activitiesGoal;
    private String timeFrameGoals;

    private QuizDbHelper dbHelper;

    private TextView textViewCurrentGoals;
    private TextView textViewCurrentTimeFrame;
    private ImageView imageViewGoal;
    private Button button1;
    private Button button3;
    private Button button5;
    private Button button10;
    private Button buttonDaily;
    private Button buttonWeekly;
    private Button buttonUpdate;
    private ConstraintLayout bannerGoals;
    private Drawable buttonBg;
    private Drawable buttonYellowBg;
    private Drawable buttonBlueBg;
    private Drawable buttonYellowOutlineBg;
    private int appBlue;
    private int appYellow;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goals_page, container, false);
        dbHelper = QuizDbHelper.getInstance(getContext());
        textViewCurrentGoals = view.findViewById(R.id.text_view_current_goals);
        textViewCurrentTimeFrame = view.findViewById(R.id.text_view_daily_goals);
        button1 = view.findViewById(R.id.button_1);
        button3 = view.findViewById(R.id.button_3);
        button5 = view.findViewById(R.id.button_5);
        button10 = view.findViewById(R.id.button_10);
        buttonDaily = view.findViewById(R.id.button_daily);
        buttonWeekly = view.findViewById(R.id.button_weekly);
        buttonUpdate = view.findViewById(R.id.button_update);
        imageViewGoal = view.findViewById(R.id.image_view_goal);
        bannerGoals = view.findViewById(R.id.banner_layout);
        appBlue =  getResources().getColor(R.color.blue_app);
        appYellow = getResources().getColor(R.color.yellow_app);
        buttonBg = getResources().getDrawable(R.drawable.blue_button_bg);
        buttonYellowBg = getResources().getDrawable(R.drawable.yellow_button_bg);
        buttonBlueBg = getResources().getDrawable(R.drawable.blue_button_all_bg);
        buttonYellowOutlineBg = getResources().getDrawable(R.drawable.yellow_button_outline_bg);

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
        updateButtonColours(buttonYellowOutlineBg, appBlue);

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateGoals();
                updateButtonColours(buttonYellowOutlineBg, appBlue);
                updateActivitiesCompleted();
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.updateGoalActivities(1);
                updateGoals();
                updateButtonColours(buttonBlueBg, Color.WHITE);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.updateGoalActivities(3);
                updateGoals();
                updateButtonColours(buttonBlueBg, Color.WHITE);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.updateGoalActivities(5);
                updateGoals();
                updateButtonColours(buttonBlueBg, Color.WHITE);
            }
        });
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.updateGoalActivities(10);
                updateGoals();
                updateButtonColours(buttonBlueBg, Color.WHITE);
            }
        });
        buttonDaily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.updateGoalTimeFrame("DAILY");
                updateGoals();
                updateButtonColours(buttonBlueBg, Color.WHITE);
            }
        });
        buttonWeekly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.updateGoalTimeFrame("WEEKLY");
                updateGoals();
                updateButtonColours(buttonBlueBg, Color.WHITE);
            }
        });
    }

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
    public void updateGoals() {
        QuizDbHelper dbHelper = QuizDbHelper.getInstance(getContext());
        timeFrameGoals = dbHelper.getTimeFrameGoals();
        activitiesGoal = dbHelper.getActivityGoals();
    }

    public void updateButtonColours(Drawable colour, int textColor) {
        button1.setBackground(buttonBg);
        button3.setBackground(buttonBg);
        button5.setBackground(buttonBg);
        button10.setBackground(buttonBg);
        buttonDaily.setBackground(buttonBg);
        buttonWeekly.setBackground(buttonBg);

        button1.setTextColor(appBlue);
        button3.setTextColor(appBlue);
        button5.setTextColor(appBlue);
        button10.setTextColor(appBlue);
        buttonDaily.setTextColor(appBlue);
        buttonWeekly.setTextColor(appBlue);

        //set button colours based on goal settings
        switch (activitiesGoal) {
            case 1:
                button1.setBackground(colour);
                button1.setTextColor(textColor);
                break;
            case 3:
                button3.setBackground(colour);
                button3.setTextColor(textColor);
                break;
            case 5:
                button5.setBackground(colour);
                button5.setTextColor(textColor);
                break;
            case 10:
                button10.setBackground(colour);
                button10.setTextColor(textColor);
                break;
        }

        //set button colours based on time frame settings
        switch (timeFrameGoals) {
            case "DAILY":
                buttonDaily.setBackground(colour);
                buttonDaily.setTextColor(textColor);
                break;
            case "WEEKLY":
                buttonWeekly.setBackground(colour);
                buttonWeekly.setTextColor(textColor);
                break;
        }
    }

}
