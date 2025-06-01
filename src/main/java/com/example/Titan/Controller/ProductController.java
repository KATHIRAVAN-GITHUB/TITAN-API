package com.example.Titan.Controller;

import com.example.Titan.Model.ProductCardModel;
import com.example.Titan.Repository.ProductCardRepository;
import com.example.Titan.Service.ProductCardService;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

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

  private static final String UPLOAD_DIR = "uploads/";

  @PostMapping("/add")
  public ProductCardModel addProduct(
      @RequestParam("productTitle") String productTitle,
      @RequestParam("price") String price,
      @RequestParam("image") MultipartFile file) throws IOException {

    // Ensure directory exists
    File uploadDir = new File(UPLOAD_DIR);
    if (!uploadDir.exists()) uploadDir.mkdirs();

    // Generate unique filename
    String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
    String filePath = UPLOAD_DIR + fileName;
    file.transferTo(new File(filePath));

    // Save product details
    ProductCardModel product = new ProductCardModel();
    product.setProducttitle(productTitle);
    product.setPrice(price);
    product.setRating(4.0f);
    product.setProductimage(fileName); // Store filename in DB

    return productCardService.saveProduct(product);
  }

}
