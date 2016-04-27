package com.and104.cognos.lab18.obj;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import java.util.ArrayList;

/**
 * Created by aescobar on 15/4/2016.
 */
public class AccesoContacto {
    private Context context;

    public AccesoContacto(Context context) {
        this.context = context;
    }

    public ArrayList<Contacto> getContactos(){
        Cursor contactosCursor = this.context.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,null,
                null, null, null);
        ArrayList<Contacto> contactos = new ArrayList<>();
        int indiceId, indiceNOmbre, indiceTelefonos;
        while(contactosCursor.moveToNext()){
            Contacto contacto = new Contacto();
            indiceId = contactosCursor.getColumnIndex(ContactsContract.Contacts._ID);
            indiceNOmbre = contactosCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            indiceTelefonos  = contactosCursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER);
            contacto.setId(contactosCursor.getString(indiceId));
            contacto.setNombre(contactosCursor.getString(indiceNOmbre));
            if(Integer.parseInt(contactosCursor.getString(indiceTelefonos)) > 0){
                AccesoTelefono accesoTelefono = new AccesoTelefono(contacto.getId(), context);
                contacto.setTelefonos(accesoTelefono.getTelefonos());
            }
            contactos.add(contacto);
        }
        contactosCursor.close();
        return contactos;
    }
}
