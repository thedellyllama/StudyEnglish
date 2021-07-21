package com.del.studyenglish1;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

public class Page5_1 extends Fragment {

    private Page5_2 page5_2;
    private String selected_type;

    private static final String ARG_LEVEL = "argLevel";
    private static final String ARG_LEVEL_NAME = "argLevelName";
    private String level;
    private String level_name;
    private Page5 page5;

    private TextView textView;
    private TextView changeLevel;
    private Button button_grammar;
    private Button button_vocabulary;
    Button button_reading;

    public static Page5_1 newInstance(String level, String level_name) {
        Page5_1 fragment = new Page5_1();
        Bundle args = new Bundle();
        args.putString(ARG_LEVEL, level);
        args.putString(ARG_LEVEL_NAME, level_name);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_page5_1, container, false);
        TextView textViewLevel = v.findViewById(R.id.text_view_level);

        if (getArguments() != null) {
            level = getArguments().getString(ARG_LEVEL);
            level_name  = getArguments().getString(ARG_LEVEL_NAME);
        }
        textViewLevel.setText(level);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textView = (TextView) view.findViewById(R.id.text_view_level);
        changeLevel = (TextView) view.findViewById(R.id.text_change_level);
        button_grammar = view.findViewById(R.id.button_grammar);
        button_vocabulary = view.findViewById(R.id.button_vocab);
        button_reading = view.findViewById(R.id.button_reading);

        changeLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page5 = new Page5();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, page5);
                fragmentTransaction.commit();
            }
        });

        button_grammar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected_type = button_grammar.getText().toString();
                nextPageType(selected_type, level, level_name);
            }
        });

        button_vocabulary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected_type = button_vocabulary.getText().toString();
                nextPageType(selected_type, level, level_name);
            }
        });

        button_reading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected_type = button_reading.getText().toString();
                nextPageType(selected_type, level, level_name);
            }
        });
    }
    private void nextPageType(String selected_type, String level, String level_name) {
        page5_2 = page5_2.newInstance(selected_type, level, level_name);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, page5_2);
        fragmentTransaction.commit();
    }
/**
    protected void updateTextView(String new_level) {
        textView.setText(new_level);
    }*/
}
