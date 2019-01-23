package chian.cloud.productservice.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name="tbProduct_Comment")
@Entity
public class Comment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	@Column(name = "product_id")
	private int productId;
	@Column(name="author_id")
	private int authorId;
	@Column(name="content")
	private String content;
	@Column(name="created")
	private Date created;
}
