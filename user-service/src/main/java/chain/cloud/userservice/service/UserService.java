package chain.cloud.userservice.service;

import java.util.List;

import chain.cloud.userservice.entity.User;
import chain.cloud.userservice.entity.UserDto;

public interface UserService {
	
	List<User> getAll();
	
	User getOne(int id);

	User save(UserDto userDto);
}
