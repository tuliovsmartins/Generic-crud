package xyinc.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import xyinc.Entity.Order;


@Repository
@Transactional
public class OrderDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void create(Order order) {

		entityManager.merge(order);
		return;
	}
	
	@SuppressWarnings("unchecked")
	public List<Order> getAll() {
		return entityManager.createQuery("from orderProducts").getResultList();
	}
	
	public Order getById(long id) {
		return entityManager.find(Order.class, id);
	}

}
