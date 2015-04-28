package com.scbio.majex.cuidatualergia;

import java.util.ArrayList;

/**
 * Created by Alex on 23/04/2015.
 */
public class Geometria {

    private String densidad;
    private ArrayList<Double> latitud, longitud;

    public Geometria(){
        this.densidad = "Media";
        this.latitud = new ArrayList<Double>();
        this.longitud = new ArrayList<Double>();
    }

    public Geometria(String densidad, ArrayList<Double> latitud, ArrayList<Double> longitud) {
        this.densidad = densidad;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getDensidad() {
        return densidad;
    }

    public void setDensidad(String densidad) {
        this.densidad = densidad;
    }

    public ArrayList<Double> getLatitud() {
        return latitud;
    }

    public void setLatitud(ArrayList<Double> latitud) {
        this.latitud = latitud;
    }

    public ArrayList<Double> getLongitud() {
        return longitud;
    }

    public void setLongitud(ArrayList<Double> longitud) {
        this.longitud = longitud;
    }
}
