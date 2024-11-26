package org.corella.AccesoDatos.aplications;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;


import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;
import org.corella.AccesoDatos.utilsAcceso.Constantes;
import org.exist.xmldb.EXistResource;


public class ManejoXML {

    private static final String INDENT_LEVEL = " ";

    public void run() throws ParserConfigurationException, IOException{
    	escribirXML();
    	leerXML(Constantes.salidaxml);
    }
    
    private void escribirXML() throws ParserConfigurationException, IOException{
    	try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document documento = db.newDocument();
			documento.setXmlVersion("1.0");
			
			Element elementoRaiz = documento.createElement("clientes");
			documento.appendChild(elementoRaiz); // está en la raiz del documento
			
			Element elementoCliente = documento.createElement("cliente");
			elementoRaiz.appendChild(elementoCliente);
			
			elementoCliente.setAttribute("DNI", "123456789M");
			// ... todos los atributos que queramos 
			
			Element nombre = documento.createElement("NOMBRE");
			elementoCliente.appendChild(nombre);
			nombre.appendChild(documento.createTextNode("Darío"));
			
			// -------------------------------------------------------
			DOMSource domSource = new DOMSource(documento);
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "clientes.dtd");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			
//			//sacar salida por consola
//			StringWriter sw = new StringWriter();
//			StreamResult sr = new StreamResult(sw);
//			transformer.transform(domSource, sr);
//			System.out.println(sw.toString());
		
			//sacar salida a un fichero
			FileWriter fw = new FileWriter(Constantes.salidaxml);
			StreamResult sr2 = new StreamResult(fw);
			transformer.transform(domSource, sr2);
			fw.close();
			
		} catch (ParserConfigurationException parserException) {
			System.err.println(parserException.getMessage());
			
		} catch (TransformerConfigurationException e) {
			
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }
    
	private void leerXML(String rutaFichero) {

		Document documentoXML = null;
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setIgnoringComments(true);
			dbf.setIgnoringElementContentWhitespace(true);
			
			boolean validaDTD = false;
			if(validaDTD) {
				dbf.setValidating(true);
			} else {
				dbf.setNamespaceAware(true);
				dbf.setSchema(SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(new File(Constantes.ficheroXSD)));
			}
			
			DocumentBuilder builder = dbf.newDocumentBuilder();
			builder.setErrorHandler(new GestorEventos());
			documentoXML = builder.parse(new File(rutaFichero));
			muestraNodo(documentoXML, System.out, 1);
			
		} catch (FileNotFoundException | ParserConfigurationException e) {
			System.err.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    private static void muestraNodo(Node nodo, PrintStream printStream, int nivel) {
    	
        if(nodo.getNodeType() == Node.TEXT_NODE && nodo.getNodeValue().isEmpty()){
            return;
        }
        for(int i = 0; i < nivel; i++){
            printStream.print(INDENT_LEVEL);
        }
        //primero determinamos el tipo de nodo que tenemos
        switch (nodo.getNodeType()){
        
            case Node.DOCUMENT_NODE:
                Document doc = (Document) nodo;
                printStream.println(
                        "Documento DOM, version: "+doc.getXmlVersion()+
                        "\t codificación: " + doc.getXmlEncoding());
                break;
            case Node.ELEMENT_NODE:
            
                printStream.print("ELEMENT_NODE: <"+nodo.getNodeName()+"> ");
                NamedNodeMap atributos = nodo.getAttributes();
                
                for(int i = 0; i < atributos.getLength(); i++){
                    Node atributo = atributos.item(i);
                    printStream.print(
                            " @ "+ atributo.getNodeName() + "["+ atributo.getNodeValue()+"]");
                }
                printStream.println(" </"+nodo.getNodeName()+">");
                break;
            case Node.TEXT_NODE:
                printStream.print("TEXT_NODE: "+nodo.getNodeName() + " ["+ nodo.getNodeValue()+"]");
                break;
            default:
                //printStream.println("nodo de tipo ("+ nodo.getNodeType() +")");
        }
        //recursivo para los nodos hijos del padre que le pasamos
        NodeList listaNodos = nodo.getChildNodes();
        for(int i = 0; i < listaNodos.getLength(); i++){
            muestraNodo(listaNodos.item(i), printStream, nivel+1);
        }
    }
    
    class GestorEventos extends DefaultHandler{
    	@Override
    	public void error(SAXParseException e) throws SAXException {
    		// TODO Auto-generated method stub
    		System.err.println("Error recuperable " + e.getMessage());
    	}
    	@Override
    	public void fatalError(SAXParseException e) throws SAXException {
    		// TODO Auto-generated method stub
    		System.err.println("Error no recuperable " + e.getMessage());
    	}
    	@Override
    	public void warning(SAXParseException e) throws SAXException {
    		// TODO Auto-generated method stub
    		System.err.println("Aviso: " + e.getMessage());
    	}
    }
}
