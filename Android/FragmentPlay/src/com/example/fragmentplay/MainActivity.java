package com.example.fragmentplay;

import java.util.Random;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
//import android.support.v4.view.GestureDetector;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends Activity {
	private int frameNumber = 0;	//indicates currently loaded frame
    private GestureDetector mDetector; //to handle flings (swipes)

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		MyFragment frag0 = new MyFragment();
         
         // Add the fragment to the 'fragment_container' FrameLayout
        FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(R.id.fragment_container, frag0);  //.add instead of replace?

            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
        mDetector = new GestureDetector(this, new MyGestureListener());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override 
    public boolean onTouchEvent(MotionEvent event){ 
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
    
	//to handle flings (swipes)
    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Gestures"; 
        
        @Override
        public boolean onDown(MotionEvent event) { 
            Log.d(DEBUG_TAG,"onDown: " + event.toString()); 
            return true;	//needed so all gestures are checked
        }
        
        private final int VELOCITY_TRIGGER = 400;
        private final int DIST_TRIGGER = 190;
        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2, 
                float velocityX, float velocityY) {
            Log.d(DEBUG_TAG, "onFling: " + velocityX + " --- " + event1.toString()+event2.toString());
            Log.d(DEBUG_TAG,"         x0="+event1.getX()+"   x1="+event2.getX());
            if (velocityX > VELOCITY_TRIGGER && event2.getX() - event1.getX() > DIST_TRIGGER) {
            	changeFrame();
            }
            return true;
        }
    }
	/** Called when changeFrame-button is clicked */
    public void changeFrame(View button) {
    	changeFrame();
    }
    
    public void changeFrame() {
    	Log.i("MainActivity", "The change-frame - frame is now " + frameNumber);
    	Random rand = new Random();
    	frameNumber = 1 - frameNumber;
    	Fragment frag = null;
    	switch (frameNumber) {	// change frames here
    	case 0:
    		frag = new MyFragment();
    		((MyFragment)(frag)).setBackgroundColor(Color.RED);
    		break;
    	case 1: 
    		frag = new Fragment2();
    		((Fragment2)(frag)).setBackgroundColor(Color.rgb(rand.nextInt(255),
    				rand.nextInt(255),rand.nextInt(100)+155));
            break;
    	}	
    	FragmentTransaction ft = getFragmentManager().beginTransaction();
		   ft.replace(R.id.fragment_container, frag);  //
           ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
           ft.addToBackStack(null);
        ft.commit();
    	//frag.getView().setBackgroundColor(Color.rgb(121, 211, 177));
    	//frag.getView().setBackgroundColor(Color.RED);

    }
}
