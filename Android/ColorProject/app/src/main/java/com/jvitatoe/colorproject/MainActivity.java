package com.jvitatoe.colorproject;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

public class MainActivity extends AppCompatActivity {

    NumberPicker numPicker = null;
    NumberPicker numPicker2 = null;
    NumberPicker numPicker3 = null;
    Button blendButton;
    private View colorViewer;
    int bGColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colorViewer = findViewById(R.id.colorSpace);
        numPicker = (NumberPicker)findViewById(R.id.numberPicker);
        numPicker.setMaxValue(255);
        numPicker.setMinValue(0);
        numPicker.setWrapSelectorWheel(true);
        numPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                setBackground(newVal);

            }
        });
        numPicker2 = (NumberPicker)findViewById(R.id.numberPicker2);
        numPicker2.setMaxValue(255);
        numPicker2.setMinValue(0);
        numPicker2.setWrapSelectorWheel(true);

        numPicker2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                setBackground((newVal));
            }
        });
        numPicker3 = (NumberPicker)findViewById(R.id.numberPicker3);
        numPicker3.setMaxValue(255);
        numPicker3.setMinValue(0);
        numPicker3.setWrapSelectorWheel(true);
        numPicker3.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                setBackground(newVal);
            }
        });
        colorViewer.setBackgroundColor(bGColor);

        blendButton = (Button)findViewById(R.id.button);
        blendButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent i = new Intent(MainActivity.this, BlenderActivity.class);
                startActivity(i);
            }
        });
        }
    public void setBackground(int neVal){
        int wVal = numPicker3.getValue();
        int xVal = numPicker2.getValue();
        int vVal = numPicker.getValue();

        bGColor = Color.rgb(vVal, xVal, wVal);
        colorViewer.setBackgroundColor(bGColor);
}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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


}
