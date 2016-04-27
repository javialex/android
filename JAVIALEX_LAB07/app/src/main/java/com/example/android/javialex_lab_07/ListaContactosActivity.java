package com.example.android.javialex_lab_07;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.javialex_lab_07.obj.Constantes;
import com.example.android.javialex_lab_07.obj.Contacto;

import java.util.ArrayList;

public class ListaContactosActivity extends AppCompatActivity{

    ArrayList<Contacto> lstContactos;
    private ListView ltvwListaContactos;
    private ArrayList<String> lista;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> lstContactosString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_contactos);
        lstContactos = new ArrayList<>();
        lstContactos.add(new Contacto("Ivan Chacolla", "75241486", "www.miteleferico.bo"));
        lstContactos.add(new Contacto("Javier Loza", "70568996", "www.miteleferico.bo"));
        lstContactos.add(new Contacto("Freddy Velasco", "72548526", "www.miteleferico.bo"));
        lstContactos.add(new Contacto("Carlos Ramirez", "75241486", "www.miteleferico.bo"));
        lstContactosString = new ArrayList<>();

        ltvwListaContactos = (ListView) findViewById(R.id.ltvwListaContactos);
        //lista = new ArrayList<>();
        adapter = new ArrayAdapter<String>(ListaContactosActivity.this, android.R.layout.simple_expandable_list_item_1, lstContactosString);
        ltvwListaContactos.setAdapter(adapter);

        for (Contacto item : lstContactos) {
            lstContactosString.add(item.getNombre());
            adapter.notifyDataSetChanged();
        }
        ltvwListaContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                Contacto contactoAux = lstContactos.get(position);
                intent = new Intent(ListaContactosActivity.this, DescContactoActivity.class);
                intent.putExtra(Constantes.CONTACTO_OBJ, contactoAux);
                ListaContactosActivity.this.startActivity(intent);
            }
        });
    }
}
