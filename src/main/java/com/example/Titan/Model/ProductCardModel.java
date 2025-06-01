package com.example.Titan.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Data;

@Data
@Entity
@Table(name="productCard")
public class ProductCardModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String producttitle;
  private String price;
  private float rating;
  private String productimage;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getproductimage() {
    return productimage;
  }

  public void setProductimage(String productimage) {
    this.productimage = productimage;
  }

  public float getRating() {
    return rating;
  }

  public void setRating(float rating) {
    this.rating = rating;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getProducttitle() {
    return producttitle;
  }

  public void setProducttitle(String producttitle) {
    this.producttitle = producttitle;
  }
}
