package com.jvitatoe.myworkouts;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by JVitatoe on 9/14/15.
 */
public class Workout  {

    public static final String[] WORKOUTS = {"Deadlift", "Dumbbell flies, Tricep extension" };
    public static final String[] DETAILS =
            {"Deadlift","Dumbbell flies", "Tricep Extention"};

    public static String[] getWORKOUTS() {
        return WORKOUTS;
    }

    public static String[] getDETAILS() {
        return DETAILS;
    }
}



