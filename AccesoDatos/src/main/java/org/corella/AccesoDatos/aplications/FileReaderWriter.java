package org.corella.AccesoDatos.aplications;

import java.io.*;

/*
    1. Crear constructor de clase para path dinamico, para esta clase inicializa la variable private String pathEscritura
    asi la variable no depende de lo que pongamos en el codigo sino de lo que se introduzca al crear el objeto de la clase.

    2. Sobrecarga el metodo run
 */

public class FileReaderWriter {

    private String pathEscritura;

    public FileReaderWriter(String pathEscritura) {
        this.pathEscritura = pathEscritura;
    }

    private void escribirContadores(String rutaEscritura, String contenido) throws IOException {

        FileWriter escritor = new FileWriter(rutaEscritura, true);

        for (int i = 0; i < contenido.length(); i++) {
            escritor.write(contenido.charAt(i));
        }
        escritor.write("\n");
        escritor.close();
    }

    public void run(String ruta){

        try{
            File fichero = new File(ruta);

            if (!fichero.exists()) {
                throw new FileNotFoundException("La ruta no existe");

            } else if (!fichero.isFile()) {
                throw new FileNotFoundException("La ruta indica un directorio");
            } else {
                FileReader lector = new FileReader(fichero);
                int [] contadorVocales = new int[5];
                int indice;

                while( (indice = lector.read()) != -1){
                    char letraLeida = (char)indice;

                    switch(letraLeida){

                        case 'a':
                            contadorVocales[0]++;
                            break;
                        case 'e':
                            contadorVocales[1]++;
                            break;
                        case 'i':
                            contadorVocales[2]++;
                            break;
                        case 'o':
                            contadorVocales[3]++;
                            break;
                        case 'u':
                            contadorVocales[4]++;
                            break;

                        case 'A':
                            contadorVocales[0]++;
                            break;
                        case 'E':
                            contadorVocales[1]++;
                            break;
                        case 'I':
                            contadorVocales[2]++;
                            break;
                        case 'O':
                            contadorVocales[3]++;
                            break;
                        case 'U':
                            contadorVocales[4]++;
                            break;

                            default:
                                break;
                    }
                }

                System.out.println("Numero de Aes: " + contadorVocales[0]);
                System.out.println("Numero de Es: " + contadorVocales[1]);
                System.out.println("Numero de Ies: " + contadorVocales[2]);
                System.out.println("Numero de Oes: " + contadorVocales[3]);
                System.out.println("Numero de Ues: " + contadorVocales[4]);

                File f = new File(pathEscritura);
                escribirContadores(pathEscritura, "Numero de Aes: " + contadorVocales[0]);
                escribirContadores(pathEscritura, "Numero de Es: " + contadorVocales[1]);
                escribirContadores(pathEscritura, "Numero de Ies: " + contadorVocales[2]);
                escribirContadores(pathEscritura, "Numero de Oes: " + contadorVocales[3]);
                escribirContadores(pathEscritura, "Numero de Ues: " + contadorVocales[4]);


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

