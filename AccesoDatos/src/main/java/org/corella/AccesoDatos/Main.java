package org.corella.AccesoDatos;

import org.corella.AccesoDatos.aplications.*;
import org.corella.AccesoDatos.utilsAcceso.Constantes;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

public class Main {

	public static void main(String[] args) throws IOException {
		try {
			new ManejoXML().run();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

