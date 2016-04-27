package com.example.administrador.javialex_lab21;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText etxUrlIntent;
    private TextView txvReloj;
    private MiServiceBinder miServiceBinder;
    private Messenger messenger = null;

    private ServiceConnection servConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MiServiceBinder.MIBinder binder = (MiServiceBinder.MIBinder) service;
            miServiceBinder = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    private ServiceConnection messConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            messenger = new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etxUrlIntent = (EditText) findViewById(R.id.etxUrl);
        RelojService.setActivityEscucha(this);
        txvReloj = (TextView) findViewById(R.id.txvReloj);
    }

    public void ejecutarIntentService(View view) {
        Intent intent = new Intent(MainActivity.this, MiIntentService.class);
        String urlArchivo = etxUrlIntent.getText().toString();
        intent.putExtra("URL_DESCARGA", urlArchivo);
        intent.setData(Uri.parse(urlArchivo));
        startService(intent);
    }

    public void ejecutarServicioReloj(View view) {
        Intent servicio = new Intent(MainActivity.this, RelojService.class);
        startService(servicio);
    }

    public void actualizarReloj(String tiempo) {
        txvReloj.setText(tiempo);
    }

    public void cerrarReloj(View view) {
        Intent servicio = new Intent(MainActivity.this, RelojService.class);
        stopService(servicio);
    }

    public void iniciarBinder(View view) {
        Intent i = new Intent(MainActivity.this, MiServiceBinder.class);
        bindService(i, servConnection, Context.BIND_AUTO_CREATE);
    }

    public void obtenerNumero(View view) {
        if (miServiceBinder != null) {
            // Las variables nativas no tienen "toString()"
            String resultado = Integer.toString(miServiceBinder.obtenerNumero());
            Toast.makeText(MainActivity.this, "El resultado es: " + resultado, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this, "¡El servicio no está iniciado!", Toast.LENGTH_LONG).show();
        }
    }

    public void terminarBinder(View view) {
        if (miServiceBinder != null) {
            miServiceBinder.stopForeground(true);
            //Si no hago esto, el servicio sigue funcionando
            unbindService(servConnection);
            miServiceBinder = null;
        } else {
            Toast.makeText(MainActivity.this, "¡El servicio no está iniciado!", Toast.LENGTH_LONG).show();
        }

    }

    public void iniciarMessage(View view) {
        Intent i = new Intent(MainActivity.this,MiServiceMessage.class);
        bindService(i,messConnection,Context.BIND_AUTO_CREATE);

    }

    public void saludarMessage(View view) {
        if(messenger!=null){
            Message msg =  Message.obtain(null,1,"Javier");
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                Log.e("MainActivity","Error al enviar mensaje"+e.getMessage());
                e.printStackTrace();
            }
        }else{
            Toast.makeText(MainActivity.this, "¡El servicio no está iniciado!", Toast.LENGTH_LONG).show();
        }
    }

    public void terminarMessage(View view) {
        if (messenger != null) {
            unbindService(messConnection);
            messenger=null;
        }else{
            Toast.makeText(MainActivity.this, "¡El servicio no está iniciado!", Toast.LENGTH_LONG).show();
        }
    }
}
