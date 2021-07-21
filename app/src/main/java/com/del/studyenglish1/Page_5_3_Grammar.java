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

        if (getArguments() != null) {
            topic = getArguments().getString(ARG_TOPIC);
            type = getArguments().getString(ARG_TYPE);
            level_name = getArguments().getString(ARG_LEVEL_NAME);
        }
        textViewTopic.setText(level_name + ": " + topic);

        return v;
    }
}