package com.and104.cognos.lab18;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.and104.cognos.lab18.obj.Contacto;
import com.and104.cognos.lab18.obj.Telefono;

import java.util.ArrayList;

/**
 * Created by aescobar on 18/4/2016.
 */
public class ContactoAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<Contacto> contactos;

    public ContactoAdapter(Context context, ArrayList<Contacto> contactos) {

        super(context, R.layout.item_view,contactos);
        this.context = context;
        this.contactos = contactos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View item = inflater.inflate(R.layout.item_view, null);
        TextView etxNombre = (TextView) item.findViewById(R.id.txvNombre);
        ImageView imgContacto = (ImageView) item.findViewById(R.id.imgContacto);
        TextView etxTelefonos = (TextView) item.findViewById(R.id.txvTelefonos);
        etxNombre.setText(contactos.get(position).getNombre());
        String telefonosString = "";
        ArrayList<Telefono> lstTelefonos = contactos.get(position).getTelefonos();
        if(lstTelefonos!=null && lstTelefonos.size()>0){
            for (Telefono tel:contactos.get(position).getTelefonos()){
                telefonosString+=tel.getTelefono() + ", ";
            }
            //telefonosString+= ",";
            //telefonosString = telefonosString.replace(",,",".");
            etxTelefonos.setText(telefonosString);
            imgContacto.setImageResource(R.mipmap.ic_launcher);
        }
        return item;
    }
}
