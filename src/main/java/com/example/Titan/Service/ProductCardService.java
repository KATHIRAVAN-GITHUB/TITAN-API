package com.example.Titan.Service;

import com.example.Titan.Model.ProductCardModel;
import com.example.Titan.Repository.ProductCardRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;


@Service
public class ProductCardService {
  @Autowired
  private ProductCardRepository productCardRepository;

  public ProductCardModel saveProduct(ProductCardModel product) {
    return productCardRepository.save(product);
  }

  public List<ProductCardModel> getAllProducts() {
    System.out.println("Fetching all products from database...");
    return productCardRepository.findAll();
  }

  public ProductCardModel getProductById(Long id) {
    System.out.println("Fetching from database...");
    return productCardRepository.findById(id).orElse(null);
  }
}

