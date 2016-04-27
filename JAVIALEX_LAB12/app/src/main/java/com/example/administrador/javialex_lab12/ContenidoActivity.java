package com.example.administrador.javialex_lab12;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ContenidoActivity extends AppCompatActivity {
    private TextView txvSaludo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenido);
        Bundle params = getIntent().getExtras();
        String txtUsuario = params.getString("USUARIO");
        txvSaludo = (TextView) findViewById(R.id.txvSaludo);
        txvSaludo.setText("Hola "+txtUsuario);
    }
}
