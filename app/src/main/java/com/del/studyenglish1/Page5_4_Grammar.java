package com.del.studyenglish1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Page5_4_Grammar extends Fragment {

    private static final String ARG_TOPIC = "argTopic";
    private static final String ARG_TYPE = "argType";
    private static final String ARG_LEVEL_NAME = "argLevelName";
    private static final String ARG_LEVEL = "argLevel";

    private String topic;
    private String type;
    private String level_name;
    private String level;

    private TextView textViewTopic;
    private TextView changeTopic;
    private TextView toExplanation;

    private Page_5_3_Grammar page_5_3_grammar;
    private Page5_2 page5_2;

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

        if (getArguments() != null) {
            topic = getArguments().getString(ARG_TOPIC);
            type = getArguments().getString(ARG_TYPE);
            level_name = getArguments().getString(ARG_LEVEL_NAME);
            level = getArguments().getString(ARG_LEVEL);
        }
        textViewTopic.setText(topic);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        changeTopic = (TextView) view.findViewById(R.id.text_view_change_topic);
        toExplanation = (TextView) view.findViewById(R.id.text_view_explanation);

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
}