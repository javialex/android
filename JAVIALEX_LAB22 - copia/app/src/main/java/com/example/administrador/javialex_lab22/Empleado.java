package com.example.administrador.javialex_lab22;

/**
 * Created by Administrador on 25/04/2016.
 */
public class Empleado {
    private int codEmpleado;
    private String nombre;
    private Area area;

    public Empleado(int codEmpleado, String nombre, Area area) {
        this.codEmpleado = codEmpleado;
        this.nombre = nombre;
        this.area = area;
    }

    public Empleado() {

    }

    public int getCodEmpleado() {
        return codEmpleado;
    }

    public void setCodEmpleado(int codEmpleado) {
        this.codEmpleado = codEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return nombre + " - " + area.getNombreArea();
    }

}
