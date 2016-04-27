package com.example.administrador.javialex_lab08;

import android.widget.LinearLayout;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Administrador on 01/04/2016.
 */
public class ViewPersonalizado extends LinearLayout {
    private EditText etxTexto1;
    private Button btnMostrar;
    private TextView txvResultado;
    private OnClickViewPersonalizado listener;

    public ViewPersonalizado(Context context) {
        super(context);
        init();
    }

    public ViewPersonalizado(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        String service = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(service);
        layoutInflater.inflate(R.layout.view_personalizado, this, true);
        etxTexto1 = (EditText) findViewById(R.id.etxTexto1);
        btnMostrar = (Button) findViewById(R.id.btnMostrar);
        txvResultado = (TextView) findViewById(R.id.txvResultado);
        asignarEventos();
    }

    public void setOnClickPersonalizado(OnClickViewPersonalizado l) {
        listener = l;
    }


    public void setMensaje(String mensaje){
        txvResultado.setText(mensaje);

    }


    private void asignarEventos() {
        btnMostrar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.mostrarDato(etxTexto1.getText().toString());
            }
        });
    }
}
