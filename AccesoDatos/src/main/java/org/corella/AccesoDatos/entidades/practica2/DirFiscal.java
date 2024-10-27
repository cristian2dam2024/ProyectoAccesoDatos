package org.corella.AccesoDatos.entidades.practica2;

import java.io.Serializable;

public class DirFiscal extends Direccion implements Serializable {

    public DirFiscal(String nombreCalle, String numero, String codigoPostal, String provincia) {
        super(nombreCalle, numero, codigoPostal, provincia);
    }


}
