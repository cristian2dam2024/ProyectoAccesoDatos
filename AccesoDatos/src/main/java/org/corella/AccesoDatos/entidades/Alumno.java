package org.corella.AccesoDatos.entidades;

public class Alumno {

    public String nombre, curso;
    public int edad;
    public float nota;

    public Alumno(String nombre, String curso, int edad, float nota) {
        this.nombre = nombre;
        this.curso = curso;
        this.edad = edad;
        this.nota = nota;
    }

    public Alumno(String[] campos){
        this.nombre = campos[0];
        this.curso = campos[1];
        this.edad =Integer.valueOf(campos[2]);
        this.nota = Float.valueOf(campos[3]);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }
}
