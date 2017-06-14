package com.jvitatoe.myworkouts;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by JVitatoe on 9/14/15.
 */
public class WorkoutFragment extends ListFragment {

    public View v;
    onDetailFragListener mListener;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        v = inflater.inflate(R.layout.fragment_workout, container, false);

        return v;
    }
    @Override
    public void onStart() {
        super.onStart();

        // When in two-pane layout, set the listview to highlight the selected list item
        // (We do this during onStart because at the point the listview is available.)
        if (getFragmentManager().findFragmentById(R.id.ex_one) != null) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

            final ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, Workout.getDETAILS());
            final ListView myList = (ListView)v.findViewById(android.R.id.list);

            setListAdapter(myAdapter);
            //onListItemClick(myList, getListView(), getSelectedItemPosition(), getId());
            myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {


                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //test listener
                Toast.makeText(getActivity(), ((TextView) view).getText(), Toast.LENGTH_SHORT).show();


            }
        });

    }

    public interface onDetailFragListener{
        public void onDetailFrag(int id);
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        mListener.onDetailFrag(position);
        getListView().setItemChecked(position,true);

    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            mListener = (onDetailFragListener) activity;
        } catch (ClassCastException e){
            throw new ClassCastException(activity.toString() + "get it together");
        }
    }
}
