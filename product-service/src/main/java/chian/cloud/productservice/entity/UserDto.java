package chian.cloud.productservice.entity;

import lombok.Data;

@Data
public class UserDto {
	private User user;
	private int serverPort;

	public UserDto(){}

	public UserDto(User user, int serverPort) {
		this.user = user;
		this.serverPort = serverPort;
	}
}
