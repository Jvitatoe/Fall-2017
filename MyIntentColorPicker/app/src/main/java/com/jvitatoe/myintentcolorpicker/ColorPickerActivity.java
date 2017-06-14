package com.jvitatoe.myintentcolorpicker;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

public class ColorPickerActivity extends AppCompatActivity {

    NumberPicker numPicker = null;//red
    NumberPicker numPicker2 = null;//green
    NumberPicker numPicker3 = null;//blue
    Button okButton;
    private View colorViewer;
    private int bGColor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_picker);

        colorViewer = findViewById(R.id.colorSpace);
        numPicker = (NumberPicker)findViewById(R.id.numberPicker);
        numPicker.setMaxValue(255);
        numPicker.setMinValue(0);
        numPicker.setWrapSelectorWheel(true);
        numPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker,
                                      int oldVal,
                                      int newVal) {
                setBackground(newVal);

            }
        });
        numPicker2 = (NumberPicker)findViewById(R.id.numberPicker2);
        numPicker2.setMaxValue(255);
        numPicker2.setMinValue(0);
        numPicker2.setWrapSelectorWheel(true);

        numPicker2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker,
                                      int oldVal,
                                      int newVal) {
                setBackground((newVal));
            }
        });
        numPicker3 = (NumberPicker)findViewById(R.id.numberPicker3);
        numPicker3.setMaxValue(255);
        numPicker3.setMinValue(0);
        numPicker3.setWrapSelectorWheel(true);
        numPicker3.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker,
                                      int oldVal,
                                      int newVal) {
                setBackground(newVal);
            }
        });
        colorViewer.setBackgroundColor(bGColor);
        okButton = (Button)findViewById(R.id.button);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnIntent();

            }
        });

    }
    public void returnIntent(){
        Intent returnIntent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putInt("Red", numPicker.getValue());
        bundle.putInt("Green", numPicker2.getValue());
        bundle.putInt("Blue", numPicker3.getValue());
        returnIntent.putExtras(bundle);
        setResult(RESULT_OK, returnIntent);
        finish();
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
        int id = item.getItemId();
        switch(id) {
            case R.id.back_button:
                Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivityForResult(myIntent, 0);
                return true;
            //noinspection SimplifiableIfStatement
            case  R.id.action_settings:
                return true;
            default:

                return super.onOptionsItemSelected(item);
        }
    }


}
