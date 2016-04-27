package com.and104.cognos.lab17_2;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String NOMBRE_PROVIDER = "com.and104.cognos.lab17";
    public static final String URL = "content://"+ NOMBRE_PROVIDER + "/empleados";
    public static final Uri CONTENT_URI = Uri.parse(URL);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void probar(View view){
        Uri empleados = CONTENT_URI;
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
}
