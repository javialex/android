package com.example.administrador.javialex_lab23;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private TextView txvEjeX;
    private TextView txvEjeY;
    private TextView txvEjeZ;
    private TextView txvMensaje;
    private SensorManager sensorManager;
    private Sensor sensorAcelero;
    private Sensor sensorProxim;
    private RelativeLayout rlyFondo;
    private ImageView imvwStar1;
    private ImageView imvwStar2;
    private int px;
    private int py;
    private int tx;
    private int ty;
    private Random rnd;
    private int t;
    private int prop = 100;
    private TextView txvwProximidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txvEjeX = (TextView) findViewById(R.id.ejeX);
        txvEjeY = (TextView) findViewById(R.id.ejeY);
        txvEjeZ = (TextView) findViewById(R.id.ejeZ);
        txvMensaje = (TextView) findViewById(R.id.mensaje);
        rlyFondo = (RelativeLayout) findViewById(R.id.rlyFondo);
        imvwStar1 = (ImageView) findViewById(R.id.imgStar1);
        imvwStar2 = (ImageView) findViewById(R.id.imgStar2);
        txvwProximidad = (TextView) findViewById(R.id.txvwProximidad);
        //Hago una medición de la pantalla y en base a ese cálculo se dimensiona la imagen a mostrarse
        t = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, prop, getResources().getDisplayMetrics());
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorAcelero = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorProxim = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);


        if (sensorAcelero == null) {
            Toast.makeText(MainActivity.this, "El celular no tiene acelerometro", Toast.LENGTH_LONG).show();
            return;
        }
        if (sensorProxim == null) {
            Toast.makeText(MainActivity.this, "El celular no tiene sensor de proximidad!", Toast.LENGTH_LONG).show();
            return;
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            tx = rlyFondo.getWidth();
            ty = rlyFondo.getWidth();
            //Posicionando la imagen al centro
            px = rlyFondo.getWidth() / 2 - prop / 2;
            py = rlyFondo.getWidth() / 2 - prop / 2;
            rnd = new Random();

            //Podriamos hacerlo manualmente:
            //RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(prop,prop);
            //Pero es mejor así:
            RelativeLayout.LayoutParams layoutRel = new RelativeLayout.LayoutParams(t, t);
            layoutRel.setMargins((int) (rnd.nextDouble() * tx - prop), (int) (rnd.nextDouble() * ty - prop), 0, 0);
            imvwStar1.setLayoutParams(layoutRel);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensorAcelero, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, sensorProxim, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            Float x = event.values[0];
            Float y = event.values[1];
            Float z = event.values[2];
            txvEjeX.setText("Pos X: " + x.intValue() + " - Float: " + x);
            txvEjeY.setText("Pos Y: " + y.intValue() + " - Float: " + y);
            txvEjeZ.setText("Pos Z: " + z.intValue() + " - Float: " + z);
            if (y < 0) {
                if (x > 0) {
                    txvMensaje.setText("Izquierda Invertido");
                } else {
                    txvMensaje.setText("Derecha Invertido");
                }
            } else {
                if (x > 0) {
                    txvMensaje.setText("Izquierda");
                } else {
                    txvMensaje.setText("Derecha");
                }
            }
            if ((z < 0 && py > 0) || (z > 0 && py > ty - prop)) {
                py += z.intValue();
            }
            if ((x < 0 && px > 0) || (x > 0 && px > tx - prop)) {
                px += x.intValue();
            }
            RelativeLayout.LayoutParams layoutRel = new RelativeLayout.LayoutParams(t, t);
            layoutRel.setMargins(px, py, 0, 0);
            imvwStar2.setLayoutParams(layoutRel);
            if (imvwStar1.getTop() == imvwStar2.getTop() && imvwStar1.getLeft() == imvwStar2.getLeft()) {
                Toast.makeText(MainActivity.this, "Lo lograste!", Toast.LENGTH_SHORT);
                rnd = new Random();
                RelativeLayout.LayoutParams layoutRel2 = new RelativeLayout.LayoutParams(t, t);
                layoutRel2.setMargins((int) (rnd.nextDouble() * tx - prop), (int) (rnd.nextDouble() * ty - prop), 0, 0);
                imvwStar2.setLayoutParams(layoutRel2);
            }
        } else if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            float valor = event.values[0];
            txvwProximidad.setText("Proximidad: "+valor);
            if(valor<5){
                rlyFondo.setBackgroundColor(Color.MAGENTA);
            }else{
                rlyFondo.setBackgroundColor(Color.GREEN);
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
