package com.jvitatoe.workouts_main;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;
import android.view.MenuItem;


/**
 * Created by JVitatoe on 11/30/15.
 */

public class WorkoutLogin extends AppCompatActivity
    implements WorkoutFragment.OnWorkoutClickListener{
    Button b1,b2;
    EditText ed1,ed2;
    TextView tx1,tx2;
    int counter = 3;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_view);

        ed1=(EditText)findViewById(R.id.editText);
        ed2=(EditText)findViewById(R.id.editText2);
        b1=(Button)findViewById(R.id.button);
        b2=(Button)findViewById(R.id.button2);
        tx2=(TextView)findViewById(R.id.textView3);
        tx1=(TextView)findViewById(R.id.textView);
        tx2.setVisibility(View.GONE);

        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (ed1.getText().toString().equals("admin") &&

                        ed2.getText().toString().equals("admin")) {
                    Toast.makeText(getApplicationContext(), "Redirecting...",
                            Toast.LENGTH_SHORT).show();
                    setContentView(R.layout.activity_workout);
                    if (findViewById(R.id.fragment_container) != null) {
                        WorkoutFragment firstFragment = new WorkoutFragment();
                        firstFragment.setArguments(getIntent().getExtras());
                        getSupportFragmentManager()
                                .beginTransaction()
                                .add(R.id.fragment_container, firstFragment)
                                .commit();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong Credentials",
                            Toast.LENGTH_SHORT).show();

                    tx2.setVisibility(View.VISIBLE);
                    tx2.setBackgroundColor(Color.RED);
                    counter--;
                    tx2.setText(Integer.toString(counter));

                    if (counter == 0) {
                        b1.setEnabled(false);
                    }
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
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

