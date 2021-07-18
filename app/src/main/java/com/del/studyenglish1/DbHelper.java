package com.del.studyenglish1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import com.del.studyenglish1.QuizContract.*;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "StudyEnglish.db";
    private static final int DATABASE_VERSION = 1;

    private static DbHelper instance;
    private SQLiteDatabase db;

    private DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /*
     * singleton: ensures only 1 database connection can be made to avoid any memory leaks
     * synchronised in case we want to access it from other methods
     * static so we don't have to create a quizDbHelper object first
     */
    public static synchronized  DbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DbHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_TOPICS_TABLE = "CREATE TABLE " +
                TopicsTable.TABLE_NAME + "( " +
                TopicsTable._ID + " INTEGER, " +
                TopicsTable.COLUMN_NAME + " TEXT, " +
                TopicsTable.COLUMN_DIFFICULTY + " TEXT, " +
                " PRIMARY KEY (" + TopicsTable._ID  + ", " + TopicsTable.COLUMN_DIFFICULTY + ") " +
                //"FOREIGN KEY(" + TopicsTable.COLUMN_DIFFICULTY + ") REFERENCES " +
                //DifficultyTable.TABLE_NAME + "(" + DifficultyTable.COLUMN_NAME + ")" + "ON DELETE CASCADE" +
                ")";

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuestionsTable.COLUMN_DIFFICULTY + " TEXT, " +
                QuestionsTable.COLUMN_TOPIC_ID + " INTEGER, " +
                "FOREIGN KEY(" + QuestionsTable.COLUMN_TOPIC_ID + ") REFERENCES " +
                TopicsTable.TABLE_NAME + "(" + TopicsTable._ID + ")" + "ON DELETE CASCADE, " +
                "FOREIGN KEY(" + QuestionsTable.COLUMN_DIFFICULTY + ") REFERENCES " +
                TopicsTable.TABLE_NAME + "(" + QuizContract.TopicsTable.COLUMN_DIFFICULTY + ")" + "ON DELETE CASCADE" +
                ")";



        db.execSQL(SQL_CREATE_TOPICS_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);

        fillTopicsTable();
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TopicsTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    private void fillTopicsTable() {
        Topic c1 = new Topic("Adverbs of Frequency", "A1", "Grammar");
        addTopic(c1);
        Topic c2 = new Topic("Used to", "B1", "Grammar");
        addTopic(c2);
        Topic c3 = new Topic("Colours", "A1", "Vocabulary");
        addTopic(c3);
    }

    private void addTopic(Topic topic) {

        ContentValues cv = new ContentValues();
        cv.put(TopicsTable.COLUMN_NAME, topic.getName());
        db.insert(TopicsTable.TABLE_NAME, null, cv);
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("Select the correct sentence","Our teacher is often late.", "Our teacher often is late.",
                "Is often our teacher late?", "Often our teacher is late",
                1, Topic.DIFFICULTY_A1, Topic.ADVERBS_OF_FREQUENCY);
        addQuestion(q1);
    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionsTable.COLUMN_DIFFICULTY, question.getDifficulty());
        cv.put(QuestionsTable.COLUMN_TOPIC_ID, question.getTopicId());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<Topic> getAllTopics() {
        List<Topic> topicList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TopicsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Topic topic = new Topic();
                topic.setId(c.getInt(c.getColumnIndex(TopicsTable._ID)));
                topic.setName(c.getString(c.getColumnIndex(TopicsTable.COLUMN_NAME)));
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
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setTopicId(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_TOPIC_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }

    public ArrayList<Question> getQuestions(int topicID, String difficulty) {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        //filter difficulty and topic id

        String selection = QuestionsTable.COLUMN_TOPIC_ID + " = ? " +
                " AND " + QuestionsTable.COLUMN_DIFFICULTY + " = ? ";
        String[] selectionArgs = new String[] {String.valueOf(topicID), difficulty};

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
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setTopicId(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_TOPIC_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }


}
