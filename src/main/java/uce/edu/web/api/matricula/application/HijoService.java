package uce.edu.web.api.matricula.application;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.api.matricula.application.representation.HijoRepresentation;
import uce.edu.web.api.matricula.domain.Hijo;
import uce.edu.web.api.matricula.infraestructure.HijoRepository;

@ApplicationScoped
public class HijoService {
    @Inject
    private HijoRepository hijoRepository;

    public List<HijoRepresentation> buscarPorIdEstudiante(Integer idEstudiante) {
        return this.hijoRepository.buscarPorIdEstudiante(idEstudiante).stream()
                .map(this::mapperTo)
                .toList();
    }

    private HijoRepresentation mapperTo(Hijo hijo) {
        HijoRepresentation representation = new HijoRepresentation();
        representation.setId(hijo.getId());
        representation.setNombre(hijo.getNombre());
        representation.setApellido(hijo.getApellido());
        representation.setFechaNacimiento(hijo.getFechaNacimiento());
        representation.setProvincia(hijo.getProvincia());
        representation.setGenero(hijo.getGenero());
        return representation;
    }
}
