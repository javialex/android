package com.example.administrador.javialex_lab29;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, LocationListener {

    private SupportMapFragment mapFragment;
    private GoogleMap miMapa;
    private LatLng p1 = new LatLng(-16.491441, -68.144682);
    private LatLng p2 = new LatLng(-16.498150, -68.152795);
    private LatLng p3 = new LatLng(-16.497386, -68.164521);
    private final static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapMain);
        mapFragment.getMapAsync(this);

    }

    public void irAMapaClick(View view) {
        Intent i = new Intent(MainActivity.this, MapsActivity.class);
        startActivity(i);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        miMapa = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(MainActivity.this, "Es necesario dar permisos de localizacion", Toast.LENGTH_SHORT).show();
            return;
        }
        miMapa.setMyLocationEnabled(true);


    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public void dibujarClick(View view) {
        MarkerOptions mrk1 = new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.mr2)).position(p1);
        MarkerOptions mrk2 = new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.mr2)).position(p2);
        MarkerOptions mrk3 = new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.mr2)).position(p3);


        miMapa.addMarker(mrk1);
        miMapa.addMarker(mrk2);
        miMapa.addMarker(mrk3);

        PolylineOptions pp = new PolylineOptions();
        pp.add(p1);
        pp.add(p2);
        pp.add(p3);
        pp.color(Color.RED);
        miMapa.addPolyline(pp);

    }

    public void limpiarClick(View view) {
        miMapa.clear();
    }

    private String obtenerUrlDirections(LatLng origen, LatLng destino){
        String paramOrigen = "origin="+origen.latitude + "," + origen.latitude;
        String paramDestino = "destination="+destino.latitude + "," + destino.latitude;
        String parametros = paramOrigen + "&" + paramDestino;
        String url = "https://maps.googleapis.com/maps/api/directions/json?" + parametros;
        Log.i(TAG, url);
        return url;
    }

    public void calcularClick(View view) {
        String urlDir = obtenerUrlDirections(p1, p2);
        DescargarDirecciones descargarDir = new DescargarDirecciones();
        descargarDir.execute(urlDir);
    }


    private class DescargarDirecciones extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {
            String datos = "";
            datos = descargar(params[0]);
            return datos;
        }

        private String descargar(String stringUrl) {
            String datos = "";
            InputStream inputStream = null;
            HttpURLConnection urlConnection = null;
            BufferedReader br = null;

            try {
                URL url = new URL(stringUrl);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();
                inputStream = urlConnection.getInputStream();
                br = new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer sb = new StringBuffer();
                String linea;
                while((linea = br.readLine()) != null){
                    sb.append(linea);
                }
                datos = sb.toString();
                br.close();

            } catch (MalformedURLException e) {
                Log.e(TAG, "Error url malformada" + e.getMessage());
            } catch (IOException e) {
                Log.e(TAG, "Error url " + e.getMessage());
            }
            return datos;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //Log.i(TAG,"json: "+s);
            ParsearDatos parsearDatos = new ParsearDatos();
            parsearDatos.execute(s);
        }
    }

    private class ParsearDatos extends AsyncTask<String, Void, ArrayList<LatLng>>{



        @Override
        protected ArrayList<LatLng> doInBackground(String... params) {
            JSONObject jsonObject;
            ArrayList<LatLng> rutas = null;

            try {
                jsonObject = new JSONObject(params[0]);
                DirectionsJSONParser dp = new DirectionsJSONParser();
                rutas = dp.getLatLngPoint(jsonObject);
                Log.i(TAG, "Numero de rutas: " + rutas.size());
            } catch (JSONException e) {
                Log.e(TAG, "Error conversion JSON" + e.getMessage());
            }
            return rutas;
        }

        @Override
        protected void onPostExecute(ArrayList<LatLng> latLngs) {
            PolylineOptions polylineOptions = new PolylineOptions();
            polylineOptions.addAll(latLngs);
            polylineOptions.width(6);
            polylineOptions.color(Color.BLUE);
            miMapa.addPolyline(polylineOptions);

        }
    }
}