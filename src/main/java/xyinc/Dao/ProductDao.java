package xyinc.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import xyinc.Entity.Client;
import xyinc.Entity.Product;

@Repository
@Transactional
public class ProductDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void create(Product product) {

		entityManager.merge(product);
		return;
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> getAll() {
		return entityManager.createQuery("from Product").getResultList();
	}
	
	public Product getById(long id) {
		return entityManager.find(Product.class, id);
	}
	
	public void deleteById(Product product) {
		entityManager.remove(entityManager.merge(product));
	}

}
