package org.corella.AccesoDatos.utilsAcceso;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Lector {

    public FileReader lectorCaracteres(File rutaFichero) throws FileNotFoundException {
        return new FileReader(rutaFichero);
    }

    public BufferedReader lectorLineas(File rutaFichero) throws FileNotFoundException {
        return new BufferedReader(new FileReader(rutaFichero));
    }

}
