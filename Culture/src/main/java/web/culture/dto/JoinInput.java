package web.culture.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class JoinInput {
	private String userId;
	
	private String userPw;
	
	private String userName;
	
	private String birthYear;
	
	private String birthMonth;
	
	private String birthDay;
	
	private String gender;
	
	private String postCode;
	
	private String address;
	
	private String detailAddress;
	
	private String extraAddress;
	
	private String email;
	
	private String tel;
}
