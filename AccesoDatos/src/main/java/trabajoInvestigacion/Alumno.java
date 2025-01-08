package trabajoInvestigacion;

import java.io.Serializable;

public class Alumno implements Serializable{
	
	
	String nombre;
	double notaT1;
	int notaFinal;
	
	public Alumno(String nombre, double notaT1, int notaFinal) {
		super();
		this.nombre = nombre;
		this.notaT1 = notaT1;
		this.notaFinal = notaFinal;
	}
	
	public Alumno() {
		super();
	}
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getNotaT1() {
		return notaT1;
	}
	public void setNotaT1(double notaT1) {
		this.notaT1 = notaT1;
	}
	public int getNotaFinal() {
		return notaFinal;
	}
	public void setNotaFinal(int notaFinal) {
		this.notaFinal = notaFinal;
	}
	
	

}
