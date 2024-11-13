package jdbc;
import java.sql.*;

public class ConectorMYSQL {
	
	public void run() {
		
		try {
			Connection conexion = creaConexion();
			selectAlumnos(conexion);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
