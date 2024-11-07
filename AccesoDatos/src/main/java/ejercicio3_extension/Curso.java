package ejercicio3_extension;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Curso {
	
	private int reg_num;
    private String subj;
    private int crse;
    private String sect;
    private String title;
    private double units;
    private String instructor;
    private String days;
    private LocalTime startTime;
    private LocalTime endTime;
    private String building;
    private String room;
    
    
    public Curso(Node nodo) {
		
    	HashMap<String, String> datos = new HashMap<>();
    	addElement(nodo.getChildNodes(), datos);
    	setNumbers(datos); 
    	
		this.reg_num = Integer.parseInt(datos.get("reg_num"));
		this.subj = datos.get("subj");
		this.crse = Integer.parseInt(datos.get("crse"));
		this.sect = datos.get("sect");
		this.title = datos.get("title");
		this.units = Double.valueOf(datos.get("units"));
		this.instructor = datos.get("instructor");
		this.days = datos.get("days");
		this.setStartTime(datos.get("start_time"));
		this.setEndTime(datos.get("end_time"));
		this.building = datos.get("building");
		this.room = datos.get("room");
	}
    
    private void setNumbers(HashMap<String, String> datos) {
		// TODO Auto-generated method stub
    	String [] claveNumeros = {"reg_num","crse","units"};

    	for (int i = 0; i < claveNumeros.length; i++) {
    		String contenido = datos.get(claveNumeros[i]);
    		if(contenido.isEmpty() || contenido == null) {
    			//System.out.println("La clave es nula");
    			datos.replace(claveNumeros[i], "0");
    		}
		}
	}

	private void addElement(NodeList elementos, HashMap<String, String> datos) {
    	
		for (int i = 0; i < elementos.getLength(); i++) {

			Node etiqueta = elementos.item(i);
			datos.put(etiqueta.getNodeName(), etiqueta.getTextContent());
			
			NodeList hijos = etiqueta.getChildNodes();
			if (hijos.getLength() != 0) {
				addElement(hijos, datos);
			}
		}
    }
    
    public void setStartTime(String tiempoEntrada) {
    	if(tiempoEntrada.isEmpty()) {return;}
    	
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mma", Locale.US);
    	this.startTime = LocalTime.parse(tiempoEntrada.trim(), formatter);
	}
    
	public void setEndTime(String tiempoSalida) {
		if(tiempoSalida.isEmpty()) {return;}
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		this.endTime = LocalTime.parse(tiempoSalida.trim(), formatter);
		
		if(endTime.isBefore(startTime)) {
			this.endTime = this.endTime.plusHours(12);
		}
	}
	
	@Override
	public String toString() {
		return "Curso [reg_num=" + reg_num + ", subj=" + subj + ", crse=" + crse + ", sect=" + sect + ", title=" + title
				+ ", units=" + units + ", instructor=" + instructor + ", days=" + days + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", building=" + building + ", room=" + room + "]";
	}

	public int getRegNum() {
		return reg_num;
	}
	public void setRegNum(int regNum) {
		this.reg_num = regNum;
	}
	public String getSubj() {
		return subj;
	}
	public void setSubj(String subj) {
		this.subj = subj;
	}
	public int getCrse() {
		return crse;
	}
	public void setCrse(int crse) {
		this.crse = crse;
	}
	public String getSect() {
		return sect;
	}
	public void setSect(String sect) {
		this.sect = sect;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getUnits() {
		return units;
	}
	public void setUnits(double units) {
		this.units = units;
	}
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public LocalTime getStartTime() {
		return startTime;
	}
	public LocalTime getEndTime() {
		return endTime;
	}
	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
    
    
	

}
