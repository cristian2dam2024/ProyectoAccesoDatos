package org.corella.AccesoDatos.aplications;

import org.corella.AccesoDatos.entidades.practica2.CuentaBancaria;
import org.corella.AccesoDatos.entidades.practica2.Persona;
import org.corella.AccesoDatos.utilsAcceso.Constantes;
import org.corella.AccesoDatos.utilsAcceso.Escritor;
import org.corella.AccesoDatos.utilsAcceso.Lector;

import java.io.*;
import java.util.ArrayList;

public class Practica2 {

    ArrayList<Persona> personas;
    ArrayList<CuentaBancaria> cuentasBancarias;

    public void run() throws IOException {
        leeFicherosEntrada();
        guardaObjetos();
        generainformes();
    }

    private void generainformes() {

        saldoPromedioClientes();
        infoMorosos();
        infoProvincias();

    }

    private void saldoPromedioClientes() {
    }

    private void infoMorosos() {
    }

    private void infoProvincias() {
    }

    private void guardaObjetos() throws IOException {

        RandomAccessFile escritorGeneral = new Escritor().escritorAleatorio(Constantes.practica2_salidaDatosGenerales);
        RandomAccessFile escritorFiscal = new Escritor().escritorAleatorio(Constantes.practica2_salidaDatosFiscales);

        for (Persona persona : personas) {
            //escritorGeneral.writeObject(persona); Sustituir por escritura en tipos basicos
        }

        for (CuentaBancaria cuenta : cuentasBancarias) {
            //escritorFiscal.writeObject(cuenta); Sustituir por escritura en tipos basicos
        }

        escritorGeneral.close();
        escritorFiscal.close();
    }

    public void leeFicherosEntrada() throws IOException {

        //Lee linea a linea el fichero csv y crea objetos de tipo persona.

        ArrayList<String []> datosPersonas = new Lector().getArrayDatos(Constantes.practica2_ficheroPersonas);
        ArrayList<String []> datosBancarios = new Lector().getArrayDatos(Constantes.practica2_datosBancarios);
        this.personas = new ArrayList<>();
        this.cuentasBancarias = new ArrayList<>();

        for (String [] datosPersona : datosPersonas) {
            personas.add(new Persona(datosPersona));
        }

        for (String [] datosBancario : datosBancarios) {
            cuentasBancarias.add(new CuentaBancaria(datosBancario));
        }
    }

    private void consultaCliente(){

    }

    private void consultaTramo(){

    }

}
