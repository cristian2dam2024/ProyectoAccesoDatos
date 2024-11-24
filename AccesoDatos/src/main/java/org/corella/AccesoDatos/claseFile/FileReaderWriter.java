package org.corella.AccesoDatos.claseFile;

import java.io.*;
import java.util.HashMap;

/*
    1. Crear constructor de clase para path dinamico, para esta clase inicializa la variable private String pathEscritura
    asi la variable no depende de lo que pongamos en el codigo sino de lo que se introduzca al crear el objeto de la clase.

    2. Sobrecarga el metodo run
 */

public class FileReaderWriter {

    private String pathEscritura = "src/main/resources/salidaCuentaVocales.txt";
    
    public void run(String ficheroEntrada){
        cuentaVocales(ficheroEntrada);
    }

	private void cuentaVocales(String ruta) {
		try{
            File fichero = new File(ruta);

            if (!fichero.exists()) {
                throw new FileNotFoundException("La ruta no existe");

            } else if (!fichero.isFile()) {
                throw new FileNotFoundException("La ruta indica un directorio");
            } else {
                FileReader lector = new FileReader(fichero);
                
                HashMap<Character, Integer> contador = new HashMap<>();
                int indice;
                while( (indice = lector.read()) != -1){
                    char letraLeida = (char)indice;
                    contador.merge(letraLeida, 1, Integer::sum);
                }
                
                FileWriter escritor = new FileWriter(this.pathEscritura, true);
                contador.forEach((letra, numero) ->{
                	try {
                		String salida = ("Numero de "+letra+"es: " + numero);
                    	System.out.println(salida);
                    	
                    	for (int i = 0; i < salida.length(); i++) {
                            escritor.write(salida.charAt(i));
                        }
                        escritor.write("\n"); 
                        
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                });
                escritor.close();
                lector.close();
            }

        // se podría poner solo catch IOException porque engloba a FileNotFound
        } catch (FileNotFoundException e){
            //System.out.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
    
}

