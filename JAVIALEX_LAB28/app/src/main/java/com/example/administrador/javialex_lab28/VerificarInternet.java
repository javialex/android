package com.example.administrador.javialex_lab28;

import android.accounts.NetworkErrorException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by Administrador on 03/05/2016.
 */
public class VerificarInternet extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //Cuando se ejecuta, lo hace en el hilo principal por lo tanto se debe lanzar otro hilo, servicio, tarea asincrona para no dejarla ahi
        boolean conectado = estaPrendido(context);
        if(conectado){
            Toast.makeText(context,"INTERNET ENCENDIDO",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(context,MiServicio.class);
            context.startService(i);
        }else{
            Toast.makeText(context,"INTERNET APAGADO",Toast.LENGTH_SHORT).show();
        }
    }

    /***
     * Función para controlar si el evento esta prendido.
     * @param context
     * @return
     */
    public boolean estaPrendido(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
}
