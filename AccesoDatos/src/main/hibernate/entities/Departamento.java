package entities;

import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "departamento")
public class Departamento {

	@Id
    @Column(name = "id_depto", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nom_depto", nullable = false, length = 32)
    private String nomDepto;
  
    //ManytoOne y mappedby define una relación bidireccional entre las clases
    @JoinColumn(name="id_sede")
    @ManyToOne(fetch = FetchType.EAGER)
    private Sede sede;
    
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
    		name ="departamento_proyectos",
    		joinColumns = {@JoinColumn(name ="id_depto")},
    		inverseJoinColumns = {@JoinColumn(name="id_proy")})
    private Set<Proyecto> proyectos;
    

    public Set<Proyecto> getProyectos() {
		return proyectos;
	}

	public void setProyectos(Set<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomDepto() {
        return nomDepto;
    }

    public void setNomDepto(String nomDepto) {
        this.nomDepto = nomDepto;
    }

	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}
	
	public Departamento() {
		super();
	}
	
	//como incluimos el constructor con parametros necesitamos hacer el constructor vacío
	public Departamento(String nomDepto, Sede sede) {
		super();
		this.nomDepto = nomDepto;
		this.sede = sede;
	}


}