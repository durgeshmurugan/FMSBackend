package com.fms.controller;

import java.io.IOException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fms.bean.EMICalculator;
import com.fms.bean.Product;
import com.fms.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@CrossOrigin("http://localhost:3000/")
public class ProductController {

	@Autowired
	private ProductService productService;

//	@PostMapping("/addProducts")
//	public String add(@RequestBody Product product) {
//		String str = "";
//		try {
//			productService.save(product);
//			str = "Product added";
//		} catch (Exception e) {
//			str = "Product add failed";
//		}
//		return str;
//	}

	@PostMapping("/addProduct")
    public boolean addProduct(@RequestParam("productName") String productName,
                              @RequestParam("price") double price,
                              @RequestParam("category") String category,
                              @RequestParam("description") String description,
                              @RequestParam("productImage") MultipartFile productImage) throws IOException {
 
        Product product = new Product();
        product.setProductName(productName);
        product.setPrice(price);
        product.setCategory(category);
        product.setDescription(description);
 
        // Store the image as a byte array
        if (!productImage.isEmpty()) {
            product.setProductImage(productImage.getBytes());
        }
 
        return productService.save(product);
    }
	
	@GetMapping("/getAllProduct")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> product = productService.findAll();
		return ResponseEntity.ok(product);
	}

	@GetMapping("/getProductByCategory/{category}")
	public ResponseEntity<List<Product>> getProducts(@PathVariable String category) {
		List<Product> product = productService.findByCategory(category);
		return ResponseEntity.ok(product);
	}

	@GetMapping("/getProductByName/{productName}")
	public ResponseEntity<Product> getProductsByName(@PathVariable String productName) {
		Product product = productService.findByName(productName);
		return ResponseEntity.ok(product);
	}
	
	@GetMapping("/getProductById/{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable Integer productId) {
		Product product = productService.findById(productId);
		return ResponseEntity.ok(product);
	}

	@DeleteMapping("/deleteProduct/{productId}")
	public void delete(@PathVariable Integer productId) {
		productService.deleteProduct(productId);
	}
	
	@GetMapping("/getProductEMIOptions/{productId}")
    public ResponseEntity<Map<String, Double>> getProductEMIOptions(@PathVariable Integer productId) {
        Product product = productService.findById(productId);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
 
        Map<String, Double> emiOptions = new HashMap<>();
        int[] tenures = {3, 6, 9, 12};
 
        for (int months : tenures) {
            double emi = EMICalculator.calculateEMI(product.getPrice(), months);
            emiOptions.put(months + " Months", emi);
        }
 
        return ResponseEntity.ok(emiOptions);
    }
	
	@PutMapping("/updateproduct")
	public Product update(@RequestParam("productId") Integer productId, @RequestParam("productName") String productName, @RequestParam("price") Double price,
			@RequestParam("category") String category, @RequestParam("description") String description, @RequestParam("image") MultipartFile image) throws IOException {
		
		Product product = new Product();
		product.setProductId(productId)
		product.setProductName(productName);
		product.setPrice(price);
		product.setCategory(category);
		product.setDescription(description);
		product.setProductImage(image.getBytes());
		return productService.update(product, image);
	}
}
