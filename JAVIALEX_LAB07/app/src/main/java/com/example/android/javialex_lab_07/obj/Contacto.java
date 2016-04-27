package com.example.android.javialex_lab_07.obj;

import java.io.Serializable;

/**
 * Created by ANDROID on 31/3/2016.
 */
public class Contacto implements Serializable{
    private String nombre;
    private String telefeno;
    private String web;

    public Contacto(String nombre, String telefeno, String web) {
        this.nombre = nombre;
        this.telefeno = telefeno;
        this.web = web;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefeno() {
        return telefeno;
    }

    public String getWeb() {
        return web;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefeno(String telefeno) {
        this.telefeno = telefeno;
    }

    public void setWeb(String web) {
        this.web = web;
    }
}
