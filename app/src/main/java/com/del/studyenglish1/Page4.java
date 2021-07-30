package com.del.studyenglish1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class Page4 extends Fragment {

    private CardView cardProfile;
    private CardView cardGoals;
    private CardView cardDictionary;
    private CardView cardStudy;

    private Fragment page5;
    private Fragment page6;
    private Fragment page7;
    private Fragment page8;


    public Page4(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
          return inflater.inflate(R.layout.fragment_page4, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //NavController navController = Navigation.findNavController(view);

        cardProfile = view.findViewById(R.id.card_view_profile);
        cardGoals = view.findViewById(R.id.card_view_goals);
        cardDictionary = view.findViewById(R.id.card_view_dictionary);
        cardStudy = view.findViewById(R.id.card_view_study);

        cardProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page7 = new Page7();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, page7);
                fragmentTransaction.commit();
            }
        });

        cardGoals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page6 = new Page6();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, page6);
                fragmentTransaction.commit();
            }
        });

        cardDictionary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page8 = new Page8();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, page8);
                fragmentTransaction.commit();
            }
        });

        cardStudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page5 = new Page5();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, page5);
                fragmentTransaction.commit();
            }
        });


    }

}
