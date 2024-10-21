package org.corella.AccesoDatos.entidades.practica2;

public class Direccion {

    private String nombreCalle;
    private int numero;
    private int codigoPostal;
    private String provincia;

    public Direccion(String nombreCalle, String numero, String codigoPostal, String provincia) {
        this.nombreCalle = nombreCalle;
        this.numero = Integer.parseInt(codigoPostal);
        this.codigoPostal =Integer.parseInt(numero);
        this.provincia = provincia;
    }
}
