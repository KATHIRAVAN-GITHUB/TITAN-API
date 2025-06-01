package com.example.Titan.Repository;

import com.example.Titan.Model.ProductCardModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCardRepository extends JpaRepository<ProductCardModel, Long> {

}
