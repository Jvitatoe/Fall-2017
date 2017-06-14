package com.jvitatoe.myintentcolorpicker;



import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public View color1;
    public View color2;
    public View mainView;
    public SeekBar seek;
    int red1, red2, green1, green2, blue1, blue2;
    int finalRed, finalGreen, finalBlue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.content_frame);

        mainView = findViewById(R.id.main_view);
        seek = (SeekBar)findViewById(R.id.seekBar);
        seek.setMax(100);
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar,
                                          int progress,
                                          boolean fromUser) {

                Double div = Double.valueOf(red2+(progress*0.01)*(red1-red2));
                finalRed = div.intValue();

                div = Double.valueOf(green2+(progress* 0.01) * (green1 - green2));
                finalGreen = div.intValue();

                div = Double.valueOf(blue2+(progress*0.01)*(blue1-blue2));
                finalBlue = div.intValue();

                mainView.setBackgroundColor(Color.rgb(finalRed,finalGreen,finalBlue) );

                Log.e("", "onProgressChanged " + progress/seek.getMax());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        color1 = findViewById(R.id.colorSpace1);;
        color1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickColor(v);
            }
        });
        color2 = findViewById(R.id.colorSpace2);

        color2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickColor(v);
            }
        });
    }

   /* private void updateMixer(int progress){
        Double div = Double.valueOf((progress/100)*(red1-red2))+red2;
        finalRed = div.intValue();

        div = Double.valueOf((progress / 100) * (green1 - green2))+green2;
        finalGreen = div.intValue();

        div = Double.valueOf((progress/100)*(blue1-blue2))+blue2;
        finalBlue = div.intValue();

        //mainView.setBackgroundColor(mBgColor + bGSeek * ((mBgColor2) - mBgColor));
        mainView.setBackgroundColor(Color.rgb(finalRed,finalGreen,finalBlue) );
}
*/

    private int interpolateColor(final int a,
                                 final int b,
                                 final float proportion) {
        final float[] hsva = new float[3];
        final float[] hsvb = new float[3];
        Color.colorToHSV(a, hsva);
        Color.colorToHSV(b, hsvb);

        for (int i = 0; i < 3; i++) {
            hsvb[i] = interpolate(hsva[i], hsvb[i], proportion);
        }
        return Color.HSVToColor(hsvb);

    }


    private float interpolate(final float a,
                              final float b,
                              final float proportion) {
        return (a + ((b - a) * proportion));
    }

    private void pickColor(View view){
        if(view == color1) {
            Intent i = new Intent(MainActivity.this, ColorPickerActivity.class);
            startActivityForResult(i, 0);
        }
        if(view == color2){
            Intent i = new Intent(MainActivity.this, ColorPickerActivity.class);
            startActivityForResult(i, 1);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0){
            if(resultCode == RESULT_OK){
                //  Toast.makeText(getApplicationContext(), getPickColorRequest, Toast.LENGTH_SHORT).show();
                Bundle bundle = data.getExtras();
                red1 = bundle.getInt("Red",-1);
                blue1 = bundle.getInt("Blue",-1);
                green1 = bundle.getInt("Green",-1);

                color1.setBackgroundColor(Color.rgb(red1, green1, blue1));
            }

        }
        if(requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Bundle bundle = data.getExtras();
                red2 = bundle.getInt("Red",-1);
                blue2 = bundle.getInt("Blue",-1);
                green2 = bundle.getInt("Green", -1);

                color2.setBackgroundColor(Color.rgb(red2, green2, blue2));
            }
        }
            if (resultCode == RESULT_CANCELED) {
                //Write your code if there's no result
                Toast.makeText(getApplicationContext(), "Nothing Returned!", Toast.LENGTH_SHORT).show();
            }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
        }
    }

