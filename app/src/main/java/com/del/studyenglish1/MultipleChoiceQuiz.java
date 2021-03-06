package com.del.studyenglish1;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.Collections;

public class MultipleChoiceQuiz extends Fragment {

    private static final String ARG_TOPIC = "argTopic";
    private static final String ARG_TYPE = "argType";
    private static final String ARG_LEVEL_NAME = "argLevelName";
    private static final String ARG_ACTIVITY_NUM = "argActivityNum";

    private String topic;
    private String type;
    private String level_name;
    private String level;
    private int topicID;
    private int activity_num;
    private ArrayList<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;
    private boolean answered;
    private boolean answeredCorrectly;
    private int answeredAttempts;

    ActivityHomePage activityHomePage;
    InformationDialog informationDialog;

    private TextView textViewQuestion;
    private TextView textViewQuestionCount;
    private TextView textViewTopic;
    private TextView textViewLevel;
    private TextView textViewInstruction;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private Button buttonCheck;
    private ImageView buttonClose;
    private ImageView imageQuestion;
    private ColorStateList textColorDefaultRb;
    private int buttonGreen;
    private int buttonBlue;

    /**
     * Creates and opens new instance of Multiple Choice Quiz
     * @param topic selected topic name
     * @param type selected activity type
     * @param level_name selected level name
     * @param activity_num selected activity number
     * @return
     */
    public static MultipleChoiceQuiz newInstance(String topic, String type, String level_name, int activity_num) {
        MultipleChoiceQuiz fragment = new MultipleChoiceQuiz();
        Bundle args = new Bundle();
        args.putString(ARG_TOPIC, topic);
        args.putString(ARG_TYPE, type);
        args.putString(ARG_LEVEL_NAME, level_name);
        args.putInt(ARG_ACTIVITY_NUM, activity_num);
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
        textViewInstruction = v.findViewById(R.id.text_view_instruction);
        imageQuestion = v.findViewById(R.id.image_view_question);
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
        }

        textViewTopic.setText("Topic: " + topic);
        textViewLevel.setText("Level: " + level_name);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
            getQuestionInformation();
            showActivityDetails(questionCountTotal);
            Collections.shuffle(questionList);
            showNextQuestion();
        showActivityDetails(questionCountTotal);

        buttonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!answered) {
                        if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()) {
                            answeredAttempts++;
                            checkAnswer();
                        } else {
                            Toast.makeText(getContext(), "Please select an answer", Toast.LENGTH_SHORT).show();
                        }
                } else {
                    showNextQuestion();
                }
            }
        });
        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityHome();
            }
        });
    }

    /**
     * Open dialog to show activity information: estimated time needed, number of questions
     */
    public void showActivityDetails(int questionCountTotal) {
        informationDialog = informationDialog.newInstance(questionCountTotal);
        informationDialog.show(getActivity().getSupportFragmentManager(), "example dialog");
    }

    /**
     * Access database to get the list of questions and get the list size
     */
    public void getQuestionInformation(){
        QuizDbHelper dbHelper = QuizDbHelper.getInstance(getContext());
        int topicID = dbHelper.getTopicId(topic, type, level_name);
        questionList = dbHelper.getQuestions(topicID, activity_num);
        questionCountTotal = questionList.size();
    }

    /**
     * Load the next question onto page and update all text colours and buttons
     */
    public void showNextQuestion() {
        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);
        rb4.setTextColor(textColorDefaultRb);
        buttonCheck.setBackgroundColor(buttonBlue);
        rbGroup.clearCheck();
        imageQuestion.setVisibility(View.INVISIBLE);
        textViewInstruction.setText("");
        textViewQuestion.setText("");

        if (questionCounter < questionCountTotal) {
            currentQuestion = questionList.get(questionCounter);

            if (currentQuestion.getImageRef() != 0) {
                Drawable imageQ = getResources().getDrawable(currentQuestion.getImageRef());
                imageQuestion.setImageDrawable(imageQ);
                imageQuestion.setVisibility(View.VISIBLE);
            }
            if (currentQuestion.getQuestion() != null) {
                textViewQuestion.setText(currentQuestion.getQuestion());
                textViewInstruction.setText(currentQuestion.getInstruction());
            } else {
                textViewInstruction.setText(currentQuestion.getInstruction());
            }

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
        }
    }

    /**
     * Check if the correct answer is given, an incorrect answer is given for the first or second
     * time. Call showSolution() and/or update all text views and buttons and DB accordingly.
     */
    public void checkAnswer() {
        answered = true;

        RadioButton rbSelected = getActivity().findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr = rbGroup.indexOfChild(rbSelected) + 1;

        if (answerNr == currentQuestion.getAnswerNr()) {
            buttonCheck.setBackgroundColor(buttonGreen);
            showSolution(true);
        } else if (answeredAttempts > 1) {
            questionList.add(currentQuestion);
            questionCountTotal++;
            showSolution(false);

        } else {
            rbGroup.clearCheck();
            buttonCheck.setBackgroundColor(Color.RED);
            buttonCheck.setText("Incorrect.\nTry Again!");
            answered = false;
            rbSelected.setTextColor(Color.RED);
        }
    }

    /**
     * Update text colour and button colour/message accordingly
     * @param answeredCorrectly true if the question has been answered correctly after two
     *                          attempts, false otherwise.
     */
    public void showSolution(boolean answeredCorrectly) {
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);
        rb4.setTextColor(Color.RED);

        switch (currentQuestion.getAnswerNr()) {
            case 1:
                rb1.setTextColor(buttonGreen);
                break;
            case 2:
                rb2.setTextColor(buttonGreen);
                break;
            case 3:
                rb3.setTextColor(buttonGreen);
                break;
            case 4:
                rb4.setTextColor(buttonGreen);
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

    /**
     * Update database to show activity has been completed and open new instance
     * of Activity Home Page.
     * @param topic selected topic name
     * @param type  selected activity type
     * @param level_name selected level name
     */
    public void finishQuiz(String topic, String type, String level_name) {
        QuizDbHelper dbHelper = QuizDbHelper.getInstance(getContext());
        topicID = dbHelper.getTopicId(topic, type, level_name);
        dbHelper.activityCompleted(topicID, activity_num);
        dbHelper.activityCompletedTopics(topicID);
        dbHelper.updateActCount(topicID);

        activityHomePage = activityHomePage.newInstance(topic, type, level, level_name);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, activityHomePage);
        fragmentTransaction.commit();
   }

    /**
     * Open new instance of Activity Home with selected topic, type, level, level_name
     */
    public void openActivityHome() {
        activityHomePage = activityHomePage.newInstance(topic, type, level, level_name);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, activityHomePage);
        fragmentTransaction.commit();
    }
}