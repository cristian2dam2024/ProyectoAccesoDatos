package entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "sede")
public class Sede {
    @Id
    @Column(name = "id_sede", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nom_sede", nullable = false, length = 40)
    private String nomSede;
    
//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name="id_sede")
//    private Set<Departamento> departamentos;
    
    @OneToMany(mappedBy = "sede") //poner el nombre de la variable privada de la clase departamento
    	//esto es una relacion bidireccional, este lado se mapea con el ManytoOne de la clase departamento
    	//se suele mapear "JoinColumn" en la parte ManytoOne y en el otro poner el mappedby
    private Set<Departamento> departamentos;
    
    public Set<Departamento> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(Set<Departamento> departamentos) {
		this.departamentos = departamentos;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomSede() {
        return nomSede;
    }

    public void setNomSede(String nomSede) {
        this.nomSede = nomSede;
    }

	@Override
	public String toString() {
		return "Sede [id=" + id + ", nomSede=" + nomSede + "]";
	}
    
    

}