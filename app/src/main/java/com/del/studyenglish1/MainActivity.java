package com.del.studyenglish1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {//implements Page5.Page5Listener {
    private Page5 page5;
    //private Page5_1 page5_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_container);

        //NavigationUI.setupWithNavController(bottomNavigationView, navController);


        page5 = new Page5();
        /** page5_1 = new Page5_1();**/

        getSupportFragmentManager().beginTransaction()
                .add(R.id.nav_host_fragment, page5)
                .commit();
    }

        /**
   // @Override
   // public void onLevelSelected(String level) {
        //page5_1 = new Page5_1();
        //page5.nextPage();
        /FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, page5_1)
                .commit();
        //page5_1.updateTextView(level);
        */



}