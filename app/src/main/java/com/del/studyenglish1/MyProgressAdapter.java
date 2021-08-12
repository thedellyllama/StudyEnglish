package com.del.studyenglish1;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyProgressAdapter extends ArrayAdapter<Topic> {
    private static final String LOG_TAG = MyProgressAdapter.class.getSimpleName();

    private ArrayList<Topic> progressList;

    private TextView topicLevelType;
    private TextView topicName;
    private ImageView imageProgress;
    private Drawable progress1_4;
    private Drawable progress2_4;
    private Drawable progress3_4;
    private Drawable progress1_2;
    private Drawable progressDone;

    /**
     * MyProgressAdapter constructor
     * @param context current context
     * @param progressList list of all topics with >0 activities completed to show in adapter
     */
    public MyProgressAdapter(Context context, ArrayList<Topic> progressList) {
        super(context, 0, progressList);
        this.progressList = progressList;
    }

    public View getView(int position, View view, ViewGroup parent) {
        Topic topic = getItem(position);

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.progress_item, parent, false);
        }

        topicLevelType = view.findViewById(R.id.text_view_level_type);
        topicName = view.findViewById(R.id.text_view_topic);
        imageProgress = view.findViewById(R.id.image_view_progress);
        progress1_2 = view.getResources().getDrawable(R.drawable.progress_1_2);
        progress1_4 = view.getResources().getDrawable(R.drawable.progress_1_4);
        progress2_4 = view.getResources().getDrawable(R.drawable.progress_2_4);
        progress3_4 = view.getResources().getDrawable(R.drawable.progress_3_4);
        progressDone = view.getResources().getDrawable(R.drawable.progress_complete);


        String levelType = topic.getDifficulty() + ": " + topic.getType();
        int progress = topic.getActivitiesCompleted() / topic.getActivitiesCount();
        int activityCount = topic.getActivitiesCount();
        int activitiesCompleted = topic.getActivitiesCompleted();

        if (activityCount == 2) {
            if (activitiesCompleted == 1) {
                imageProgress.setImageDrawable(progress1_2);
            } else if (activitiesCompleted == 2) {
                    imageProgress.setImageDrawable(progressDone);
            }
        } else if (activityCount == 4) {
                if (activitiesCompleted == 1) {
                    imageProgress.setImageDrawable(progress1_4);
                } else if (activitiesCompleted == 2) {
                    imageProgress.setImageDrawable(progress2_4);
                } else if (activitiesCompleted == 3) {
                    imageProgress.setImageDrawable(progress3_4);
                } else if (activitiesCompleted == 4) {
                    imageProgress.setImageDrawable(progressDone);
            }
        }
        topicLevelType.setText(levelType);
        topicName.setText(topic.getName());

        return view;
    }
}

