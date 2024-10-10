package org.corella.AccesoDatos.utilsAcceso;

import java.io.*;

public class Escritor {

    public BufferedWriter escritorLineas(File rutaFichero) throws IOException {
        return new BufferedWriter(new FileWriter(rutaFichero, true));
    }
}
