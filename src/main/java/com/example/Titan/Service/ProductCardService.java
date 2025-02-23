package com.example.Titan.Service;

import com.example.Titan.Model.ProductCardModel;
import com.example.Titan.Repository.ProductCardRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductCardService {
  @Autowired
  private ProductCardRepository productCardRepository;

  public List<ProductCardModel> getAllProducts() {
    return productCardRepository.findAll();
  }

  public ProductCardModel getProductById(Long id) {
    return productCardRepository.findById(id).orElse(null);
  }



}

