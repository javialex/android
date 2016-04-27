package com.example.administrador.javialex_lab09;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private LoginActivity loginActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginActivity = (LoginActivity) findViewById(R.id.viewLoguin);
        loginActivity.setOnClickPersonalizado(new OnClickLoguin() {
            @Override
            public void validarDatos(String user, String pass) {
                Toast toast = Toast.makeText(getApplicationContext(), user, Toast.LENGTH_SHORT);
                if (user.equalsIgnoreCase("")||pass.equalsIgnoreCase("")) {
                    loginActivity.setMensaje("Debe introducir valores necesariamente");
                    toast.show();
                } else {
                    //loginActivity.setMensaje("Los datos introducidos son: " + user + " - " + pass);
                    Intent i = new Intent (MainActivity.this,FragmentActivity.class);
                    MainActivity.this.startActivity(i);
                }
            }
        });

    }

}
