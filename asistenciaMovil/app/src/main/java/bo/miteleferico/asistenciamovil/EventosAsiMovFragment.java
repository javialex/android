package bo.miteleferico.asistenciamovil;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import bo.miteleferico.asistenciamovil.obj.EventosAsiMov;


/**
 * A simple {@link Fragment} subclass.
 */
public class EventosAsiMovFragment extends Fragment {

    private ListView ltvsEventosAsiMov;
    private ArrayList<EventosAsiMov> lstEventosAsiMov;
    private ArrayAdapter<EventosAsiMov> adapterEventosAsiMov;

    public EventosAsiMovFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_eventos_asi_mov, container, false);

/*
        setContentView(R.layout.activity_main_asi_mov);
        ltvsEventosAsiMov = (ListView) findViewById(R.id.ltvwEventosAsiMov);
        lstEventosAsiMov = new ArrayList<>();
        AccesoContacto acc = new AccesoContacto(MainActivity.this);
        lstContacos = acc.getContactos();*/
        /*adapterContacto = new ArrayAdapter<Contacto>(MainActivity.this,
                android.R.layout.simple_list_item_1, lstContacos);
        ltvsContactos.setAdapter(adapterContacto);*/
        /*ContactoAdapter contactoAdapter = new ContactoAdapter(MainActivity.this, lstContacos);
        ltvsContactos.setAdapter(contactoAdapter);*/

    }

}
