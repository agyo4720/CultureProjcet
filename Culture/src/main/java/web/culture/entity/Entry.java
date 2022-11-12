package web.culture.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="ENTRY_TB")
public class Entry {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, 
		generator = "ENTRY_SEQ")
	@SequenceGenerator(sequenceName = "entId", 
		allocationSize = 1, name = "ENTRY_SEQ")
	private Integer entId;
	
	@Column
	private String payCode;
	
	@Column(nullable=false)
	private LocalDate submitStart;
	
	@Column(nullable=false)
	private LocalDate submitEnd;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Program program;
}
