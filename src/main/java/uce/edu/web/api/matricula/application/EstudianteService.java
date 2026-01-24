package uce.edu.web.api.matricula.application;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.application.representation.EstudianteRepresentation;
import uce.edu.web.api.matricula.domain.Estudiante;
import uce.edu.web.api.matricula.infraestructure.EstudianteRepository;

@ApplicationScoped
public class EstudianteService {
    @Inject
    private EstudianteRepository estudianteRepository;

    public List<EstudianteRepresentation> listarTodos() {
        return this.estudianteRepository.listAll().stream()
                .map(this::mapperToRepresentation)
                .collect(Collectors.toList());
    }

    public EstudianteRepresentation consultarPorId(Integer id) {
        return this.mapperToRepresentation(this.estudianteRepository.findById(id.longValue()));
    }

    @Transactional
    public EstudianteRepresentation crear(EstudianteRepresentation estuRep) {
        Estudiante estu = mapperToEstudiante(estuRep);
        this.estudianteRepository.persist(estu);
        return mapperToRepresentation(estu);
    }

    @Transactional
    public EstudianteRepresentation actualizar(Integer id, EstudianteRepresentation estRep) {
        Estudiante estu = this.estudianteRepository.findById(id.longValue());
        if (estu == null) {
            return null;
        }
        estu.setApellido(estRep.getApellido());
        estu.setNombre(estRep.getNombre());
        estu.setFechaNacimiento(estRep.getFechaNacimiento());
        estu.setProvincia(estRep.getProvincia());
        estu.setGenero(estRep.getGenero());
        return mapperToRepresentation(estu);
    }

    @Transactional
    public void actualizarParcial(Integer id, EstudianteRepresentation estRep) {
        Estudiante estu = this.estudianteRepository.findById(id.longValue());
        if (estu != null) {
            if (estRep.getNombre() != null) {
                estu.setNombre(estRep.getNombre());
            }
            if (estRep.getApellido() != null) {
                estu.setApellido(estRep.getApellido());
            }
            if (estRep.getFechaNacimiento() != null) {
                estu.setFechaNacimiento(estRep.getFechaNacimiento());
            }
        }
    }

    @Transactional
    public void eliminar(Integer id) {
        this.estudianteRepository.deleteById(id.longValue());
    }

    public List<EstudianteRepresentation> buscarPorProvincia(String provincia, String genero) {
        return this.estudianteRepository.find("provincia = ?1 and genero = ?2", provincia, genero)
                .list().stream()
                .map(obj -> mapperToRepresentation((Estudiante) obj))
                .collect(Collectors.toList());
    }

    private EstudianteRepresentation mapperToRepresentation(Estudiante estudiante) {
        if (estudiante == null) {
            return null;
        }
        EstudianteRepresentation representation = new EstudianteRepresentation();
        representation.setId(estudiante.getId());
        representation.setNombre(estudiante.getNombre());
        representation.setApellido(estudiante.getApellido());
        representation.setFechaNacimiento(estudiante.getFechaNacimiento());
        representation.setProvincia(estudiante.getProvincia());
        representation.setGenero(estudiante.getGenero());
        return representation;
    }

    private Estudiante mapperToEstudiante(EstudianteRepresentation representation) {
        Estudiante estudiante = new Estudiante();
        estudiante.setNombre(representation.getNombre());
        estudiante.setApellido(representation.getApellido());
        estudiante.setFechaNacimiento(representation.getFechaNacimiento());
        estudiante.setProvincia(representation.getProvincia());
        estudiante.setGenero(representation.getGenero());
        return estudiante;
    }

}
