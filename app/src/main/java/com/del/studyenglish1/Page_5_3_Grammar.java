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

public class Page_5_3_Grammar extends Fragment {

    private static final String ARG_TOPIC = "argTopic";
    private static final String ARG_TYPE = "argType";
    private static final String ARG_LEVEL_NAME = "argLevelName";
    private static final String ARG_LEVEL = "argLevel";

    private String topic;
    private String type;
    private String level_name;
    private String level;

    private TextView textViewTopic;
    private TextView textViewExplanation;
    private TextView changeLevel;
    private TextView changeType;
    private TextView changeTopic;
    private Button activities;

    private Page5 page5;
    private Page5_1 page5_1;
    private Page5_2 page5_2;
    private Page5_4_Grammar page5_4_grammar;

    public Page_5_3_Grammar() {
        // Required empty public constructor
    }

    public static Page_5_3_Grammar newInstance(String topic, String type, String level_name) {
        Page_5_3_Grammar fragment = new Page_5_3_Grammar();
        Bundle args = new Bundle();
        args.putString(ARG_TOPIC, topic);
        args.putString(ARG_TYPE, type);
        args.putString(ARG_LEVEL_NAME, level_name);
        //args.putString(ARG_LEVEL, level);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_page_5_3__grammar, container, false);
        textViewTopic = v.findViewById(R.id.text_view_topic);
        textViewExplanation = v.findViewById(R.id.text_view_explanation);
        textViewExplanation.setMovementMethod(new ScrollingMovementMethod());
        QuizDbHelper dbHelper = QuizDbHelper.getInstance(getContext());


        if (getArguments() != null) {
            topic = getArguments().getString(ARG_TOPIC);
            type = getArguments().getString(ARG_TYPE);
            level_name = getArguments().getString(ARG_LEVEL_NAME);
        }
        int topicId = dbHelper.getTopicId(topic, type, level_name);
        String explanation = dbHelper.getTopicInfo(topicId);
        textViewExplanation.setText(explanation);
        textViewTopic.setText(level_name + ": " + topic);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //changeLevel = (TextView) view.findViewById(R.id.text_view_change_level);
        //changeType = (TextView) view.findViewById(R.id.text_view_change_type);
        changeTopic = (TextView) view.findViewById(R.id.text_view_change_topic);
        activities = (Button) view.findViewById(R.id.button_activities);

        activities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivities(topic, type, level_name);
            }
        });

       changeTopic.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               changeTopicPage(type, level, level_name);
           }
       });
    }
    public void changeTopicPage(String type, String level, String level_name) {
        page5_2 = page5_2.newInstance(type, level, level_name);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, page5_2);
        fragmentTransaction.commit();
    }

    public void openActivities(String topic, String type, String level_name) {
        page5_4_grammar = page5_4_grammar.newInstance(topic, type, level_name);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, page5_4_grammar);
        fragmentTransaction.commit();
    }

}