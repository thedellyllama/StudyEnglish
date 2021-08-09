package com.del.studyenglish1;

import java.sql.Date;

public class Activity {
    public static final String FALSE = "FALSE";
    public static final String TRUE = "TRUE";
    public static final int ACT_NUM_1 = 1;
    public static final int ACT_NUM_2 = 2;
    public static final int ACT_NUM_3 = 3;
    public static final int ACT_NUM_4 = 4;


    //private int id;
    private int topicId;
    private int activityNum;
    private String completed;
    private Date timeStamp;

    public Activity(){}

    public Activity(int topicId, int activityNum, String completed) {
        this.topicId = topicId;
        this.activityNum = activityNum;
        this.completed = completed;
    }
    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public int getActivityNum() {
        return activityNum;
    }

    public void setActivityNum(int activityNum) {
        this.activityNum = activityNum;
    }

    public String getCompleted() {
        return completed;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }

    public Date getTimeStamp() { return timeStamp; }

    public void setTimeStamp(Date timeStamp) { this.timeStamp = timeStamp; }
}
