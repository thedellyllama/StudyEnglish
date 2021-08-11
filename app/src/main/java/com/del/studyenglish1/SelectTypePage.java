package com.del.studyenglish1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class SelectTypePage extends Fragment {
    private static final String ARG_LEVEL = "argLevel";
    private static final String ARG_LEVEL_NAME = "argLevelName";

    private String level;
    private String level_name;
    private String selected_type;

    private SelectLevelPage selectLevelPage;
    private SelectTopicPage selectTopicPage;

    private TextView textViewLevel;
    private ImageView backButton;
    private Button button_grammar;
    private Button button_vocabulary;
    Button button_reading;

    /**
     * Create and open new instance of Select Type Page with selected arguments
     * @param level  selected level description
     * @param level_name selected level name
     * @return SelectTypePage with given arguments
     */
    public static SelectTypePage newInstance(String level, String level_name) {
        SelectTypePage fragment = new SelectTypePage();
        Bundle args = new Bundle();
        args.putString(ARG_LEVEL, level);
        args.putString(ARG_LEVEL_NAME, level_name);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_select_type_page, container, false);
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

        textViewLevel = (TextView) view.findViewById(R.id.text_view_level);
        backButton = view.findViewById(R.id.back_button);
        button_grammar = view.findViewById(R.id.button_grammar);
        button_vocabulary = view.findViewById(R.id.button_vocab);
        button_reading = view.findViewById(R.id.button_reading);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSelectLevelPage();
            }
        });

        button_grammar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected_type = button_grammar.getText().toString();
                openSelectTopicPage(selected_type, level, level_name);
            }
        });

        button_vocabulary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected_type = button_vocabulary.getText().toString();
                openSelectTopicPage(selected_type, level, level_name);
            }
        });

        button_reading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected_type = button_reading.getText().toString();
                openSelectTopicPage(selected_type, level, level_name);
            }
        });
    }

    /**
     * Open new SelectLevelPage
     */
    public void openSelectLevelPage() {
        selectLevelPage = new SelectLevelPage();
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, selectLevelPage);
        fragmentTransaction.commit();
    }

    /**
     * Open new instance of SelectTopicPage with given arguments
     * @param selected_type selected topic type
     * @param level selected level description
     * @param level_name selected level name
     */
    public void openSelectTopicPage(String selected_type, String level, String level_name) {
        selectTopicPage = selectTopicPage.newInstance(selected_type, level, level_name);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, selectTopicPage);
        fragmentTransaction.commit();
    }
}
