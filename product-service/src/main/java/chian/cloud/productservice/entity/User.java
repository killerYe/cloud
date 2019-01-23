package chian.cloud.productservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name="tbUser")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String nickname;
	private String avatar;
	
	public User() {}

	public User(int id, String nickname, String avatar) {
		this.id = id;
		this.nickname = nickname;
		this.avatar = avatar;
	}
}
