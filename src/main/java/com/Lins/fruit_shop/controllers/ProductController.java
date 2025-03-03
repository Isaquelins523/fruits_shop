package com.Lins.fruit_shop.controllers;

import com.Lins.fruit_shop.domain.Product;
import com.Lins.fruit_shop.domain.ProductRepository;
import com.Lins.fruit_shop.domain.RequestProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping
    public ResponseEntity showAllProducts(){
        var allProducts = repository.findAll();
        return ResponseEntity.ok(allProducts);
    }

    @PostMapping
    public ResponseEntity createProduct(@RequestBody @Validated RequestProduct data){
        var newProduct = new Product(data);
        repository.save(newProduct);

        return ResponseEntity.ok("Product created successfully");
    }

    @PutMapping
    public ResponseEntity changeProduct(@RequestBody @Validated RequestProduct data){
        Optional<Product> optionalProduct = repository.findById(data.id());
        if (optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            product.setName(data.name());
            product.setPrice_in_cents((Integer) data.price_in_cents());

            repository.save(product);

            return ResponseEntity.ok("Product updated successfully");
        }else {
            return ResponseEntity.badRequest().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProductById(@PathVariable("id") String id){
        if (repository.existsById(id)){
            repository.deleteById(id);

            return ResponseEntity.ok("Deleted successfully");
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
