package org.corella.AccesoDatos;

import org.corella.AccesoDatos.aplications.*;
import org.corella.AccesoDatos.utilsAcceso.Constantes;

import jdbc.ConectorMYSQL;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import javax.xml.parsers.ParserConfigurationException;

public class Main {

    public static void main(String[] args) throws IOException {
    
    	try {
			new ConectorMYSQL().run();
			System.out.println("Todo ha salido bien.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    } 

    
}

