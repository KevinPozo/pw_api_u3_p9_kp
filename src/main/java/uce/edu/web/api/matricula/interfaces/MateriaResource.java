package uce.edu.web.api.matricula.interfaces;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import uce.edu.web.api.matricula.application.MateriaService;
import uce.edu.web.api.matricula.domain.Materia;

@Path("/materias")
public class MateriaResource {

    @Inject
    private MateriaService materiaService;

    @GET
    @Path("")
    public List<Materia> listarTodos(@QueryParam("codigo") String codigo, @QueryParam("semestre") String semestre) {
        if (codigo != null && !codigo.isEmpty()) {
            Materia m = this.materiaService.buscarPorCodigo(codigo);
            return m != null ? List.of(m) : List.of();
        }
        if (semestre != null && !semestre.isEmpty()) {
            Materia m = this.materiaService.buscarPorSemestre(semestre);
            return m != null ? List.of(m) : List.of();
        }
        return this.materiaService.listarTodos();
    }

    @GET
    @Path("/{id}")
    public Materia consultarPorId(@PathParam("id") Integer id) {
        return this.materiaService.consultarPorId(id);
    }

    @POST
    @Path("")
    public void guardarMateria(Materia materia) {
        this.materiaService.crear(materia);
    }

    @PUT
    @Path("/{id}")
    public void actualizarMateria(@PathParam("id") Integer id, Materia materia) {
        this.materiaService.actualizar(id, materia);
    }

    @PATCH
    @Path("/{id}")
    public void actualizarMateriaParcial(@PathParam("id") Integer id, Materia materia) {
        this.materiaService.actualizarParcial(id, materia);
    }

    @DELETE
    @Path("/{id}")
    public void eliminarMateria(@PathParam("id") Integer id) {
        this.materiaService.eliminar(id);
    }
}
