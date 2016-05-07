package com.example.administrador.javialex_lab26;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.IllegalFormatCodePointException;

public class MainActivity extends AppCompatActivity implements LocationListener {
    private TextView txvLatitud;
    private TextView txvLongitud;
    private LocationManager locationManager;
    private TextView txvEstado;
    private TextView txvProvider1;
    private TextView txvProvider2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txvLatitud = (TextView) findViewById(R.id.txvLatitud);
        txvLongitud = (TextView) findViewById(R.id.txvLongitud);
        txvEstado = (TextView) findViewById(R.id.txvEstado);
        txvProvider1 = (TextView) findViewById(R.id.txvProvider1);
        txvProvider2 = (TextView) findViewById(R.id.txvProvider2);
    }

    public void iniciarGPS() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Boolean gpsHabilitado = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        Boolean networkHabilitado = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        txvEstado.setText("GPS: " + gpsHabilitado + " NETWORK: " + networkHabilitado);
        System.out.println("GPS: " + gpsHabilitado + " NETWORK: " + networkHabilitado);
        if (networkHabilitado) {
            //Hay dos posibles proveedores una por el GPS propio del celular y otro por red (triangulación en base al internet)
            if (Build.VERSION.SDK_INT >= 23 && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                //A partir de la versión 23 nos piden hacer esta verificación
                Toast.makeText(MainActivity.this, "Los permisos son necesarios", Toast.LENGTH_LONG).show();
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        } else {
            Toast.makeText(MainActivity.this, "Es necesario encender el GPS", Toast.LENGTH_LONG).show();
        }
    }

    public void obtenerClick(View view) {
        iniciarGPS();
    }

    @Override
    public void onLocationChanged(Location location) {
        Double latitud = location.getLatitude();
        Double longitud = location.getLongitude();
        txvLatitud.setText("Latitud: " + latitud.toString());
        txvLongitud.setText("Longitud: " + longitud.toString());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
        System.out.println(provider);
        if (provider.toUpperCase().contains("GPS")) {
            txvProvider1.setText("GPS: HABILITADO");
        }
        if (provider.toUpperCase().contains("NET")) {
            txvProvider2.setText("NETWORK: HABILITADO");
        }
    }

    @Override
    public void onProviderDisabled(String provider) {
        System.out.println(provider);
        if (provider.toUpperCase().contains("GPS")) {
            txvProvider1.setText("GPS: DESHABILITADO");
        }
        if (provider.toUpperCase().contains("NET")) {
            txvProvider2.setText("NETWORK: DESHABILITADO");
        }
    }
}
