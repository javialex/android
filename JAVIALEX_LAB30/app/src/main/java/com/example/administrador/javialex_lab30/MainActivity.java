package com.example.administrador.javialex_lab30;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver receiverRegistro;
    private TextView txvMensaje;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txvMensaje = (TextView) findViewById(R.id.txvMensaje);
        receiverRegistro = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
                String token = sp.getString(Parametros.TOKEN_REGISTRO, null);
                if (token != null) {
                    txvMensaje.setText("El token es: " + token);
                } else {
                    txvMensaje.setText("El token no pudo ser recuperado");
                }
            }
        };
        if (verificarPlayServices()) {
            Intent registroIntent = new Intent(MainActivity.this, RegistroAppService.class);
        } else {
            txvMensaje.setText("EL DISPOSITIVO NO ES COMPATIBLE!");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(receiverRegistro, new IntentFilter(Parametros.RECEIVER_REGISTRO_COMPLETO));
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiverRegistro);
    }

    private boolean verificarPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultado = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultado == ConnectionResult.SUCCESS) {
            return true;
        } else {
            return false;
        }

    }
}
