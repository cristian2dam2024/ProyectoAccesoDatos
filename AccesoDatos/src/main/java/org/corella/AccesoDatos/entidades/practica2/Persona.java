package org.corella.AccesoDatos.entidades.practica2;

public class Persona {

    private String dni;
    private int numSegSocial;
    private String nombre, apellido1, apellido2;

    private Direccion direccionSocial;
    private DirFiscal direccionFiscal;
    private CuentaBancaria cuentaBancaria;

    public Persona (String[] datosPersona, String[] datosBancarios){

        this.dni=datosPersona[0];
        this.numSegSocial=Integer.parseInt(datosPersona[1]);
        this.nombre=datosPersona[2];
        this.apellido1=datosPersona[3];
        this.apellido2=datosPersona[4];

        this.direccionSocial = new Direccion(datosPersona[5],datosPersona[6],datosPersona[7],datosPersona[8]);
        this.direccionFiscal = new DirFiscal(datosPersona[9],datosPersona[10],datosPersona[11],datosPersona[12]);
        // Estructura de datos en AccesoDatos/src/main/resources/practica2/datosEntrada/estructuraEntrada.txt

        this.cuentaBancaria = new CuentaBancaria(this.dni, datosBancarios);

    }




}
