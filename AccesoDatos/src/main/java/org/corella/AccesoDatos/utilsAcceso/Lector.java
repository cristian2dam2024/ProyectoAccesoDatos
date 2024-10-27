package org.corella.AccesoDatos.utilsAcceso;

import java.io.*;
import java.util.ArrayList;

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

    public ArrayList<String []> getArrayDatos(String ficheroDatos, String separador) throws IOException {
        ArrayList<String []> arrayCampos = new ArrayList<>();
        BufferedReader lector = new Lector().lectorLineas(new File(ficheroDatos));

        String linea;
        do{
            linea = lector.readLine();
            arrayCampos.add(linea.split(separador));
        } while(linea != null);
        lector.close();

        return arrayCampos;
    }

}
