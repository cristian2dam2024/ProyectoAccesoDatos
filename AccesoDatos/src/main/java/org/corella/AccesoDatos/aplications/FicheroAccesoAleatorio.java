package org.corella.AccesoDatos.aplications;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;

public class FicheroAccesoAleatorio {

    private File file;
    private List<Pair<String, Integer>> campos;
    private long longitudRegistro, numeroRegistros;


    public void run(){
        List campos = new ArrayList<>();

        campos.add(new Pair<>("DNI", 9));
        campos.add(new Pair<>("NOMBRE", 32));
        campos.add(new Pair<>("NACIONALIDAD", 20));

        try {
            FicheroAccesoAleatorio escritor = new FicheroAccesoAleatorio("src/main/resources/ficheroRA.dat", campos);
            Map registros = new HashMap();

            registros.put("DNI", "76546473H");
            registros.put("NOMBRE", "JUAN CARLOS");
            registros.put("NACIONALIDAD", "ESPAÑA");
            escritor.insertar(registros, 1);

            registros.clear();
            registros.put("DNI", "76000073H");
            registros.put("NOMBRE", "MARIA TERESA");
            registros.put("NACIONALIDAD", "FRANCIA");
            escritor.insertar(registros, 1);

            registros.clear();
            registros.put("DNI", "76000073H");
            registros.put("NOMBRE", "MARTIN");
            registros.put("NACIONALIDAD", "ALEMANIA");
            escritor.insertar(registros, 1);


        }catch (IOException e){
            System.err.println("ERROR DE E/S: " + e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public FicheroAccesoAleatorio(String fileName, List<Pair<String, Integer>> fields) throws IOException {
        this.campos = fields;
        this.file = new File(fileName);
        longitudRegistro = 0;

        for (Pair<String, Integer> campo : campos) {
            this.longitudRegistro += campo.getValue();
        }

        if (file.exists()) {
            this.numeroRegistros = file.length() / this.longitudRegistro;
        }
    }

    public void insertar (Map<String, String> registro) throws IOException {
        insertar(registro, this.numeroRegistros++);
    }

    public void insertar (Map<String, String> registro, long posicion)throws IOException{
        RandomAccessFile escritorAleatorio = new RandomAccessFile (file, "rws");
        escritorAleatorio.seek(posicion *longitudRegistro);

        for(Pair<String, Integer> campo : this.campos){
            String nombreCampo = campo.getKey();
            Integer longitudCampo = campo.getValue();
            String valorCampo = registro.get(nombreCampo);
            if(valorCampo == null){
                valorCampo = "";
            }

            String valorCampoForm = String.format("%1$-" + longitudCampo + "s", valorCampo +"s", valorCampo);
            escritorAleatorio.write(valorCampoForm.getBytes("UTF-8"), 0, longitudCampo);

        }
    }

    public long numeroRegistros() {
        return numeroRegistros;
    }

}
