package com.fms.dao;

import java.util.List;

import com.fms.bean.Product;

public interface ProductDAO {
	
	public Product findById(Integer productId);

    public boolean save(Product product);
    
    public Product update(Product product);

    public void delete(Product product);

    public List<Product> findAll();

    public Product findByName(String productName);
    
    public List<Product> findByCategory(String category);

}
