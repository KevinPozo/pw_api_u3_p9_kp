package uce.edu.web.api.matricula.infraestructure;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import uce.edu.web.api.matricula.domain.Materia;

@ApplicationScoped
public class MateriaRepository implements PanacheRepository<Materia> {

    public Materia findByCodigo(String codigo) {
        return find("SELECT m FROM Materia m WHERE m.codigo = ?1", codigo).firstResult();
    }

    public Materia findBySemestre(String semestre) {
        return find("SELECT m FROM Materia m WHERE m.semestre = ?1", semestre).firstResult();
    }

}
