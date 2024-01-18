package ups.edu.ec.preuba63Tania.dao;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import ups.edu.ec.preuba63Tania.model.Deudas;

@Stateless
public class DeudasDAO {
	@PersistenceContext
	private EntityManager em;
	
	public void insert(Deudas deudas) {
		em.persist(deudas);
	}
	
	public void update(Deudas deudas) {
		em.merge(deudas);
	}
	
	public void remove(int codigo) {
		Deudas deudas = em.find(Deudas.class, codigo);
		em.refresh(deudas);
	}
	
	public Deudas read(int codigo) {
		Deudas deudas = em.find(Deudas.class, codigo);
		return deudas;
	}
	
	public List<Deudas> getAll(){
		String jpql = "SELECT c FROM Deudas c";
		Query q = em.createQuery(jpql, Deudas.class);
		return q.getResultList();
	}

}
