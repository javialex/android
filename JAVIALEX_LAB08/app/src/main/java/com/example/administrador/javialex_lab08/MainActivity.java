package com.example.administrador.javialex_lab08;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ViewPersonalizado viewPersonalizado;
    private MiTextView mtxvTexto1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPersonalizado = (ViewPersonalizado) findViewById(R.id.viewPersonalizado);
        mtxvTexto1 = (MiTextView) findViewById(R.id.mtxvTextoNuevo);
        viewPersonalizado.setOnClickPersonalizado(new OnClickViewPersonalizado() {
            @Override
            public void mostrarDato(String dato) {
                Toast toast = Toast.makeText(getApplicationContext(), dato, Toast.LENGTH_SHORT);
                if (dato.equalsIgnoreCase("")) {
                    viewPersonalizado.setMensaje("");
                } else {
                    //viewPersonalizado.setMensaje("Su mensaje es: " + dato);
                    mtxvTexto1.setText("Su mensaje es este: " + dato);
                    toast.show();
                }
            }
        });

    }
}
