package org.corella.AccesoDatos.entidades;

public class AlumnoPractica1 {

    private String nombre, apellido;
    private int edad;
    private float altura, calificacion;

    public AlumnoPractica1(String [] campos, float altura) {
        this.nombre = campos[0];
        this.apellido = campos[1];
        this.altura = altura;

        this.edad = (int) ((altura*100) %100)/2;
        //System.out.println("La edad para "+ this.nombre+" es: " + this.edad);

        int auxiliar = this.edad;
        do{
            auxiliar /= 10;
        } while (auxiliar >= 10);

        //System.out.println("El valor auxiliar para "+ this.nombre+" es: " + auxiliar);
        this.calificacion = (float) auxiliar * 2;
    }

    @Override
    public String toString() {
        return this.nombre + " " + this.apellido + " " + this.edad + " " + this.altura + " " + this.calificacion +"\n";
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getEdad() {
        return edad;
    }

    public float getAltura() {
        return altura;
    }

    public float getCalificacion() {
        return calificacion;
    }
}
