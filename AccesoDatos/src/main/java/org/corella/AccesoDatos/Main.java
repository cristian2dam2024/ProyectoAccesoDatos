package org.corella.AccesoDatos;

import org.corella.AccesoDatos.claseFile.FileReaderWriter;
import org.corella.AccesoDatos.claseFile.FuncionesDirectorio;

public class Main {

    public static void main(String[] args) {
        //PruebaDirectorio();
        PruebaReaderWriter();
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

