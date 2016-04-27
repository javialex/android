package com.and104.cognos.lab18.obj;

import java.util.ArrayList;

/**
 * Created by aescobar on 15/4/2016.
 */
public class Contacto {
    private String id;
    private String nombre;
    private String email;
    private ArrayList<Telefono> telefonos;

    public Contacto() {
    }

    public Contacto(String id, String nombre, String email, ArrayList<Telefono> telefonos) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefonos = telefonos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Telefono> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(ArrayList<Telefono> telefonos) {
        this.telefonos = telefonos;
    }

    @Override
    public String toString() {
        String res = "Id: " + id + "\nNombre: " + nombre + "\ne-mail: " +
                (email != null ? email : "");
        res+="\nTelefonos: ";
        if(telefonos != null){
            for(Telefono tel : telefonos){
                res+= "\n" + tel.getTelefono();
            }
        }
        return res;
    }
}
