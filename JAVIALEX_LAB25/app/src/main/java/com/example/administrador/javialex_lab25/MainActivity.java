package com.example.administrador.javialex_lab25;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.NfcA;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txvNombre;
    TextView txvCodigo;
    PendingIntent pendingIntent;
    NfcAdapter adapter;
    IntentFilter[] filters;
    final String TAG = "NFC";
    //Lo definimos así porque así nos pide en el métod enableForegroundDispach
    String[][] listaTech = new String[][]{
            new String[]{
                    NfcA.class.getName()
            }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);
        txvNombre = (TextView) findViewById(R.id.txvNombre);
        txvCodigo = (TextView) findViewById(R.id.txvNombre);
        pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        IntentFilter ndef = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);
        filters = new IntentFilter[]{
                ndef
        };
        //Como no sabemos que tipo tiene, puede ser una image...
        try {
            ndef.addDataType("*/*");
        } catch (IntentFilter.MalformedMimeTypeException e) {
            Log.e(TAG, "Error en el tipo de dato" + e);
        }
        adapter = NfcAdapter.getDefaultAdapter(this);
        if (getIntent() != null) {
            resolverIntent(getIntent());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.enableForegroundDispatch(this, pendingIntent, filters, listaTech);
    }

    @Override
    protected void onPause() {
        super.onPause();
        adapter.disableForegroundDispatch(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        resolverIntent(intent);
    }

    private void resolverIntent(Intent intent) {
        Context context = getApplicationContext();
        if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(intent.getAction())) {
            //Un Tag propio para el NFC
            Tag tagNfc = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            byte[] extraId = tagNfc.getId();
            //Con esto puedo obtener que tipo de tecnologia se está usando:
            //tagNfc.getTechList();
            StringBuilder sbCodigo = new StringBuilder();
            for (byte b : extraId) {
                //Convertimos el dato a exadecimal
                sbCodigo.append(String.format("%02X", b));
            }
            txvCodigo.setText(sbCodigo.toString());
        }
    }
}