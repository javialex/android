package com.example.android.javialex_lab_07;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.javialex_lab_07.obj.Constantes;
import com.example.android.javialex_lab_07.obj.Contacto;

public class DescContactoActivity extends AppCompatActivity {
    Contacto contactoSeleccionado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc_contacto);
        Bundle data = getIntent().getExtras();
        if (data == null) return;
        contactoSeleccionado = (Contacto) getIntent().getSerializableExtra(Constantes.CONTACTO_OBJ);
    }
}
