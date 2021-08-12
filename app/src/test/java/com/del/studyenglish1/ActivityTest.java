package com.del.studyenglish1;

import junit.framework.TestCase;


public class ActivityTest extends TestCase {
 private Activity a;

    public void setUp() throws Exception {
         a = new Activity(1,2,"TRUE");
        super.setUp();
    }

    public void testGetTopicId() {
        assertEquals(1, a.getTopicId());
    }

    public void testSetTopicId() {
        a.setTopicId(3);
        assertEquals(3, a.getTopicId());
    }

    public void testGetActivityNum() {
        assertEquals(2, a.getActivityNum());
    }

    public void testSetActivityNum() {
        assertNotSame(1, a.getActivityNum());
        a.setActivityNum(4);
        assertEquals(4, a.getActivityNum());
    }

    public void testGetCompleted() {
        assertEquals("TRUE", a.getCompleted());
    }

    public void testSetCompleted() {
        a.setCompleted("FALSE");
        assertEquals("FALSE", a.getCompleted());
    }

    public void testGetTimeStamp() {
        assertNull(a.getTimeStamp());
    }

    public void testSetTimeStamp() {
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date date = new java.sql.Date(utilDate.getTime());
        a.setTimeStamp(date);
        assertNotNull(a.getTimeStamp());
    }
}