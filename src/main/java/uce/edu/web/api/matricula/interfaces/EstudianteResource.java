package uce.edu.web.api.matricula.interfaces;

import java.util.List;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.Consumes;

import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.matricula.application.EstudianteService;
import uce.edu.web.api.matricula.application.HijoService;
import uce.edu.web.api.matricula.application.representation.EstudianteRepresentation;
import uce.edu.web.api.matricula.application.representation.HijoRepresentation;
import uce.edu.web.api.matricula.application.representation.LinkDto;

@Path("/estudiantes")
public class EstudianteResource {
    @Inject
    private EstudianteService estudianteService;
    @Inject
    private HijoService hijoService;
    @Context
    private UriInfo uriInfo;

    @GET
    @Path("")
    @RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    public List<EstudianteRepresentation> listarTodos() {
        System.out.println("ALISTAR TODOS XXXXXXXXXXXXX");
        return this.estudianteService.listarTodos().stream()
                .map(this::construirLinks)
                .toList();
    }

    @GET
    @Path("/{id}")
    // @PermitAll
    @RolesAllowed("admin")
    public EstudianteRepresentation consultarPorId(@PathParam("id") Integer id) {
        EstudianteRepresentation er = this.estudianteService.consultarPorId(id);
        if (er == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return this.construirLinks(er);
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public Response guardarEstudiante(EstudianteRepresentation estu) {
        EstudianteRepresentation creado = this.estudianteService.crear(estu);
        return Response.status(Response.Status.CREATED).entity(creado).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public Response actualizarEstudiante(@PathParam("id") Integer id, EstudianteRepresentation estu) {
        EstudianteRepresentation actualizado = this.estudianteService.actualizar(id, estu);
        return Response.status(209).entity(actualizado).build();
    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public void actualizarEstudianteParcial(@PathParam("id") Integer id, EstudianteRepresentation estu) {
        this.estudianteService.actualizarParcial(id, estu);

    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("admin")
    public void eliminarEstudiante(@PathParam("id") Integer id) {
        this.estudianteService.eliminar(id);
    }

    @GET
    @Path("/provincia/genero")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public List<EstudianteRepresentation> buscarPorProvincia(@QueryParam("provincia") String provincia,
            @QueryParam("genero") String genero) {
        System.out.println("ALISTAR POR PROVINCIA Y GENERO XXXXXXXXXXXXX");
        return this.estudianteService.buscarPorProvincia(provincia, genero);
    }

    @GET
    @Path("/{id}/hijos")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public List<HijoRepresentation> buscarPorIdEstudiante(@PathParam("id") Integer id) {
        return this.hijoService.buscarPorIdEstudiante(id);
    }

    private EstudianteRepresentation construirLinks(EstudianteRepresentation er) {
        String self = this.uriInfo.getBaseUriBuilder().path(EstudianteResource.class)
                .path(String.valueOf(er.getId())).build().toString();
        String hijos = this.uriInfo.getBaseUriBuilder().path(EstudianteResource.class)
                .path(String.valueOf(er.getId())).path("/hijos").build().toString();
        er.setLinks(List.of(new LinkDto(self, "self"), new LinkDto(hijos, "hijos")));
        return er;
    }
}
