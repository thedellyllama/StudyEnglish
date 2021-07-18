package com.del.studyenglish1;

public class Topic {
    public static final int ADVERBS_OF_FREQUENCY = 1;
    public static final int ARTICLES_1 = 2;
    public static final int ARTICLES_2 = 3;
    public static final int COUNTABLE_UNCOUNTABLE_1 = 4;
    public static final int ADJECTIVES1 = 5;
    public static final int PAST_CONTINUOUS = 6;
    public static final int USED_TO = 7;
    public static final int ADJECTIVES2 = 8;

    public static final String DIFFICULTY_A1 = "A1";
    public static final String DIFFICULTY_A2 = "A2";
    public static final String DIFFICULTY_B1 = "B1";
    public static final String DIFFICULTY_B2 = "B2";
    public static final String DIFFICULTY_C1 = "C1";

    public static final String TYPE_READING = "Reading";
    public static final String TYPE_GRAMMAR = "Grammar";
    public static final String TYPE_VOCABULARY = "Vocabulary";

    private int id;
    private String name;
    private String difficulty;
    private String type;

    public Topic() {
    }

    public Topic(String name, String difficulty, String type) {
        this.name = name;
        this.difficulty = difficulty;
        this.type = type;
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

    //so the adapter returns to the name of the topic instead of class name
    @Override
    public String toString() {
        return getName();
    }
}


