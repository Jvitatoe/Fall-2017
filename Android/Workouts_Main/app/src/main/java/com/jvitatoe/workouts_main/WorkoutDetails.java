package com.jvitatoe.workouts_main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by JVitatoe on 9/20/15.
 */
public class WorkoutDetails extends Fragment {
    Random rand;
    final static String ARG_POSITION = "position";
    public int mPos = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(savedInstanceState != null){
            mPos = savedInstanceState.getInt(ARG_POSITION);
        }
        return inflater.inflate(R.layout.workout_view, container, false);
    }

    @Override
    public void onStart(){
        super.onStart();
        Bundle args= getArguments();

        if(args != null){
            updateWorkoutView(args.getInt(ARG_POSITION));

        }else if(mPos != -1)
            updateWorkoutView(mPos);
        {
        }
    }

    public void updateWorkoutView(int position){
        rand = new Random();
        TextView workout = (TextView) getActivity().findViewById(R.id.workout);
        workout.setText(Workouts.DETAILS[position]);
        workout.setBackgroundColor(rand.nextInt());
        workout.setMovementMethod((new ScrollingMovementMethod()));
        mPos = position;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ARG_POSITION,mPos);
    }
}

