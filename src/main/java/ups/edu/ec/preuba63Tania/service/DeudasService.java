package ups.edu.ec.preuba63Tania.service;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ups.edu.ec.preuba63Tania.business.GestionDeudas;
import ups.edu.ec.preuba63Tania.model.Deudas;
import ups.edu.ec.preuba63Tania.service.ErrorMessage;

@Stateless
@Path("deudas")
public class DeudasService {
	@Inject
    private GestionDeudas gDeudas;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(Deudas deuda) {
        try {
            gDeudas.guardarDeuda(deuda);
            return Response.ok(deuda).build();
        } catch (Exception e) {
            ErrorMessage error = new ErrorMessage(99, e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(error)
                    .build();
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizar(Deudas deuda) {
        try {
            gDeudas.actualizarDeuda(deuda);
            return Response.ok(deuda).build();
        } catch (Exception e) {
            ErrorMessage error = new ErrorMessage(99, e.getMessage());
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(error)
                    .build();
        }
    }

    @DELETE
    @Path("/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response borrar(@PathParam("codigo") int codigo) {
        try {
            gDeudas.eliminarDeuda(codigo);
            return Response.ok("OK").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error")
                    .build();
        }
    }

    @GET
    @Path("/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response leer(@PathParam("codigo") int codigo) {
        try {
            Deudas deuda = gDeudas.obtenerDeudaPorCodigo(codigo);
            return Response.ok(deuda).build();
        } catch (Exception e) {
            ErrorMessage error = new ErrorMessage(4, "Deuda no existe");
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(error)
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("list")
    public Response getDeudas() {
        List<Deudas> deudas = gDeudas.obtenerTodasLasDeudas();
        if (deudas.size() > 0)
            return Response.ok(deudas).build();

        ErrorMessage error = new ErrorMessage(6, "No se registran deudas");
        return Response.status(Response.Status.NOT_FOUND)
                .entity(error)
                .build();
    }

}
