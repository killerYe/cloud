package chian.cloud.productservice.repository;

import java.util.List;

import chian.cloud.productservice.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
	
	List<Comment> findByProductIdOrderByCreated(int id);
	
}
