package en.spring.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import en.spring.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
	Customer findByAccount(String account);
	Customer findByEmail(String email);
}
