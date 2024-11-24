package org.corella.AccesoDatos;

import org.corella.AccesoDatos.aplications.FileReaderWriter;
import org.corella.AccesoDatos.aplications.FuncionesDirectorio;
import org.corella.AccesoDatos.aplications.LeerEscribirObjetos;
import org.corella.AccesoDatos.aplications.ManejoCSV;
import org.corella.AccesoDatos.utilsAcceso.Constantes;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

//    	new LeerEscribirObjetos().run(Constantes.rutaSalidaFicheroBytes);
    	new LeerEscribirObjetos().runType(Constantes.rutaSalidaFicheroTipos);
//    	new LeerEscribirObjetos().runObject(Constantes.rutaSalidaFicheroObjetos);

    }
}

