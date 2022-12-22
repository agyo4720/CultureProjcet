package web.culture.kakaoPay;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import web.culture.dto.SubmitDto;
import web.culture.entity.User;
import web.culture.service.EntryService;

@Slf4j
@RequiredArgsConstructor
@Controller
public class KakaoPayController {
	private final EntryService entryService;
	
	@Setter(onMethod_ = @Autowired)
	private KakaoPay kakaopay;
	
	@GetMapping("/kakaoPay")
	public void kakaoPayGet() {}
	
	@PostMapping("/kakaoPay")
	public String kakaoPay(HttpSession session) {
		log.info(">>> KakaoPay POST");
		
		User user = (User) session.getAttribute("user");
		SubmitDto dto = (SubmitDto) session.getAttribute("submitDto");
		
		log.info(">>> " + user);
		log.info(">>> " + dto);
		
		return "redirect:" + kakaopay.kakaoPayReady(session);
	}
	
	@GetMapping("/kakaoPaySuccess")
	public String kakaoPaySuccess(@RequestParam("pg_token") String pg_token
								, Model model
								, HttpSession session
								) {
		
		User user = (User) session.getAttribute("user");
		SubmitDto dto = (SubmitDto) session.getAttribute("submitDto");
		
		log.info(">>> KakaoPay Success GET");
		log.info(">>> KakaoPay Success pg_token : " + pg_token);
		
		this.entryService.entryAdd(user, dto.getPrgCode());
		model.addAttribute("info", kakaopay.kakaoPayInfo(pg_token, session));
		
		session.invalidate();
		
		return 	"kakaoPaySuccess";
	}
	
	@GetMapping("/kakaoPayCancel")
	public String kakaoPayCancel() {
			return "kakaoPayCancel";
	}
	
	@GetMapping("/kakaoPaySuccessFail")
	public void kakaoPaySuccessFail() {}
}
