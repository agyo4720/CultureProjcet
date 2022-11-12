package web.culture.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import web.culture.dto.ListDto;
import web.culture.dto.SearchDto;
import web.culture.dto.SubmitDto;
import web.culture.kakaoPay.KakaoPay;
import web.culture.service.ProgramService;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/program")
@Controller
public class ProgramController {
	private final ProgramService programService;
	
	@Setter(onMethod_=@Autowired)
	private KakaoPay kakaopay;
	
//	수강 신청(검색) 페이지
	@RequestMapping("/list")
	public String prgSearchGET(Model model, SearchDto searchDto) {
		log.info(">>> GET /program/list");
		
		dropList(model);
		
		List<ListDto> findList = this.programService.prgListTB();
		model.addAttribute("findList", findList);
		
		return "program/list";
	}
	
	@RequestMapping("/list.do")
	public String prgListGET(Model model, SearchDto searchDto) {
		log.info(">>> GET /program/list.do");
		
		dropList(model);
		
		List<ListDto> findList = this.programService.prgSearchListTB(searchDto);
		model.addAttribute("findList", findList);
		
		return "program/list";
	}
	
//	수강 신청 페이지
	@RequestMapping(value="/submit/{prgCode}", method=RequestMethod.GET)
	public String prgSubmitGET(Model model
			, @PathVariable("prgCode") String prgCode
			, SubmitDto submitDto
			, HttpSession session
			) {
		submitDto = this.programService.prgSubmit(prgCode);
		
		session.setAttribute("submitDto", submitDto);
		model.addAttribute("submitDto", submitDto);
		
		log.info(">>> " + submitDto);
		
		return "program/submit";
	}
	
	@RequestMapping(value="/submit/{prgCode}", method=RequestMethod.POST)
	public String prgSubmitPOST() {
		return "redirect:/kakaoPay";
	}
	
//	Select 드롭
	private void dropList(Model model) {
		model.addAttribute("dropSubject", dropSubject());
		model.addAttribute("dropDay", dropDay());
		model.addAttribute("dropStart", dropStart());
		model.addAttribute("dropEnd", dropEnd());
		model.addAttribute("dropPerson", dropPerson());
		model.addAttribute("dropPayment", dropPayment());
	}
	
	private List<String> dropSubject() {
		return Arrays.asList("수영", "배드민턴", "탁구");
	}
	
	private List<String> dropDay() {
		return Arrays.asList("주 5일", "월수금", "화목");
	}
	
	private List<String> dropStart() {
		return Arrays.asList("06:00"
				, "07:00"
				, "08:00"
				, "09:00"
				, "10:00"
				, "11:00"
				, "12:00"
				, "13:00"
				, "14:00"
				, "15:00"
				, "16:00"
				, "17:00"
				, "18:00"
				, "19:00"
				, "20:00"
				, "21:00"
				, "22:00"
				);
	}
	
	private List<String> dropEnd() {
		return Arrays.asList("06:50"
				, "07:50"
				, "08:50"
				, "09:50"
				, "10:50"
				, "11:50"
				, "12:50"
				, "13:50"
				, "14:50"
				, "15:50"
				, "16:50"
				, "17:50"
				, "18:50"
				, "19:50"
				, "20:50"
				, "21:50"
				, "22:50"
				);
	}
	
	private List<String> dropPerson() {
		return Arrays.asList("성인", "청소년");
	}
	
	private List<Integer> dropPayment() {
		return Arrays.asList(35000, 42000, 18000);
	}
}
