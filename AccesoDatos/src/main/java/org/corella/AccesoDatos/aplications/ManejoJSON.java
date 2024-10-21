package org.corella.AccesoDatos.aplications;
import java.io.IOException;
import org.json.*;

public class ManejoJSON {

    public void run() throws IOException {
        escribirJSON();
    }

    private void escribirJSON() throws IOException {

        JSONObject objetoJSON = new JSONObject();
        objetoJSON.put("Nombre", "Carlos");
        objetoJSON.put("Edad", 23);

        JSONArray asignaturasMatriculadas = new JSONArray();
        asignaturasMatriculadas.put(0, "Acceso a datos");
        asignaturasMatriculadas.put(1, "Empresa");
        objetoJSON.put("Asignaturas matriculadas", asignaturasMatriculadas);

        JSONArray notas = new JSONArray();
        JSONObject notaAsignatura = new JSONObject();
        notaAsignatura.put("Base de datos", 10);
        notaAsignatura.put("Programacion", 10);
        notas.put(0, notas);
        objetoJSON.put("Asignatura_notas" , notas);

        System.out.println(objetoJSON.toString());

    }


}

