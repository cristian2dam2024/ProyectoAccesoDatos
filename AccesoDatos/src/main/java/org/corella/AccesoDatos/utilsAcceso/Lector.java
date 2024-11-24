package org.corella.AccesoDatos.utilsAcceso;

import java.io.*;

public class Lector {
	
	public ObjectInputStream lectorObjetos(String ruta) throws IOException {
		return new ObjectInputStream(new FileInputStream(new File(ruta)));
	}

	public DataInputStream lectorDataStream (String ruta) throws FileNotFoundException {
		return new DataInputStream(new FileInputStream(new File(ruta)));
	}
	
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
