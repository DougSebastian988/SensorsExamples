package com.example.android.sensorsexamples;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity implements SensorEventListener {

    public TextView textViewSensor;
    public TextView textView1;
    public TextView textView2;
    public TextView textView3;
    public TextView textView4;
    public TextView textView5;
    public TextView textView6;

    private int sensor_type;
    private SensorManager sensorManager;
    double ax,ay,az;   // these are the acceleration in x,y and z axis

    private Handler handler = new Handler();


    private Runnable runnable = new Runnable() {
        @Override
        public void run() {

            switch (sensor_type) {
                default:
                    break;
            }
      /* and here comes the "trick" */
            handler.postDelayed(this, 100);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        textViewSensor = (TextView) findViewById(R.id.textViewSensor);
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);
        textView5 = (TextView) findViewById(R.id.textView5);
        textView6 = (TextView) findViewById(R.id.textView6);

        sensor_type = Sensor.TYPE_MAGNETIC_FIELD;
        textViewSensor.setText("Magnetic Field");
        //handler.postDelayed(runnable, 100);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_NORMAL);

    }

    private  void monitorSensor(int type) {
        sensorManager.unregisterListener(this);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(type), SensorManager.SENSOR_DELAY_NORMAL);
    }


    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
       
        if (event.sensor.getType()==Sensor.TYPE_MAGNETIC_FIELD){
            ax=event.values[0];
            ay=event.values[1];
            az=event.values[2];
            textView1.setText("X = " + ax);
            textView2.setText("Y = " + ay);
            textView3.setText("Z = " + az);
        }
        if (event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            ax=event.values[0];
            ay=event.values[1];
            az=event.values[2];
            textView1.setText("X = " + ax);
            textView2.setText("Y = " + ay);
            textView3.setText("Z = " + az);
        }
        if (event.sensor.getType()==Sensor.TYPE_GYROSCOPE){
            ax=event.values[0];
            ay=event.values[1];
            az=event.values[2];
            textView1.setText("X = " + ax);
            textView2.setText("Y = " + ay);
            textView3.setText("Z = " + az);
        }
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
        if (id == R.id.magnetic_field) {
            sensor_type = Sensor.TYPE_MAGNETIC_FIELD;
            textViewSensor.setText("Magnetic Field");
            monitorSensor(sensor_type);
            return true;
        }
        if (id == R.id.accelerometer) {
            sensor_type = Sensor.TYPE_ACCELEROMETER;
            textViewSensor.setText("Accelerometer");
            monitorSensor(sensor_type);
            return true;
        }

        if (id == R.id.gyroscope) {
            sensor_type = Sensor.TYPE_GYROSCOPE;
            textViewSensor.setText("Gyroscope");
            monitorSensor(sensor_type);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
