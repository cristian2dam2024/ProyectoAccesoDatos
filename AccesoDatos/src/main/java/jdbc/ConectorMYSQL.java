package jdbc;
import java.sql.*;
import java.util.Scanner;

public class ConectorMYSQL {
	

	public void run() throws SQLException {
		
//			consultasEmpleados();
			consultasEscuela();
			
	}

	private void consultasEmpleados() throws SQLException {
		Connection conexion = creaConexion("employees");
		operacionesCRUD(conexion);
//		salarioMedioDepartamentos(conexion);
//		llamadaProcedimiento(conexion);
		conexion.close();
	}

	private void consultasEscuela() throws SQLException {
		Connection conexion = creaConexion("Escuela");
//		selectAlumnos(conexion);
		selectAlumnosPrepared(conexion);
//		insertAlumnosPrepared(conexion);
//		ejecutarBatch(conexion);
		conexion.close();
	}
	
	private static String hostname = "localhost";
	private static int port = 3306;
//	private static String databaseName = "employees";
	
	private static final String driverClassName = "com.mysql.cj.jdbc.Driver";
	private static String url; 
	private static final String username = "root";
	private static final String password = "1234";
	
	public Connection creaConexion(String databaseName){
		ConectorMYSQL.url = "jdbc:mysql://" + hostname + ":" + port + "/"+ databaseName +"?useSSL=false";
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

	private void selectAlumnos(Connection conexion) throws SQLException {
		Statement sentencia = conexion.createStatement();
		ResultSet salidaQuery = sentencia.executeQuery("SELECT * FROM alumno;");
		imprimeSalida(salidaQuery);
		
		sentencia.close();
		salidaQuery.close();
		conexion.close();
	}

	//CRUD: crear, leer, update, borrar sobre la tabla departaments
	
	private void selectAlumnosPrepared(Connection conexion)throws SQLException {
		
			try {				
				String query = "SELECT * FROM alumno where id = ?";
	//			String query = "SELECT * FROM alumnos where nombre = ? AND apellido1 = ?";
				
				PreparedStatement sentencia = conexion.prepareStatement(query);
				sentencia.setInt(1, 2); //el set debe corresponderse con el tipo de dato que tenemos en la base de datos
				ResultSet salidaQuery = sentencia.executeQuery();
				imprimeSalida(salidaQuery);
				
				sentencia.close();
				salidaQuery.close();
				conexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	private PreparedStatement insertAlumnosPrepared(Connection conexion) throws SQLException {
		String query = "INSERT INTO alumno (nombre, notaT1, notaFinal) VALUES (?,?,?)";
		PreparedStatement statement = conexion.prepareStatement(query);
		
		statement.setString(1, "Manuel");
		statement.setDouble(2, 10);
		statement.setInt(3, 10);
		statement.executeUpdate();
		
		conexion.commit();
		statement.close();
		
		//de la manera en la que esta construida la conexion, inicia la transaccion
		//cuando se llama al metodo setAutocommit(false), se cierra la transaccion cuando hacemos commit
		//se pueden iniciar mas transacciones cerrando la conexion y abriendo otra o llamando 
		//de nuevo al metodo setAutoCommit(false)
		
		//usamos transacciones cuando necesitamos que los datos accesibles deben ser iguales para los usuarios que usan la base de datos
		//y para evitar problemas de moficaciones en variables estaticas
		
		
		//executeUpdate//modificaciones dentro la tabla, insert update...
		//para consultas dinamicas, evita problemas de inyeccion de codigo sql dentro del codigo java
		//mejora el rendimiento de las consultas a la base de datos
		
		return statement;
	}

	public void operacionesCRUD(Connection conexion) throws SQLException {
		
		Statement sentencia = conexion.createStatement();
		Scanner scanner = new Scanner(System.in);
		ResultSet salidaQuery = null;
		
		System.out.println("Que tipo de operacion va a realizar? (crear - leer - modificar - borrar)");
		String operacion = scanner.nextLine();
		
		System.out.println("\nTodas las operaciones se realizan sobre la tabla 'departments' de la base de datos employees");
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
//				+ "    COUNT(*) AS numero_empleados,\r\n"
//				+ "    SUM(s.salary) AS suma_salarios,\r\n"
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
		imprimeSalida(salidaQuery);

		sentencia.close();
		salidaQuery.close();
		conexion.close();
	}
	

	private void llamadaProcedimiento (Connection conexion) throws SQLException{
		CallableStatement cs = conexion.prepareCall("{call procedimiento_media_salarios_dept_anyo(?,?,?)}");
		
		int anyo= 1988;
		String dpto = "d001";
		
		cs.setInt(1, anyo);
		cs.setString(2, dpto);
		cs.registerOutParameter(3, Types.DOUBLE);
		cs.execute();
		
		double salidaProcedimiento = cs.getDouble(3);
		
		System.out.println("Media de salarios para el departamento " + dpto + " en el año: " + anyo +": " + salidaProcedimiento);
		
		cs.close();
		conexion.close();
		
		
//		ejecucion por lotes, normalmente se usa para insertar datos en la base de datos
//		addBatch deja las tareas preparadas en diferido
	}
	
	private void llamadaFuncion (Connection conexion) throws SQLException{
		CallableStatement cs = conexion.prepareCall("{? = call media_salarios_dept_anyo2(?,?)}");
		
		int anyo= 1988;
		String dpto = "d001";
		
		cs.setInt(2, anyo);
		cs.setString(3, dpto);
		cs.registerOutParameter(1, Types.DOUBLE);
		cs.execute();
		
		double salidaProcedimiento = cs.getDouble(1);
		
		System.out.println("Media de salarios para el departamento " + dpto + " en el año: " + anyo +": " + salidaProcedimiento);
		cs.close();
		conexion.close();
		
//		ejecucion por lotes, normalmente se usa para insertar datos en la base de datos
//		addBatch deja las tareas preparadas en diferido
	}
	
	private void ejecutarBatch(Connection conexion) {
		String nombreAlumno = null;
		String [][] datos = {{"Anton","10","10"},{"Arancha","100","10"},{"Abel","10","100"}};
		try {
			PreparedStatement sentencia = conexion.prepareStatement("INSERT INTO alumno (nombre, notaT1, notaFinal) VALUES (?,?,?)");
			conexion.setAutoCommit(false);
			
			//ESTE TIPO DE PROCESAMIENTO DE INSERTS O MODIFICACIONES DE LA BASE DE DATOS
			//SE SUELE PLANIFICAR PARA EJECUTARLA DURANTE LAS HORAS DONDE LA BASE DE DATOS TIENE MENOS TRAFICO
			//POR LA NOCHE HAY MENOS TRAFICO Y ES CUANDO SE HACEN LAS ACTUALIZACIONES BATCH
			for (int punteroFila = 0; punteroFila < datos.length; punteroFila++) {
				
				for (int punteroRegistro = 0; punteroRegistro < datos[0].length; punteroRegistro++) {
					
					switch (punteroRegistro) {
					case 0:
						sentencia.setString(1, datos[punteroFila][punteroRegistro]);
						break;
					case 1:
						sentencia.setDouble(2, Double.valueOf(datos[punteroFila][punteroRegistro]));
						break;
					case 2:
						sentencia.setInt(3, Integer.valueOf(datos[punteroFila][punteroRegistro]));
						break;

					default:
						break;
					}
					
					
				}
				sentencia.addBatch();
			}
			sentencia.executeBatch();
			conexion.commit();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
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
	
	//con el numero de departamento y el año, 
	//calcular salario medio del departamento en un año dado
	
	
	
}
