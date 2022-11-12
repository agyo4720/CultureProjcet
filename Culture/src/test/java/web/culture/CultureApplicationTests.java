package web.culture;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import web.culture.entity.Entry;
import web.culture.entity.Program;
import web.culture.entity.User;
import web.culture.repository.EntryRepository;
import web.culture.repository.ProgramRepository;
import web.culture.repository.UserRepository;

@SpringBootTest
class CultureApplicationTests {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProgramRepository programRepository;
	
	@Autowired
	private EntryRepository entryRepository;
	
//	유저 생성
	@Test
	public void userCreate() {
		User user = new User();
		user.setUserId("user");
		user.setUserPw("asdf1234");
		user.setUserName("이유저");
		user.setBirth(Date.valueOf("1996-01-01"));
		user.setGender("M");
		user.setAddress("부산");
		user.setEmail("user@gmail.com");
		user.setTel("01012341234");
		
		this.userRepository.save(user);
	}
	
//	유저 삭제
	@Test
	public void userDelete() {
		List<User> list = this.userRepository.findAll();
		List<Program> pList = this.programRepository.findAll();
		pList.get(0).setCountCurrent(pList.get(0).getCountCurrent() - 1);
		this.userRepository.delete(list.get(0));
		this.programRepository.save(pList.get(0));
	}
	
//	프로그램 생성
	@Test
	public void prgCreate() {
		Program prg = new Program();
		prg.setPrgCode("A002");
		prg.setSubject("수영");
		prg.setDay("월화수");
		prg.setClassType("A");
		prg.setClassStart(Time.valueOf("06:00:00"));
		prg.setClassEnd(Time.valueOf("06:50:00"));
		prg.setPerson("성인");
		prg.setCountCurrent(0);
		prg.setCountMax(90);
		prg.setPayment(27000);
		prg.setPaymentType("실버");
		this.programRepository.save(prg);
	}
	
//	프로그램 삭제
	@Test
	public void prgDelete() {
		List<Program> list = this.programRepository.findAll();
		this.programRepository.delete(list.get(1));
	}
	
//	엔트리 생성
	@Test
	public void entryCreate() {
		User user = this.userRepository.findAll().get(0);
		List<Program> pList = this.programRepository.findAll();
		
		Entry entry = new Entry();
		entry.setSubmitStart(LocalDate.now().plusDays(1));
		entry.setSubmitEnd(LocalDate.now().plusDays(31));
		entry.setUser(user);
		entry.setProgram(pList.get(0));
		pList.get(0).setCountCurrent(pList.get(0).getCountCurrent() + 1);
		
		this.entryRepository.save(entry);
		this.programRepository.save(pList.get(0));
	}
	
//	엔트리 삭제
	@Test
	public void entryDelete() {
		List<Entry> eList = this.entryRepository.findAll();
		List<Program> pList = this.programRepository.findAll();
		pList.get(0).setCountCurrent(pList.get(0).getCountCurrent() - 1);
		this.entryRepository.delete(eList.get(0));
		this.programRepository.save(pList.get(0));
	}
}
