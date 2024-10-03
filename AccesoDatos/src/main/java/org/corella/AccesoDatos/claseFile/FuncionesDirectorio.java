package org.corella.AccesoDatos.claseFile;
import java.io.File;
import java.io.IOException;

public class FuncionesDirectorio {

    public void run(String ruta) {
        File fichero = new File(ruta);

        if (!fichero.exists()) {
            System.out.println("No existe el fichero");
        } else {
            if (fichero.isFile()) {
                System.out.println("La ruta " + ruta + " es un fichero.");
            } else {
                //System.out.println("La ruta " + ruta + " es un directorio.");
                try {
                    listaDirectorio(fichero);
                } catch (IOException e) {
                    System.err.println("No se pudo abrir el fichero " + ruta);
                }
            }
        }
    }

    private void listaDirectorio(File directorio) throws IOException{
        for (File fichero : directorio.listFiles()) {
            if (fichero.isFile()) {
                System.out.println(fichero.getName() + " " + fichero.length());
            } else {
                System.out.print("\t");
                listaDirectorio(fichero);
            }
        }
    }

}