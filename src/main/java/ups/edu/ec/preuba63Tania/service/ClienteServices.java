package ups.edu.ec.preuba63Tania.service;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ups.edu.ec.preuba63Tania.business.GestionCliente;
import ups.edu.ec.preuba63Tania.model.Cliente;
import ups.edu.ec.preuba63Tania.service.ErrorMessage;

@Path("clientes")
public class ClienteServices {
	@Inject
	private GestionCliente gClientes;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(Cliente cliente) {
		try{
			gClientes.guardarClientes(cliente);
			return Response.ok(cliente).build();
		}catch (Exception e) {
			// TODO: handle exception
			ErrorMessage error = new ErrorMessage(99, e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(error)
					.build();
		}
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response actualizar(Cliente cliente) {
		try{
			gClientes.actualizarCliente(cliente);
			return Response.ok(cliente).build();
		}catch (Exception e) {
			// TODO: handle exception
			ErrorMessage error = new ErrorMessage(99, e.getMessage());
			return Response.status(Response.Status.NOT_FOUND)
					.entity(error)
					.build();
		}
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public String borrar(@QueryParam("id") int codigo) {
		try{
			gClientes.borrarCliente(codigo);
			return "OK";
		}catch (Exception e) {
			// TODO: handle exception
			return "Error";
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	//@Produces("application/json")
	public Response leer(@QueryParam("cedula") String cedula, @QueryParam("nombre") String nombre) {
		try{
			System.out.println("cedula " +  cedula + " nom=" + nombre);
			Cliente cli = gClientes.getClientePorCedula(cedula);
			return Response.ok(cli).build();
		}catch (Exception e) {
			// TODO: handle exception
			ErrorMessage error = new ErrorMessage(4, "Cliente no existe");
			return Response.status(Response.Status.NOT_FOUND)
					.entity(error)
					.build();
		}
	}
	
	@GET
	@Path("{cedula}/{nombre}")
	@Produces(MediaType.APPLICATION_JSON)
	//@Produces("application/json")
	public Response leer2(@PathParam("cedula") String cedula, @PathParam("nombre") String nombre) {
		try{
			System.out.println("cedula " +  cedula + " nom=" + nombre);
			Cliente cli = gClientes.getClientePorCedula(cedula);
			return Response.ok(cli).build();
		}catch (Exception e) {
			// TODO: handle exception
			ErrorMessage error = new ErrorMessage(4, "Cliente no existe");
			return Response.status(Response.Status.NOT_FOUND)
					.entity(error)
					.build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("list")
	public Response getClientes(){
		List<Cliente> clientes = gClientes.getClientes();
		if(clientes.size()>0)
			return Response.ok(clientes).build();
		
		ErrorMessage error = new ErrorMessage(6, "No se registran clientes");
		return Response.status(Response.Status.NOT_FOUND)
				.entity(error)
				.build();
		
	}

}
