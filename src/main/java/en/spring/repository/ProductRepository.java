package en.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import en.spring.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
