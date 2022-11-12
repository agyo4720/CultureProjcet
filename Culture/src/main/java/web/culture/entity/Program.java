package web.culture.entity;

import java.sql.Time;
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
@Table(name="PROGRAM_TB")
public class Program {
	@Id
	@Column(length=10, nullable=false)
	private String prgCode;
	
	@Column(length=60, nullable=false)
	private String subject;
	
	@Column(length=60, nullable=false)
	private String day;
	
	@Column(nullable=false)
	private String classType;
	
	@Column(nullable=false)
	private Time classStart;
	
	@Column(nullable=false)
	private Time classEnd;
	
//	대상
	@Column(length=30, nullable=false)
	private String person;
	
//	현재 인원 수
	@Column(length=2, nullable=false)
	private Integer countCurrent;
	
//	최대 인원 수
	@Column(length=2, nullable=false)
	private Integer countMax;
	
//	참가비
	@Column(length=5, nullable=false)
	private Integer payment;
	
//	참가 타입
	@Column(length=30, nullable=false)
	private String paymentType;
	
	@OneToMany(mappedBy="program", cascade=CascadeType.REMOVE)
	private List<Entry> entryList;
}
