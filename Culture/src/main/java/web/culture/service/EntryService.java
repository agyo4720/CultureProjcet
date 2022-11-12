package web.culture.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import web.culture.entity.Entry;
import web.culture.entity.Program;
import web.culture.entity.User;
import web.culture.repository.EntryRepository;

@RequiredArgsConstructor
@Service
public class EntryService {
	private final EntryRepository entryRepository;
	private final ProgramService programService;
	
	public List<Entry> getList() {
		return this.entryRepository.findAll();
	}
	
	public Entry entryAdd(User user, String prgCode) {
		List<Program> list = this.programService.getList();
		Program program = null;
		
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getPrgCode().equals(prgCode)) {
				program = list.get(i);
				list.get(i).setCountCurrent(list.get(i).getCountCurrent() + 1);
			}
		}
		
		Entry entry = new Entry();
		entry.setSubmitStart(LocalDate.now().plusDays(1));
		entry.setSubmitEnd(LocalDate.now().plusDays(31));
		entry.setUser(user);
		entry.setProgram(program);
		this.entryRepository.save(entry);
		
		return entry;
	}
	
}
