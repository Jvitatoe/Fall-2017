package com.jvitatoe.workouts_main;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.app.ActionBar;
public class Workout extends AppCompatActivity
        implements WorkoutFragment.OnWorkoutClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        Intent intent = new Intent(this,WorkoutLogin.class);

        // calling an activity using <intent-filter> action name
        //  Intent inent = new Intent("com.hmkcode.android.ANOTHER_ACTIVITY");

        startActivity(intent);

    }

   @Override
    protected void onStart() {
        super.onStart();

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.back_button:
                WorkoutFragment backFragment = new WorkoutFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, backFragment)
                        .commit();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_workout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void onWorkoutSelected(int position){
        WorkoutDetails workoutFrag = (WorkoutDetails)
                getSupportFragmentManager().findFragmentById(R.id.workout);

        if(workoutFrag !=null){
            workoutFrag.updateWorkoutView(position);
        }else{
            WorkoutDetails newFragment = new WorkoutDetails();
            Bundle args = new Bundle();
            args.putInt(WorkoutDetails.ARG_POSITION, position);
            newFragment.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }

    }

}
