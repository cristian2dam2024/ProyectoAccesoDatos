package org.corella.AccesoDatos.entidades.practica2;

import java.io.Serializable;
import java.util.ArrayList;

public class Configuracion implements Serializable{
	
	String rutaFicheroPosiciones, rutaFicheroDatos, rutaLongitudRegistros, rutaFicheroSalida, criterioOrden, tipo;
	String clavePuntero = "";
	
	ArrayList<Long> registros = new ArrayList<Long>();
	
	int tamanioTotalRegistros;
	int numeroRegistros = 0;
	
	public Configuracion() {
		super();
	}
	
	public Configuracion(String rutaFicheroPosiciones, String rutaFicheroDatos, String criterioOrden,
			int tamanioTotalRegistros) {
		super();
		this.rutaFicheroPosiciones = rutaFicheroPosiciones;
		this.rutaFicheroDatos = rutaFicheroDatos;
		this.criterioOrden = criterioOrden;
		this.tamanioTotalRegistros = tamanioTotalRegistros;
	}
	public String getRutaFicheroPosiciones() {
		return rutaFicheroPosiciones;
	}
	public void setRutaFicheroPosiciones(String rutaFicheroPosiciones) {
		this.rutaFicheroPosiciones = rutaFicheroPosiciones;
	}
	public String getRutaFicheroDatos() {
		return rutaFicheroDatos;
	}
	public void setRutaFicheroDatos(String rutaFicheroDatos) {
		this.rutaFicheroDatos = rutaFicheroDatos;
	}
	public String getCriterioOrden() {
		
		return criterioOrden;
	}
	public void setCriterioOrden(String criterioOrden) {
		this.criterioOrden = criterioOrden;
	}
	public int getTamanioTotalRegistros() {
		return tamanioTotalRegistros;
	}
	public void setTamanioTotalRegistros(int tamanioTotalRegistros) {
		this.tamanioTotalRegistros = tamanioTotalRegistros;
	}
	public String getRutaLongitudRegistros() {
		return rutaLongitudRegistros;
	}
	public void setRutaLongitudRegistros(String rutaLongitudRegistros) {
		this.rutaLongitudRegistros = rutaLongitudRegistros;
	}
	public String getRutaFicheroSalida() {
		return rutaFicheroSalida;
	}
	public void setRutaFicheroSalida(String rutaFicheroSalida) {
		this.rutaFicheroSalida = rutaFicheroSalida;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getClavePuntero() {
		return clavePuntero;
	}
	public void setClavePuntero(String clavePuntero) {
		this.clavePuntero = clavePuntero;
	}

	public ArrayList<Long> getRegistros() {
		return registros;
	}
	public void setRegistros(ArrayList<Long> registros) {
		this.registros = registros;
	}
	public void addRegistro(Long posicionRegistro) {
		this.registros.add(posicionRegistro);
	}
	public int getNumeroRegistros() {
		return numeroRegistros;
	}
	public void setNumeroRegistros() {
		this.numeroRegistros++;
	}
	
	
	
	
	
	

}
