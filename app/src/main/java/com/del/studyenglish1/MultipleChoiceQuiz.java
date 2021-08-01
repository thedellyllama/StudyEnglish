package com.del.studyenglish1;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MultipleChoiceQuiz extends Fragment {

    private static final String KEY_QUESTION_COUNT = "keyQuestionCount";
    private static final String KEY_ANSWERED = "keyAnswered";
    private static final String KEY_ANSWERED_CORRECTLY = "keyAnsweredCorrectly";
    private static final String KEY_ANSWER_ATTEMPTS = "keyAnswerAttempts";
    private static final String KEY_QUESTION_LIST = "keyQuestionList";
    private static final String ARG_TOPIC = "argTopic";
    private static final String ARG_TYPE = "argType";
    private static final String ARG_LEVEL_NAME = "argLevelName";
    private static final String ARG_LEVEL = "argLevel";
    private static final String ARG_TOPIC_ID = "argTopicID";
    private static final String ARG_ACTIVITY_NUM = "argActivityNum";

    private String topic;
    private String type;
    private String level_name;
    private String level;
    private int topicID;
    private int activity_num;

    private TextView textViewQuestion;
    private TextView textViewQuestionCount;
    private TextView textViewTopic;
    private TextView textViewLevel;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private Button buttonCheck;
    private ImageView buttonClose;
    private ColorStateList textColorDefaultRb;
    private ArrayList<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;
    private boolean answered;
    private boolean answeredCorrectly;
    private int answeredAttempts;
    private long backPressedTime;

    Page5_4_Grammar page5_4_grammar;
    InformationDialog informationDialog;
    private int buttonGreen;
    private int buttonBlue;

    private SQLiteDatabase newDb;

    public static MultipleChoiceQuiz newInstance(String topic, String type, String level_name, int activity_num) {
    //public static MultipleChoiceQuiz newInstance(int topicID, String topic, String type, String level_name, int activity_num) {
        MultipleChoiceQuiz fragment = new MultipleChoiceQuiz();
        Bundle args = new Bundle();
        args.putString(ARG_TOPIC, topic);
        args.putString(ARG_TYPE, type);
        args.putString(ARG_LEVEL_NAME, level_name);
        args.putInt(ARG_ACTIVITY_NUM, activity_num);
        //args.putInt(ARG_TOPIC_ID, topicID);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_multiple_choice_quiz, container, false);

        textViewQuestionCount = v.findViewById(R.id.text_view_question_count);
        textViewTopic = v.findViewById(R.id.text_view_topic_name);
        textViewLevel = v.findViewById(R.id.text_view_level_name);
        textViewQuestion = v.findViewById(R.id.text_view_question);
        rbGroup = v.findViewById(R.id.radioGroup);
        rb1 = v.findViewById(R.id.radio_button1);
        rb2 = v.findViewById(R.id.radio_button2);
        rb3 = v.findViewById(R.id.radio_button3);
        rb4 = v.findViewById(R.id.radio_button4);
        buttonCheck = v.findViewById(R.id.button_check);
        buttonClose = v.findViewById(R.id.button_close);
        textColorDefaultRb = rb1.getTextColors();
        buttonGreen = getResources().getColor(R.color.green_button);
        buttonBlue = getResources().getColor(R.color.blue_app);


        if (getArguments() != null) {
            topic = getArguments().getString(ARG_TOPIC);
            type = getArguments().getString(ARG_TYPE);
            level_name = getArguments().getString(ARG_LEVEL_NAME);
            activity_num = getArguments().getInt(ARG_ACTIVITY_NUM);
            //level = getArguments().getString(ARG_LEVEL);
            //topicID = getArguments().getInt(ARG_TOPIC_ID);
        }

        textViewTopic.setText("Topic: " + topic);
        textViewLevel.setText("Level: " + level_name);
/*
        QuizDbHelper dbHelper = QuizDbHelper.getInstance(getContext());
        //int topicID = dbHelper.getTopicId(topic, type, level_name);
        questionList = dbHelper.getQuestions(topicID);
        questionCountTotal = questionList.size();
        Collections.shuffle(questionList);
        showNextQuestion();
*/

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //if (savedInstanceState == null) {
            QuizDbHelper dbHelper = QuizDbHelper.getInstance(getContext());
            int topicID = dbHelper.getTopicId(topic, type, level_name);
            questionList = dbHelper.getQuestions(topicID, activity_num);
            questionCountTotal = questionList.size();
            showActivityDetails(questionCountTotal);
            Collections.shuffle(questionList);
            showNextQuestion();
        showActivityDetails(questionCountTotal);
        /*} else {
            questionList = savedInstanceState.getParcelableArrayList(KEY_QUESTION_LIST);
            questionCountTotal = questionList.size();
            questionCounter = savedInstanceState.getInt(KEY_QUESTION_COUNT);
            currentQuestion = questionList.get(questionCounter -1);
            answered = savedInstanceState.getBoolean(KEY_ANSWERED);
            answeredCorrectly = savedInstanceState.getBoolean(KEY_ANSWERED_CORRECTLY);
            answeredAttempts = savedInstanceState.getInt(KEY_ANSWER_ATTEMPTS);

            if (answeredAttempts > 1) {
                showSolution();
            }
        }*/
        buttonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!answered) {
                    //if (answeredAttempts < 2) {
                        if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()) {
                            answeredAttempts++;
                            checkAnswer();
                        } else {
                            Toast.makeText(getContext(), "Please select an answer", Toast.LENGTH_SHORT).show();
                        }
                    //} else {
                    //    showSolution();
                    //}
                } else {
                    showNextQuestion();
                }
            }
        });
        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page5_4_grammar = page5_4_grammar.newInstance(topic, type, level_name);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, page5_4_grammar);
                fragmentTransaction.commit();
            }
        });
    }

    private void showNextQuestion() {
        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);
        rb4.setTextColor(textColorDefaultRb);
        buttonCheck.setBackgroundColor(buttonBlue);
        rbGroup.clearCheck();

        if (questionCounter < questionCountTotal) {
            currentQuestion = questionList.get(questionCounter);

            textViewQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());
            rb4.setText(currentQuestion.getOption4());

            questionCounter++;
            textViewQuestionCount.setText("Question: " + questionCounter + "/" + questionCountTotal);
            answered = false;
            answeredCorrectly = false;
            answeredAttempts = 0;
            buttonCheck.setText("CHECK ANSWER");
        } else {
            finishQuiz(topic, type, level_name);
            //finishQuiz(topic, type, level_name);

        }
    }
    private void checkAnswer() {
        answered = true;

        RadioButton rbSelected = getActivity().findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr = rbGroup.indexOfChild(rbSelected) + 1;

        if (answerNr == currentQuestion.getAnswerNr()) {
            buttonCheck.setBackgroundColor(buttonGreen);
            //well done message
            showSolution(true);
            //set answered correctly in db to TRUE
        } else if (answeredAttempts > 1) {
            questionList.add(currentQuestion);
            questionCountTotal++;
            //no well done message
            showSolution(false);
            //set answered correctly in db to FALSE
            //add question to end of array

        } else {
            //answeredAttempts++;
            rbGroup.clearCheck();
            buttonCheck.setBackgroundColor(Color.RED);
            buttonCheck.setText("Incorrect.\nTry Again!");
            answered = false;
            rbSelected.setTextColor(Color.RED);
        }

    }

    private void showSolution(boolean answeredCorrectly) {
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);
        rb4.setTextColor(Color.RED);

        switch (currentQuestion.getAnswerNr()) {
            case 1:
                rb1.setTextColor(buttonGreen);
                //textViewQuestion.setText("Answer 1 is correct");
                break;
            case 2:
                rb2.setTextColor(buttonGreen);
                //textViewQuestion.setText("Answer 2 is correct");
                break;
            case 3:
                rb3.setTextColor(buttonGreen);
                //textViewQuestion.setText("Answer 3 is correct");
                break;
            case 4:
                rb4.setTextColor(buttonGreen);
                //textViewQuestion.setText("Answer 4 is correct");
                break;
        }

        if (questionCounter < questionCountTotal) {
            if (answeredCorrectly) {
                buttonCheck.setText("Correct!\nNext Question");
            } else {
                buttonCheck.setText("Next Question");
            }
        } else {
            buttonCheck.setText("Well done!\nFinish Quiz");
        }
    }

    private void finishQuiz(String topic, String type, String level_name) {

        /**update activities_completed column in db**/
        QuizDbHelper dbHelper = QuizDbHelper.getInstance(getContext());
        topicID = dbHelper.getTopicId(topic, type, level_name);
        dbHelper.activityCompleted(topicID, activity_num);
        dbHelper.activityCompletedTopics(topicID);
        dbHelper.updateActCount(topicID);
        //dbHelper.activityCompleted(topicID, activity_num);
        //dbHelper.updateActivitiesCompleted(topicID);

        page5_4_grammar = page5_4_grammar.newInstance(topic, type, level_name);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, page5_4_grammar);
        fragmentTransaction.commit();
        //page5_4_grammar.buttonTextUpdated(activity_num);


    }

    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            finishQuiz(topic, type, level_name);

            /**page5_4_grammar.newInstance(topic, type, level_name);
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.nav_host_fragment, page5_4_grammar);
            fragmentTransaction.commit();**/
        } else {
            Toast.makeText(getContext(), "Press back again to finish", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_QUESTION_COUNT, questionCounter);
        outState.putBoolean(KEY_ANSWERED, answered);
        outState.putBoolean(KEY_ANSWERED_CORRECTLY, answeredCorrectly);
        outState.putInt(KEY_ANSWER_ATTEMPTS, answeredAttempts);
        outState.putParcelableArrayList(KEY_QUESTION_LIST, questionList);
    }

    /**
     * method to show activity information: estimated time needed, number of questions
     */
    public void showActivityDetails(int questionCountTotal) {
        informationDialog = informationDialog.newInstance(questionCountTotal);
        informationDialog.show(getActivity().getSupportFragmentManager(), "example dialog");
    }

/*
    public MultipleChoiceQuiz() {
        // Required empty public constructor
    }*/

}