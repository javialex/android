package bo.miteleferico.asistenciamovil.obj;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by Administrador on 07/05/2016.
 */
public class EventosAsiMov {
    private int id;
    private String eventoMovil;
    private Double latitud;
    private Double longitud;
    private Double radio;
    private Date fechaIni;
    private Date fechaFin;
    private int cantMarcacionesDia;
    private String observacion;

    public EventosAsiMov() {
    }

    public EventosAsiMov(int id, String eventoMovil, Double latitud, Double longitud, Double radio, Date fechaIni, Date fechaFin, int cantMarcacionesDia, String observacion) {
        this.id = id;
        this.eventoMovil = eventoMovil;
        this.latitud = latitud;
        this.longitud = longitud;
        this.radio = radio;
        this.fechaIni = fechaIni;
        this.fechaFin = fechaFin;
        this.cantMarcacionesDia = cantMarcacionesDia;
        this.observacion = observacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventoMovil() {
        return eventoMovil;
    }

    public void setEventoMovil(String eventoMovil) {
        this.eventoMovil = eventoMovil;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Double getRadio() {
        return radio;
    }

    public void setRadio(Double radio) {
        this.radio = radio;
    }

    public Date getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(Date fechaIni) {
        this.fechaIni = fechaIni;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getCantMarcacionesDia() {
        return cantMarcacionesDia;
    }

    public void setCantMarcacionesDia(int cantMarcacionesDia) {
        this.cantMarcacionesDia = cantMarcacionesDia;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}
