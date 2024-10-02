package org.corella.AccesoDatos;

import org.corella.AccesoDatos.claseFile.FuncionesDirectorio;

public class Main {

    public static void main(String[] args) {
        FuncionesDirectorio fd = new FuncionesDirectorio();
        //fd.run("README.txt");
        //fd.run("pom.xml");
        //fd.run("target\\classes");
        fd.run("src");
    }
}

