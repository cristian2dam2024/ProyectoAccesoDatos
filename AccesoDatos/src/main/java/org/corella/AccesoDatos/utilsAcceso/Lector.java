package org.corella.AccesoDatos.utilsAcceso;

import java.io.*;

public class Lector {

    public FileInputStream lectorBytes (String ruta) throws FileNotFoundException {
        return new FileInputStream(new File(ruta));
    }

    public FileReader lectorCaracteres(File rutaFichero) throws FileNotFoundException {
        return new FileReader(rutaFichero);
    }

    public BufferedReader lectorLineas(File rutaFichero) throws FileNotFoundException {
        return new BufferedReader(new FileReader(rutaFichero));
    }

}
