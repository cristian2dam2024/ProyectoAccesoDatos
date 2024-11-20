package jdbc;
import java.sql.*;
import java.util.Scanner;

public class ConectorMYSQL {
	

	public void run() {
		
		try {
			Connection conexion = creaConexion();
			//selectAlumnos(conexion);
			//selectAlumnosPrepared(conexion);
			salarioMedioDepartamentos(conexion);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//CRUD: crear, leer, update, borrar sobre la tabla departaments
	
	public void operacionCRUD(Connection conexion) throws SQLException {
		
		Statement sentencia = conexion.createStatement();
		Scanner scanner = new Scanner(System.in);
		ResultSet salidaQuery;
		
		System.out.println("Que tipo de operacion va a realizar? (crear - leer - modificar - borrar)");
		String operacion = scanner.nextLine();
		
		System.out.println("\nTodas las operaciones se realizan sobre la tabla 'departments' de la base de datos");
		String entrada;
		
	
		switch (operacion) {
		case "crear":

			String dept_no;
			String dept_name;
			
			salidaQuery = sentencia.executeQuery("SELECT COUNT (*) FROM departments");
			int numeroRegistros = Integer.parseInt(salidaQuery.toString());
			dept_no = "d00"+String.valueOf(numeroRegistros+1);
			
			System.out.println("Que nombre quiere que tenga el nuevo departamento?");
			dept_name = scanner.nextLine();
			
			sentencia.executeUpdate("INSERT INTO department VALUES ("+dept_no+","+dept_name+");");
			
			
			break;
		case "leer":
			
			System.out.println("Introduzca el numero del departamento (d00+numero) o el nombre del departamento que quiere consultar.");
			entrada = scanner.nextLine();
			
			salidaQuery = sentencia.executeQuery("SELECT FROM departments WHERE dept_no = "+ entrada + " OR dept_name = " + entrada);
			imprimeSalida(salidaQuery);

			break;
		case "update":
			try {
				
				PreparedStatement sentenciaPreparada = conexion.prepareStatement("UPDATE departments SET ? = ? WHERE ? = ? ");
				
				System.out.println("Introduzca el numero del departamento (d00+numero) o el nombre del departamento que quiere modificar.");
				entrada = scanner.nextLine();
				 
				
				
			} catch (SQLIntegrityConstraintViolationException e) {
				// TODO: handle exception
			}
			
			
			
			

			break;
		case "borrar":

			break;

		default:
			break;
		}
		
		conexion.commit();
		
		scanner.close();
		sentencia.close();
		salidaQuery.close();
		conexion.close();
	}
	
	
	
	
	
	
	//Consultar salario medio de los empleados de cada departamento
		// salario total del departamento/numero de empleados del departamento
		//  cada empleado tiene un salario y esta asociado a un departamento
		// 
		// empleado (tabla empleados) -> check dept numero
	private void salarioMedioDepartamentos(Connection conexion) throws SQLException{
		
		Statement sentencia = conexion.createStatement();
		
		String query = "SELECT\r\n"
				+ "    e.dept_no,\r\n"
				+ "    COUNT(*) AS numero_empleados,\r\n"
				+ "    SUM(s.salary) AS suma_salarios,\r\n"
				+ "    AVG(s.salary) AS media_salarios\r\n"
				+ "FROM (\r\n"
				+ "    -- Empleados que son managers\r\n"
				+ "    SELECT managers.emp_no, managers.dept_no\r\n"
				+ "    FROM dept_manager managers\r\n"
				+ "\r\n"
				+ "    UNION ALL\r\n"
				+ "\r\n"
				+ "    -- Empleados normales\r\n"
				+ "    SELECT otros.emp_no, otros.dept_no\r\n"
				+ "    FROM dept_emp otros\r\n"
				+ ") AS e\r\n"
				+ "JOIN salaries s ON e.emp_no = s.emp_no\r\n"
				+ "GROUP BY e.dept_no;";
		
		ResultSet salidaQuery = sentencia.executeQuery(query);
		int numeroColumnas = salidaQuery.getMetaData().getColumnCount();
		while (salidaQuery.next()) {

			for (int i = 1; i <= numeroColumnas; i++) {
				System.out.print(salidaQuery.getObject(i) + " ");
			}
			System.out.println();
		}

		sentencia.close();
		salidaQuery.close();
		conexion.close();
		
		
		
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
			
			imprimeSalida(salidaQuery);
			
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
	

	private void imprimeSalida(ResultSet salidaQuery) throws SQLException {
		// TODO Auto-generated method stub
		int numeroColumnas = salidaQuery.getMetaData().getColumnCount();
		while(salidaQuery.next()) {
			
			for (int i = 1; i <= numeroColumnas; i++) {
				System.out.print(salidaQuery.getObject(i) +" ");
			}
			System.out.println();
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
