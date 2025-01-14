package entities;

import jakarta.persistence.*;

@Entity
@Table(name = "empleado")
public class Empleado {
    @Id
    @Column(name = "dni", nullable = false, length = 9)
    private String dni;

    @Column(name = "nom_emp", nullable = false, length = 40)
    private String nomEmp;

    @Column(name="id_depto")
    private int idDepto;
    
    //AGREGAMOS LOS DATOS PROFESIONALES AL OBJETO JUNTO CON GETTERS Y SETTERS
    //relación unidireccional, los datos profesionales se acceden a través del empleado
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dni", referencedColumnName = "dni")
    private EmpleadoDatosProf datosProfesionales;
    

    public EmpleadoDatosProf getDatosProfesionales() {
		return datosProfesionales;
	}

	public void setDatosProfesionales(EmpleadoDatosProf datosProfesionales) {
		this.datosProfesionales = datosProfesionales;
	}

	public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNomEmp() {
        return nomEmp;
    }

    public void setNomEmp(String nomEmp) {
        this.nomEmp = nomEmp;
    }

    public int getIdDepto() {
        return idDepto;
    }

    public void setIdDepto(int idDepto) {
        this.idDepto = idDepto;
    }

}