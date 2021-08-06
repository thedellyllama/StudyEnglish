package com.del.studyenglish1;

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
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class Page4 extends Fragment {

    private CardView cardProfile;
    private CardView cardGoals;
    private CardView cardDictionary;
    private CardView cardStudy;

    private Page5 page5;
    private Page6 page6;
    private Page7 page7;
    private Page8 page8;

    private TextView textViewCurrentGoals;
    private TextView textViewCurrentTimeFrame;
    private int activitiesCompletedDaily;
    private int activitiesCompletedWeekly;
    private int activitiesGoal;
    private String timeFrameGoals;
    private QuizDbHelper dbHelper;

    public Page4(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page4, container, false);
        dbHelper = QuizDbHelper.getInstance(getContext());
        textViewCurrentGoals = view.findViewById(R.id.text_view_current_goals);
        textViewCurrentTimeFrame = view.findViewById(R.id.text_view_daily_goals);
        timeFrameGoals = dbHelper.getTimeFrameGoals();
        activitiesGoal = dbHelper.getActivityGoals();
        activitiesCompletedDaily = dbHelper.getAllActivityCompletedDaily();
        activitiesCompletedWeekly = dbHelper.getAllActivityCompletedWeekly();

        updateGoals();
        updateActivitiesCompleted();
          return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //NavController navController = Navigation.findNavController(view);

        cardProfile = view.findViewById(R.id.card_view_profile);
        cardGoals = view.findViewById(R.id.card_view_goals);
        cardDictionary = view.findViewById(R.id.card_view_dictionary);
        cardStudy = view.findViewById(R.id.card_view_study);

        updateGoals();
        updateActivitiesCompleted();

        cardProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page7 = new Page7();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, page7);
                fragmentTransaction.commit();
            }
        });

        cardGoals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page6 = new Page6();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, page6);
                fragmentTransaction.commit();
            }
        });

        cardDictionary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page8 = new Page8();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, page8);
                fragmentTransaction.commit();
            }
        });

        cardStudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page5 = new Page5();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, page5);
                fragmentTransaction.commit();
            }
        });


    }

    public void updateActivitiesCompleted() {
        //QuizDbHelper dbHelper = QuizDbHelper.getInstance(getContext());
        //newActivitiesCompletedDaily = dbHelper.getAllActivityCompletedDaily();
        //newActivitiesCompletedWeekly = dbHelper.getAllActivityCompletedWeekly();
       /** textViewCurrentTimeFrame.setText(timeFrameGoals + " GOALS:");
        textViewCurrentGoals.setText(activitiesCompletedDaily + "/" + activitiesGoal + " activities completed");
        textViewCurrentTimeFrame.setText(timeFrameGoals + " GOALS:");
**/
        activitiesCompletedDaily = dbHelper.getAllActivityCompletedDaily();
        activitiesCompletedWeekly = dbHelper.getAllActivityCompletedWeekly();

            if (timeFrameGoals.equals("DAILY")) {
                if (activitiesCompletedDaily >= activitiesGoal) {
                    //textViewCurrentGoals.setText(activitiesCompletedDaily + "/" + activitiesGoal + " activities completed\nGOAL ACHIEVED!");
                    textViewCurrentGoals.setText(activitiesCompletedDaily + "/" + activitiesGoal + " activities completed\nGOAL ACHIEVED!");

                } else {
                    textViewCurrentGoals.setText(activitiesCompletedDaily + "/" + activitiesGoal + " activities completed");
                }
            } else {
                if (activitiesCompletedWeekly >= activitiesGoal) {
                    textViewCurrentGoals.setText(activitiesCompletedWeekly + "/" + activitiesGoal + " activities completed\nGOAL ACHIEVED!");
                } else {
                    textViewCurrentGoals.setText(activitiesCompletedWeekly + "/" + activitiesGoal + " activities completed");
                }        activitiesGoal = dbHelper.getActivityGoals();
                textViewCurrentTimeFrame.setText(timeFrameGoals + " GOALS:");
            }


/*
        if (timeFrameGoals.equals("DAILY")) {
            textViewCurrentGoals.setText(activitiesCompletedDaily + "/" + activitiesGoal + " activities completed");
        } else {
            textViewCurrentGoals.setText(activitiesCompletedWeekly + "/" + activitiesGoal + " activities completed");
        }

        textViewCurrentTimeFrame.setText(timeFrameGoals + " GOALS:");
*/
    }

    public void updateGoals() {
        QuizDbHelper dbHelper = QuizDbHelper.getInstance(getContext());
        timeFrameGoals = dbHelper.getTimeFrameGoals();
        activitiesGoal = dbHelper.getActivityGoals();
    }

}
