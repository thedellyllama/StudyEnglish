package com.del.studyenglish1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import com.del.studyenglish1.QuizContract.*;

public class QuizDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "StudyEnglish.db";
    public static final int DATABASE_VERSION = 1;

    private static QuizDbHelper instance;
    private SQLiteDatabase db;

    public QuizDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /*
     * singleton: ensures only 1 database connection can be made to avoid any memory leaks
     * synchronised in case we want to access it from other methods
     * static so we don't have to create a quizDbHelper object first
     */
    public static synchronized QuizDbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new QuizDbHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_TOPICS_TABLE = "CREATE TABLE " +
                TopicsTable.TABLE_NAME + "( " +
                TopicsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TopicsTable.COLUMN_NAME + " TEXT, " +
                TopicsTable.COLUMN_DIFFICULTY + " TEXT, " +
                TopicsTable.COLUMN_TYPE + " TEXT, " +
                TopicsTable.COLUMN_ACT_COUNT + " INTEGER, " +
                TopicsTable.COLUMN_ACT_COMPLETED + " INTEGER" +
                ")";

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NUM + " INTEGER, " +
                QuestionsTable.COLUMN_TOPIC_ID + " INTEGER, " +
                QuestionsTable.COLUMN_ACT_NUM + " INTEGER, " +
                "FOREIGN KEY(" + QuestionsTable.COLUMN_TOPIC_ID + ") REFERENCES " +
                TopicsTable.TABLE_NAME + "(" + TopicsTable._ID + ") ON DELETE CASCADE " +
              // " FOREIGN KEY(" + QuestionsTable.COLUMN_ACT_NUM + ") REFERENCES " +
               //ActivityTable.TABLE_NAME + "(" + TopicsTable._ID + ")" +
                ")";

        final String SQL_CREATE_ACTIVITY_TABLE = "CREATE TABLE " +
                ActivityTable.TABLE_NAME + " ( " +
                ActivityTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ActivityTable.COLUMN_TOPIC_ID +  " INTEGER, " +
                ActivityTable.COLUMN_ACT_NUM + " INTEGER, " +
                ActivityTable.COLUMN_COMPLETED + " BOOLEAN, " +
                ActivityTable.COLUMN_TIME_STAMP + " DATE, " +
                "FOREIGN KEY(" + ActivityTable.COLUMN_TOPIC_ID + ") REFERENCES " +
                TopicsTable.TABLE_NAME + "(" + TopicsTable._ID + ") ON DELETE CASCADE" +
                ")";

        final String SQL_CREATE_GRAMMAR_TABLE = "CREATE TABLE " +
                GrammarTable.TABLE_NAME + " ( " +
                GrammarTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                GrammarTable.COLUMN_TOPIC_ID +  " INTEGER, " +
                GrammarTable.COLUMN_EXPLANATION + " STRING, " +
                "FOREIGN KEY(" + ActivityTable.COLUMN_TOPIC_ID + ") REFERENCES " +
                TopicsTable.TABLE_NAME + "(" + TopicsTable._ID + ") ON DELETE CASCADE" +
                ")";

        final String SQL_CREATE_VOCAB_TABLE = "CREATE TABLE " +
                VocabTable.TABLE_NAME + " ( " +
                VocabTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                VocabTable.COLUMN_TOPIC_ID +  " INTEGER, " +
                VocabTable.COLUMN_NAME + " STRING, " +
                VocabTable.COLUMN_DEFINITION + " STRING, " +
                "FOREIGN KEY(" + ActivityTable.COLUMN_TOPIC_ID + ") REFERENCES " +
                TopicsTable.TABLE_NAME + "(" + TopicsTable._ID + ") ON DELETE CASCADE" +
                ")";

        final String SQL_CREATE_GOALS_TABLE = "CREATE TABLE " +
                GoalsTable.TABLE_NAME + " ( " +
                GoalsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                GoalsTable.COLUMN_GOAL_ACTIVITY + " INTEGER, " +
                GoalsTable.COLUMN_GOAL_TIME_FRAME + " STRING" +
                ")";


        db.execSQL(SQL_CREATE_TOPICS_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        db.execSQL(SQL_CREATE_ACTIVITY_TABLE);
        db.execSQL(SQL_CREATE_GRAMMAR_TABLE);
        db.execSQL(SQL_CREATE_VOCAB_TABLE);
        db.execSQL(SQL_CREATE_GOALS_TABLE);

        fillTopicsTable();
        fillQuestionsTable();
        fillActivityTable();
        fillGrammarTable();
        fillVocabTable();
        fillGoalsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TopicsTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ActivityTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + GrammarTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + VocabTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + GoalsTable.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    private void fillTopicsTable() {
        Topic t1 = new Topic("PRESENT SIMPLE - TO BE", Topic.DIFFICULTY_A1, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        Topic t2 = new Topic("POSSESSIVE ADJECTIVES & SUBJECT PRONOUNS", Topic.DIFFICULTY_A1, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        Topic t3 = new Topic("ADJECTIVES", Topic.DIFFICULTY_A1, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        Topic t4 = new Topic("QUESTIONS", Topic.DIFFICULTY_A1, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        Topic t5 = new Topic("ADVERBS OF FREQUENCY", Topic.DIFFICULTY_A1, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        Topic t6 = new Topic("PREPOSITIONS OF TIME", Topic.DIFFICULTY_A1, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        Topic t7 = new Topic("PREPOSITIONS OF PLACE", Topic.DIFFICULTY_A1, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        Topic t8 = new Topic("PRESENT CONTINUOUS", Topic.DIFFICULTY_A1, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        Topic t9 = new Topic("IMPERATIVE", Topic.DIFFICULTY_A1, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        Topic t10 = new Topic("PAST SIMPLE - TO BE", Topic.DIFFICULTY_A1, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        Topic t11 = new Topic("PAST SIMPLE", Topic.DIFFICULTY_A1, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        Topic t12 = new Topic("PAST SIMPLE - NEGATIVES & QUESTIONS", Topic.DIFFICULTY_A1, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        Topic t13 = new Topic("VERBS + TO + INFINITIVE & VERBS + ING", Topic.DIFFICULTY_A1, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        Topic t14 = new Topic("COUNTABLE & UNCOUNTABLE NOUNS", Topic.DIFFICULTY_A1, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);


        Topic t15 = new Topic("Vocab Topic 1", Topic.DIFFICULTY_A1, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic t16 = new Topic("Vocab Topic 2", Topic.DIFFICULTY_A1, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic t17 = new Topic("Vocab Topic 3", Topic.DIFFICULTY_A1, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic t18 = new Topic("Vocab Topic 4", Topic.DIFFICULTY_A1, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic t19 = new Topic("Vocab Topic 5", Topic.DIFFICULTY_A2, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic t20 = new Topic("Vocab Topic 6", Topic.DIFFICULTY_A2, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic t21 = new Topic("Vocab Topic 7", Topic.DIFFICULTY_A2, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic t22 = new Topic("Vocab Topic 8", Topic.DIFFICULTY_A2, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic t23 = new Topic("Vocab Topic 9", Topic.DIFFICULTY_A2, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic t24 = new Topic("Vocab Topic 10", Topic.DIFFICULTY_A2, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic t25 = new Topic("Vocab Topic 11", Topic.DIFFICULTY_A2, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic t26 = new Topic("Vocab Topic 12", Topic.DIFFICULTY_A2, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic t27 = new Topic("Vocab Topic 13", Topic.DIFFICULTY_C1, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);


        addTopic(t1);
        addTopic(t2);
        addTopic(t3);
        addTopic(t4);
        addTopic(t5);
        addTopic(t6);
        addTopic(t7);
        addTopic(t8);
        addTopic(t9);
        addTopic(t10);
        addTopic(t11);
        addTopic(t12);
        addTopic(t13);
        addTopic(t14);

        addTopic(t15);
        addTopic(t16);
        addTopic(t17);
        addTopic(t18);
        addTopic(t19);
        addTopic(t20);
        addTopic(t21);
        addTopic(t22);
        addTopic(t23);
        addTopic(t24);
        addTopic(t25);
        addTopic(t26);
        addTopic(t27);

    }

    private void addTopic(Topic topic) {
        ContentValues cv = new ContentValues();
        cv.put(TopicsTable.COLUMN_NAME, topic.getName());
        cv.put(TopicsTable.COLUMN_DIFFICULTY, topic.getDifficulty());
        cv.put(TopicsTable.COLUMN_TYPE, topic.getType());
        db.insert(TopicsTable.TABLE_NAME, null, cv);
    }

    private void fillActivityTable() {

        Activity a1 = new Activity(Topic.t5_ID, Activity.ACT_NUM_1, Activity.FALSE);
        Activity a2 = new Activity(Topic.t5_ID, Activity.ACT_NUM_2, Activity.FALSE);
        Activity a3 = new Activity(Topic.t5_ID, Activity.ACT_NUM_3, Activity.FALSE);
        Activity a4 = new Activity(Topic.t5_ID, Activity.ACT_NUM_4, Activity.FALSE);

        addActivity(a1);
        addActivity(a2);
        addActivity(a3);
        addActivity(a4);

    }

    private void addActivity(Activity activity) {
        ContentValues cv = new ContentValues();
        cv.put(ActivityTable.COLUMN_TOPIC_ID, activity.getTopicId());
        cv.put(ActivityTable.COLUMN_ACT_NUM, activity.getActivityNum());
        cv.put(ActivityTable.COLUMN_COMPLETED, activity.getCompleted());
        db.insert(ActivityTable.TABLE_NAME, null, cv);
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("Select the correct phrase:", "Our teacher is often late.", "Our teacher often is late.", "Is often our teacher late?", "Often our teacher is late", 1, Topic.t5_ID, Activity.ACT_NUM_1);
        Question q2 = new Question("Select the correct phrase:", "I am tired always.", "I am always tired.", "Always I am tired.", "Tired I am always.", 2, Topic.t5_ID, Activity.ACT_NUM_1);
        Question q3 = new Question("Select the correct phrase:", "My sister watches TV hardly ever.", "My sister doesn't hardly ever watch TV.", "My sister watches TV hardly ever.", "My sister hardly ever watches TV.", 4, Topic.t5_ID, Activity.ACT_NUM_1);
        Question q4 = new Question("Select the correct phrase:", "We never eat sushi.", "We eat sushi never.", "We eat never sushi.", "Never we eat sushi.", 1, Topic.t5_ID, Activity.ACT_NUM_1);
        Question q5 = new Question("Select the correct phrase:", "English they study every day.", "They study every day English.", "They every day study English.", "They study English every day.", 4, Topic.t5_ID, Activity.ACT_NUM_1);
        Question q6 = new Question("Select the correct phrase:", "He doesn't wake up early usually.", "Does he wake up usually early?", "He doesn't usually wake up early.", "He usually doesn't wake up early.", 3, Topic.t5_ID, Activity.ACT_NUM_1);
        Question q7 = new Question("Select the correct phrase:", "Do you go to the cinema often?", "Do you go often to the cinema?", "Do often you go to the cinema?", "Do you often go to the cinema?", 1, Topic.t5_ID, Activity.ACT_NUM_1);
        Question q8 = new Question("Select the correct phrase:", "I always wears a hat", "I always wear a hat", "I wear a hat always", "I wear always a hat", 2, Topic.t5_ID, Activity.ACT_NUM_1);
        Question q9 = new Question("Select the incorrect phrase:", "He doesn't wake up early usually.", "I always wear a hat", "Do often you go to the cinema?", "I am always tired.", 1, Topic.t5_ID, Activity.ACT_NUM_2);

        addQuestion(q1);
        addQuestion(q2);
        addQuestion(q3);
        addQuestion(q4);
        addQuestion(q5);
        addQuestion(q6);
        addQuestion(q7);
        addQuestion(q8);
        addQuestion(q9);

    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_OPTION4, question.getOption4());
        cv.put(QuestionsTable.COLUMN_ANSWER_NUM, question.getAnswerNr());
        cv.put(QuestionsTable.COLUMN_TOPIC_ID, question.getTopicId());
        cv.put(QuestionsTable.COLUMN_ACT_NUM, question.getActivityNum());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }



    private void fillGrammarTable() {
        Grammar g1 = new Grammar(Topic.t5_ID, "Adverbs of frequency describe how often something happens.\n Subject \u002B Adverb \u002B Main Verb\n Sam always passes his exams.\n Subject \u002B be/have \u002B Adverb\n She is usually busy on Saturdays.\n\n always\n usually/normally\n often\n sometimes\n occasionally\n hardly ever\n never\n\n every day\n every week \n every month\n every year\n once a week\n twice a month\n three times a day\n");
        addGrammar(g1);

    }

    private void addGrammar(Grammar grammar) {
        ContentValues cv = new ContentValues();
        cv.put(GrammarTable.COLUMN_TOPIC_ID, grammar.getTopicId());
        cv.put(GrammarTable.COLUMN_EXPLANATION, grammar.getExplanation());
        db.insert(GrammarTable.TABLE_NAME, null, cv);
    }

    private void fillVocabTable() {

    }

    private void addVocab(Vocabulary vocabulary) {
        ContentValues cv = new ContentValues();
        cv.put(VocabTable.COLUMN_TOPIC_ID, vocabulary.getTopicId());
        cv.put(VocabTable.COLUMN_NAME, vocabulary.getName());
        cv.put(VocabTable.COLUMN_DEFINITION, vocabulary.getDefinition());
        db.insert(VocabTable.TABLE_NAME, null, cv);
    }

    private void fillGoalsTable() {
        Goal userGoal = new Goal(5, Goal.DAILY);
        addGoals(userGoal);
    }

    public void addGoals(Goal goal) {
        ContentValues cv = new ContentValues();
        cv.put(GoalsTable.COLUMN_GOAL_ACTIVITY, goal.getGoalActivities());
        cv.put(GoalsTable.COLUMN_GOAL_TIME_FRAME, goal.getGoalTimeframe());
        db.insert(GoalsTable.TABLE_NAME, null, cv);
    }

    public ArrayList<Topic> getAllVocabTopics() {
        ArrayList<Topic> topicList = new ArrayList<>();
        db = getReadableDatabase();
        String query = "SELECT * FROM " + TopicsTable.TABLE_NAME + " WHERE "
                + TopicsTable.COLUMN_TYPE + " = '" + Topic.TYPE_VOCABULARY +"'"
                ;
                Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                Topic topic = new Topic();
                topic.setId(c.getInt(c.getColumnIndex(TopicsTable._ID)));
                topic.setName(c.getString(c.getColumnIndex(TopicsTable.COLUMN_NAME)));
                //topic.setDifficulty(c.getString(c.getColumnIndex(TopicsTable.COLUMN_DIFFICULTY)));
                topic.setType(c.getString(c.getColumnIndex(TopicsTable.COLUMN_TYPE)));
                topic.setActivitiesCount(c.getInt(c.getColumnIndex(TopicsTable.COLUMN_ACT_COUNT)));
                topic.setActivitiesCompleted(c.getInt(c.getColumnIndex(TopicsTable.COLUMN_ACT_COMPLETED)));
                topicList.add(topic);
            } while (c.moveToNext());
        }
        c.close();
        return topicList;
    }

    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex((QuestionsTable._ID))));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NUM)));
                //question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setTopicId(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_TOPIC_ID)));
                question.setActivityNum(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ACT_NUM)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }

    public ArrayList<Topic> getTopics(String type, String difficulty) {
        ArrayList<Topic> topicList = new ArrayList<>();
        db = getReadableDatabase();

        //filter difficulty and type
        String[] selectionArgs = new String[]{type, difficulty};
        String selection = TopicsTable.COLUMN_TYPE + " = ? " +
                " AND " + TopicsTable.COLUMN_DIFFICULTY + " = ? ";
        Cursor c = db.query(
                TopicsTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (c.moveToFirst()) {
            do {
                Topic topic = new Topic();
                topic.setId(c.getInt(c.getColumnIndex(TopicsTable._ID)));
                topic.setName(c.getString(c.getColumnIndex(TopicsTable.COLUMN_NAME)));
                topic.setDifficulty(c.getString(c.getColumnIndex(TopicsTable.COLUMN_DIFFICULTY)));
                topic.setType(c.getString(c.getColumnIndex(TopicsTable.COLUMN_TYPE)));
                topic.setActivitiesCount(c.getInt(c.getColumnIndex(TopicsTable.COLUMN_ACT_COUNT)));
                topic.setActivitiesCompleted(c.getInt(c.getColumnIndex(TopicsTable.COLUMN_ACT_COMPLETED)));
                topicList.add(topic);
            } while (c.moveToNext());
        }
        c.close();
        return topicList;
    }

    public ArrayList<Question> getQuestions(int topicID, int activityNum) {
        ArrayList<Question> questionList = new ArrayList<>();
        //will also need to filter activity number
        //filter topic id
        String selection = QuestionsTable.COLUMN_TOPIC_ID + " = ? "
                + " AND " + QuestionsTable.COLUMN_ACT_NUM + " = ? ";
        String[] selectionArgs = new String[] {String.valueOf(topicID), String.valueOf(activityNum)};

        Cursor c = db.query(
                QuestionsTable.TABLE_NAME,
                null, //returns all columns
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex((QuestionsTable._ID))));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NUM)));
                question.setTopicId(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_TOPIC_ID)));
                question.setActivityNum(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ACT_NUM)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }

    public int getTopicId(String selected_topic, String type, String level_name) {
        int topicId = 0;
        db = getReadableDatabase();

        //filter difficulty and type
        String[] selectionArgs = new String[]{selected_topic, type, level_name};
        String selection = TopicsTable.COLUMN_NAME + " = ? " +
                " AND " + TopicsTable.COLUMN_TYPE + " = ? " +
                " AND " + TopicsTable.COLUMN_DIFFICULTY + " = ? ";
        Cursor c = db.query(
                TopicsTable.TABLE_NAME,
                new String[]{TopicsTable._ID},
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (c.moveToFirst()) {
            do {
                Topic topic = new Topic();
                topic.setId(c.getInt(c.getColumnIndex(TopicsTable._ID)));
                //topic.setName(c.getString(c.getColumnIndex(TopicsTable.COLUMN_NAME)));
                //topic.setDifficulty(c.getString(c.getColumnIndex(TopicsTable.COLUMN_DIFFICULTY)));
                //topic.setType(c.getString(c.getColumnIndex(TopicsTable.COLUMN_TYPE)));
                topicId = (int) topic.getId();
            } while (c.moveToNext());
        }
        c.close();
        return topicId;
    }

    public String getGrammarExplanation(int topicId) {
        String grammar_explanation = "";
       // db = getReadableDatabase();
        String[] selectionArgs = new String[]{String.valueOf(topicId)};
        String selection = GrammarTable.COLUMN_TOPIC_ID + " = ? ";
        Cursor c = db.query(
                GrammarTable.TABLE_NAME,
                new String[]{GrammarTable.COLUMN_EXPLANATION},
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        if (c.moveToFirst()) {
            do {
                Grammar grammar = new Grammar();
                grammar.setExplanation(c.getString(c.getColumnIndex(GrammarTable.COLUMN_EXPLANATION)));
                grammar_explanation = (String) grammar.getExplanation();
            } while (c.moveToNext());
        }
        c.close();
        return grammar_explanation;
    }

    /*method to update the 'completed' column of Activity Table to true
    /*and  update the timestamp when activity is completed */
    public boolean activityCompleted(int topicId, int activity_num) {
        //identify corresponding row in activity table and return activity_id
        String STRING_SQL_UPDATE_ACTIVITY_COMPLETED = "UPDATE " + ActivityTable.TABLE_NAME
                + " SET " + ActivityTable.COLUMN_COMPLETED + "= 'TRUE' WHERE " +
                ActivityTable.COLUMN_TOPIC_ID + " = " + topicId + " AND " +
                ActivityTable.COLUMN_ACT_NUM + " = " + activity_num
                ;

        String STRING_SQL_UPDATE_TIMESTAMP = "UPDATE " + ActivityTable.TABLE_NAME
                + " SET " + ActivityTable.COLUMN_TIME_STAMP + " = CURRENT_TIMESTAMP WHERE "
                + ActivityTable.COLUMN_TOPIC_ID + " = " + topicId + " AND "
                + ActivityTable.COLUMN_ACT_NUM + " = " + activity_num
                ;

    db.execSQL(STRING_SQL_UPDATE_ACTIVITY_COMPLETED);
    db.execSQL(STRING_SQL_UPDATE_TIMESTAMP);
        return true;
    }
    /**method to update the activities_completed column in the Topics Table*/
    public void activityCompletedTopics(int topicId) {
        db = getWritableDatabase();
        int activitiesCompleted = 0;
        ArrayList<Activity> activityList = new ArrayList<>();
        String[] selectionArgs = new String[]{String.valueOf(topicId)};
        String selection = ActivityTable.COLUMN_TOPIC_ID + " = ? "
                + " AND " + ActivityTable.COLUMN_COMPLETED + " = 'TRUE' ";
        Cursor c = db.query(
                ActivityTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        if (c.moveToFirst()) {
            do {
                Activity activity = new Activity();
                activity.setTopicId(c.getInt(c.getColumnIndex(ActivityTable.COLUMN_TOPIC_ID)));
                activity.setActivityNum(c.getInt(c.getColumnIndex(ActivityTable.COLUMN_ACT_NUM)));
                activity.setCompleted(c.getString(c.getColumnIndex(ActivityTable.COLUMN_COMPLETED)));
                activityList.add(activity);
            } while (c.moveToNext());
        }
        c.close();
        activitiesCompleted = activityList.size();

        String STRING_SQL_UPDATE_ACTIVITY_COMPLETED = "UPDATE " + TopicsTable.TABLE_NAME
                + " SET " + TopicsTable.COLUMN_ACT_COMPLETED + "= " + activitiesCompleted +
                " WHERE " + TopicsTable._ID + " = " + topicId;
        db.execSQL(STRING_SQL_UPDATE_ACTIVITY_COMPLETED);

    }


    /* method to get the total activity count for a topic*/
    public int getActivityCount(int topicId) {
        ArrayList<Activity> activityList = new ArrayList<>();
        //db = getReadableDatabase();

        //create an Array List of Activity objects of the given Topic ID
        String[] selectionArgs = new String[]{String.valueOf(topicId)};
        String selection = ActivityTable.COLUMN_TOPIC_ID + " = ? ";
        Cursor c = db.query(
                ActivityTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        if (c.moveToFirst()) {
            do {
                Activity activity = new Activity();
                activity.setTopicId(c.getInt(c.getColumnIndex(ActivityTable.COLUMN_TOPIC_ID)));
                activity.setActivityNum(c.getInt(c.getColumnIndex(ActivityTable.COLUMN_ACT_NUM)));
                activity.setCompleted(c.getString(c.getColumnIndex(ActivityTable.COLUMN_COMPLETED)));
                activityList.add(activity);
            } while (c.moveToNext());
        }
        c.close();
        return activityList.size();
    }

    /*method to return the number of completed activities in topic*/
    public int getActivityCompleted(int topicId) {
        int activitiesCompleted = 0;
        //create an Array List of Activity objects of the given Topic ID
        String[] selectionArgs = new String[]{String.valueOf(topicId)};
        String selection = TopicsTable._ID + " = ? ";
        Cursor c = db.query(
                TopicsTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        if (c.moveToFirst()) {
            do {
                Topic topic = new Topic();
                topic.setActivitiesCompleted(c.getInt(c.getColumnIndex(TopicsTable.COLUMN_ACT_COMPLETED)));
                activitiesCompleted = topic.getActivitiesCompleted();
            } while (c.moveToNext());
        }
        c.close();
        return activitiesCompleted;
     }

     /**method to return the number of activities completed today**/
     public int getAllActivityCompletedDaily() {
         ArrayList<Activity> activityList = new ArrayList<>();
    db = getReadableDatabase();

         String query = "SELECT * FROM " + ActivityTable.TABLE_NAME
                 + " WHERE DATE(" + ActivityTable.COLUMN_TIME_STAMP + ") = DATE('now')";
         Cursor c = db.rawQuery(query, null);

         if (c.moveToFirst()) {
             do {
                 Activity activity = new Activity();
                 activity.setTopicId(c.getInt(c.getColumnIndex(ActivityTable.COLUMN_TOPIC_ID)));
                 activity.setActivityNum(c.getInt(c.getColumnIndex(ActivityTable.COLUMN_ACT_NUM)));
                 activity.setCompleted(c.getString(c.getColumnIndex(ActivityTable.COLUMN_COMPLETED)));
                 //activity.setTimeStamp(c.getBlob(c.getColumnIndex(ActivityTable.TIME_STAMP)));
                 activityList.add(activity);

             } while (c.moveToNext());
         }
         c.close();
         return activityList.size();
     }

    /**method to return the number of activities completed today**/
    public int getAllActivityCompletedWeekly() {
        ArrayList<Activity> activityList = new ArrayList<>();
        db = getReadableDatabase();

        String query = "SELECT * FROM " + ActivityTable.TABLE_NAME
                + " WHERE strftime('%W', " + ActivityTable.COLUMN_TIME_STAMP + ") = strftime('%W', 'now')";
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                Activity activity = new Activity();
                activity.setTopicId(c.getInt(c.getColumnIndex(ActivityTable.COLUMN_TOPIC_ID)));
                activity.setActivityNum(c.getInt(c.getColumnIndex(ActivityTable.COLUMN_ACT_NUM)));
                activity.setCompleted(c.getString(c.getColumnIndex(ActivityTable.COLUMN_COMPLETED)));
                //activity.setTimeStamp(c.getBlob(c.getColumnIndex(ActivityTable.TIME_STAMP)));
                activityList.add(activity);

            } while (c.moveToNext());
        }
        c.close();
        return activityList.size();
    }

    /*method to update the activities completed column of Topics Table*/
    public void updateActCount(int topicId) {

        //update activity table
        //get the activities_completed value from Topics Table

        int activities_completed = getActivityCompleted(topicId);
        int activities_count = getActivityCount(topicId);


        //increase activities_completed value if has not already been completed
        //if (activities_completed < activities_count) {
          //  activities_completed++;

            //update activities_completed value
            String SQL_UPDATE_ACT_COMPLETED = " UPDATE " + TopicsTable.TABLE_NAME
                    + " SET " + TopicsTable.COLUMN_ACT_COMPLETED + " = "
                    + activities_completed
                    + " WHERE " + TopicsTable._ID + " = " + topicId
                    ;
            db.execSQL(SQL_UPDATE_ACT_COMPLETED);
        //}
    }

    /*method to check whether an activity num in topic has been completed*/
    public boolean checkCompleted(int topicId, int activity_num) {
        //ArrayList<Activity> activityList = new ArrayList<>();
        db = getReadableDatabase();
        boolean activityCompleted = false;

        //create an Array List of Activity objects of the given Topic ID
        String[] selectionArgs = new String[]{String.valueOf(topicId), String.valueOf(activity_num)};
        String selection = ActivityTable.COLUMN_TOPIC_ID + " = ? "
                + "AND " + ActivityTable.COLUMN_ACT_NUM + " = ? ";
        Cursor c = db.query(
                ActivityTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        if (c.moveToFirst()) {
            do {
                Activity activity = new Activity();
                activity.setTopicId(c.getInt(c.getColumnIndex(ActivityTable.COLUMN_TOPIC_ID)));
                activity.setActivityNum(c.getInt(c.getColumnIndex(ActivityTable.COLUMN_ACT_NUM)));
                activity.setCompleted(c.getString(c.getColumnIndex(ActivityTable.COLUMN_COMPLETED)));
                activityCompleted = Boolean.parseBoolean(activity.getCompleted());
            } while (c.moveToNext());
        }
        c.close();
        return activityCompleted;
    }

    /**method to return the set user goals -> number of activities*/
    public int getActivityGoals() {
        db = getReadableDatabase();

        int activityGoals = 0;
        String query = "SELECT * FROM " + GoalsTable.TABLE_NAME;
        Cursor c = db.rawQuery(query, null);
        if (c.moveToFirst()) {
            do {
                Goal goal = new Goal();
                goal.setGoalActivities(c.getInt(c.getColumnIndex(GoalsTable.COLUMN_GOAL_ACTIVITY)));
                goal.setGoalTimeframe(c.getString(c.getColumnIndex(GoalsTable.COLUMN_GOAL_TIME_FRAME)));
                activityGoals = goal.getGoalActivities();
            } while (c.moveToNext());
        }
        c.close();
        return activityGoals;
    }

    public String getTimeFrameGoals() {
        db = getReadableDatabase();

        String timeFrame = "";
        String query = "SELECT * FROM " + GoalsTable.TABLE_NAME;
        Cursor c = db.rawQuery(query, null);
        if (c.moveToFirst()) {
            do {
                Goal goal = new Goal();
                goal.setGoalActivities(c.getInt(c.getColumnIndex(GoalsTable.COLUMN_GOAL_ACTIVITY)));
                goal.setGoalTimeframe(c.getString(c.getColumnIndex(GoalsTable.COLUMN_GOAL_TIME_FRAME)));
                timeFrame = goal.getGoalTimeframe();
            } while (c.moveToNext());
        }
        c.close();
        return timeFrame;
    }


    /**method to update user goals**/
    public void updateGoalActivities(int goalsActivities) {
        db = getWritableDatabase();

        String SQL_UPDATE_GOALS = " UPDATE " + GoalsTable.TABLE_NAME
                + " SET " + GoalsTable.COLUMN_GOAL_ACTIVITY + " = "
                + goalsActivities;

        db.execSQL(SQL_UPDATE_GOALS);
    }

    /**method to update user goals by timeframe*/
    public void updateGoalTimeFrame(String goalsTimeFrame) {
        db = getWritableDatabase();

        String SQL_UPDATE_GOALS = " UPDATE " + GoalsTable.TABLE_NAME
                + " SET " + GoalsTable.COLUMN_GOAL_TIME_FRAME + " = '"
                + goalsTimeFrame + "'";

        db.execSQL(SQL_UPDATE_GOALS);
    }

}
