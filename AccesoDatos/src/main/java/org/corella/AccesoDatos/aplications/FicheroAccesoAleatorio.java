package org.corella.AccesoDatos.aplications;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.corella.AccesoDatos.utilsAcceso.Constantes;

import javafx.util.Pair;

public class FicheroAccesoAleatorio {

	private long tamanyoRegistros, numeroRegistros;
	private File ficheroAleatorio = new File(Constantes.rutaficheroAleatorio);
	private List<Pair<String, Integer>> estructuraDatos;

	public void run(String rutaFichero) throws IOException {
		this.ficheroAleatorio = new File(rutaFichero);
		escribeFicheroAleatorio();
		leeFicheroAleatorio();
	}

	private void escribeFicheroAleatorio() {
		this.estructuraDatos = new ArrayList<>();
		estructuraDatos.add(new Pair<>("DNI", 9));
		estructuraDatos.add(new Pair<>("NOMBRE", 32));
		estructuraDatos.add(new Pair<>("NACIONALIDAD", 20));
		this.tamanyoRegistros = 9+32+20;
		try {
			insertaRegistros();

		} catch (IOException e) {
			System.err.println("ERROR DE E/S: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void insertaRegistros() throws IOException {
		Map<String, String> registros = new HashMap<>();
		registros.put("DNI", "76546473H");
		registros.put("NOMBRE", "JUAN CARLOS");
		registros.put("NACIONALIDAD", "ESPAÑA");
		insertar(registros, 0);
		registros.clear();

		registros.put("DNI", "76000073H");
		registros.put("NOMBRE", "MARIA TERESA");
		registros.put("NACIONALIDAD", "FRANCIA");
		insertar(registros, 1);
		registros.clear();

		registros.put("DNI", "76000073H");
		registros.put("NOMBRE", "MARTIN");
		registros.put("NACIONALIDAD", "ALEMANIA");
		insertar(registros,2);
		
	}

	public void insertar(Map<String, String> registro) throws IOException {
		insertar(registro, this.numeroRegistros++);
	}

	public void insertar(Map<String, String> registros, long posicion) throws IOException {
		RandomAccessFile escritorAleatorio = new RandomAccessFile(ficheroAleatorio, "rws");
		escritorAleatorio.seek(posicion * this.tamanyoRegistros);

		for (Pair<String, Integer> campo : this.estructuraDatos) {
			String nombreCampo = campo.getKey();
			Integer longitudCampo = campo.getValue();
			String valorCampo = registros.get(nombreCampo);
			if (valorCampo == null) {
				valorCampo = "";
			}

			String valorCampoForm = String.format("%1$-" + longitudCampo + "s", valorCampo);
			escritorAleatorio.write(valorCampoForm.getBytes("UTF-8"), 0, longitudCampo);
		}
		this.numeroRegistros++;
		escritorAleatorio.close();
	}

	public void leeFicheroAleatorio() throws IOException {

		RandomAccessFile lectorAleatorio = new RandomAccessFile(ficheroAleatorio, "r");
		if (ficheroAleatorio.exists()) {
			try {
				this.numeroRegistros = ficheroAleatorio.length() / this.tamanyoRegistros;
				System.out.println("Numero de Registros: " + this.numeroRegistros);
			} catch (Exception e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
			}
		}
		
		long puntero = 0;
		try {
			for (int i = 0; i < this.numeroRegistros; i++) {
				
				System.out.println("Registro "+(i+1)+":");
				
				for (Pair<String, Integer> campo : this.estructuraDatos) {
					lectorAleatorio.seek(puntero);
					byte[] buffer = new byte[campo.getValue()];
					lectorAleatorio.readFully(buffer);
					String campoLeido = new String(buffer).trim();
					System.out.println(campo.getKey()+": " + campoLeido);

					puntero += campo.getValue();
				}
				System.out.println("--------------------------\n");
			} 
			
		} catch (EOFException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}

		lectorAleatorio.close();
	}
}
