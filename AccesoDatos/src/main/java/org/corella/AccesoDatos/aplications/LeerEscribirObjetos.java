package org.corella.AccesoDatos.aplications;
import org.corella.AccesoDatos.utilsAcceso.Constantes;

import org.corella.AccesoDatos.utilsAcceso.Escritor;
import org.corella.AccesoDatos.utilsAcceso.Lector;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class LeerEscribirObjetos {

    public void run() throws IOException {
        //escribirFlujo();
        //lectorFlujo();
        escribirTipo();

    }

    private void escribirTipo() throws IOException {
        DataOutputStream escritor = new Escritor().escritorTipos(Constantes.rutaSalidaFicheroTipos);

        escritor.writeBoolean(true);
        escritor.writeLong(100L);
        escritor.writeInt(99);

        escritor.close();

    }


    private void escribirFlujo() throws IOException{
        FileOutputStream escritor = new Escritor().escritorBytes(Constantes.rutaSalidaFicheroBytes);

        escritor.write(67);
        String cadena = "Hola clase";
        escritor.write(cadena.getBytes());
        escritor.close();
    }

    private void lectorFlujo() throws IOException {

        FileInputStream lector = new Lector().lectorBytes(Constantes.rutaSalidaFicheroBytes);

        byte[] bytesLeidos = lector.readAllBytes();
        String cadenaBytesLeidos = new String(bytesLeidos, StandardCharsets.UTF_8);

        System.out.println(
                "La cantidad de bytes almacenados es: " + bytesLeidos.length
                        +"\nSu posicion de memoria es:" + bytesLeidos.toString()
                        +"\nY su contenido es:" + Arrays.toString(bytesLeidos)
                        +"\ny su contenido en forma legible es: " + cadenaBytesLeidos);

        lector.close();

    }




}
