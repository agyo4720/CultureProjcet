package web.culture.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import web.culture.entity.Entry;
import web.culture.service.EntryService;

@RequiredArgsConstructor
@RequestMapping("/entry")
@Controller
public class EntryController {
	private final EntryService entryService;
	
//	수강 내역 페이지
	@RequestMapping("/list")
	public String prgEntryGET(Model model) {
		
		List<Entry> list = this.entryService.getList();
		model.addAttribute("eList", list);
		
		return "entry/list";
	}
}
