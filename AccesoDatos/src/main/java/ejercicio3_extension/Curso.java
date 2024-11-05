package ejercicio3_extension;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Curso {
	
	
	private int regNum;
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
    private int room;
    
    public Curso(ArrayList<String> datos) {
		
		this.regNum = Integer.parseInt(datos[]);
		this.subj = datos[];
		this.crse = Integer.parseInt(datos[]);
		this.sect = datos[];
		this.title = datos[];
		this.units = Double.valueOf(datos[]);
		this.instructor = datos[];
		this.days = datos[];
		this.setStartTime(datos[]);
		this.setEndTime(datos[]);
		this.building = datos[];
		this.room = Integer.parseInt(datos[]);
	}
    
    
    public void setStartTime(String tiempoEntrada) {
    	this.startTime.parse(tiempoEntrada, DateTimeFormatter.ofPattern("hh:mma"));
	}
    
	public void setEndTime(String tiempoSalida) {
		this.startTime.parse(tiempoSalida, DateTimeFormatter.ofPattern("hh:mm"));
	}
	
	public int getRegNum() {
		return regNum;
	}
	public void setRegNum(int regNum) {
		this.regNum = regNum;
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
	public int getRoom() {
		return room;
	}
	public void setRoom(int room) {
		this.room = room;
	}
    
    
	

}
