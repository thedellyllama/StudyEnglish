package com.del.studyenglish1;

import junit.framework.TestCase;

public class VocabularyTest extends TestCase {

    Vocabulary v;
    public void setUp() throws Exception {
        super.setUp();
        v = new Vocabulary(1,"vocabulary", "definition");
    }
/*
    public void testGetId() {
        assertEquals(1,get);
    }

    public void testSetId() {
    }
*/
    public void testGetTopicId() {
        assertEquals(1,v.getTopicId());
    }

    public void testSetTopicId() {
        v.setTopicId(10);
        assertEquals(10, v.getTopicId());
    }

    public void testTestGetName() {
        assertEquals("vocabulary", v.getName());
    }

    public void testTestSetName() {
        v.setName("vocabulary10");
        assertEquals("vocabulary10", v.getName());
    }

    public void testGetDefinition() {
        assertEquals("definition", v.getDefinition());
    }

    public void testSetDefinition() {
        v.setDefinition("definition10");
        assertEquals("definition10", v.getDefinition());
    }
}