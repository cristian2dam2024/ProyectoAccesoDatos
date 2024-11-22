package org.corella.AccesoDatos;

import org.corella.AccesoDatos.aplications.*;
import org.corella.AccesoDatos.utilsAcceso.Constantes;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        //PruebaDirectorio();
        //PruebaReaderWriter();
        //PruebaCSV();
        //PruebaIOStream();

        //PruebaAccesoAleatorio();
        PruebaJSON();


    }

    private static void PruebaJSON() throws IOException {
        new ManejoJSON().run();
    }

}

