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
import uce.edu.web.api.matricula.application.MateriaService;
import uce.edu.web.api.matricula.domain.Materia;

@Path("/materias")
public class MateriaResource {

    @Inject
    private MateriaService materiaService;

    @GET
    @Path("/todos")
    public List<Materia> listarTodos() {
        return this.materiaService.listarTodos();
    }

    @GET
    @Path("/consultarPorId/{id}")
    public Materia consultarPorId(@PathParam("id") Integer id) {
        return this.materiaService.consultarPorId(id);
    }

    @GET
    @Path("/buscar/codigo/{codigo}")
    public Materia buscarPorCodigo(@PathParam("codigo") String codigo) {
        return this.materiaService.buscarPorCodigo(codigo);
    }

    @GET
    @Path("/buscar/semestre/{semestre}")
    public Materia buscarPorSemestre(@PathParam("semestre") String semestre) {
        return this.materiaService.buscarPorSemestre(semestre);
    }

    @POST
    @Path("/guardar")
    public void guardarMateria(Materia materia) {
        this.materiaService.crear(materia);
    }

    @PUT
    @Path("/actualizar/{id}")
    public void actualizarMateria(@PathParam("id") Integer id, Materia materia) {
        this.materiaService.actualizar(id, materia);
    }

    @PATCH
    @Path("/actualizarParcial/{id}")
    public void actualizarMateriaParcial(@PathParam("id") Integer id, Materia materia) {
        this.materiaService.actualizarParcial(id, materia);
    }

    @DELETE
    @Path("/eliminar/{id}")
    public void eliminarMateria(@PathParam("id") Integer id) {
        this.materiaService.eliminar(id);
    }
}
