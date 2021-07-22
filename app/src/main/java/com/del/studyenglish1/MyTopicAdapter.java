package com.del.studyenglish1;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class MyTopicAdapter extends ArrayAdapter<Topic> {

    private ArrayList<Topic> topicsList;
    private static final String LOG_TAG = MyTopicAdapter.class.getSimpleName();

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
        Button topicButton = (Button) listItemView.findViewById(R.id.button_topic_item);
        topicButton.setText(chosenTopic.getName());
        return listItemView;
    }


}
