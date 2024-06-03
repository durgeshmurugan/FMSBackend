package com.fms.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.fms.bean.Product;
import com.fms.dao.ProductDAO;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDAO prodcutDAO;

	@Override
	public Product findById(Integer productId) {
		return prodcutDAO.findById(productId);
	}

	@Override
	public boolean save(Product product) {
		return prodcutDAO.save(product);
	}

	@Override
	public void deleteProduct(Integer productId) {
		Product product = prodcutDAO.findById(productId);
		if (product != null) {
			prodcutDAO.delete(product);
		}
	}

	@Override
	public List<Product> findAll() {
		return prodcutDAO.findAll();
	}

	@Override
	public Product findByName(String productName) {
		return prodcutDAO.findByName(productName);
	}

	@Override
	public List<Product> findByCategory(String category) {
		return prodcutDAO.findByCategory(category);
	}

	@Override
	public Product update(Product product, MultipartFile image) throws IOException {
		
		Product products = new Product();
		products.setProductName(product.getProductName());
		products.setPrice(product.getPrice());
		products.setCategory(product.getCategory());
		products.setDescription(product.getDescription());
		products.setProductImage(image.getBytes());
		
		return prodcutDAO.update(products);
	}

}
