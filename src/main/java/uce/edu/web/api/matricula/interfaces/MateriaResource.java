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
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import uce.edu.web.api.matricula.application.MateriaService;
import uce.edu.web.api.matricula.domain.Materia;

@Path("/materias")
public class MateriaResource {

    @Inject
    private MateriaService materiaService;

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodos() {
        return Response.status(Response.Status.OK).entity(this.materiaService.listarTodos()).build();
    }

    @GET
    @Path("/codigo/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorCodigo(@PathParam("codigo") String codigo) {
        Materia materia = this.materiaService.buscarPorCodigo(codigo);
        return Response.status(210).entity(materia).build();
    }

    @GET
    @Path("/semestre/{semestre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorSemestre(@PathParam("semestre") String semestre) {
        Materia materia = this.materiaService.buscarPorSemestre(semestre);
        return Response.status(210).entity(materia).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarPorId(@PathParam("id") Integer id) {
        Materia materia = this.materiaService.consultarPorId(id);
        return Response.status(Response.Status.OK).entity(materia).build();
    }

    @POST
    @Path("")
    public Response guardarMateria(Materia materia) {
        this.materiaService.crear(materia);
        return Response.status(Response.Status.CREATED).entity(materia).build();
    }

    @PUT
    @Path("/{id}")
    public Response actualizarMateria(@PathParam("id") Integer id, Materia materia) {
        this.materiaService.actualizar(id, materia);
        return Response.status(Response.Status.OK).entity(materia).build();
    }

    @PATCH
    @Path("/{id}")
    public Response actualizarMateriaParcial(@PathParam("id") Integer id, Materia materia) {
        this.materiaService.actualizarParcial(id, materia);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    public Response eliminarMateria(@PathParam("id") Integer id) {
        this.materiaService.eliminar(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
