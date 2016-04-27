package com.example.android.javialex_lab02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText etxNumero1;
    EditText etxNumero2;
    Button btnCalcular;
    Button btnCalcularMultiplicacion;
    TextView txvResultado;
    TextView txvResultado2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etxNumero1 = (EditText) findViewById(R.id.etxNumero1);
        etxNumero2 = (EditText) findViewById(R.id.etxNumero2);
        btnCalcular = (Button) findViewById(R.id.btnCalcular);
        txvResultado = (TextView) findViewById(R.id.txvResultado);
        txvResultado2 = (TextView) findViewById(R.id.txvResultado2);
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numero1 = Integer.parseInt(etxNumero1.getText().toString());
                int numero2 = Integer.parseInt(etxNumero2.getText().toString());
                int resultado = numero1+numero2;
                txvResultado.setText("El resultado de la suma es: "+resultado);
                txvResultado2.setText("El resultado de la multiplcacion es: "+(numero1*numero2));
            }
        });
    }}
