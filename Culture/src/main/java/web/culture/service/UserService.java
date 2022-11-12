package web.culture.service;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import web.culture.dto.JoinInput;
import web.culture.dto.LoginInput;
import web.culture.entity.User;
import web.culture.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class UserService {
	private final UserRepository userRepository;
	
	public List<User> getList() {
		return this.userRepository.findAll();
	}
	
//	로그인
	public User loginCheck(LoginInput loginInput) {
		List<User> list = getList();
		
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getUserId().equals(loginInput.getUserId())
				&& list.get(i).getUserPw().equals(loginInput.getUserPw())) {
				return list.get(i);
			}
		}
		
		return null;
	}
	
//	회원가입
	public User userJoin(JoinInput joinInput) {
		String birth
			= joinInput.getBirthYear() + "-"
			+ joinInput.getBirthMonth() + "-"
			+ joinInput.getBirthDay();
		
		String address
			= joinInput.getPostCode() + " "
			+ joinInput.getAddress() + " "
			+ joinInput.getDetailAddress() + " "
			+ joinInput.getExtraAddress();
		
		User user = new User();
		user.setUserId(joinInput.getUserId());
		user.setUserPw(joinInput.getUserPw());
		user.setUserName(joinInput.getUserName());
		user.setBirth(Date.valueOf(birth));
		user.setGender(joinInput.getGender());
		user.setAddress(address);
		user.setEmail(joinInput.getEmail());
		user.setTel(joinInput.getTel());
		
		this.userRepository.save(user);
		
		return user;
	}

}
