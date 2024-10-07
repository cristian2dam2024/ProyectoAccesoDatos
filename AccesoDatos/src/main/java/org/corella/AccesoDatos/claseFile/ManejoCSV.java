package org.corella.AccesoDatos.claseFile;


import org.corella.AccesoDatos.entidades.Alumno;
import org.corella.AccesoDatos.utilsAccesoFichero.Lector;

import java.io.*;
import java.util.ArrayList;

public class ManejoCSV {

    public void run(String rutaFichero) throws FileNotFoundException {

        BufferedReader lectorLineas = new Lector().lectorLineas(new File(rutaFichero));
        importarAlumnos(lectorLineas);
    }

    private static void importarAlumnos(BufferedReader lectorLineas) {

        try {
            String linea = lectorLineas.readLine();
            String []campos = linea.split(",");
            ArrayList<Alumno> alumnos = new ArrayList<>();

            try {
                Integer.parseInt(campos[3]);
            } catch (NumberFormatException e) {
                //throw new RuntimeException(e);
                System.out.println("Se omite la cabecera.");
                linea = lectorLineas.readLine();
            }

            while(linea != null){
               campos = linea.split(",");
               alumnos.add(new Alumno(campos));
               linea = lectorLineas.readLine();
           }
            System.out.println("Todo ha ido bien");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
