package org.corella.AccesoDatos;

import org.corella.AccesoDatos.claseFile.FileReaderWriter;
import org.corella.AccesoDatos.claseFile.FuncionesDirectorio;

import current.ManejoCSV;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        //PruebaDirectorio();
        //PruebaReaderWriter();
        PruebaCSV();
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
        FileReaderWriter frw = new FileReaderWriter("src/test/resources/PruebaWriter.txt");
        frw.run("src/test/resources/PruebaReader.txt");
    }


    private static void PruebaDirectorio() {
        FuncionesDirectorio fd = new FuncionesDirectorio();
        //fd.run("README.txt");
        //fd.run("pom.xml");
        //fd.run("target\\classes");
        fd.run("src");
    }
}

