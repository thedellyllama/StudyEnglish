package com.del.studyenglish1;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;


public class SelectTopicPage extends Fragment {
    private static final String ARG_TYPE = "argType";
    private static final String ARG_LEVEL_NAME = "argLevelName";
    private static final String ARG_LEVEL = "argLevel";

    private String type;
    private String level_name;
    private String level;
    private SQLiteDatabase newDb;

    private SelectLevelPage page5;
    private SelectTypePage page5_1;
    private ActivityHomePage activityHomePage;

    private TextView textViewType;
    private TextView changeLevel;
    private TextView changeType;
    private ListView listView;




    public SelectTopicPage() {}

    public static SelectTopicPage newInstance(String type, String level, String level_name) {
        SelectTopicPage fragment = new SelectTopicPage();
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
        View v = inflater.inflate(R.layout.fragment_select_topic, container, false);
        textViewType = v.findViewById(R.id.text_view_type);
        listView = (ListView) v.findViewById(R.id.recycler_topic);

        loadTopics();

        if (getArguments() != null) {
            type = getArguments().getString(ARG_TYPE);
            level_name = getArguments().getString(ARG_LEVEL_NAME);
            level = getArguments().getString(ARG_LEVEL);
        }
        textViewType.setText(level_name + ": " + type);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        changeLevel = (TextView) view.findViewById(R.id.text_view_change_level);
        changeType = (TextView) view.findViewById(R.id.text_view_change_type);
        listView = (ListView) view.findViewById(R.id.recycler_topic);

        changeLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeLevelPage();
            }
        });

        changeType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                level_name = getArguments().getString(ARG_LEVEL_NAME);
                level = getArguments().getString(ARG_LEVEL);
               changeTypePage(level, level_name);
            }
        });
    }
    /**
     * @return Cursor finds all topics filtered by chosen type and level
     */

    private void loadTopics() {
        type = getArguments().getString(ARG_TYPE);
        level_name = getArguments().getString(ARG_LEVEL_NAME);
        QuizDbHelper dbHelper = new QuizDbHelper(getActivity());
        newDb = dbHelper.getReadableDatabase();
        ArrayList<Topic> topicList = dbHelper.getTopics(type, level_name);
        MyTopicAdapter myTopicAdapter = new MyTopicAdapter(getContext(), topicList);
        listView.setAdapter(myTopicAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    String topicSelected = listView.getItemAtPosition(position).toString();
                    loadTopicHomePage(topicSelected, type, level_name);
            }
        });
    }

    public void loadTopicHomePage(String topicSelected, String type, String level_name) {
        TopicHomePage fragment = TopicHomePage.newInstance(topicSelected, type, level, level_name);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
        fragmentTransaction.commit();
    }

    private void changeLevelPage() {
        page5 = new SelectLevelPage();
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, page5);
        fragmentTransaction.commit();
    }

    private void changeTypePage(String level, String level_name) {
        page5_1 = page5_1.newInstance(level, level_name);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, page5_1);
        fragmentTransaction.commit();
    }
}