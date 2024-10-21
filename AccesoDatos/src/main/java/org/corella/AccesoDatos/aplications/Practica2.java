package org.corella.AccesoDatos.aplications;

import org.corella.AccesoDatos.entidades.practica2.Persona;
import org.corella.AccesoDatos.utilsAcceso.Constantes;
import org.corella.AccesoDatos.utilsAcceso.Lector;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Practica2 {

    public void run() throws IOException {
        leeFicherosEntrada(Constantes.ficheroPersonas);
    }

    public void leeFicherosEntrada(String ficheroPersonas) throws IOException {

        //Lee linea a linea el fichero csv y crea objetos de tipo persona.

        ArrayList<String []> datosPersonas = new Lector().getArrayDatos(Constantes.ficheroPersonas);
        ArrayList<String []> datosBancarios = new Lector().getArrayDatos(Constantes.datosBancarios);
        ArrayList<Persona> personas = new ArrayList<>();
        int posicion = 0;

        for (String [] datosPersona : datosPersonas) {
            personas.add(new Persona(datosPersona, datosBancarios.get(posicion)));
            posicion++;
        }



    }




}
