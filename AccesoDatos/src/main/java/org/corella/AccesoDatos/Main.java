package org.corella.AccesoDatos;

import org.corella.AccesoDatos.aplications.FileReaderWriter;
import org.corella.AccesoDatos.aplications.FuncionesDirectorio;
import org.corella.AccesoDatos.aplications.LeerEscribirObjetos;
import org.corella.AccesoDatos.aplications.ManejoCSV;
import org.corella.AccesoDatos.utilsAcceso.Constantes;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        //PruebaDirectorio();
        //PruebaReaderWriter();
        PruebaCSV();
        //PruebaIOStream();

    }

    private static void PruebaIOStream() throws IOException {
        LeerEscribirObjetos IOStreamTester = new LeerEscribirObjetos();
        IOStreamTester.run();
    }

    private static void PruebaCSV() throws IOException {
        try {
            ManejoCSV tester = new ManejoCSV();
            tester.run("src/main/resources/alumnos.csv");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void PruebaReaderWriter() {
        FileReaderWriter frw = new FileReaderWriter(Constantes.rutaFicheroSalidaContarVocales);
        frw.run(Constantes.rutaFicheroVocales);
    }


    private static void PruebaDirectorio() {
        FuncionesDirectorio fd = new FuncionesDirectorio();
        //fd.run("README.txt");
        //fd.run("pom.xml");
        //fd.run("target\\classes");
        fd.run("src");
    }
}

