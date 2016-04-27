package com.example.administrador.javialex_lab21;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.security.MessageDigest;

/**
 * Created by Administrador on 25/04/2016.
 */
public class MiServiceMessage extends Service {
    final Messenger messenger = new Messenger(new MiHandler());
    private static Context context;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        context = this;
        Toast.makeText(context, "El servicio está iniciado!", Toast.LENGTH_LONG).show();
        return messenger.getBinder();
    }

    @Override
    public void onDestroy() {
        Toast.makeText(context, "El servicio ha terminado!", Toast.LENGTH_LONG).show();
    }

    private static class MiHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Toast.makeText(context, "Hola " + msg.obj.toString(), Toast.LENGTH_LONG).show();
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }
}