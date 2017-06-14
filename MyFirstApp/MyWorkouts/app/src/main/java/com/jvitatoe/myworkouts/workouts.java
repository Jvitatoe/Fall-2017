package com.jvitatoe.myworkouts;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.zip.Inflater;

public class workouts extends AppCompatActivity implements WorkoutFragment.onDetailFragListener {

private int mCurrentPos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workouts);


        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if(fragment == null){
            fragment = new WorkoutFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {


               default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_workouts, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onDetailFrag(int id) {
        mCurrentPos = id;
        WorkoutFragment2 detailFrag = new WorkoutFragment2();
        Bundle bundle = new Bundle();
        bundle.putInt("money",id);
        detailFrag.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, detailFrag);
    }
}





