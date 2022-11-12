package web.culture.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import web.culture.entity.Program;

@Repository
public interface ProgramRepository extends JpaRepository<Program, String>{
	public Optional<Program> findByPrgCode(String prgCode);
	
}
