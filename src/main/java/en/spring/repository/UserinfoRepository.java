package en.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import en.spring.model.Userinfo;


@Repository
public interface UserinfoRepository extends JpaRepository<Userinfo,Long> {

	Userinfo findByAccount(String account);



}
