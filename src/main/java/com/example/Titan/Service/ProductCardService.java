package com.example.Titan.Service;

import com.example.Titan.Model.ProductCardModel;
import com.example.Titan.Repository.ProductCardRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Service
public class ProductCardService {
  @Autowired
  private ProductCardRepository productCardRepository;

  @CachePut(value = "products", key = "#product.id")
  public ProductCardModel saveProduct(ProductCardModel product) {
    return productCardRepository.save(product);
  }

  @Cacheable(value = "allProducts")
  public List<ProductCardModel> getAllProducts() {
    System.out.println("Fetching all products from database...");
    return productCardRepository.findAll();
  }

  @Cacheable(value = "products", key = "#id")
  public ProductCardModel getProductById(Long id) {
    System.out.println("Fetching from database...");
    return productCardRepository.findById(id).orElse(null);
  }

  private final RestTemplate restTemplate;
  private final DataSource dataSource;

  public ProductCardService(RestTemplate restTemplate, DataSource dataSource) {
    this.restTemplate = restTemplate;
    this.dataSource = dataSource;


  }

  // Keep Render API alive every 4 minutes
  @Scheduled(fixedRate = 240000)  // 240,000 ms = 4 minutes
  public void pingRender() {
    String apiUrl = "https://titan-api-ffre.onrender.com";
    try {
      restTemplate.getForObject(apiUrl, String.class);
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  // Keep NeonDB active every 4 minutes
  @Scheduled(fixedRate = 240000)  // 4 minutes
  public void pingDatabase() {
    try (Connection connection = dataSource.getConnection()) {
      connection.prepareStatement("SELECT 1").execute();
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
  }


}

