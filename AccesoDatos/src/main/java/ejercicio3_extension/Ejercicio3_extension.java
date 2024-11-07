package ejercicio3_extension;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.SchemaFactory;

import org.corella.AccesoDatos.utilsAcceso.Constantes;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Ejercicio3_extension {
	
	private ArrayList<Curso> arrayCursos = new ArrayList<>();
	private Document xmlValidado;
	
	public void run() throws IOException, SAXException, ParserConfigurationException{
	
		File ficheroXML = new File(Constantes.ejercicio3_ext_ficheroxml);
		File ficheroXSD = new File(Constantes.ejercicio3_ext_ficheroxsd);
		
		validaFichero(ficheroXML, null); // el fichero DTD no hace falta instanciarlo
		validaFichero(ficheroXML, ficheroXSD);
		
		System.out.println(arrayCursos.get(0).toString());
		
		listaInstructores();
		cuentaCursosImpartidos();
		getShortestLongest();
		
		
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
		
	}
	
	public void cuentaCursosImpartidos() {
		
	}
	
	public void getShortestLongest() {
		
	}
	
	
	

}
