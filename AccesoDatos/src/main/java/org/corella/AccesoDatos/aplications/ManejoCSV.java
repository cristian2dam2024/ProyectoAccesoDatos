package org.corella.AccesoDatos.aplications;

import org.corella.AccesoDatos.entidades.Alumno;
import org.corella.AccesoDatos.utilsAcceso.Escritor;
import org.corella.AccesoDatos.utilsAcceso.Lector;

import org.corella.AccesoDatos.utilsAcceso.Constantes;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class ManejoCSV {

    private void exportarAlumno(Alumno alumno) throws IOException {
        File rutaFichero = new File(Constantes.rutaSalidaFicheroCSV);
        BufferedWriter escritor = new Escritor().escritorLineas(rutaFichero);
        escritor.write(alumno.toStringCSV());
        escritor.close();
    }

    public void run(String rutaFichero) throws IOException {

        BufferedReader lectorLineas = new Lector().lectorLineas(new File(rutaFichero));
        ArrayList<Alumno> alumnos = importarAlumnos(lectorLineas);

        alumnos.sort(Comparator.comparingInt(Alumno::getEdad).thenComparing(Alumno::getNota));
        for (Alumno alumno : alumnos) {
            exportarAlumno(alumno);
        }
    }

    private static ArrayList<Alumno> importarAlumnos(BufferedReader lectorLineas) {

        String []campos;
        String linea;
        ArrayList<Alumno> alumnos = new ArrayList<>();;

        String patron;

        try {
            linea = lectorLineas.readLine();
            campos = linea.split(",");

            patron = "[a-zA-Z ]+";
            if(!linea.matches(patron)) { linea = lectorLineas.readLine(); }

            do{
                linea = linea.replaceAll(";$","");
                campos = (linea.split(","));
                alumnos.add(new Alumno(campos));

                linea = lectorLineas.readLine();
            }while(linea!=null);

            System.out.println("Todo ha ido bien");
            return alumnos;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
