package com.example.administrador.javialex_lab19;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Integer> lstNumeros;
    private EditText etxLimite;
    private float limite;
    private TextView txvResultado;
    private Thread th;
    private ProgressBar pbarProgreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etxLimite = (EditText) findViewById(R.id.etxLimite);
        txvResultado = (TextView) findViewById(R.id.txvResultado);
        pbarProgreso = (ProgressBar) findViewById(R.id.pbarProgreso);
        pbarProgreso.setProgress(0);
    }

    private ArrayList<Integer> burbuja(ArrayList<Integer> lista) {
        int aux;
        for (int i = 0; i < lista.size(); i++) {
            for (int j = 0; j < lista.size(); j++) {
                if (lista.get(j) > lista.get(i)) {
                    aux = lista.get(j);
                    lista.set(j, lista.get(i));
                    lista.set(i, aux);
                }
            }
        }
        return lista;
    }

    private void imprimir(ArrayList<Integer> lista) {
        String res = "";
        for (Integer i : lista) {
            res += i + ", ";
        }
        res += ",";
        res = res.replace(", ,", ".");
        txvResultado.setText(res);
    }

    public void cargar(View view) {
        limite = Integer.parseInt(etxLimite.getText().toString());
        lstNumeros = new ArrayList<>();
        Random rnd = new Random();
        for (int i = 0; i < limite; i++) {
            lstNumeros.add((int) (rnd.nextDouble() * 100 + 1));
        }
        imprimir(lstNumeros);
    }

    public void ordenar(View view) {
        burbuja(lstNumeros);
        imprimir(lstNumeros);
    }

    public void limpiar(View view) {
        etxLimite.setText("");
        txvResultado.setText("");
    }

    public void ordenarHilo(View view) {
        th = new Thread(new Runnable() {
            @Override
            public void run() {
                float inc = 100/limite;
                int aux;
                pbarProgreso.post(new Runnable() {
                    @Override
                    public void run() {
                        pbarProgreso.setProgress(0);
                    }
                });
                float sum = 0;
                for (int i = 0; i < lstNumeros.size(); i++) {
                    sum+=inc;
                    final float finalSum = sum;
                    pbarProgreso.post(new Runnable() {
                        @Override
                        public void run() {
                            pbarProgreso.setProgress((int)finalSum);
                        }
                    });
                    for (int j = 0; j < lstNumeros.size(); j++) {
                        if (lstNumeros.get(j) > lstNumeros.get(i)) {
                            aux = lstNumeros.get(j);
                            lstNumeros.set(j, lstNumeros.get(i));
                            lstNumeros.set(i, aux);
                        }
                    }
                    pbarProgreso.post(new Runnable() {
                        @Override
                        public void run() {
                            pbarProgreso.setProgress(100);
                        }
                    });
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            imprimir(lstNumeros);
                        }
                    });
                }
                /*No se puede directamente ir de hilos separados a un hilo principal:*/
                //burbuja(lstNumeros);
                //Simprimir(lstNumeros);

            }
        });
        th.start();
    }
    public void interrumpir(){
        th.interrupt();
    }
}
