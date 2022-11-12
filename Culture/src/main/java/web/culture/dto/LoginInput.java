package web.culture.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LoginInput {
	private String userId;
	
	private String userPw;
}
