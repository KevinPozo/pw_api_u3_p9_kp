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
import uce.edu.web.api.matricula.application.EstudianteService;
import uce.edu.web.api.matricula.domain.Estudiante;

@Path("/estudiantes")
public class EstudianteResource {
    @Inject
    private EstudianteService estudianteService;

    @GET
    @Path("")
    public List<Estudiante> listarTodos() {
        System.out.println("ALISTAR TODOS XXXXXXXXXXXXX");
        return this.estudianteService.listarTodos();
    }
    @GET
    @Path("/{id}")
    public Estudiante consultarPorId(@PathParam("id") Integer id) {
        return this.estudianteService.consultarPorId(id);
    }

    @POST
    @Path("")
    public void guardarEstudiante(Estudiante estu){
        this.estudianteService.crear(estu);
    }
    @PUT
    @Path("/{id}")
    public void actualizarEstudiante(@PathParam("id") Integer id, Estudiante estu){
        this.estudianteService.actualizar(id, estu);
    }
    @PATCH
    @Path("/{id}")
    public void actualizarEstudianteParcial(@PathParam("id") Integer id, Estudiante estu){
        this.estudianteService.actualizarParcial(id, estu);
    }
    @DELETE
    @Path("/{id}")
    public void eliminarEstudiante(@PathParam("id") Integer id){
        this.estudianteService.eliminar(id);
    }
    @GET
    @Path("/provincia/genero")
    public List<Estudiante> buscarPorProvincia(@QueryParam("provincia") String provincia, @QueryParam("genero") String genero) {
        System.out.println("ALISTAR POR PROVINCIA Y GENERO XXXXXXXXXXXXX");
        return this.estudianteService.buscarPorProvincia(provincia, genero);
    }
}
