package chain.cloud.userservice.service;

import java.util.List;

import chain.cloud.userservice.entity.User;

public interface UserService {
	
	List<User> getAll();
	
	User getOne(int id);
	
}
