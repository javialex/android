package com.example.administrador.javialex_lab21;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ServiceConfigurationError;
import java.util.Timer;
import java.util.TimerTask;

import java.util.logging.LogRecord;

/**
 * Created by Administrador on 21/04/2016.
 */
public class RelojService extends Service {
    private Timer relojTimer = new Timer();
    private static final long INTERVALO = 10;
    private static MainActivity updater;
    private String relojString = "";
    private Handler handler;

    public static void setActivityEscucha(MainActivity _updater){
        updater = _updater;
    }
    @Override
    public void onCreate(){
        super.onCreate();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg){
                updater.actualizarReloj(relojString);
            }

        };
        IniciarReloj();
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        pararReloj();
    }

    public void IniciarReloj(){
        relojTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                relojString = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss").format(new Date());
                handler.sendEmptyMessage(0);
            }
        },0,INTERVALO);
    }
    public void pararReloj(){
        if(relojTimer!=null){
            relojTimer.cancel();
        }
    }
}
