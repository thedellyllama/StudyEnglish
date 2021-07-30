package com.del.studyenglish1;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

//import com.del.studyenglish1.Page5_1.SelectTypeListener;

public class Page5 extends Fragment {

    private Page5_1 page5_1;
    private Page5_CheckLevel page5_checkLevel;
    private String selected_level;
    private String level_name;
    private Button button_a1;
    private Button button_a2;
    private Button button_b1;
    private Button button_b2;
    private Button button_c1;
    private TextView check_level;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_page5, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        button_a1 = v.findViewById(R.id.button_a1);
        button_a2 = v.findViewById(R.id.button_a2);
        button_b1 = v.findViewById(R.id.button_b1);
        button_b2 = v.findViewById(R.id.button_b2);
        button_c1 = v.findViewById(R.id.button_c1);
        check_level = v.findViewById(R.id.text_check_level);

        button_a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //level name is for database use
                level_name = "A1";
                selected_level =  button_a1.getText().toString();
                nextPage(selected_level, level_name);
            }
        });

        button_a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                level_name = "A2";
                selected_level =  button_a2.getText().toString();
                nextPage(selected_level, level_name);
            }
        });

        button_b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                level_name = "B1";
                selected_level =  button_b1.getText().toString();
                nextPage(selected_level, level_name);
            }
        });

        button_b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                level_name = "B2";
                selected_level =  button_b2.getText().toString();
                nextPage(selected_level, level_name);
            }
        });

        button_c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                level_name = "C1";
                selected_level =  button_c1.getText().toString();
                nextPage(selected_level, level_name);
            }
        });

        check_level.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page5_checkLevel = new Page5_CheckLevel();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, page5_checkLevel);
                fragmentTransaction.commit();
            }
        });
    }

    public void nextPage(String selected_level, String level_name) {
        page5_1 = page5_1.newInstance(selected_level, level_name);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, page5_1);
        fragmentTransaction.commit();
    }
}
