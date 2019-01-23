package chian.cloud.productservice.service;

import java.util.List;

import chian.cloud.productservice.entity.UserDto;
import chian.cloud.productservice.service.impl.UserServiceFallback;
import chian.cloud.productservice.service.impl.UserServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@Component
//@FeignClient(name = "USERSERVICE",fallback = UserServiceFallback.class)
public interface UserService {
//	@GetMapping("/users")
	List<UserDto> users();

//	@GetMapping("/users/{id}")
//	UserDto getUser(@PathVariable("id") int id);
	UserDto getUser(int id);
	
}