package bo.miteleferico.asistenciamovil.obj;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import java.util.ArrayList;

/**
 * Created by Administrador on 11/05/2016.
 */
public class AccesoEventosAsiMov {
    private Context context;

    public AccesoEventosAsiMov(Context context) {
        this.context = context;
    }
    public ArrayList<EventosAsiMov> getEventosAsiMov(){
        Cursor eventosAsiMovCursor = this.context.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,null,
                null, null, null);
        ArrayList<EventosAsiMov> eventosAsiMov = new ArrayList<>();
        int indiceId, indiceEventoMovil, indiceLatitud;
        while(eventosAsiMovCursor.moveToNext()){
            EventosAsiMov evento = new EventosAsiMov();
            //indiceId = eventosAsiMovCursor.getColumnIndex()
            /*indiceId = eventosAsiMovCursor.getColumnIndex(EventoAsiMovContract.Contacts._ID);
            indiceNOmbre = eventosAsiMovCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            indiceTelefonos  = eventosAsiMovCursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER);
            contacto.setId(eventosAsiMovCursor.getString(indiceId));
            contacto.setNombre(eventosAsiMovCursor.getString(indiceNOmbre));
            if(Integer.parseInt(eventosAsiMovCursor.getString(indiceTelefonos)) > 0){
                AccesoTelefono accesoTelefono = new AccesoTelefono(contacto.getId(), context);
                contacto.setTelefonos(accesoTelefono.getTelefonos());
            }*/
            eventosAsiMov.add(evento);
        }
        eventosAsiMovCursor.close();
        return eventosAsiMov;
    }
}
