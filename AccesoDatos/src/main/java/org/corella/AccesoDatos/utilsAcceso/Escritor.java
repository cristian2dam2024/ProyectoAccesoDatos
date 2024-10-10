package org.corella.AccesoDatos.utilsAcceso;

import java.io.*;

public class Escritor {

    public DataOutputStream escritorTipos(String ruta) throws IOException {
        return new DataOutputStream(escritorBytes(ruta));
    }

    public FileOutputStream escritorBytes (String ruta) throws IOException {
        return new FileOutputStream(new File(ruta));
    }

    public BufferedWriter escritorLineas(File rutaFichero) throws IOException {
        return new BufferedWriter(new FileWriter(rutaFichero, true));
    }
}
