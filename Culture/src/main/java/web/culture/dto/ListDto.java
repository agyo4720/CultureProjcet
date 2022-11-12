package web.culture.dto;

import java.sql.Time;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ListDto {
	private String prgCode;
	
	private String subject;
	
	private String day;
	
	private Time classStart;
	
	private Time classEnd;
	
	private String person;
	
	private int countCurrent;
	
	private int countMax;
	
	private int payment;
	
	private String paymentState;
}
