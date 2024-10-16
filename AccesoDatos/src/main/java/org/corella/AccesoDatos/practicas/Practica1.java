package org.corella.AccesoDatos.practicas;

import org.corella.AccesoDatos.entidades.AlumnoPractica1;
import org.corella.AccesoDatos.utilsAcceso.Constantes;
import org.corella.AccesoDatos.utilsAcceso.Escritor;
import org.corella.AccesoDatos.utilsAcceso.Lector;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class Practica1 {

    private ArrayList<AlumnoPractica1> alumnos;

    public void run() throws IOException {
        creaAlumnos();
        exportaAlumnos();
    }

    public void creaAlumnos() throws IOException {

        this.alumnos = new ArrayList();
        BufferedReader lectorCSV = new Lector().lectorLineas(new File(Constantes.rutaFicheroNombresPractica1));
        String linea;
        String campos[];

        while ((linea = lectorCSV.readLine()) != null) {
            campos = linea.split(",");
            alumnos.add(new AlumnoPractica1(campos, generaAlturaAleatoria()));
        }
        alumnos.sort(Comparator.comparing(AlumnoPractica1::getNombre).thenComparing(AlumnoPractica1::getApellido));

        lectorCSV.close();
    }

    public void exportaAlumnos() throws IOException {

        BufferedWriter escritor = new Escritor().escritorLineas(new File(Constantes.rutaFicheroSalidaPractica1));
        for (AlumnoPractica1 alumno : alumnos) {
            if(alumno.getCalificacion() >= 5.0f){
                escritor.write(alumno.toString());
            }
        }
        escritor.close();
    }

    public float generaAlturaAleatoria(){
        Random random = new Random();
        float minimo = 1.10f;
        float maximo = 1.97f;

        float alturaAleatoria = minimo + random.nextFloat() * (maximo - minimo);
        alturaAleatoria = Math.round(alturaAleatoria *100) / 100f;

        return alturaAleatoria;
    }
}
