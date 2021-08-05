package com.del.studyenglish1;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class Page5_2 extends Fragment {
    private static final String ARG_TYPE = "argType";
    private static final String ARG_LEVEL_NAME = "argLevelName";
    private static final String ARG_LEVEL = "argLevel";

    private String type;
    private String level_name;
    private String level;

    private Page5 page5;
    private Page5_1 page5_1;
    private Page_5_3_Grammar page_5_3_grammar;
    private Page5_4_Grammar page5_4_grammar;
    private TextView textViewType;
    private TextView changeLevel;
    private TextView changeType;
    private SQLiteDatabase newDb;
    private ListView recyclerView;
    private ListView listView;
    private MyTopicAdapter myTopicAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    //private ArrayList<Topic> mTopics = new ArrayList<>();



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
        listView = (ListView) v.findViewById(R.id.recycler_topic);

        QuizDbHelper dbHelper = new QuizDbHelper(getActivity());
        newDb = dbHelper.getReadableDatabase();

        loadTopics();

        if (getArguments() != null) {
            type = getArguments().getString(ARG_TYPE);
            level_name = getArguments().getString(ARG_LEVEL_NAME);
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
        QuizDbHelper dbHelper = QuizDbHelper.getInstance(getContext());
        ArrayList<Topic> topicList = dbHelper.getTopics(type, level_name);

        MyTopicAdapter myTopicAdapter = new MyTopicAdapter(getContext(), topicList);
        listView.setAdapter(myTopicAdapter);

        Button topicButton = (Button) listView.findViewById(R.id.button_topic_item);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                    String topicSelected = listView.getItemAtPosition(position).toString();
                    loadGrammarHomePage(topicSelected, type, level_name);
            }
        });

    }

    public void loadGrammarHomePage(String topicSelected, String type, String level_name) {
        Page_5_3_Grammar fragment = Page_5_3_Grammar.newInstance(topicSelected, type, level_name);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
        fragmentTransaction.commit();
    }

    private void changeLevelPage() {
        page5 = new Page5();
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