package com.del.studyenglish1;

public class Topic {

    private int id;
    private String name;
    private String difficulty;
    private String type;
    private int activitiesCount;
    private int activitiesCompleted;
    private String information;

    public static final int g1_ID = 1;
    public static final int g2_ID = 2;
    public static final int g3_ID = 3;
    public static final int g4_ID = 4;
    public static final int g5_ID = 5;
    public static final int g6_ID = 6;
    public static final int g7_ID = 7;
    public static final int g8_ID = 8;
    public static final int g9_ID = 9;
    public static final int g10_ID = 10;
    public static final int g11_ID = 11;
    public static final int g12_ID = 12;
    public static final int g13_ID = 13;
    public static final int g14_ID = 14;
    public static final int g15_ID = 15;
    public static final int g16_ID = 16;
    public static final int g17_ID = 17;
    public static final int g18_ID = 18;
    public static final int g19_ID = 19;
    public static final int g20_ID = 20;
    public static final int g21_ID = 21;
    public static final int g22_ID = 22;
    public static final int g23_ID = 23;
    public static final int g24_ID = 24;
    public static final int g25_ID = 25;

    public static final int v1_ID = 26;
    public static final int v2_ID = 27;
    public static final int v3_ID = 28;
    public static final int v4_ID = 29;
    public static final int v5_ID = 30;
    public static final int v6_ID = 31;
    public static final int v7_ID = 32;
    public static final int v8_ID = 33;
    public static final int v9_ID = 34;
    public static final int v10_ID = 35;
    public static final int v11_ID = 36;
    public static final int v12_ID = 37;
    public static final int v13_ID = 38;
    public static final int v14_ID = 39;
    public static final int v15_ID = 40;
    public static final int v16_ID = 41;
    public static final int v17_ID = 42;
    public static final int v18_ID = 43;
    public static final int v19_ID = 44;
    public static final int v20_ID = 45;
    public static final int v21_ID = 46;
    public static final int v22_ID = 47;
    public static final int v23_ID = 48;
    public static final int v24_ID = 49;
    public static final int v25_ID = 50;

    public static final int r1_ID = 51;
    public static final int r2_ID = 52;
    public static final int r3_ID = 53;
    public static final int r4_ID = 54;
    public static final int r5_ID = 55;
    public static final int r6_ID = 56;
    public static final int r7_ID = 57;
    public static final int r8_ID = 58;
    public static final int r9_ID = 59;
    public static final int r10_ID = 60;
    public static final int r11_ID = 61;
    public static final int r12_ID = 62;
    public static final int r13_ID = 63;
    public static final int r14_ID = 64;
    public static final int r15_ID = 65;
    public static final int r16_ID = 66;
    public static final int r17_ID = 67;
    public static final int r18_ID = 68;
    public static final int r19_ID = 69;
    public static final int r20_ID = 70;
    public static final int r21_ID = 71;
    public static final int r22_ID = 72;
    public static final int r23_ID = 73;
    public static final int r24_ID = 74;
    public static final int r25_ID = 75;

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


    public Topic() {
    }

    /**
     * Topic constructor with no information
     * @param id selected topic id
     * @param name selected topic name
     * @param difficulty selected topic difficulty name
     * @param type selected topic type
     * @param activitiesCount  number of activities
     */
    public Topic(int id, String name, String difficulty, String type, int activitiesCount) {
        this.id = id;
        this.name = name;
        this.difficulty = difficulty;
        this.type = type;
        this.activitiesCount = activitiesCount;
        this.activitiesCompleted = 0;
        this.information = "No information available";
    }

    /**
     * Topic constructor with no information or activities added
     * @param id selected topic id
     * @param name selected topic name
     * @param difficulty selected topic difficulty name
     * @param type selected topic type
     */
    public Topic(int id, String name, String difficulty, String type) {
        this.id = id;
        this.name = name;
        this.difficulty = difficulty;
        this.type = type;
        activitiesCount = 0;
        activitiesCompleted = 0;
        this.information = "No information available";
    }

    /**
     * Full Topic constructor
     * @param id selected topic id
     * @param name selected topic name
     * @param difficulty selected topic difficulty name
     * @param type selected topic type
     * @param activitiesCount  number of activities
     * @param information topic information
     */
    public Topic(int id, String name, String difficulty, String type, int activitiesCount, String information) {
        this.id = id;
        this.name = name;
        this.difficulty = difficulty;
        this.type = type;
        this.activitiesCount = activitiesCount;
        this.activitiesCompleted = 0;
        this.information = information;
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

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    /**
     * Returns Topic name instead of class name when toString() is called in adapter
     * @return
     */
    @Override
    public String toString() {
        return getName();
    }
}


