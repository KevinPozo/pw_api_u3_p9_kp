package uce.edu.web.api.matricula.domain;

import java.time.LocalDateTime;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.FetchType;

@Entity
@Table(name = "hijo")
@SequenceGenerator(name = "hijo_seq", sequenceName = "hijo_seq", allocationSize = 1)
public class Hijo extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hijo_seq")
    public Integer id;
    public String nombre;
    public String apellido;
    public LocalDateTime fechaNacimiento;
    public String provincia;
    public String genero;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estudiante_id")
    @JsonbTransient
    public Estudiante estudiante;
}
