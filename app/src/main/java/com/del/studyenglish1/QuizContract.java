package com.del.studyenglish1;

import android.provider.BaseColumns;

public final class QuizContract {
    //empty constructor to stop instances being created
    private QuizContract() {}

    public static class TopicsTable implements BaseColumns {
        public static final String TABLE_NAME = "quiz_topics";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DIFFICULTY = "difficulty";
        public static final String COLUMN_TYPE = "type";
    }

    public static class QuestionsTable implements BaseColumns {
        public static final String TABLE_NAME = "quiz_questions";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_OPTION1 = "option1";
        public static final String COLUMN_OPTION2 = "option2";
        public static final String COLUMN_OPTION3 = "option3";
        public static final String COLUMN_ANSWER_NR = "answer_nr";
        public static final String COLUMN_DIFFICULTY = "difficulty";
        public static final String COLUMN_TOPIC_ID = "topic_id";
    }

}
