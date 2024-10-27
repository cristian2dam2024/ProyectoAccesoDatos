package org.corella.AccesoDatos.aplications;

import javafx.util.Pair;
import org.corella.AccesoDatos.entidades.practica2.CuentaBancaria;
import org.corella.AccesoDatos.entidades.practica2.Persona;
import org.corella.AccesoDatos.utilsAcceso.Constantes;
import org.corella.AccesoDatos.utilsAcceso.Escritor;
import org.corella.AccesoDatos.utilsAcceso.Lector;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Practica2 {

    private List<Pair<String, Integer>> camposGenerales;
    private List<Pair<String, Integer>> camposFiscales;
    private Map<String, String> registrosGenerales;
    private Map<String, String> registrosFiscales;

    private final int tamanioDatosGenerales = 205;
    private final int tamanioDatosFiscales = 55;


    ArrayList<Persona> personas;
    ArrayList<CuentaBancaria> cuentasBancarias;

    public void run() throws IOException {
        leeFicherosEntrada();
        guardaDatos();
        generainformes();
    }

    private void generainformes() {

        saldoPromedioClientes();
        infoMorosos();
        infoProvincias();

    }

    private void saldoPromedioClientes() {
    }

    private void infoMorosos() {
    }

    private void infoProvincias() {
    }

    private void guardaDatos() throws IOException {

        RandomAccessFile escritorGeneral = new Escritor().escritorAleatorio(Constantes.practica2_salidaDatosGenerales);;
        RandomAccessFile escritorFiscal = new Escritor().escritorAleatorio(Constantes.practica2_salidaDatosFiscales);;













        escritorGeneral.close();
        escritorFiscal.close();
    }

    public void leeFicherosEntrada() throws IOException {

        Lector lector = new Lector();

        ArrayList<String []> bytesGenerales = lector.getArrayDatos(Constantes.practica2_estructuraBytesDatosGenerales,":");
        ArrayList<String []> bytesFiscales = lector.getArrayDatos(Constantes.practica2_estructuraBytesDatosFiscales,":");

        ArrayList<String []> datosGenerales = lector.getArrayDatos(Constantes.practica2_ficheroPersonas,",");
        ArrayList<String []> datosFiscales = lector.getArrayDatos(Constantes.practica2_datosFiscales,",");

        mapeaDatos(bytesGenerales, this.camposGenerales, datosGenerales, this.registrosGenerales);
        mapeaDatos(bytesFiscales, this.camposFiscales, datosFiscales, this.registrosFiscales);

    }

    private void mapeaDatos(ArrayList<String[]> datosEstructura, List<Pair<String, Integer>> listaPares, ArrayList<String[]> ficheroDatos, Map<String, String> registro) {

        ArrayList<String> nombreCampo = new ArrayList<>();
        int posicion = 0;

        //Estructura lista de pares para datos
        for (String [] estructura : datosEstructura) {
            listaPares.add(new Pair<>(estructura[0], Integer.parseInt(estructura[1])));
            nombreCampo.add(estructura[0]);
        }

        //Agrega los datos generales al Map local
        for (String [] datos : ficheroDatos) {
            registro.put(nombreCampo.get(posicion), datos[posicion]);
            posicion++;
        }

    }

    private void consultaCliente(){

    }

    private void consultaTramo(){

    }

}
