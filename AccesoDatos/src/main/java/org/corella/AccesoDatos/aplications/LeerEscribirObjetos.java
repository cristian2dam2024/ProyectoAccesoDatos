package org.corella.AccesoDatos.aplications;
import org.corella.AccesoDatos.entidades.Alumno;
import org.corella.AccesoDatos.utilsAcceso.Constantes;

import org.corella.AccesoDatos.utilsAcceso.Escritor;
import org.corella.AccesoDatos.utilsAcceso.Lector;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class LeerEscribirObjetos {

	public void run(String fileRoute) throws IOException {
		escribirFlujo(fileRoute);
        lectorFlujo(fileRoute);
    }

    public void runType(String fileRoute) throws IOException {
    	escribirTipo(fileRoute);
        leerTipo(fileRoute);
    }

    public void runObject(String fileRoute) throws IOException, ClassNotFoundException {
        escribeObjeto(fileRoute, new Alumno("Alejandro", "2DAM", 10,  10));
        leeObjeto(fileRoute);
    }
    
    private void escribirFlujo(String fileRoute) throws IOException{
        FileOutputStream escritor = new Escritor().escritorBytes(fileRoute);
        escritor.write(67);
        String cadena = "Hola clase";
        escritor.write(cadena.getBytes());
        escritor.close();
    }
    private void lectorFlujo(String fileRoute) throws IOException {

        FileInputStream lector = new Lector().lectorBytes(fileRoute);
        byte[] bytesLeidos = lector.readAllBytes();
        String cadenaBytesLeidos = new String(bytesLeidos, StandardCharsets.UTF_8);

        System.out.println(
                "La cantidad de bytes almacenados es: " + bytesLeidos.length
                        +"\nSu posicion de memoria es:" + bytesLeidos.toString()
                        +"\nY su contenido es:" + Arrays.toString(bytesLeidos)
                        +"\ny su contenido en forma legible es: " + cadenaBytesLeidos);
        lector.close();
    }
    
    private void escribirTipo(String fileRoute) throws IOException {
        DataOutputStream escritor = new Escritor().escritorTipos(fileRoute);
        escritor.writeBoolean(true);
        escritor.writeLong(100L);
        escritor.writeInt(99);
        escritor.close();
    }
    
    private void leerTipo(String fileRoute) throws IOException {
        DataInputStream reader = new Lector().lectorDataStream(fileRoute);
        System.out.println(reader.readBoolean());
        System.out.println(reader.readLong());
        System.out.println(reader.readInt());
        reader.close();
    }

    private void escribeObjeto(String fileRoute, Alumno alumno) throws IOException {
        ObjectOutputStream outputStream = new Escritor().escritorObjetos(fileRoute);
        outputStream.writeObject(alumno);
        outputStream.close();
    }
    private void leeObjeto(String fileRoute) throws IOException, ClassNotFoundException {
        ObjectInputStream reader = new Lector().lectorObjetos(fileRoute);
        Alumno readAlumn = (Alumno) reader.readObject();
        System.out.println(readAlumn.getNota());
        reader.close();
    }

}
