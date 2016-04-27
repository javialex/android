package com.and104.cognos.lab17;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.and104.cognos.lab17.util.MiContentProvider;

public class MainActivity extends AppCompatActivity {

    private EditText etxNombre;
    private EditText etxDato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etxDato = (EditText) findViewById(R.id.etxDato);
        etxNombre = (EditText) findViewById(R.id.etxNombre);
    }

    public void agregarEmpleado(View view) {
        String nombre = etxNombre.getText().toString();
        String dato = etxDato.getText().toString();
        if(!nombre.isEmpty() && !dato.isEmpty()){
            ContentValues cv = new ContentValues();
            cv.put(MiContentProvider.NOMBRE, nombre);
            cv.put(MiContentProvider.DATO, dato);
            Uri uri = getContentResolver().insert(MiContentProvider.CONTENT_URI, cv);
            Toast.makeText(MainActivity.this, "Registro insertado", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(MainActivity.this, "Se debe ingresar datos", Toast.LENGTH_LONG).show();
        }
    }

    public void mostrarEmpelados(View view){
        Uri empleados = MiContentProvider.CONTENT_URI;
        Cursor cursor = getContentResolver().query(empleados, null, null, null, "nombre");
        String resultado = "Resultado: ";
        if(!cursor.moveToFirst()){
            Toast.makeText(MainActivity.this, resultado + "No hay registros", Toast.LENGTH_LONG).show();
        }
        do{
            resultado += "\n ID:" +cursor.getInt(0) + " Nombre: " + cursor.getString(1) + " Dato: " + cursor.getString(2);
        }while (cursor.moveToNext());

        Toast.makeText(MainActivity.this, resultado, Toast.LENGTH_LONG).show();
    }

    public void update(int id, String nombre, String dato){
        ContentValues cv = new ContentValues();
        cv.put(MiContentProvider.NOMBRE, nombre);
        cv.put(MiContentProvider.DATO, dato);
        getContentResolver().update(Uri.parse(MiContentProvider.URL+"/"+id),cv, null, null);

    }

}
