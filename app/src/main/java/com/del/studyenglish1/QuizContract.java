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
        public static final String COLUMN_ACT_COUNT = "activities_count";
        public static final String COLUMN_ACT_COMPLETED = "activities_completed";
    }

    public static class QuestionsTable implements BaseColumns {
        public static final String TABLE_NAME = "quiz_questions";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_OPTION1 = "option1";
        public static final String COLUMN_OPTION2 = "option2";
        public static final String COLUMN_OPTION3 = "option3";
        public static final String COLUMN_OPTION4 = "option4";
        public static final String COLUMN_ANSWER_NUM = "answer_num";
        //public static final String COLUMN_DIFFICULTY = "difficulty";
        public static final String COLUMN_TOPIC_ID = "topic_id";
        public static final String COLUMN_ACT_NUM = "activity_num";
    }

    public static class ActivityTable implements BaseColumns {
        public static final String TABLE_NAME = "quiz_activities";
        public static final String COLUMN_TOPIC_ID = "topic_id";
        public static final String COLUMN_ACT_NUM = "activity_num";
        public static final String COLUMN_COMPLETED= "activity_completed";
    }

    public static class GrammarTable implements BaseColumns {
        public static final String TABLE_NAME = "information_grammar";
        public static final String COLUMN_TOPIC_ID = "topic_id";
        public static final String COLUMN_EXPLANATION = "explanation";
    }

    public static class VocabTable implements BaseColumns {
        public static final String TABLE_NAME = "information_vocabulary";
        public static final String COLUMN_TOPIC_ID = "topic_id";
        public static final String COLUMN_NAME = "new_vocabulary";
        public static final String COLUMN_DEFINITION = "definition";

    }

}
