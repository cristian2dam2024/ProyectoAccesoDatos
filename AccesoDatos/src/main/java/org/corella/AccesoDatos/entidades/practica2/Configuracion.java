package org.corella.AccesoDatos.entidades.practica2;

import java.io.Serializable;

public class Configuracion implements Serializable{
	
	String rutaFicheroPosiciones, rutaFicheroDatos, rutaLongitudRegistros, rutaFicheroSalida, criterioOrden;
	int tamanioTotalRegistros;
	
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
	
	
	
	
	

}
