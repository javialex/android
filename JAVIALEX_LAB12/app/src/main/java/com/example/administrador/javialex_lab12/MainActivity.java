package com.example.administrador.javialex_lab12;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnUnoClick(View view) {
        //AlertDialog.Builder alertDialog = new AlertDialog.Builder(getApplicationContext());
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Alerta!!");
        alertDialog.setMessage("Se ejecutó un click");
        //alertDialog.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "ACEPTAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "ACEPTADO", Toast.LENGTH_SHORT).show();
            }
        });
        //alertDialog.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "CANCELAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "CANCELADO", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog.show();
    }

    public void btnDosClick(View view) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        final String[] listaOpciones = new String[9];
        //final String[] listaOpciones = {"LA PAZ", "ORURO", "POTOSI"};
        listaOpciones[0] = "LA PAZ";
        listaOpciones[1] = "COCHABAMBA";
        listaOpciones[2] = "SANTA CRUZ";
        listaOpciones[3] = "ORURO";
        listaOpciones[4] = "SUCRE";
        listaOpciones[5] = "BENI";
        listaOpciones[6] = "POTOSI";
        listaOpciones[7] = "TARIJA";
        listaOpciones[8] = "PANDO";
        alertDialog.setTitle("DEPARTAMENTOS").setItems(listaOpciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int pos) {
                Toast.makeText(MainActivity.this, "Seleccione el Departamento: " + listaOpciones[pos], Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog.create();
        alertDialog.show();
    }

    public void btnTresClick(View view) {
        Dialog dialog = new Dialog(this);
        dialog.setTitle("LOGIN");
        dialog.setContentView(R.layout.login_alert);
        final EditText etxUsuario = (EditText) dialog.findViewById(R.id.etxNombreLogin);
        EditText etxPassword = (EditText) dialog.findViewById(R.id.etxPasswordLogin);
        Button btnIngresar = (Button) dialog.findViewById(R.id.btnIngresar);
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textUsuario = etxUsuario.getText().toString();
                Intent i = new Intent(MainActivity.this, ContenidoActivity.class);
                i.putExtra("USUARIO", textUsuario);
                startActivity(i);
            }
        });
        dialog.show();
    }

    /*Solapar el metodo que crea menus*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        /*MenuInflater mf = getMenuInflater();
        mf.inflate(R.menu.main_menu,menu);*/
        MenuItem menuItemUno = menu.add(Menu.NONE, Menu.FIRST, Menu.NONE, "Ingresar..");
        MenuItem menuItemDos = menu.add(Menu.NONE, Menu.FIRST, Menu.NONE, "Salir..");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        /*
        Mejor Opcion-->
        switch (item.getItemId()){
            case R.id.menuPropiedades:
                Intent i = new Intent(MainActivity.this,ContenidoActivity.class);
                i.putExtra("USUARIO","MENU");
                startActivity(i);
                break;
            case R.id.menuSalir:
                finish();
                break;
        }*/
        /* Segunda opcion--->*/
        switch (item.getItemId()) {
            case Menu.FIRST:
                Intent i = new Intent(MainActivity.this,ContenidoActivity.class);
                i.putExtra("USUARIO","MENU");
                startActivity(i);
                break;
            case (Menu.FIRST + 1):
                finish();
                break;
        }
        return true;
    }
}
