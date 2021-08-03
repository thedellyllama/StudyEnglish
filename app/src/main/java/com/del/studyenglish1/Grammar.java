package com.del.studyenglish1;

public class Grammar {

    private int id;
    private int topicId;
    private String explanation;

    public Grammar() {
            }

    public Grammar(int topicId, String explanation) {
        this.topicId = topicId;
        this.explanation = explanation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}
