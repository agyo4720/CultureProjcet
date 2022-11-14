package web.culture.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="USER_TB")
public class User implements Serializable {
	private static final long serialVersionUID = 1565331648158792575L;

	@Id
	@Column(length=20, nullable=false)
	private String userId;
	
	@Column(length=16, nullable=false)
	private String userPw;
	
	@Column(length=20, nullable=false)
	private String userName;
	
	@Column(nullable=false)
	private Date birth;
	
	@Column(length=1, nullable=false)
	private String gender;
	
	@Column(length=300, nullable=false)
	private String address;
	
	@Column(length=320)
	private String email;
	
	@Column(length=20, nullable=false)
	private String tel;
	
	@OneToMany(mappedBy="user", cascade = CascadeType.REMOVE)
	private List<Entry> entryList;
}
