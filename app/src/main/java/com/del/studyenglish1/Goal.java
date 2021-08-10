package com.del.studyenglish1;

public class Goal {

    public static final String DAILY = "DAILY";
    public static final String WEEKLY = "WEEKLY";

    private int goalActivities;
    private String goalTimeFrame;

    /**
     * Empty Goal constructor
     */
    public Goal(){}

    /**
     * Goal constructor
     * @param goalActivities number of activities
     * @param goalTimeFrame time frame for goal "DAILY" or "WEEKLY"
     */
    public Goal(int goalActivities, String goalTimeFrame) {
        this.goalActivities = goalActivities;
        this.goalTimeFrame = goalTimeFrame;
    }

    public int getGoalActivities() {
        return goalActivities;
    }

    public void setGoalActivities(int goalActivities) {
        this.goalActivities = goalActivities;
    }

    public String getGoalTimeFrame() {
        return goalTimeFrame;
    }

    public void setGoalTimeFrame(String goalTimeFrame) {
        this.goalTimeFrame = goalTimeFrame;
    }
}
