package com.example.administrador.javialex_lab27;

import android.app.VoiceInteractor;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ConfigWidgetActivity extends AppCompatActivity {
    private EditText etxNombreConf;
    private EditText etxApellidoConf;
    private int widgetId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_widget);
        etxNombreConf = (EditText) findViewById(R.id.etxNombreConf);
        etxApellidoConf = (EditText) findViewById(R.id.etxApellidoConf);
        Bundle parametros = getIntent().getExtras();
        widgetId = parametros.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        setResult(RESULT_CANCELED);
    }

    public void btnAsignarClick(View view) {
        SharedPreferences prefs = getSharedPreferences(MiWidget.SP_NOMBRE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        String nombre = etxNombreConf.getText().toString();
        String apellido = etxApellidoConf.getText().toString();
        editor.putString(MiWidget.SP_WIDGET_NOMBRE, nombre);
        editor.putString(MiWidget.SP_WIDGET_APELLIDO, apellido);
        editor.commit();
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(ConfigWidgetActivity.this);
        MiWidget.actualizarWidgets(ConfigWidgetActivity.this, appWidgetManager, widgetId);
        Intent intent = new Intent();
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void btnCancelarClick(View view) {
        finish();
    }
}
