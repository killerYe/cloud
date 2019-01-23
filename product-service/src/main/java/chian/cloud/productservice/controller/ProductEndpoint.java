package chian.cloud.productservice.controller;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import chian.cloud.productservice.entity.Comment;
import chian.cloud.productservice.entity.CommentDto;
import chian.cloud.productservice.entity.Product;
import chian.cloud.productservice.entity.UserDto;
import chian.cloud.productservice.repository.CommentRepository;
import chian.cloud.productservice.repository.ProductRepository;
import chian.cloud.productservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/products")
public class ProductEndpoint {

	private static Logger logger = LoggerFactory.getLogger(Product.class);

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
//	@Autowired
//	private RestTemplate restTemplate;

//	@Autowired
//	private LoadBalancerClient loadBalancerClient;

	@Autowired
	private UserService userService;

	@GetMapping
	public List<Product> products(){
		return productRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Product detail(@PathVariable("id") int id) {
		return productRepository.findById(id);
	}
	
	@GetMapping("/{id}/comments")
	public List<CommentDto> comments(@PathVariable("id") int id){
		List<Comment> comments = commentRepository.findByProductIdOrderByCreated(id);
		if(comments == null || comments.isEmpty()) {
			return Collections.emptyList();
		}else {
			return comments.stream().map((comment) -> {
				CommentDto dto = new CommentDto();
				dto.setAuthor(userService.getUser(comment.getAuthorId()));
//				dto.setAuthor(this.loadUser(comment.getAuthorId()));
				dto.setComment(comment);
				return dto;
			}).collect(Collectors.toList());
		}
	}

	@GetMapping("/users")
	public List<UserDto> users(){
		return userService.users();
	}
//	protected UserDto loadUser(int user_id) {
//		UserDto dto = this.restTemplate.getForEntity("http://USERSERVICE/users/{id}", UserDto.class,user_id).getBody();
//		return dto;
//		ServiceInstance instance = this.loadBalancerClient.choose("USERSERVICE");
//		URI userUri = URI.create(String.format("http://%s:%s/users/%s", instance.getHost(), instance.getPort(),user_id));
//		logger.info("product_end_point",userUri.toString());
//		logger.info("product_end_point",user_id);
//		UserDto dto = this.restTemplate.getForEntity(userUri.toString(),UserDto.class).getBody();
//		return dto;
//	}
	
}
