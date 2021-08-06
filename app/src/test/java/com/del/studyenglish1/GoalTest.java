package com.del.studyenglish1;

import junit.framework.TestCase;

public class GoalTest extends TestCase {
private Goal g;
    public void setUp() throws Exception {
        super.setUp();
         g = new Goal(3, "DAILY");
    }

    public void testGetGoalActivities() {
        assertEquals(3,g.getGoalActivities());
    }

    public void testSetGoalActivities() {
        g.setGoalActivities(5);
        assertEquals(5,g.getGoalActivities());
    }

    public void testGetGoalTimeframe() {
        assertEquals("DAILY", g.getGoalTimeframe());
    }

    public void testSetGoalTimeframe() {
        g.setGoalTimeframe("WEEKLY");
        assertEquals("WEEKLY", g.getGoalTimeframe());
    }
}