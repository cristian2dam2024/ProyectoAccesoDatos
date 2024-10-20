package org.corella.AccesoDatos.entidades.practica2;

public class Direccion {

    private String nombreCalle;
    private int numero;
    private String codigoPostal;
    private String provincia;

    public Direccion(String nombreCalle, int numero, String codigoPostal, String provincia) {
        this.nombreCalle = nombreCalle;
        this.numero = numero;
        this.codigoPostal = codigoPostal;
        this.provincia = provincia;
    }
}
