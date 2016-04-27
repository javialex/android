package com.example.administrador.javialex_lab13;

import android.app.WallpaperManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private GridView gridViewUno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridViewUno = (GridView) findViewById(R.id.gridViewUno);
        gridViewUno.setAdapter(new ImageAdapter(this));
        registerForContextMenu(gridViewUno);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Integer id = (Integer) gridViewUno.getItemAtPosition(item.getItemId());
        switch (item.getGroupId()) {
            case 1:
                final WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
                try {
                    wallpaperManager.setResource(id);
                    Toast.makeText(MainActivity.this,"El fondo fue modificado",Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Log.e("LABORATORIO_13",e.getMessage());
                }
                break;
            case 2:
                Intent i = new Intent(MainActivity.this,PreviewActivity.class);
                i.putExtra("ID_MAGEN",id);
                startActivity(i);
                break;
        }
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Opciones");
        AdapterView.AdapterContextMenuInfo cmi = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.add(1,cmi.position,0,"Establecer fondo");
        menu.add(2,cmi.position,0,"Vista Previa");
    }
}
