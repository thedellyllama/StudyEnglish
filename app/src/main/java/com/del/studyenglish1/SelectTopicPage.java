package com.del.studyenglish1;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
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

    private SelectTypePage selectTypePage;
    private TopicHomePage topicHomePage;

    private TextView textViewType;
    private ImageView backButton;
    private ListView listView;

    public SelectTopicPage() {}

    /**
     * Create and open new instance of Select Topic Page with selected arguments
     * @param type selected type
     * @param level selected level description
     * @param level_name selected level name
     * @return new Select Topic Page with given arguments
     */
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
        backButton = v.findViewById(R.id.button_back);

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

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                level_name = getArguments().getString(ARG_LEVEL_NAME);
                level = getArguments().getString(ARG_LEVEL);
                openSelectTypePage(level, level_name);
            }
        });
    }

    /**
     * Access data base to add all Topic names of selected type and level name
     * to listView
     */
    private void loadTopics() {
        type = getArguments().getString(ARG_TYPE);
        level_name = getArguments().getString(ARG_LEVEL_NAME);
        QuizDbHelper dbHelper = new QuizDbHelper(getActivity());
        dbHelper.getReadableDatabase();
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

    /**
     * Open new instance of TopicHomePage with selected arguments
     * @param topicSelected selected topic name
     * @param type selected type
     * @param level_name selected level name
     */
    public void loadTopicHomePage(String topicSelected, String type, String level_name) {
        topicHomePage = TopicHomePage.newInstance(topicSelected, type, level, level_name);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, topicHomePage);
        fragmentTransaction.commit();
    }

    /**
     * Open new Select Type Page
     * @param level selected level description
     * @param level_name selected level name
     */
    private void openSelectTypePage(String level, String level_name) {
        selectTypePage = selectTypePage.newInstance(level, level_name);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, selectTypePage);
        fragmentTransaction.commit();
    }
}