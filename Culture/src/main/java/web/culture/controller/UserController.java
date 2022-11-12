package web.culture.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import web.culture.dto.JoinInput;
import web.culture.dto.LoginInput;
import web.culture.entity.User;
import web.culture.service.UserService;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
@Controller
public class UserController {
	private final UserService userService;
	
//	로그인
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(LoginInput loginInput) {
		
		log.info(">>> GET /user/login");
		log.info(">>> loginInput : " + loginInput);
		
		return "user/login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String loginPOST(HttpSession session
							, RedirectAttributes rttr
							, LoginInput loginInput) {
		User user = this.userService.loginCheck(loginInput);

		if(user!= null) {
			session.setAttribute("user", user);
			
			log.info(">>> POST /user/login");
			log.info(">>> loginInput : " + loginInput);
			log.info(">>> user : " + user);
			
			return "redirect:/";
		} else {
			log.info(">>> loginInput : " + loginInput);
			log.info(">>> user : " + user);
			
			rttr.addFlashAttribute("msg","false");
			
			return "redirect:/user/login";
		}
	}
	
//	로그아웃
	@RequestMapping("/logout")
	public String logoutGET(HttpSession session) {
		User user = (User) session.getAttribute("user");
		
		session.invalidate();
		
		log.info(">>> LOGOUT " + user);
		
		return "redirect:/ ";
	}
	
//	회원가입
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String joinGET() {
		return "user/join";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String joinPOST(@ModelAttribute JoinInput joinInput) {
		this.userService.userJoin(joinInput);
		return "redirect:/user/login";
	}
	
}
