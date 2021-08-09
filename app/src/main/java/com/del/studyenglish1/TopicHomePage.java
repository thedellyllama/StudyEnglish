package com.del.studyenglish1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class TopicHomePage extends Fragment {

    private static final String ARG_TOPIC = "argTopic";
    private static final String ARG_TYPE = "argType";
    private static final String ARG_LEVEL_NAME = "argLevelName";
    private static final String ARG_LEVEL = "argLevel";

    private String topic;
    private String type;
    private String level_name;
    private String level;

    private SelectTopicPage selectTopicPage;
    private ActivityHomePage activityHomePage;
    private ActivityHomePageReading activityHomePageReading;

    private TextView textViewTopic;
    private TextView textViewExplanation;
    private TextView changeTopic;
    private Button activities;

    public TopicHomePage() {}

    public static TopicHomePage newInstance(String topic, String type, String level, String level_name) {
        TopicHomePage fragment = new TopicHomePage();
        Bundle args = new Bundle();
        args.putString(ARG_TOPIC, topic);
        args.putString(ARG_TYPE, type);
        args.putString(ARG_LEVEL, level);
        args.putString(ARG_LEVEL_NAME, level_name);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_topic_home_page, container, false);
        textViewTopic = v.findViewById(R.id.text_view_topic);
        textViewExplanation = v.findViewById(R.id.text_view_explanation);
        textViewExplanation.setMovementMethod(new ScrollingMovementMethod());


        if (getArguments() != null) {
            topic = getArguments().getString(ARG_TOPIC);
            type = getArguments().getString(ARG_TYPE);
            level_name = getArguments().getString(ARG_LEVEL_NAME);
            level = getArguments().getString(ARG_LEVEL);
        }
        setExplanation();
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        changeTopic = (TextView) view.findViewById(R.id.text_view_change_topic);
        activities = (Button) view.findViewById(R.id.button_activities);

        activities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type.equals("READING")) {
                    openReadingActivities(topic, type, level_name);
                } else {
                    openActivities(topic, type, level_name);
                }
            }
        });
       changeTopic.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               changeTopicPage(type, level, level_name);
           }
       });
    }

    public void setExplanation() {
        QuizDbHelper dbHelper = QuizDbHelper.getInstance(getContext());
        int topicId = dbHelper.getTopicId(topic, type, level_name);
        String explanation = dbHelper.getTopicInfo(topicId);
        textViewExplanation.setText(explanation);
        textViewTopic.setText(level_name + ": " + topic);
    }

    public void changeTopicPage(String type, String level, String level_name) {
        selectTopicPage = selectTopicPage.newInstance(type, level, level_name);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, selectTopicPage);
        fragmentTransaction.commit();
    }

    public void openActivities(String topic, String type, String level_name) {
        activityHomePage = activityHomePage.newInstance(topic, type, level, level_name);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, activityHomePage);
        fragmentTransaction.commit();
    }

    public void openReadingActivities(String topic, String type, String level_name) {
        activityHomePageReading = activityHomePageReading.newInstance(topic, type, level, level_name);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, activityHomePageReading);
        fragmentTransaction.commit();
    }
}