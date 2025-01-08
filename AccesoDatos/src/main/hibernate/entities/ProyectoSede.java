package entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "proyecto_sede")
public class ProyectoSede {
    @EmbeddedId
    private ProyectoSedeId id;

    @Column (name ="id_proy", insertable=false, updatable=false)
    private int idProy;
    
    @Column (name="id_sede", insertable=false, updatable=false)
    private int idSede;

    @Column(name = "f_inicio", nullable = false)
    private LocalDate fInicio;

    @Column(name = "f_fin")
    private LocalDate fFin;

    public ProyectoSedeId getId() {
        return id;
    }

    public void setId(ProyectoSedeId id) {
        this.id = id;
    }

    public int getIdProy() {
        return idProy;
    }

    public void setIdProy(int idProy) {
        this.idProy = idProy;
    }

    public int getIdSede() {
        return idSede;
    }

    public void setIdSede(int idSede) {
        this.idSede = idSede;
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

}