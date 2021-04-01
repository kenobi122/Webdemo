package en.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import en.spring.model.Cartmodel;

@Repository
public interface CartmodelRepository extends JpaRepository<Cartmodel,Long> {
	 
}
