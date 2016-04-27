package com.example.administrador.javialex_lab11;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText etxTexto;
    private ListView ltvwLista;
    private ImageButton btnAgregar;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etxTexto = (EditText) findViewById(R.id.etxTexto);
        ltvwLista = (ListView) findViewById(R.id.ltvLista);
        btnAgregar = (ImageButton) findViewById(R.id.btnAgregar);
        lista = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, lista);
        ltvwLista.setAdapter(adapter);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String texto = etxTexto.getText().toString();
                if (!texto.isEmpty()) {
                    lista.add(0, texto);
                    adapter.notifyDataSetChanged();
                    etxTexto.setText("");
                } else {
//                    Toast toast = Toast.makeText(getApplicationContext(), "Debe introducir al menos un carácter...", Toast.LENGTH_LONG);
                    Toast toast = Toast.makeText(MainActivity.this,R.string.no_carteres, Toast.LENGTH_SHORT);
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
