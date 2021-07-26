package com.del.studyenglish1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import com.del.studyenglish1.QuizContract.*;

import static android.widget.Toast.LENGTH_SHORT;
import static java.security.AccessController.getContext;

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
                TopicsTable.TABLE_NAME + "(" + TopicsTable._ID + ")" + "ON DELETE CASCADE" +
                ")";

        final String SQL_CREATE_ACTIVITY_TABLE = "CREATE TABLE " +
                ActivityTable.TABLE_NAME + " ( " +
                ActivityTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ActivityTable.COLUMN_TOPIC_ID +  " INTEGER, " +
                ActivityTable.COLUMN_ACT_NUM + " INTEGER, " +
                ActivityTable.COLUMN_COMPLETED + " BOOLEAN, " +
                "FOREIGN KEY(" + ActivityTable.COLUMN_TOPIC_ID + ") REFERENCES " +
                TopicsTable.TABLE_NAME + "(" + TopicsTable._ID + ") ON DELETE CASCADE" +
                ")";

        db.execSQL(SQL_CREATE_TOPICS_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        db.execSQL(SQL_CREATE_ACTIVITY_TABLE);

        fillTopicsTable();
        fillQuestionsTable();
        fillActivityTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TopicsTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ActivityTable.TABLE_NAME);
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
    }

    private void addTopic(Topic topic) {
        ContentValues cv = new ContentValues();
        cv.put(TopicsTable.COLUMN_NAME, topic.getName());
        cv.put(TopicsTable.COLUMN_DIFFICULTY, topic.getDifficulty());
        cv.put(TopicsTable.COLUMN_TYPE, topic.getType());
        db.insert(TopicsTable.TABLE_NAME, null, cv);
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("Select the correct phrase:", "Our teacher is often late.", "Our teacher often is late.", "Is often our teacher late?", "Often our teacher is late", 1, Topic.t5_ID, 1);
        Question q2 = new Question("Select the correct phrase:", "I am tired always.", "I am always tired.", "Always I am tired.", "Tired I am always.", 2, Topic.t5_ID, 1);
        Question q3 = new Question("Select the correct phrase:", "My sister watches TV hardly ever.", "My sister doesn't hardly ever watch TV.", "My sister watches TV hardly ever.", "My sister hardly ever watches TV.", 4, Topic.t5_ID, 1);
        Question q4 = new Question("Select the correct phrase:", "We never eat sushi.", "We eat sushi never.", "We eat never sushi.", "Never we eat sushi.", 1, Topic.t5_ID, 1);
        Question q5 = new Question("Select the correct phrase:", "English they study every day.", "They study every day English.", "They every day study English.", "They study English every day.", 4, Topic.t5_ID, 1);
        Question q6 = new Question("Select the correct phrase:", "He doesn't wake up early usually.", "Does he wake up usually early?", "He doesn't usually wake up early.", "He usually doesn't wake up early.", 3, Topic.t5_ID, 1);
        Question q7 = new Question("Select the correct phrase:", "Do you go to the cinema often?", "Do you go often to the cinema?", "Do often you go to the cinema?", "Do you often go to the cinema?", 1, Topic.t5_ID, 1);
        Question q8 = new Question("Select the correct phrase:", "I always wears a hat", "I always wear a hat", "I wear a hat always", "I wear always a hat", 2, Topic.t5_ID, 1);
        Question q9 = new Question("Select the incorrect phrase:", "He doesn't wake up early usually.", "I always wear a hat", "Do often you go to the cinema?", "I am always tired.", 1, Topic.t5_ID, 2);

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

    private void fillActivityTable() {

        Activity a1 = new Activity(Topic.t5_ID, 1, Activity.FALSE);
        Activity a2 = new Activity(Topic.t5_ID, 2, Activity.FALSE);
        Activity a3 = new Activity(Topic.t5_ID, 3, Activity.FALSE);
        Activity a4 = new Activity(Topic.t5_ID, 4, Activity.FALSE);

        addActivity(a1);
        addActivity(a2);
        addActivity(a3);
        addActivity(a4);

    }

    private void addActivity(Activity activity) {
        ContentValues cv = new ContentValues();
        cv.put(ActivityTable.COLUMN_TOPIC_ID, activity.getTopicId());
        cv.put(ActivityTable.COLUMN_ACT_NUM, activity.getActivityNum());
        cv.put(ActivityTable.COLUMN_COMPLETED, activity.isCompleted());
    }

    public ArrayList<Topic> getAllTopics() {
        ArrayList<Topic> topicList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TopicsTable.TABLE_NAME, null);

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
        String selection = QuestionsTable.COLUMN_TOPIC_ID + " = ? AND "
                + QuestionsTable.COLUMN_ACT_NUM + " = ? ";
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

    //update activity table
    public void updateActivitiesCompleted(int topicId) {
        db = getWritableDatabase();
        int activities_completed = 0;

        String[] selectionArgs = new String[]{String.valueOf(topicId)};
        String selection = TopicsTable._ID + " = ? ";
        Cursor c = db.query(
                TopicsTable.TABLE_NAME,
                new String[]{TopicsTable.COLUMN_ACT_COMPLETED},
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
                if (topic.getActivitiesCompleted() != 0) {
                    activities_completed = (int) topic.getActivitiesCompleted();
                } else activities_completed = 0;
            } while (c.moveToNext());
        }
        c.close();
        activities_completed++;
        String selection1 = "UPDATE " + TopicsTable.TABLE_NAME + " SET "
                + TopicsTable.COLUMN_ACT_COMPLETED + " = " +
                activities_completed + " WHERE "
                + TopicsTable._ID + " = " + topicId;
        db.execSQL(selection1);
    }

    public void correctlyAnswered() {
        db = getWritableDatabase();
        String selection = "UPDATE " + QuestionsTable.TABLE_NAME ;
    }


}
