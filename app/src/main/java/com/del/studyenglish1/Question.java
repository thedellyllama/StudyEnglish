package com.del.studyenglish1;

import android.os.Parcel;
import android.os.Parcelable;


public class Question {
        /*
        implements Parcelable {

        public static final String DIFFICULTY_A1 = "A1";
        public static final String DIFFICULTY_A2 = "A2";
        public static final String DIFFICULTY_B1 = "B1";
        public static final String DIFFICULTY_B2 = "B2";
        public static final String DIFFICULTY_C1 = "C1";
*/
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

        public Question() { }

        public Question(String instruction, String option1, String option2, String option3,
                        String option4, int answerNr, int topicId, int activityNum) {
            this.instruction = instruction;
            this.option1 = option1;
            this.option2 = option2;
            this.option3 = option3;
            this.option4 = option4;
            this.answerNr = answerNr;
            this.topicId = topicId;
            this.activityNum = activityNum;
        }

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
    public Question(int imageRef, String instruction, String option1, String option2, String option3,
                    String option4, int answerNr, int topicId, int activityNum) {
        this.imageRef = imageRef;
        this.instruction = instruction;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answerNr = answerNr;
        this.topicId = topicId;
        this.activityNum = activityNum;
    }

    /**constructor for Reading Quiz Questions**/
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
/*
        protected Question(Parcel in) {
            //id = in.readInt();
            question = in.readString();
            option1 = in.readString();
            option2 = in.readString();
            option3 = in.readString();
            option4 = in.readString();
            answerNr = in.readInt();
            //difficulty = in.readString();
            topicId = in.readInt();
            activityNum = in.readInt();
        }

    @Override
        public void writeToParcel(Parcel dest, int flags) {
            //dest.writeInt(id);
            dest.writeString(question);
            dest.writeString(option1);
            dest.writeString(option2);
            dest.writeString(option3);
            dest.writeString(option4);
            dest.writeInt(answerNr);
            //dest.writeString(difficulty);
            dest.writeInt(topicId);
            dest.writeInt(activityNum);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<Question> CREATOR = new Creator<Question>() {
            @Override
            public Question createFromParcel(Parcel in) {
                return new Question(in);
            }

            @Override
            public Question[] newArray(int size) {
                return new Question[size];
            }
        };

        public int getId() {
            return id;
        }

    public void setId(int id) {
            this.id = id;
        }
*/
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


