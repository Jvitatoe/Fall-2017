/**
 * 
 */
package com.example.fragmentplay;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author aaron
 *
 */
public class Fragment2 extends Fragment {
	int bgcolor = Color.rgb(211,231,251);
	View parentView = null;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	    // Inflate the layout for this fragment
	    this.parentView =  inflater.inflate(R.layout.fragment2_view, container, false);
	    this.parentView.setBackgroundColor(bgcolor);
	    return this.parentView;
	}

	public void setBackgroundColor(int c) {
		this.bgcolor = c;
		if (parentView != null)
	        this.parentView.setBackgroundColor(bgcolor);
	}
}
