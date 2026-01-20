package uce.edu.web.api.matricula.application;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.domain.Materia;
import uce.edu.web.api.matricula.infraestructure.MateriaRepository;

@ApplicationScoped
public class MateriaService {
    
    @Inject
    private MateriaRepository materiaRepository;

    public List<Materia> listarTodos() {
        return this.materiaRepository.listAll();
    }

    public Materia consultarPorId(Integer id) {
        return this.materiaRepository.findById(id.longValue());
    }

    public Materia buscarPorCodigo(String codigo) {
        return this.materiaRepository.findByCodigo(codigo);
    }

    public Materia buscarPorSemestre(String semestre) {
        return this.materiaRepository.findBySemestre(semestre);
    }

    @Transactional
    public void crear(Materia materia) {
        this.materiaRepository.persist(materia);
    }

    @Transactional
    public void actualizar(Integer id, Materia materia) {
        Materia m = this.consultarPorId(id);
        if (m != null) {
            m.setNombre(materia.getNombre());
            m.setSemestre(materia.getSemestre());
            m.setCreditos(materia.getCreditos());
            m.setCodigo(materia.getCodigo());
        }
    }

    @Transactional
    public void actualizarParcial(Integer id, Materia materia) {
        Materia m = this.consultarPorId(id);
        if (m != null) {
            if (materia.getNombre() != null) {
                m.setNombre(materia.getNombre());
            }
            if (materia.getSemestre() != null) {
                m.setSemestre(materia.getSemestre());
            }
            if (materia.getCreditos() != null) {
                m.setCreditos(materia.getCreditos());
            }
            if (materia.getCodigo() != null) {
                m.setCodigo(materia.getCodigo());
            }
        }
    }

    @Transactional
    public void eliminar(Integer id) {
        this.materiaRepository.deleteById(id.longValue());
    }
}
