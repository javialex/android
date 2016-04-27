package com.and104.cognos.lab18.obj;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import java.util.ArrayList;

/**
 * Created by aescobar on 15/4/2016.
 */
public class AccesoTelefono {
    private String idContacto;
    private Context context;

    public AccesoTelefono(String idContacto, Context context) {
        this.idContacto = idContacto;
        this.context = context;
    }

    public ArrayList<Telefono> getTelefonos(){
        Cursor telefonosCursor = this.context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + idContacto, null, null);
        ArrayList<Telefono> telefonos = new ArrayList<>();
        int incideTelefono;
        while(telefonosCursor.moveToNext()){
            Telefono tel = new Telefono();
            incideTelefono = telefonosCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            tel.setTelefono(telefonosCursor.getString(incideTelefono));
            telefonos.add(tel);
        }
        telefonosCursor.close();
        return  telefonos;
    }
}
