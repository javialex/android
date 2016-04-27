package com.and104.cognos.lab16;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.and104.cognos.lab16.util.MiDBAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etxNombreEmpleado;
    private Button btnAgregar;
    private Button btnEliminarSistemas;
    private Spinner spnArea;
    private ListView ltvwEmpleados;
    private MiDBAdapter miDBAdapter;
    private String[] areas = {"Sistemas","RRHH","Comercializacion"};
    private ArrayList<String>  empleados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etxNombreEmpleado = (EditText) findViewById(R.id.etxNombreEmpleado);
        btnAgregar = (Button) findViewById(R.id.btnAgregar);
        btnEliminarSistemas = (Button) findViewById(R.id.btnEliminarSistemas);
        spnArea = (Spinner) findViewById(R.id.spnArea);
        ltvwEmpleados = (ListView) findViewById(R.id.ltvEmpleados);
        btnAgregar.setOnClickListener(this);
        btnEliminarSistemas.setOnClickListener(this);

        spnArea.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, areas));

        miDBAdapter = new MiDBAdapter(MainActivity.this);
        miDBAdapter.open();
        actualizarLista();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAgregar:
                String nombre = etxNombreEmpleado.getText().toString();
                int area = spnArea.getSelectedItemPosition() + 1;
                miDBAdapter.insertarEmpleado(nombre, area);
                actualizarLista();
                break;
            case R.id.btnEliminarSistemas:
                miDBAdapter.eliminarSistemas();
                actualizarLista();
                break;
        }
    }

    public void actualizarLista(){
        empleados = new ArrayList<>();
        empleados = miDBAdapter.seleccionarEmpleados();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, empleados);
        ltvwEmpleados.setAdapter(adapter);

    }
}
