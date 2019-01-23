package chian.cloud.productservice.repository;

import java.util.List;

import chian.cloud.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	List<Product> findAll();
	Product findById(int id);
	
}
