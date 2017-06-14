package com.jvitatoe.myworkouts;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by JVitatoe on 9/15/15.
 */
public class WorkoutFragment2 extends Fragment {
    public int mPos = -1;
    public View v;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
         v = inflater.inflate(R.layout.fragment_workout2, container, false);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle args = getArguments();
        mPos = args.getInt("money",-1);
        upDetail(mPos);
       // TextView text =(TextView)getActivity().findViewById(R.id.ex_one);
       // text.setText(mPos);
    }

    public void upDetail(int position){
        TextView workouts = (TextView)getActivity().findViewById(R.id.ex_one);
        workouts.setText(Workout.DETAILS[position]);
        mPos = position;
        


    }
}
