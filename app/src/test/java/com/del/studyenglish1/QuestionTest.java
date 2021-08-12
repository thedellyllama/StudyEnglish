package com.del.studyenglish1;

import junit.framework.TestCase;

public class QuestionTest extends TestCase {

    private Question q1;
    private Question q2;
    private Question q3;
    private Question q4;

    public void setUp() throws Exception {
        super.setUp();
        //constructor 1
        q1 = new Question("instruction1", "option1a", "option1b", "option1c", "option1d", 1, 1, 1);
        //constructor 2
        q2 = new Question("instruction2", "question2","option2a", "option2b","option2c","option2d", 2,2,2);
       //constructor 3
        q3 = new Question(R.drawable.crab, "instruction3", "option3a", "option3b","option3c","option3d", 3,3,3);
        //constructor 4
        q4 = new Question(R.drawable.tree, "instruction4", "question4", "option4a", "option4b", "option4c", "option4d", 4, 4,4);
    }
/*
    public void testGetId() {
        assertEquals(1,q1.getId());
        assertEquals(2,q2.getId());
        assertEquals(3,q3.getId());
        assertEquals(4,q4.getId());
    }

    public void testSetId() {
        q1.setId(10);
        q2.setId(20);
        q3.setId(30);
        q4.setId(40);

        assertEquals(10,q1.getId());
        assertEquals(20,q2.getId());
        assertEquals(30,q3.getId());
        assertEquals(40,q4.getId());
    }*/

    public void testGetImageRef() {
        assertEquals(0,q1.getImageRef());
        assertEquals(0,q2.getImageRef());
        assertEquals(R.drawable.crab, q3.getImageRef());
        assertEquals(R.drawable.tree, q4.getImageRef());
    }

    public void testSetImageRef() {
        q1.setImageRef(R.drawable.boat);
        q2.setImageRef(R.drawable.boots);
        q3.setImageRef(R.drawable.bowtie);
        q4.setImageRef(R.drawable.bracelet);

        assertEquals(R.drawable.boat, q1.getImageRef());
        assertEquals(R.drawable.boots, q2.getImageRef());
        assertEquals(R.drawable.bowtie, q3.getImageRef());
        assertEquals(R.drawable.bracelet, q4.getImageRef());
    }

    public void testGetInstruction() {
        assertEquals("instruction1",q1.getInstruction());
        assertEquals("instruction2",q2.getInstruction());
        assertEquals("instruction3",q3.getInstruction());
        assertEquals("instruction4",q4.getInstruction());
    }

    public void testSetInstruction() {
        q1.setInstruction("instruction10");
        q2.setInstruction("instruction20");
        q3.setInstruction("instruction30");
        q4.setInstruction("instruction40");

        assertEquals("instruction10",q1.getInstruction());
        assertEquals("instruction20",q2.getInstruction());
        assertEquals("instruction30",q3.getInstruction());
        assertEquals("instruction40",q4.getInstruction());
    }

    public void testGetQuestion() {
        assertNull(q1.getQuestion());
        assertEquals("question2",q2.getQuestion());
        assertNull(q3.getQuestion());
        assertEquals("question4",q4.getQuestion());
    }

    public void testSetQuestion() {
        q1.setQuestion("question10");
        q2.setQuestion("question20");
        q3.setQuestion("question30");
        q4.setQuestion("question40");

        assertEquals("question10",q1.getQuestion());
        assertEquals("question20",q2.getQuestion());
        assertEquals("question30",q3.getQuestion());
        assertEquals("question40",q4.getQuestion());
    }

    public void testGetOption1() {
        assertEquals("option1a",q1.getOption1());
        assertEquals("option2a",q2.getOption1());
        assertEquals("option3a",q3.getOption1());
        assertEquals("option4a",q4.getOption1());
    }

    public void testSetOption1() {
        q1.setOption1("option10a");
        q2.setOption1("option20a");
        q3.setOption1("option30a");
        q4.setOption1("option40a");
    }

    public void testGetOption2() {
        assertEquals("option1b",q1.getOption2());
        assertEquals("option2b",q2.getOption2());
        assertEquals("option3b",q3.getOption2());
        assertEquals("option4b",q4.getOption2());
    }

    public void testSetOption2() {
        q1.setOption2("option10b");
        q2.setOption2("option20b");
        q3.setOption2("option30b");
        q4.setOption2("option40b");

        assertEquals("option10b",q1.getOption2());
        assertEquals("option20b",q2.getOption2());
        assertEquals("option30b",q3.getOption2());
        assertEquals("option40b",q4.getOption2());
    }

    public void testGetOption3() {
        assertEquals("option1c",q1.getOption3());
        assertEquals("option2c",q2.getOption3());
        assertEquals("option3c",q3.getOption3());
        assertEquals("option4c",q4.getOption3());
    }

    public void testSetOption3() {
        q1.setOption3("option10c");
        q2.setOption3("option20c");
        q3.setOption3("option30c");
        q4.setOption3("option40c");

        assertEquals("option10c",q1.getOption3());
        assertEquals("option20c",q2.getOption3());
        assertEquals("option30c",q3.getOption3());
        assertEquals("option40c",q4.getOption3());
    }

    public void testGetOption4() {
        assertEquals("option1d",q1.getOption4());
        assertEquals("option2d",q2.getOption4());
        assertEquals("option3d",q3.getOption4());
        assertEquals("option4d",q4.getOption4());
    }

    public void testSetOption4() {
        q1.setOption4("option10d");
        q2.setOption4("option20d");
        q3.setOption4("option30d");
        q4.setOption4("option40d");

        assertEquals("option10d",q1.getOption4());
        assertEquals("option20d",q2.getOption4());
        assertEquals("option30d",q3.getOption4());
        assertEquals("option40d",q4.getOption4());
    }

    public void testGetAnswerNr() {
        assertEquals(1,q1.getActivityNum());
        assertEquals(2,q2.getActivityNum());
        assertEquals(3,q3.getActivityNum());
        assertEquals(4,q4.getActivityNum());
    }

    public void testSetAnswerNr() {
        q1.setAnswerNr(10);
        q2.setAnswerNr(20);
        q3.setAnswerNr(30);
        q4.setAnswerNr(40);

        assertEquals(10,q1.getAnswerNr());
        assertEquals(20,q2.getAnswerNr());
        assertEquals(30,q3.getAnswerNr());
        assertEquals(40,q4.getAnswerNr());
    }

    public void testGetTopicId() {
        assertEquals(1,q1.getTopicId());
        assertEquals(2,q2.getTopicId());
        assertEquals(3,q3.getTopicId());
        assertEquals(4,q4.getTopicId());
    }

    public void testSetTopicId() {
        q1.setTopicId(10);
        q2.setTopicId(20);
        q3.setTopicId(30);
        q4.setTopicId(40);

        assertEquals(10,q1.getTopicId());
        assertEquals(20,q2.getTopicId());
        assertEquals(30,q3.getTopicId());
        assertEquals(40,q4.getTopicId());
    }

    public void testGetActivityNum() {
        assertEquals(1,q1.getActivityNum());
        assertEquals(2,q2.getActivityNum());
        assertEquals(3,q3.getActivityNum());
        assertEquals(4,q4.getActivityNum());
    }

    public void testSetActivityNum() {
        q1.setActivityNum(10);
        q2.setActivityNum(20);
        q3.setActivityNum(30);
        q4.setActivityNum(40);

        assertEquals(10,q1.getActivityNum());
        assertEquals(20,q2.getActivityNum());
        assertEquals(30,q3.getActivityNum());
        assertEquals(40,q4.getActivityNum());
    }


}