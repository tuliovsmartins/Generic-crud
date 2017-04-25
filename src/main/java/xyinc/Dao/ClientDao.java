package xyinc.Dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import xyinc.Entity.Client;


@Repository
@Transactional
public class ClientDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	
	public void create(Client client) {

		entityManager.merge(client);
		return;
	}
	
	@SuppressWarnings("unchecked")
	public List<Client> getAll() {
		return entityManager.createQuery("from Client").getResultList();
	}
	
	public Client getById(long id) {
		return entityManager.find(Client.class, id);
	}
	
	public void deleteById(Client client) {
		entityManager.remove(entityManager.merge(client));
	}
}
