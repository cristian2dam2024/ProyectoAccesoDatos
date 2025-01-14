package app;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;

import entities.Departamento;
import entities.Empleado;
import entities.EmpleadoDatosProf;
import entities.Proyecto;
import entities.Sede;
import utils.HibernateUtil;

public class ORM_Conexion {
	
	public void run() {
		
//		creaSede();
		modificaSede();
		eliminaSede();
		
		consultaSedes();
//		consultaSedeDinamica();
		
//		consultaSueldoEmpleado();
//		creaEmpleado(); //da error de foreign key
//		creaDatosProfesionales();
		
//		consultaDepartamentosSede();
//		creaSedeYDepartamento(); // relaciones 1-n usando colecciones de objetos
//		creaSedeYDepartamentoCorregido();
		
		//crear la relación de departamento-empleado
		
//		relacionManytoMany();
		
		//CLASE 16-12-24
//		ejemploQuerySelect();
//		ejemploQueryUpdate();
		
	}
	
	private void relacionManytoMany() {
		// TODO Auto-generated method stub
		
		Transaction transaction = null;
		try {
			Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
			transaction = sesion.beginTransaction();
			
			Proyecto nuevoProyecto = new Proyecto("Chemical Reactivity");
			sesion.persist(nuevoProyecto);
			
			Departamento departamento1 = sesion.get(Departamento.class, 23);
			Departamento departamento2 = sesion.get(Departamento.class, 22);
			
			departamento1.getProyectos().add(nuevoProyecto);
			departamento2.getProyectos().add(nuevoProyecto);
			
			sesion.persist(departamento1);
			sesion.persist(departamento2);
			
			transaction.commit();
			
//			sesion.refresh(departamento1);
//			sesion.refresh(departamento2);
			
			sesion.close();
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			if(transaction != null) {
				transaction.rollback();
			}
		}
		
	}

	private void creaSedeYDepartamentoCorregido() {
		// TODO Auto-generated method stub
		
		Transaction transaction = null;
		try {
			Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
			transaction = sesion.beginTransaction();
			
			Sede nuevaSede = new Sede();
			nuevaSede.setNomSede("Oregón");
			sesion.persist(nuevaSede);
			
			Departamento nuevoDepartamento = new Departamento("Geología", nuevaSede);
			sesion.persist(nuevoDepartamento);
			nuevoDepartamento = new Departamento("Biología", nuevaSede);
			sesion.persist(nuevoDepartamento);
			
//			sesion.persist(nuevoDepartamento);
//			sesion.persist(dptoQA);
//			sesion.persist(dptoMarketing);
			
			// refresca la relacion que tenemos en la base de datos

			sesion.refresh(nuevaSede);
		
			if(nuevaSede.getDepartamentos() != null) {
				Iterator<Departamento> itdept =  nuevaSede.getDepartamentos().iterator();
				while(itdept.hasNext()) {
					Departamento item = (Departamento) itdept.next();
					System.out.println(item.getNomDepto());
				}
			}
			
			if(nuevaSede.getDepartamentos() == null) {
				transaction.rollback();
			} else {
				transaction.commit();
			}
				
			sesion.close();
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			if(transaction != null) {
				transaction.rollback();
			}
		}
		
	}
	
	private void creaSedeYDepartamento() {
		// TODO Auto-generated method stub
		
		Transaction transaction = null;
		try {
			Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
			transaction = sesion.beginTransaction();
			
			Sede nuevaSede = new Sede();
			nuevaSede.setNomSede("Chicago");
			
			Departamento dptoQA = new Departamento();
			dptoQA.setNomDepto("QA");
			dptoQA.setSede(nuevaSede);
						
			Departamento dptoMarketing = new Departamento();
			dptoMarketing.setNomDepto("Marketing");
			dptoMarketing.setSede(nuevaSede);
			
			sesion.persist(nuevaSede);
			sesion.persist(dptoQA);
			sesion.persist(dptoMarketing);
			
			// refresca la relacion que tenemos en la base de datos
			sesion.refresh(nuevaSede);
			
			if(nuevaSede.getDepartamentos() != null) {
				Iterator<Departamento> itdept =  nuevaSede.getDepartamentos().iterator();
				while(itdept.hasNext()) {
					Departamento item = (Departamento) itdept.next();
					System.out.println(item.getNomDepto());
				}
			}
			
			if(nuevaSede.getDepartamentos() == null) {
				transaction.rollback();
			} else {
				transaction.commit();
			}
				
			sesion.close();
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			if(transaction != null) {
				transaction.rollback();
			}
		}
		
	}

	private void consultaDepartamentosSede() {
		// TODO Auto-generated method stub
		Transaction transaction = null;
		try {
			Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
			transaction = sesion.beginTransaction();
			
			Sede sede = sesion.get(Sede.class, 1);
			
			for(Departamento depto : sede.getDepartamentos()) {
				System.out.println(depto.getNomDepto());
			}
			
			sesion.close();
		
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			if(transaction != null) {
				transaction.rollback();
			}
		}
	}

	private void creaDatosProfesionales() {
		// TODO Auto-generated method stub
		Transaction transaction = null;
		try {
			Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
			transaction = sesion.beginTransaction();
			
			String dni = "12345678D";
			EmpleadoDatosProf datosProfesionales = new EmpleadoDatosProf();
			datosProfesionales.setDni(dni);
			datosProfesionales.setCategoria("01");
			datosProfesionales.setSueldoBrutoAnual(BigDecimal.valueOf(300000));
			
			Empleado empleado = new Empleado();
			empleado.setDni(dni);
			empleado.setNomEmp("Vicente");
			Departamento departamento = sesion.get(Departamento.class, 1);
			empleado.setIdDepto(departamento.getId());
			
//			datosProfesionales.setEmpleado(empleado);
			
			sesion.persist(datosProfesionales);
			System.out.println("\n\n\n\n\nPERSIST OBJETO\n\n\n\n\n");

			transaction.commit();
			
			System.out.println("Nuevo empleado guardada.");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			if(transaction != null) {
				transaction.rollback();
			}
		}
	}
	
	
	private void creaEmpleado() {
		// TODO Auto-generated method stub
		Transaction transaction = null;
		try {
			Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
			transaction = sesion.beginTransaction();
			
			Empleado empleado = new Empleado();
			String dni = "12345678D";
			empleado.setDni(dni);
			empleado.setNomEmp("Vicente");
			Departamento departamento = sesion.get(Departamento.class, 1);
			empleado.setIdDepto(departamento.getId());
			sesion.save(empleado);
			
			empleado = sesion.find(Empleado.class, dni);
			
			EmpleadoDatosProf datosProfesionales = new EmpleadoDatosProf();
			datosProfesionales.setDni(empleado.getDni());
			datosProfesionales.setCategoria("01");
			datosProfesionales.setSueldoBrutoAnual(BigDecimal.valueOf(300000));
//			datosProfesionales.setEmpleado(empleado);
			sesion.save(datosProfesionales);
			
			empleado.setDatosProfesionales(datosProfesionales);
			sesion.update(empleado);
			System.out.println("\n\n\n\n\nPERSIST OBJETO\n\n\n\n\n");

			transaction.commit();
			
			System.out.println("Nuevo empleado guardada.");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			if(transaction != null) {
				transaction.rollback();
			}
		}
	}


	private void consultaSueldoEmpleado() {
		Transaction transaction = null;
		try {
			Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
			transaction = sesion.beginTransaction();
			
			String dni = "12345678A";
			// FIND Y GET devuelven el mismo resultado
//			Empleado empleado = sesion.find(Empleado.class, dni);
			Empleado empleado = sesion.get(Empleado.class, dni);
			
			System.out.println("Sueldo del empleado: "+ empleado.getDatosProfesionales().getSueldoBrutoAnual());
			

			/*
				buscar datos profesionales y que devuelva empleado
			EmpleadoDatosProf datosProfesionales = sesion.get(EmpleadoDatosProf.class, dni);
			System.out.println("Nombre del empleado: "+ datosProfesionales.getEmpleado().getNomEmp());
			
			//NO FUNCIONA PORQUE NO TENEMOS MAPEADA LA RELACION DESDE LOS DATOS PROFESIONALES,
			// POR LOGICA VAMOS A LOS DATOS PROFESIONALES DESDE EL EMPLEADO, NO AL REVES
			*/
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			if(transaction != null) {
				transaction.rollback();
			}
		}
	}

	private void creaSede() {
		Transaction transaction = null;
		try {
			Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
			transaction = sesion.beginTransaction();
			
			Sede sede = new Sede();
			sede.setNomSede("Nueva York");
			
			System.out.println("\n\n\n\n\nPERSIST OBJETO\n\n\n\n\n");
			
			sesion.persist(sede);
			transaction.commit();
			
			System.out.println("Nueva sede guardada.");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			if(transaction != null) {
				transaction.rollback();
			}
		}
	}
	
	private void modificaSede() {
		Transaction transaction = null;
		try {
			Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
			transaction = sesion.beginTransaction();
			
			Sede sede = sesion.get(Sede.class, 1);
			
			sede.setNomSede("Alabama");
			System.out.println("\n\n\n\n\nPERSIST OBJETO\n\n\n\n\n");
			sesion.persist(sede);
			
			transaction.commit();
			
			System.out.println("Sede modificada con exito.");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			if(transaction != null) {
				transaction.rollback();
			}
		}
	}
	
	private void eliminaSede() {
		Transaction transaction = null;
		try {
			Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
			transaction = sesion.beginTransaction();
			
			Sede sede = sesion.get(Sede.class, 1);
			sesion.remove(sede);
			System.out.println("\n\n\n\n\nPERSIST OBJETO\n\n\n\n\n");
			
			transaction.commit();
			
			System.out.println("Sede eliminada con exito.");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			if(transaction != null) {
				transaction.rollback();
			}
		}
	}
	
	private void consultaSedes() {
		Transaction transaction = null;
		try {
			Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
			transaction = sesion.beginTransaction();
			
			List lista = sesion.createQuery("from Sede").list();
			
			for (Object object : lista) {
				Sede sede = (Sede)object;
				System.out.println(sede.toString());
			}
			
			System.out.println("Sedes consultadas con exito.");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			if(transaction != null) {
				transaction.rollback();
			}
		}
	}
	
	private void consultaSedeDinamica() {
		Transaction transaction = null;
		try {
			Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
			transaction = sesion.beginTransaction();
			
			Query<Sede> query = sesion.createQuery("from Sede where id=:att1", Sede.class);
			query.setParameter("att1", 4);
			
			Sede sede = query.uniqueResult();
			System.out.println(sede.toString());
		
			System.out.println("Sede consultada con exito de forma dinamica.");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			if(transaction != null) {
				transaction.rollback();
			}
		}
	}
	
	//CLASE 16-12-24
	
	private void ejemploQuerySelect() {
		Transaction transaction = null;
		try {
			Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
			transaction = sesion.beginTransaction();
			
			Query<Sede> query = sesion.createQuery("from Sede where nomSede like '%e%'", Sede.class).setReadOnly(true);
			
			List<Sede> listaSede = query.getResultList();
			
			listaSede.forEach(sede ->{
				System.out.println(sede.toString());
			});

//			query.getSingleResult(); //si recibe 0 o varios da error
			
//			Sede sede = query.uniqueResult();
//			System.out.println(sede.toString());
 
			System.out.println("Sede consultada con exito.");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			if(transaction != null) {
				transaction.rollback();
			}
		}
	}
	
	private void ejemploQueryUpdate() {
		Transaction transaction = null;
		try {
			Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
			transaction = sesion.beginTransaction();
			
			MutationQuery query = sesion.createMutationQuery("update Sede set nomSede =:nombre where nomSede = 'Dublin'");
			query.setParameter("nombre", "Arkansas");
			query.executeUpdate();
			System.out.println("Sede actualizada con exito.");
			
			transaction.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			if(transaction != null) {
				transaction.rollback();
			}
		}
	}
		
	
}
