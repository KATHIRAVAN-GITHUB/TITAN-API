package com.example.Titan.Controller;

import com.example.Titan.Model.ProductCardModel;
import com.example.Titan.Repository.ProductCardRepository;
import com.example.Titan.Service.ProductCardService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins ="*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RestController
public class ProductController {

  @Autowired
  ProductCardService productCardService;

  @PostMapping("/GetCard")
  public List<ProductCardModel> getProducts(){
    return productCardService.getAllProducts();
  }

  @GetMapping("/GetCardByid/{id}")
  public ResponseEntity<ProductCardModel> getProductById(@PathVariable Long id) {
    ProductCardModel product = productCardService.getProductById(id);
    if (product != null) {
      return ResponseEntity.ok(product);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

}
