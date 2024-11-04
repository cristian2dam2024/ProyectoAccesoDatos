package org.corella.AccesoDatos.aplications;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class ValidacionXML {
	
	
	public static DocumentBuilderFactory validarXML () {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setIgnoringComments(true);
		dbf.setIgnoringElementContentWhitespace(true);
		dbf.setValidating(true);
		return dbf;
	}
	
	public static DocumentBuilderFactory validarXML (File esquema) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setIgnoringComments(true);
		dbf.setIgnoringElementContentWhitespace(true);
		dbf.setNamespaceAware(true);
		try {
			dbf.setSchema(SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(esquema));
		} catch (SAXException e) {
			// TODO: handle exception
		}
		return dbf;
	}
	

	public void run(ArrayList<String> listaFicheros) throws ParserConfigurationException, SAXException, IOException {
		File fichero = null, esquema = null;
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setIgnoringComments(true);
		dbf.setIgnoringElementContentWhitespace(true);
		
		if(listaFicheros.isEmpty() || listaFicheros.size()>2) {
			System.err.println("La lista está mal compuesta");
			
		} else if(listaFicheros.size() == 1) { // valida con DTD
			dbf.setValidating(true);
			
		} else { // valida con xsd
			try {
				dbf.setSchema(SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(esquema));
			} catch (SAXException e) {
				// TODO: handle exception
			}
		}
		
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.parse(fichero);
		
	}
	
	/*	DTD: DATA type definition; muy antiguo antes de la creacion de xml para sgml 
	 * 	esquemas XML: ficheros XSD lo pasamos al programa y validamos el fichero xml
	 *  
	 * 
	 * */
}
