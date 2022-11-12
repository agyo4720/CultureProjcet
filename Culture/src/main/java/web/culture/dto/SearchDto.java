package web.culture.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SearchDto {
//	private int pageNum;
//	private int amount;
	
	private String subject;
	private String day;
	private String classStart;
	private String classEnd;
	private String person;
	private int payment;
	
//	public SearchDto() {
//		this(1, 10);
//	}
	
//	public SearchDto(int pageNum, int amount) {
//		this.pageNum = pageNum;
//		this.amount = amount;
//	}
}
