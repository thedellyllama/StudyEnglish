package com.del.studyenglish1;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Page5_4_Grammar extends Fragment {

    private static final String ARG_TOPIC = "argTopic";
    private static final String ARG_TYPE = "argType";
    private static final String ARG_LEVEL_NAME = "argLevelName";
    private static final String ARG_LEVEL = "argLevel";
    private static final String ARG_TOPIC_ID = "argTopicID";

    private String topic;
    private String type;
    private String level_name;
    private String level;
    private int activityNum;

    private TextView textViewTopic;
    private TextView changeTopic;
    private TextView toExplanation;
    private ImageView imageProgress;
    private Button activity1;
    private Button activity2;
    private Button activity3;
    private Button activity4;
    private int yellow;

    private Page_5_3_Grammar page_5_3_grammar;
    private Page5_2 page5_2;
    private MultipleChoiceQuiz multipleChoiceQuiz;

    public static Page5_4_Grammar newInstance(String topic, String type, String level_name) {
        Page5_4_Grammar fragment = new Page5_4_Grammar();
        Bundle args = new Bundle();
        args.putString(ARG_TOPIC, topic);
        args.putString(ARG_TYPE, type);
        args.putString(ARG_LEVEL_NAME, level_name);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_page5_4_grammar, container, false);
        textViewTopic = v.findViewById(R.id.text_view_topic);
        activity1 = (Button) v.findViewById(R.id.button_activity1);
        activity2 = (Button) v.findViewById(R.id.button_activity2);
        activity3 = (Button) v.findViewById(R.id.button_activity3);
        activity4 = (Button) v.findViewById(R.id.button_activity4);
        imageProgress = v.findViewById(R.id.image_progress);
        yellow = getResources().getColor(R.color.yellow_app);

        if (getArguments() != null) {
            topic = getArguments().getString(ARG_TOPIC);
            type = getArguments().getString(ARG_TYPE);
            level_name = getArguments().getString(ARG_LEVEL_NAME);
            level = getArguments().getString(ARG_LEVEL);
        }
        textViewTopic.setText(topic);
        updateProgressImage();
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        changeTopic = (TextView) view.findViewById(R.id.text_view_change_topic);
        toExplanation = (TextView) view.findViewById(R.id.text_view_to_explanation);

        updateButtonTexts();

        activity1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showActivityDetails();
                activityNum = 1;
                openMultipleChoiceQuiz(topic, type, level_name, activityNum);
            }
        });

        activity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showActivityDetails();
                activityNum = 2;
                openMultipleChoiceQuiz(topic, type, level_name, activityNum);
            }
        });

        activity3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showActivityDetails();
                activityNum = 3;
                openMultipleChoiceQuiz(topic, type, level_name, activityNum);
            }
        });

        activity4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showActivityDetails();
                activityNum = 4;
                openMultipleChoiceQuiz(topic, type, level_name, activityNum);
            }
        });

        changeTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toExplanationPage(topic, type, level_name);
            }
        });

        toExplanation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeTopicPage(type, level, level_name);
            }
        });
    }

    private void toExplanationPage(String topic, String type, String level_name) {
        page5_2 = page5_2.newInstance(type, level, level_name);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, page5_2);
        fragmentTransaction.commit();
    }

    private void changeTopicPage(String type, String level, String level_name) {
        page_5_3_grammar = page_5_3_grammar.newInstance(topic, type, level_name);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, page_5_3_grammar);
        fragmentTransaction.commit();
    }

    public void openMultipleChoiceQuiz(String topic, String type, String level_name, int activity_num) {
        //int topicId = topic.getID()
        //multipleChoiceQuiz = multipleChoiceQuiz.newInstance(topicID, topic, type, level_name, activity_num);

        multipleChoiceQuiz = multipleChoiceQuiz.newInstance(topic, type, level_name, activity_num);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, multipleChoiceQuiz);
        fragmentTransaction.commit();
    }

    /*method to show progress by updating button text and colour after completing activity*/
    public void updateButtonTexts() {
        QuizDbHelper dbHelper = QuizDbHelper.getInstance(getContext());
        int topicId = dbHelper.getTopicId(topic, type, level_name);

        if (dbHelper.checkCompleted(topicId, 1)) {
            activity1.setText("✔");
            activity1.setBackgroundColor(yellow);
        }

        if (dbHelper.checkCompleted(topicId, 2)) {
            activity2.setText("✔");
            activity2.setBackgroundColor(yellow);
        }
        if (dbHelper.checkCompleted(topicId, 3)) {
            activity3.setText("✔");
            activity3.setBackgroundColor(yellow);
        }
        if (dbHelper.checkCompleted(topicId, 4)) {
            activity4.setText("✔");
            activity4.setBackgroundColor(yellow);
        }
    }

    /*method to update progress image based on number of activities completed*/
    public void updateProgressImage() {
        QuizDbHelper dbHelper = QuizDbHelper.getInstance(getContext());
        int topicId = dbHelper.getTopicId(topic, type, level_name);
        int activitiesCompleted = dbHelper.getActivityCompleted(topicId);
        Drawable progress0 = getResources().getDrawable(R.drawable.progress_0);
        Drawable progress1_4 = getResources().getDrawable(R.drawable.progress_1_4);
        Drawable progress2_4 = getResources().getDrawable(R.drawable.progress_2_4);
        Drawable progress3_4 = getResources().getDrawable(R.drawable.progress_3_4);
        Drawable progress4_4 = getResources().getDrawable(R.drawable.progress_complete);

        if (activitiesCompleted == 0) {
            imageProgress.setImageDrawable(progress0);
        } else if (activitiesCompleted == 1) {
                imageProgress.setImageDrawable(progress1_4);
        } else if (activitiesCompleted == 2) {
                imageProgress.setImageDrawable(progress2_4);
        } else if (activitiesCompleted == 3) {
                imageProgress.setImageDrawable(progress3_4);
        } else if (activitiesCompleted == 4) {
            imageProgress.setImageDrawable(progress4_4);
        }
    }

}