package web.culture.service;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import web.culture.dto.ListDto;
import web.culture.dto.SearchDto;
import web.culture.dto.SubmitDto;
import web.culture.entity.Program;
import web.culture.repository.ProgramRepository;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProgramService {
	private final ProgramRepository programRepository;
	
	public List<Program> getList() {
		return this.programRepository.findAll();
	}
	
//	모든 프로그램 리스트
	public List<ListDto> prgListTB() {
		log.info(">>> prgFindList()");
		
		List<ListDto> findList = new ArrayList<ListDto>();
		
		for(Program p : getList()) {
			ListDto dto = new ListDto();
			dto.setPrgCode(p.getPrgCode());
			dto.setSubject(p.getSubject());
			dto.setDay(p.getDay());
			dto.setClassStart(p.getClassStart());
			dto.setClassEnd(p.getClassEnd());
			dto.setPerson(p.getPerson());
			dto.setCountCurrent(p.getCountCurrent());
			dto.setCountMax(p.getCountMax());
			dto.setPayment(p.getPayment());
			findList.add(dto);
		}
		return findList;
	}

//	프로그램 검색 리스트
	public List<ListDto> prgSearchListTB(SearchDto searchDto) {
		log.info(">>> prgFindList()");
		
		log.info(">>> " + searchDto);
		
		List<Program> list = getList();
		List<ListDto> findList = new ArrayList<ListDto>();
		
		for(int i = 0; i < list.size(); i++) {
			ListDto dto = new ListDto();
			if(list.get(i).getSubject().equals(searchDto.getSubject())
				|| list.get(i).getDay().equals(searchDto.getDay())
				|| list.get(i).getClassStart().equals(Time.valueOf(searchDto.getClassStart()))
				|| list.get(i).getClassEnd().equals(Time.valueOf(searchDto.getClassEnd()))
				|| list.get(i).getPerson().equals(searchDto.getPerson())
				|| list.get(i).getPayment().equals(searchDto.getPayment())
				) {
				dto.setPrgCode(list.get(i).getPrgCode());
				dto.setSubject(list.get(i).getSubject());
				dto.setDay(list.get(i).getDay());
				dto.setClassStart(list.get(i).getClassStart());
				dto.setClassEnd(list.get(i).getClassEnd());
				dto.setPerson(list.get(i).getPerson());
				dto.setPerson(list.get(i).getPerson());
				dto.setCountCurrent(list.get(i).getCountCurrent());
				dto.setCountMax(list.get(i).getCountMax());
				dto.setPayment(list.get(i).getPayment());
				findList.add(dto);
			}
		}
		
		return findList;
	}
	
//	수강 결제하기
	public SubmitDto prgSubmit(String prgCode) {
		log.info(">>> prgSubmit");
		
		List<Program> list = getList();
		SubmitDto submitDto = new SubmitDto();
		
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getPrgCode().equals(prgCode)) {
				submitDto.setPrgCode(list.get(i).getPrgCode());
				submitDto.setSubject(list.get(i).getSubject());
				submitDto.setDay(list.get(i).getDay());
				submitDto.setClassStart(list.get(i).getClassStart());
				submitDto.setClassEnd(list.get(i).getClassEnd());
				submitDto.setPerson(list.get(i).getPerson());
				submitDto.setPayment(list.get(i).getPayment());
				submitDto.setStartDate(LocalDate.now().plusDays(1));
				submitDto.setEndDate(LocalDate.now().plusDays(30));
			}
		}
		
		log.info(">>> " + list);
		log.info(">>> " + submitDto);
		
		return submitDto;
	}
}
