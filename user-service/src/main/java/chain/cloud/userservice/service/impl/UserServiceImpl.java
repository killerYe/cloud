package chain.cloud.userservice.service.impl;

import java.util.List;

import brave.Tracer;
import chain.cloud.userservice.entity.User;
import chain.cloud.userservice.entity.UserDto;
import chain.cloud.userservice.mq.UserMsg;
import chain.cloud.userservice.mq.UserMsgSender;
import chain.cloud.userservice.repository.UserRepository;
import chain.cloud.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMsgSender userMsgSender;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private Tracer tracer;
	@Override
	public List<User> getAll() {
		List<User> users = userRepository.findAll();
		return users;
	}

	@Override
	public User getOne(int id) {
		return userRepository.findById(id);
	}

	@Override
	public User save(UserDto userDto) {
		User user = this.userRepository.findById(userDto.getUser().getId());
		if(user==null){
			user = new User();
		}
		user.setNickname(userDto.getUser().getNickname());
		user.setAvatar(userDto.getUser().getAvatar());
		user = this.userRepository.save(user);
//		this.userMsgSender()
		this.sendMsg(UserMsg.UA_UPDATE,user.getId());
		return user;
	}

	public void delete(int id){
		this.userRepository.deleteById(id);
		this.sendMsg(UserMsg.UA_DELETE,id);
	}

	public void sendMsg(String action,int userId){
		this.userMsgSender.sendMsg(new UserMsg(action,userId,this.getTracerId()));
	}

	public String getTracerId(){
		return this.tracer.currentSpan().context().traceIdString();
	}

}
