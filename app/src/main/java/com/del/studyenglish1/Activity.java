package com.del.studyenglish1;

public class Activity {
    public static final boolean FALSE = false;
    public static final boolean TRUE = true;


    private int topicId;
    private int activityNum;
    private boolean completed;

    public Activity(){}

    public Activity(int topicId, int activityNum, boolean completed) {
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

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
