package org.me.gcu.labstuff.gcu_trafficscotland_md_arungoyal_3rdyear;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Switch;
import android.app.SearchManager;
import android.view.MenuInflater;
import android.content.Context;
import android.content.Intent;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationMenu);
        bottomNavigationView.setOnItemSelectedListener(this);

        if(savedInstanceState == null)
        {
            loadFragment(new ScotlandRoadworks());
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        Fragment selectedFragment = null;

        switch (item.getItemId()){
            case R.id.roadworks:
                selectedFragment = new ScotlandRoadworks();
                Log.e("Roadworks", "Display Roadworks");
                break;
            case R.id.plannedRoadworks:
                selectedFragment = new ScotlandPlannedRoadworks();
                Log.e("Planned Roadworks", "Display Planned Roadworks");
                break;
            case R.id.currentIncidents:
                selectedFragment = new ScotlandCurrentIncidents();
                Log.e("Current Incidents", "Display Current Incidents");
                break;
    }
    return loadFragment(selectedFragment);
    }

    private boolean loadFragment(Fragment selectedFragment){
        if(selectedFragment != null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.frmFragment, selectedFragment).commit();
            return true;
        }
        return false;
    }

}