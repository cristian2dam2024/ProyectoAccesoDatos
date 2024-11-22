package org.corella.AccesoDatos;

import org.corella.AccesoDatos.claseFile.FileReaderWriter;
import org.corella.AccesoDatos.claseFile.FuncionesDirectorio;

public class Main {

    public static void main(String[] args) {

    	PruebaDirectorio();
//        new FileReaderWriter().run("src/main/resources/letras.txt");
    }

    private static void PruebaDirectorio() {
        FuncionesDirectorio fd = new FuncionesDirectorio();
        fd.run("README.txt");
        fd.run("pom.xml");
        fd.run("target/classes");
        fd.run("src");
    }
}

