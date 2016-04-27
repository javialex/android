package com.example.android.javialex_lab06;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.jar.Manifest;

/*Implementamos el onclick para usarlo dentro de un switch*/
public class PrimerActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnLlamarActivity;
    private Button btnLlamarExplorador;
    private Button btnLlamarGeo;
    private Button btnLlamarCelular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primer);
        btnLlamarActivity = (Button) findViewById(R.id.btnLlamarActivity);
        btnLlamarExplorador = (Button) findViewById(R.id.btnLlamarExplorador);
        btnLlamarGeo = (Button) findViewById(R.id.btnLlamarGeo);
        btnLlamarCelular = (Button) findViewById(R.id.btnLlamarCelular);
        /*Usamos esa implementación*/
        btnLlamarActivity.setOnClickListener(this);
        btnLlamarExplorador.setOnClickListener(this);
        btnLlamarGeo.setOnClickListener(this);
        btnLlamarCelular.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btnLlamarActivity:
                /*Le enviamos el contexto*/
                intent = new Intent(this, SegundaActivity.class);
                intent.putExtra(intent.EXTRA_TEXT, "Yo no me llamo Freddy");
                this.startActivity(intent);
                break;
            case R.id.btnLlamarExplorador:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.miteleferico.bo"));
                this.startActivity(intent);
                break;
            case R.id.btnLlamarGeo:
                /*Otra forma de agregarle el Uri*/
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:-18.212,-19.121"));
                this.startActivity(intent);
                break;
            case R.id.btnLlamarCelular:
                intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:70617136"));
                /*Nos pedirá permisos*/
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    Toast toast = Toast.makeText(this,"NO SE CUENTA CON LOS PERMISOS",Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
                this.startActivity(intent);
                break;
        }
    }
}
