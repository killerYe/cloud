package chian.cloud.productservice.entity;

import lombok.Data;

@Data
public class CommentDto {
	private Comment comment;
	private UserDto author;
}
