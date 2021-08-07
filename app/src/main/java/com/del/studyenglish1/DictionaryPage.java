package com.del.studyenglish1;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class DictionaryPage extends Fragment {

    private Spinner spinnerTopic;
    private Button buttonA1;
    private Button buttonA2;
    private Button buttonB1;
    private Button buttonB2;
    private Button buttonC1;
    private Button buttonSearch;
    private Drawable blueBg;
    private Drawable blueAllBg;
    private ListView listView;
    private TextView textViewTopic;
    private int blueApp;

    private String selectedLevel;
    private String selectedTopic;
    private int topicId;
    private List<Vocabulary> newVocabList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dictionary_page, container, false);

        spinnerTopic = view.findViewById(R.id.spinner_topics);
        buttonA1 = view.findViewById(R.id.button_a1);
        buttonA2 = view.findViewById(R.id.button_a2);
        buttonB1 = view.findViewById(R.id.button_b1);
        buttonB2 = view.findViewById(R.id.button_b2);
        buttonC1 = view.findViewById(R.id.button_c1);
        buttonSearch = view.findViewById(R.id.button_search);
        listView = view.findViewById(R.id.list_view_vocab);
        textViewTopic = view.findViewById(R.id.text_view_topic_name);
        blueBg = getResources().getDrawable(R.drawable.blue_button_bg);
        blueAllBg = getResources().getDrawable(R.drawable.blue_button_all_bg);
        blueApp = getResources().getColor(R.color.blue_app);

        loadAllTopics();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        buttonA1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedLevel = "A1";
                resetButtonColours();
                changeSelectedColour(buttonA1);
                loadTopics(selectedLevel);
            }
        });

        buttonA2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedLevel = String.valueOf(buttonA2.getText());
                loadTopics(selectedLevel);
                resetButtonColours();
                changeSelectedColour(buttonA2);
            }
        });

        buttonB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedLevel = String.valueOf(buttonB1.getText());
                loadTopics(selectedLevel);
                resetButtonColours();
                changeSelectedColour(buttonB1);
            }
        });

        buttonB2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedLevel = String.valueOf(buttonB2.getText());
                loadTopics(selectedLevel);
                resetButtonColours();
                changeSelectedColour(buttonB2);
            }
        });

        buttonC1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedLevel = String.valueOf(buttonC1.getText());
                loadTopics(selectedLevel);
                resetButtonColours();
                changeSelectedColour(buttonC1);
            }
        });

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDictionary();
            }
        });
    }

    public void loadAllTopics() {
        QuizDbHelper dbHelper = QuizDbHelper.getInstance(getContext());
        List<Topic> topics = dbHelper.getAllVocabTopics();

        ArrayAdapter<Topic> adapterTopic = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, topics);

        adapterTopic.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTopic.setAdapter(adapterTopic);
    }

    public void loadTopics(String selectedLevel) {
        QuizDbHelper dbHelper = QuizDbHelper.getInstance(getContext());
        List<Topic> topics = dbHelper.getTopics("VOCABULARY", selectedLevel);

        ArrayAdapter<Topic> adapterTopic = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, topics);

        adapterTopic.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTopic.setAdapter(adapterTopic);
    }

    public void resetButtonColours() {
        buttonA1.setBackground(blueBg);
        buttonA2.setBackground(blueBg);
        buttonB1.setBackground(blueBg);
        buttonB2.setBackground(blueBg);
        buttonC1.setBackground(blueBg);
        buttonA1.setTextColor(blueApp);
        buttonA2.setTextColor(blueApp);
        buttonB1.setTextColor(blueApp);
        buttonB2.setTextColor(blueApp);
        buttonC1.setTextColor(blueApp);
    }

    public void changeSelectedColour(Button selectedButton) {
        selectedButton.setBackground(blueAllBg);
        selectedButton.setTextColor(Color.WHITE);
    }

    public void showDictionary() {
        //get topic id from selected spinner item
        Topic topic = (Topic) spinnerTopic.getSelectedItem();
        selectedTopic = topic.getName();
        topicId = topic.getId();
        textViewTopic.setText(selectedTopic);
        QuizDbHelper dbHelper = QuizDbHelper.getInstance(getContext());
        ArrayList<Vocabulary> newVocabList = dbHelper.getVocab(topicId);
        MyVocabAdapter myVocabAdapter = new MyVocabAdapter(getContext(), newVocabList);
        listView.setAdapter(myVocabAdapter);
    }
}
