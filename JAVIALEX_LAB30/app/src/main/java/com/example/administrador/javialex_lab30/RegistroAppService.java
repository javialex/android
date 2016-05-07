package com.example.administrador.javialex_lab30;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

/**
 * Created by Administrador on 05/05/2016.
 */
public class RegistroAppService extends IntentService {
    private final static String TAG = "RegistroAppService";

    public RegistroAppService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        InstanceID instanceID = InstanceID.getInstance(this);
        try {
            String token = instanceID.getToken(getString(R.string.gcm_defaultSenderId), GoogleCloudMessaging.INSTANCE_ID_SCOPE);
            Log.i(TAG, "El token es: " + token);
            registrarToken(token);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Intent registroCompletoIntent = new Intent(Parametros.RECEIVER_REGISTRO_COMPLETO);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registroCompletoIntent);
    }

    private void registrarToken(String token) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        sp.edit().putString(Parametros.TOKEN_REGISTRO, token).commit();

    }
}
