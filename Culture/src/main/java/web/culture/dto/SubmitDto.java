package web.culture.dto;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SubmitDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private String prgCode;
	
	private String subject;
	
	private String day;
	
	private Time classStart;
	
	private Time classEnd;
	
	private String person;
	
	private LocalDate startDate;
	
	private LocalDate endDate;
	
	private int payment;
	
//	private int countCurrent = 1;
}
