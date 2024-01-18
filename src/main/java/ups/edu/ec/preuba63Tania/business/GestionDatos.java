package ups.edu.ec.preuba63Tania.business;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;
import ups.edu.ec.preuba63Tania.dao.ClienteDAO;
import ups.edu.ec.preuba63Tania.dao.DeudasDAO;
import ups.edu.ec.preuba63Tania.model.Cliente;
import ups.edu.ec.preuba63Tania.model.Deudas;

@Singleton
@Startup
public class GestionDatos {
	@Inject
	private ClienteDAO daoCliente;
	
	@Inject
	private DeudasDAO daoDeudas;
	
	@PostConstruct
	public void init() {
		System.out.println("iniciando");
		
		Cliente cliente = new Cliente();
		cliente.setCodigo(1);
		cliente.setCedula("0106717671");
		cliente.setNombre("Tania Lojano");
		
		daoCliente.insert(cliente);
		
		cliente = new Cliente();
		cliente.setCodigo(2);
		cliente.setCedula("0106717689");
		cliente.setNombre("Magali Lojano");
		
		daoCliente.insert(cliente);
		
		Deudas deudas = new Deudas();
		deudas.setCodigo(1);
		deudas.setMes("Marzo");
		deudas.setPrecio(13.24);
		
		daoDeudas.insert(deudas);
		
		deudas = new Deudas();
		deudas.setCodigo(2);
		deudas.setMes("Agosto");
		deudas.setPrecio(10.12);
		
		daoDeudas.insert(deudas);
	}

}
