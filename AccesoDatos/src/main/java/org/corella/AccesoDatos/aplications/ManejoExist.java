package org.corella.AccesoDatos.aplications;

import javax.xml.transform.OutputKeys;

import org.exist.xmldb.EXistResource;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

public class ManejoExist {
	
	public void run() throws Exception {
		obtenerColeccion();
	}
	
	private static Collection obtenerColeccion() throws Exception {
    	Database databaseDriver;
    	Collection coleccion;
    	
    	databaseDriver = (Database) Class.forName("org.exist.xmldb.DatabaseImpl").newInstance();
    	coleccion = DatabaseManager.getCollection("xmldb:exist://localhost:8080//db/apps/demo/data/addresses/","admin", "admin");
//    	return coleccion;
    	
    	
    	 final String URI = "xmldb:exist://localhost:8080/exist/xmlrpc/db/exist/apps/demo/data/addresses/";
    	 final String driver = "org.exist.xmldb.DatabaseImpl";
    	 //driver gestiona la conexion con la base de datos
    	 //cada lenguage tiene su driver, mysql tiene otro
         
         // initialize database driver
         Class cl = Class.forName(driver);
         Database database = (Database) cl.newInstance();
         database.setProperty("create-database", "true");
         //en caso de que no exista la crea
         DatabaseManager.registerDatabase(database);
         
         //variables de gestion de recursos de la base de datos, recursos o ficheros
         Collection col = null;
         //coleccion va a tener mas colecciones dentro
         XMLResource res = null;
         //va a ser un documento o recurso de esa coleccion, de aqui vamos a sacar los datos
         
         try {    
             // get the collection
             col = DatabaseManager.getCollection(URI ,"admin","admin");
             col.setProperty(OutputKeys.INDENT, "no");
             res = (XMLResource)col.getResource("0ff8612a-b998-4677-84a3-73e9ef84ba5f.xml");
             
             //EJERCICIOS INCOMPLETOS eXist-DB
//             Crear y borrar una coleccion.
//             String URIserver;
//             String rutaColeccion;
//             Collection start = DatabaseManager.getCollection(URIserver + rutaColeccion);
//             
//             if(start == null) {
//            	 Collection parent = DatabaseManager.getCollection(URIserver);
//                 CollectionManagementService mgt = (CollectionManagementService) parent.getService("CollectionManagementService", "1.0");
//                 
//             }
//             Crear (con contenido) un recurso y borrar un recurso
//             (XMLResource).setContent
//             col.createResource(driver, null)
             if(res == null) {
                 System.out.println("document not found!");
             } else {
                 System.out.println(res.getContent());
                 return coleccion;
             }
         } finally {
             //dont forget to clean up!
             if(res != null) {
                 try { ((EXistResource)res).freeResources(); } catch(XMLDBException xe) {xe.printStackTrace();}
             }
             if(col != null) {
                 try { col.close(); } catch(XMLDBException xe) {xe.printStackTrace();}
             }
         }
		return col;
    }

}
