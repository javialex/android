package com.example.administrador.javialex_lab21;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by Administrador on 25/04/2016.
 */
public class MiServiceBinder extends Service {
    private final IBinder binder = new MIBinder();
    private final Random random = new Random();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(this,"Servicio iniciado",Toast.LENGTH_LONG).show();
        return binder;
    }

    public class MIBinder extends Binder{
        public MiServiceBinder getService(){
            return MiServiceBinder.this;
        }
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this,"Servicio terminado",Toast.LENGTH_LONG).show();
    }
    public int obtenerNumero(){
        return random.nextInt(100);
    }
}
