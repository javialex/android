package com.example.administrador.javialex_lab24;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.soundcloud.android.crop.Crop;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ImageView imgvwFoto;
    String archivo;
    //Valor que se usará como santo y seña
    int REQUEST_CODE = 0;
    final String TAG = "CAMARA";
    EditText etxNombre;
    Bitmap imagenFinal = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgvwFoto = (ImageView) findViewById(R.id.imgvwFoto);
        etxNombre = (EditText) findViewById(R.id.etxNombre);

    }

    public void tomarFotoClick(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        archivo = Environment.getExternalStorageDirectory() + "/" + System.currentTimeMillis();
        File file = new File(archivo + ".jpg");
        Uri outputUri = Uri.fromFile(file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            File fileOrg = new File(archivo + ".jpg");
            Uri origen = Uri.fromFile(fileOrg);
            File file = new File(archivo + "rec.jpg");
            Uri destino = Uri.fromFile(file);
            Crop.of(origen, destino).asSquare().start(this);
        } else {
            if (resultCode != RESULT_CANCELED) {
                //cargarImagen();
                ComprimirImagen comprimirImagen = new ComprimirImagen();
                comprimirImagen.execute(archivo + "rec.jpg");
            }
        }
    }

    private void cargarImagen() {
        FileInputStream fis;
        Bitmap bmp = null;
        try {
            fis = new FileInputStream(archivo + "rec.jpg");
            bmp = BitmapFactory.decodeStream(fis);
            fis.close();
        } catch (FileNotFoundException e) {
            Log.e(TAG, "El archivo no existe " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "Error al cargar el archivo " + e.getMessage());
        }
        if (bmp != null) {
            bmp = Bitmap.createScaledBitmap(bmp, 100, 100, true);
            imgvwFoto.setImageBitmap(bmp);
        }
    }

    private String archivoImageString(String dir) {
        Bitmap bm = BitmapFactory.decodeFile(dir);
        ByteArrayOutputStream imagen = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, imagen);
        byte[] bytesImages = imagen.toByteArray();
        String cadenaImagen = Base64.encodeToString(bytesImages, Base64.DEFAULT);
        return cadenaImagen;
    }

    private String archivoImageAString(Bitmap bm) {
        //Bitmap bm = BitmapFactory.decodeFile(dir);
        ByteArrayOutputStream imagen = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 10, imagen);
        byte[] bytesImages = imagen.toByteArray();
        String cadenaImagen = Base64.encodeToString(bytesImages, Base64.DEFAULT);
        return cadenaImagen;
    }

    public void enviarPersonaClick(View view) {
        EnviarPersona enviarPersona = new EnviarPersona();
        String nombre = etxNombre.getText().toString();
        //String imagen = archivoImageString(archivo+"rec.jpg");
        String imagen = archivoImageAString(imagenFinal);
        enviarPersona.execute(nombre, imagen);
    }

    private class EnviarPersona extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... params) {
            URL url = null;
            try {
                url = new URL("http://192.168.4.56/AndroidTest/persona");
                //url = new URL("http://10.0.2.2:8081/AndroidTest/persona");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("content-type", "aplication/json");
                JSONObject jsonPersona = new JSONObject();
                jsonPersona.put("nombre", params[0]);
                jsonPersona.put("img", params[1]);
                OutputStream os = conn.getOutputStream();
                os.write(jsonPersona.toString().getBytes("UTF-8"));
                os.close();
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String linea, output = "";
                while ((linea = br.readLine()) != null) {
                    output += linea;
                }
                conn.disconnect();
                return output;
            } catch (MalformedURLException e) {
                Log.e(TAG, "Error URL" + e);
            } catch (IOException e) {
                Log.e(TAG, "No se puede leer la url" + e);
            } catch (JSONException e) {
                Log.e(TAG, "No es serializable" + e);
            }
            return null;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s != null) {
                Toast.makeText(MainActivity.this, "El id del registro es " + s, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(MainActivity.this, "Error al enviar persona", Toast.LENGTH_LONG).show();
            }

        }
    }

    private class ComprimirImagen extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bmp = null;
            byte[] imagenBytes = ImageUtils.compressImage(params[0]);
            if (imagenBytes != null) {
                bmp = BitmapFactory.decodeStream(new ByteArrayInputStream(imagenBytes));
            }
            return bmp;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imgvwFoto.setImageBitmap(bitmap);
            imagenFinal = bitmap;
        }
    }
}
