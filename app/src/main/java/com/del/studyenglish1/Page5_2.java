package com.del.studyenglish1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Page5_2 extends Fragment {
    private static final String ARG_TYPE = "argType";
    private static final String ARG_LEVEL_NAME = "argLevelName";
    private static final String ARG_LEVEL = "argLevel";

    private String type;
    private String level_name;
    private String level;

    private Page5 page5;
    private Page5_1 page5_1;
    private TextView textViewType;
    private TextView changeLevel;
    private TextView changeType;
    private RecyclerView recyclerTopic;
    //TopicAdapter adapter;
    //ArrayList<Topic> topics = new ArrayList<>();
    private SQLiteDatabase newDb;
    private TopicAdapter adapter;


    public Page5_2() {
        // Required empty public constructor
    }

    public static Page5_2 newInstance(String type, String level, String level_name) {
        Page5_2 fragment = new Page5_2();
        Bundle args = new Bundle();
        args.putString(ARG_TYPE, type);
        args.putString(ARG_LEVEL_NAME, level_name);
        args.putString(ARG_LEVEL, level);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_page5_2, container, false);
        textViewType = v.findViewById(R.id.text_view_type);

        QuizDbHelper dbHelper = new QuizDbHelper(getActivity());
        newDb = dbHelper.getReadableDatabase();
        RecyclerView recyclerView = v.findViewById(R.id.recycler_topic);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new TopicAdapter(getActivity(), getAllItems());
       // adapter = new TopicAdapter(getActivity(), dbHelper.getTopics(type, level));

        recyclerView.setAdapter(adapter);

        if (getArguments() != null) {
            type = getArguments().getString(ARG_TYPE);
            level_name = getArguments().getString(ARG_LEVEL_NAME);
        }
        textViewType.setText(level_name + ": " + type);
        //newDb.getTopics(type, level_name);

        //loadTopicLevels();

        return v;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        changeLevel = (TextView) view.findViewById(R.id.text_view_change_level);
        changeType = (TextView) view.findViewById(R.id.text_view_change_type);

        changeLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page5 = new Page5();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, page5);
                fragmentTransaction.commit();
            }
        });

        changeType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                level_name = getArguments().getString(ARG_LEVEL_NAME);
                level = getArguments().getString(ARG_LEVEL);
                page5_1 = page5_1.newInstance(level, level_name);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, page5_1);
                fragmentTransaction.commit();
            }
        });
    }

    private Cursor getAllItems() {
        /*change variables here to get the topics we want!!!*/
        return newDb.query(
                QuizContract.TopicsTable.TABLE_NAME,
                new String[]{QuizContract.TopicsTable.COLUMN_NAME},
                null,
                null,
                null,
                null,
                QuizContract.TopicsTable.COLUMN_NAME + " DESC"
                );
    }
/**
    private void loadTopics() {
        type = getArguments().getString(ARG_TYPE);
        level_name = getArguments().getString(ARG_LEVEL_NAME);
        QuizDbHelper dbHelper = QuizDbHelper.getInstance(getActivity());
        List<Topic> topics = dbHelper.getTopics(type, level_name);

    }**/
/*
    private void loadTopicLevels() {
        QuizDbHelper dbHelper = QuizDbHelper.getInstance(getContext());
        List<Topic> topics = dbHelper.getAllTopics();

        ArrayAdapter<Topic> adapterTopic = new ArrayAdapter<>(getContext(), android.R.layout.simple_selectable_list_item, topics);
        adapterTopic.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        recyclerTopic.setAdapter(adapterTopic);

    }*/
}