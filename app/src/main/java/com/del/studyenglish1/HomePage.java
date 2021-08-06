package com.del.studyenglish1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class HomePage extends Fragment {

    private CardView cardProfile;
    private CardView cardGoals;
    private CardView cardDictionary;
    private CardView cardStudy;

    private SelectLevelPage selectLevelPage;
    private GoalsPage goalsPage;
    private ProfilePage profilePage;
    private DictionaryPage dictionaryPage;

    private TextView textViewCurrentGoals;
    private TextView textViewCurrentTimeFrame;
    private int activitiesCompletedDaily;
    private int activitiesCompletedWeekly;
    private int activitiesGoal;
    private String timeFrameGoals;
    private QuizDbHelper dbHelper;

    public HomePage(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
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
                profilePage = new ProfilePage();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, profilePage);
                fragmentTransaction.commit();
            }
        });

        cardGoals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goalsPage = new GoalsPage();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, goalsPage);
                fragmentTransaction.commit();
            }
        });

        cardDictionary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dictionaryPage = new DictionaryPage();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, dictionaryPage);
                fragmentTransaction.commit();
            }
        });

        cardStudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectLevelPage = new SelectLevelPage();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, selectLevelPage);
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
