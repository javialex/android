package com.example.administrador.javialex_lab28;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Administrador on 03/05/2016.
 */
public class MiServicio extends IntentService {
    //Lo bueno de usar un IntentService es que se cierra automáticamente, además de que corre en un hilo alterno de manera automática, en cambio si fuera extendido de una
    //clase Servicio se ejecutaría en el hilo principal
    Handler handler;

    public MiServicio() {
        super("MiServicio");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        handler = new Handler();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Log.e("TAG", "Error Sleep " + e.getMessage());
            }
            final int iter = i + 1;
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MiServicio.this, "Iteracion " + iter, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
