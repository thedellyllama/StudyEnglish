package com.del.studyenglish1;

public class Topic {
    public static final int t1_ID = 1;
    public static final int t2_ID = 2;
    public static final int t3_ID = 3;
    public static final int t4_ID = 4;
    public static final int t5_ID = 5;
    public static final int t6_ID = 6;
    public static final int t7_ID = 7;
    public static final int t8_ID = 8;
    public static final int t9_ID = 9;
    public static final int t10_ID = 10;
    public static final int t11_ID = 11;
    public static final int t12_ID = 12;
    public static final int t13_ID = 13;
    public static final int t14_ID = 14;

    public static final int t26_ID = 26;
    public static final int t27_ID = 27;

    public static final String DIFFICULTY_A1 = "A1";
    public static final String DIFFICULTY_A2 = "A2";
    public static final String DIFFICULTY_B1 = "B1";
    public static final String DIFFICULTY_B2 = "B2";
    public static final String DIFFICULTY_C1 = "C1";

    public static final String TYPE_READING = "READING";
    public static final String TYPE_GRAMMAR = "GRAMMAR";
    public static final String TYPE_VOCABULARY = "VOCABULARY";

    public static final int NUM_GRAMMAR = 4;
    public static final int NUM_VOCAB = 4;
    public static final int NUM_READING = 2;

    private int id;
    private String name;
    private String difficulty;
    private String type;
    private int activitiesCount;
    private int activitiesCompleted;

    public Topic() {
    }

    public Topic(String name, String difficulty, String type, int activitiesCount) {
        this.name = name;
        this.difficulty = difficulty;
        this.type = type;
        this.activitiesCount = activitiesCount;
        activitiesCompleted = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getActivitiesCount() {
        return activitiesCount;
    }

    public void setActivitiesCount(int activitiesCount) {
        this.activitiesCount = activitiesCount;
    }

    public int getActivitiesCompleted() {
        return activitiesCompleted;
    }

    public void setActivitiesCompleted(int activitiesCompleted) {
        this.activitiesCompleted = activitiesCompleted;
    }

    //so the adapter returns to the name of the topic instead of class name
    @Override
    public String toString() {
        return getName();
    }


    //list of all types
    public static String[] getAllActivityTypes() {
        return new String[] {
                TYPE_GRAMMAR,
                TYPE_VOCABULARY,
                TYPE_READING
        };
    }

    //list of difficulties
    public static String[] getAllDifficultyLevels() {
        return new String[] {
                DIFFICULTY_A1,
                DIFFICULTY_A2,
                DIFFICULTY_B1,
                DIFFICULTY_B2,
                DIFFICULTY_C1
        };
    }
}


