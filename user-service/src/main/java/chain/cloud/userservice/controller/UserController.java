package chain.cloud.userservice.controller;

import java.util.List;
import java.util.stream.Collectors;

import chain.cloud.userservice.entity.User;
import chain.cloud.userservice.entity.UserDto;
import chain.cloud.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@Value("${server.port}")
	private int serverPort = 0;
	
	@GetMapping("/users")
	public List<UserDto> users(){
		List<User> users = userService.getAll();
		return users.stream().map((user)->{
			UserDto dto = new UserDto();
			dto.setServerPort(serverPort);
			dto.setUser(user);
			return dto;
		}).collect(Collectors.toList());
	}
	@GetMapping("/users/{id}")
	public UserDto getUser(@PathVariable("id") int id) {
		return new UserDto(userService.getOne(id),serverPort);
	}

	@PostMapping("/users/{id}")
	public void modifyUser(@RequestBody User user){
		UserDto dto = new UserDto();
		dto.setUser(user);
		userService.save(dto);
	}

}
