package com.example.administrador.javialex_lab24;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    ImageView imgvwFoto;
    String archivo;
    //Valor que se usará como santo y seña
    int REQUEST_CODE = 0;
    final String TAG = "CAMARA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgvwFoto = (ImageView) findViewById(R.id.imgvwFoto);

    }

    public void tomarFotoClick(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        archivo = Environment.getExternalStorageDirectory() + "/" + System.currentTimeMillis() + ".jpg";
        File file = new File(archivo);
        Uri outputUri = Uri.fromFile(file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode != RESULT_CANCELED) {
                cargarImagen();
            }
        }
    }

    private void cargarImagen() {
        FileInputStream fis;
        Bitmap bmp = null;
        try {
            fis = new FileInputStream(archivo);
            bmp = BitmapFactory.decodeStream(fis);
            fis.close();
        } catch (FileNotFoundException e) {
            Log.e(TAG, "El archivo no existe " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "Error al cargar el archivo " + e.getMessage());
        }
        if (bmp != null) {
            bmp = Bitmap.createScaledBitmap(bmp,100,100,true);
            imgvwFoto.setImageBitmap(bmp);
        }
    }
}
