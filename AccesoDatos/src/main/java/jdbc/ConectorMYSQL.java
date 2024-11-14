package jdbc;
import java.sql.*;

public class ConectorMYSQL {
	
	public void run() {
		
		try {
			Connection conexion = creaConexion();
			//selectAlumnos(conexion);
			selectAlumnosPrepared(conexion);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	private String selectAlumnosPrepared(Connection conexion)throws SQLException {
		String nombreAlumno = "";
		
		try {
//			String query2 = "SELECT * FROM alumnos where nombre = ? AND apellido1 = ?";
//			PreparedStatement pr2 = conexion.prepareStatement(query2);
			
			String query1 = "SELECT * FROM alumnos where id = ?";
			PreparedStatement pr = conexion.prepareStatement(query1);
			
			String id = "2";
			pr.setString(1, id); //el set debe corresponderse con el tipo de dato que tenemos en la base de datos
			ResultSet salidaQuery = pr.executeQuery();
			int numeroColumnas = salidaQuery.getMetaData().getColumnCount();
			while(salidaQuery.next()) {
				
				for (int i = 1; i <= numeroColumnas; i++) {
					System.out.print(salidaQuery.getObject(i) +" ");
				}
				System.out.println();
			}
			pr.close();
			
			String query3 = "INSERT INTO alumnos VALUES (?,?,?,?)";
			PreparedStatement pr3 = conexion.prepareStatement(query3);
			pr3.setString(1, "5");
			pr3.setString(2, "Manuel");
			pr3.setString(3, "Gomez");
			pr3.setString(4, "Gomez");
			pr3.executeUpdate();
			conexion.commit();
			pr3.close();
			
			
			salidaQuery.close();
			conexion.close();
			
			//executeUpdate//modificaciones dentro la tabla, insert update...
			//para consultas dinamicas, evita problemas de inyeccion de codigo sql dentro del codigo java
			//mejora el rendimiento de las consultas a la base de datos
			//
			
			return nombreAlumno;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	} 
	

	private void selectAlumnos(Connection conexion) throws SQLException {
		Statement sentencia = conexion.createStatement();
		ResultSet salidaQuery = sentencia.executeQuery("SELECT * FROM alumnos;");
		int numeroColumnas = salidaQuery.getMetaData().getColumnCount();
		
		while(salidaQuery.next()) {
			
			for (int i = 1; i <= numeroColumnas; i++) {
				System.out.print(salidaQuery.getObject(i) +" ");
			}
			System.out.println();
		}
		
		sentencia.close();
		salidaQuery.close();
		conexion.close();
	}
	
	private static String hostname = "localhost";
	private static int port = 3306;
	private static String databaseName = "pruebaConexion";
	
	private static final String driverClassName = "com.mysql.cj.jdbc.Driver";
	private static final String url = "jdbc:mysql://" + hostname + ":" + port + "/"+ databaseName +"?useSSL=false";
	private static final String username = "root";
	private static final String password = "1234";
	
	public Connection creaConexion(){
		
		try {
			Connection conexion = null;
			// Load driver class
			Class.forName(driverClassName);
			
			// Obtain a connection
			conexion = DriverManager.getConnection(url, username, password);
			conexion.setAutoCommit(false);
			return conexion;
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
}
