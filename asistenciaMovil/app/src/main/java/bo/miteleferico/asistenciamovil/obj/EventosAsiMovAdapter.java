package bo.miteleferico.asistenciamovil.obj;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import bo.miteleferico.asistenciamovil.R;

/**
 * Created by Administrador on 11/05/2016.
 */
public class EventosAsiMovAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<EventosAsiMov> eventosAsiMovs;

    TextView txvEventoMovil;
    TextView txvLatitud;
    TextView txvLongitud;
    TextView txvRadio;
    TextView txvFechaIni;
    TextView txvFechaFin;
    TextView txvCantMarcacionesDia;
    TextView txvObservacion;

    public EventosAsiMovAdapter(Context context, ArrayList<EventosAsiMov> eventosAsiMovs) {

        super(context, R.layout.item_view_asi_mov, eventosAsiMovs);
        this.context = context;
        this.eventosAsiMovs = eventosAsiMovs;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View item = inflater.inflate(R.layout.item_view_asi_mov, null);
        txvEventoMovil = (TextView) item.findViewById(R.id.txvEventoMovil);
        txvLatitud = (TextView) item.findViewById(R.id.txvLatitud);
        txvLongitud = (TextView) item.findViewById(R.id.txvLongitud);
        txvRadio = (TextView) item.findViewById(R.id.txvRadio);
        txvFechaIni = (TextView) item.findViewById(R.id.txvFechaIni);
        txvFechaFin = (TextView) item.findViewById(R.id.txvFechaFin);
        txvCantMarcacionesDia = (TextView) item.findViewById(R.id.txvCantMarcacionesDia);
        txvObservacion = (TextView) item.findViewById(R.id.txvObservacion);
        return item;
    }
}
