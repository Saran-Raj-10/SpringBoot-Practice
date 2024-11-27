package com.sandhyaproject.ecommerce.repositories;

 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sandhyaproject.ecommerce.models.Product;
 
@Repository
public interface ProductRepository extends JpaRepository<Product,Long>{
	
	

}
