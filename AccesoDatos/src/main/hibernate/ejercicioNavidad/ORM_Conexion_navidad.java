package ejercicioNavidad;

import java.util.Set;

import org.corella.AccesoDatos.utilsAcceso.Dates;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ejercicioNavidad_clases.Departments;
import ejercicioNavidad_clases.DeptEmp;
import ejercicioNavidad_clases.DeptEmpId;
import ejercicioNavidad_clases.DeptManager;
import ejercicioNavidad_clases.Employees;
import ejercicioNavidad_clases.Salaries;
import ejercicioNavidad_clases.SalariesId;
import ejercicioNavidad_clases.Titles;
import ejercicioNavidad_clases.TitlesId;
import entities.Empleado;
import utils.HibernateUtilNavidad;

public class ORM_Conexion_navidad {
	
	public void run() {
//		insertaEmpleadoNormal();
//		insertaEmpleadoManager();
		updateEmpleado(14);
//		deleteEmpleado(18);
	}
	
	

	private void insertaEmpleadoNormal() {
		// TODO Auto-generated method stub
		
		Transaction transaction = null;
		try {
			
			//Crear un empleado y asignarlo a un departamento (normal-manager)
			Session sesion = HibernateUtilNavidad.getSessionFactory().getCurrentSession();
			transaction = sesion.beginTransaction();
			
			Employees empleado = new Employees();
			empleado.setFirstName("Carlos");
			empleado.setLastName("Gómez");
			empleado.setGender('M');
//			sesion.persist(empleado);
			
			Departments departamento = sesion.get(Departments.class, "d004");
			
			DeptEmp nuevaEntradaDepartamento = new DeptEmp();
			nuevaEntradaDepartamento.setDepartamento(departamento);
			nuevaEntradaDepartamento.setEmpleado(empleado);
			sesion.persist(nuevaEntradaDepartamento);
//			
//			//Asignar un titulo a ese empleado
//			
			Titles titulo = new Titles();
			titulo.setEmployees(empleado);
//			sesion.persist(titulo);
//			
			TitlesId claveTitulo = new TitlesId();
			claveTitulo.setFromDate(new Dates().getFechaSQL("1996-06-11"));
			claveTitulo.setTitle("Junior Developer");
			titulo.setId(claveTitulo);
//			sesion.refresh(titulo);
			sesion.persist(titulo);
//			
			//Crear un registro de salario para ese empleado
			Salaries salario = new Salaries();
			salario.setEmpleado(empleado);
			salario.setSalary(2000);
//			sesion.persist(salario);
			
			SalariesId claveSalario = new SalariesId();
			claveSalario.setFromDate(new Dates().getFechaSQL("2025-04-02"));
			salario.setId(claveSalario);
//			sesion.refresh(salario);
			sesion.persist(salario);
			
			//Modificar ese empleado
			//Eliminar ese empleado
			
			transaction.commit();
			sesion.close();
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			if(transaction != null) {
				transaction.rollback();
			}
		}
		
	}
	
	private void insertaEmpleadoManager() {
		// TODO Auto-generated method stub
		Transaction transaction = null;
		try {
			
			//Crear un empleado y asignarlo a un departamento (normal-manager)
			Session sesion = HibernateUtilNavidad.getSessionFactory().getCurrentSession();
			transaction = sesion.beginTransaction();
			
			Employees empleado = new Employees();
			empleado.setFirstName("Manuel");
			empleado.setLastName("Gómez");
			empleado.setGender('M');
//			sesion.persist(empleado);
			
			Departments departamento = sesion.get(Departments.class, "d004");
			
			DeptManager nuevaEntradaDepartamento = new DeptManager();
			nuevaEntradaDepartamento.setDepartamento(departamento);
			nuevaEntradaDepartamento.setEmpleado(empleado);
			sesion.persist(nuevaEntradaDepartamento);
//			
//			//Asignar un titulo a ese empleado
//			
			Titles titulo = new Titles();
			titulo.setEmployees(empleado);
//			sesion.persist(titulo);
//			
			TitlesId claveTitulo = new TitlesId();
			claveTitulo.setFromDate(new Dates().getFechaSQL("1996-06-11"));
			claveTitulo.setTitle("Senior Developer");
			titulo.setId(claveTitulo);
//			sesion.refresh(titulo);
			sesion.persist(titulo);
//			
			//Crear un registro de salario para ese empleado
			Salaries salario = new Salaries();
			salario.setEmpleado(empleado);
			salario.setSalary(2000);
//			sesion.persist(salario);
			
			SalariesId claveSalario = new SalariesId();
			claveSalario.setFromDate(new Dates().getFechaSQL("2025-04-02"));
			salario.setId(claveSalario);
//			sesion.refresh(salario);
			sesion.persist(salario);
			
			//Modificar ese empleado
			//Eliminar ese empleado
			
			transaction.commit();
			sesion.close();
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			if(transaction != null) {
				transaction.rollback();
			}
		}
	}

	private void updateEmpleado(int id) {
		// TODO Auto-generated method stub
		
		Transaction transaction = null;
		try {
			Session sesion = HibernateUtilNavidad.getSessionFactory().getCurrentSession();
			transaction = sesion.beginTransaction();
			
			Employees empleado = sesion.get(Employees.class, id);
			empleado.setHireDate(new Dates().getFechaSQL("2021-02-02"));
			
			Set<Salaries> salarios = empleado.getSalarios();
			
			Salaries nuevoSalario = new Salaries();
			nuevoSalario.setSalary(5000);
			nuevoSalario.getId().setFromDate(new Dates().getFechaSQL("2021-03-02"));
			nuevoSalario.setEmpleado(empleado);
			
			salarios.add(nuevoSalario);
			
			sesion.merge(empleado);
			
			transaction.commit();	
			sesion.close();
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			if(transaction != null) {
				transaction.rollback();
			}
		}
		
	}
	
	private void deleteEmpleado(int id) {
		// TODO Auto-generated method stub
		
		Transaction transaction = null;
		try {
			Session sesion = HibernateUtilNavidad.getSessionFactory().getCurrentSession();
			transaction = sesion.beginTransaction();
			
			Employees empleado =  sesion.get(Employees.class, id);
			sesion.remove(empleado);
			
			transaction.commit();
			sesion.close();
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			if(transaction != null) {
				transaction.rollback();
			}
		}
		
	}

	
}
