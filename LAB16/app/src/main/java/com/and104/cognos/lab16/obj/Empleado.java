package com.and104.cognos.lab16.obj;

import java.io.Serializable;

/**
 * Created by aescobar on 13/4/2016.
 */
public class Empleado {
    private int id;
    private String nombre;
    private int areea;

    public Empleado(int id, String nombre, int areea) {
        this.id = id;
        this.nombre = nombre;
        this.areea = areea;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAreea() {
        return areea;
    }

    public void setAreea(int areea) {
        this.areea = areea;
    }
}
