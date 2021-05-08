package com.example.alexstore;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    BottomNavigationView botNav;
    Fragment select = null;
    BDComunicare bdComunicare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        loadFragment(new Home());
        botNav = findViewById(R.id.bottom_nav);
        botNav.setSelectedItemId(R.id.home);
        botNav.setOnNavigationItemSelectedListener(navListener);

        bdComunicare = new BDComunicare();


    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                    int id = botNav.getSelectedItemId();

                    if (id != item.getItemId()) {
                        switch (item.getItemId()) {
                            case R.id.home:
                                select = new Home();
                                break;
                            case R.id.cont:
                                if (bdComunicare.getUserUid() == false) {
                                    select = new Login();
                                    break;
                                } else {
                                    select = new Dashboard();
                                    break;
                                }
                            case R.id.search:
                                select = new Search();
                                break;
                        }
                    }
                    return loadFragment(select);

                }
            };

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit();

            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        System.exit(0);
    }
}
