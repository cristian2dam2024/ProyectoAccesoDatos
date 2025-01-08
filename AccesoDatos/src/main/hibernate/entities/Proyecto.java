package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "proyecto")
public class Proyecto {
    public Proyecto() {
		super();
	}



	public Proyecto(String nomProy) {
		super();
		this.nomProy = nomProy;
	}

	@Id
    @Column(name = "id_proy", nullable = false)
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "f_inicio")
    private LocalDate fInicio;

    @Column(name = "f_fin")
    private LocalDate fFin;

    @Column(name = "nom_proy", nullable = false, length = 20)
    private String nomProy;
    
    @ManyToMany(mappedBy = "proyectos")
    private Set<Departamento> departamentos;
    
    public Set<Departamento> getDepartamentos() {
		return departamentos;
	}
    
    

	public void setDepartamentos(Set<Departamento> departamentos) {
		this.departamentos = departamentos;
	}

    public LocalDate getfInicio() {
		return fInicio;
	}

	public void setfInicio(LocalDate fInicio) {
		this.fInicio = fInicio;
	}

	public LocalDate getfFin() {
		return fFin;
	}

	public void setfFin(LocalDate fFin) {
		this.fFin = fFin;
	}

	

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getFInicio() {
        return fInicio;
    }

    public void setFInicio(LocalDate fInicio) {
        this.fInicio = fInicio;
    }

    public LocalDate getFFin() {
        return fFin;
    }

    public void setFFin(LocalDate fFin) {
        this.fFin = fFin;
    }

    public String getNomProy() {
        return nomProy;
    }

    public void setNomProy(String nomProy) {
        this.nomProy = nomProy;
    }
    
    
    

}