package org.corella.AccesoDatos.entidades.practica2;

import java.io.Serializable;

public class Persona implements Serializable {

    private String dni;
    private int numSegSocial;
    private String nombre, apellido1, apellido2;

    private Direccion direccionSocial;
    private DirFiscal direccionFiscal;

    public Persona (String[] datosPersona){

        this.dni=datosPersona[0];
        this.numSegSocial=Integer.parseInt(datosPersona[1]);
        this.nombre=datosPersona[2];
        this.apellido1=datosPersona[3];
        this.apellido2=datosPersona[4];

        this.direccionSocial = new Direccion(datosPersona[5],datosPersona[6],datosPersona[7],datosPersona[8]);
        this.direccionFiscal = new DirFiscal(datosPersona[9],datosPersona[10],datosPersona[11],datosPersona[12]);
        // Estructura de datos en AccesoDatos/src/main/resources/practica2/datosEntrada/estructuraDatosGenerales.txt
    }




}
