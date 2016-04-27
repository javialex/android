package com.example.android.javialex_lab04;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText etxTexto;
    private Button btnAgregar;
    private ListView ltvwLista;
    private ArrayList<String> lista;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etxTexto = (EditText) findViewById(R.id.etxTexto);
        btnAgregar = (Button) findViewById(R.id.btnAgregar);
        ltvwLista = (ListView) findViewById(R.id.ltvwLista);
        lista = new ArrayList<>();
        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, lista);
        ltvwLista.setAdapter(adapter);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String texto = etxTexto.getText().toString();
                if (texto.length() > 0) {
                    lista.add(texto);
                    /*Actualizará toda la data que el adapter tiene*/
                    adapter.notifyDataSetChanged();
                    etxTexto.setText("");
                } else {
                    /*En caso de que no envie un texto con algun carácter, generamos una alerta*/
                    Toast toast = Toast.makeText(getApplicationContext(), "Debe introducir al menos un carácter...", Toast.LENGTH_LONG);
                    /*Mostramos*/
                    toast.show();
                }
            }
        });
        ltvwLista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                lista.remove(position);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }
}

