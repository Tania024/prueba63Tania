package ups.edu.ec.preuba63Tania.business;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import ups.edu.ec.preuba63Tania.dao.DeudasDAO;
import ups.edu.ec.preuba63Tania.model.Deudas;

@Stateless
public class GestionDeudas {
	    @Inject
	    private DeudasDAO deudasDAO;

	    public void guardarDeuda(Deudas deuda) {
	        Deudas deudaExistente = deudasDAO.read(deuda.getCodigo());
	        if (deudaExistente != null) {
	            deudasDAO.update(deuda);
	        } else {
	            deudasDAO.insert(deuda);
	        }
	    }

	    public void actualizarDeuda(Deudas deuda) throws Exception {
	        Deudas deudaExistente = deudasDAO.read(deuda.getCodigo());
	        if (deudaExistente != null) {
	            deudasDAO.update(deuda);
	        } else {
	            throw new Exception("Deuda no existe");
	        }
	    }

	    public Deudas obtenerDeudaPorCodigo(int codigo) throws Exception {
	        Deudas deuda = deudasDAO.read(codigo);
	        if (deuda == null) {
	            throw new Exception("Deuda no encontrada");
	        }
	        return deuda;
	    }

	    public void eliminarDeuda(int codigo) {
	        deudasDAO.remove(codigo);
	    }

	    public List<Deudas> obtenerTodasLasDeudas() {
	        return deudasDAO.getAll();
	    }

}
