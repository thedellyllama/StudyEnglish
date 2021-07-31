package com.del.studyenglish1;

public class Goal {

    public static final String DAILY = "DAILY";
    public static final String WEEKLY = "WEEKLY";

    private int goalActivities;
    private String goalTimeframe;

    public Goal(){}
    public Goal(int goalActivities, String goalTimeframe) {
        this.goalActivities = goalActivities;
        this.goalTimeframe = goalTimeframe;
    }

    public int getGoalActivities() {
        return goalActivities;
    }

    public void setGoalActivities(int goalActivities) {
        this.goalActivities = goalActivities;
    }

    public String getGoalTimeframe() {
        return goalTimeframe;
    }

    public void setGoalTimeframe(String goalTimeframe) {
        this.goalTimeframe = goalTimeframe;
    }
}
