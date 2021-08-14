package com.del.studyenglish1;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private FragmentManager fm;
    private Fragment fragment;

    private long backPressedTime;
    private Toast toast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment, new HomePage())
                    .commit();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new HomePage();
                            break;
                        case R.id.nav_study:
                            selectedFragment = new SelectLevelPage();
                            break;
                        case R.id.nav_goals:
                            selectedFragment = new GoalsPage();
                            break;
                        case R.id.nav_progress:
                            selectedFragment = new ProgressPage();
                            break;
                        case R.id.nav_dictionary:
                            selectedFragment = new DictionaryPage();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.nav_host_fragment, selectedFragment)
                            .commit();

                    return true;
                }
            };

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            toast.cancel();
            super.onBackPressed();
            return;
        } else {
           toast = Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT);
           toast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }
}
