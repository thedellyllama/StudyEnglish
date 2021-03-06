package com.del.studyenglish1;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class ActivityHomePage extends Fragment {

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
    private ImageView backButton;
    private ImageView imageProgress;
    private Button activity1;
    private Button activity2;
    private Button activity3;
    private Button activity4;
    private int yellow;

    private TopicHomePage topicHomePage;
    private SelectTopicPage selectTopicPage;
    private MultipleChoiceQuiz multipleChoiceQuiz;
    private ReadingQuiz readingQuiz;
    private QuizDbHelper dbHelper;

    /**
     * Opens a new instance of the Activity Home Page using the given parameters
     * @param topic the selected topic name
     * @param type  the selected topic type
     * @param level the selected level description
     * @param level_name the selected level name as saved in the DB
     * @return new Activity Home Page
     */
    public static ActivityHomePage newInstance(String topic, String type, String level, String level_name) {
        ActivityHomePage fragment = new ActivityHomePage();
        Bundle args = new Bundle();
        args.putString(ARG_TOPIC, topic);
        args.putString(ARG_TYPE, type);
        args.putString(ARG_LEVEL_NAME, level_name);
        args.putString(ARG_LEVEL, level);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_activity_home_page, container, false);
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
        backButton = view.findViewById(R.id.button_back);

        updateButtonTexts();

        activity1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showActivityDetails();
                activityNum = 1;
                if (type.equals("READING")) {
                    openReadingQuiz(topic, type, level_name, activityNum);
                } else {
                    openMultipleChoiceQuiz(topic, type, level_name, activityNum);
                }
            }
        });

        activity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showActivityDetails();
                activityNum = 2;
                if (type.equals("READING")) {
                    openReadingQuiz(topic, type, level_name, activityNum);
                } else {
                    openMultipleChoiceQuiz(topic, type, level_name, activityNum);
                }            }
        });

        activity3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityNum = 3;
                if (type.equals("READING")) {
                    openReadingQuiz(topic, type, level_name, activityNum);
                } else {
                    openMultipleChoiceQuiz(topic, type, level_name, activityNum);
                }            }
        });

        activity4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showActivityDetails();
                activityNum = 4;
                if (type.equals("READING")) {
                    openReadingQuiz(topic, type, level_name, activityNum);
                } else {
                    openMultipleChoiceQuiz(topic, type, level_name, activityNum);
                }            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTopicHomePage(type, level, level_name);
            }
        });
    }

    /**
     * Opens a new instance of Topic Home Page with selected arguments
     * @param type the selected topic type
     * @param level the selected level description
     * @param level_name the selected level name
     */
    public void openTopicHomePage(String type, String level, String level_name) {
        topicHomePage = topicHomePage.newInstance(topic, type, level, level_name);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, topicHomePage);
        fragmentTransaction.commit();
    }

    /**
     * Opens a new instance of Multiple Choice Quiz with selected arguments
     * @param topic the selected topic name
     * @param type the selected topic type
     * @param level_name the selected level name
     * @param activity_num the activity number
     */
    public void openMultipleChoiceQuiz(String topic, String type, String level_name, int activity_num) {
        multipleChoiceQuiz = multipleChoiceQuiz.newInstance(topic, type, level_name, activity_num);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, multipleChoiceQuiz);
        fragmentTransaction.commit();
    }

    /**
     * Opens a new instance of Reading Quiz with selected arguments
     * @param topic the selected topic name
     * @param type the selected topic type
     * @param level_name the selected level name
     * @param activity_num the activity number
     */
    public void openReadingQuiz(String topic, String type, String level_name, int activity_num) {
        readingQuiz = readingQuiz.newInstance(topic, type, level_name, activity_num);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, readingQuiz);
        fragmentTransaction.commit();
    }

    /**
     * Shows progress by updating button text and colour after completing activity
     */
    public void updateButtonTexts() {
        dbHelper = QuizDbHelper.getInstance(getContext());
        int topicId = dbHelper.getTopicId(topic, type, level_name);

        if (dbHelper.checkCompleted(topicId, 1)) {
            activity1.setText("???");
            activity1.setBackgroundColor(yellow);
        }

        if (dbHelper.checkCompleted(topicId, 2)) {
            activity2.setText("???");
            activity2.setBackgroundColor(yellow);
        }
        if (dbHelper.checkCompleted(topicId, 3)) {
            activity3.setText("???");
            activity3.setBackgroundColor(yellow);
        }
        if (dbHelper.checkCompleted(topicId, 4)) {
            activity4.setText("???");
            activity4.setBackgroundColor(yellow);
        }
    }

    /**
     *method to update progress image based on number of activities completed
     * */
    public void updateProgressImage() {
        dbHelper = QuizDbHelper.getInstance(getContext());
        int topicId = dbHelper.getTopicId(topic, type, level_name);
        dbHelper.activityCompletedTopics(topicId);
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