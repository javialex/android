package com.example.administrador.javialex_lab30;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.games.internal.constants.NotificationChannel;
import com.google.android.gms.gcm.GcmListenerService;

/**
 * Created by Administrador on 05/05/2016.
 */
public class ReceptorMensajesService extends GcmListenerService {
    private final static String TAG = "ReceptorMensajesService";

    @Override
    public void onMessageReceived(String from, Bundle data) {
        super.onMessageReceived(from, data);
        String mensaje = data.getString(Parametros.MSG_PARAM_MENSAJE);
        String titulo = data.getString(Parametros.MSG_PARAM_TITULO);
        Log.i(TAG, "Mensaje " + mensaje + " Titulo " + titulo);
        enviarNotificacion(mensaje, titulo);
    }

    private void enviarNotificacion(String mensaje, String titulo) {
        Intent intent = new Intent(this, MainActivity.class);
        //Si hay una actividad creada, la limpie.
        intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder notificacion = new NotificationCompat.Builder(this).setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(titulo).setContentText(mensaje).setAutoCancel(true)
                .setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificacion.build());
    }
}
