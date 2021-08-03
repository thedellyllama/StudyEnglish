package com.del.studyenglish1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyVocabAdapter extends ArrayAdapter<Vocabulary> {

    private ArrayList<Vocabulary> vocabList;
    private static final String LOG_TAG = MyVocabAdapter.class.getSimpleName();

    public MyVocabAdapter(Context context, ArrayList<Vocabulary> vocabList) {
        super(context, 0, vocabList);
        this.vocabList = vocabList;
    }

    public View getView(int position, View view, ViewGroup parent) {
        Vocabulary chosenVocab = getItem(position);

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.vocab_item, parent, false);
        }
        /*
        View listItemView = view;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.vocab_item, parent, false);*/


        TextView textViewTopic = view.findViewById(R.id.text_view_topic);
        TextView textViewVocab = view.findViewById(R.id.text_view_vocab);
        TextView textViewDef = view.findViewById(R.id.text_view_definition);

        //textViewTopic.setText(chosenVocab.String.valueof(getTopicId()));
        textViewVocab.setText(chosenVocab.getName());
        textViewDef.setText(chosenVocab.getDefinition());

        return view;
    }


}
