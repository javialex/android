package com.example.administrador.javialex_lab21;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrador on 21/04/2016.
 */
public class MiIntentService extends IntentService {
    private static String TAG = "MiIntentService";

    public MiIntentService() {
        super("MiIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Uri datosUri = intent.getData();
        String urlArchivo = intent.getStringExtra("URL_DESCARGA");
        String nombreArchivo = datosUri.getLastPathSegment();
        InputStream inputStream = null;
        OutputStream outputStream = null;
        HttpURLConnection connection = null;
        try {
            URL url = new URL(urlArchivo);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            inputStream = connection.getInputStream();
            outputStream = new FileOutputStream("/sdcard/" + nombreArchivo);
            byte datos[] = new byte[2096];
            long total = 0;
            int contador;//Bites que se escribirán por cada vuelta
            while ((contador = inputStream.read()) != -1) {
                total += contador;
                outputStream.write(datos, 0, contador);
            }

            // Construyendo una notificación usando un patrón decorator (Añadiendo atributos uno por uno)
            Notification notification = new NotificationCompat.Builder(getApplicationContext())
                    .setContentTitle("Archivo Descargado")
                    .setContentText("El archivo "+nombreArchivo+" fue descargado exitosamente")
                    .setTicker("Notificacion")
                    .setAutoCancel(true)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .build();
            //getSystemService: Se hace una referencia al servicio que corre todo el tiempo
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(1,notification);

        } catch (MalformedURLException e) {
            Log.e(TAG, "Error en la URL " + e.getMessage());

        } catch (IOException e) {
            Log.e(TAG, "El archivo no puede ser encontrado " + e.getMessage());
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    Log.e(TAG, "Error al cerrar Streams ");
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    Log.e(TAG, "Error al cerrar Streams ");
                }
            }
            if (connection != null) {
                connection.disconnect();
            }


        }

    }
}
