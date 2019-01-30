package chain.cloud.userservice.repository;

import java.util.List;

import chain.cloud.userservice.entity.User;
import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	List<User> findAll();
	
	User findById(int id);

	User save(User user);

	int deleteById(int id);



}
