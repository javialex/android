package com.example.administrador.javialex_lab22;

import android.nfc.Tag;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Spinner spnArea;
    private ListView ltvwEmpleados;
    private EditText etxNombre;
    private EditText etxCod;
    private final static String TAG = "MainActivity - REST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spnArea = (Spinner) findViewById(R.id.spnAreas);
        ltvwEmpleados = (ListView) findViewById(R.id.ltvwEmpleados);
        etxCod = (EditText) findViewById(R.id.etxId);
        etxNombre = (EditText) findViewById(R.id.etxNombre);
        final ArrayList<Area> lstArea = new ArrayList<>();
        lstArea.add(new Area("Sistemas", 100));
        lstArea.add(new Area("RRHH", 101));
        //En cada iteración muestra al objeto y lo llama con el "toSring", por ello en las clases empleados y areas les sobreescribimos los métodos toString.
        //Otro método que se sobre escribe generalmente es el "equals"
        ArrayAdapter<Area> adapter = new ArrayAdapter<Area>(MainActivity.this, android.R.layout.simple_spinner_item, lstArea);
        spnArea.setAdapter(adapter);
        ltvwEmpleados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Empleado emp = null;
                try {
                    emp = (Empleado) ltvwEmpleados.getAdapter().getItem(position);
                    etxCod.setText(emp.getCodEmpleado() + "");
                    etxNombre.setText(emp.getNombre());
                    //Para setear el spinner
                    for (int i = 0; i < lstArea.size(); i++) {
                        if (lstArea.get(i).getCodArea() == emp.getArea().getCodArea()) {
                            spnArea.setSelection(i);
                            break;
                        }
                    }
                } catch (Exception ex) {

                }
            }
        });


        listar();

    }

    public void listar() {
        Thread th;
        th = new Thread(new Runnable() {
            @Override
            public void run() {
                JSONArray jsonArray = null;

                HttpURLConnection httpURLConnection = null;
                BufferedReader bufferedReader;
                StringBuilder stringBuilder;
                String linea, jsonString = null;

                try {
                    //URL url = new URL("http://10.0.2.2/AndroidTest/empleado");
                    URL url = new URL("http://192.168.4.56/AndroidTest/empleado");
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    bufferedReader = new BufferedReader(new InputStreamReader((httpURLConnection.getInputStream())));
                    stringBuilder = new StringBuilder();

                    while ((linea = bufferedReader.readLine()) != null) {
                        stringBuilder.append(linea + "\n");
                    }
                    jsonString = stringBuilder.toString();

                } catch (MalformedURLException e) {
                    Log.e(TAG, "Error en la URL" + e.getMessage());
                } catch (IOException e) {
                    Log.e(TAG, "La url no existe" + e.getMessage());
                }

                try {
                    jsonArray = new JSONArray(jsonString);
                } catch (JSONException e) {
                    Log.e(TAG, "Error conversion JSON" + e.getMessage());
                }

                final ArrayList<Empleado> lstEmpleados = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject itemEmpleado = null;
                    JSONObject itemArea = null;
                    Area area = null;
                    Empleado empleado = null;
                    try {
                        itemEmpleado = jsonArray.getJSONObject(i);
                        itemArea = itemEmpleado.getJSONObject("area");
                        area = new Area(itemArea.getString("nombreArea"), itemArea.getInt("codArea"));
                        empleado = new Empleado(itemEmpleado.getInt("codEmpleado"), itemEmpleado.getString("nombre"), area);
                        lstEmpleados.add(empleado);
                    } catch (JSONException e) {
                        Log.e(TAG, "Error conversion JSON" + e.getMessage());
                    }
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ArrayAdapter<Empleado> adapterEmp = new ArrayAdapter<Empleado>(MainActivity.this, android.R.layout.simple_list_item_1, lstEmpleados);
                        ltvwEmpleados.setAdapter(adapterEmp);
                    }
                });


            }
        });
        th.start();

    }

    public void listarrrr() {
        // Debido a que al obtener los datos de un servicio y este puede quedar en un "time out", es mejor manejar hilos.
        Thread th;
        th = new Thread(new Runnable() {
            @Override
            public void run() {
                JSONArray jsonArray = null;
                //HttpClient ya no se usa, esta deprecated
                HttpURLConnection httpURLConnection = null;
                BufferedReader bufferedReader = null;
                StringBuilder stringBuilder;
                String linea, jsonString = null;
                //En Android esta es la dirección de la ip local
                //URL url = new URL("http://10.0.2.2/AndroidTest/empleado");
                try {
                    //URL url = new URL("http://192.168.2/AndroidTest/empleado");
                    URL url = new URL("http://192.168.4.56/AndroidTest/empleado");
                    //URL url = new URL("http://10.0.2.2:8081/AndroidTest/empleado");
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    stringBuilder = new StringBuilder();
                    while ((linea = bufferedReader.readLine()) != null) {
                        stringBuilder.append(linea + "\n");
                    }
                    jsonString = stringBuilder.toString();

                } catch (MalformedURLException e) {
                    Log.e(TAG, "Error en la URL " + e.getMessage());
                } catch (IOException e) {
                    Log.e(TAG, "La URL no existe " + e.getMessage());
                }
                try {
                    jsonArray = new JSONArray(jsonString);
                } catch (JSONException e) {
                    Log.e(TAG, "Error en la conversion JSON " + e.getMessage());
                }
                final ArrayList<Empleado> lstEmpleados = new ArrayList<Empleado>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject itemEmpleado = null;
                    JSONObject itemArea = null;
                    Area area = null;
                    Empleado empleado = null;
                    try {
                        itemEmpleado = jsonArray.getJSONObject(i);
                        itemArea = itemEmpleado.getJSONObject("area");
                        area = new Area(itemArea.getString("nombreArea"), itemArea.getInt("codArea"));
                        empleado = new Empleado(itemEmpleado.getInt("codEmnpleado"), itemEmpleado.getString("nombreEmpleado"), area);
                        lstEmpleados.add(empleado);

                    } catch (JSONException e) {
                        Log.e(TAG, "Error en la conversion JSON dentro del objeto " + e.getMessage());
                    }
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ArrayAdapter<Empleado> adapterEmp = new ArrayAdapter<Empleado>(MainActivity.this, android.R.layout.simple_list_item_1, lstEmpleados);
                        ltvwEmpleados.setAdapter(adapterEmp);
                    }
                });
            }
        });
        th.start();
    }

    //
    public void insertar(View view) {
        String nombre = etxNombre.getText().toString();
        //Para acceder al Spinner, inicialmente debemos acceder a su adapter
        int codArea = ((Area) spnArea.getAdapter().getItem(spnArea.getSelectedItemPosition())).getCodArea();
        Area area = new Area("", codArea);
        Empleado emp = new Empleado(0, nombre, area);

        /*Otra forma:
        emp.setNombre(etxNombre.getText().toString());
        emp.setArea(new Area());
        ...
        */
        EjecutarInsert insert = new EjecutarInsert();
        insert.execute(emp);
    }

    private class EjecutarInsert extends AsyncTask<Empleado, Integer, Empleado> {
        @Override
        protected Empleado doInBackground(Empleado... params) {
            URL url = null;
            try {
                url = new URL("http://192.168.4.56/AndroidTest/empleado");
                //url = new URL("http://10.0.2.2:8081/AndroidTest/empleado");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("content-type", "application/json");
                conn.setDoInput(true);
                Empleado empleado = (Empleado) params[0];
                JSONObject jsonEmpleado = new JSONObject();
                JSONObject jsonArea = new JSONObject();
                jsonEmpleado.put("nombre", empleado.getNombre());
                jsonArea.put("codArea", empleado.getArea().getCodArea());
                jsonEmpleado.put("area", jsonArea);
                OutputStream os = conn.getOutputStream();
                //Acá le podemos pasar la codificación
                os.write(jsonEmpleado.toString().getBytes("UTF-8"));
                os.close();
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String linea, output = "";
                while ((linea = br.readLine()) != null) {
                    output += linea;
                }
                conn.disconnect();

                JSONObject itemEmpleado = null;
                try {
                    itemEmpleado = new JSONObject(output);
                    empleado.setCodEmpleado(itemEmpleado.getInt("codEmpleado"));
                } catch (JSONException e) {
                    Log.e(TAG, "Error en la conversion JSON " + e.getMessage());

                }
                return empleado;

            } catch (MalformedURLException e) {
                Log.e(TAG, "Error en la URL " + e.getMessage());
            } catch (IOException e) {
                Log.e(TAG, "Error en la URL " + e.getMessage());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Empleado empleado) {
            etxCod.setText(empleado.getCodEmpleado() + "");
            listar();
        }

        public void eliminar(View view) {

            final String cod = etxCod.getText().toString();
            Thread th;
            th = new Thread(new Runnable() {
                String resp = "";

                @Override
                public void run() {
                    HttpURLConnection httpURLConnection = null;
                    BufferedReader bufferedReader;
                    StringBuilder stringBuilder;
                    String linea, jsonString = null;
                    try {
                        URL url = new URL("http://192.168.4.56/AndroidTest/empleado" + cod);
                        //URL url = new URL("http://10.0.2.2:8081/AndroidTest/empleado");
                        httpURLConnection = (HttpURLConnection) url.openConnection();
                        httpURLConnection.setRequestMethod("DELETE");
                        bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                        stringBuilder = new StringBuilder();
                        while ((linea = bufferedReader.readLine()) != null) {
                            stringBuilder.append(linea);
                        }
                        resp = stringBuilder.toString();
                    } catch (Exception e) {

                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "El num de registro eliminados es: " + resp, Toast.LENGTH_LONG);
                        }
                    });
                }
            });
            th.start();
        }
    }
}
