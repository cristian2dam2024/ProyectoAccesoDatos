package ejercicio3_extension;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.SchemaFactory;

import org.corella.AccesoDatos.utilsAcceso.Constantes;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Ejercicio3_extension {
	
	private ArrayList<Curso> arrayCursos = new ArrayList<>();
	private int numeroCursos;
	
	public void run() throws IOException, SAXException, ParserConfigurationException{
	
		File ficheroXML = new File(Constantes.ejercicio3_ext_ficheroxml);
		File ficheroXSD = new File(Constantes.ejercicio3_ext_ficheroxsd);
		
//		validaFichero(ficheroXML, null); // el fichero DTD no hace falta instanciarlo
		validaFichero(ficheroXML, ficheroXSD);
//		System.out.println(arrayCursos.get(0).toString()); // prueba
		
		cuentaCursosImpartidos();
//		listaInstructores();
//		getShortestLongest();
		
		
	}
	
	private void validaFichero(File ficheroXML, File esquemaXSD) throws SAXException, IOException, ParserConfigurationException {
		// TODO Auto-generated method stub
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setIgnoringComments(true);
		dbf.setIgnoringElementContentWhitespace(true);
		
		if(esquemaXSD == null) {
			dbf.setValidating(true);
			System.out.println("Fichero validado correctamente con DTD.");
		} else {
			dbf.setNamespaceAware(true);
			dbf.setSchema(SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(esquemaXSD));
			System.out.println("Fichero validado correctamente con XSD.");
		}
		
		Document documentoXML = dbf.newDocumentBuilder().parse(ficheroXML);
		NodeList cursos = documentoXML.getElementsByTagName("course");
		
		for (int i = 0; i < cursos.getLength(); i++) {
			arrayCursos.add(new Curso(cursos.item(i)));
		}
	}


	public void listaInstructores() {
		
		HashSet<String> instructores = new HashSet<String>();
		
		for(Curso item : arrayCursos) {
			instructores.add(item.getInstructor());
		}
		
		System.out.println("El numero de instructores es: " + instructores.size());
		new TreeSet<>(instructores).forEach(System.out::println);
	}
	
//	public void cuentaCursosImpartidos() {
//		
//		Map<Integer, Map<String, Integer>> contador = new HashMap<Integer, Map<String,Integer>>();		
//		//HashMap<Integer, Integer> contador = new HashMap<Integer, Integer>();
//		
//		for(Curso item : arrayCursos) {
//			contador.computeIfAbsent(item.getCrse(), categoria -> new HashMap<String, Integer>())
//				.merge(item.getTitle(), 1, Integer::sum);		
//			}
//		this.numeroCursos = contador.size();
//		System.out.println("Hay " + numeroCursos + " cursos distintos.");
//		
//		Map<Integer, Map<String, Integer>> ordenaNumeros = new TreeMap<Integer, Map<String,Integer>>(contador);
//		ordenaNumeros.forEach((claveNumerica , curso) -> {
//			new TreeMap<String, Integer>(curso).forEach((nombreCurso, numVeces) -> {
//				System.out.println(claveNumerica + ": [" + nombreCurso + "] se ha impartido "+ numVeces + ((numVeces > 1) ? " veces. ":" vez."));
//			});
//		});
//	} 	
//			LA SALIDA DE ESTE METODO DEVUELVE MULTIPLES VALORES POR CADA NOMBRE CURSO, ejemplo: AIKIDO 101, 102... etc, 
//				VARIANDO EL crse o NUMERO DE CURSO, NO PARECE QUE SEA IDENTIFICADOR COMPUESTO

	public void cuentaCursosImpartidos() {

		Map<String, Integer> contador = new HashMap<String, Integer>();
		// HashMap<Integer, Integer> contador = new HashMap<Integer, Integer>();

		for (Curso item : arrayCursos) {
			contador.merge(item.getTitle(), 1, Integer::sum);
		}
		System.out.println("Hay " + contador.size() + " cursos distintos.");
		
		new TreeMap<>(contador).forEach((nombreCurso, numVeces) -> {
			System.out.println(
					"[" + nombreCurso + "] se ha impartido " + numVeces + ((numVeces > 1) ? " veces. " : " vez."));
		});
	}

	public void getShortestLongest() {
		
		Map<String, Duration> contador = new HashMap<String, Duration>();		
		//HashMap<Integer, Integer> contador = new HashMap<Integer, Integer>();
		
		for(Curso item : arrayCursos) {
			
			LocalTime startTime = item.getStartTime();
			LocalTime endTime = item.getEndTime();
			Duration tiempoClase = null;

			if (startTime != null && endTime != null) {
				int numeroDias = item.getDays().split("-").length;
				tiempoClase = Duration.between(item.getStartTime(), item.getEndTime()).multipliedBy(numeroDias);
				contador.merge(item.getTitle(), tiempoClase, Duration::plus);
			}
		}
		
		Duration maximo = null;
		Duration minimo = null;
		String cursoMaximo = null;
		String cursoMinimo = null;
		
		for(Entry<String, Duration> clave : contador.entrySet()) {
			Duration duracionClase = clave.getValue();
			if(maximo == null || duracionClase.compareTo(maximo) > 0 ){
				maximo = duracionClase;
				cursoMaximo = clave.getKey();
			} else if(minimo == null || duracionClase.compareTo(minimo) < 0 ){
				minimo = duracionClase;
				cursoMinimo = clave.getKey();
			}
		}
		
		System.out.println("Curso mas largo: [" + cursoMaximo +"] " + maximo.toHours() +" horas, "+maximo.toMinutesPart()+" minutos");
		System.out.println("Curso mas corto: [" + cursoMinimo +"] " + minimo.toHours() +" horas, "+minimo.toMinutesPart()+" minutos"); 
		
	}
	
	
	

}
