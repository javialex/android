package com.example.administrador.javialex_lab27;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void cambiarConfClick(View view){
        SharedPreferences prefs = getSharedPreferences(MiWidget.SP_NOMBRE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(MiWidget.SP_WIDGET_NOMBRE, "NOMBRE QUEMADO");
        editor.putString(MiWidget.SP_WIDGET_APELLIDO, "APELLIDO QUEMADO");
        editor.commit();
        Toast.makeText(MainActivity.this,"Se ejecuto la accion",Toast.LENGTH_LONG).show();
        //finish();
    }
}
