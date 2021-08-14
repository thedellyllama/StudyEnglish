package com.del.studyenglish1;

public class Question {
    private String instruction;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private int answerNr;
    private int topicId;
    private int activityNum;

    private int imageRef;

    /**
     * Empty Question constructor
     */
        public Question() { }

    /**
     * Question constructor with no image reference and no question
     * @param instruction instruction/question
     * @param option1 possible answer 1
     * @param option2 possible answer 2
     * @param option3 possible answer 3
     * @param option4 possible answer 4
     * @param answerNr correct answer number
     * @param topicId the topicId the question belongs to
     * @param activityNum the activity number the question belongs to
     */
        public Question(String instruction, String option1, String option2, String option3,
                        String option4, int answerNr, int topicId, int activityNum) {
            this.instruction = instruction;
            this.question = "";
            this.option1 = option1;
            this.option2 = option2;
            this.option3 = option3;
            this.option4 = option4;
            this.answerNr = answerNr;
            this.topicId = topicId;
            this.activityNum = activityNum;
        }

    /**
     * Question constructor with no image reference
     * @param instruction instruction
     * @param question question
     * @param option1 possible answer 1
     * @param option2 possible answer 2
     * @param option3 possible answer 3
     * @param option4 possible answer 4
     * @param answerNr correct answer number
     * @param topicId the topicId the question belongs to
     * @param activityNum the activity number the question belongs to
     */
    public Question(String instruction, String question, String option1, String option2, String option3,
                    String option4, int answerNr, int topicId, int activityNum) {
        this.instruction = instruction;
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answerNr = answerNr;
        this.topicId = topicId;
        this.activityNum = activityNum;
    }

    /**Question constructor with no question
     *
     * @param imageRef image reference
     * @param instruction instruction/question
     * @param option1 possible answer 1
     * @param option2 possible answer 2
     * @param option3 possible answer 3
     * @param option4 possible answer 4
     * @param answerNr correct answer number
     * @param topicId the topicId the question belongs to
     * @param activityNum the activity number the question belongs to
     */
    public Question(int imageRef, String instruction, String option1, String option2, String option3,
                    String option4, int answerNr, int topicId, int activityNum) {
        this.imageRef = imageRef;
        this.instruction = instruction;
        this.question = "";
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answerNr = answerNr;
        this.topicId = topicId;
        this.activityNum = activityNum;
    }

    /**Full question constructor for Reading Quiz Questions
     *
     * @param imageRef image reference
     * @param instruction instruction/question
     * @param question question
     * @param option1 possible answer 1
     * @param option2 possible answer 2
     * @param option3 possible answer 3
     * @param option4 possible answer 4
     * @param answerNr correct answer number
     * @param topicId the topicId the question belongs to
     * @param activityNum the activity number the question belongs to
     */
    public Question(int imageRef, String instruction, String question, String option1, String option2, String option3,
                    String option4, int answerNr, int topicId, int activityNum) {
        this.imageRef = imageRef;
        this.instruction = instruction;
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answerNr = answerNr;
        this.topicId = topicId;
        this.activityNum = activityNum;
    }
        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getOption1() {
            return option1;
        }

        public void setOption1(String option1) {
            this.option1 = option1;
        }

        public String getOption2() {
            return option2;
        }

        public void setOption2(String option2) {
            this.option2 = option2;
        }

        public String getOption3() {
            return option3;
        }

        public void setOption3(String option3) {
            this.option3 = option3;
        }

        public String getOption4() {
            return option4;
        }

        public void setOption4(String option4) {
            this.option4 = option4;
        }

        public int getAnswerNr() {
            return answerNr;
        }

        public void setAnswerNr(int answerNr) {
        this.answerNr = answerNr;
    }

        public int getActivityNum() {  return activityNum;    }

        public void setActivityNum(int activityNum) { this.activityNum = activityNum;    }

        public int getTopicId() {
            return topicId;
        }

        public void setTopicId(int topicId) {
            this.topicId = topicId;
        }

    public int getImageRef() {
        return imageRef;
    }

    public void setImageRef(int imageRef) {
        this.imageRef = imageRef;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }
}


