package com.fms.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fms.bean.Product;

public interface ProductService {
	
	Product findById(Integer productId);
	 
    boolean save(Product product);
        
    void deleteProduct(Integer productId);
 
    List<Product> findAll();
 
    Product findByName(String productName);
 
    List<Product> findByCategory(String category);

	Product update(Product product, MultipartFile image) throws IOException;

}
