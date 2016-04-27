package com.and104.cognos.lab18;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.and104.cognos.lab18.obj.AccesoContacto;
import com.and104.cognos.lab18.obj.Contacto;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView ltvsContactos;
    private ArrayList<Contacto> lstContacos;
    private ArrayAdapter<Contacto> adapterContacto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ltvsContactos = (ListView) findViewById(R.id.ltvwContactos);
        lstContacos = new ArrayList<>();
        AccesoContacto acc = new AccesoContacto(MainActivity.this);
        lstContacos = acc.getContactos();
        /*adapterContacto = new ArrayAdapter<Contacto>(MainActivity.this,
                android.R.layout.simple_list_item_1, lstContacos);
        ltvsContactos.setAdapter(adapterContacto);*/
        ContactoAdapter contactoAdapter = new ContactoAdapter(MainActivity.this,lstContacos);
        ltvsContactos.setAdapter(contactoAdapter);
    }


}
