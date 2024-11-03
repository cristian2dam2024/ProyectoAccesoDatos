package org.corella.AccesoDatos.aplications;

import javafx.util.Pair;

import org.corella.AccesoDatos.entidades.practica2.*;
import org.corella.AccesoDatos.utilsAcceso.*;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Practica2 {
    
    public void run() throws IOException{
    	
    	escribeFicheros();
    	
    }
	private void escribeFicheros() throws IOException {
    	
    	Configuracion configuracionGeneral = getObjetoConfig(new File (Constantes.practica2_ficheroConfiguracionGeneral));
    	Configuracion configuracionFiscal = getObjetoConfig(new File (Constantes.practica2_ficheroConfiguracionFiscal));
    		
    	escribeFichero(configuracionGeneral);
    	escribeFichero(configuracionFiscal);
	}
	
	private Configuracion getObjetoConfig(File ficheroJSON) {
		
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			Configuracion config = mapper.readValue(ficheroJSON, Configuracion.class);
			System.out.println("Se ha mapeado el objeto correctamente");
			return config;
			
		} catch (StreamReadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	private void escribeFichero(Configuracion config) throws IOException{

		Map<String, Integer> mapPosiciones = setPosicionesIniciales(config.getRutaFicheroPosiciones());
		List<Pair<String, Integer>> guiaLongitudes = setListaPares(config.getRutaLongitudRegistros());
		
		// necesitamos las lineas mapeadas que vamos a escribir en el fichero
		
		RandomAccessFile escritor = new RandomAccessFile(config.getRutaFicheroSalida(), "rws");		
		ArrayList<String[]> lineaDatos = new Lector().getArrayDatos(config.getRutaFicheroDatos(), ",");
		Map<String, String> registro = new HashMap();
		
		int longitudTotalRegistro = config.getTamanioTotalRegistros();
		String clave;
		int longitudCampo;
		
		for (String [] linea : lineaDatos) {
			
			for (int i = 0; i < linea.length; i++) {
				
				Pair<String, Integer> guiaRegistro = guiaLongitudes.get(i);
				clave = guiaRegistro.getKey();
				longitudCampo = guiaRegistro.getValue();
				
				registro.put(clave, linea[i]);
			}
			
			long pointerPosition = getPosicionPuntero(mapPosiciones, config, registro);
			mapPosiciones.replace(config.getClavePuntero(), (int) (pointerPosition+1));
			escritor.seek(pointerPosition*longitudTotalRegistro);
			
			for(Pair<String, Integer> guiaRegistro : guiaLongitudes){
	            clave = guiaRegistro.getKey();
	            longitudCampo = guiaRegistro.getValue();
	            String valorCampo = registro.get(clave);
	            if(valorCampo == null){
	                valorCampo = "";
	            }

	            String valorCampoForm = String.format("%1$-" + longitudCampo + "s", valorCampo +"s", valorCampo);
	            escritor.write(valorCampoForm.getBytes("UTF-8"), 0, longitudCampo);
	        }
			System.out.println("Escrito registro de tipo [" + config.getTipo()+ "] en la posición "+ pointerPosition +" del fichero binario.");
			System.out.println("Nueva posición del puntero con clave "+ config.getClavePuntero()+" : " + mapPosiciones.get(config.getClavePuntero()));
			registro.clear();
		}
		escritor.close();
	}

	private long getPosicionPuntero(Map<String, Integer> mapPosiciones, Configuracion config, Map<String, String> registro) {
		// TODO Auto-generated method stub
		
		String criterioOrden = config.getCriterioOrden();
		
		if(criterioOrden.equals("IRPF")) {
			
			int porcentajeIRPF = Integer.parseInt(registro.get(criterioOrden));
			
			if(porcentajeIRPF <= 10) {
				
				config.setClavePuntero("<=10");
			} else if (porcentajeIRPF <= 15) {
				config.setClavePuntero("<=15");
			} else {
				config.setClavePuntero("else");
			}
			return mapPosiciones.get(config.getClavePuntero());
		}
		
		String ciudad = registro.get(criterioOrden.toUpperCase());
		config.setClavePuntero(ciudad);
		
		return mapPosiciones.get(registro.get(criterioOrden.toUpperCase()));
	}

	private List<Pair<String, Integer>> setListaPares(String rutaLongitudRegistros) {
		// TODO Auto-generated method stub
		List<Pair<String, Integer>> listaPares= new ArrayList<>();
		try {
			ArrayList<String[]> datosEstructura = new Lector().getArrayDatos(rutaLongitudRegistros, ":");
			for (String [] estructura : datosEstructura) {
			    listaPares.add(new Pair<>(estructura[0], Integer.parseInt(estructura[1])));
			}
			
			return listaPares;
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
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

	private void consultaCliente(){

    }

    private void consultaTramo(){

    }

}
