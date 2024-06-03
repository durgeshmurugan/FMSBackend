package com.fms.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fms.bean.Product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private EntityManager em;

	@Override
	public Product findById(Integer productId) {
		return em.find(Product.class, productId);
	}

	@Override
	public boolean save(Product product) {
		if (product.getProductId() == null) {
			em.persist(product);
			return true;
		} else {
			em.merge(product);
		}
		return false;
	}

	@Override
	public void delete(Product product) {
		em.remove(product);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findAll() {
		return em.createQuery("SELECT p FROM Product p").getResultList();
	}

	@Override
	public Product findByName(String productName) {
		try {
			TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p WHERE p.productName = :name",
					Product.class);
			query.setParameter("name", productName);
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Product> findByCategory(String category) {
		try {
			TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p WHERE p.category = :category",
					Product.class);
			query.setParameter("category", category);
			return query.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Product update(Product product) {
		return em.merge(product);
	}

}
