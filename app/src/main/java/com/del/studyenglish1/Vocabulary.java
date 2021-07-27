package com.del.studyenglish1;

public class Vocabulary {
    private int topicId;
    private String name;
    private String definition;

    public Vocabulary(int topicId, String name, String definition) {
        this.topicId = topicId;
        this.name = name;
        this.definition = definition;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }
}