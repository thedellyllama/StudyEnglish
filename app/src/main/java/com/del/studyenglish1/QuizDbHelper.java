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
                TopicsTable.COLUMN_ACT_COMPLETED + " INTEGER, " +
                TopicsTable.COLUMN_INFO + " TEXT" +
                ")";

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_INSTRUCTION + " TEXT, " +
                QuestionsTable.COLUMN_IMAGE_REF + " INTEGER, " +
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
/*
        final String SQL_CREATE_GRAMMAR_TABLE = "CREATE TABLE " +
                GrammarTable.TABLE_NAME + " ( " +
                GrammarTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                GrammarTable.COLUMN_TOPIC_ID +  " INTEGER, " +
                GrammarTable.COLUMN_EXPLANATION + " STRING, " +
                "FOREIGN KEY(" + ActivityTable.COLUMN_TOPIC_ID + ") REFERENCES " +
                TopicsTable.TABLE_NAME + "(" + TopicsTable._ID + ") ON DELETE CASCADE" +
                ")";*/

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
        //db.execSQL(SQL_CREATE_GRAMMAR_TABLE);
        db.execSQL(SQL_CREATE_VOCAB_TABLE);
        db.execSQL(SQL_CREATE_GOALS_TABLE);

        fillTopicsTable();
        fillQuestionsTable();
        fillActivityTable();
        //fillGrammarTable();
        fillVocabTable();
        fillGoalsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TopicsTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ActivityTable.TABLE_NAME);
        ////db.execSQL("DROP TABLE IF EXISTS " + GrammarTable.TABLE_NAME);
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
/*        Topic g1 = new Topic( "ADVERBS OF FREQUENCY", Topic.DIFFICULTY_A1, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR, "Adverbs of Frequency are adverbs of time that answer the question \'\'How frequently?\'\' or \'\'How often?\'\'. They tell us how often something happens. Here are some examples:\n-daily, weekly, yearly\n-often, sometimes, rarely\nThe words in a) describe definite frequency.\nThe words in b) describe indefinite frequency\nWe separate them into two groups because they normally go in different positions in the sentence.\n\nAdverbs of definite frequency, typically go in END position.\n-Most companies pay taxes yearly.\n-The manager checks the toilets every hour.\nThe directors meet weekly to review progress.\nSometimes, usually for reasons of emphasis or style, some adverbs of definite frequency may go at the FRONT, for example:\n-Every day, more than five thousand people die on our roads.\n\nHere are some adverbs of indefinite frequency:\n100%\t always, constantly\n\tusually, normally\n\tfrequently, regularly\n\toften\n50%\tsometimes\n\toccasionally\n\trarely, infrequently\n\tseldom\n\thardly ever\n0%\tnever\n\nAdverbs of indefinite frequency mainly go in MID position in the sentence. They go before the main verb (except the main verb TO BE):\n-We usually go shopping on Saturday.\n-I have often done that.\n-She is always late.");
        Topic g2 = new Topic( "PRESENT SIMPLE", Topic.DIFFICULTY_A1, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR, "The Present Simple tense uses the base form of the verb (except for the verb be). The only change from the base is the addition of s for third person singular.\nHow do we make the Present Simple tense?\nThere are two basic structures for the Present Simple:\n1. Positive sentences:\nsubject	+\tmain verb in present simple \n2.Negative and question sentences:\nsubject\t+\tauxiliary do (conjugated in Present Simple)\t+\tmain verb (base)\n\nExamples with the main verb like:\n\n-I, you, we, they\tlike\tcoffee.\n-He, she, it\tlikes\tcoffee.\n-I, you, we, they\tdo\tnot\tlike	coffee.\n-He, she, it\tdoes\tnot\tlike coffee.\n-Do\tI, you, we, they\tlike coffee?\n-Does\the, she, it\tlike\tcoffee?");
        Topic g3 = new Topic( "QUESTION WORDS", Topic.DIFFICULTY_A1, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR, "These are the most common question words in English: \nWHO is used when asking about people. (I want to know the person)\n\nWHERE is used when asking about a place or location. (= I want to know the place)\n\n WHEN is used to ask about time or an occasion. (= I want to know the time)\n\nWHY is used to ask for an explanation or a reason. (= I want to know the reason)\nNormally the response starts with \'Because...\'\nWHAT is used to ask about specific information. (= I want to know the thing)\n\nWHICH is used to ask about a choice. (= I want to know the chosen thing)\n\nHOW is used to describe the way or manner that something is done. (= I want to know the way)\nThere are more expressions with HOW:\nHow much – to ask about quantity or price (uncountable nouns)\nHow many – to ask about quantity (countable nouns)\nHow often – to ask about frequency");
        Topic g4 = new Topic( "QUESTIONS", Topic.DIFFICULTY_A1, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        Topic g5 = new Topic( "ADJECTIVES", Topic.DIFFICULTY_A1, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        Topic g6 = new Topic( "PAST SIMPLE", Topic.DIFFICULTY_A2, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        Topic g7 = new Topic( "QUANTIFIERS", Topic.DIFFICULTY_A2, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        Topic g8 = new Topic( "COMPARATIVES AND SUPERLATIVES", Topic.DIFFICULTY_A2, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        Topic g9 = new Topic( "SOMETHING, ANYTHING, NOTHING", Topic.DIFFICULTY_A2, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        Topic g10 = new Topic( "PRESENT PERFECT", Topic.DIFFICULTY_A2, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        Topic g11 = new Topic( "REFLEXIVE PRONOUNS", Topic.DIFFICULTY_B1, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        Topic g12 = new Topic( "FIRST CONDITIONAL", Topic.DIFFICULTY_B1, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        Topic g13 = new Topic( "SECOND CONDITIONAL", Topic.DIFFICULTY_B1, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        Topic g14 = new Topic( "GERUND OR INFINITIVE", Topic.DIFFICULTY_B1, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        Topic g15 = new Topic( "QUESTION TAGS", Topic.DIFFICULTY_B1, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        Topic g16 = new Topic( "FUTURE FORMS", Topic.DIFFICULTY_B2, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        Topic g17 = new Topic( "MODAL VERBS", Topic.DIFFICULTY_B2, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        Topic g18 = new Topic( "THERE AND IT", Topic.DIFFICULTY_B2, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        Topic g19 = new Topic( "GENERIC PRONOUNDS", Topic.DIFFICULTY_B2, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        Topic g20 = new Topic( "COMPOUND NOUNS", Topic.DIFFICULTY_B2, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        Topic g21 = new Topic( "PASSIVES ", Topic.DIFFICULTY_C1, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        Topic g22 = new Topic( "REPORTING VERBS", Topic.DIFFICULTY_C1, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        Topic g23 = new Topic( "VERBS OF SENSES", Topic.DIFFICULTY_C1, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        //Topic g24 = new Topic( "", Topic.DIFFICULTY_C1, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        //Topic g25 = new Topic( "", Topic.DIFFICULTY_C1, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);

        Topic v1 = new Topic( "CLOTHES 1", Topic.DIFFICULTY_A1, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic v2 = new Topic( "ACCESSORIES", Topic.DIFFICULTY_A1, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic v3 = new Topic( "HOLIDAYS", Topic.DIFFICULTY_A1, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic v4 = new Topic( "CLOTHES 2", Topic.DIFFICULTY_A1, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic v5 = new Topic( "BEDROOMS", Topic.DIFFICULTY_A1, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic v6 = new Topic( "DAILY ROUTINE", Topic.DIFFICULTY_A2, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic v7 = new Topic( "EVERYDAY OBJECTS", Topic.DIFFICULTY_A2, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic v8 = new Topic( "HOMES", Topic.DIFFICULTY_A2, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic v9 = new Topic( "HOTELS", Topic.DIFFICULTY_A2, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic v10 = new Topic( "JOBS 1", Topic.DIFFICULTY_A2, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic v11 = new Topic( "BATHROOMS", Topic.DIFFICULTY_B1, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic v12 = new Topic( "CARS", Topic.DIFFICULTY_B1, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic v13 = new Topic( "BICYCLES", Topic.DIFFICULTY_B1, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic v14 = new Topic( "BODY PARTS", Topic.DIFFICULTY_B1, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic v15 = new Topic( "MEAT AND FISH", Topic.DIFFICULTY_B1, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic v16 = new Topic( "HEALTH", Topic.DIFFICULTY_B2, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic v17 = new Topic( "STREETS AND ROADS", Topic.DIFFICULTY_B2, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic v18 = new Topic( "TOOLS", Topic.DIFFICULTY_B2, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic v19 = new Topic( "WILD ANIMALS", Topic.DIFFICULTY_B2, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic v20 = new Topic( "VEGETABLES 1", Topic.DIFFICULTY_B2, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic v21 = new Topic( "DIET", Topic.DIFFICULTY_C1, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic v22 = new Topic( "RELATIONSHIPS", Topic.DIFFICULTY_C1, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic v23 = new Topic( "SYNONYMS AND ANTONYMS", Topic.DIFFICULTY_C1, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic v24 = new Topic( "FOOD AND DRINK", Topic.DIFFICULTY_C1, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic v25 = new Topic( "LEISURE ACTIVITIES", Topic.DIFFICULTY_C1, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);

        Topic r1 = new Topic( "TRAVEL", Topic.DIFFICULTY_A1, Topic.TYPE_READING, Topic.NUM_READING);
        Topic r2 = new Topic( "RESTAURANT", Topic.DIFFICULTY_A1, Topic.TYPE_READING, Topic.NUM_READING);
        Topic r3 = new Topic( "JOBS", Topic.DIFFICULTY_A1, Topic.TYPE_READING, Topic.NUM_READING);
        Topic r4 = new Topic( "STUDENT CARD APPLICATION", Topic.DIFFICULTY_A1, Topic.TYPE_READING, Topic.NUM_READING);
        Topic r5 = new Topic( "TEXTING A FRIEND", Topic.DIFFICULTY_A1, Topic.TYPE_READING, Topic.NUM_READING);
        Topic r6 = new Topic( "JOB APPLICATION", Topic.DIFFICULTY_A2, Topic.TYPE_READING, Topic.NUM_READING);
        Topic r7 = new Topic( "CONTACTING FRIENDS", Topic.DIFFICULTY_A2, Topic.TYPE_READING, Topic.NUM_READING);
        Topic r8 = new Topic( "CHOOSING A COURSE", Topic.DIFFICULTY_A2, Topic.TYPE_READING, Topic.NUM_READING);
        Topic r9 = new Topic( "END OF TERM REPORT", Topic.DIFFICULTY_A2, Topic.TYPE_READING, Topic.NUM_READING);
        Topic r10 = new Topic( "PROFESSIONAL PROFILES", Topic.DIFFICULTY_A2, Topic.TYPE_READING, Topic.NUM_READING);
        Topic r11 = new Topic( "TRAVEL GUIDE", Topic.DIFFICULTY_B1, Topic.TYPE_READING, Topic.NUM_READING);
        Topic r12 = new Topic( "CONFERENCE PROGRAMME", Topic.DIFFICULTY_B1, Topic.TYPE_READING, Topic.NUM_READING);
        Topic r13 = new Topic( "GYM LEAFLET", Topic.DIFFICULTY_B1, Topic.TYPE_READING, Topic.NUM_READING);
        Topic r14 = new Topic( "PLANNING AN EVENT", Topic.DIFFICULTY_B1, Topic.TYPE_READING, Topic.NUM_READING);
        Topic r15 = new Topic( "BOOK REVIEW", Topic.DIFFICULTY_B1, Topic.TYPE_READING, Topic.NUM_READING);
        Topic r16 = new Topic( "SHORT STORY EXTRACT", Topic.DIFFICULTY_B2, Topic.TYPE_READING, Topic.NUM_READING);
        Topic r17 = new Topic( "EMAIL FROM A FRIEND", Topic.DIFFICULTY_B2, Topic.TYPE_READING, Topic.NUM_READING);
        Topic r18 = new Topic( "FILM REVIEW", Topic.DIFFICULTY_B2, Topic.TYPE_READING, Topic.NUM_READING);
        Topic r19 = new Topic( "ARTICLE: POLLUTION", Topic.DIFFICULTY_B2, Topic.TYPE_READING, Topic.NUM_READING);
        Topic r20 = new Topic( "ARTICLE: SOCIAL MEDIA", Topic.DIFFICULTY_B2, Topic.TYPE_READING, Topic.NUM_READING);
        Topic r21 = new Topic( "ARTICLE: SPACE EXPLORATION", Topic.DIFFICULTY_C1, Topic.TYPE_READING, Topic.NUM_READING);
        Topic r22 = new Topic( "ARTICLE: RESTAURANT CRITICS AT WAR", Topic.DIFFICULTY_C1, Topic.TYPE_READING, Topic.NUM_READING);
        Topic r23 = new Topic( "BOOK SUMMARIES", Topic.DIFFICULTY_C1, Topic.TYPE_READING, Topic.NUM_READING);
        Topic r24 = new Topic( "ARTICLE: CULTURE IN THE WORKPLACE", Topic.DIFFICULTY_C1, Topic.TYPE_READING, Topic.NUM_READING);
        Topic r25 = new Topic( "BIOGRAPHY", Topic.DIFFICULTY_C1, Topic.TYPE_READING, Topic.NUM_READING);
*/
        Topic g1 = new Topic(Topic.g1_ID, "ADVERBS OF FREQUENCY", Topic.DIFFICULTY_A1, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR, "Adverbs of Frequency are adverbs of time that answer the question \'\'How frequently?\'\' or \'\'How often?\'\'. They tell us how often something happens. Here are some examples:\n-daily, weekly, yearly\n-often, sometimes, rarely\nThe words in a) describe definite frequency.\nThe words in b) describe indefinite frequency\nWe separate them into two groups because they normally go in different positions in the sentence.\n\nAdverbs of definite frequency, typically go in END position.\n-Most companies pay taxes yearly.\n-The manager checks the toilets every hour.\nThe directors meet weekly to review progress.\nSometimes, usually for reasons of emphasis or style, some adverbs of definite frequency may go at the FRONT, for example:\n-Every day, more than five thousand people die on our roads.\n\nHere are some adverbs of indefinite frequency:\n100%\t always, constantly\n\tusually, normally\n\tfrequently, regularly\n\toften\n50%\tsometimes\n\toccasionally\n\trarely, infrequently\n\tseldom\n\thardly ever\n0%\tnever\n\nAdverbs of indefinite frequency mainly go in MID position in the sentence. They go before the main verb (except the main verb TO BE):\n-We usually go shopping on Saturday.\n-I have often done that.\n-She is always late.");
        Topic g2 = new Topic(Topic.g2_ID, "PRESENT SIMPLE", Topic.DIFFICULTY_A1, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR, "The Present Simple tense uses the base form of the verb (except for the verb be). The only change from the base is the addition of s for third person singular.\nHow do we make the Present Simple tense?\nThere are two basic structures for the Present Simple:\n1. Positive sentences:\nsubject	+\tmain verb in present simple \n2.Negative and question sentences:\nsubject\t+\tauxiliary do (conjugated in Present Simple)\t+\tmain verb (base)\n\nExamples with the main verb like:\n\n-I, you, we, they\tlike\tcoffee.\n-He, she, it\tlikes\tcoffee.\n-I, you, we, they\tdo\tnot\tlike	coffee.\n-He, she, it\tdoes\tnot\tlike coffee.\n-Do\tI, you, we, they\tlike coffee?\n-Does\the, she, it\tlike\tcoffee?");
        Topic g3 = new Topic(Topic.g3_ID, "QUESTION WORDS", Topic.DIFFICULTY_A1, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR, "These are the most common question words in English: \nWHO is used when asking about people. (I want to know the person)\n\nWHERE is used when asking about a place or location. (= I want to know the place)\n\n WHEN is used to ask about time or an occasion. (= I want to know the time)\n\nWHY is used to ask for an explanation or a reason. (= I want to know the reason)\nNormally the response starts with \'Because...\'\n\nWHAT is used to ask about specific information. (= I want to know the thing)\n\nWHICH is used to ask about a choice. (= I want to know the chosen thing)\n\nHOW is used to describe the way or manner that something is done. (= I want to know the way)\nThere are more expressions with HOW:\nHow much – to ask about quantity or price (uncountable nouns)\nHow many – to ask about quantity (countable nouns)\nHow often – to ask about frequency");
        Topic g4 = new Topic(Topic.g4_ID, "QUESTIONS", Topic.DIFFICULTY_A1, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        Topic g5 = new Topic(Topic.g5_ID, "ADJECTIVES", Topic.DIFFICULTY_A1, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        Topic g6 = new Topic(Topic.g6_ID, "PAST SIMPLE", Topic.DIFFICULTY_A2, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        Topic g7 = new Topic(Topic.g7_ID, "QUANTIFIERS", Topic.DIFFICULTY_A2, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        Topic g8 = new Topic(Topic.g8_ID, "COMPARATIVES AND SUPERLATIVES", Topic.DIFFICULTY_A2, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        Topic g9 = new Topic(Topic.g9_ID, "SOMETHING, ANYTHING, NOTHING", Topic.DIFFICULTY_A2, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        Topic g10 = new Topic(Topic.g10_ID, "PRESENT PERFECT", Topic.DIFFICULTY_A2, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        Topic g11 = new Topic(Topic.g11_ID, "REFLEXIVE PRONOUNS", Topic.DIFFICULTY_B1, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        Topic g12 = new Topic(Topic.g12_ID, "FIRST CONDITIONAL", Topic.DIFFICULTY_B1, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        Topic g13 = new Topic(Topic.g13_ID, "SECOND CONDITIONAL", Topic.DIFFICULTY_B1, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        Topic g14 = new Topic(Topic.g14_ID, "GERUND OR INFINITIVE", Topic.DIFFICULTY_B1, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        Topic g15 = new Topic(Topic.g15_ID, "QUESTION TAGS", Topic.DIFFICULTY_B1, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        Topic g16 = new Topic(Topic.g16_ID, "FUTURE FORMS", Topic.DIFFICULTY_B2, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        Topic g17 = new Topic(Topic.g17_ID, "MODAL VERBS", Topic.DIFFICULTY_B2, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        Topic g18 = new Topic(Topic.g18_ID, "THERE AND IT", Topic.DIFFICULTY_B2, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        Topic g19 = new Topic(Topic.g19_ID, "GENERIC PRONOUNS", Topic.DIFFICULTY_B2, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        Topic g20 = new Topic(Topic.g20_ID, "COMPOUND NOUNS", Topic.DIFFICULTY_B2, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        Topic g21 = new Topic(Topic.g21_ID, "PASSIVES ", Topic.DIFFICULTY_C1, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        Topic g22 = new Topic(Topic.g22_ID, "REPORTING VERBS", Topic.DIFFICULTY_C1, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        Topic g23 = new Topic(Topic.g23_ID, "VERBS OF SENSES", Topic.DIFFICULTY_C1, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        //Topic g24 = new Topic(Topic.g24_ID, "", Topic.DIFFICULTY_C1, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        //Topic g25 = new Topic(Topic.g25_ID, "", Topic.DIFFICULTY_C1, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);

        Topic v1 = new Topic(Topic.v1_ID, "CLOTHES 1", Topic.DIFFICULTY_A1, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB, "Learning vocabulary will help you improve your English and communicate more effectively!\n\n\nNew vocabulary includes:\n\t-socks\n\t-jumper\n\t-hoodie\n\t-jacket\n\t-shoes\n\t-boots\n\n\nVisit the Dictionary section to read the full definitions!");
        Topic v2 = new Topic(Topic.v2_ID, "ACCESSORIES", Topic.DIFFICULTY_A1, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB, "Learning vocabulary will help you improve your English and communicate more effectively!\n\n\nNew vocabulary includes:\n\t-rucksack\n\t-hat\n\t-gloves\n\t-tie\n\t-watch\n\t-bracelet\n\n\nVisit the Dictionary section to read the full definitions!");
        Topic v3 = new Topic(Topic.v3_ID, "HOLIDAYS", Topic.DIFFICULTY_A1, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB, "Learning vocabulary will help you improve your English and communicate more effectively!\n\n\nNew vocabulary includes:\n\t-caravan\n\t-picnic\n\t-flight\n\t-ticket\n\t-hotel\n\t-passport\n\n\nVisit the Dictionary section to read the full definitions!");
        Topic v4 = new Topic(Topic.v4_ID, "CLOTHES 2", Topic.DIFFICULTY_A1, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic v5 = new Topic(Topic.v5_ID, "BEDROOMS", Topic.DIFFICULTY_A1, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic v6 = new Topic(Topic.v6_ID, "DAILY ROUTINE", Topic.DIFFICULTY_A2, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic v7 = new Topic(Topic.v7_ID, "EVERYDAY OBJECTS", Topic.DIFFICULTY_A2, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic v8 = new Topic(Topic.v8_ID, "HOMES", Topic.DIFFICULTY_A2, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic v9 = new Topic(Topic.v9_ID, "HOTELS", Topic.DIFFICULTY_A2, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic v10 = new Topic(Topic.v10_ID, "JOBS 1", Topic.DIFFICULTY_A2, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic v11 = new Topic(Topic.v11_ID, "BATHROOMS", Topic.DIFFICULTY_B1, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic v12 = new Topic(Topic.v12_ID, "CARS", Topic.DIFFICULTY_B1, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic v13 = new Topic(Topic.v13_ID, "BICYCLES", Topic.DIFFICULTY_B1, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic v14 = new Topic(Topic.v14_ID, "BODY PARTS", Topic.DIFFICULTY_B1, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic v15 = new Topic(Topic.v15_ID, "MEAT AND FISH", Topic.DIFFICULTY_B1, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic v16 = new Topic(Topic.v16_ID, "HEALTH", Topic.DIFFICULTY_B2, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic v17 = new Topic(Topic.v17_ID, "STREETS AND ROADS", Topic.DIFFICULTY_B2, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic v18 = new Topic(Topic.v18_ID, "TOOLS", Topic.DIFFICULTY_B2, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic v19 = new Topic(Topic.v19_ID, "WILD ANIMALS", Topic.DIFFICULTY_B2, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic v20 = new Topic(Topic.v20_ID, "VEGETABLES 1", Topic.DIFFICULTY_B2, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic v21 = new Topic(Topic.v21_ID, "DIET", Topic.DIFFICULTY_C1, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic v22 = new Topic(Topic.v22_ID, "RELATIONSHIPS", Topic.DIFFICULTY_C1, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic v23 = new Topic(Topic.v23_ID, "SYNONYMS AND ANTONYMS", Topic.DIFFICULTY_C1, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic v24 = new Topic(Topic.v24_ID, "FOOD AND DRINK", Topic.DIFFICULTY_C1, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);
        Topic v25 = new Topic(Topic.v25_ID, "LEISURE ACTIVITIES", Topic.DIFFICULTY_C1, Topic.TYPE_VOCABULARY, Topic.NUM_VOCAB);

        Topic r1 = new Topic(Topic.r1_ID, "TRAVEL", Topic.DIFFICULTY_A1, Topic.TYPE_READING, Topic.NUM_READING, "Reading is an important skill!\n\nImprove your reading skills and your travel vocabulary by practising.\nThese activities involve looking at the Departures Board in an aiport.");
        Topic r2 = new Topic(Topic.r2_ID, "RESTAURANT", Topic.DIFFICULTY_A1, Topic.TYPE_READING, Topic.NUM_READING);
        Topic r3 = new Topic(Topic.r3_ID, "JOBS", Topic.DIFFICULTY_A1, Topic.TYPE_READING, Topic.NUM_READING);
        Topic r4 = new Topic(Topic.r4_ID, "STUDENT CARD APPLICATION", Topic.DIFFICULTY_A1, Topic.TYPE_READING, Topic.NUM_READING);
        Topic r5 = new Topic(Topic.r5_ID, "TEXTING A FRIEND", Topic.DIFFICULTY_A1, Topic.TYPE_READING, Topic.NUM_READING);
        Topic r6 = new Topic(Topic.r6_ID, "JOB APPLICATION", Topic.DIFFICULTY_A2, Topic.TYPE_READING, Topic.NUM_READING);
        Topic r7 = new Topic(Topic.r7_ID, "CONTACTING FRIENDS", Topic.DIFFICULTY_A2, Topic.TYPE_READING, Topic.NUM_READING);
        Topic r8 = new Topic(Topic.r8_ID, "CHOOSING A COURSE", Topic.DIFFICULTY_A2, Topic.TYPE_READING, Topic.NUM_READING);
        Topic r9 = new Topic(Topic.r9_ID, "END OF TERM REPORT", Topic.DIFFICULTY_A2, Topic.TYPE_READING, Topic.NUM_READING);
        Topic r10 = new Topic(Topic.r10_ID, "PROFESSIONAL PROFILES", Topic.DIFFICULTY_A2, Topic.TYPE_READING, Topic.NUM_READING);
        Topic r11 = new Topic(Topic.r11_ID, "TRAVEL GUIDE", Topic.DIFFICULTY_B1, Topic.TYPE_READING, Topic.NUM_READING);
        Topic r12 = new Topic(Topic.r12_ID, "CONFERENCE PROGRAMME", Topic.DIFFICULTY_B1, Topic.TYPE_READING, Topic.NUM_READING);
        Topic r13 = new Topic(Topic.r13_ID, "GYM LEAFLET", Topic.DIFFICULTY_B1, Topic.TYPE_READING, Topic.NUM_READING);
        Topic r14 = new Topic(Topic.r14_ID, "PLANNING AN EVENT", Topic.DIFFICULTY_B1, Topic.TYPE_READING, Topic.NUM_READING);
        Topic r15 = new Topic(Topic.r15_ID, "BOOK REVIEW", Topic.DIFFICULTY_B1, Topic.TYPE_READING, Topic.NUM_READING);
        Topic r16 = new Topic(Topic.r16_ID, "SHORT STORY EXTRACT", Topic.DIFFICULTY_B2, Topic.TYPE_READING, Topic.NUM_READING);
        Topic r17 = new Topic(Topic.r17_ID, "EMAIL FROM A FRIEND", Topic.DIFFICULTY_B2, Topic.TYPE_READING, Topic.NUM_READING);
        Topic r18 = new Topic(Topic.r18_ID, "FILM REVIEW", Topic.DIFFICULTY_B2, Topic.TYPE_READING, Topic.NUM_READING);
        Topic r19 = new Topic(Topic.r19_ID, "ARTICLE: POLLUTION", Topic.DIFFICULTY_B2, Topic.TYPE_READING, Topic.NUM_READING);
        Topic r20 = new Topic(Topic.r20_ID, "ARTICLE: SOCIAL MEDIA", Topic.DIFFICULTY_B2, Topic.TYPE_READING, Topic.NUM_READING);
        Topic r21 = new Topic(Topic.r21_ID, "ARTICLE: SPACE EXPLORATION", Topic.DIFFICULTY_C1, Topic.TYPE_READING, Topic.NUM_READING);
        Topic r22 = new Topic(Topic.r22_ID, "ARTICLE: RESTAURANT CRITICS AT WAR", Topic.DIFFICULTY_C1, Topic.TYPE_READING, Topic.NUM_READING);
        Topic r23 = new Topic(Topic.r23_ID, "BOOK SUMMARIES", Topic.DIFFICULTY_C1, Topic.TYPE_READING, Topic.NUM_READING);
        Topic r24 = new Topic(Topic.r24_ID, "ARTICLE: CULTURE IN THE WORKPLACE", Topic.DIFFICULTY_C1, Topic.TYPE_READING, Topic.NUM_READING);
        Topic r25 = new Topic(Topic.r25_ID, "BIOGRAPHY", Topic.DIFFICULTY_C1, Topic.TYPE_READING, Topic.NUM_READING);

        addTopic(g1);
        addTopic(g2);
        addTopic(g3);
        addTopic(g4);
        addTopic(g5);
        addTopic(g6);
        addTopic(g7);
        addTopic(g8);
        addTopic(g9);
        addTopic(g10);
        addTopic(g11);
        addTopic(g12);
        addTopic(g13);
        addTopic(g14);
        addTopic(g15);
        addTopic(g16);
        addTopic(g17);
        addTopic(g18);
        addTopic(g19);
        addTopic(g20);
        addTopic(g21);
        addTopic(g22);
        addTopic(g23);
        //addTopic(g24);
        //addTopic(g25);

        addTopic(v1);
        addTopic(v2);
        addTopic(v3);
        addTopic(v4);
        addTopic(v5);
        addTopic(v6);
        addTopic(v7);
        addTopic(v8);
        addTopic(v9);
        addTopic(v10);
        addTopic(v11);
        addTopic(v12);
        addTopic(v13);
        addTopic(v14);
        addTopic(v15);
        addTopic(v16);
        addTopic(v17);
        addTopic(v18);
        addTopic(v19);
        addTopic(v20);
        addTopic(v21);
        addTopic(v22);
        addTopic(v23);
        addTopic(v24);
        addTopic(v25);

        addTopic(r1);
        addTopic(r2);
        addTopic(r3);
        addTopic(r4);
        addTopic(r5);
        addTopic(r6);
        addTopic(r7);
        addTopic(r8);
        addTopic(r9);
        addTopic(r10);
        addTopic(r11);
        addTopic(r12);
        addTopic(r13);
        addTopic(r14);
        addTopic(r15);
        addTopic(r16);
        addTopic(r17);
        addTopic(r18);
        addTopic(r19);
        addTopic(r20);
        addTopic(r21);
        addTopic(r22);
        addTopic(r23);
        addTopic(r24);
        addTopic(r25);




        /*
        Topic t1 = new Topic("PRESENT SIMPLE - TO BE", Topic.DIFFICULTY_A1, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        Topic t2 = new Topic("POSSESSIVE ADJECTIVES & SUBJECT PRONOUNS", Topic.DIFFICULTY_A1, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        Topic t3 = new Topic("ADJECTIVES", Topic.DIFFICULTY_A1, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        Topic t4 = new Topic("QUESTIONS", Topic.DIFFICULTY_A1, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR);
        Topic t5 = new Topic("ADVERBS OF FREQUENCY", Topic.DIFFICULTY_A1, Topic.TYPE_GRAMMAR, Topic.NUM_GRAMMAR, "Adverbs of Frequency are adverbs of time that answer the question \'\'How frequently?\'\' or \'\'How often?\'\'. They tell us how often something happens. Here are some examples:\n-daily, weekly, yearly\n-often, sometimes, rarely\nThe words in a) describe definite frequency.\nThe words in b) describe indefinite frequency\nWe separate them into two groups because they normally go in different positions in the sentence.\n\nAdverbs of definite frequency, typically go in END position.\n-Most companies pay taxes yearly.\n-The manager checks the toilets every hour.\nThe directors meet weekly to review progress.\nSometimes, usually for reasons of emphasis or style, some adverbs of definite frequency may go at the FRONT, for example:\n-Every day, more than five thousand people die on our roads.\n\nHere are some adverbs of indefinite frequency:\n100%\t always, constantly\n\tusually, normally\n\tfrequently, regularly\n\toften\n50%\tsometimes\n\toccasionally\n\trarely, infrequently\n\tseldom\n\thardly ever\n0%\tnever\n\nAdverbs of indefinite frequency mainly go in MID position in the sentence. They go before the main verb (except the main verb TO BE):\n-We usually go shopping on Saturday.\n-I have often done that.\n-She is always late.\n");
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
*/
    }

    private void addTopic(Topic topic) {
        ContentValues cv = new ContentValues();
        cv.put(TopicsTable._ID, topic.getId());
        cv.put(TopicsTable.COLUMN_NAME, topic.getName());
        cv.put(TopicsTable.COLUMN_DIFFICULTY, topic.getDifficulty());
        cv.put(TopicsTable.COLUMN_TYPE, topic.getType());
        cv.put(TopicsTable.COLUMN_ACT_COUNT, topic.getActivitiesCount());
        cv.put(TopicsTable.COLUMN_INFO, topic.getInformation());
        db.insert(TopicsTable.TABLE_NAME, null, cv);
    }

    private void fillActivityTable() {
        Activity a1 = new Activity(Topic.g1_ID, Activity.ACT_NUM_1, Activity.FALSE);
        Activity a2 = new Activity(Topic.g1_ID, Activity.ACT_NUM_2, Activity.FALSE);
        Activity a3 = new Activity(Topic.g1_ID, Activity.ACT_NUM_3, Activity.FALSE);
        Activity a4 = new Activity(Topic.g1_ID, Activity.ACT_NUM_4, Activity.FALSE);
        Activity a5 = new Activity(Topic.g2_ID, Activity.ACT_NUM_1, Activity.FALSE);
        Activity a6 = new Activity(Topic.g2_ID, Activity.ACT_NUM_2, Activity.FALSE);
        Activity a7 = new Activity(Topic.g2_ID, Activity.ACT_NUM_3, Activity.FALSE);
        Activity a8 = new Activity(Topic.g2_ID, Activity.ACT_NUM_4, Activity.FALSE);
        Activity a9 = new Activity(Topic.g3_ID, Activity.ACT_NUM_1, Activity.FALSE);
        Activity a10 = new Activity(Topic.g3_ID, Activity.ACT_NUM_2, Activity.FALSE);
        Activity a11 = new Activity(Topic.g3_ID, Activity.ACT_NUM_3, Activity.FALSE);
        Activity a12 = new Activity(Topic.g3_ID, Activity.ACT_NUM_4, Activity.FALSE);
        Activity a13 = new Activity(Topic.v1_ID, Activity.ACT_NUM_1, Activity.FALSE);
        Activity a14 = new Activity(Topic.v1_ID, Activity.ACT_NUM_2, Activity.FALSE);
        Activity a15 = new Activity(Topic.v1_ID, Activity.ACT_NUM_3, Activity.FALSE);
        Activity a16 = new Activity(Topic.v1_ID, Activity.ACT_NUM_4, Activity.FALSE);
        Activity a17 = new Activity(Topic.v2_ID, Activity.ACT_NUM_1, Activity.FALSE);
        Activity a18 = new Activity(Topic.v2_ID, Activity.ACT_NUM_2, Activity.FALSE);
        Activity a19 = new Activity(Topic.v2_ID, Activity.ACT_NUM_3, Activity.FALSE);
        Activity a20 = new Activity(Topic.v2_ID, Activity.ACT_NUM_4, Activity.FALSE);
        Activity a21 = new Activity(Topic.v3_ID, Activity.ACT_NUM_1, Activity.FALSE);
        Activity a22 = new Activity(Topic.v3_ID, Activity.ACT_NUM_2, Activity.FALSE);
        Activity a23 = new Activity(Topic.v3_ID, Activity.ACT_NUM_3, Activity.FALSE);
        Activity a24 = new Activity(Topic.v3_ID, Activity.ACT_NUM_4, Activity.FALSE);
        Activity a25 = new Activity(Topic.r1_ID, Activity.ACT_NUM_1, Activity.FALSE);
        Activity a26 = new Activity(Topic.r1_ID, Activity.ACT_NUM_2, Activity.FALSE);

        addActivity(a1);
        addActivity(a2);
        addActivity(a3);
        addActivity(a4);
        addActivity(a5);
        addActivity(a6);
        addActivity(a7);
        addActivity(a8);
        addActivity(a9);
        addActivity(a10);
        addActivity(a11);
        addActivity(a12);
        addActivity(a13);
        addActivity(a14);
        addActivity(a15);
        addActivity(a16);
        addActivity(a17);
        addActivity(a18);
        addActivity(a19);
        addActivity(a20);
        addActivity(a21);
        addActivity(a22);
        addActivity(a23);
        addActivity(a24);
        addActivity(a25);
        addActivity(a26);

        /*
        Activity a1 = new Activity(Topic.t5_ID, Activity.ACT_NUM_1, Activity.FALSE);
        Activity a2 = new Activity(Topic.t5_ID, Activity.ACT_NUM_2, Activity.FALSE);
        Activity a3 = new Activity(Topic.t5_ID, Activity.ACT_NUM_3, Activity.FALSE);
        Activity a4 = new Activity(Topic.t5_ID, Activity.ACT_NUM_4, Activity.FALSE);

        addActivity(a1);
        addActivity(a2);
        addActivity(a3);
        addActivity(a4);
*/
    }

    private void addActivity(Activity activity) {
        ContentValues cv = new ContentValues();
        cv.put(ActivityTable.COLUMN_TOPIC_ID, activity.getTopicId());
        cv.put(ActivityTable.COLUMN_ACT_NUM, activity.getActivityNum());
        cv.put(ActivityTable.COLUMN_COMPLETED, activity.getCompleted());
        db.insert(ActivityTable.TABLE_NAME, null, cv);
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("Select the correct phrase:", "Our teacher is often late.", "Our teacher often is late.", "Is often our teacher late?", "Often our teacher is late", 1, Topic.g1_ID, Activity.ACT_NUM_1);
        Question q2 = new Question("Select the correct phrase:", "I am tired always.", "I am always tired.", "Always I am tired.", "Tired I am always.", 2, Topic.g1_ID, Activity.ACT_NUM_1);
        Question q3 = new Question("Select the correct phrase:", "My sister watches TV hardly ever.", "My sister doesn't hardly ever watch TV.", "My sister watches TV hardly ever.", "My sister hardly ever watches TV.", 4, Topic.g1_ID, Activity.ACT_NUM_1);
        Question q4 = new Question("Select the correct phrase:", "We never eat sushi.", "We eat sushi never.", "We eat never sushi.", "Never we eat sushi.", 1, Topic.g1_ID, Activity.ACT_NUM_1);
        Question q5 = new Question("Select the correct phrase:", "English they study every day.", "They study every day English.", "They every day study English.", "They study English every day.", 4, Topic.g1_ID, Activity.ACT_NUM_1);
        Question q6 = new Question("Select the correct phrase:", "He doesn't wake up early usually.", "Does he wake up usually early?", "He doesn't usually wake up early.", "He usually doesn't wake up early.", 3, Topic.g1_ID, Activity.ACT_NUM_1);
        Question q7 = new Question("Select the correct phrase:", "Do you go to the cinema often?", "Do you go often to the cinema?", "Do often you go to the cinema?", "Do you often go to the cinema?", 1, Topic.g1_ID, Activity.ACT_NUM_1);
        Question q8 = new Question("Select the correct phrase:", "I always wears a hat", "I always wear a hat", "I wear a hat always", "I wear always a hat", 2, Topic.g1_ID, Activity.ACT_NUM_1);
        Question q9 = new Question("Fill the gap with the correct phrase:", "I hate coffee. I _________ drink coffee.", "always", "often", "sometimes", "never", 4, Topic.g1_ID, Activity.ACT_NUM_2);
        Question q10 = new Question("Fill the gap with the correct phrase:", "I love music so I __________ listen to the radio", "always", "often", "sometimes", "never", 1, Topic.g1_ID, Activity.ACT_NUM_2);
        Question q11 = new Question("Fill the gap with the correct phrase:", "They go to Italy twice a year. They _______ go to Italy.", "usually", "often", "sometimes", "occassionally", 4, Topic.g1_ID, Activity.ACT_NUM_2);
        Question q12 = new Question("Fill the gap with the correct phrase:", "She likes swimming in the morning and she swims most days. She _______ swims in the morning", "usually", "often", "sometimes", "occassionally", 1, Topic.g1_ID, Activity.ACT_NUM_2);
        Question q13 = new Question("Fill the gap with the correct phrase:", "How ____________ do you study English?", "usually", "often", "sometimes", "occassionally", 2, Topic.g1_ID, Activity.ACT_NUM_2);
        Question q14 = new Question("Fill the gap with the correct phrase:", "Our teacher gives us homework every day. We _________ get homework.", "usually", "often", "always", "occassionally", 3, Topic.g1_ID, Activity.ACT_NUM_2);
        Question q15 = new Question("Fill the gap with the correct phrase:", "He goes to the cinema once a year. He _________ goes to the cinema.", "sometimes", "usually", "hardly ever", "never", 3, Topic.g1_ID, Activity.ACT_NUM_2);
        Question q16 = new Question("Fill the gap with the correct phrase:", "I don't like driving so I __________ walk to work.", "never", "hardly ever", "every day", "always", 4, Topic.g1_ID, Activity.ACT_NUM_2);
        Question q17 = new Question(R.drawable.everyday, "Select the correct adverb", "everyday", "twice a week", "never", "weekly", 1, Topic.g1_ID, Activity.ACT_NUM_3);
        Question q18 = new Question(R.drawable.never, "Select the correct adverb", "everyday", "twice a week", "never", "weekly", 3, Topic.g1_ID, Activity.ACT_NUM_3);
        Question q19 = new Question(R.drawable.twice_a_week, "Select the correct adverb", "everyday", "twice a week", "never", "weekly", 2, Topic.g1_ID, Activity.ACT_NUM_3);
        Question q20 = new Question(R.drawable.weekly, "Select the correct adverb", "everyday", "twice a week", "never", "weekly", 4, Topic.g1_ID, Activity.ACT_NUM_3);
        Question q21 = new Question(R.drawable.rarely, "Select the correct adverb", "always", "often", "rarely", "never", 3, Topic.g1_ID, Activity.ACT_NUM_3);
        Question q22 = new Question(R.drawable.always2, "Select the correct adverb", "always", "often", "rarely", "never", 1, Topic.g1_ID, Activity.ACT_NUM_3);
        Question q23 = new Question(R.drawable.never2, "Select the correct adverb", "always", "often", "rarely", "never", 4, Topic.g1_ID, Activity.ACT_NUM_3);
        Question q24 = new Question(R.drawable.usually2, "Select the correct adverb", "always", "usually", "rarely", "never", 2, Topic.g1_ID, Activity.ACT_NUM_3);
        Question q25 = new Question(R.drawable.weekly, "Select the correct adverb", "everyday", "twice a week", "never", "weekly", 4, Topic.g1_ID, Activity.ACT_NUM_4);
        Question q26 = new Question(R.drawable.rarely, "Select the correct adverb", "always", "often", "rarely", "never", 3, Topic.g1_ID, Activity.ACT_NUM_4);
        Question q27 = new Question(R.drawable.everyday, "Select the correct adverb", "everyday", "twice a week", "never", "weekly", 1, Topic.g1_ID, Activity.ACT_NUM_4);
        Question q28 = new Question(R.drawable.never, "Select the correct adverb", "everyday", "twice a week", "never", "weekly", 3, Topic.g1_ID, Activity.ACT_NUM_4);
        Question q29 = new Question("Fill the gap with the correct phrase:", "They go to Italy twice a year. They _______ go to Italy.", "usually", "often", "sometimes", "occassionally", 4, Topic.g1_ID, Activity.ACT_NUM_4);
        Question q30 = new Question("Fill the gap with the correct phrase:", "She likes swimming in the morning and she swims most days. She _______ swims in the morning", "usually", "often", "sometimes", "occassionally", 1, Topic.g1_ID, Activity.ACT_NUM_4);
        Question q31 = new Question("Fill the gap with the correct phrase:", "How ____________ do you study English?", "usually", "often", "sometimes", "occassionally", 2, Topic.g1_ID, Activity.ACT_NUM_4);
        Question q32 = new Question("Fill the gap with the correct phrase:", "Our teacher gives us homework every day. We _________ get homework.", "usually", "often", "always", "occassionally", 3, Topic.g1_ID, Activity.ACT_NUM_4);
        Question q33 = new Question("Select the correct phrase:", "I am tired always.", "I am always tired.", "Always I am tired.", "Tired I am always.", 2, Topic.g1_ID, Activity.ACT_NUM_4);
        Question q34 = new Question("Select the correct phrase:", "My sister watches TV hardly ever.", "My sister doesn't hardly ever watch TV.", "My sister watches TV hardly ever.", "My sister hardly ever watches TV.", 4, Topic.g1_ID, Activity.ACT_NUM_4);
        Question q35 = new Question("Select the correct phrase:", "We never eat sushi.", "We eat sushi never.", "We eat never sushi.", "Never we eat sushi.", 1, Topic.g1_ID, Activity.ACT_NUM_4);
        Question q36 = new Question("Select the correct phrase:", "English they study every day.", "They study every day English.", "They every day study English.", "They study English every day.", 4, Topic.g1_ID, Activity.ACT_NUM_4);
        Question q37 = new Question("Select the correct present simple form:", "She ________ strong", "has", "is", "are", "have", 2, Topic.g2_ID, Activity.ACT_NUM_1);
        Question q38 = new Question("Select the correct present simple form:", "I ________ cold", "have", "has", "am", "is", 3, Topic.g2_ID, Activity.ACT_NUM_1);
        Question q39 = new Question("Select the correct present simple form:", "The car ________ expensive", "are", "is", "has", "cost", 2, Topic.g2_ID, Activity.ACT_NUM_1);
        Question q40 = new Question("Select the correct present simple form:", "They always ________ pizza", "eat", "eats", "eating", "has", 1, Topic.g2_ID, Activity.ACT_NUM_1);
        Question q41 = new Question("Select the correct present simple form:", "They never ________ to the cinema", "goes", "going", "go", "visits", 3, Topic.g2_ID, Activity.ACT_NUM_1);
        Question q42 = new Question("Select the correct present simple form:", "He ________ 13 years old", "have", "are", "has", "is", 4, Topic.g2_ID, Activity.ACT_NUM_1);
        Question q43 = new Question("Select the correct present simple form:", "You ________ my friend", "has", "are", "go", "is", 2, Topic.g2_ID, Activity.ACT_NUM_1);
        Question q44 = new Question("Select the correct present simple form:", "He ________ blue eyes.", "have", "has", "is", "see", 2, Topic.g2_ID, Activity.ACT_NUM_1);
        Question q45 = new Question("Select the correct negative form of the phrase:", "He is a teacher", "He is not a teacher", "He is a teacher not", "He not is a teacher", "Teacher he is not", 1, Topic.g2_ID, Activity.ACT_NUM_2);
        Question q46 = new Question("Select the correct negative form of the phrase:", "They're in Rome", "They not are in Rome", "They are in Rome not", "They are not in Rome", "They is not in Rome", 3, Topic.g2_ID, Activity.ACT_NUM_2);
        Question q47 = new Question("Select the correct negative form of the phrase:", "I am very tired", "Tired I am not", "I am very not tired", "I am not tired very", "I am not very tired", 4, Topic.g2_ID, Activity.ACT_NUM_2);
        Question q48 = new Question("Select the correct negative form of the phrase:", "It's late", "It isn't not late", "It's late not", "It not is late", "It isn't late", 4, Topic.g2_ID, Activity.ACT_NUM_2);
        Question q49 = new Question("Select the correct negative form of the phrase:", "The house is big", "The house is not big", "The house is big not", "The house not is big", "The house not is big", 1, Topic.g2_ID, Activity.ACT_NUM_2);
        Question q50 = new Question("Select the correct negative form of the phrase:", "You are tall", "Not tall you are", "You are not tall", "You are tall not", "You're tall not", 2, Topic.g2_ID, Activity.ACT_NUM_2);
        Question q51 = new Question("Select the correct negative form of the phrase:", "She is my teacher", "My teacher is not she", "She is not my teacher", "She's isn't my teacher", "My teacher isn't she", 2, Topic.g2_ID, Activity.ACT_NUM_2);
        Question q52 = new Question("Select the correct negative form of the phrase:", "You are Spanish", "Spanish you are not", "Spanish you aren't", "You aren't Spanish", "You are Spanish not", 3, Topic.g2_ID, Activity.ACT_NUM_2);
        Question q53 = new Question("Select the correct response:", "Is he a docotor?", "He's not a doctor", "His not a doctor", "Yes, isn't he a doctor", "He's isn't a doctor", 1, Topic.g2_ID, Activity.ACT_NUM_3);
        Question q54 = new Question("Select the correct response:", "Are they in Rome?", "They isn't in Rome", "They're in Rome", "They's in Rome", "Their in Rome", 2, Topic.g2_ID, Activity.ACT_NUM_3);
        Question q55 = new Question("Select the correct response:", "Are you tired?", "I'm very am tired", "I am very not tired", "I'm not very tired", "No, I'm very tired", 3, Topic.g2_ID, Activity.ACT_NUM_3);
        Question q56 = new Question("Select the correct response:", "Is it late?", "It isn't not late", "It not is late", "It's not is late", "It's not late", 4, Topic.g2_ID, Activity.ACT_NUM_3);
        Question q57 = new Question("Select the correct response:", "Is the house big?", "The house isn't very big", "The house is very not big", "The house isn't very not big", "The house very isn't big", 1, Topic.g2_ID, Activity.ACT_NUM_3);
        Question q58 = new Question("Select the correct response:", "Am I tall?", "You've tall", "You're tall", "Your tall", "I'm tall", 2, Topic.g2_ID, Activity.ACT_NUM_3);
        Question q59 = new Question("Select the correct response:", "Is the bike green?", "No isn't green the bike", "Yes, the bike's not green", "No, the bike isn't green", "No is not green the bike green", 3, Topic.g2_ID, Activity.ACT_NUM_3);
        Question q60 = new Question("Select the correct response:", "Is he kind?", "His kind", "Kind he are", "He's kind", "His not kind", 4, Topic.g2_ID, Activity.ACT_NUM_3);
        Question q61 = new Question("Select the correct response:", "Is he a docotor?", "He's not a doctor", "His not a doctor", "Yes, isn't he a doctor", "He's isn't a doctor", 1, Topic.g2_ID, Activity.ACT_NUM_4);
        Question q62 = new Question("Select the correct response:", "Are they in Rome?", "They isn't in Rome", "They're in Rome", "They's in Rome", "Their in Rome", 2, Topic.g2_ID, Activity.ACT_NUM_4);
        Question q63 = new Question("Select the correct response:", "Are you tired?", "I'm very am tired", "I am very not tired", "I'm not very tired", "No, I'm very tired", 3, Topic.g2_ID, Activity.ACT_NUM_4);
        Question q64 = new Question("Select the correct response:", "Is it late?", "It isn't not late", "It not is late", "It's not is late", "It's not late", 4, Topic.g2_ID, Activity.ACT_NUM_4);
        Question q65 = new Question("Select the correct negative form of the phrase:", "They're in Rome", "They not are in Rome", "They are in Rome not", "They are not in Rome", "They is not in Rome", 3, Topic.g2_ID, Activity.ACT_NUM_4);
        Question q66 = new Question("Select the correct negative form of the phrase:", "I am very tired", "Tired I am not", "I am very not tired", "I am not tired very", "I am not very tired", 4, Topic.g2_ID, Activity.ACT_NUM_4);
        Question q67 = new Question("Select the correct negative form of the phrase:", "It's late", "It isn't not late", "It's late not", "It not is late", "It isn't late", 4, Topic.g2_ID, Activity.ACT_NUM_4);
        Question q68 = new Question("Select the correct negative form of the phrase:", "The house is big", "The house is not big", "The house is big not", "The house not is big", "The house not is big", 1, Topic.g2_ID, Activity.ACT_NUM_4);
        Question q69 = new Question("Select the correct present simple form:", "I ________ cold", "have", "has", "am", "is", 3, Topic.g2_ID, Activity.ACT_NUM_4);
        Question q70 = new Question("Select the correct present simple form:", "The car ________ expensive", "are", "is", "has", "cost", 2, Topic.g2_ID, Activity.ACT_NUM_4);
        Question q71 = new Question("Select the correct present simple form:", "They always ________ pizza", "eat", "eats", "eating", "has", 1, Topic.g2_ID, Activity.ACT_NUM_4);
        Question q72 = new Question("Select the correct present simple form:", "They never ________ to the cinema", "goes", "going", "go", "visits", 3, Topic.g2_ID, Activity.ACT_NUM_4);
        Question q73 = new Question("Select the correct question word:", "________ is your birthday?", "When", "Where", "How", "What", 1, Topic.g3_ID, Activity.ACT_NUM_1);
        Question q74 = new Question("Select the correct question word:", "________ music do you like?", "Where", "What", "Which", "When", 2, Topic.g3_ID, Activity.ACT_NUM_1);
        Question q75 = new Question("Select the correct question word:", "________ do you work?", "How", "Which", "Where", "What", 3, Topic.g3_ID, Activity.ACT_NUM_1);
        Question q76 = new Question("Select the correct question word:", "________ one is yours?", "Where", "When", "How", "What", 4, Topic.g3_ID, Activity.ACT_NUM_1);
        Question q77 = new Question("Select the correct question word:", "________ is your favourite film?", "What", "How", "Which", "When", 1, Topic.g3_ID, Activity.ACT_NUM_1);
        Question q78 = new Question("Select the correct question word:", "________ time is it?", "What", "How", "How", "Which", 2, Topic.g3_ID, Activity.ACT_NUM_1);
        Question q79 = new Question("Select the correct question word:", "________ do you get to school, by bus or train?", "Where", "Which", "How", "What", 3, Topic.g3_ID, Activity.ACT_NUM_1);
        Question q80 = new Question("Select the correct question word:", "________ much does the scarf cost?", "What", "Which", "When", "How", 4, Topic.g3_ID, Activity.ACT_NUM_1);
        Question q81 = new Question("Select the correct question word:", "________ brought the cake?", "Who", "When", "Where", "Why", 1, Topic.g3_ID, Activity.ACT_NUM_2);
        Question q82 = new Question("Select the correct question word:", "________ did they go there for?", "Why", "What", "How", "When", 2, Topic.g3_ID, Activity.ACT_NUM_2);
        Question q83 = new Question("Select the correct question word:", "________ film do you want to see?", "What", "How", "Which", "When", 3, Topic.g3_ID, Activity.ACT_NUM_2);
        Question q84 = new Question("Select the correct question word:", "________ does the train leave?", "What", "Why", "Which", "When", 4, Topic.g3_ID, Activity.ACT_NUM_2);
        Question q85 = new Question("Select the correct question word:", "________ is the youngest?", "Who", "What", "Why", "Which", 1, Topic.g3_ID, Activity.ACT_NUM_2);
        Question q86 = new Question("Select the correct question word:", "________ often do you go on holiday?", "What", "How", "When", "Which", 2, Topic.g3_ID, Activity.ACT_NUM_2);
        Question q87 = new Question("Select the correct question word:", "________ shirts do you have?", "How much", "How often", "How many", "How", 3, Topic.g3_ID, Activity.ACT_NUM_2);
        Question q88 = new Question("Select the correct question word:", "________ are you doing tomorrow?", "Where", "How", "When", "What", 4, Topic.g3_ID, Activity.ACT_NUM_2);
        Question q89 = new Question("Select the correct response:", "What colours does she like?", "She likes blue and yellow", "She does like blue and yellow", "She like blue and yellow", "She's like blue and yellow", 1, Topic.g3_ID, Activity.ACT_NUM_3);
        Question q90 = new Question("Select the correct response:", "When is your birthday?", "It is my 21st birthday", "My birthday is on Tuesday", "It's in Spain", "It's not my birthday", 2, Topic.g3_ID, Activity.ACT_NUM_3);
        Question q91 = new Question("Select the correct response:", "Why are you tired?", "I am always tired", "I am hardly ever tired", "I am tired because it's late", "Yes, I am tired", 3, Topic.g3_ID, Activity.ACT_NUM_3);
        Question q92 = new Question("Select the correct response:", "Where does she live?", "She lives with her dog", "Her house has many rooms", "She is from Manchester", "She lives in Edinburgh", 4, Topic.g3_ID, Activity.ACT_NUM_3);
        Question q93 = new Question("Select the correct response:", "How often do they go to the cinema?", "They go to the cinema once a week", "They go often to the cinema", "They go to the cinema on the weekend", "They go to the cinema at 7pm", 1, Topic.g3_ID, Activity.ACT_NUM_3);
        Question q94 = new Question("Select the correct response:", "How old is Sam?", "Sam has 40 years", "Sam is 40 years old", "Sam's birthday is next week", "Sam is not old", 2, Topic.g3_ID, Activity.ACT_NUM_3);
        Question q95 = new Question("Select the correct response:", "What do you study?", "I study at univeristy", " I study every day", "I study English", "I always study", 3, Topic.g3_ID, Activity.ACT_NUM_3);
        Question q96 = new Question("Select the correct response:", "Where is he from?", "He lives in Glasgow", "He arrives tomorrow", "He is from Scottish", "He is from Scotland", 4, Topic.g3_ID, Activity.ACT_NUM_3);
        Question q97 = new Question("Select the correct question word:", "________ is the youngest?", "Who", "What", "Why", "Which", 1, Topic.g3_ID, Activity.ACT_NUM_4);
        Question q98 = new Question("Select the correct question word:", "________ often do you go on holiday?", "What", "How", "When", "Which", 2, Topic.g3_ID, Activity.ACT_NUM_4);
        Question q99 = new Question("Select the correct question word:", "________ shirts do you have?", "How much", "How often", "How many", "How", 3, Topic.g3_ID, Activity.ACT_NUM_4);
        Question q100 = new Question("Select the correct question word:", "________ are you doing tomorrow?", "Where", "How", "When", "What", 4, Topic.g3_ID, Activity.ACT_NUM_4);
        Question q101 = new Question("Select the correct response:", "What colours does she like?", "She likes blue and yellow", "She does like blue and yellow", "She like blue and yellow", "She's like blue and yellow", 1, Topic.g3_ID, Activity.ACT_NUM_4);
        Question q102 = new Question("Select the correct response:", "When is your birthday?", "It is my 21st birthday", "My birthday is on Tuesday", "It's in Spain", "It's not my birthday", 2, Topic.g3_ID, Activity.ACT_NUM_4);
        Question q103 = new Question("Select the correct response:", "Why are you tired?", "I am always tired", "I am hardly ever tired", "I am tired because it's late", "Yes, I am tired", 3, Topic.g3_ID, Activity.ACT_NUM_4);
        Question q104 = new Question("Select the correct response:", "Where does she live?", "She lives with her dog", "Her house has many rooms", "She is from Manchester", "She lives in Edinburgh", 4, Topic.g3_ID, Activity.ACT_NUM_4);
        Question q105 = new Question("Select the correct question word:", "________ is your favourite film?", "What", "How", "Which", "When", 1, Topic.g3_ID, Activity.ACT_NUM_4);
        Question q106 = new Question("Select the correct question word:", "________ time is it?", "What", "How", "How", "Which", 2, Topic.g3_ID, Activity.ACT_NUM_4);
        Question q107 = new Question("Select the correct question word:", "________ do you get to school, by bus or train?", "Where", "Which", "How", "What", 3, Topic.g3_ID, Activity.ACT_NUM_4);
        Question q108 = new Question("Select the correct question word:", "________ much does the scarf cost?", "What", "Which", "When", "How", 4, Topic.g3_ID, Activity.ACT_NUM_4);
        Question q109 = new Question(R.drawable.t_shirt, "Select the correct word or phrase:", "shirt", "jumper", "hoodie", "t-shirt", 4, Topic.v1_ID, Activity.ACT_NUM_1);
        Question q110 = new Question(R.drawable.trainers, "Select the correct word or phrase:", "socks", "jumper", "boots", "trainers", 4, Topic.v1_ID, Activity.ACT_NUM_1);
        Question q111 = new Question(R.drawable.t_shirt, "Select the correct word or phrase:", "trainers", "trousers", "jacket", "top", 4, Topic.v1_ID, Activity.ACT_NUM_2);
        Question q112 = new Question(R.drawable.jacket, "Select the correct word or phrase:", "top", "boots", "skirt", "jacket", 4, Topic.v1_ID, Activity.ACT_NUM_2);
        Question q113 = new Question("Select the word that is being described:", "jacket", "jumper", "coat", "hoodie", 4, Topic.v1_ID, Activity.ACT_NUM_3);
        Question q114 = new Question("Select the word that is being described:", "skirt", "suit", "socks", "shorts", 4, Topic.v1_ID, Activity.ACT_NUM_3);
        Question q115 = new Question(R.drawable.jacket, "Select the correct word or phrase:", "top", "boots", "skirt", "jacket", 4, Topic.v1_ID, Activity.ACT_NUM_4);
        Question q116 = new Question("Select the word that is being described:", "jacket", "jumper", "coat", "hoodie", 4, Topic.v1_ID, Activity.ACT_NUM_4);
        Question q117 = new Question(R.drawable.jumper, "Select the correct word or phrase:", "coat", "dress", "jumper", "t-shirt", 3, Topic.v1_ID, Activity.ACT_NUM_4);
        Question q118 = new Question(R.drawable.trousers, "Select the correct word or phrase:", "trousers", "top", "shorts", "suit", 1, Topic.v1_ID, Activity.ACT_NUM_1);
        Question q119 = new Question(R.drawable.shorts, "Select the correct word or phrase:", "shorts", "skirt", "trousers", "dress", 1, Topic.v1_ID, Activity.ACT_NUM_1);
        Question q120 = new Question(R.drawable.dress, "Select the correct word or phrase:", "dress", "skirt", "suit", "top", 1, Topic.v1_ID, Activity.ACT_NUM_2);
        Question q121 = new Question(R.drawable.socks, "Select the correct word or phrase:", "socks", "top", "shoes", "trainers", 1, Topic.v1_ID, Activity.ACT_NUM_2);
        Question q122 = new Question("Select the word that is being described:", "trainers", "socks", "boots", "dress", 1, Topic.v1_ID, Activity.ACT_NUM_3);
        Question q123 = new Question("Select the word that is being described:", "socks", "shorts", "boots", "trainers", 1, Topic.v1_ID, Activity.ACT_NUM_3);
        Question q124 = new Question(R.drawable.socks, "Select the correct word or phrase:", "socks", "top", "shoes", "trainers", 1, Topic.v1_ID, Activity.ACT_NUM_4);
        Question q125 = new Question("Select the word that is being described:", "trainers", "socks", "boots", "dress", 1, Topic.v1_ID, Activity.ACT_NUM_4);
        Question q126 = new Question(R.drawable.t_shirt, "Select the correct word or phrase:", "shirt", "jumper", "hoodie", "t-shirt", 4, Topic.v1_ID, Activity.ACT_NUM_4);
        Question q127 = new Question(R.drawable.shirt, "Select the correct word or phrase:", "skirt", "shirt", "shirt", "jumper", 2, Topic.v1_ID, Activity.ACT_NUM_1);
        Question q128 = new Question(R.drawable.suit, "Select the correct word or phrase:", "t-shirt", "suit", "jacket", "jumper", 2, Topic.v1_ID, Activity.ACT_NUM_1);
        Question q129 = new Question(R.drawable.hoodie, "Select the correct word or phrase:", "t-shirt", "hoodie", "shirt", "coat", 2, Topic.v1_ID, Activity.ACT_NUM_2);
        Question q130 = new Question(R.drawable.boots, "Select the correct word or phrase:", "trainers", "boots", "socks", "dress", 2, Topic.v1_ID, Activity.ACT_NUM_2);
        Question q131 = new Question("Select the word that is being described:", "socks", "boots", "shoes", "trainers", 2, Topic.v1_ID, Activity.ACT_NUM_3);
        Question q132 = new Question("Select the word that is being described:", "jumper", "coat", "dress", "top", 2, Topic.v1_ID, Activity.ACT_NUM_3);
        Question q133 = new Question(R.drawable.boots, "Select the correct word or phrase:", "trainers", "boots", "socks", "dress", 2, Topic.v1_ID, Activity.ACT_NUM_4);
        Question q134 = new Question("Select the word that is being described:", "socks", "boots", "shoes", "trainers", 2, Topic.v1_ID, Activity.ACT_NUM_4);
        Question q135 = new Question(R.drawable.shorts, "Select the correct word or phrase:", "shorts", "skirt", "trousers", "dress", 1, Topic.v1_ID, Activity.ACT_NUM_4);
        Question q136 = new Question(R.drawable.skirt, "Select the correct word or phrase:", "coat", "top", "skirt", "shorts", 3, Topic.v1_ID, Activity.ACT_NUM_1);
        Question q137 = new Question(R.drawable.jumper, "Select the correct word or phrase:", "coat", "dress", "jumper", "t-shirt", 3, Topic.v1_ID, Activity.ACT_NUM_1);
        Question q138 = new Question(R.drawable.coat, "Select the correct word or phrase:", "shoes", "hoodie", "coat", "jumper", 3, Topic.v1_ID, Activity.ACT_NUM_2);
        Question q139 = new Question(R.drawable.shoes, "Select the correct word or phrase:", "coat", "t-shirt", "shoes", "hoodie", 3, Topic.v1_ID, Activity.ACT_NUM_2);
        Question q140 = new Question("Select the word that is being described:", "top", "dress", "jumper", "coat", 3, Topic.v1_ID, Activity.ACT_NUM_3);
        Question q141 = new Question("Select the word that is being described:", "top", "skirt", "jacket", "dress", 3, Topic.v1_ID, Activity.ACT_NUM_3);
        Question q142 = new Question(R.drawable.shoes, "Select the correct word or phrase:", "coat", "t-shirt", "shoes", "hoodie", 3, Topic.v1_ID, Activity.ACT_NUM_4);
        Question q143 = new Question("Select the word that is being described:", "top", "dress", "jumper", "coat", 3, Topic.v1_ID, Activity.ACT_NUM_4);
        Question q144 = new Question(R.drawable.suit, "Select the correct word or phrase:", "t-shirt", "suit", "jacket", "jumper", 2, Topic.v1_ID, Activity.ACT_NUM_4);
        Question q145 = new Question(R.drawable.scarf, "Select the correct word or phrase:", "gloves", "tie", "earrings", "scarf", 4, Topic.v2_ID, Activity.ACT_NUM_1);
        Question q146 = new Question(R.drawable.gloves, "Select the correct word or phrase:", "earrings", "hat", "scarf", "gloves", 4, Topic.v2_ID, Activity.ACT_NUM_1);
        Question q147 = new Question(R.drawable.earrings, "Select the correct word or phrase:", "ring", "necklace", "gloves", "earrings", 4, Topic.v2_ID, Activity.ACT_NUM_2);
        Question q148 = new Question(R.drawable.hairband, "Select the correct word or phrase:", "tie", "belt", "hat", "hairband", 4, Topic.v2_ID, Activity.ACT_NUM_2);
        Question q149 = new Question("Select the word that is being described:", "necklace", "tie", "bow tie", "scarf", 4, Topic.v2_ID, Activity.ACT_NUM_3);
        Question q150 = new Question("Select the word that is being described:", "bow tie", "gloves", "pocket", "rucksack", 4, Topic.v2_ID, Activity.ACT_NUM_3);
        Question q151 = new Question(R.drawable.hairband, "Select the correct word or phrase:", "tie", "belt", "hat", "hairband", 4, Topic.v2_ID, Activity.ACT_NUM_4);
        Question q152 = new Question("Select the word that is being described:", "necklace", "tie", "bow tie", "scarf", 4, Topic.v2_ID, Activity.ACT_NUM_4);
        Question q153 = new Question(R.drawable.bag, "Select the correct word or phrase:", "suitcase", "bag", "ring", "bow tie", 2, Topic.v2_ID, Activity.ACT_NUM_4);
        Question q154 = new Question(R.drawable.belt, "Select the correct word or phrase:", "belt", "pocket", "scarf", "bow tie", 1, Topic.v2_ID, Activity.ACT_NUM_1);
        Question q155 = new Question(R.drawable.hat, "Select the correct word or phrase:", "hat", "bag", "hairband", "necklace", 1, Topic.v2_ID, Activity.ACT_NUM_1);
        Question q156 = new Question(R.drawable.tie, "Select the correct word or phrase:", "tie", "belt", "suitcase", "bow tie", 1, Topic.v2_ID, Activity.ACT_NUM_2);
        Question q157 = new Question(R.drawable.necklace, "Select the correct word or phrase:", "necklace", "ring", "earrings", "watch", 1, Topic.v2_ID, Activity.ACT_NUM_2);
        Question q158 = new Question("Select the word that is being described:", "bracelet", "earrings", "ring", "necklace", 1, Topic.v2_ID, Activity.ACT_NUM_3);
        Question q159 = new Question("Select the word that is being described:", "tie", "necklace", "scarf", "earrings", 1, Topic.v2_ID, Activity.ACT_NUM_3);
        Question q160 = new Question(R.drawable.necklace, "Select the correct word or phrase:", "necklace", "ring", "earrings", "watch", 1, Topic.v2_ID, Activity.ACT_NUM_4);
        Question q161 = new Question("Select the word that is being described:", "bracelet", "earrings", "ring", "necklace", 1, Topic.v2_ID, Activity.ACT_NUM_4);
        Question q162 = new Question(R.drawable.pocket, "Select the correct word or phrase:", "scarf", "rucksack", "pocket", "tie", 3, Topic.v2_ID, Activity.ACT_NUM_4);
        Question q163 = new Question(R.drawable.rucksack, "Select the correct word or phrase:", "suitcase", "rucksack", "gloves", "pocket", 2, Topic.v2_ID, Activity.ACT_NUM_1);
        Question q164 = new Question(R.drawable.bag, "Select the correct word or phrase:", "suitcase", "bag", "ring", "bow tie", 2, Topic.v2_ID, Activity.ACT_NUM_1);
        Question q165 = new Question(R.drawable.watch, "Select the correct word or phrase:", "bracelet", "watch", "pocket", "hat", 2, Topic.v2_ID, Activity.ACT_NUM_2);
        Question q166 = new Question(R.drawable.ring, "Select the correct word or phrase:", "necklace", "ring", "earrings", "watch", 2, Topic.v2_ID, Activity.ACT_NUM_2);
        Question q167 = new Question("Select the word that is being described:", "tie", "necklace", "earrings", "ring", 2, Topic.v2_ID, Activity.ACT_NUM_3);
        Question q168 = new Question("Select the word that is being described:", "bracelet", "watch", "ring", "bag", 2, Topic.v2_ID, Activity.ACT_NUM_3);
        Question q169 = new Question(R.drawable.ring, "Select the correct word or phrase:", "necklace", "ring", "earrings", "watch", 2, Topic.v2_ID, Activity.ACT_NUM_4);
        Question q170 = new Question("Select the word that is being described:", "tie", "necklace", "earrings", "ring", 2, Topic.v2_ID, Activity.ACT_NUM_4);
        Question q171 = new Question(R.drawable.scarf, "Select the correct word or phrase:", "gloves", "tie", "earrings", "scarf", 4, Topic.v2_ID, Activity.ACT_NUM_4);
        Question q172 = new Question(R.drawable.pocket, "Select the correct word or phrase:", "scarf", "rucksack", "pocket", "tie", 3, Topic.v2_ID, Activity.ACT_NUM_1);
        Question q173 = new Question(R.drawable.suitcase, "Select the correct word or phrase:", "rucksack", "watch", "suitcase", "hairband", 3, Topic.v2_ID, Activity.ACT_NUM_1);
        Question q174 = new Question(R.drawable.bracelet, "Select the correct word or phrase:", "earrings", "belt", "bracelet", "necklace", 3, Topic.v2_ID, Activity.ACT_NUM_2);
        Question q175 = new Question(R.drawable.bowtie, "Select the correct word or phrase:", "tie", "watch", "bow tie", "belt", 3, Topic.v2_ID, Activity.ACT_NUM_2);
        Question q176 = new Question("Select the word that is being described:", "tie", "bow tie", "belt", "necklace", 3, Topic.v2_ID, Activity.ACT_NUM_3);
        Question q177 = new Question("Select the word that is being described:", "gloves", "hat", "pocket", "tie", 3, Topic.v2_ID, Activity.ACT_NUM_3);
        Question q178 = new Question(R.drawable.bowtie, "Select the correct word or phrase:", "tie", "watch", "bow tie", "belt", 3, Topic.v2_ID, Activity.ACT_NUM_4);
        Question q179 = new Question("Select the word that is being described:", "tie", "bow tie", "belt", "necklace", 3, Topic.v2_ID, Activity.ACT_NUM_4);
        Question q180 = new Question(R.drawable.hat, "Select the correct word or phrase:", "hat", "bag", "hairband", "necklace", 1, Topic.v2_ID, Activity.ACT_NUM_4);
        Question q181 = new Question(R.drawable.tourist, "Select the correct word or phrase:", "ticket", "map", "baggage", "tourist", 4, Topic.v3_ID, Activity.ACT_NUM_1);
        Question q182 = new Question(R.drawable.hotel, "Select the correct word or phrase:", "picnic", "ferry", "campsite", "hotel", 4, Topic.v3_ID, Activity.ACT_NUM_1);
        Question q183 = new Question(R.drawable.map, "Select the correct word or phrase:", "picnic", "baggage", "hotel", "map", 4, Topic.v3_ID, Activity.ACT_NUM_2);
        Question q184 = new Question(R.drawable.landmark, "Select the correct word or phrase:", "map", "hotel", "map", "landmark", 4, Topic.v3_ID, Activity.ACT_NUM_2);
        Question q185 = new Question("Select the word that is being described:", "hotel", "ferry", "landmark", "campsite", 4, Topic.v3_ID, Activity.ACT_NUM_3);
        Question q186 = new Question("Select the word that is being described:", "landmark", "airport", "queue", "train station", 4, Topic.v3_ID, Activity.ACT_NUM_3);
        Question q187 = new Question(R.drawable.landmark, "Select the correct word or phrase:", "map", "hotel", "map", "landmark", 4, Topic.v3_ID, Activity.ACT_NUM_4);
        Question q188 = new Question("Select the word that is being described:", "hotel", "ferry", "landmark", "campsite", 4, Topic.v3_ID, Activity.ACT_NUM_4);
        Question q189 = new Question(R.drawable.passport, "Select the correct word or phrase:", "flight", "passport", "map", "ticket", 4, Topic.v3_ID, Activity.ACT_NUM_4);
        Question q190 = new Question(R.drawable.tent, "Select the correct word or phrase:", "tent", "caravan", "campsite", "queue", 1, Topic.v3_ID, Activity.ACT_NUM_1);
        Question q191 = new Question(R.drawable.campsite, "Select the correct word or phrase:", "campsite", "ferry", "landmark", "ferry", 1, Topic.v3_ID, Activity.ACT_NUM_1);
        Question q192 = new Question(R.drawable.baggage, "Select the correct word or phrase:", "baggage", "picnic", "map", "queue", 1, Topic.v3_ID, Activity.ACT_NUM_2);
        Question q193 = new Question(R.drawable.queue, "Select the correct word or phrase:", "queue", "ticket", "hotel", "landmark", 1, Topic.v3_ID, Activity.ACT_NUM_2);
        Question q194 = new Question("Select the word that is being described:", "tent", "campsite", "train station", "ferry", 1, Topic.v3_ID, Activity.ACT_NUM_3);
        Question q195 = new Question("Select the word that is being described:", "passport", "ticket", "map", "queue", 1, Topic.v3_ID, Activity.ACT_NUM_3);
        Question q196 = new Question(R.drawable.queue, "Select the correct word or phrase:", "queue", "ticket", "hotel", "landmark", 1, Topic.v3_ID, Activity.ACT_NUM_4);
        Question q197 = new Question("Select the word that is being described:", "tent", "campsite", "train station", "ferry", 1, Topic.v3_ID, Activity.ACT_NUM_4);
        Question q198 = new Question(R.drawable.caravan, "Select the correct word or phrase:", "ferry", "landmark", "caravan", "campsite", 1, Topic.v3_ID, Activity.ACT_NUM_4);
        Question q199 = new Question(R.drawable.airport, "Select the correct word or phrase:", "flight", "airport", "station", "ticket", 2, Topic.v3_ID, Activity.ACT_NUM_1);
        Question q200 = new Question(R.drawable.passport, "Select the correct word or phrase:", "flight", "passport", "map", "ticket", 2, Topic.v3_ID, Activity.ACT_NUM_1);
        Question q201 = new Question(R.drawable.picnic, "Select the correct word or phrase:", "flight", "picnic", "queue", "baggage", 2, Topic.v3_ID, Activity.ACT_NUM_2);
        Question q202 = new Question(R.drawable.ferry, "Select the correct word or phrase:", "campsite", "ferry", "ticket", "flight", 2, Topic.v3_ID, Activity.ACT_NUM_2);
        Question q203 = new Question("Select the word that is being described:", "station", "airport", "campsite", "caravan", 2, Topic.v3_ID, Activity.ACT_NUM_3);
        Question q204 = new Question("Select the word that is being described:", "tent", "baggage", "ferry", "ticket", 2, Topic.v3_ID, Activity.ACT_NUM_3);
        Question q205 = new Question(R.drawable.ferry, "Select the correct word or phrase:", "campsite", "ferry", "ticket", "flight", 2, Topic.v3_ID, Activity.ACT_NUM_4);
        Question q206 = new Question("Select the word that is being described:", "station", "airport", "campsite", "caravan", 2, Topic.v3_ID, Activity.ACT_NUM_4);
        Question q207 = new Question(R.drawable.tourist, "Select the correct word or phrase:", "ticket", "map", "baggage", "tourist", 2, Topic.v3_ID, Activity.ACT_NUM_4);
        Question q208 = new Question(R.drawable.caravan, "Select the correct word or phrase:", "ferry", "landmark", "caravan", "campsite", 3, Topic.v3_ID, Activity.ACT_NUM_1);
        Question q209 = new Question(R.drawable.ticket, "Select the correct word or phrase:", "passport", "flight", "ticket", "train station", 3, Topic.v3_ID, Activity.ACT_NUM_1);
        Question q210 = new Question(R.drawable.train_station, "Select the correct word or phrase:", "ticket", "ferry", "train_station", "airport", 3, Topic.v3_ID, Activity.ACT_NUM_2);
        Question q211 = new Question(R.drawable.flight, "Select the correct word or phrase:", "airport", "ticket", "flight", "ferry", 3, Topic.v3_ID, Activity.ACT_NUM_2);
        Question q212 = new Question("Select the word that is being described:", "landmark", "flight", "tourist", "baggage", 3, Topic.v3_ID, Activity.ACT_NUM_3);
        Question q213 = new Question("Select the word that is being described:", "queue", "map", "picnic", "campsite", 3, Topic.v3_ID, Activity.ACT_NUM_3);
        Question q214 = new Question(R.drawable.flight, "Select the correct word or phrase:", "airport", "ticket", "flight", "ferry", 3, Topic.v3_ID, Activity.ACT_NUM_4);
        Question q215 = new Question("Select the word that is being described:", "landmark", "flight", "tourist", "baggage", 3, Topic.v3_ID, Activity.ACT_NUM_4);
        Question q216 = new Question(R.drawable.campsite, "Select the correct word or phrase:", "campsite", "ferry", "landmark", "ferry", 3, Topic.v3_ID, Activity.ACT_NUM_4);
        Question q217 = new Question(R.drawable.reading_plane, "Select the correct phrase:", "The plane has left the airport", "cancelled", "boarding", "delayed", "departed", 4, Topic.r1_ID, Activity.ACT_NUM_1);
        Question q218 = new Question(R.drawable.reading_plane, "Select the best answer:", "Can you board the flight to Lisbon?", "Yes", "Unknown", "Maybe", "No", 4, Topic.r1_ID, Activity.ACT_NUM_2);
        Question q219 = new Question(R.drawable.reading_plane, "Select the correct phrase:", "The plane is not going to leave", "cancelled", "boarding", "delayed", "departed", 1, Topic.r1_ID, Activity.ACT_NUM_1);
        Question q220 = new Question(R.drawable.reading_plane, "Select the best answer:", "Can you get on the flight to Manchester?", "Yes", "No", "Maybe", "Only if you're quick", 1, Topic.r1_ID, Activity.ACT_NUM_2);
        Question q221 = new Question(R.drawable.reading_plane, "Select the best answer:", "Can you get on flight KL1008?", "Only if you're quick", "No", "Unknown", "Yes", 1, Topic.r1_ID, Activity.ACT_NUM_2);
        Question q222 = new Question(R.drawable.reading_plane, "Select the correct phrase:", "You can get on the plane now", "cancelled", "boarding", "delayed", "departed", 2, Topic.r1_ID, Activity.ACT_NUM_1);
        Question q223 = new Question(R.drawable.reading_plane, "Select the best answer:", "Where will flight LH9354 land?", "Copenhagen", "Chicago", "Nice", "Manchester", 2, Topic.r1_ID, Activity.ACT_NUM_2);
        Question q224 = new Question(R.drawable.reading_plane, "Select the correct phrase:", "The plane is late", "cancelled", "boarding", "delayed", "departed", 3, Topic.r1_ID, Activity.ACT_NUM_1);
        Question q225 = new Question(R.drawable.reading_plane, "Select the best answer:", "What time will flight BA346 depart?", "0.423611111111111", "Cancelled", "Unknown", "0.430555555555556", 3, Topic.r1_ID, Activity.ACT_NUM_2);

        addQuestion(q1);
        addQuestion(q2);
        addQuestion(q3);
        addQuestion(q4);
        addQuestion(q5);
        addQuestion(q6);
        addQuestion(q7);
        addQuestion(q8);
        addQuestion(q9);
        addQuestion(q10);
        addQuestion(q11);
        addQuestion(q12);
        addQuestion(q13);
        addQuestion(q14);
        addQuestion(q15);
        addQuestion(q16);
        addQuestion(q17);
        addQuestion(q18);
        addQuestion(q19);
        addQuestion(q20);
        addQuestion(q21);
        addQuestion(q22);
        addQuestion(q23);
        addQuestion(q24);
        addQuestion(q25);
        addQuestion(q26);
        addQuestion(q27);
        addQuestion(q28);
        addQuestion(q29);
        addQuestion(q30);
        addQuestion(q31);
        addQuestion(q32);
        addQuestion(q33);
        addQuestion(q34);
        addQuestion(q35);
        addQuestion(q36);
        addQuestion(q37);
        addQuestion(q38);
        addQuestion(q39);
        addQuestion(q40);
        addQuestion(q41);
        addQuestion(q42);
        addQuestion(q43);
        addQuestion(q44);
        addQuestion(q45);
        addQuestion(q46);
        addQuestion(q47);
        addQuestion(q48);
        addQuestion(q49);
        addQuestion(q50);
        addQuestion(q51);
        addQuestion(q52);
        addQuestion(q53);
        addQuestion(q54);
        addQuestion(q55);
        addQuestion(q56);
        addQuestion(q57);
        addQuestion(q58);
        addQuestion(q59);
        addQuestion(q60);
        addQuestion(q61);
        addQuestion(q62);
        addQuestion(q63);
        addQuestion(q64);
        addQuestion(q65);
        addQuestion(q66);
        addQuestion(q67);
        addQuestion(q68);
        addQuestion(q69);
        addQuestion(q70);
        addQuestion(q71);
        addQuestion(q72);
        addQuestion(q73);
        addQuestion(q74);
        addQuestion(q75);
        addQuestion(q76);
        addQuestion(q77);
        addQuestion(q78);
        addQuestion(q79);
        addQuestion(q80);
        addQuestion(q81);
        addQuestion(q82);
        addQuestion(q83);
        addQuestion(q84);
        addQuestion(q85);
        addQuestion(q86);
        addQuestion(q87);
        addQuestion(q88);
        addQuestion(q89);
        addQuestion(q90);
        addQuestion(q91);
        addQuestion(q92);
        addQuestion(q93);
        addQuestion(q94);
        addQuestion(q95);
        addQuestion(q96);
        addQuestion(q97);
        addQuestion(q98);
        addQuestion(q99);
        addQuestion(q100);
        addQuestion(q101);
        addQuestion(q102);
        addQuestion(q103);
        addQuestion(q104);
        addQuestion(q105);
        addQuestion(q106);
        addQuestion(q107);
        addQuestion(q108);
        addQuestion(q109);
        addQuestion(q110);
        addQuestion(q111);
        addQuestion(q112);
        addQuestion(q113);
        addQuestion(q114);
        addQuestion(q115);
        addQuestion(q116);
        addQuestion(q117);
        addQuestion(q118);
        addQuestion(q119);
        addQuestion(q120);
        addQuestion(q121);
        addQuestion(q122);
        addQuestion(q123);
        addQuestion(q124);
        addQuestion(q125);
        addQuestion(q126);
        addQuestion(q127);
        addQuestion(q128);
        addQuestion(q129);
        addQuestion(q130);
        addQuestion(q131);
        addQuestion(q132);
        addQuestion(q133);
        addQuestion(q134);
        addQuestion(q135);
        addQuestion(q136);
        addQuestion(q137);
        addQuestion(q138);
        addQuestion(q139);
        addQuestion(q140);
        addQuestion(q141);
        addQuestion(q142);
        addQuestion(q143);
        addQuestion(q144);
        addQuestion(q145);
        addQuestion(q146);
        addQuestion(q147);
        addQuestion(q148);
        addQuestion(q149);
        addQuestion(q150);
        addQuestion(q151);
        addQuestion(q152);
        addQuestion(q153);
        addQuestion(q154);
        addQuestion(q155);
        addQuestion(q156);
        addQuestion(q157);
        addQuestion(q158);
        addQuestion(q159);
        addQuestion(q160);
        addQuestion(q161);
        addQuestion(q162);
        addQuestion(q163);
        addQuestion(q164);
        addQuestion(q165);
        addQuestion(q166);
        addQuestion(q167);
        addQuestion(q168);
        addQuestion(q169);
        addQuestion(q170);
        addQuestion(q171);
        addQuestion(q172);
        addQuestion(q173);
        addQuestion(q174);
        addQuestion(q175);
        addQuestion(q176);
        addQuestion(q177);
        addQuestion(q178);
        addQuestion(q179);
        addQuestion(q180);
        addQuestion(q181);
        addQuestion(q182);
        addQuestion(q183);
        addQuestion(q184);
        addQuestion(q185);
        addQuestion(q186);
        addQuestion(q187);
        addQuestion(q188);
        addQuestion(q189);
        addQuestion(q190);
        addQuestion(q191);
        addQuestion(q192);
        addQuestion(q193);
        addQuestion(q194);
        addQuestion(q195);
        addQuestion(q196);
        addQuestion(q197);
        addQuestion(q198);
        addQuestion(q199);
        addQuestion(q200);
        addQuestion(q201);
        addQuestion(q202);
        addQuestion(q203);
        addQuestion(q204);
        addQuestion(q205);
        addQuestion(q206);
        addQuestion(q207);
        addQuestion(q208);
        addQuestion(q209);
        addQuestion(q210);
        addQuestion(q211);
        addQuestion(q212);
        addQuestion(q213);
        addQuestion(q214);
        addQuestion(q215);
        addQuestion(q216);
        addQuestion(q217);
        addQuestion(q218);
        addQuestion(q219);
        addQuestion(q220);
        addQuestion(q221);
        addQuestion(q222);
        addQuestion(q223);
        addQuestion(q224);
        addQuestion(q225);

    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_INSTRUCTION, question.getInstruction());
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_IMAGE_REF, question.getImageRef());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_OPTION4, question.getOption4());
        cv.put(QuestionsTable.COLUMN_ANSWER_NUM, question.getAnswerNr());
        cv.put(QuestionsTable.COLUMN_TOPIC_ID, question.getTopicId());
        cv.put(QuestionsTable.COLUMN_ACT_NUM, question.getActivityNum());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    private void fillVocabTable() {
        Vocabulary v1 = new Vocabulary(Topic.v1_ID, "suit", "A set of clothes made of the same cloth, including a jacket and trousers or a skirt");
        Vocabulary v2 = new Vocabulary(Topic.v1_ID, "skirt", "A piece of clothing often worn by a woman or girl that hangs from the middle part of the body");
        Vocabulary v3 = new Vocabulary(Topic.v1_ID, "shirt", "A piece of clothing worn on the upper part of the body, made of light cloth, with sleeves and usually with a collar and buttons down the front");
        Vocabulary v4 = new Vocabulary(Topic.v1_ID, "jumper", "(U.K) a piece of clothing for the upper part of the body, made of wool or cotton, with long sleeves (= arms) and no buttons, also known as a sweater");
        Vocabulary v5 = new Vocabulary(Topic.v1_ID, "t-shirt", "An informal shirt, usually with short sleeves (= arms) no collar or buttons, or just a few buttons at the top");
        Vocabulary v6 = new Vocabulary(Topic.v1_ID, "trainers", "(U.K) a shoe that you wear for sports or as informal clothing, also known as sneakers (U.S)");
        Vocabulary v7 = new Vocabulary(Topic.v1_ID, "trousers", "A piece of clothing that covers the lower body and is divided into two parts to cover each leg separately");
        Vocabulary v8 = new Vocabulary(Topic.v1_ID, "shorts", "Short trousers that end above or at the knee");
        Vocabulary v9 = new Vocabulary(Topic.v1_ID, "dress", "A piece of clothing often worn by women that is made in one piece and covers the body down to the legs, sometimes reaching to below the knees, or to the ankles");
        Vocabulary v10 = new Vocabulary(Topic.v1_ID, "hoodie", "A jacket or a sweatshirt with a hood");
        Vocabulary v11 = new Vocabulary(Topic.v1_ID, "coat", "A piece of outdoor clothing that is worn over other clothes to keep warm or dry. Coats have sleeves (= parts covering the arms) and may be long or short.");
        Vocabulary v12 = new Vocabulary(Topic.v1_ID, "top", "A piece of clothing worn on the upper part of the body");
        Vocabulary v13 = new Vocabulary(Topic.v1_ID, "jacket", "A piece of clothing worn on the top half of the body over a shirt, etc. that has arms and fastens down the front; a short, light coat");
        Vocabulary v14 = new Vocabulary(Topic.v1_ID, "shoes", "One of a pair of objects usually made of leather or plastic that you wear on your feet");
        Vocabulary v15 = new Vocabulary(Topic.v1_ID, "boots", "A strong shoe that covers the foot and ankle and often the lower part of the leg");
        Vocabulary v16 = new Vocabulary(Topic.v1_ID, "socks", "A piece of clothing that is worn over the foot, ankle and lower part of the leg, especially inside a shoe");
        Vocabulary v17 = new Vocabulary(Topic.v2_ID, "belt", "A long narrow piece of leather, cloth, etc. that you wear around the middle part of your body");
        Vocabulary v18 = new Vocabulary(Topic.v2_ID, "rucksack", "(U.K) a bag that you carry on your back, made of strong material and often used by people who go climbing or walking, also known as a backpack (U.S)");
        Vocabulary v19 = new Vocabulary(Topic.v2_ID, "pocket", "A small piece of material like a small bag sewn into or onto a piece of clothing so that you can carry things in it");
        Vocabulary v20 = new Vocabulary(Topic.v2_ID, "scarf", "A piece of cloth that is worn around the neck, for example to keep warm or for decoration. Some people also wear scarves over their shoulders or hair.");
        Vocabulary v21 = new Vocabulary(Topic.v2_ID, "hat", "A piece of clothing made to fit the head, often with a brim (= a flat edge that sticks out), usually worn out of doors");
        Vocabulary v22 = new Vocabulary(Topic.v2_ID, "bag", "A container made of cloth, leather, plastic or paper, used to carry things in, especially when shopping or travelling");
        Vocabulary v23 = new Vocabulary(Topic.v2_ID, "suitcase", "A case with flat sides and a handle, used for carrying clothes, etc. when you are travelling");
        Vocabulary v24 = new Vocabulary(Topic.v2_ID, "gloves", "A piece of clothing for the hand, made of wool, leather, etc. with separate parts for each finger and the thumb");
        Vocabulary v25 = new Vocabulary(Topic.v2_ID, "tie", "A long, narrow piece of cloth worn around the neck, with a knot in front, also known as a necktie (U.S)");
        Vocabulary v26 = new Vocabulary(Topic.v2_ID, "watch", "A type of small clock that you wear on your wrist, or (in the past) carried in your pocket");
        Vocabulary v27 = new Vocabulary(Topic.v2_ID, "bracelet", "A piece of jewellery worn around the wrist or arm");
        Vocabulary v28 = new Vocabulary(Topic.v2_ID, "earrings", "A piece(s) of jewellery that you fasten in or on your ear");
        Vocabulary v29 = new Vocabulary(Topic.v2_ID, "necklace", "A piece of jewellery worn around the neck");
        Vocabulary v30 = new Vocabulary(Topic.v2_ID, "ring", "A piece of jewellery that you wear on your finger, consisting of a round band of gold, silver, etc., sometimes decorated with precious stones");
        Vocabulary v31 = new Vocabulary(Topic.v2_ID, "bow tie", "A tie that is tied in the shape of a bow and that does not hang down");
        Vocabulary v32 = new Vocabulary(Topic.v2_ID, "hairband", "A narrow piece of cloth or curved plastic worn in the hair, that fits closely over the top of the head and behind the ears");
        Vocabulary v33 = new Vocabulary(Topic.v3_ID, "tent", "A shelter made of a large sheet of canvas, nylon, etc. that is supported by poles and ropes fixed to the ground, and is used especially for camping");
        Vocabulary v34 = new Vocabulary(Topic.v3_ID, "airport", "A place where planes land and take off and that has buildings for passengers to wait in");
        Vocabulary v35 = new Vocabulary(Topic.v3_ID, "caravan", "A road vehicle without an engine that is pulled by a car, designed for people to live and sleep in, especially when they are on holiday");
        Vocabulary v36 = new Vocabulary(Topic.v3_ID, "tourist", "A person who is travelling or visiting a place for pleasure");
        Vocabulary v37 = new Vocabulary(Topic.v3_ID, "campsite", "A place where people on holiday can put up their tents, park their caravan, etc., often with toilets, water, etc.");
        Vocabulary v38 = new Vocabulary(Topic.v3_ID, "passport", "An official document that identifies you as a citizen of a particular country, and that you may have to show when you enter or leave a country");
        Vocabulary v39 = new Vocabulary(Topic.v3_ID, "ticket", "A printed piece of paper, or a message or image received on your phone or computer, that gives you the right to travel on a particular bus, train, etc. or to go into a theatre, etc.");
        Vocabulary v40 = new Vocabulary(Topic.v3_ID, "hotel", "A building where people stay, usually for a short time, paying for their rooms and meals");
        Vocabulary v41 = new Vocabulary(Topic.v3_ID, "baggage", "Bags, cases, etc. that contain somebody's clothes and things when they are travelling");
        Vocabulary v42 = new Vocabulary(Topic.v3_ID, "picnic", "An occasion when people pack a meal and take it to eat outdoors, especially in the countryside");
        Vocabulary v43 = new Vocabulary(Topic.v3_ID, "train station", "A place where trains stop so that passengers can get on and off; the buildings connected with this");
        Vocabulary v44 = new Vocabulary(Topic.v3_ID, "map", "A drawing or plan of the earth’s surface or part of it, showing countries, towns, rivers, etc.");
        Vocabulary v45 = new Vocabulary(Topic.v3_ID, "queue", "(U.K) A line of people, cars, etc. waiting for something or to do something, also known as a line (U.S)");
        Vocabulary v46 = new Vocabulary(Topic.v3_ID, "ferry", "A boat or ship that carries people, vehicles and goods across a river or across a narrow part of the sea");
        Vocabulary v47 = new Vocabulary(Topic.v3_ID, "flight", "A journey made by air, especially in a plane");
        Vocabulary v48 = new Vocabulary(Topic.v3_ID, "landmark", "Something, such as a large building, that you can see clearly from a distance and that will help you to know where you are");

        addVocab(v1);
        addVocab(v2);
        addVocab(v3);
        addVocab(v4);
        addVocab(v5);
        addVocab(v6);
        addVocab(v7);
        addVocab(v8);
        addVocab(v9);
        addVocab(v10);
        addVocab(v11);
        addVocab(v12);
        addVocab(v13);
        addVocab(v14);
        addVocab(v15);
        addVocab(v16);
        addVocab(v17);
        addVocab(v18);
        addVocab(v19);
        addVocab(v20);
        addVocab(v21);
        addVocab(v22);
        addVocab(v23);
        addVocab(v24);
        addVocab(v25);
        addVocab(v26);
        addVocab(v27);
        addVocab(v28);
        addVocab(v29);
        addVocab(v30);
        addVocab(v31);
        addVocab(v32);
        addVocab(v33);
        addVocab(v34);
        addVocab(v35);
        addVocab(v36);
        addVocab(v37);
        addVocab(v38);
        addVocab(v39);
        addVocab(v40);
        addVocab(v41);
        addVocab(v42);
        addVocab(v43);
        addVocab(v44);
        addVocab(v45);
        addVocab(v46);
        addVocab(v47);
        addVocab(v48);

        /*
        Vocabulary v1 = new Vocabulary(Topic.t27_ID, "plops", "insulting word used to express frustration");
        Vocabulary v2 = new Vocabulary(Topic.t27_ID, "tnetennba", "There are no known uses of the word, prior to the IT Crowd.");
        Vocabulary v3 = new Vocabulary(Topic.t26_ID, "plops", "insulting word used to express frustration");
        Vocabulary v4 = new Vocabulary(Topic.t26_ID, "tnetennba", "There are no known uses of the word, prior to the IT Crowd.");
        Vocabulary v5 = new Vocabulary(Topic.t27_ID, "plops", "insulting word used to express frustration");
        Vocabulary v6 = new Vocabulary(Topic.t26_ID, "tnetennba", "There are no known uses of the word, prior to the IT Crowd.");
        Vocabulary v7 = new Vocabulary(Topic.t26_ID, "tnetennba", "There are no known uses of the word, prior to the IT Crowd.");
        Vocabulary v8 = new Vocabulary(Topic.t27_ID, "plops", "insulting word used to express frustration");
        Vocabulary v9 = new Vocabulary(Topic.t26_ID, "tnetennba", "There are no known uses of the word, prior to the IT Crowd.");
        Vocabulary v10 = new Vocabulary(Topic.t26_ID, "tnetennba", "There are no known uses of the word, prior to the IT Crowd.");
        Vocabulary v11 = new Vocabulary(Topic.t26_ID, "tnetennba", "There are no known uses of the word, prior to the IT Crowd.");
        Vocabulary v12 = new Vocabulary(Topic.t27_ID, "plops", "insulting word used to express frustration");
        Vocabulary v13 = new Vocabulary(Topic.t27_ID, "plops", "insulting word used to express frustration");


        addVocab(v1);
        addVocab(v2);
        addVocab(v3);
        addVocab(v4);
        addVocab(v5);
        addVocab(v6);
        addVocab(v7);
        addVocab(v8);
        addVocab(v9);
        addVocab(v10);
        addVocab(v11);
        addVocab(v12);
        addVocab(v13);
*/
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
                //question.setId(c.getInt(c.getColumnIndex((QuestionsTable._ID))));
                question.setInstruction(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_INSTRUCTION)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setImageRef(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_IMAGE_REF)));
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
                //question.setId(c.getInt(c.getColumnIndex((QuestionsTable._ID))));
                question.setInstruction(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_INSTRUCTION)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setImageRef(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_IMAGE_REF)));
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

    public ArrayList<Vocabulary> getVocab(int topicId) {
        db = getReadableDatabase();
        ArrayList<Vocabulary> vocabList = new ArrayList<>();

        String selection = VocabTable.COLUMN_TOPIC_ID + " = ? ";
        String[] selectionArgs = new String[] {String.valueOf(topicId)};

        Cursor c = db.query(
                VocabTable.TABLE_NAME,
                null, //returns all columns
                selection,
                selectionArgs,
                null,
                null,
                VocabTable.COLUMN_NAME
        );
        if (c.moveToFirst()) {
            do {
                Vocabulary vocabulary = new Vocabulary();
                //vocabulary.setId(c.getInt(c.getColumnIndex((VocabTable._ID))));
                vocabulary.setTopicId(c.getInt(c.getColumnIndex(VocabTable.COLUMN_TOPIC_ID)));
                vocabulary.setName(c.getString(c.getColumnIndex(VocabTable.COLUMN_NAME)));
                vocabulary.setDefinition(c.getString(c.getColumnIndex(VocabTable.COLUMN_DEFINITION)));
                vocabList.add(vocabulary);
            } while (c.moveToNext());
        }
        c.close();
        return vocabList;
    }

    /**a method to return an array list of topics with >1 completed activity**/
    public ArrayList<Topic> getTopicProgress() {
        db = getReadableDatabase();
        ArrayList<Topic> progressList = new ArrayList<>();

        //String[] selectionArgs = null;
        String selection = TopicsTable.COLUMN_ACT_COMPLETED + " > 0 ";
        Cursor c = db.query(
                TopicsTable.TABLE_NAME,
                null,
                selection,
                null,
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
                progressList.add(topic);
            } while (c.moveToNext());
        }
        c.close();
        return progressList;
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

    public String getTopicInfo(int topicId) {
        String topic_info = "";
       // db = getReadableDatabase();
        String[] selectionArgs = new String[]{String.valueOf(topicId)};
        String selection = TopicsTable._ID + " = ? ";
        Cursor c = db.query(
                TopicsTable.TABLE_NAME,
                new String[]{TopicsTable.COLUMN_INFO},
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        if (c.moveToFirst()) {
            do {
                Topic topic = new Topic();
                topic.setInformation(c.getString(c.getColumnIndex(TopicsTable.COLUMN_INFO)));
                topic_info = (String) topic.getInformation();
            } while (c.moveToNext());
        }
        c.close();
        return topic_info;
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
    /**method to update the activities_completed column in the Topics Table
     * by counting the number of completed activities from activity table*/
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
