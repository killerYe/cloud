package chain.cloud.userservice.service.impl;

import java.util.List;

import chain.cloud.userservice.entity.User;
import chain.cloud.userservice.repository.UserRepository;
import chain.cloud.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<User> getAll() {
		List<User> users = userRepository.findAll();
		return users;
	}

	@Override
	public User getOne(int id) {
		return userRepository.findById(id);
	}

}
