package org.corella.AccesoDatos.aplications;
import java.io.*;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.*;


public class ManejoJSON{


    public void run() throws IOException {

        //escribirJSON();
        serializedOrgJSON();
        //writeJacksonJSON();
        //leerJacksonJSON();
    }
    
    private void escribirJSON() {

        JSONObject objetoJSON = new JSONObject();
        objetoJSON.put("Nombre", "Carlos");
        objetoJSON.put("Edad", 23);

        JSONArray asignaturasMatriculadas = new JSONArray();
        asignaturasMatriculadas.put(0, "Acceso a datos");
        asignaturasMatriculadas.put(1, "Empresa");
        objetoJSON.put("Asignaturas matriculadas", asignaturasMatriculadas);
//
        JSONArray notas = new JSONArray();
        JSONObject notaAsignatura = new JSONObject();
        notaAsignatura.put("Base de datos", 10);
        notaAsignatura.put("Programacion", 10);
        notas.put(notaAsignatura);
        objetoJSON.put("Asignatura_notas" , notas);

        System.out.println(objetoJSON.toString());

    }

    private void serializedOrgJSON() throws IOException {
        Writer demoJSON = new StringWriter();
        JSONWriter writerJSON = new JSONWriter(demoJSON);

        writerJSON.object();
        writerJSON.key("Nombre").value("Andreia");
        writerJSON.key("Edad").value(20);
        writerJSON.key("Titula").value(true);

        writerJSON.key("AsignaturasSuperadas");
        writerJSON.array();
        writerJSON.value("Bases de datos");
        writerJSON.value("Programacion");
        writerJSON.value("Entornos");
        writerJSON.value("Acceso a datos");
        writerJSON.value("Ingés");

        writerJSON.endArray();
        writerJSON.endObject();

        System.out.println(demoJSON);
    }
    
    private void writeJacksonJSON() throws IOException {
        ObjectMapper mapeoObjeto = new ObjectMapper();
        ArrayList<String> asignaturasSuperadas = new ArrayList<>();
        asignaturasSuperadas.add("Acceso a datos");
        asignaturasSuperadas.add("Entornos");
        asignaturasSuperadas.add("Programacion");

        Alumno alumno = new Alumno("",20,false, asignaturasSuperadas);
        
        File ficheroSalida = new File("AccesoDatos/src/main/resources/FicheroOutAlumnos.json");
        mapeoObjeto.writeValue(ficheroSalida, alumno);
    }


    private void leerJacksonJSON() throws IOException {
        ObjectMapper mapeoObjeto = new ObjectMapper();
        File fichero = new File("AccesoDatos/src/main/resources/FicheroOutAlumnos.json");
        Alumno alumno = mapeoObjeto.readValue(fichero, Alumno.class);
        System.out.println(alumno.getNombre());
    }

    static class Alumno implements Serializable {
        private String nombre;
        private int edad;
        private boolean titula;
        private ArrayList<String> asignaturasSuperadas;

        public Alumno() {
        }

        public Alumno(String nombre, int edad, boolean titula, ArrayList<String> asignaturasSuperadas) {
            this.nombre = nombre;
            this.edad = edad;
            this.titula = titula;
            this.asignaturasSuperadas = asignaturasSuperadas;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public int getEdad() {
            return edad;
        }

        public void setEdad(int edad) {
            this.edad = edad;
        }

        public boolean isTitula() {
            return titula;
        }

        public void setTitula(boolean titula) {
            this.titula = titula;
        }

        public ArrayList<String> getAsignaturasSuperadas() {
            return asignaturasSuperadas;
        }

        public void setAsignaturasSuperadas(ArrayList<String> asignaturasSuperadas) {
            this.asignaturasSuperadas = asignaturasSuperadas;
        }
    }
}

