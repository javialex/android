package com.example.administrador.javialex_lab22;

/**
 * Created by Administrador on 25/04/2016.
 */
public class Area {
    private String nombreArea;
    private int codArea;

    public Area(String nombreArea, int codArea) {
        this.nombreArea = nombreArea;
        this.codArea = codArea;
    }

    public String getNombreArea() {
        return nombreArea;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }

    public int getCodArea() {
        return codArea;
    }

    public void setCodArea(int codArea) {
        this.codArea = codArea;
    }

    @Override
    public String toString() {
        return nombreArea;
    }
}
