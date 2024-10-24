package org.corella.AccesoDatos.entidades.practica2;

import java.io.Serializable;

public class CuentaBancaria implements Serializable {

    private String dni;
    private int numCuenta, porcentajeIrpf;
    private float balance;

    private float aportacionesONG, aportacionesFPFI; // fondoPensiones o fondoInversion
    private String tipoAhorro;

        public CuentaBancaria(String [] datos){
            this.dni = datos[0];

            this.numCuenta = Integer.parseInt(datos[1]);
            this.porcentajeIrpf = Integer.parseInt(datos[2]);
            this.balance = Float.parseFloat(datos[3]);

            this.aportacionesONG = Float.parseFloat(datos[4]);
            this.aportacionesFPFI = Float.parseFloat(datos[5]);

            this.tipoAhorro = datos[6];

        }

}
