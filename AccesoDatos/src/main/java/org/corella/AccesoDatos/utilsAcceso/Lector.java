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

    public ArrayList<String []> getArrayDatos(String rutaFichero, String separador) throws IOException {
    	
    	File fichero = new File(rutaFichero);
    	
    	if(fichero.exists()) {
    		System.out.println("Encontrado fichero en la ruta: " + rutaFichero);
    		ArrayList<String []> arrayCampos = new ArrayList<>();
            BufferedReader lector = new Lector().lectorLineas(fichero);

            String linea = "";
            
            while((linea = lector.readLine())!= null) {
            	arrayCampos.add(linea.split(separador));
            }
            lector.close();

            return arrayCampos;
    	}
    	
    	return null;
    }

}
