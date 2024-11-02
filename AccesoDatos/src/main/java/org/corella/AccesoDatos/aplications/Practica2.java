package org.corella.AccesoDatos.aplications;

import javafx.util.Pair;
import org.corella.AccesoDatos.entidades.practica2.CuentaBancaria;
import org.corella.AccesoDatos.entidades.practica2.Persona;
import org.corella.AccesoDatos.utilsAcceso.Constantes;
import org.corella.AccesoDatos.utilsAcceso.Escritor;
import org.corella.AccesoDatos.utilsAcceso.Lector;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Practica2 {

    private List<Pair<String, Integer>> camposGenerales;
    private List<Pair<String, Integer>> camposFiscales;
    private Map<String, String> registrosGenerales;
    private Map<String, String> registrosFiscales;

    private final int tamanioDatosGenerales = 205;
    private final int tamanioDatosFiscales = 55;

    private Map<String, Integer> posicionEscrituraDatosGenerales;
    private Map<String, Integer> posicionEscrituraDatosFiscales;

    
    public Practica2() throws IOException{
    	
    	setLongitudRegistros();
    	escribeFicheros();
    	
        
        this.posicionEscrituraDatosGenerales = new HashMap();
    	this.posicionEscrituraDatosFiscales = new HashMap();
    	
        
    	this.registrosGenerales= new HashMap();
    	this.registrosFiscales = new HashMap();
    	
    	
    }
    
    private void setLongitudRegistros() {
		// TODO Auto-generated method stub
    	
    	try {
			Lector lector = new Lector();
			ArrayList<String []> bytesGenerales = lector.getArrayDatos(Constantes.practica2_estructuraBytesDatosGenerales,":");
			ArrayList<String []> bytesFiscales = lector.getArrayDatos(Constantes.practica2_estructuraBytesDatosFiscales,":");
			setListaPares(this.camposGenerales, bytesGenerales);
			setListaPares(this.camposGenerales, bytesFiscales);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    private void setListaPares(List<Pair<String, Integer>> listaPares, ArrayList<String[]> datosBytes) {
		// TODO Auto-generated method stub
    	listaPares = new ArrayList();
    	for(String[] linea : datosBytes) {
    		listaPares.add(new Pair<>(linea[0], Integer.parseInt(linea[1])));
    	}
		
	}

	private void escribeFicheros() {
    	
    	File ficheroGeneral = new File(Constantes.practica2_salidaDatosGenerales);
    	File ficheroFinanzas = new File(Constantes.practica2_salidaDatosFiscales);
    	
    	escribeFichero(ficheroGeneral, Constantes.practica2_posicionesEscrituraGenerales, 205, Constantes.practica2_ficheroPersonas);
    	escribeFichero(ficheroFinanzas, Constantes.practica2_posicionesEscrituraFiscales, 55, Constantes.practica2_datosFiscales);
	}
	
	

	private void escribeFichero(File ficheroSalida, String ficheroPosiciones, int longitudRegistro, String ficheroDatos, String criterioOrden) {

		
		
		
		// escribe las lineas del fichero en forma de bytes con un RandomAccessFile
		
		String linea;
		
		// escribe en el fichero según las provincias y el porcentaje de irpf en base a los documentos del formato
		// por cada linea comprueba el dato pertinente y situa el puntero en la posición adecuada
		
		Map<String, Integer> posicion = setPosicionesIniciales(ficheroPosiciones);
		
		// necesitamos las lineas mapeadas que vamos a escribir en el fichero
		
		RandomAccessFile escritorAleatorio;
		ArrayList<String[]> lineas = new Lector().getArrayDatos(ficheroDatos, ",");
		
		for (String [] lineaDatos : lineas) {
			
			
			
			
			
			
		}
		
		
		//para posicionarnos necesitamos ver el valor que contiene el Integer del mapa posicion para la clave dada
		
		posicionaCursor(posicion, linea);
		
		
		escritorAleatorio.seek(posicion*longitudRegistro);
		
		//= new RandomAccessFile (file, "rws");
        

        for(Pair<String, Integer> campo : this.campos){
            String nombreCampo = campo.getKey();
            Integer longitudCampo = campo.getValue();
            String valorCampo = registro.get(nombreCampo);
            if(valorCampo == null){
                valorCampo = "";
            }

            String valorCampoForm = String.format("%1$-" + longitudCampo + "s", valorCampo +"s", valorCampo);
            escritorAleatorio.write(valorCampoForm.getBytes("UTF-8"), 0, longitudCampo);

        }
		
		
		
		
		
		RandomAccessFile escritor;
		
		
		 
		
		
		
		
		
		
		
		
		
	}

	private Map<String, Integer> setPosicionesIniciales(String ficheroPosiciones) {
		
		try {
			Map<String, Integer> posiciones;
			posiciones = new HashMap<String, Integer>();
			ArrayList<String []> listaPosiciones = new Lector().getArrayDatos(ficheroPosiciones, ":");
			
			for(String [] lineaPosiciones : listaPosiciones) {
				posiciones.put(lineaPosiciones[0], Integer.parseInt(lineaPosiciones[1]));
			}

			return posiciones;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
	}

	

	public void leeFicherosEntrada() throws IOException {

        Lector lector = new Lector();
        
        ArrayList<String []> datosGenerales = lector.getArrayDatos(Constantes.practica2_ficheroPersonas,",");
        ArrayList<String []> datosFiscales = lector.getArrayDatos(Constantes.practica2_datosFiscales,",");
        
        mapeaDatos(bytesGenerales, this.camposGenerales, datosGenerales, this.registrosGenerales, this.tamanioDatosGenerales, Constantes.practica2_salidaDatosGenerales);
        mapeaDatos(bytesFiscales, this.camposFiscales, datosFiscales, this.registrosFiscales, this.tamanioDatosFiscales, Constantes.practica2_salidaDatosFiscales);
        

    }
    
    private void mapeaDatos(ArrayList<String[]> datosEstructura, List<Pair<String, Integer>> listaPares, ArrayList<String[]> datosEntrada, Map<String, String> registro, int longitudRegistro, String rutaFicheroSalida) {
    	
        ArrayList<String> nombreCampos = new ArrayList();
        int posicion = 0;
        
        //Estructura lista de pares para datos con el nombre del campo y su tamaño en bytes
        for (String [] estructura : datosEstructura) {
            listaPares.add(new Pair<>(estructura[0], Integer.parseInt(estructura[1])));
            nombreCampos.add(estructura[0]);
        }
        
        //Agrega los datos generales al Map local para escribirlos a través de un RAF
        
        for(String[] lineaDatos : datosEntrada) {
        	for (int i = 0; i < lineaDatos.length; i++) {
        		registro.put(nombreCampos.get(i), lineaDatos[i]);
        		insertarRegistro(registro, longitudRegistro, rutaFicheroSalida);
        		registro.clear();
			}
        }
    }
    

    public void run() throws IOException {
    	
        leeFicherosEntrada();
        //guardaDatos();
        //generainformes();
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

    

    

    private void insertarRegistro(Map<String, String> registro, int longitudRegistro, String rutaFichero) {

    	try {
			RandomAccessFile escritor = new Escritor().escritorAleatorio(rutaFichero);
			
			
			
			
			
			
			escritor.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void consultaCliente(){

    }

    private void consultaTramo(){

    }

}
