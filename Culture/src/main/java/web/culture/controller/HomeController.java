package web.culture.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import web.culture.entity.Entry;
import web.culture.entity.Program;
import web.culture.entity.User;
import web.culture.service.EntryService;
import web.culture.service.ProgramService;
import web.culture.service.UserService;

@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeController {
	private final UserService userService;
	private final ProgramService programService;
	private final EntryService entryService;
	
	@RequestMapping("/")
	public String home() {
		log.info(">>> GET /root");
		return "home";
	}
	
//	Entity 값 확인
	@RequestMapping("/list")
	public String list(Model model) {
		List<User> uList = this.userService.getList();
		model.addAttribute("uList", uList);
		
		List<Program> pList = this.programService.getList();
		model.addAttribute("pList", pList);
		
		List<Entry> eList = this.entryService.getList();
		model.addAttribute("eList", eList);
		
		return "list";
	}
}
