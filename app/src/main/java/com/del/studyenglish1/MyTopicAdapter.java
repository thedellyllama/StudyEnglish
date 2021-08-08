package com.del.studyenglish1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyTopicAdapter extends ArrayAdapter<Topic> {
    private static final String LOG_TAG = MyTopicAdapter.class.getSimpleName();

    private ArrayList<Topic> topicsList;

    public MyTopicAdapter(Context context, ArrayList<Topic> topicsList) {
        super(context, 0, topicsList);
        this.topicsList = topicsList;
    }

    public View getView(int position, View view, ViewGroup parent) {

        View listItemView = view;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.topic_item, parent, false);
        }
        Topic chosenTopic = getItem(position);
        TextView textView = listItemView.findViewById(R.id.text_view_topic_item);
        textView.setText(chosenTopic.getName());
        return listItemView;
    }

}

