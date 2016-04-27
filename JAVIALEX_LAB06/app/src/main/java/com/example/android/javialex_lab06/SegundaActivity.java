package com.example.android.javialex_lab06;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SegundaActivity extends AppCompatActivity {
    private TextView txvTexto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);
        Bundle data = getIntent().getExtras();
        String texto = (String) data.get(Intent.EXTRA_TEXT);
        txvTexto = (TextView) findViewById(R.id.etxTextoSegAct);
        txvTexto.setText(texto);
    }
}
