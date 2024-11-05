package ejercicio3_extension;

import java.io.File;
import java.io.IOException;

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
	
	public void run() throws IOException{
	
		File ficheroXML = new File(Constantes.ejercicio3_ext_ficheroxml);
		//File ficheroDTD = new File(Constantes.ejercicio3_ext_ficherodtd);
		File ficheroXSD = new File(Constantes.ejercicio3_ext_ficheroxsd);
		
		validaFichero(ficheroXML, null);
		validaFichero(ficheroXML, ficheroXSD);
		
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
		} else {
			dbf.setNamespaceAware(true);
			dbf.setSchema(SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(esquemaXSD));
		}
		
		Document documentoXML = dbf.newDocumentBuilder().parse(ficheroXML);
		
		//lee primer nodo(raiz) y almacena los hijos en un NodeList, para cada nodo del nodeList repite la operación de lectura
		almacenaNodo(documentoXML);
		
		
	}

	private void almacenaNodo(Node documentoXML) {
		// TODO Auto-generated method stub
		
		
		
		NodeList hijos = documentoXML.getChildNodes();
		for (int i = 0; i < hijos.getLength(); i++) {
			almacenaNodo(hijos.item(i));
		}
		
	}

	private void validaDTD(File ficheroXML) {
		// TODO Auto-generated method stub
		
		
		
	}

	public void listaInstructores() {
		
	}
	
	public void cuentaCursosImpartidos() {
		
	}
	
	public void getShortestLongest() {
		
	}
	
	
	

}
