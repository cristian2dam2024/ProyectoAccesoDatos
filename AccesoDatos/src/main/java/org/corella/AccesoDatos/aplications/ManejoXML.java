package org.corella.AccesoDatos.aplications;
import org.corella.AccesoDatos.utilsAcceso.Constantes;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class ManejoXML {

    private static final String INDENT_LEVEL = " ";

    public void run(){

        Document documentoXML = leerXML(Constantes.pruebaxml);

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
                        "\n codificación: " + doc.getXmlEncoding());

                break;

            case Node.ELEMENT_NODE:
                printStream.println("<"+nodo.getNodeName()+">");
                NamedNodeMap atributos = nodo.getAttributes();

                for(int i = 0; i < atributos.getLength(); i++){

                    Node atributo = atributos.item(i);
                    printStream.println(
                            " @ "+ atributo.getNodeName() + "["+ atributo.getNodeValue()+"]");

                }
                printStream.println("</"+nodo.getNodeName()+">");

                break;

            case Node.TEXT_NODE:
                printStream.println(nodo.getNodeName() + " ["+ nodo.getNodeValue()+"]");
                break;

            default:
                printStream.println("nodo de tipo ("+ nodo.getNodeType() +")");
        }

        //recursivo para los nodos hijos del padre que le pasamos
        NodeList listaNodos = nodo.getChildNodes();
        for(int i = 0; i < listaNodos.getLength(); i++){
            muestraNodo(listaNodos.item(i), printStream, nivel+1);
        }

    }

    private Document leerXML (String rutaFichero){
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        //llama una clase que crea instancias pre construidas de otra clase
        //documentbuilder() parsea el fichero y lo trae al codigo java
        //set-get atributos() para manejar el factory

        //dbf.setIgnoringComments(true);
        //dbf.setIgnoringElementContentWhitespace(true);
        Document documentoXML = null;
        try {
            DocumentBuilder builder = dbf.newDocumentBuilder();
            documentoXML = builder.parse(new File(Constantes.pruebaxml));
            muestraNodo(documentoXML, System.out, Constantes.pruebaxml.length());
        } catch (FileNotFoundException | ParserConfigurationException e){
            System.err.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
