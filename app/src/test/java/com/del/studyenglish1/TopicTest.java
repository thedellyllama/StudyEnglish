package com.del.studyenglish1;

import junit.framework.TestCase;

public class TopicTest extends TestCase {
    Topic t1;
    Topic t2;
    Topic t3;

    public void setUp() throws Exception {
        super.setUp();
        t1 = new Topic(1,"topic1",Topic.DIFFICULTY_A1,Topic.TYPE_READING,1);
        t2 = new Topic(2, "topic2", Topic.DIFFICULTY_B1,Topic.TYPE_GRAMMAR);
        t3 = new Topic(3, "topic3", Topic.DIFFICULTY_C1, Topic.TYPE_VOCABULARY,3, "information");
    }

    public void testGetId() {
        assertEquals(1,t1.getId());
        assertEquals(2,t2.getId());
        assertEquals(3,t3.getId());
    }

    public void testSetId() {
        t1.setId(10);
        t2.setId(20);
        t3.setId(30);

        assertEquals(10,t1.getId());
        assertEquals(20,t2.getId());
        assertEquals(30,t3.getId());
    }

    public void testTestGetName() {
        assertEquals("topic1",t1.getName());
        assertEquals("topic2",t2.getName());
        assertEquals("topic3",t3.getName());
    }

    public void testTestSetName() {
        t1.setName("topic10");
        t2.setName("topic20");
        t3.setName("topic30");

        assertEquals("topic10",t1.getName());
        assertEquals("topic20",t2.getName());
        assertEquals("topic30",t3.getName());
    }

    public void testGetDifficulty() {
        assertEquals("A1",t1.getDifficulty());
        assertEquals("B1",t2.getDifficulty());
        assertEquals("C1",t3.getDifficulty());
    }

    public void testSetDifficulty() {
        t1.setDifficulty(Topic.DIFFICULTY_A2);
        t2.setDifficulty(Topic.DIFFICULTY_B2);
        t3.setDifficulty("impossible");

        assertEquals("A2",t1.getDifficulty());
        assertEquals("B2",t2.getDifficulty());
        assertEquals("impossible",t3.getDifficulty());
    }

    public void testGetType() {
        assertEquals("READING",t1.getType());
        assertEquals("GRAMMAR",t2.getType());
        assertEquals("VOCABULARY",t3.getType());
    }

    public void testSetType() {
        t1.setType(Topic.TYPE_GRAMMAR);
        t2.setType(Topic.TYPE_VOCABULARY);
        t3.setType(Topic.TYPE_READING);

        assertEquals("GRAMMAR",t1.getType());
        assertEquals("VOCABULARY",t2.getType());
        assertEquals("READING",t3.getType());
    }

    public void testGetActivitiesCount() {
        assertEquals(1, t1.getActivitiesCount());
        assertEquals(0, t2.getActivitiesCount());
        assertEquals(3, t3.getActivitiesCount());
    }

    public void testSetActivitiesCount() {
        t1.setActivitiesCount(Topic.NUM_READING);
        t2.setActivitiesCount(Topic.NUM_GRAMMAR);
        t3.setActivitiesCount(Topic.NUM_VOCAB);

        assertEquals(2, t1.getActivitiesCount());
        assertEquals(4, t2.getActivitiesCount());
        assertEquals(4, t3.getActivitiesCount());
    }

    public void testGetActivitiesCompleted() {
        assertEquals(0, t1.getActivitiesCompleted());
        assertEquals(0, t2.getActivitiesCompleted());
        assertEquals(0, t3.getActivitiesCompleted());
    }

    public void testSetActivitiesCompleted() {
        t1.setActivitiesCompleted(1);
        t2.setActivitiesCompleted(2);
        t3.setActivitiesCompleted(3);

        assertEquals(1, t1.getActivitiesCompleted());
        assertEquals(2, t2.getActivitiesCompleted());
        assertEquals(3, t3.getActivitiesCompleted());
    }

    public void testGetInformation() {
        assertEquals("No information available",t1.getInformation());
        assertEquals("No information available",t2.getInformation());
        assertEquals("information",t3.getInformation());
    }

    public void testSetInformation() {
        t1.setInformation("information1");
        t2.setInformation("information2");
        t3.setInformation("information3");

        assertEquals("information1",t1.getInformation());
        assertEquals("information2",t2.getInformation());
        assertEquals("information3",t3.getInformation());

    }

}