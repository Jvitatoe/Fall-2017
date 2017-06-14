package com.jvitatoe.workouts_main;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by JVitatoe on 9/20/15.
 */
public class WorkoutFragment extends ListFragment {
    OnWorkoutClickListener mListener;

    public interface OnWorkoutClickListener{
        public void onWorkoutSelected(int position);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layout = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
                android.R.layout.simple_list_item_activated_1 : android.R.layout.simple_list_item_1;

        final ArrayAdapter<String> myAdapter =
                new ArrayAdapter<String>(getActivity(),layout,
                        Workouts.getWORKOUTS());

        setListAdapter(myAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();

        if(getFragmentManager().findFragmentById(R.id.workout)!= null){
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try{
            mListener = (OnWorkoutClickListener)activity;
        } catch (ClassCastException e){
            throw new ClassCastException((activity.toString() + "get it together"));
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Toast.makeText(getActivity(), ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
        mListener.onWorkoutSelected(position);
        getListView().setItemChecked(position, true);
    }
}
